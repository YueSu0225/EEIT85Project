package tw.rc.javaee;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.Source;

@WebServlet("/RC17")//將本機圖片輸出在Web上
public class RC17 extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String source = "C:\\Users\\User\\eclipse-workspace\\RCWeb\\src\\main\\webapp\\upload\\ok.jpg";
		
		BufferedImage img = ImageIO.read(new File(source));
		Graphics2D g2d = img.createGraphics();
		
		Font font1 = new Font(null, Font.BOLD, 72);
		AffineTransform af = new AffineTransform();//專門在做變形用((可以實現圖形驗證器
		af.rotate(Math.toRadians(-30));//做旋轉
		Font font2 = font1.deriveFont(af);//將字體使用變形來做改變呈現
		
		g2d.setColor(Color.WHITE);
		g2d.setFont(font2);
		
		g2d.drawString("Welcome!!!", 100, 400);
		
		response.setContentType("image/jpeg");
		ImageIO.write(img, "JPEG", response.getOutputStream());
		response.flushBuffer();
		
		ImageIO.write(img, "JPEG", new File("D:/RC/RC.jpg"));//順便存檔
		
		
	}


}
