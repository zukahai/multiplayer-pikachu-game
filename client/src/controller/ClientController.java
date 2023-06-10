package controller;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import configs.Configs;
import models.Client;
import models.JoinRoom;
import models.Step;
import models.User;
import views.BoadGameGUI;

public class ClientController extends Thread {

    private BoadGameGUI gui;

    private Client client;

    private int board[][] = new int[9][16];

    private Step step = new Step();

    private int indexStep = 1;

    public ClientController(String host) {
        this.gui = new BoadGameGUI();
        this.client = new Client(host, Configs.SERVER_PORT);
        this.client.writeObjectToServer(new User("Hai", "Linh"));
        this.client.writeObjectToServer(new JoinRoom("JoinRoom", 111));
        this.init();
    }

    public void init() {
        for (int i = 0; i < this.gui.Nrow; i++) {
            for (int j = 0; j < this.gui.Ncol; j++) {
                this.gui.buttons[i][j].addActionListener(new ActionListener() {
			
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // TODO Auto-generated method stub
                       String actionCommand = e.getActionCommand();
                       int row = Integer.parseInt(actionCommand.split(" ")[0]);
                       int col = Integer.parseInt(actionCommand.split(" ")[1]);
                       Point point = new Point(row, col);
                       switch (indexStep){
                           case 1:
                               step.setPosition1(point);
                               break;
                           case 2:
                               step.setPosition2(point);
                               client.writeObjectToServer(step);
                               break;
                       }
                       indexStep = 3 - indexStep;
                       
                    }
                });
            }
        }
    }

    public void run() {
        while(true) {
            this.board = this.client.getBoard();
            int room_id = this.client.getRoomID();
            this.step.setRoomID(room_id);
            this.step.setUser(this.client.getUser());
            this.step.setBoard(board);
            
            this.gui.setBoardFormArray(this.board);
            this.gui.setRoomIDLable(room_id);
        }
    }

    public static void main(String[] args) {
        ClientController clientController = new ClientController("localhost");
        clientController.start();
    }

}
