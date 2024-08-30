package tw.rc.javaee;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;


@WebServlet("/RC22")
@MultipartConfig
public class RC22 extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		// 1. 處理 Part ((取得part串流進來InputStream再變成readAllBytes然後再放在request.setAttribute
		Part part = request.getPart("icon");
		System.out.println(part.getContentType());
		byte[] buf = part.getInputStream().readAllBytes();
		request.setAttribute("icon", buf);
		
		// 2. 其他工作交給 jsp 去處理,在getRequestDispatcher("路徑")去forward
		request.getRequestDispatcher("insertMember.jsp").forward(request, response);
		
		
		
		
	}

}
