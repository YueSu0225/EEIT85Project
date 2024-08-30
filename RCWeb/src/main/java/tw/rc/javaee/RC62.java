package tw.rc.javaee;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/RC62")
public class RC62 extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("RC62");
		
		String account = request.getParameter("account");
		String passwd = request.getParameter("passwd");
		String remember = request.getParameter("remember");
		
		if (checkAccount(account,passwd)) {
			if (remember != null && remember.equals("true")) {
				Cookie cookie = new Cookie("account", account);
				cookie.setMaxAge(3*60);
				response.addCookie(cookie);
			}else {
				Cookie cookie = new Cookie("account", account);
				cookie.setMaxAge(0);
				response.addCookie(cookie);
				request.getSession().setAttribute("account", account);
			}
			response.sendRedirect("rc63.jsp");
		}else {
			response.sendRedirect("rc61.html");
		}
		
	}
	
	private boolean checkAccount(String account, String passwd) {
		if (account.equals("rc") && passwd.equals("123456")) {
			return true;
		}else {
			return false;
		}
	}
	
}
