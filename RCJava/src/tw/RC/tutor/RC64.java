package tw.RC.tutor;

public class RC64 {
	public static void main(String[] args) {
		Store store = new Store();
		Producer p = new Producer(store);
		Consumer c1 = new Consumer(store, "RC");
		Consumer c2 = new Consumer(store, "Amy");
		Consumer c3 = new Consumer(store, "Tony");
		p.start();
		c1.start();
		c2.start();
		c3.start();
	}
}
class Store {//建立商店
	private int qty;//設計商品庫存量
	private int max;//設計貨架
	Store() {//建構式,讓貨架初始化;
		max = 10;//貨價最多10個
	}
	
	//設計鎖定方法
	synchronized void add (int add) throws InterruptedException{//進貨方法
		System.out.printf("準備進貨: %d\n", add);
		while (qty + add > max) {
			wait();//等候
		}
		qty += add;//qty=qty+add;
		System.out.printf("完成進貨: %d, 目前庫存: %d\n", add, qty);
		notify();//通知(由cpu決定通知誰)
	}
	
	synchronized void buy (int buy, String name) throws InterruptedException{
		System.out.printf("%s想買: %d\n", name, buy);
		while (qty < buy) {
			wait();
		}
		qty -= buy;//qty= qty-add;
		System.out.printf("完成賣出: %d(%s), 目前庫存: %d\n", buy, name,qty);
		notify();
	}	
}

class Producer extends Thread {//創造生產者執行續
	private Store store;//生產者要認識商店才能執行所以先讓生產者認識商店
	public Producer(Store store) {
		this.store = store;
	}
	@Override
	public void run() {
		for(int i =0; i < 10; i++) {//生產10次
			try {
				store.add(5);//生產一次5個
				Thread.sleep(200);//延遲0.2S
			} catch (InterruptedException e) {
				System.out.println(e);
			}
		}
	}
}

class Consumer extends Thread {
	private Store store;//消費者認識商店
	private String name;
	public Consumer(Store store, String name) {
		this.store = store;
		this.name = name;
	}
	@Override
	public void run() {
		for (int i = 0; i < 10; i++ ) {//買10次
			try {
				store.buy((int)(Math.random()*3)+1, name);//買1-3個,誰買
			} catch (InterruptedException e) {
				System.out.println(e);
			}
		}
	}
}



