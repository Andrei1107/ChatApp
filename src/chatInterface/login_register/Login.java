package chatInterface.login_register;

import javax.swing.*;
import java.awt.*;

public class Login {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.WHITE);

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setForeground(new Color(255, 255, 255));
//		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\oprea\\Desktop\\user-icon.png"));
		lblNewLabel.setIcon(new ImageIcon("user-icon.png"));
		lblNewLabel.setBounds(60, 37, 278, 239);
		panel.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("ID");
		lblNewLabel_1.setFont(new Font("Trebuchet MS", Font.BOLD, 24));
		lblNewLabel_1.setForeground(Color.BLACK);
		lblNewLabel_1.setBounds(12, 428, 51, 29);
		panel.add(lblNewLabel_1);

		textField = new JTextField();
		textField.setBounds(101, 435, 191, 22);
		panel.add(textField);
		textField.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Password");
		lblNewLabel_2.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
		lblNewLabel_2.setForeground(Color.BLACK);
		lblNewLabel_2.setBounds(7, 544, 105, 16);
		panel.add(lblNewLabel_2);

		textField_1 = new JTextField();
		textField_1.setBounds(101, 543, 191, 22);
		panel.add(textField_1);
		textField_1.setColumns(10);

		JButton btnNewButton = new JButton("Login");
		btnNewButton.setBackground(new Color(255, 255, 255));
		btnNewButton.setFont(new Font("Trebuchet MS", Font.BOLD, 15));
		btnNewButton.setForeground(new Color(0, 0, 0));
		btnNewButton.setBounds(139, 949, 97, 25);
		panel.add(btnNewButton);

		JLabel lblNewLabel_3 = new JLabel("Login");
		lblNewLabel_3.setForeground(new Color(178, 34, 34));
		lblNewLabel_3.setFont(new Font("Trebuchet MS", Font.BOLD, 24));
		lblNewLabel_3.setBounds(154, 308, 82, 59);
		panel.add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("or");
		lblNewLabel_4.setFont(new Font("Trebuchet MS", Font.BOLD, 24));
		lblNewLabel_4.setBounds(185, 618, 51, 29);
		panel.add(lblNewLabel_4);

		JButton btnNewButton_1 = new JButton("Create a new account");
		btnNewButton_1.setForeground(new Color(178, 34, 34));
		btnNewButton_1.setBackground(new Color(255, 255, 255));
		btnNewButton_1.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
		btnNewButton_1.setBounds(108, 709, 184, 29);
		panel.add(btnNewButton_1);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setSize(400,screenSize.height);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}
