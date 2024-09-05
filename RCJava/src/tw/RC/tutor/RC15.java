package tw.RC.tutor;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;

public class RC15 extends JFrame{//第一步:父類別為視窗
	private JButton b1, b2, b3; //定義自己能用的JButton b1, b2, b3
	
	public RC15() {//第三步:建構式初始值,因為RC15已經是Frame了
		b1 = new JButton("B1"); //創建按鈕物件顯示B1 
		b2 = new JButton("B2");
		b3 = new JButton("B3");
		
		setLayout(new FlowLayout());
		add(b1); add(b2); add(b3);
		
		
		setSize(640, 480);//視窗大小
		setVisible(true); //視窗顯示
		setDefaultCloseOperation(EXIT_ON_CLOSE);//案XX關閉,程式也結束
	}
	
	public static void main(String[] args) {
		new RC15();//第二步:將RC15創建出來
		
	}

}
