package tw.RC.tutor;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;
import javax.swing.event.MouseInputListener;

public class RC24 extends JFrame{//2.引用extends JFrame
	
	public RC24() { //3.
		
		MyMouseListener mouseListener = new MyMouseListener(); // 6.
		addMouseListener(mouseListener);//4.括號內本來是NULL(),第五.六步做完再填入
		addMouseMotionListener(mouseListener);//因為	MyMouseListener有實作	MouseMotionListener
		
		setSize(640, 480);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	
	public static void main(String[] args) {
		new RC24();//1.
	}
	
}
class MyMouseListener implements MouseListener, MouseMotionListener {//5.

	@Override
	public void mouseClicked(MouseEvent e) {
		System.out.println("Click");
	}

	@Override
	public void mousePressed(MouseEvent e) {
		System.out.println("Pressed");
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		System.out.println("Released");
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
	
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		System.out.println("Dragged");
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}

