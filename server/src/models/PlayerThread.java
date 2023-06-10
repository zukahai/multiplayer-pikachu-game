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
	private Game game = new Game(0);
	
	public PlayerThread(Socket socket) {
		this.socket = socket;
	}
	
	public void run() {
		while (true) {
			Object object = this.readObjectFromClient();
			if (object instanceof User) {
				User user = (User) object;
				this.user = user;
				this.writeObjectToClient(this.socket, this.user);
				System.out.println("Login success");
			}
			if (object instanceof JoinRoom) {
				JoinRoom joinRoom = (JoinRoom) object;
				int roomID = joinRoom.getRoomID();
				System.out.println(joinRoom);
				this.game = new Game(Server.rooms[roomID].getBoard(), roomID);
				this.writeObjectToClient(this.socket, this.game);
				Server.rooms[roomID].addPlayer(this.user, socket);
				System.out.println("Room " + roomID + " join, Number of player: " + Server.rooms[roomID].getNumberOfPlayers());
			}
			if (object instanceof Step) {
				Step step = (Step) object;
				System.out.println("Step: " +step);
				int board[][] = step.getBoard();

				// clear 2 point
				int x1 = step.getPosition1().x;
				int y1 = step.getPosition1().y;
				int x2 = step.getPosition2().x;
				int y2 = step.getPosition2().y;
				board[x1][y1] = board[x2][y2] = 0;

				// send game to all player
				Server.rooms[step.getRoomID()].setBoard(board);
				int roomID = step.getRoomID();
				this.game = new Game(Server.rooms[roomID].getBoard(), roomID);
				Util.printArray(step.getBoard());
				this.sendAllSocketInRoom(step.getRoomID());
			}
		}
	}

	public void sendAllSocketInRoom(int roomID) {
		for (Socket socket : Server.rooms[roomID].getSockets()) {
			this.writeObjectToClient(socket, game);
		}
	}

	public void writeObjectToClient(Socket socket, Object object) {
		try {
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
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