package models;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ThreadReadOject extends Thread {
    private Socket socket = null;
    public Game game = new Game(0);
    private User user = null;
    private Register register = new Register();
    private HighScore hs = new HighScore();
    private ListRoom listRoom;
    
    public ThreadReadOject(Socket socket) {
       this.socket = socket;
    }
    
    public void run() {
        while(true) {
            Object object = this.readObjectFromServer();
            if (object == null)
                continue;
            if (object instanceof Game) {
                this.game = (Game) object;
                System.out.println("Game change");
            }
            if (object instanceof User) {
                this.user = (User) object;
            }
            if (object instanceof Register) {
                this.register = (Register) object;
            }
            if (object instanceof HighScore) {
                this.hs = (HighScore) object;
            }
            if (object instanceof ListRoom) {
                this.listRoom = (ListRoom) object;
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

    public Game getGame() {
        return this.game;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public HighScore getHighScore() {
        return this.hs;
    }

    public ListRoom getListRoom() {
        return this.listRoom;
    }

    public void setListRoom(ListRoom listRoom) {
        this.listRoom = listRoom;
    }

    public Register getRegister() {
        return this.register;
    }
}
