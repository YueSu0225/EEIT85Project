package tw.rc.javaee;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.RepaintManager;

import tw.rc.apis.Bike;


@WebServlet("/RC12")
public class RC12 extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		//String x = request.getParameter("x");
		
		int intX = (Integer)request.getAttribute("x");
		String aname = (String)request.getAttribute("name");
		Bike b1 = (Bike)request.getAttribute("bike");
		
		response.getWriter().printf("Welcome, %s, %d, %s, %s", name, intX, aname, b1);
		
		
		
		
	}


}
