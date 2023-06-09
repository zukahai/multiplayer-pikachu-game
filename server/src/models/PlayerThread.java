package models;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

public class PlayerThread extends Thread{

	private Socket socket = null;
	
	public PlayerThread(Socket socket) {
		this.socket = socket;
	}
	
	public void run() {
		while (true) {
			try {
				ObjectInputStream objectInputStream = new ObjectInputStream(this.socket.getInputStream());
				User user = (User) objectInputStream.readObject();
				System.out.println("Read " + user);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}

}