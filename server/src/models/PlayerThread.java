package models;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class PlayerThread extends Thread{

	private Socket socket = null;
	
	public PlayerThread(Socket socket) {
		this.socket = socket;
	}
	
	public void run() {
		while (true) {
			Object object = this.readObjectFromClient();
			if (object instanceof User) {
				User user = (User) object;
				System.out.println(user);
			}
			if (object instanceof JoinRoom) {
				JoinRoom joinRoom = (JoinRoom) object;
				System.out.println(joinRoom);
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

}