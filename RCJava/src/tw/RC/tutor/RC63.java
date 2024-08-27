package tw.RC.tutor;

import java.util.Timer;
import java.util.TimerTask;

public class RC63 {
	public static void main(String[] args) {
		Timer timer = new Timer(true);//(true)變成背景,前景一結束背景就結束
		timer.schedule(new MyTask(), 0, 1*1000);//(3*1000=3S)
		timer.schedule(new StopTask(timer), 10*1000);
		System.out.println("Main");//前景一結束背景就結束
	}
}
class MyTask extends TimerTask {
	private int i;
	@Override
	public void run() {
		System.out.println(i++);
	}
}
class StopTask extends TimerTask {
	private Timer timer;
	public StopTask(Timer timer) {
		this.timer = timer;
	}
	@Override
	public void run() {
		timer.purge();
		timer.cancel();
		timer = null;
	}
}	
