package tw.rc.javaee;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/RC08")
public class RC08 extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println(req.getMethod());// 執行1,與super看誰在前就先執行
		super.service(req, resp);// 執行2

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("doGet");
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String account = request.getParameter("account");
		String passwd = request.getParameter("passwd");
		String[] habbit = request.getParameterValues("habbit");
		System.out.println("doPost");
		System.out.printf("acc:%s,pd:%s\n", account, passwd);
		if (habbit != null) {
			for (String h : habbit) {
				System.out.print(h);
			}
		}

	}

}
