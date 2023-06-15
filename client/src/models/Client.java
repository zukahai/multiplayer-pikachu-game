package models;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import configs.Configs;

public class Client extends Thread {
	
	private String host;

	private int port;

	Socket socket = new Socket();

	public int board[][] = new int[9][16];

	private Thread threadReadOject;
	
	public Client(String host, int port) {
		this.host = host;
		this.port = port;
	}
	
	public boolean connect() {
		try {
			this.socket = new Socket(this.host, this.port);
			System.out.println("Connect");
			threadReadOject = new ThreadReadOject(this.socket);
			threadReadOject.start();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			return false;
		}
		return true;
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

	 public int[][] getBoard() {
		Game game = ((ThreadReadOject)threadReadOject).getGame();
		int board[][] = game.getBoard().clone();
        return board;
    }

	public int getRoomID() {
		Game game = ((ThreadReadOject)threadReadOject).getGame();
		int roomID = game.getRoomID();
		return roomID;
	}

	public Game getGame() {
		return ((ThreadReadOject)threadReadOject).getGame();
	}

	public void leaveRoom() {
		int roomID = this.getRoomID();
		LeaveRoom leaveRoom = new LeaveRoom("Leave room", roomID);
		this.writeObjectToServer(leaveRoom);
	}

	public User getUser() {
		return ((ThreadReadOject)threadReadOject).getUser();
	}

	public void setUser(User user) {
		((ThreadReadOject)threadReadOject).setUser(user);
	}

	public HighScore getHighScore() {
		return ((ThreadReadOject)threadReadOject).getHighScore();
	}

	public ListRoom getListRoom() {
		return ((ThreadReadOject)threadReadOject).getListRoom();
	}

	public Register getRegister() {
		return ((ThreadReadOject)threadReadOject).getRegister();
	}
	
	public static void main(String[] args) {
		Client client = new Client("localhost", Configs.SERVER_PORT);
	}

}