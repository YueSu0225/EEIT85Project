package tw.RC.tutor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

public class JDBC16 {
	private static final String USER = "root";// 固定常數
	private static final String PASSWD = "root";
	private static final String URL = "jdbc:mysql://127.0.0.1:3306/iii";
	private static final String SQL_QUERY = "SELECT * FROM gift";
	
	public static void main(String[] args) {
		Properties prop = new Properties();
		prop.put("user", USER);
		prop.put("password", PASSWD);

		try {
			Connection conn = DriverManager.getConnection(URL, prop);
			PreparedStatement pstmt = conn.prepareStatement(SQL_QUERY, 
					ResultSet.TYPE_SCROLL_INSENSITIVE, 
					ResultSet.CONCUR_UPDATABLE);//
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				String id = rs.getString("id");
				String name = rs.getString("name");
				System.out.printf("%s : %s\n", id, name);
			}
			System.out.println("-----");
			
			if (rs.first()) {
				String id = rs.getString("id");
				String name = rs.getString("name");
				System.out.printf("%s : %s\n", id, name);

			}
			System.out.println("-----");
			if(rs.absolute(47)) {
				String id = rs.getString("id");
				String name = rs.getString("name");
				System.out.printf("%s : %s\n", id, name);

			}
			//因為if(rs.absolute(47))所以現在游標在id=47
			System.out.println("-----");
			rs.updateString("name", "古意禮");//變更
			rs.updateString("town", "田頭鎮");//變更
			rs.updateRow();//要+這敘述句,更新整段

			String id = rs.getString("id");
			String name = rs.getString("name");
			System.out.printf("%s : %s\n", id, name);
			
			System.out.println("-----");
			
//			rs.deleteRow();//刪除這列
			id = rs.getString("id");
			name = rs.getString("name");
			System.out.printf("%s : %s\n", id, name);
			
			//新增一筆在最後一列
			System.out.println("-----");
			rs.moveToInsertRow();
			rs.updateString("name", "古意禮");
			rs.updateString("town", "田頭鎮");
			rs.updateString("feature", "");
			rs.updateString("addr", "");
			rs.updateString("picurl", "");
			rs.updateString("city", "");
			rs.updateDouble("lat", 0.0);
			rs.updateDouble("lng", 0.0);
			rs.insertRow();
			
			
		}catch (Exception e) {
			System.out.println(e);
		}
	}

}
