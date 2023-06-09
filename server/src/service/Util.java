package service;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.DriverManager;

public class Util {
	public static String getIPv4() {
		InetAddress IP = null;
		try {
			IP = InetAddress.getLocalHost();
		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return IP.getHostAddress();
	}
	
	public static Connection getConnection() {
		Connection conn = null;
		try{ 
			   String userName = "root";
			   String password = "";
			   String databaseName = "pikachu";
			   String url = "jdbc:mysql://localhost/" + databaseName;
			   Class.forName ("com.mysql.cj.jdbc.Driver");
			   conn = DriverManager.getConnection(url, userName, password);
			   System.out.println("Connected datatabase " + databaseName);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return conn;
	}
	

	public static void main(String[] args) {
		System.out.println(Util.getIPv4());
		Util.getConnection();
	}
}
