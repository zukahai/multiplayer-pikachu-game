package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import configs.Configs;
import models.Client;
import views.BoadGameGUI;

public class ClientController {
    private BoadGameGUI gui;
    private Client client;
    private int board[][];

    public ClientController(String host) {
        this.gui = new BoadGameGUI();
        this.init();
        this.client = new Client(host, Configs.SERVER_PORT);
    }

    public void init() {
        this.board = this.gui.board;
        for (int i = 0; i < this.gui.Nrow; i++) {
            for (int j = 0; j < this.gui.Ncol; j++) {
                this.gui.buttons[i][j].addActionListener(new ActionListener() {
			
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // TODO Auto-generated method stub
                       System.out.println("Click: " + e.getActionCommand());
                    }
                });
            }
        }
    }

    public static void main(String[] args) {
        ClientController clientController = new ClientController("localhost");
    }

}
