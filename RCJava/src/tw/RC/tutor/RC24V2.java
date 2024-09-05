package tw.RC.tutor;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;
import javax.swing.event.MouseInputListener;

public class RC24V2 extends JFrame{
	
	public RC24V2() { 
		
		MyMouseListenerV2 mouseListener = new MyMouseListenerV2();
		addMouseListener(mouseListener);
		addMouseMotionListener(mouseListener);
		
		setSize(640, 480);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	
	public static void main(String[] args) {
		new RC24V2();
	}
class MyMouseListenerV2	extends MouseAdapter {
	public void mousePressed(MouseEvent e) {
		System.out.println("Press");
		
	}
	public void mouseDragged(MouseEvent e) {
		System.out.println(String.format("%d x %d", e.getX() ,getY()));
		
	}
}
	
	
	
	
}


