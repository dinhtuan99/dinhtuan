package Da;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.List;

import entity.OrderDetail;

public class OrderDetailDA {
	private static Connection con = null;
	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		if (con == null) {
			Class.forName("com.mysql.jdbc.Driver");
			// tao doi tuogn ket noi
			String db_url = "jdbc:mysql://localhost/test1";
			String db_user = "root";
			String db_pass = "123456";	
			con = DriverManager.getConnection(db_url, db_user, db_pass);
		}
		return con;
	}
	
	public List<OrderDetail> getOrderDetail() throws SQLException, ClassNotFoundException {
		List<OrderDetail> list = new ArrayList<OrderDetail>();
	
		
		String sql = "SELECT * FROM `orders_detail`";
		PreparedStatement	statement = getConnection().prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery(sql);
		while (resultSet.next()) {
			OrderDetail orderDetail = new OrderDetail(resultSet.getInt(1), resultSet.getInt(2),resultSet.getInt(3),resultSet.getInt(4));
			list.add(orderDetail);
		}
		return list;
	}
	public int getItemID(String itemName) throws ClassNotFoundException, SQLException {
		String sql = "SELECT id FROM items WHERE name = ?";
		PreparedStatement stmt = getConnection().prepareStatement(sql);
		stmt.setString(1, itemName);
		ResultSet rs = stmt.executeQuery();
		while(rs.next())
			return rs.getInt(1);
		return 0;
	}
	public double getItemPrice(String itemName)  throws ClassNotFoundException, SQLException{
		String sql = "SELECT price FROM items WHERE name = ?";
		PreparedStatement stmt = getConnection().prepareStatement(sql);
		stmt.setString(1, itemName);
		ResultSet rs = stmt.executeQuery();
		while(rs.next())
			return rs.getDouble(1);
		return 0;
	}
	public void addOrder(int itemID, int orderID, double price, int quantity) throws SQLException, ClassNotFoundException {
		String sql = "INSERT INTO orders_detail(item_id, order_id , quantity, price) VALUES(?,?,?,?)";
		PreparedStatement stmt = getConnection().prepareStatement(sql);
		stmt.setInt(1,itemID);
		stmt.setInt(2, orderID);
		stmt.setInt(3, quantity);
		stmt.setDouble(4, price);
		
		stmt.executeUpdate();
	}
	public double getSum(int orderID) throws ClassNotFoundException, SQLException {
		String sql = "SELECT SUM(price) FROM orders_detail WHERE order_id = ?";
		PreparedStatement stmt = getConnection().prepareStatement(sql);
		stmt.setInt(1, orderID);
		ResultSet rs = stmt.executeQuery();
		while(rs.next())
			return rs.getDouble(1);
		return 0;
	}
	
}
