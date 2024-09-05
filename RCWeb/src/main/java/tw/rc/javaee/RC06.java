package tw.rc.javaee;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/RC06")
public class RC06 extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		String x = request.getParameter("x");
		String y = request.getParameter("y");
		System.out.printf("%s, %s\n", x, y);
		
		int r = Integer.parseInt(x) + Integer.parseInt(y);
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.printf("%s + %s = %d", x, y, r);
		
		//out.flush();//建議1
		//out.close();//建議1
		response.flushBuffer();//建議2
		//建議1與2則一即可
		
		
	}

}