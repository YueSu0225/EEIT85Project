package tw.rc.javaee;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.PublicKey;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Base64;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.mindrot.BCrypt;

@WebServlet("/RC21")
@MultipartConfig
public class RC21 extends HttpServlet {
	private static final String USER = "root";//固定常數
	private static final String PASSWD = "root";
	private static final String URL = "jdbc:mysql://127.0.0.1:3306/iii";
	private static final String SQL_INSERT = "INSERT INTO member (account,passwd,name,icon)" + 
			" VALUES (?,?,?,?)";
	private Connection conn;

	public RC21() {
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
		request.setCharacterEncoding("UTF-8");
		Part icon = request.getPart("icon");
		InputStream in = icon.getInputStream();
		
		String account = request.getParameter("account");
		String passwd = BCrypt.hashpw(request.getParameter("passwd"),BCrypt.gensalt());
		String name = request.getParameter("name");
		response.setContentType("text/html; charset=UTF-8");
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL_INSERT);
			pstmt.setString(1, account);
			pstmt.setString(2, passwd);
			pstmt.setString(3, name);
			pstmt.setBinaryStream(4, in);
			pstmt.executeUpdate();
		}catch(SQLException e) {
			System.out.println(e);
		}		
		//icon.write("url");另存新檔
		
	
		
		
//		byte[] buf = in.readAllBytes();
//		String base64String = Base64.getEncoder().encodeToString(buf);
//		以下code秀出圖片在網頁		
//		response.setContentType("text/html; charset=UTF-8");
//		response.getWriter().print(String.format("<img src='data:image/png;base64, %s' />", base64String));
		
		response.sendRedirect("main.jsp");
	
	}

}
