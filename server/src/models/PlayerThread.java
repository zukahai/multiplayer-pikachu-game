package models;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.time.LocalTime;
import java.util.ArrayList;

import javax.swing.Timer;

import configs.Configs;
import utils.PikachuAlgorithm;
import utils.Util;

public class PlayerThread extends Thread{

	private Socket socket = null;
	private User user = new User(LocalTime.now().toString(), "Guest");
	private Game game = new Game(0);
	
	public PlayerThread(Socket socket) {
		this.socket = socket;
		Timer timer = new Timer(1000, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int roomID = game.getRoomID();
				if (roomID != 0) {
					System.out.println(Server.score[roomID].keySet());
					ArrayList<User> users = new ArrayList<>();
					for (User user: Server.score[roomID].keySet()) {
						user.setScore(Server.score[roomID].get(user));
						users.add(user);
					}
					HighScore hs = new HighScore(users);
					writeObjectToClient(socket, hs);
				}
			}
		});
		// timer.start();
	}
	
	public void run() {
		while (true) {
			Object object = this.readObjectFromClient();
			if (object instanceof User) {
				User user = (User) object;
				this.user = user;
				this.writeObjectToClient(this.socket, user);
				System.out.println("Login success");
			}

			if (object instanceof JoinRoom) {
				JoinRoom joinRoom = (JoinRoom) object;
				int roomID = joinRoom.getRoomID();
				System.out.println(joinRoom);
				int board[][] = Server.rooms[roomID].getBoard();
				this.game = new Game(board, roomID);
				this.writeObjectToClient(this.socket, this.game);
				Server.rooms[roomID].addPlayer(this.user, socket);

				Server.score[roomID].put(this.user, 0);

				//Update ranking
				ArrayList<User> users = new ArrayList<>();
				for (User user: Server.score[roomID].keySet()) {
					user.setScore(Server.score[roomID].get(user));
					users.add(user);
				}
				HighScore hs = new HighScore(users);
				this.sendAllSocketInRoom(roomID, hs);

				System.out.println("Room " + roomID + " join, Number of player: " + Server.rooms[roomID].getNumberOfPlayers() + " User " + this.user);
			}

			if (object instanceof Step) {
				Step step = (Step) object;
				System.out.println("Step: " +step);
				int board[][] = step.getBoard();

				//check step
				if (PikachuAlgorithm.checkStep(board, step.getPosition1(), step.getPosition2())) {
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
					this.sendAllSocketInRoom(step.getRoomID(), this.game);
					System.out.println("=============>> TRUE");

					// update score
					int bonus = Server.rooms[roomID].getBonus();
					Server.rooms[roomID].subBonus(Configs.SUB_SCORE_BONUS);
					Server.score[roomID].put(this.user, Server.score[roomID].get(this.user) + Configs.SCORE + bonus);
					System.out.println("Score: " + Server.score[roomID].get(this.user) + " Username: " + this.user.getUsername());

					//Update ranking
					ArrayList<User> users = new ArrayList<>();
					for (User user: Server.score[roomID].keySet()) {
						user.setScore(Server.score[roomID].get(user));
						users.add(user);
					}
					HighScore hs = new HighScore(users);
					this.sendAllSocketInRoom(roomID, hs);
				} else
					System.out.println("=============>> FALSE");
			}
		}
	}

	public void sendAllSocketInRoom(int roomID, Object object) {
		for (Socket socket : Server.rooms[roomID].getSockets()) {
			this.writeObjectToClient(socket, object);
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