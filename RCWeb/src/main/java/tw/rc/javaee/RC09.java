package tw.rc.javaee;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;


@WebServlet("/RC09")
@MultipartConfig( location = "C:\\Users\\User\\eclipse-workspace\\RCWeb\\src\\main\\webapp\\upload" )    
//類似宣告蓋在,我會處理Multipart,檔案存在MultipartConfig( location = "路徑" )
public class RC09 extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF=8");
		PrintWriter out = response.getWriter();
		
		
		System.out.println(request.getRemoteAddr());
		Part part = request.getPart("upload");
		System.out.println(part.getName());
		System.out.println(part.getSize());
		System.out.println(part.getSubmittedFileName());
		String filename = request.getRemoteAddr() + "_" + part.getSubmittedFileName();
		
		
		if (part.getSize() >0 ) {
			
			part.write(filename);
			out.print("Upload Success");
		}else {
			out.print("Upload Failure");
		}
		
		
		
		
	}

}
