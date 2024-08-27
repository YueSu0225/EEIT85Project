package tw.RC.tutor;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Properties;

import javax.imageio.stream.FileImageInputStream;

import tw.RC.apis.Bike;

public class JDBC13 {
	private static final String USER = "root";//固定常數
	private static final String PASSWD = "root";
	private static final String URL = "jdbc:mysql://127.0.0.1:3306/iii";
	private static final String SQL_UPDATE = "UPDATE member SET bike = ? WHERE id = ?";
	public static void main(String[] args) {
		Properties prop = new Properties();
		prop.put("user", USER);
		prop.put("password", PASSWD);
		
		Bike b1 = new Bike();
		b1.upSpeed();b1.upSpeed();
		b1.upSpeed();b1.upSpeed();
		b1.upSpeed();b1.upSpeed();
		b1.upSpeed();b1.upSpeed();
		b1.upSpeed();b1.upSpeed();
		System.out.println(b1.getSpeed());
		
		
		try {
			Connection conn = DriverManager.getConnection(URL, prop);
			PreparedStatement pstmt = conn.prepareStatement(SQL_UPDATE);

			pstmt.setObject(1, b1);
			pstmt.setInt(2, 1);
			if (pstmt.executeUpdate() > 0) {
				System.out.println("OK");
			}else {
				System.out.println("XX");
			}
			
			
			
		} catch (Exception e) {
			System.out.println(e);
		}
		
		
	}

}
