package tw.rc.javaee;

import java.io.IOException;
import java.io.InputStream;
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
	        try {	                        
	            String sql = "SELECT icon FROM member WHERE id = ?";
	            PreparedStatement stmt = conn.prepareStatement(sql);
	            stmt.setString(1, id);
	            ResultSet rs = stmt.executeQuery();

	            if (rs.next()) {            	
	            	InputStream in = rs.getBinaryStream("icon");
	                if (in != null) {
	                    response.setContentType("image/jpeg");
	                    OutputStream out = response.getOutputStream();
	                    byte[] buf = new byte[1024];
	                    int Read;
	                    while ((Read = in.read(buf)) != -1) {
	                        out.write(buf, 0, Read);
	                    }
	                    out.flush(); 
	                }
	            }
	            } catch (Exception e) {
	            e.printStackTrace();
	        } 
	    
	
	}
}
