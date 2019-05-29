package Presentation;

import javax.swing.JFrame;
import net.miginfocom.swing.MigLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import Business.ItemB;
import Business.OrderB;
import Business.OrderDetailB;

import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.awt.Toolkit;
import java.awt.Color;
public class ItemFrame extends JFrame {
	private DefaultTableModel model;
	private JTable table;
	public ItemB itemB;
	private JTextArea txtArea;
	private OrderB orderB;
	private OrderDetailB orderDetailB;
	public ItemFrame(String username, String password) throws ClassNotFoundException, SQLException {
		getContentPane().setBackground(new Color(128, 0, 0));
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Joe\\Desktop\\CuoiKiCucManh\\Icon\\c6cd15f98b.png"));
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("FastFood 5 Stars");
		this.setBounds(300, 10, 1000, 500);
		getContentPane().setLayout(new MigLayout("", "[grow][115.00][grow]", "[grow]"));
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, "cell 0 0,grow");
		panel.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel = new JLabel("Menu");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblNewLabel, BorderLayout.NORTH);
		
		Panel panel_3 = new Panel();
		panel_3.setBackground(new Color(128, 0, 0));
		panel.add(panel_3, BorderLayout.CENTER);
		panel_3.setLayout(new BorderLayout(0, 0));
		initTable();
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportView(table);
		panel_3.add(scrollPane, BorderLayout.CENTER);
		
		Panel panel_1 = new Panel();
		getContentPane().add(panel_1, "cell 1 0, grow");
		panel_1.setLayout(new BorderLayout(0, 0));
		txtArea = new JTextArea("");
		panel_1.add(txtArea, BorderLayout.CENTER);
		JButton buttonOrder = new JButton("Order");
		buttonOrder.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				orderB = new OrderB();
				orderDetailB = new OrderDetailB();
				try {
					orderB.addNewCustomer(username,password);
					String text  = txtArea.getText();
					String[] array = text.split("\n");
					System.out.println(text);
					for(String txt : array) {
						String []array2 =  txt.split("x");
							
							orderDetailB.addOrderDetail(array2[0].trim(), orderB.getOrderID(), Integer.parseInt(array2[1].trim()));
							orderB.addSum(itemB.getPrice(itemB.getItemID(array2[0].trim()))*Integer.parseInt(array2[1].trim()));
						
					}

					orderB.addSum(orderDetailB.getSum(orderB.getOrderID()));
		
					JOptionPane.showMessageDialog(null, "Order th�nh c�ng");
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		}); 
		JButton buttonRefresh = new JButton("Refresh");
		buttonRefresh.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				txtArea.setText("");
				try {
					initTable();
					scrollPane.setViewportView(table);
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		JPanel panelBot = new JPanel();
		panelBot.setLayout(new FlowLayout());
		panelBot.add(buttonOrder);
		panelBot.add(buttonRefresh);
		panel_1.add(panelBot, BorderLayout.SOUTH);
	}	
	private void initTable() throws SQLException, ClassNotFoundException {
		table = new JTable();
		itemB = new ItemB();
		model = itemB.getAllItem();
		table.setModel(model);
		table.setDefaultEditor(Object.class, null);
		table.setAutoCreateRowSorter(true);
		TableColumn col = table.getColumnModel().getColumn(4);
		for(int i = 0 ; i < itemB.getNum(); i++) {
			JTextField textField = new JTextField(5);
			col.setCellEditor(new DefaultCellEditor(textField));
			
			table.getColumnModel().getColumn(5).setCellRenderer(new ButtonRenderer());;
			table.getColumnModel().getColumn(5).setCellEditor(new ButtonEditor(textField));
		}
	

	}
	class ButtonRenderer extends JButton implements  TableCellRenderer{

		  //CONSTRUCTOR
		  public ButtonRenderer() {
		    //SET BUTTON PROPERTIES
		    setOpaque(true);
		  }
		  @Override
		  public Component getTableCellRendererComponent(JTable table, Object obj,
		      boolean selected, boolean focused, int row, int col) {

		    //SET PASSED OBJECT AS BUTTON TEXT
		      setText((obj==null) ? "":obj.toString());

		    return this;
		  }

		}

		//BUTTON EDITOR CLASS
		class ButtonEditor extends DefaultCellEditor{
			protected JButton btn;
			private String lbl;
			private Boolean clicked;
		   
		   public ButtonEditor(JTextField txt) {
		    super(txt);
		    lbl = txt.getText();
		    btn=new JButton();
		    btn.setOpaque(true);

		    //WHEN BUTTON IS CLICKED
		    btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				 fireEditingStopped();
			}
		    });
		  }

		   //OVERRIDE A COUPLE OF METHODS
		   @Override
		  public Component getTableCellEditorComponent(JTable table, Object obj,
		      boolean selected, int row, int col) {

		      //SET TEXT TO BUTTON,SET CLICKED TO TRUE,THEN RETURN THE BTN OBJECT
		     lbl=(obj==null) ? "":obj.toString();
		     btn.setText("Order");
		     clicked=true;
		    return btn;
		  }

		  //IF BUTTON CELL VALUE CHNAGES,IF CLICKED THAT IS
		   @Override
		  public Object getCellEditorValue() {

		     if(clicked)
		      {
		      //SHOW US SOME MESSAGE
		    	int index = table.getSelectedRow();
				String itemName = model.getValueAt(index, 1).toString();
				String quantity = model.getValueAt(index, 4).toString();
				int id = Integer.parseInt(model.getValueAt(index, 0).toString());
				try {
					if(Integer.parseInt(quantity) > itemB.getQ(id)) {
						JOptionPane.showMessageDialog(null,  itemB.getQ(id)+itemName + "Không đủ hàng");
					}
					else {
						itemB.updateItem(Integer.parseInt(quantity), id);
						txtArea.setText(txtArea.getText() +  itemName +" "+"x "+ quantity + "\n");
					}
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}catch(NumberFormatException e) {
					JOptionPane.showMessageDialog(null, "Hãy nhập số");
				}
				
		      }
		    //SET IT TO FALSE NOW THAT ITS CLICKED
		    clicked=false;
		    return new String(lbl);
		  }

		   @Override
		  public boolean stopCellEditing() {

		         //SET CLICKED TO FALSE FIRST
		      clicked=false;
		    return super.stopCellEditing();
		  }

		   @Override
		  protected void fireEditingStopped() {
		    // TODO Auto-generated method stub
		    super.fireEditingStopped();
		  }

		}

}
