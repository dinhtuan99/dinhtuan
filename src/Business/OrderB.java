package Business;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import Da.OrderDA;
import entity.Order;

public class OrderB {
	private OrderDA orderDA;
	private String dateS;
	public OrderB(){
		orderDA = new OrderDA();
	}
	private String formatDate(Date date) {
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
		return format.format(date);
	}
	public DefaultTableModel getAllOrder() throws SQLException, ClassNotFoundException {
		List<Order> orders = orderDA.getOrder();
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("ID");
		model.addColumn("Thời gian mua");
		model.addColumn("Tổng tiền");
		model.addColumn("Người bán");

		for (Order order : orders) {
			String[] row = new String[4];
			row[0] = String.valueOf(order.getId());
			row[1] = String.valueOf(order.getTime());
			row[2] = String.valueOf(order.getTotal());
			row[3] = String.valueOf(order.getEmployeeID());
			model.addRow(row);
		}
		return model;
	}
	public void addNewCustomer(String username, String password) throws ParseException, SQLException, ClassNotFoundException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		Date date2 = format.parse(date);
		dateS = format.format(new Date());
		orderDA.getEmployeeID(username, password);
		orderDA.addOrder(dateS);
		System.out.println(dateS);
	}
	public int getOrderID() throws ClassNotFoundException, SQLException {
		return orderDA.getOrderID(dateS);
	}
	public void addSum(double sum) throws ClassNotFoundException, SQLException {
		orderDA.addSum(getOrderID(), sum);
	}
}
