package Business;

import java.sql.SQLException;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import Da.OrderDetailDA;
import entity.OrderDetail;

public class OrderDetailB {
	private OrderDetailDA orderDetailDA;
	public OrderDetailB(){
		orderDetailDA = new OrderDetailDA();
	}
	
	public DefaultTableModel getAllOrder() throws SQLException, ClassNotFoundException {
		List<OrderDetail> orderDetails = orderDetailDA.getOrderDetail();
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("ID");
		model.addColumn("Mã hoá đơn");
		model.addColumn("Số lượng");
		model.addColumn("Thành tiền");

		for (OrderDetail orderDetail : orderDetails) {
			String[] row = new String[4];
			row[0] = String.valueOf(orderDetail.getId());
			row[1] = String.valueOf(orderDetail.getItemId());
			row[2] = String.valueOf(orderDetail.getOrderId());
			row[3] = String.valueOf(orderDetail.getQuantity());
			model.addRow(row);
		}
		return model;
	}
	public int getItemID(String itemName) throws ClassNotFoundException, SQLException {
		return orderDetailDA.getItemID(itemName);
	}
	public double getItemPrice(String itemName) throws ClassNotFoundException, SQLException {
		return orderDetailDA.getItemPrice(itemName);
	}
	public void addOrderDetail(String itemName , int orderID, int quantity) throws ClassNotFoundException, SQLException {
		orderDetailDA.addOrder(getItemID(itemName), orderID, getItemPrice(itemName), quantity);
	}
	public double getSum(int orderID) throws ClassNotFoundException, SQLException {
		return orderDetailDA.getSum(orderID);
	}
}
