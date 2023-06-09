package utils;

import java.net.*;
import java.util.*;

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
	public static void main(String[] args) {
		System.out.println(Util.getIPv4());
	}
}
