package tw.RC.tutor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JDBC06 {

	public static void main(String[] args) {
		Properties prop = new Properties();
		prop.put("user", "root");
		prop.put("password", "root");
		
		String url = "jdbc:mysql://127.0.0.1:3306/rc";
		
		
		
		try {
			Connection conn = DriverManager.getConnection(url, prop);
			String sql = "SELECT * FROM gift ORDER BY city";//sql語法 搜索全部 從gift表,order by :以城市為單位排序
			Statement stmt = conn.createStatement();//在 Java 中，createStatement 是 java.sql.Connection 接口中的一个方法，
													//用于创建一个 Statement 对象，这个对象可以用来执行 SQL 查询
			ResultSet rs = stmt.executeQuery(sql); //你可以使用 Statement 对象来执行 SQL 查询，例如 executeQuery 用于执行 SELECT 查询，executeUpdate 用于执行 INSERT、UPDATE 或 DELETE 语句。
												   //处理从数据库中返回的结果集（ResultSet）并进行必要的操作。	
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
