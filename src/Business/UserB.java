package Business;

import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import Da.UserDA;
import entity.User;

public class UserB {
	private UserDA userDA;
	public String user;
	public String pass;
	public String getUser() {
		return user;
	}

	public String getPass() {
		return pass;
	}

	public UserB() {
		// TODO Auto-generated constructor stub
		userDA = new UserDA();
	}
	
	public DefaultTableModel getUsers() throws SQLException, ClassNotFoundException {
		List<User> list = userDA.getUsers();
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("Mã ID");
		model.addColumn("Tên đăng nhập");
		model.addColumn("Mật khẩu");
		model.addColumn("Tên nhân viên");
		model.addColumn("Email");
		model.addColumn("Địa chỉ");
		model.addColumn("Vị trí");
		try {
	

			for (User user : list) {
				String row[] = new String[7];

				row[0] = String.valueOf(user.getId());
				row[1] = user.getUser();
				row[2] = user.getPassword();
				row[3] = user.getName();
				row[4] = user.getEmail();
				row[5] = user.getAddress();
				row[6] = user.getRole();

				model.addRow(row);
				
			}
		} catch (NullPointerException e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, "Database trống trơn????");

		}

		return model;
	}
	
	public boolean checkLogin(String username, String password) throws ClassNotFoundException, SQLException{
		return userDA.checkUser(username, password);
	}
	public void getID(String username, String password) {
		user = username;
		pass = password;
	}
	public void addNewUser(int id,String username, String passwords,String name, String email,String address, String role) throws SQLException {
		userDA.addNewUser(id, username, passwords, name, email, address, role);
		}
	public void deleteUser(String id) throws SQLException {
		userDA.deleteUser(Integer.parseInt(id));
	}
	
	
	public void editUser(int userId, String username, String passwords,String name, String email,String address, String role) throws SQLException {
		userDA.editUser(userId, username, passwords, name, email, address, role);
	}
	
}
