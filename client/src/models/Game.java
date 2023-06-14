package models;

import java.io.Serializable;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

import configs.Configs;

public class Game implements Serializable{
    private int board[][];
    private int roomID;
    private int bonus = 72;
    HashMap <User, Socket> players = new HashMap<>();

    public Game() {
        
    }

    public Game(int roomID) {
        this.roomID = roomID;
        this.initBoard();
    }

    public Game(int board[][]) {
        this.board = board;
    }

    public Game(int board[][], int roomID) {
        this.board = board;
        this.roomID = roomID;
    }

    void initBoard() {
        
    }

    public ArrayList<Socket> getSockets() {
        ArrayList<Socket> sockets = new ArrayList<>();
        for (User user : players.keySet()) {
            sockets.add(players.get(user));
        }
        return sockets;
    }

    public void addPlayer(User user, Socket socket) {
        this.players.put(user, socket);
    }

    public void removePlayer(User user) {
        this.players.remove(user);
    }

    public void setBonus(int bonus) {
        this.bonus = bonus;
    }

    public void subBonus(int sub) {
        this.bonus -= sub;
    }

    int getNumberOfPlayers() {
        return this.players.size();
    }

    public HashMap<User, Socket> getPlayers() {
        return this.players;
    }

    public void setPlayers(HashMap<User, Socket> players) {
        this.players = players;
    }

    public Socket getPlayer(String username) {
        return this.players.get(username);
    }

    public int[][] getBoard() {
        return board;
    }

    public void setBoard(int[][] board) {
        this.board = board;
    }

    public int getRoomID() {
        return roomID;
    }

    public int getBonus() {
        return bonus;
    }

    public void setRoomID(int roomID) {
        this.roomID = roomID;
    }

    public Game clone() {
        Game game = new Game();
        game.setRoomID(roomID);
        game.setPlayers(players);
        return game;
    }

    public boolean isEnd() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] > 0 && board[i][j] <= Configs.MAX_LEVEL) {
                    return false;
                }
            }
        }
        return true;
    }
    
}
