package controller;

import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import models.Client;
import models.ListRoom;
import models.User;
import views.Register;

public class RegisterController {
    private Register registerGUI;
    private Client client;

    public RegisterController(Client client) {
        this.client = client;
        this.registerGUI = new Register();
        this.registerGUI.init();

        this.registerGUI.registerButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                // TODO Auto-generated method stub
                String username = registerGUI.usernameField.getText();
                String password = registerGUI.passwordField.getText();
                String confirmPassword = registerGUI.confirmPasswordField.getText();
                String name = registerGUI.nameField.getText();
                int avataID = registerGUI.avatarCombobox.getSelectedIndex() + 1;
                System.out.println(username + " " + password + " " + confirmPassword + " " + name + " " + avataID);

                if (username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty() || name.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please enter all information!");
                } else {
                    if (!password.equals(confirmPassword)) {
                        JOptionPane.showMessageDialog(null, "Password does not match!");
                    }
                    else {
                        //Register
                        User user = new User(0, name, username, password, 0, avataID);
                        models.Register register = new models.Register(user);
                        client.writeObjectToServer(register);

                        while(client.getRegister().getUser() == null)
                            System.out.println("wait register " + user);
                        
                        if (client.getRegister().getUser().getId() < 0) {
                            JOptionPane.showMessageDialog(null, "User is already exist!");
                        }
                        else {
                           JOptionPane.showMessageDialog(null, "Register success!");
                           new LoginController(client);
                           registerGUI.setVisible(false);
                        }
                    }
                } 
            }
      });
    }

    public static void main(String[] args) {
        new RegisterController(null);
    }
}
