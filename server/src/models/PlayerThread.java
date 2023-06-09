package models;

import java.awt.Point;
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
import services.UserService;
import utils.PikachuAlgorithm;
import utils.Util;

public class PlayerThread extends Thread{

	private Socket socket = null;
	private User user = new User(LocalTime.now().toString(), "Guest");
	private Game game = new Game(0);
	private UserService userService = new UserService();
	
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
				Notification<User> notification = userService.login(user);
				if (notification.isSuccess()) {
					this.user = notification.getData();
					this.writeObjectToClient(this.socket, this.user);
					System.out.println("Login success " + this.user);
				} else {
					this.user.setId(Configs.ID_USER_FAILER);
					this.writeObjectToClient(this.socket, this.user);
					System.out.println("Login fail");
				}
			}

			if (object instanceof Register) {
				Register register = (Register) object;
				this.user = register.getUser();
				Notification<User> notification = userService.register(this.user);
				if (notification.isSuccess()) {
					System.out.println("Register success " + this.user);
				} else {
					user.setId(-999);
					register.setUser(user);
					System.out.println("Register fail");
				}
				this.writeObjectToClient(this.socket, register);
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
				int roomID = step.getRoomID();
				int board[][] = Server.rooms[roomID].getBoard();

				//Convert line to 0
				for (int i = 0; i < board.length; i++) {
						for (int j = 0; j < board[i].length; j++) {
							if (board[i][j] > Configs.LEVEL) {
								board[i][j] = 0;
							}
						}
					}

				//check step
				if (PikachuAlgorithm.checkStep(board, step.getPosition1(), step.getPosition2())) {
					// clear 2 point
					int x1 = step.getPosition1().x;
					int y1 = step.getPosition1().y;
					int x2 = step.getPosition2().x;
					int y2 = step.getPosition2().y;
					board[x1][y1] = board[x2][y2] = 0;

					//set line
					int boardIconLine[][] = Util.solveIcon(PikachuAlgorithm.points);
					for (int i = 0; i < boardIconLine.length; i++) {
						for (int j = 0; j < boardIconLine[i].length; j++) {
							board[i][j] += boardIconLine[i][j];
						}
					}

					// update score
					int bonus = Server.rooms[roomID].getBonus();
					Server.rooms[roomID].subBonus(Configs.SUB_SCORE_BONUS);
					int totalScore = (Configs.SCORE + bonus) * Server.rooms[roomID].getNumberOfPlayers();
					Server.score[roomID].put(this.user, Server.score[roomID].get(this.user) + totalScore);
					

					//Update ranking
					ArrayList<User> users = new ArrayList<>();
					for (User user: Server.score[roomID].keySet()) {
						user.setScore(Server.score[roomID].get(user));
						users.add(user);
					}
					HighScore hs = new HighScore(users);
					this.sendAllSocketInRoom(roomID, hs);

					// send game to all player
					Server.rooms[step.getRoomID()].setBoard(board);
					this.game = new Game(board, roomID);
					this.game.setBonus(bonus - Configs.SUB_SCORE_BONUS);
					this.sendAllSocketInRoom(step.getRoomID(), this.game);
					System.out.println("=============>> TRUE");

				} else
					System.out.println("=============>> FALSE");
			}

			if (object instanceof ListRoom) {
				Game rooms[] = Util.cloneArray(Server.rooms);
				ListRoom listRoom = new ListRoom(rooms);
				this.writeObjectToClient(this.socket, listRoom);
			}

			if (object instanceof LeaveRoom) {
				LeaveRoom leaveRoom = (LeaveRoom) object;
				int roomID = leaveRoom.getRoomID();
				Server.rooms[roomID].removePlayer(this.user);
				Server.score[roomID].remove(this.user);
				System.out.println("Room " + roomID + " leave, Number of player: " + Server.rooms[roomID].getNumberOfPlayers() + " User " + this.user);

				Game rooms[] = Util.cloneArray(Server.rooms);
				ListRoom listRoom = new ListRoom(rooms);
				this.writeObjectToClient(this.socket, listRoom);

				//send all player in room
				ArrayList<User> users = new ArrayList<>();
				for (User user: Server.score[roomID].keySet()) {
					user.setScore(Server.score[roomID].get(user));
					users.add(user);
				}
				HighScore hs = new HighScore(users);
				this.sendAllSocketInRoom(roomID, hs);
			}

			if (object instanceof ResetGame) {
				ResetGame resetGame = (ResetGame) object;
				User user = resetGame.getUser();
				int roomID = resetGame.getRoomID();
				user.setScore(user.getScore() + Server.score[roomID].get(user));
				System.out.println("Score: " + user.getScore());
				userService.updateScore(user.getId(), user.getScore());
				if (Server.rooms[roomID].isEnd())
					Server.rooms[roomID] = new Game(roomID);
				this.writeObjectToClient(this.socket, user);
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