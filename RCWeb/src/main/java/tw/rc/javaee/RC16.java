package tw.rc.javaee;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tw.rc.apis.RCUtils;

@WebServlet("/RC16")
public class RC16 extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 取出 Attribute
		String result = (String) request.getAttribute("result");
		String x = (String) request.getAttribute("x");
		String y = (String) request.getAttribute("y");
		String view = (String) request.getAttribute("view");
		
		try {
			// 先Load View
			String webPage = RCUtils.loadView(view);

			// 後Show View
			webPage = webPage.replaceAll("@x@", x).replaceAll("@y@", y).replaceAll("@result@", result);
			
			
			response.getWriter().print(webPage);
			response.flushBuffer();
		} catch (Exception e) {
			response.getWriter().print("ERRO!!!");
			response.flushBuffer();

		}
	}

}
