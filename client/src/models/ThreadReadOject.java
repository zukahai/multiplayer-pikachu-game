package models;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class ThreadReadOject extends Thread {
    private Socket socket = null;
    private int board[][] = new int[9][16];
    
    public ThreadReadOject(Socket socket) {
        this.socket = socket;
        for (int i = 0; i < this.board.length; i++) {
            for (int j = 0; j < this.board[i].length; j++) {
                this.board[i][j] = 1;
            }
        }
    }
    
    public void run() {
        while(true) {
            Object object = this.readObjectFromServer();
            if (object instanceof Game) {
                Game game = (Game) object;
                this.board = game.getBoard().clone();
                System.out.println("Game change");
            }
        }
    }

    public Object readObjectFromServer() {
		try {
			ObjectInputStream objectInputStream = new ObjectInputStream(this.socket.getInputStream());
			return objectInputStream.readObject();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

    public void writeObjectToServer(Object object) {
		try {
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(this.socket.getOutputStream());
			objectOutputStream.writeObject(object);
			System.out.println("Send: " + object);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

    public int[][] getBoard() {
        return this.board.clone();
    }
}
