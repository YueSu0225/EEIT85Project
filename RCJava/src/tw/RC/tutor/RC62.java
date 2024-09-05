package tw.RC.tutor;

public class RC62 {
	public static void main(String[] args) {
		MyThread mt1 = new MyThread("A");
		MyThread mt2 = new MyThread("B");
		MyRunnable mr1 = new MyRunnable("C");
		Thread t1 = new Thread(mr1); //使用thread執行runnable
		
		mt1.start();//執行緒start生命只有一次,只能執行一次
		mt2.start();//執行會先被cpu叫去等候區,一定會執行,但時間準度不高
		t1.start();//效果與thread執行效果一樣
		
		System.out.println("OK");
		
	}
}

class MyThread extends Thread {
	private String name;
	
	public MyThread(String name) {
		this.name = name;
	}
	
	@Override
	public void run() {
		for (int i = 0; i < 20; i++) {
			System.out.printf("%s : %d\n", name, i);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
			}
		}
	}	
	
}

//Runnable的彈性比較大
class MyRunnable implements Runnable {//宣告:我父類別是object但是我有證照Runnable
	private String name;
	
	public MyRunnable(String name) {
		this.name = name;
	}
	
	@Override
	public void run() {
		for (int i = 0; i < 20; i++) {
			System.out.printf("%s : %d\n", name, i);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
			}
		}
	}	
	
}

