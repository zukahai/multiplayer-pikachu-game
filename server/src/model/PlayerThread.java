package model;

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
				Account account = (Account) objectInputStream.readObject();
				System.out.println("Read " + account);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}

}
