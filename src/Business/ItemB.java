package Business;

//import java.io.BufferedWriter;
//import java.io.File;
//import java.io.FileWriter;
//import java.io.IOException;
import java.sql.SQLException;
//import java.sql.Timestamp;
//import java.util.ArrayList;
//import java.util.Calendar;
import java.util.List;
//import java.util.Scanner;
//
//import javax.swing.CellEditor;
//import javax.swing.DefaultCellEditor;
//import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import Da.ItemDA;
import entity.Item;


public class ItemB {
//	private File orders;
//	private Scanner readMenu;
//	private BufferedWriter orderLogger;
//	private FileWriter orderLoggerStream;
//	private int count;
//	private String inputFile;
//	private Calendar calendar;
//	private ArrayList<Item> menuItems;
	private ItemDA da;
	public ItemB(){
		da = new ItemDA();
	}
	public DefaultTableModel getAllItem() throws SQLException, ClassNotFoundException {
		List<Item> items = da.getAll();
		DefaultTableModel model = new DefaultTableModel();

		model.addColumn("ID");
		model.addColumn("Đồ ăn/uống");
		model.addColumn("Số lượng còn lại");
		model.addColumn("Giá bán");
		model.addColumn("Số lượng mua");
		model.addColumn("Xác nhận");

		for (Item item : items) {
			String[] row = new String[4];
			row[0] = String.valueOf(item.getItemId());
			row[1] = item.getName();
			row[2] = String.valueOf(item.getQuantity());
			row[3] = String.valueOf(item.getSellPrice()); 
			model.addRow(row);
		}
		return model;
	}
	public void addNewItem(int id,String name, int quantity,double price) throws SQLException, ClassNotFoundException {
		da.addNewItem (id,name, quantity, price);
	}
	
	public void deleteItem(String id) throws SQLException {
		da.deleteItem(Integer.parseInt(id));
	}
	
	
	public void editItem(int id,String name, int quantity, double price) throws SQLException {
		da.editItem(id, name, quantity, price);
	}
	public void updateItem(int quantity, int id) throws SQLException, NumberFormatException, ClassNotFoundException {
		da.updateItem(quantity , id);
	}

	public int getNum() throws ClassNotFoundException, SQLException {
		return da.getNum();
	}
	public int getQ(int id) throws ClassNotFoundException, SQLException {
		return da.getQuantity2(id);
	}
	public double getPrice(int id) throws ClassNotFoundException, SQLException {
		return da.getPrice(id);
	}
	public int getItemID(String itemName) throws ClassNotFoundException, SQLException {
		return da.getItemID(itemName);
	}
}
