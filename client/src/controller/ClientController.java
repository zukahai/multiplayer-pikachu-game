package controller;

import javax.swing.JOptionPane;

import configs.Configs;
import models.Client;
import views.AddressIP;

public class ClientController {
    private String host = "localhost";

    private Client client;
    private AddressIP addressIP ;

    public ClientController() {
        this.host = "localhost";
        this.initalize();
    }

    public ClientController(String host) {
        this.host = host;
        this.initalize();
    }

    public void initalize() {
        this.addressIP = new AddressIP();
        // demo action listener for accept button
        this.addressIP.getAccepButton().addActionListener(e -> {
            String ip = addressIP.getTextField().getText();
            try {
                client = new Client(ip, Configs.SERVER_PORT);
                if (client.connect()) {
                    new LoginController(client);
                    //turn off address IP
                    addressIP.setVisible(false);

                } else {
                    JOptionPane.showMessageDialog(null, "You entered: " + addressIP.getTextField().getText() + " connect failed!");
                }
               
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "You entered: " + addressIP.getTextField().getText() + " connect failed!");
            }
        });
    }


    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public static void main(String[] args) {
        new ClientController();
    }

}
