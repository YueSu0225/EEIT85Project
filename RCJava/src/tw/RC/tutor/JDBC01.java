package tw.RC.tutor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JDBC01 {
	public static void main(String[] args) {
		// Load Driver (Connector)先載入驅動程式
		// BUT, 新版JAVA可以不用,WEB開發一定要
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");//載入驅動程式,驅動程式名稱不能亂打
			System.out.println("OK");
		} catch (ClassNotFoundException e) {
			System.out.println(e);
			throw new RuntimeException();
		}
		
		//連線方式1:
								//localhost=127.0.0.1
//		String url1 = "jdbc:mysql://localhost:3306/rc?user=root&password=root";
//		try (Connection conn = DriverManager.getConnection(url1)){
//			System.out.println("OK66666666");
//		} 
//		catch (Exception e) {
//			System.out.println(e);
//		}
		//連線方式2:
//		String url2 = "jdbc:mysql://localhost:3306/rc";
//		try (Connection conn = DriverManager.getConnection(url2, "root", "root")){
//			System.out.println("OK77777777");
//		} 
//		catch (Exception e) {
//			System.out.println(e);
//		}
		//連線方式3:(建議使用,因為key&value多的話比較不會打錯)
		String url3 = "jdbc:mysql://localhost:3306/rc";
		Properties prop = new Properties();
		prop.put("user", "root");
		prop.put("password", "root");
		try (Connection conn = DriverManager.getConnection(url3, prop)){
//			System.out.println("OK888888");
			Statement stmt = conn.createStatement();
			String sql = "INSERT INTO cust (name,tel,birthday) VALUES ('RC','123','1999-01-02')";//新增
			sql += ", ('Amy','1111','2024-04-02')";
			sql += ", ('Peter','1223','1979-11-22')";
			int n = stmt.executeUpdate(sql);
			System.out.println(n);
			
		} 
		catch (Exception e) {
			System.out.println(e);
		}
		
		
	}
}
