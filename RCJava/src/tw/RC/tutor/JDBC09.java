package tw.RC.tutor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

import org.mindrot.BCrypt;
import org.w3c.dom.ranges.RangeException;

public class JDBC09 {
	static Connection conn;//讓所有人認識
	
	public static void main(String[] args) {
		System.out.println("Register...");

		Properties prop = new Properties();
		prop.put("user", "root");
		prop.put("password", "root");
		
		String url = "jdbc:mysql://localhost:3306/iii";
		try {
			conn = DriverManager.getConnection(url, prop);
		
			Scanner scanner = new Scanner(System.in);

			String account;
			do {
				System.out.print("Account: ");
				account = scanner.next();
			}while(isAccountExist(account));
			System.out.print("Password: ");
			String passwd = scanner.next();
			System.out.print("Name: ");
			String name = scanner.next();
	
			String sql = "INSERT INTO member (account,passwd,name) VALUES (?,?,?)";
			String hashPasswd = BCrypt.hashpw(passwd, BCrypt.gensalt());

			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, account);
			pstmt.setString(2, hashPasswd);
			pstmt.setString(3, name);
			if (pstmt.executeUpdate() > 0 ) {
				System.out.println("Register Success!!!");
			}else {
				System.out.println("Register Failure");
			}
			
			
		} catch (Exception e) {
			System.out.println(e);
			throw new RuntimeException();
		}
		
		
		
		
		
		
		
		
		
		

		
		
	}
// 在資料庫尋找有無帳號重複
// 方法1:	
//	static boolean isAccountExist(String account) throws SQLException {//true就是已存在false就是無存在
//		String sql = "SELECT account FROM member WHERE account = ?";
//		PreparedStatement pstmt = conn.prepareStatement(sql);
//		pstmt.setString(1, account);
//		ResultSet rs = pstmt.executeQuery();
//
//		return rs.next();
//	}
// 方法2:
	static boolean isAccountExist(String account) throws SQLException {
		String sql = "SELECT count(account) count FROM member WHERE account = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, account);
		ResultSet rs = pstmt.executeQuery();
		rs.next();
		int count = rs.getInt("count");
		
		return count > 0;
	}

}
