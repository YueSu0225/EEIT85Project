package tw.rc.javaee;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RC04
 */
@WebServlet("/RC04")
public class RC04 extends HttpServlet {
    public RC04() {
    	System.out.println("RC04()");
    }

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		System.out.println("service(1)");
		//super.service(req, resp);//呼叫doGet,doPost
		
		
		//如果doGet與doPost邏輯一樣,可用req.getMethod()來做呼叫就好
		System.out.println(req.getMethod());
	}



	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.print("doGet()");		
	}
	
	@Override
		protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
				throws ServletException, IOException {
		resp.setContentType("text/html; charset=UTF-8");
		PrintWriter out = resp.getWriter();
		out.print("doPost()");		

	}
	
}
