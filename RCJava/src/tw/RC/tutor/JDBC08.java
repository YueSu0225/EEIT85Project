package tw.RC.tutor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.Scanner;

public class JDBC08 {

	public static void main(String[] args) {
		
		int rpp = 4;//一頁有幾筆
		
		System.out.print("頁: ");
		Scanner scanner = new Scanner(System.in);
		int page = scanner.nextInt();
		int start = (page - 1) * rpp;//limit(start, rpp);
		
		
		Properties prop = new Properties();
		prop.put("user", "root");
		prop.put("password", "root");
		
		String url = "jdbc:mysql://127.0.0.1:3306/rc";
		
		try {
			Connection conn = DriverManager.getConnection(url, prop);
			String sql = "SELECT * FROM gift ORDER BY id LIMIT ?, ?";
			//sql語法 搜索全部 從gift表,order by :以id排序,limit:看幾筆 limit 0,7代表 從第一筆1-7筆 ;limit 14, 7 = 從第14筆顯示7筆也就是15-21號 
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, start);
			pstmt.setInt(2, rpp);
			ResultSet rs = pstmt.executeQuery();
			
			
			while (rs.next()) {
				String id = rs.getString("id");
			    String city = rs.getString("city");
			    String name = rs.getString("name");
			    
			    System.out.printf("%s : %s : %s\n", id, name, city);
			}
			
			
		} catch (SQLException e) {
			System.out.println(e);
		}
	}
}
