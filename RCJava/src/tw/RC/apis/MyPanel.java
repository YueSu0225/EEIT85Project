package tw.RC.apis;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedList;

import javax.swing.DebugGraphics;
import javax.swing.JPanel;

public class MyPanel extends JPanel {//1.
	private LinkedList<LinkedList<Point>> lines;//12.  //15.將單一線改成多線
	
	public MyPanel() {//2.
		setBackground(Color.YELLOW);//3.
		
		lines = new LinkedList<>();//12.與宣告型別相同
		MyListener myListener = new MyListener();//10.
		
		addMouseListener(myListener);//8.創建  11.帶入參數
		addMouseMotionListener(myListener);//8.創建  11.帶入參數		
	}
	
	private class MyListener extends MouseAdapter {//9.內部類別
		@Override
		public void mousePressed(MouseEvent e) {
			LinkedList<Point> line = new LinkedList<>();
			
			Point point = new Point(e.getX(), e.getY());//13.收集point
			line.add(point);//13.將收集的point放進line的List
		
			lines.add(line); //17.
			
		}
		@Override
		public void mouseDragged(MouseEvent e) {
			Point point = new Point(e.getX(), e.getY());//13.
			lines.getLast().add(point);//13. //17.
			
			repaint();//16.
		}
	}	
	
	@Override
	protected void paintComponent(Graphics g) {//4.
		super.paintComponent(g);
		
		Graphics2D g2d = (Graphics2D)g;//6.
		//強制轉型Graphics轉Graphics2D,前提是你會使用到Graphics2D的方法
				
		g2d.setColor(Color.BLUE);//7.
		g2d.setStroke(new BasicStroke(4));//7.		
//		g2d.drawLine(0, 0, 100, 200);//5.
		
		for (LinkedList<Point>line : lines) {//18.再將14步驟放進去
			for (int i = 1; i< line.size(); i++) {//14.
			Point p0 = line.get(i-1);
			Point p1 = line.get(i);
			g2d.drawLine(p0.getX(), p0.getY(), p1.getX(), p1.getY());
		}
		}
		
		
		
		
		
		
	}	
	
}
