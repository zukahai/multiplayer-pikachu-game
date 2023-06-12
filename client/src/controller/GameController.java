package controller;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import models.Client;
import models.JoinRoom;
import models.Step;
import models.User;
import views.BoadGameGUI;

public class GameController extends Thread {

    private BoadGameGUI gui;

    private Client client;

    private int board[][] = new int[9][16];

    private Step step = new Step();

    private int indexStep = 1;

    public GameController(Client client, int roomID) {
        this.gui = new BoadGameGUI();
        this.gui.userJListCustom.setCurrentUser(client.getUser());
        this.client = client;

        this.gui.leaveRoomLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                client.leaveRoom();
                new RoomController(client);
                gui.setVisible(false);
            }
        });
        
        this.client.writeObjectToServer(new JoinRoom("JoinRoom", roomID));
        this.init();
        this.start();
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
                               gui.buttons[row][col].setEnabled(false);
                               break;
                           case 2:
                               step.setPosition2(point);
                               gui.buttons[step.getPosition1().x][step.getPosition1().y].setEnabled(true);
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

            ArrayList<User> users = this.client.getHighScore().getUsers();
            // users.add(new User(1, "Phan Đức Hùng", "HungZuka", "a", 1000, 9));
            // users.add(new User(2, "Nguyễn Văn Quang", "Quang077", "a", 729, 10));
            // users.add(new User(3, "Phan Việt Hùng", "HungPhan", "a", 729, 1));
            // users.add(new User(4, "Phan Mỹ Linh", "MyLinh", "a", 3729, 2));
            // users.add(new User(5, "Phan Đức Hiếu", "HieuPC", "a", 1229, 3));
            this.gui.userJListCustom.setUsers(users);
        }
    }

    public static void main(String[] args) {
    }

}
