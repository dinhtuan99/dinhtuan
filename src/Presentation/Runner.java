package Presentation;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import Business.UserB;

import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class Runner {

	private JFrame frmFastfoodStars;
	private JTextField textField;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Runner window = new Runner();
					window.frmFastfoodStars.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Runner() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public String username;
	public String password;
	private JPanel contentPane;
	private UserB userB;
	private void initialize() {
		frmFastfoodStars = new JFrame();
		frmFastfoodStars.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Joe\\Desktop\\CuoiKiCucManh\\Icon\\c6cd15f98b.png"));
		frmFastfoodStars.setTitle("FastFood 5 Stars");
		frmFastfoodStars.setBounds(100, 100, 600, 400);
		frmFastfoodStars.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		
		panel.setLayout(null);
		panel.setBorder(null);
		frmFastfoodStars.getContentPane().add(panel, BorderLayout.CENTER);
		
		textField = new JTextField();
		textField.setBounds(386, 143, 188, 20);
		textField.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		panel.add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(386, 178, 188, 20);
		passwordField.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					userB = new UserB();
					try {
						if(userB.checkLogin(textField.getText(), passwordField.getText())){
							username = textField.getText();
							password = passwordField.getText();
							if(username.equals("admin")) {
								managerFrame mFrame = new managerFrame(username,password);
								mFrame.setVisible(true);
								frmFastfoodStars.setVisible(false);
							}else {
							ItemFrame emFrame = new ItemFrame(username,password);
							
							emFrame.setVisible(true);
							frmFastfoodStars.setVisible(false);
							}
						}else{
							JOptionPane.showMessageDialog(null, "Sai tĂªn Ä‘Äƒng nháº­p hoáº·c máº­t kháº©u");
						}
					} catch (ClassNotFoundException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		panel.add(passwordField);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				userB = new UserB();
				try {
					if(userB.checkLogin(textField.getText(), passwordField.getText())){
						username = textField.getText();
						password = passwordField.getText();
						if(username.equals("admin")) {
							managerFrame mFrame = new managerFrame(username,password);
							mFrame.setVisible(true);
							frmFastfoodStars.setVisible(false);
						}else {
						ItemFrame emFrame = new ItemFrame(username,password);
						
						emFrame.setVisible(true);
						frmFastfoodStars.setVisible(false);
						}
					}else{
						JOptionPane.showMessageDialog(null, "Sai tên đăng nhập hoặc mật khẩu !");
					}
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnLogin.setBounds(499, 210, 75, 29);
		panel.add(btnLogin);
		
		JLabel label = new JLabel("");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setIcon(new ImageIcon("C:\\Users\\Joe\\Desktop\\CuoiKiCucManh\\itemImage\\Untitled-1.jpg"));
		ImageIcon icon = new ImageIcon();
		
		label.setBounds(0, 0, 584, 362);
		panel.add(label);
	}
}
