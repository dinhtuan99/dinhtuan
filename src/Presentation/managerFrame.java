package Presentation;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import Business.ItemB;
import Business.OrderB;
import Business.UserB;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.Toolkit;
import java.awt.Font;
import java.awt.Color;
import javax.swing.border.EtchedBorder;
import javax.swing.JLabel;
import javax.swing.ScrollPaneConstants;
import javax.swing.ListSelectionModel;
public class managerFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	private DefaultTableModel model;
	private DefaultTableModel users;
	private DefaultTableModel orders;
	JTable table;
	JTable table2;
	JTable table3;
	private ItemB itemB;
	private UserB userB;
	private OrderB orderB;
	JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
	public static final String strImagePath = "BoundBall.png";
	ImageIcon background;
	JPanel jpanel;
	private JScrollPane scrollPane1;
	private JScrollPane scrollPane2;
	private JScrollPane scrollPane3;
	private String username;
	private String password;

	private void initTableItem() throws SQLException, ClassNotFoundException {
		itemB = new ItemB();
		model = itemB.getAllItem();
		table.setModel(model);
		table.setDefaultEditor(Object.class, null);
		table.setAutoCreateRowSorter(true);
	}
	private void initTableOrder() throws SQLException, ClassNotFoundException {
		orderB = new OrderB();
		orders = orderB.getAllOrder();
		table3.setModel(orders);
		table3.setDefaultEditor(Object.class, null);
		table3.setAutoCreateRowSorter(true);
	}
	private void initTableUsers() throws SQLException, ClassNotFoundException {
		UserB userB = new UserB();
		users = userB.getUsers();
		table2.setModel(users);
		table2.setDefaultEditor(Object.class, null);
		table2.setAutoCreateRowSorter(true);
	}

	private Image loadImage(String path) {
		ImageIcon imageIcon = new ImageIcon(path);
		Image image = imageIcon.getImage();
		Image imageCell = image.getScaledInstance(20, 20, Image.SCALE_SMOOTH);

		return imageCell;
	}

	public managerFrame(String username, String password) {
		setBackground(new Color(0, 128, 0));
		this.username = username;
		this.password = password;
		setForeground(new Color(0, 255, 0));
		setFont(new Font("Aardvark Cafe", Font.BOLD, 16));
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Joe\\Desktop\\CuoiKiCucManh\\Icon\\c6cd15f98b.png"));
		setTitle("FastFood 5 Stars");
		setBounds(150, 100, 900, 450);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout(0, 0));
		tabbedPane.setBackground(Color.LIGHT_GRAY);
		tabbedPane.setForeground(new Color(0, 0, 0));
		tabbedPane.setFont(new Font("Tahoma", Font.BOLD, 14));

		getContentPane().add(tabbedPane, BorderLayout.CENTER);
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(new Color(220, 20, 60));
		setJMenuBar(menuBar);

		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);

		JMenuItem mntmRefresh = new JMenuItem("Refresh");
		mnFile.add(mntmRefresh);

		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int value = JOptionPane.showConfirmDialog(null, "Do You Really Want To Exit?");
				if (value == 0) {
					dispose();
				}
			}
		});
		mnFile.add(mntmExit);

		JMenu mnAbout = new JMenu("About");

		menuBar.add(mnAbout);

		JMenuItem mntmAbout = new JMenuItem("About ");
		mntmAbout.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "A+ Oop");

			}
		});
		mnAbout.add(mntmAbout);
		JPanel panel1 = itemFrame();
		JPanel panel2 = userFrame();
		JPanel panel3 = orderFrame();
		tabbedPane.addTab("Sản phẩm", null, panel1, null);
		tabbedPane.addTab("Nhân viên", null, panel2, null);
		tabbedPane.addTab("Hoá đơn", null, panel3, null);
		getContentPane().add(tabbedPane);
		validate();
	}

	/*
	 * 
	 * 
	 * frame của các tab
	 * 
	 * 
	 * 
	 * 
	 */
	public JPanel orderFrame() {
		JPanel panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setForeground(Color.BLUE);

		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "T\u1EA5t c\u1EA3 s\u1EA3n ph\u1EA9m", TitledBorder.CENTER, TitledBorder.TOP, null, Color.BLACK));
		panel.setLayout(new BorderLayout(0, 0));

		JPanel panelButton = new JPanel();
		panelButton.setBackground(Color.LIGHT_GRAY);
		panel.add(panelButton, BorderLayout.SOUTH);

		
		JButton btnSearch = new JButton("Tìm kiếm");
		panelButton.add(btnSearch);
		btnSearch.setIcon(new ImageIcon(loadImage("Icon\\search-icon.png")));
		btnSearch.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String text = JOptionPane.showInputDialog(null, "Tìm kiếm:", "Find",
						JOptionPane.QUESTION_MESSAGE);
				TableRowSorter<TableModel> rowSorter = new TableRowSorter<TableModel>(table3.getModel());
				try {

					table3.setRowSorter(rowSorter);
					if (text.trim().length() == 0) {
						rowSorter.setRowFilter(null);
					} else {
						rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text.trim().toLowerCase()));
					}
				} catch (NullPointerException e2) {
					// TODO: handle exception
					// JOptionPane.showMessageDialog(null, "Nhập Kí Tự Cần Tìm ", "Thông Báo",
					// JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		JButton btnInfor = new JButton("Xem hoá đơn");
		panelButton.add(btnInfor);
		btnInfor.setIcon(new ImageIcon(loadImage("Icon\\search-icon.png")));
		btnInfor.addActionListener(new ActionListener() {
			
			
			
			
			@Override
			public void actionPerformed(ActionEvent e) {
			InforFrame frame = new InforFrame();
			frame.setVisible(true);
				
				
				// TODO Auto-generated method stub
				
			}
		});
		panel.add(orderTable(), BorderLayout.CENTER);

		panel.add(panelButton, BorderLayout.SOUTH);

		return panel;
	}
	public JPanel itemFrame() {
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setForeground(Color.BLUE);

		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "T\u1EA5t c\u1EA3 s\u1EA3n ph\u1EA9m", TitledBorder.CENTER, TitledBorder.TOP, null, Color.BLACK));
		panel.setLayout(new BorderLayout(0, 0));

		JPanel panelButton = new JPanel();
		panelButton.setBackground(Color.LIGHT_GRAY);
		panel.add(panelButton, BorderLayout.SOUTH);

		JButton btnAdd = new JButton("Thêm");
		panelButton.add(btnAdd);

		btnAdd.setHorizontalAlignment(SwingConstants.LEFT);
		btnAdd.setIcon(new ImageIcon(loadImage("Icon\\add.png")));
		btnAdd.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				AddItem frame = new AddItem(managerFrame.this);
				frame.setVisible(true);
				if (frame.conditional()) {
					String id = frame.txtId.getText();
					String name = frame.txtName.getText();
					String quantity = frame.txtQuantity.getText();
					String price = frame.txtPrice.getText();
					try {
						itemB.addNewItem(Integer.parseInt(id), name, Integer.parseInt(quantity),
								Integer.parseInt(price));
						try {
							initTableItem();
						} catch (ClassNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					} catch (NumberFormatException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (ClassNotFoundException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
				}
			}
		});

		JButton btnDelete = new JButton("Xóa");

		panelButton.add(btnDelete);
		btnDelete.setIcon(new ImageIcon(loadImage("Icon\\Delete.png")));
		btnDelete.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					String id = (String) table.getValueAt(table.getSelectedRow(), 0);
					try {

						itemB.deleteItem(id);
						initTableItem();
						if (true) {
							JOptionPane.showMessageDialog(null, "Xóa Thành công");
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} catch (ArrayIndexOutOfBoundsException e2) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null, "Hãy chọn hàng để xóa");
				}
			}
		});

		JButton btnEdit = new JButton("Sửa");
		panelButton.add(btnEdit);
		btnEdit.setIcon(new ImageIcon(loadImage("Icon\\Edit.png")));
		btnEdit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					AddItem frame = new AddItem(managerFrame.this);

					frame.txtId.setText((String) table.getValueAt(table.getSelectedRow(), 0));
					frame.txtName.setText((String) table.getValueAt(table.getSelectedRow(), 1));
					frame.txtQuantity.setText((String) table.getValueAt(table.getSelectedRow(), 2));
					frame.txtPrice.setText((String) table.getValueAt(table.getSelectedRow(), 3));

					frame.setVisible(true);

					if (frame.conditional()) {
						String id = frame.txtId.getText();
						String name = frame.txtName.getText();
						String quantity = frame.txtQuantity.getText();
						String price = frame.txtPrice.getText();
						try {
							itemB.editItem(Integer.parseInt(id), name, Integer.parseInt(quantity),
									Double.parseDouble(price));
							initTableItem();
						} catch (NumberFormatException e2) {
							// TODO Auto-generated catch block
							JOptionPane.showMessageDialog(null, "Sai định dạng	 ");
						} catch (SQLException e1) {
							JOptionPane.showMessageDialog(null, "Sql lỗi ");
						} catch (ClassNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}

				} catch (ArrayIndexOutOfBoundsException e2) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null, "Hãy chọn hàng");
				}
			}
		});

		JButton btnRefresh = new JButton("Refresh");
		panelButton.add(btnRefresh);
		btnRefresh.setIcon(new ImageIcon(loadImage("Icon\\Refresh.png")));
		btnRefresh.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					initTableItem();
				} catch (SQLException | ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		JButton btnSearch = new JButton("Tìm Kiếm");
		panelButton.add(btnSearch);
		btnSearch.setIcon(new ImageIcon(loadImage("Icon\\search-icon.png")));
		btnSearch.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String text = JOptionPane.showInputDialog(null, "Tìm kiếm:", "Find",
						JOptionPane.QUESTION_MESSAGE);
				TableRowSorter<TableModel> rowSorter = new TableRowSorter<TableModel>(table.getModel());
				try {

					table.setRowSorter(rowSorter);
					if (text.trim().length() == 0) {
						rowSorter.setRowFilter(null);
					} else {
						rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text.trim().toLowerCase()));
					}
				} catch (NullPointerException e2) {
					// TODO: handle exception
					// JOptionPane.showMessageDialog(null, "Nhập Kí Tự Cần Tìm ", "Thông Báo",
					// JOptionPane.WARNING_MESSAGE);
				}
			}
		});

		panel.add(itemTable(), BorderLayout.CENTER);

		panel.add(panelButton, BorderLayout.SOUTH);

		return panel;

	}
	/*
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 */

	public JPanel userFrame() {

		JPanel panel2 = new JPanel();
		panel2.setBackground(Color.WHITE);
		panel2.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "T\u1EA5t c\u1EA3 nh\u00E2n vi\u00EAn", TitledBorder.CENTER, TitledBorder.TOP, null, Color.BLACK));
		panel2.setLayout(new BorderLayout(0, 0));

		JPanel panelButton2 = new JPanel();
		panelButton2.setBackground(Color.LIGHT_GRAY);
		panel2.add(panelButton2, BorderLayout.SOUTH);

		JButton btnAdd2 = new JButton("Thêm");
		panelButton2.add(btnAdd2);
		btnAdd2.setHorizontalAlignment(SwingConstants.LEFT);
		btnAdd2.setIcon(new ImageIcon(loadImage("Icon\\add.png")));

		btnAdd2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				AddUser frame = new AddUser(managerFrame.this);
				frame.setVisible(true);
				if (frame.conditional()) {
					String id = frame.txtId.getText();
					String username = frame.txtUserName.getText();
					String password = frame.txtPassWord.getText();
					String name = frame.txtName.getText();
					String email = frame.txtEmail.getText();
					String address = frame.txtAddress.getText();
					String role = frame.txtRole.getText();
					try {
						userB = new UserB();
						userB.addNewUser(Integer.parseInt(id), username, password, name, email, address, role);
						initTableUsers();
					} catch (NumberFormatException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} 
			}
		});

		JButton btnDelete2 = new JButton("Xóa");
		panelButton2.add(btnDelete2);
		btnDelete2.setIcon(new ImageIcon(loadImage("Icon\\Delete.png")));

		btnDelete2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					String id = (String) table2.getValueAt(table2.getSelectedRow(), 0);
					try {
						UserB userB = new UserB();
						userB.deleteUser(id);
						initTableUsers();
						if (true) {
							JOptionPane.showMessageDialog(null, "Xóa Thành công");
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} catch (ArrayIndexOutOfBoundsException e2) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null, "Hãy chọn hàng để xóa");
				}
			}
		});

		JButton btnEdit2 = new JButton("Sửa");
		panelButton2.add(btnEdit2);
		btnEdit2.setIcon(new ImageIcon(loadImage("Icon\\Edit.png")));
		btnEdit2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					AddUser frame2 = new AddUser(managerFrame.this);

					frame2.txtId.setText((String) table2.getValueAt(table2.getSelectedRow(), 0));
					frame2.txtUserName.setText((String) table2.getValueAt(table2.getSelectedRow(), 1));
					frame2.txtPassWord.setText((String) table2.getValueAt(table2.getSelectedRow(), 2));
					frame2.txtName.setText((String) table2.getValueAt(table2.getSelectedRow(), 3));
					frame2.txtEmail.setText((String) table2.getValueAt(table2.getSelectedRow(), 4));
					frame2.txtAddress.setText((String) table2.getValueAt(table2.getSelectedRow(), 5));
					frame2.txtRole.setText((String) table2.getValueAt(table2.getSelectedRow(), 6));

					frame2.setVisible(true);

					if (frame2.conditional()) {
						String id = frame2.txtId.getText();
						String userName = frame2.txtUserName.getText();
						String passWord = frame2.txtPassWord.getText();
						String name = frame2.txtName.getText();
						String email = frame2.txtEmail.getText();
						String address = frame2.txtAddress.getText();
						String role = frame2.txtRole.getText();
						try {
							UserB userB = new UserB();
							userB.editUser(Integer.parseInt(id), userName, passWord, name, email, address, role);
							initTableUsers();
						} catch (NumberFormatException e1) {
							// TODO Auto-generated catch block
							JOptionPane.showMessageDialog(null, "Nhập lại đi ");
						} catch (SQLException e2) {
							JOptionPane.showMessageDialog(null, "Sql lỗi ");
						} catch (ClassNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}

				} catch (ArrayIndexOutOfBoundsException e2) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null, "Hãy chọn hàng");
				}
			}
		});

		JButton btnRefresh2 = new JButton("Refresh");
		panelButton2.add(btnRefresh2);
		btnRefresh2.setIcon(new ImageIcon(loadImage("Icon\\Refresh.png")));
		btnRefresh2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					initTableUsers();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		JButton btnSearch2 = new JButton("Tìm Kiếm");
		panelButton2.add(btnSearch2);
		btnSearch2.setIcon(new ImageIcon(loadImage("Icon\\search-icon.png")));
		btnSearch2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String text = JOptionPane.showInputDialog(null, "Tìm kiếm:", "Find",
						JOptionPane.QUESTION_MESSAGE);
				TableRowSorter<TableModel> rowSorter = new TableRowSorter<TableModel>(table2.getModel());
				try {

					table2.setRowSorter(rowSorter);
					if (text.trim().length() == 0) {
						rowSorter.setRowFilter(null);
					} else {
						rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text.trim().toLowerCase()));
					}
				} catch (NullPointerException e2) {
					// TODO: handle exception
					// JOptionPane.showMessageDialog(null, "Nhập Kí Tự Cần Tìm ", "Thông Báo",
					// JOptionPane.WARNING_MESSAGE);
				}
			}
		});

		panel2.add(userTable(), BorderLayout.CENTER);
		panel2.add(panelButton2, BorderLayout.SOUTH);

		return panel2;
	}

	public JScrollPane userTable() {

		JScrollPane scrollPane2 = new JScrollPane();
		scrollPane2.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		table2 = new JTable();
		table2.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		scrollPane2.setViewportView(table2);
		try {
			initTableUsers();
			;
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return scrollPane2;
	}

	public JScrollPane itemTable() {

		scrollPane1 = new JScrollPane();
		table = new JTable();
		table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		table.setBackground(Color.WHITE);
		scrollPane1.setViewportView(table);
		try {
			initTableItem();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return scrollPane1;
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
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

}
