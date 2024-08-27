package tw.RC.tutor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JDBC02 {
	public static void main(String[] args) {
		String url = "jdbc:mysql://localhost:3306/rc";
		Properties prop = new Properties();
		prop.put("user", "root");
		prop.put("password", "root");
		try (Connection conn = DriverManager.getConnection(url, prop)){
			Statement stmt = conn.createStatement();
			String sql = "DELETE FROM cust WHERE id = 2";//刪除
			int n = stmt.executeUpdate(sql);
			System.out.println(n);
			
		} 
		catch (Exception e) {
			System.out.println(e);
		}
		
		
	}
}
