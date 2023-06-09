package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import configs.Configs;
import models.Client;
import models.User;
import views.BoadGameGUI;

public class ClientController {
    private BoadGameGUI gui;
    private Client client;
    private int board[][] = new int[9][16];
    private User user;

    public ClientController(String host) {
        this.gui = new BoadGameGUI();
        this.init();
        this.client = new Client(host, Configs.SERVER_PORT);
    }

    public void init() {
        this.board = this.gui.getBoard().clone();
        this.printArray(this.board);
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

    public void printArray(int arr[][]) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                // print 2 charact
                System.out.printf("%2d   ", arr[i][j]);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        ClientController clientController = new ClientController("localhost");
    }

}
