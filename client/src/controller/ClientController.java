package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import configs.Configs;
import models.Client;
import models.JoinRoom;
import models.Step;
import views.BoadGameGUI;

public class ClientController extends Thread {
    private BoadGameGUI gui;
    private Client client;
    private int board[][] = new int[9][16];
    private Step step = new Step();

    public ClientController(String host) {
        this.gui = new BoadGameGUI();
        this.client = new Client(host, Configs.SERVER_PORT);
        this.client.writeObjectToServer(new JoinRoom("JoinRoom", 111));
        this.init();
    }

    public void init() {
        this.board = this.client.getBoard();
        this.gui.setBoardFormArray(this.board);
        for (int i = 0; i < this.gui.Nrow; i++) {
            for (int j = 0; j < this.gui.Ncol; j++) {
                this.gui.buttons[i][j].addActionListener(new ActionListener() {
			
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // TODO Auto-generated method stub
                       System.out.println("Click: " + e.getActionCommand());
                       client.writeObjectToServer(new JoinRoom(e.getActionCommand(), 222));
                    }
                });
            }
        }
    }

    public void run() {
        while(true) {
            this.board = this.client.getBoard();
            this.gui.setBoardFormArray(this.board);
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
        clientController.start();
    }

}
