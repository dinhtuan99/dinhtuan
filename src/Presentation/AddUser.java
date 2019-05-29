package Presentation;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class AddUser extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private boolean isOk;
	public JTextField txtUserName;
	public JTextField txtPassWord;
	public JTextField txtName;
	public JTextField txtEmail;
	public JTextField txtAddress;
	public JTextField txtRole;
	public JTextField txtId;

	private Image loadImage(String path) {
		ImageIcon imageIcon = new ImageIcon(path);
		Image image = imageIcon.getImage();
		Image imageCell = image.getScaledInstance(20, 20, Image.SCALE_SMOOTH);

		return imageCell;
	}

	

	public AddUser(JFrame parents) {
		super(parents, "", true);
		isOk = false;

		setTitle("Thông Tin Nhân Viên");
		setBounds(150, 150, 380, 280);
		setLocationRelativeTo(parents);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.NORTH);
		contentPanel.setLayout(new MigLayout("", "[][grow][]", "[][][][][][][]"));
		{
			JLabel lblNhpThngTin = new JLabel("Nhập Thông Tin Về Nhân Viên");
			contentPanel.add(lblNhpThngTin, "cell 1 0,alignx center");
		}
		
		{
			JLabel lblId = new JLabel("ID : ");
			contentPanel.add(lblId, "cell 0 1,alignx left");
		}
		{
			txtId = new JTextField();
			contentPanel.add(txtId, "cell 1 1,growx");
			txtId.setColumns(10);
		}
		
		{
			JLabel lblUserName = new JLabel("Tên đăng nhập: ");
			contentPanel.add(lblUserName, "cell 0 2,alignx left");
		}
		{
			txtUserName = new JTextField();
			contentPanel.add(txtUserName, "cell 1 2,growx");
			txtUserName.setColumns(10);
		}

		{
			JLabel lblPassWord = new JLabel("Mật khẩu: ");
			contentPanel.add(lblPassWord, "cell 0 3,alignx left");
		}
		{
			txtPassWord = new JTextField();
			contentPanel.add(txtPassWord, "cell 1 3,growx");
			txtPassWord.setColumns(10);
		}

		{
			JLabel lblName = new JLabel("Tên: ");
			contentPanel.add(lblName,  "cell 0 4,alignx left");
		}
		{
			txtName = new JTextField();
			contentPanel.add(txtName,  "cell 1 4,growx");
			txtName.setColumns(10);
		}

		{
			JLabel lblEmail = new JLabel("Email: ");
			contentPanel.add(lblEmail, "cell 0 5,alignx left");
		}
		{
			txtEmail = new JTextField();
			contentPanel.add(txtEmail, "cell 1 5,growx");
			txtEmail.setColumns(10);
		}

		{
			JLabel lblAddress = new JLabel("Địa chỉ: ");
			contentPanel.add(lblAddress, "cell 0 6,alignx left");
		}
		{
			txtAddress = new JTextField();
			contentPanel.add(txtAddress,  "cell 1 6,growx");
			txtAddress.setColumns(10);
		}

		{
			JLabel lblRole = new JLabel("Vị trí: ");
			contentPanel.add(lblRole, "cell 0 7,alignx left");
		}
		{
			txtRole = new JTextField();
			contentPanel.add(txtRole, "cell 1 7,growx");
			txtRole.setColumns(10);
		}
		
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		{
			JButton okButton = new JButton("OK");
			okButton.setActionCommand("OK");
			buttonPane.add(okButton);
			okButton.setIcon(new ImageIcon(loadImage("Icon\\Ok.png")));
			getRootPane().setDefaultButton(okButton);
			okButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					if (txtName.getText().trim().equals("") || txtUserName.getText().trim().equals("")
							|| txtPassWord.getText().trim().equals("") || txtEmail.getText().trim().equals("")
							|| txtAddress.getText().trim().equals("") || txtRole.getText().trim().equals("")) {
						JOptionPane.showMessageDialog(null, "Không được để trống");
					} else {
						isOk = true;
						dispose();
					}
				}

			});
		}
		{
			JButton cancelButton = new JButton("Cancel");
			cancelButton.setActionCommand("Cancel");
			buttonPane.add(cancelButton);
			cancelButton.setIcon(new ImageIcon(loadImage("Icon\\Cancel.png")));
			cancelButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					isOk = false;
					dispose();
				}

			});

		}

	}

	public boolean conditional() {
		return isOk;
	}

}
