package tw.RC.tutor;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class GuessNumber extends JFrame implements ActionListener {//宣告可以使用ActionListener
	private JTextField input;
	private JButton guess;
	private JTextArea log;
	private String answer;
	private int counter;
		
	public GuessNumber() {
		super("猜數字遊戲");
		
		input = new JTextField();
		guess = new JButton("猜");
		log = new JTextArea();
		
		input.setFont(new Font(null, Font.BOLD|Font.ITALIC, 36));//設定字體
		
		setLayout(new BorderLayout());
		
		JPanel top = new JPanel(new BorderLayout());//創造PANEL物件名為TOP
		top.add(guess, BorderLayout.EAST);//將GUESS(JButton)放在PANEL右邊
		top.add(input, BorderLayout.CENTER);//將INPUT(JTextField)放在中間
		
		add(top, BorderLayout.NORTH);//將PANEL放在JFrame上方
		add(log, BorderLayout.CENTER);//將logJTextArea()放在中間
		
		setSize(640, 480);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		init();
		initEvent();//呼叫initEvent事件
	}
	
	private void init() {
		answer = createAnswer(3);
		counter = 0;
		System.out.println(answer);
	}
	
	private void initEvent() {
		guess.addActionListener(this);//本類別(this)來使用addActionListener
	}
	
	private static String createAnswer(int d) {
		final int nums = 10;
		int[] poker = new int[nums];
		for (int i = 0; i < poker.length; i++) poker[i] = i;
		
		for (int i = nums-1; i > 0; i--) {
			int rand = (int)(Math.random()*(i+1));
			// poker[rand] <=> poker[i]
			int temp = poker[rand];
			poker[rand] = poker[i];
			poker[i] = temp;
		}
		
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < d; i++) {
			sb.append(poker[i]);
		}
			
		return sb.toString();
	}
	
	
	public static void main(String[] args) {
		new GuessNumber();
	}

	@Override
	public void actionPerformed(ActionEvent e) {//實作addActionListener方法
		counter++;
		
		String g = input.getText();
		String result = checkAB(g, answer);
	    log.append(String.format("%d. %s => %s\n", counter, g, result));

		input.setText("");
		
		if (result.equals("3A0B")) {
			JOptionPane.showMessageDialog(null, "WINNER");
		}else if (counter == 10) {
			JOptionPane.showMessageDialog(null, "LOSER:" + answer);
		}
		

		
	}
	
	private static String checkAB(String g, String a) {
		int A = 0, B = 0;
		
		for (int i = 0; i < a.length(); i++) {
			if (g.charAt(i) == a.charAt(i)) {//g 中第i 馬 == a 中第 i 馬
				A++;
			}else if (a.indexOf(g.charAt(i)) != -1) {//g 中第i 馬是否存在於 a 中
				B++;
			}
		}
		
		return String.format("%dA%dB", A, B);
	}

}
