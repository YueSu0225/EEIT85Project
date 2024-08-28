package tw.rc.javaee;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@WebServlet("/addicontest")
@MultipartConfig
public class addicontest extends HttpServlet {
	private static final String USER = "root";//固定常數
	private static final String PASSWD = "root";
	private static final String URL = "jdbc:mysql://127.0.0.1:3306/iii";
	private static final String SQL_UPDATE = "UPDATE member SET icon =? WHERE id = ?";
	private Connection conn;

	public addicontest() {
		Properties prop = new Properties();
		prop.put("user", USER);
		prop.put("password", PASSWD);
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(URL, prop);
		} catch (Exception e) {
			System.out.println(e);
		}
		
	}
		
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Part icon = request.getPart("icon");	
		String id = request.getParameter("id");
		 if (icon != null && id != null && icon.getSize() > 0) {
	            InputStream in = null;
	            PreparedStatement pstmt = null;
		try {
			in = icon.getInputStream();
			pstmt = conn.prepareStatement(SQL_UPDATE);
			pstmt.setBinaryStream(1,in,(int) icon.getSize());
			pstmt.setString(2, id);
			pstmt.executeUpdate();
		}catch(SQLException e) {
			System.out.println(e);
		}finally {
				try {
					if (pstmt != null) pstmt.close();
					if (in != null) in.close();
				} catch (SQLException e) {
					System.out.println(e);
				}
			}
		
		 } 
		response.sendRedirect("main.jsp");
	
	}

}
