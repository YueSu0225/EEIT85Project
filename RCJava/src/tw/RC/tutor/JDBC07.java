package tw.RC.tutor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.Scanner;

public class JDBC07 {

	public static void main(String[] args) {
		System.out.print("關鍵字: ");
		Scanner scanner = new Scanner(System.in);
		String key = scanner.next();

		String sql = "SELECT * FROM gift WHERE name LIKE ? OR feature LIKE ? OR addr LIKE ?";
		String kw = "%" + key + "%";//設計關鍵字?的搜索
		
		
		Properties prop = new Properties();
		prop.put("user", "root");
		prop.put("password", "root");
		
		String url = "jdbc:mysql://127.0.0.1:3306/rc";
		try {
			Connection conn = DriverManager.getConnection(url, prop);
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, kw);
			pstmt.setString(2, kw);
			pstmt.setString(3, kw);
			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				String name = rs.getString("name");
				String feature = rs.getString("feature");
				String addr = rs.getString("addr");
				
				System.out.println(name);
				System.out.println(feature);
				System.out.println(addr);
				
				System.out.println("----");
				
			}
			
			
		}catch (Exception e) {
			System.out.println(e);
		}
		
		
		
	}

}
