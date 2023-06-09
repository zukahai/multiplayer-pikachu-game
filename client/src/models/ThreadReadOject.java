package models;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class ThreadReadOject extends Thread {
    private Socket socket = null;
    
    public ThreadReadOject(Socket socket) {
        this.socket = socket;
    }
    
    public void run() {
        while (true) {
            Scanner sc = new Scanner(System.in);
            System.out.print("Enter username: ");
            String userName = sc.nextLine();
            System.out.print("Enter password: ");
            String password = sc.nextLine();
            User user = new User(userName, password);
            
            this.writeObjectToServer(user);
        
        }
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
}
