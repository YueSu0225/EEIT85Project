package tw.rc.javaee;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tw.rc.apis.OwntestDB;

@WebServlet("/OwntestRegister")
public class OwntestRegister extends HttpServlet {
	private OwntestDB owntestDB = null;
	
    public OwntestRegister() {
    	try {
    		owntestDB = new OwntestDB();
		} catch (Exception e) {
			System.out.println("Rebug1");
		}
    }
	private boolean isEmail(String email) {
        
		String emailmember = "^[a-zA-Z0-9._%+-]+@(gmail\\.com|yahoo\\.com\\.tw)$";
    
		return email.matches(emailmember);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		if (owntestDB == null) return;
		
		request.setCharacterEncoding("UTF-8");
		String account = request.getParameter("account");
		String passwd = request.getParameter("passwd");
		
        if (!isEmail(account)) {
            response.sendRedirect("owntestregister.jsp?errType=4");
            System.out.println("debug8");
            return;
        }

		if (!owntestDB.isAccountExist(account)) {
			System.out.println("Rebug3");
			try {
				if(owntestDB.addMember(account, passwd)) {
					response.sendRedirect("owntest.jsp");
					System.out.println("Rebug4");
				}else {
					response.sendRedirect("owntestregister.jsp?errType=3");
					System.out.println("Rebug5");
				}
			}catch (Exception e) {
				response.sendRedirect("owntestregister.jsp?errType=2");
				System.out.println("Rebug6");
			}
		}else {
			response.sendRedirect("owntestregister.jsp?errType=1");
			System.out.println("Rebug7");
		}
		
	}

}
