package tw.RC.apis;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JLabel;

public class MyClock extends JLabel{
	private Timer timer;
	
	public MyClock() {
		timer = new Timer();
		timer.schedule(new MyTask(), 0, 100);
//		setText("10:20:30");
	}
	
	private class MyTask extends TimerTask {
		@Override
		public void run() {
			SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
			setText(sdf.format(new Date()));
		}
	}
	
}
