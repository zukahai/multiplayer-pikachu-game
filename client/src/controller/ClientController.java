package controller;

import configs.Configs;
import models.Client;

public class ClientController {
    private String host = "localhost";

    private Client client;

    public ClientController() {
        this.host = "localhost";
        this.initalize();
    }

    public ClientController(String host) {
        this.host = host;
        this.initalize();
    }

    public void initalize() {
        this.client = new Client(host, Configs.SERVER_PORT);
        new GameController(client);
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
