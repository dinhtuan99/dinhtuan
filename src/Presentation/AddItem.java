
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

public class AddItem extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private boolean isOk;
	public JTextField txtId;
	public JTextField txtName;
	public JTextField txtQuantity;
	public JTextField txtPrice;

	private Image loadImage(String path) {
		ImageIcon imageIcon = new ImageIcon(path);
		Image image = imageIcon.getImage();
		Image imageCell = image.getScaledInstance(20, 20, Image.SCALE_SMOOTH);

		return imageCell;
	}

	public AddItem(JFrame parents) {
		super(parents, "", true);
		isOk = false;

		setTitle("Thông Tin Sản Phẩm");
		setBounds(100, 100, 340, 220);
		setLocationRelativeTo(parents);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.NORTH);
		contentPanel.setLayout(new MigLayout("", "[][grow][]", "[][][][][][][]"));
		{
			JLabel lblNhpThngTin = new JLabel("Nhập Thông Tin Về Sản Phẩm");
			contentPanel.add(lblNhpThngTin, "cell 1 0,alignx center");
		}
		{
			JLabel lblSell = new JLabel("ID");
			contentPanel.add(lblSell, "cell 0 1,alignx left");
		}
		{
			txtId = new JTextField();
			contentPanel.add(txtId, "cell 1 1,growx");
			txtId.setColumns(10);
		}
		{
			JLabel lblName = new JLabel("Tên");
			contentPanel.add(lblName, "cell 0 2,alignx left");
		}
		{
			txtName = new JTextField();
			contentPanel.add(txtName, "cell 1 2,growx");
			txtName.setColumns(10);
		}
		{
			JLabel lblQuantity = new JLabel("Số Lượng");
			contentPanel.add(lblQuantity, "cell 0 3,alignx left");
		}
		{
			txtQuantity = new JTextField();
			contentPanel.add(txtQuantity, "cell 1 3,growx");
			txtQuantity.setColumns(10);
		}

		{
			JLabel lblPrice = new JLabel("Giá");
			contentPanel.add(lblPrice, "cell 0 4,alignx left");
		}
		{
			txtPrice = new JTextField();
			contentPanel.add(txtPrice, "cell 1 4,growx");
			txtPrice.setColumns(10);
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
					if (txtName.getText().trim().equals("") || txtId.getText().trim().equals("")|| txtPrice.getText().trim().equals("")
							|| txtQuantity.getText().trim().equals("")) {
						JOptionPane.showMessageDialog(null, "Không được để trống");
					} else {
						isOk = true;
						dispose();
					}
				}

			});
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
	}

	public boolean conditional() {
		return isOk;
	}

}
