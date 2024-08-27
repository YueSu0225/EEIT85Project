package tw.RC.tutor;

public class RC10 {

	public static void main(String[] args) {
		int[][] a = new int[3][];
		a[0] = new int[2];
		a[1] = new int[3];
		a[2] = new int[4];
		
		for (int[] a1 : a) { //整數陣列a里的二維陣列
			for (int v : a1) { //二微陣列里的值
				System.out.print(v + " "); // 00 000 0000
			}
			System.out.println();  //在console裡面空一行
		}
		
	}

}
