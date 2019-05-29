package Da;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.List;

import Business.UserB;
import entity.Order;

public class OrderDA {
	private static Connection con = null;
	private int i = 1;
	private int employeeID;
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
	public List<Order> getOrder() throws SQLException, ClassNotFoundException {
		List<Order> list = new ArrayList<Order>();
	
		String sql = "SELECT * FROM `orders`";
		PreparedStatement	statement = getConnection().prepareStatement(sql);
		
		ResultSet resultSet = statement.executeQuery();
		while (resultSet.next()) {
			Order order = new Order(resultSet.getInt(1), resultSet.getString(2),resultSet.getInt(3),resultSet.getInt(4));
			list.add(order);
		}
		return list;
	}
	public void getEmployeeID(String username, String password) throws ClassNotFoundException, SQLException {
			String sql = "SELECT id FROM users WHERE username = ? AND passwords = ?";
			PreparedStatement stmt = getConnection().prepareStatement(sql);
			stmt.setString(1, username);
			stmt.setString(2, password);
			
			ResultSet rs = stmt.executeQuery();
			while(rs.next())
				this.employeeID = rs.getInt(1);
	}
	public void addOrder(String date) throws SQLException, ClassNotFoundException {
		String sql = "INSERT INTO orders(id,times,total,employee_id) VALUES(null,?,?,?)";
		PreparedStatement stmt = getConnection().prepareStatement(sql);
		stmt.setString(1, date);
		stmt.setDouble(2, 0);
		stmt.setInt(3, employeeID);
		
		stmt.executeUpdate();
	}
	public void addSum(int oderID,double sum)throws SQLException, ClassNotFoundException{
		String sql = "UPDATE orders SET total = ? WHERE id = ?";
		PreparedStatement stmt = getConnection().prepareStatement(sql);
		stmt.setDouble(1, sum);
		stmt.setInt(2, oderID);
		stmt.executeUpdate();
		
	}
	public int getOrderID(String dateS) throws SQLException, ClassNotFoundException {
		String sql = "SELECT id From orders WHERE times = ?";
		PreparedStatement stmt = getConnection().prepareStatement(sql);
		stmt.setString(1, dateS);
		ResultSet rs = stmt.executeQuery();
		while(rs.next())
			return rs.getInt(1);
		return 0;
		
	}
//	public void addOrderDeTail(int quantity, String itemName) {
//		String sql = "INSERT INTO order(id,order_id,item_id,quantity) VALUES(null,?,?)";
//		
//	}
}
