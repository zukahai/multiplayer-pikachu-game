package models;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

import configs.Configs;
import utils.Hash;
import utils.Util;

public class Server extends Thread {

	private int port;

	private ServerSocket serverSocket = null;

	private ArrayList<Socket> sockets = new ArrayList<>();

	private ArrayList<PlayerThread> playerThreads = new ArrayList<>();

	public static int Nroom = 1000;

	public static Game rooms[] = new Game[Nroom + 1];
	public static HashMap<User, Integer> score[] = new HashMap[Nroom + 1];
	
	public Server(int port) {
		this.port = port;
		this.init();
        this.run();
	}

	static {
		for (int i = 0; i <= Nroom; i++) {
			rooms[i] = new Game(i);
			score[i] = new HashMap<>();
		}
	}
	
	public void init() {
		try {
			this.serverSocket = new ServerSocket(port);
			System.out.println("Server start: " + Util.getIPv4());
           
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void run() {
		while(true) {
			try {
				Socket socket = this.serverSocket.accept();
				
				PlayerThread playerThread = new PlayerThread(socket);
				playerThread.start();
				
				System.out.println(socket);
				this.playerThreads.add(playerThread);
				this.sockets.add(socket);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Server server = new Server(Configs.SERVER_PORT);
	}

}