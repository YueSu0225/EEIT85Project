package tw.rc.apis;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

import org.mindrot.BCrypt;

public class OwntestDB {//Member連接DB的物件
	private static final String DRIVER ="com.mysql.cj.jdbc.Driver";
	private static final String USER ="root";
	private static final String PASSWORD ="root";
	private static final String URL ="jdbc:mysql://localhost:3306/owntest";
	private static final String SQL_CHECK ="SELECT count(account) count FROM membertest WHERE account = ?";
	private static final String SQL_INSERT ="INSERT INTO membertest (account,passwd) VALUE (?,?)";//SQL語法,怕撞到關鍵字可以使用``包起來
	private static final int INSERT_ACCOUNT= 1; 
	private static final int INSERT_PASSWD= 2; 

	
	private Connection conn;
	
	public OwntestDB() throws Exception {
		Properties prop = new Properties();
		prop.put("user", USER);prop.put("password", PASSWORD);
	
		Class.forName(DRIVER);
		conn = DriverManager.getConnection(URL, prop);
	}
	
	public boolean isAccountExist(String account) {
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL_CHECK);
			pstmt.setString(1, account);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			return rs.getInt("count") > 0;
		}catch (Exception e) {
			System.out.println("ERROR(1)");
		}
		
		return true;
	}
	
	public boolean addMember(String account, String passwd) throws Exception{
		PreparedStatement pstmt = conn.prepareStatement(SQL_INSERT);
		pstmt.setString(INSERT_ACCOUNT, account);
		pstmt.setString(INSERT_PASSWD, BCrypt.hashpw(passwd, BCrypt.gensalt()));
		

		return pstmt.executeUpdate() > 0;
	}
	
}
