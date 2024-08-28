package tw.rc.javaee;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;




@WebServlet("/iconouttest")
public class iconouttest extends HttpServlet {
	private static final String USER = "root";//固定常數
	private static final String PASSWD = "root";
	private static final String URL = "jdbc:mysql://127.0.0.1:3306/iii";
	private Connection conn;
	
	
	public iconouttest() {
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
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		 String id = request.getParameter("id");

	        
	        PreparedStatement stmt = null;
	        ResultSet rs = null;
	        OutputStream out = response.getOutputStream();
	        try {	                        
	            String sql = "SELECT icon FROM member WHERE id = ?";
	            stmt = conn.prepareStatement(sql);
	            stmt.setString(1, id);
	            rs = stmt.executeQuery();

	            if (rs.next()) {            	
	                byte[] imgData = rs.getBytes("icon"); 
	                response.setContentType("image/png"); 
	                out.write(imgData); 
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        } 
	    }


}
