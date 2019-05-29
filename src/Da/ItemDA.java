package Da;

import java.util.List;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;

import entity.Item;

public class ItemDA {
	private static Connection con = null;
	public List<Item> getAll() throws SQLException, ClassNotFoundException{
		List<Item> list = new ArrayList<Item>();
		String sql = "SELECT * FROM `items`";
		PreparedStatement statement = getConnection().prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery(sql);
		while (resultSet.next()) {
			Item item = new Item(resultSet.getInt(1), resultSet.getString(2),resultSet.getInt(3),resultSet.getDouble(4));
			list.add(item);
		}
		return list;
	}
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
	public int getQuantity(String itemName) throws SQLException, ClassNotFoundException {	
		String sql1 = "SELECT quantity FROM items WHERE name = ?";
		PreparedStatement stmt1 = getConnection().prepareStatement(sql1);
		stmt1.setString(1, itemName);
		ResultSet rs = stmt1.executeQuery();
		int x = 0;
		while(rs.next())
			x = rs.getInt(1);
		return x;	
	}
	public int getQuantity2(int id) throws SQLException, ClassNotFoundException {	
		String sql1 = "SELECT quantity FROM items WHERE id = ?";
		PreparedStatement stmt1 = getConnection().prepareStatement(sql1);
		stmt1.setInt(1, id);
		ResultSet rs = stmt1.executeQuery();
		int x = 0;
		while(rs.next())
			x = rs.getInt(1);
		return x;	
	}
	public void updateItem(int quantity, int id) throws ClassNotFoundException, SQLException {
		int x = getQuantity2(id) - quantity;
		
		String sql2 = "UPDATE items SET quantity = ? WHERE id = ?";
		PreparedStatement stmt2 = getConnection().prepareStatement(sql2);
		stmt2.setInt(1, x);
		stmt2.setInt(2,id );
		stmt2.executeUpdate();
	}
	public int getNum() throws ClassNotFoundException, SQLException {
		int x = 0;
		
		String sql2 = "SELECT id from items ";
		PreparedStatement stmt2 = getConnection().prepareStatement(sql2);
		ResultSet rs = stmt2.executeQuery();
		while(rs.next())
			x++;
		return x;
	}
	public void editItem(int id,String name, int quantity, double price) throws SQLException {
		String sql = "UPDATE `items` SET `id` = ?, `name` = ?, `quantity` = ?, `price` = ?  WHERE `items`.`id` = ?;";
		PreparedStatement statement = con.prepareStatement(sql);
		statement.setInt(1,id);
		statement.setString(2, name);
		statement.setInt(3, quantity);
		statement.setDouble(4,price);
		statement.setInt(5,id);
		
		statement.executeUpdate();
	}
	public int getItemID(String itemName) throws ClassNotFoundException, SQLException {
		String sql1 = "SELECT id FROM items WHERE name = ?";
		PreparedStatement stmt1 = getConnection().prepareStatement(sql1);
		stmt1.setString(1, itemName);
		ResultSet rs = stmt1.executeQuery();
		int x =0;
		while(rs.next())
			x = rs.getInt(1);
		return x;
	}
	public double getPrice(int id) throws SQLException, ClassNotFoundException {
		String sql1 = "SELECT price FROM items WHERE id = ?";
		PreparedStatement stmt1 = getConnection().prepareStatement(sql1);
		stmt1.setInt(1, id);
		ResultSet rs = stmt1.executeQuery();
		int x =0;
		while(rs.next())
			x = rs.getInt(1);
		return x;
	}
	public void addNewItem(int id,String name, int quantity, double price)
			throws SQLException, ClassNotFoundException {
		String sql = "INSERT INTO `items` (`id`, `name`, `quantity`, `price`) VALUES (?, ?, ?, ?);";
		PreparedStatement	statement = getConnection().prepareStatement(sql);
		statement.setInt(1,id);
		statement.setString(2, name);
		statement.setInt(3, quantity);
		statement.setDouble(4,price);
		

		statement.executeUpdate();
	}
	public void deleteItem(int id) throws SQLException {
		String sql = "DELETE FROM `items` WHERE `items`.`id` = ?";
		PreparedStatement statement = con.prepareStatement(sql);
		statement.setInt(1, id);
		statement.executeUpdate();
	}
}
