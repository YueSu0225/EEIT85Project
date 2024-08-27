package tw.RC.tutor;

public class RC65 {
	public static void main(String[] args) {//主執行續
		Thread1 t1 = new Thread1();
		Thread2 t2 = new Thread2();
		t1.start();
		t2.start();
//		System.out.println("Main Finish");//正常主執行續先完成
	
		try {//join觀念是強制同步;
			t1.join();//等t1執行續完成後才會執行;
		} catch (InterruptedException e) {
		}
			System.out.println("Main Finish");//等t1執行續完成後才會執行;
	}
}
class Thread1 extends Thread {//執行續
	@Override
	public void run() {
		System.out.println("Thread1 Start working...");
		
		try {
			Thread.sleep(3*1000);
		} catch (InterruptedException e) {
		}
		
		System.out.println("Thread1 FINISH!");
	}
}
class Thread2 extends Thread {//執行續
	@Override
	public void run() {
		System.out.println("Thread2 Start working...");
		
		try {
			Thread.sleep(5*1000);
		} catch (InterruptedException e) {
		}
		
		System.out.println("Thread2 FINISH!");
	}
}