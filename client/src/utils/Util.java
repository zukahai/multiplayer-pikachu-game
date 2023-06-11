package utils;

import javax.swing.*;
import java.awt.*;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Objects;

public class Util {
    public static void printArray(int arr[][]) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                // print 2 charact
                System.out.printf("%2d   ", arr[i][j]);
            }
            System.out.println();
        }
    }

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

}
