package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.lang.model.type.PrimitiveType;

import models.Client;
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
                new GameController(client);
                loginGUI.setVisible(false);
            }
            
      });
    }

    public static void main(String[] args) {
        new LoginController(null);
    }
}
