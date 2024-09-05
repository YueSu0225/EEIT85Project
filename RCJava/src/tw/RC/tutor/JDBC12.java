package tw.RC.tutor;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

public class JDBC12 {
	private static final String USER = "root";// 固定常數
	private static final String PASSWD = "root";
	private static final String URL = "jdbc:mysql://127.0.0.1:3306/iii";
	private static final String SQL_QUERY = "SELECT * FROM member WHERE id = ?";

	public static void main(String[] args) {
		Properties prop = new Properties();
		prop.put("user", USER);
		prop.put("password", PASSWD);

		try {
			Connection conn = DriverManager.getConnection(URL, prop);
			PreparedStatement pstmt = conn.prepareStatement(SQL_QUERY);
			pstmt.setInt(1, 1);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				String account = rs.getString("account");
				InputStream in = rs.getBinaryStream("icon");

				// -------------以下證明方式:
				new Thread() {//使用執行續讓踏在旁邊做
					public void run() {
						try {
							String filename = String.format("dir2/%s.png", account);
							FileOutputStream fout = new FileOutputStream(filename);
							byte[] buf = new byte[64 * 1024];
							int len = in.read(buf);
							fout.write(buf, 0, len);
							fout.flush();
							fout.close();
							System.out.println("Save OK");
						} catch (Exception e) {
							System.out.println(e);
						}
					}
				}.start();
				
				System.out.println("Main Finish");
				
			} else {
				System.out.println("id not exist");
			}

		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
