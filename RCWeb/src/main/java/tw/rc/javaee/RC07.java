package tw.rc.javaee;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/RC07")
public class RC07 extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		String x, y, result, op;
		x = y = result = op = "";
		
		request.setCharacterEncoding("UTF-8");

		
		if (request.getParameter("x") != null ) {
			x = request.getParameter("x");
			y = request.getParameter("y");
			op = request.getParameter("op");
			
			try {
			int intX = Integer.parseInt(x);
			int intY = Integer.parseInt(y);
			
			int r1, r2;
			
			switch (op) {
				case "1":
					r1 = intX + intY;
					result += r1;
					break;
				case "2":
					r1 = intX - intY;
					result += r1;
					break;
				case "3":
					r1 = intX * intY;
					result += r1;
					break;
				case "4":
					r1 = intX / intY;
					r2 = intX % intY;
					result += String.format("%d ...... %d", r1, r2);
							
					break;
			}
			}catch (Exception e) {
				
			}
		}	    
//			try {
//			int r = Integer.parseInt(x) + Integer.parseInt(y);
//			result += r;
//			}catch (Exception e) {
//				result = "WTF?";
//			}
//		}
	
		
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<meta charset='UTF-8'>");
		out.println("<form action='RC07'>");
		out.printf("<input type='number' name='x' value='%s' />\n", x);//%s要先改成printf,為了顯示輸入後的數字顯示在上面
		out.println("<select name='op'>");//name是在html裡要在後端JAVA的參數
		out.printf("<option value='1' %s>+</option>\n", op.equals("1")?"selected":"");//value=1顯示在op=1		op.equals("1")?"selected":""   選擇如果是1就顯示在上面,selected
		out.printf("<option value='2' %s>-</option>\n", op.equals("2")?"selected":"");//value=2顯示在op=2		op.equals("2")?"selected":""   選擇如果是2就顯示在上面,selected
		out.printf("<option value='3' %s>x</option>\n", op.equals("3")?"selected":"");//value=3顯示在op=3		op.equals("3")?"selected":""   選擇如果是3就顯示在上面,selected
		out.printf("<option value='4' %s>/</option>\n", op.equals("4")?"selected":"");//value=4顯示在op=4		op.equals("4")?"selected":""   選擇如果是4就顯示在上面,selected
		out.println("</select>");
		out.printf("<input type='number' name='y' value='%s' />\n", y);
		out.println("<input type='submit' value='=' />");
		out.printf("<span>%s</span>\n", result);
		out.println("</form>");

		
		response.flushBuffer();
		
		
		
	}

}