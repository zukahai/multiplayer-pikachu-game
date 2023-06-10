package models;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.time.LocalTime;

import utils.Util;

public class PlayerThread extends Thread{

	private Socket socket = null;
	private User user = new User(LocalTime.now().toString(), "Guest");
	
	public PlayerThread(Socket socket) {
		this.socket = socket;
	}
	
	public void run() {
		while (true) {
			Object object = this.readObjectFromClient();
			if (object instanceof User) {
				User user = (User) object;
				this.user = user;
				this.writeObjectToClient(this.user);
				System.out.println("Login success");
			}
			if (object instanceof JoinRoom) {
				JoinRoom joinRoom = (JoinRoom) object;
				int roomID = joinRoom.getRoomID();
				System.out.println(joinRoom);
				Game game = new Game(Server.rooms[roomID].getBoard(), roomID);
				this.writeObjectToClient(game);
				Server.rooms[roomID].addPlayer(this.user, socket);
				System.out.println("Room " + roomID + " join, Number of player: " + Server.rooms[roomID].getNumberOfPlayers());
			}
			if (object instanceof Step) {
				Step step = (Step) object;
				System.out.println("Step: " +step);
				Util.printArray(step.getBoard());
			}
		}
	}

	public void writeObjectToClient(Object object) {
		try {
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(this.socket.getOutputStream());
			objectOutputStream.writeObject(object);
			System.out.println("Send: " + object);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Object readObjectFromClient() {
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

	public User getUser() {
		return user;
	}

}