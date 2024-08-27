package tw.rc.javaee;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@WebServlet("/RC10")
@MultipartConfig(location = "C:\\Users\\User\\eclipse-workspace\\RCWeb\\src\\main\\webapp\\upload")
public class RC10 extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		String account = request.getParameter("account");

		int count = 0;
		Collection<Part> parts = request.getParts();
		for (Part part : parts) {
			if (part.getName().equals("upload")) {
				if (part.getSize() > 0) {
					String fname = account + "_" + part.getSubmittedFileName();
					try {
						part.write(fname);
						count++;
					} catch (Exception e) {
					}

				}
			}
//			System.out.println(part.getName());
//			System.out.println(part.getSize());
//			System.out.println(part.getSubmittedFileName());
//			System.out.println(part.getContentType());
//			System.out.println("-----");

		}
		response.setContentType("text/html; charset=UTF=8");
		response.getWriter().printf("%d Field(s) Upload Success".formatted(count));
	}

}
