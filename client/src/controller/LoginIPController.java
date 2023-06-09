package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import model.Client;
import view.LoginIPGUI;

public class LoginIPController {
	private LoginIPGUI loginIPGUI;
	private Client client;
	public LoginIPController() {
		loginIPGUI = new LoginIPGUI();
		loginIPGUI.connect_bt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					client = new Client(loginIPGUI.ip_tf.getText(), 2712);
					System.out.println("aaaa");
				} catch (Exception e2) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null, e, null, 0, null);
				}
			}
		});
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LoginIPController loginIPController = new LoginIPController();
	}

}
