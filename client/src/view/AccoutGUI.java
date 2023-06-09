package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AccoutGUI extends JFrame {

	private JPanel contentPane;
	private JTextField username_login_textfeild;
	private JTextField username_register_tf;
	private JPasswordField password_login_pwf;
	private JPasswordField password_register_pwf;
	private JPasswordField confirm_password_register_pwf;
	private JPanel panel_login, panel_register;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AccoutGUI frame = new AccoutGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AccoutGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 459, 343);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(224, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		panel_login = new JPanel();
		panel_login.setBackground(new Color(224, 255, 255));
		panel_login.setBounds(21, 22, 409, 268);
		contentPane.add(panel_login);
		panel_login.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Login Account");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel.setBounds(132, 11, 168, 29);
		panel_login.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(224, 255, 255));
		panel_1.setBounds(10, 44, 389, 213);
		panel_login.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("UserName");
		lblNewLabel_1.setBounds(34, 53, 64, 14);
		panel_1.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Password");
		lblNewLabel_2.setBounds(34, 103, 64, 14);
		panel_1.add(lblNewLabel_2);
		
		username_login_textfeild = new JTextField();
		username_login_textfeild.setBounds(125, 46, 238, 29);
		panel_1.add(username_login_textfeild);
		username_login_textfeild.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Register account");
		lblNewLabel_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				panel_login.setVisible(false);
				panel_register.setVisible(true);
			}
		});
		lblNewLabel_3.setForeground(new Color(128, 0, 0));
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblNewLabel_3.setBounds(233, 188, 130, 14);
		panel_1.add(lblNewLabel_3);
		
		JButton login_bt = new JButton("Login");
		login_bt.setBackground(new Color(72, 209, 204));
		login_bt.setBounds(147, 148, 130, 29);
		panel_1.add(login_bt);
		
		password_login_pwf = new JPasswordField();
		password_login_pwf.setBounds(125, 96, 238, 29);
		panel_1.add(password_login_pwf);
		
		panel_register = new JPanel();
		panel_register.setVisible(false);
		panel_register.setLayout(null);
		panel_register.setBackground(new Color(224, 255, 255));
		panel_register.setBounds(21, 22, 409, 268);
		contentPane.add(panel_register);
		
		JLabel lblNewLabel_4 = new JLabel("Register Account");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel_4.setBounds(132, 11, 168, 29);
		panel_register.add(lblNewLabel_4);
		
		JPanel panel_1_1 = new JPanel();
		panel_1_1.setLayout(null);
		panel_1_1.setBackground(new Color(224, 255, 255));
		panel_1_1.setBounds(10, 44, 389, 213);
		panel_register.add(panel_1_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("UserName");
		lblNewLabel_1_1.setBounds(34, 18, 64, 14);
		panel_1_1.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_2_1 = new JLabel("Password");
		lblNewLabel_2_1.setBounds(34, 60, 64, 14);
		panel_1_1.add(lblNewLabel_2_1);
		
		username_register_tf = new JTextField();
		username_register_tf.setColumns(10);
		username_register_tf.setBounds(125, 11, 238, 29);
		panel_1_1.add(username_register_tf);
		
		JLabel lblNewLabel_3_1 = new JLabel("Login account");
		lblNewLabel_3_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				panel_login.setVisible(true);
				panel_register.setVisible(false);
			}
		});
		lblNewLabel_3_1.setForeground(new Color(128, 0, 0));
		lblNewLabel_3_1.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblNewLabel_3_1.setBounds(233, 188, 130, 14);
		panel_1_1.add(lblNewLabel_3_1);
		
		JButton register_bt = new JButton("Register");
		register_bt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		register_bt.setBackground(new Color(72, 209, 204));
		register_bt.setBounds(147, 148, 130, 29);
		panel_1_1.add(register_bt);
		
		JLabel lblNewLabel_2_1_1 = new JLabel("Confirm");
		lblNewLabel_2_1_1.setBounds(34, 99, 64, 14);
		panel_1_1.add(lblNewLabel_2_1_1);
		
		JLabel lblNewLabel_5 = new JLabel("password");
		lblNewLabel_5.setBounds(34, 117, 64, 14);
		panel_1_1.add(lblNewLabel_5);
		
		password_register_pwf = new JPasswordField();
		password_register_pwf.setBounds(125, 53, 238, 29);
		panel_1_1.add(password_register_pwf);
		
		confirm_password_register_pwf = new JPasswordField();
		confirm_password_register_pwf.setBounds(125, 96, 238, 29);
		panel_1_1.add(confirm_password_register_pwf);
		
		
		setLocationRelativeTo(null);
		setResizable(false);
	}
}
