package tw.rc.javaee;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tw.rc.apis.MyModel;

@WebServlet("/RC15")
public class RC15 extends HttpServlet {//Controller

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		// 接收參數
		String x = request.getParameter("x");
		String y = request.getParameter("y");

		// 運算工作(import tw.rc.apis.MyModel;)
		try {
			MyModel myModel = new MyModel(x, y);
			String result = myModel.plus();
			request.setAttribute("result", result);
			request.setAttribute("x", x);
			request.setAttribute("y", y);
			request.setAttribute("view", "view2");
		} catch (Exception e) {
			request.setAttribute("result", "WTF???");
			request.setAttribute("x", x==null?"":x);
			request.setAttribute("y", y==null?"":y);
			request.setAttribute("view", "view1");
		}
		
		// 呈現 View 呈現在("RC16");
		response.setContentType("text/html; charset=UTF-8");
		RequestDispatcher dispatcher = request.getRequestDispatcher("RC16");
		dispatcher.forward(request, response);

	}

}
