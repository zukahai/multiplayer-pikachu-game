package utils;

import java.awt.Point;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.HashMap;

import models.Game;
import models.User;

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
	
	public static String convertByteToHex1(byte[] data) {
		  BigInteger number = new BigInteger(1, data);
		  String hashtext = number.toString(16);
		  // Now we need to zero pad it if you actually want the full 32 chars.
		  while (hashtext.length() < 32) {
		    hashtext = "0" + hashtext;
		  }
		  return hashtext;
		}

	
	public static String getMD5(String input) {
		  try {
		    MessageDigest md = MessageDigest.getInstance("MD5");
		    byte[] messageDigest = md.digest(input.getBytes());
		    return convertByteToHex1(messageDigest);
		  } catch (NoSuchAlgorithmException e) {
		    throw new RuntimeException(e);
		  }
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

	public static void printArray(int arr[][]) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                // print 2 charact
                System.out.printf("%2d   ", arr[i][j]);
            }
            System.out.println();
        }
    }

	public static Game[] cloneArray(Game arr[]) {
		Game [] newArr = new Game[arr.length];
		for (int i = 0; i < arr.length; i++) {
			newArr[i] = new Game(i);
			HashMap<User, Socket> hm = new HashMap<>();
			for (int j = 0; j < arr[i].getPlayers().size(); j++) {
				hm.put(new User("username" + j, "a"), null);
			}
			newArr[i].setPlayers(hm);
		}
		return newArr;
	}

	public static int[][] solveIcon(ArrayList<Point> points) {
		int icon[] = {100, 200, 400, 700};
		int pusRow[] = {1, 0, -1, 0};
		int pusCol[] = {0, -1, 0, 1};
		int arr[][] = new int[9][16];
		boolean board[][] = new boolean[9][16];
		for (Point p : points) {
			board[p.x][p.y] = true;
		}

		int indexIconStart = 0;
		int rowCurrent = points.get(0).x;
		int colCurrent = points.get(0).y;
		for (int j = 0; j < pusRow.length; j++) {
			int rowNext = rowCurrent + pusRow[j];
			int colNext = colCurrent + pusCol[j];
			if (checkLocation(rowNext, colNext) && board[rowNext][colNext]) {
				arr[rowNext][colNext] = icon[j];
				board[rowNext][colNext] = false;
				indexIconStart = j + 2;
				if (indexIconStart >= 4)
					indexIconStart -= 4;
				arr[rowCurrent][colCurrent] = icon[indexIconStart];
				board[rowCurrent][colCurrent] = false;
				rowCurrent = rowNext;
				colCurrent = colNext;
				break;
			}
		}

		for (int i = 0; i < points.size(); i++) {
			for (int j = 0; j < pusRow.length; j++) {
				int rowNext = rowCurrent + pusRow[j];
				int colNext = colCurrent + pusCol[j];
				if (checkLocation(rowNext, colNext) && board[rowNext][colNext]) {
					arr[rowNext][colNext] = icon[j];
					indexIconStart = j + 2;
					board[rowNext][colNext] = false;
					if (indexIconStart >= 4)
					indexIconStart -= 4;
					arr[rowCurrent][colCurrent] += icon[indexIconStart];
					rowCurrent = rowNext;
					colCurrent = colNext;
					break;
				}
			}
		}
		return arr;
	}

	public static boolean checkLocation(int x, int y) {
		if (x >= 0 && x < 9 && y >= 0 && y < 16) {
			return true;
		}
		return false;
	}
	

	public static void main(String[] args) {
		System.out.println(Util.getMD5("Nam"));
	}
}