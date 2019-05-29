package Presentation;

import java.awt.BorderLayout;
import java.awt.Image;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import Business.OrderDetailB;
import net.miginfocom.swing.MigLayout;

public class InforFrame extends JDialog {
	private final JPanel contentPanel = new JPanel();
	private boolean isOk;
	public JTextField txtId;
	public JTextField txtItemId;
	public JTextField txtOrderId;
	public JTextField txtQuantity;
	public JScrollPane scrollPane3;
	private DefaultTableModel orderDetail;
	JTable table3;
	private OrderDetailB orderDetailB;
	
	
	private void initTableOrder() throws SQLException, ClassNotFoundException {
		orderDetailB = new OrderDetailB();
		orderDetail = orderDetailB.getAllOrder();
		table3.setModel(orderDetail);
		table3.setDefaultEditor(Object.class, null);
		table3.setAutoCreateRowSorter(true);
	}
	
	private Image loadImage(String path) {
		ImageIcon imageIcon = new ImageIcon(path);
		Image image = imageIcon.getImage();
		Image imageCell = image.getScaledInstance(20, 20, Image.SCALE_SMOOTH);

		return imageCell;
	}
	
	
	public InforFrame() {
		

		setTitle("Thông Tin Sản Phẩm");
		setBounds(100, 100, 400, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.NORTH);
		contentPanel.setLayout(new MigLayout("", "[][grow][]", "[][][][]"));
		
		contentPanel.add(orderTable(),BorderLayout.CENTER);
		
		
	}
	public JScrollPane orderTable() {

		scrollPane3 = new JScrollPane();
		scrollPane3.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane3.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		table3 = new JTable();
		table3.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		scrollPane3.setViewportView(table3);
		try {
			initTableOrder();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return scrollPane3;
	}
	public static void main(String[] args) {
		InforFrame frame = new InforFrame();
		frame.setVisible(true);
	}

}
