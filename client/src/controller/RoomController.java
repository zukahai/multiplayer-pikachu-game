package controller;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import models.Client;
import models.Game;
import views.Room;

public class RoomController {
    private Client client;
    private Room roomGUI;

    public RoomController(Client client) {
        this.client = client;
        this.initalize();
    }

    public void initalize() {
        roomGUI = new Room();
        roomGUI.initFrame();
        ArrayList<Game> games = this.client.getListRoom().getArrayListRoom();
        this.roomGUI.setRoomList(games);

         // Add event listener for roomList (demo)
        roomGUI.roomList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int index = roomGUI.roomList.locationToIndex(evt.getPoint());
                if (index >= 0) {
                    
                    // JOptionPane.showConfirmDialog(null, " " + index, "Join room", JOptionPane.DEFAULT_OPTION);
                    new GameController(client, index);
                    roomGUI.setVisible(false);

                }
            }
        });
    }

    public static void main(String[] args) {
        RoomController roomController = new RoomController(null);
    }
}
