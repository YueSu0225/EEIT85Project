package tw.RC.tutor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.Scanner;

import org.mindrot.BCrypt;

public class membertest1 {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
        System.out.print("輸入帳號: ");
        String account = scanner.next();
       System.out.print("輸入密碼: ");
        String password = scanner.next();
		
		
		String url = "jdbc:mysql://localhost:3306/owntest";
		Properties prop = new Properties();
		prop.put("user", "root");
		prop.put("password", "root");
		
	 try {
		 Connection conn = DriverManager.getConnection(url, prop);
		 String sql = "SELECT passwd FROM membertest WHERE account = ?";
         PreparedStatement pstmt = conn.prepareStatement(sql);
         pstmt.setString(1, account);

         ResultSet rs = pstmt.executeQuery();
         if (rs.next()) {

             String ckpw = rs.getString("passwd");

             if (BCrypt.checkpw(password, ckpw)) {
                 System.out.println("登入成功");
             } else {
                 System.out.println("密碼錯誤");
             }
         } else {
             System.out.println("帳號不存在");
         }

     } catch (Exception e) {
         e.printStackTrace();
     }
    scanner.close();
	}
}
