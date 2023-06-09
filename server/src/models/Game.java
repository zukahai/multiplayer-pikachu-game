package models;

import java.io.Serializable;
import java.net.Socket;
import java.util.HashMap;

public class Game implements Serializable{
    private int board[][];
    HashMap <String, Socket> players = new HashMap<>();

    public Game() {
        this.initBoard();
    }

    public Game(int board[][]) {
        this.board = board;
    }

    void initBoard() {
        int board[][] = new int[9][16];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 16; j++) {
                board[i][j] = (int) (Math.round(Math.random() * 100000)) % 36 + 1;
            }
        }
        this.board = board;
    }

    public void addPlayer(String username, Socket socket) {
        this.players.put(username, socket);
    }

    public void removePlayer(String username) {
        this.players.remove(username);
    }

    int getNumberOfPlayers() {
        return this.players.size();
    }

    public HashMap<String, Socket> getPlayers() {
        return this.players;
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
    
}
