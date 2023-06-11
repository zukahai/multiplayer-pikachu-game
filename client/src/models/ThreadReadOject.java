package models;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ThreadReadOject extends Thread {
    private Socket socket = null;
    public Game game = new Game(0);
    private User user = new User("Guest", "Guest");
    
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
}
