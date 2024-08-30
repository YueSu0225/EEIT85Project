package tw.RC.tutor;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class TurtleGame extends JFrame{
	private JPanel panel;
	
	private List<Color> colors;
	private Random random;
	
	public TurtleGame() {
		setTitle("Game");
		setLayout(new BorderLayout());
		
        random = new Random();
        Colors();
		Grid();
		
		setSize(640, 480);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
        }
	
	
	private void Grid () {
        panel = new JPanel();
        panel.setLayout(new GridLayout(3, 3));
        
        for (int i = 1; i <= 9; i++) {
        	JButton button = new JButton(Integer.toString(i));
        	button.addActionListener(new ActionListener() {
    			@Override
    			public void actionPerformed(ActionEvent e) {
                    Color randomColor = colors.get(random.nextInt(colors.size()));
                    button.setBackground(randomColor);
    			}
    		});
            panel.add(button);
        }

        add(panel, BorderLayout.CENTER);
    }
    private void Colors() {
        colors = new ArrayList<>();
        colors.add(Color.RED);
        colors.add(Color.GREEN);
        colors.add(Color.BLUE);
        colors.add(Color.YELLOW);
        colors.add(Color.ORANGE);
        colors.add(Color.CYAN);
        colors.add(Color.MAGENTA);
        colors.add(Color.PINK);
        colors.add(Color.LIGHT_GRAY);
        colors.add(Color.DARK_GRAY);
    }
	
	
	public static void main(String[] args) {
		new TurtleGame();

	}

}
