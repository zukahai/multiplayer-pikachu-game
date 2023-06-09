package model;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client extends Thread {
	private String host;
	private int port;
	Socket socket = new Socket();
	
	public Client(String host, int port) {
		this.host = host;
		this.port = port;
	}
	
	public void connect() {
		try {
			this.socket = new Socket(this.host, this.port);
			System.out.println("Connect");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void run() {
		while(true) {
			Scanner sc = new Scanner(System.in);
			System.out.print("Enter username: ");
			String userName = sc.nextLine();
			System.out.print("Enter password: ");
			String password = sc.nextLine();
			Account account = new Account(userName, password);
			
			try {
				ObjectOutputStream objectOutputStream = new ObjectOutputStream(this.socket.getOutputStream());
				objectOutputStream.writeObject(account);
				System.out.println("Send: " + account);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}


	
	public static void main(String[] args) {
		Client client = new Client("localhost", 2712);
		client.connect();
		client.run();
	}

}
