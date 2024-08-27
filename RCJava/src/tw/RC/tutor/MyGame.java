package tw.RC.tutor;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import tw.RC.apis.GamePanel;
import tw.RC.apis.GamePanelV2;

public class MyGame extends JFrame{
	private GamePanelV2 panel;
	public MyGame () {
		
		setLayout(new BorderLayout());
		panel = new GamePanelV2();
		add(panel, BorderLayout.CENTER);
		
		setSize(640, 480);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		new MyGame();
	}

}
