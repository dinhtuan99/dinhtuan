package Da;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


import java.util.ArrayList;
import java.util.List;

import entity.User;



public class UserDA{
	private static Connection con = null;

	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		if (con == null) {
			Class.forName("com.mysql.jdbc.Driver");
			// tao doi tuogn ket noi
			String db_url = "jdbc:mysql://localhost/test";
			String db_user = "root";
			String db_pass = "123456";		
			con = DriverManager.getConnection(db_url, db_user, db_pass);
		}
		return con;
	}
	public List<User> getUsers() throws SQLException, ClassNotFoundException {
		List<User> list = new ArrayList<>();
		
		String sql = "SELECT * FROM `users`";
		PreparedStatement	statement = getConnection().prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery(sql);
		while (resultSet.next()) {
			User user = new User(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
					resultSet.getString(4), resultSet.getString(5), resultSet.getString(6),resultSet.getString(7));
			list.add(user);
		}
		return list;
	}
	
	public boolean checkUser(String username, String password) throws ClassNotFoundException, SQLException {
		String sql = "SELECT * FROM users WHERE username = ? AND passwords = ?";
		PreparedStatement stmt = getConnection().prepareStatement(sql);
		stmt.setString(1, username);
		stmt.setString(2, password);
	
		ResultSet rs = stmt.executeQuery();
		if (rs.next())
			return true;
		
		return false;
	}
	public void addNewUser(int id,String username, String passwords,String name, String email,String address, String role)
			throws SQLException {
		String sql = "INSERT INTO `users` (`id`,`username`, `passwords`, `name`,`email`,`address`,`role`) VALUES (?,?, ?, ?, ?,?,?)";
		PreparedStatement statement = con.prepareStatement(sql);
		statement.setInt(1, id);
		statement.setString(2,username);
		statement.setString(3, passwords);
		statement.setString(4, name);
		statement.setString(5,email);
		statement.setString(6,address);
		statement.setString(7,role);
		

		statement.executeUpdate();
	}
	public void editUser(int userId, String username, String passwords,String name, String email,String address, String role) throws SQLException {
		String sql = "UPDATE `users` SET `id` = ?, `username` = ?, `passwords` = ?, `name` = ? , `email` = ?, `address` = ?, `role` = ?  WHERE `users`.`id` = ?;";
		PreparedStatement statement = con.prepareStatement(sql);
		statement.setInt(1,userId );
		statement.setString(2,username);
		statement.setString(3, passwords);
		statement.setString(4, name);
		statement.setString(5,email);
		statement.setString(6,address);
		statement.setString(7,role);
		statement.setInt(8,userId );
		
		statement.executeUpdate();
	}

	public void deleteUser(int id) throws SQLException {
		String sql = "DELETE FROM `users` WHERE `users`.`id` = ?";
		PreparedStatement statement = con.prepareStatement(sql);
		statement.setInt(1, id);
		statement.executeUpdate();
	}	
	

}