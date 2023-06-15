package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.lang.model.type.PrimitiveType;
import javax.swing.JOptionPane;

import models.Client;
import models.ListRoom;
import models.User;
import views.Login;

public class LoginController {

    private Client client;

    private Login loginGUI;

    public LoginController(Client client) {
        this.client = client;
        this.initalize();  
    }

    public void initalize() {
        loginGUI = new Login();
        loginGUI.init();

        loginGUI.loginButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                String username = loginGUI.usernameField.getText();
                String password = loginGUI.passwordField.getText();
                System.out.println(username + " " + password);

                //Check login
                User user = new User(0, username.toUpperCase(), username, password, 0, (int)Math.round(1 + 9 * Math.random()));
                client.writeObjectToServer(user);

                while(client.getUser() == null)
                    System.out.println("wait login " + user.getUsername());

                if (client.getUser().getId() < 0) {
                    client.setUser(null);
                    JOptionPane.showMessageDialog(null, "Login fail!");
                }
                else {
                    client.writeObjectToServer(new ListRoom());
                // new GameController(client);
                    new RoomController(client);
                    loginGUI.setVisible(false);
                }
            }
      });

      loginGUI.registerLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                loginGUI.setVisible(false);
                new RegisterController(client);
            }
        });
    }

    public static void main(String[] args) {
        new LoginController(null);
    }
}
