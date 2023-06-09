package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.Account;

public class DBService {
	private static Connection conn = Util.getConnection();
	
	public static boolean create(Account account) {
		String sql = "INSERT INTO user(username, password, score) VALUES (?,?,?)";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			String passwordMD5 = Util.getMD5(account.getPassword());
			ps.setString(1, account.getUserName());
			ps.setString(2, passwordMD5);
			ps.setInt(3, account.getScore());	
			int record = ps.executeUpdate();
			if(record == 0)
				return false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return false;
		}
		return true;
	}
	
	public static boolean update(Account account, int score) {
		String sql = "UPDATE user SET score = score + ?  WHERE username = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, score);
			ps.setString(2, account.getUserName());
			int record = ps.executeUpdate();
			if(record == 0)
				return false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Account account = new Account("HaiZuka", "1");
//		boolean create = DBService.create(new Account("HaiZuka", "1"));
		boolean ud = update(account, 100);
		System.out.println(ud);
	}

}
