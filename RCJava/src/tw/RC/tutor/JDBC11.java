package tw.RC.tutor;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Properties;

import javax.imageio.stream.FileImageInputStream;

public class JDBC11 {//更新圖檔到mysql
	private static final String USER = "root";//固定常數
	private static final String PASSWD = "root";
	private static final String URL = "jdbc:mysql://127.0.0.1:3306/iii";
	private static final String SQL_UPDATE = "UPDATE member SET icon = ? WHERE id = ?";
	public static void main(String[] args) {
		Properties prop = new Properties();
		prop.put("user", USER);
		prop.put("password", PASSWD);
		
		try {
			Connection conn = DriverManager.getConnection(URL, prop);
			PreparedStatement pstmt = conn.prepareStatement(SQL_UPDATE);
			FileInputStream fin = new FileInputStream("dir1/ball3.png");//blob檔
			pstmt.setBinaryStream(1, fin);
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
