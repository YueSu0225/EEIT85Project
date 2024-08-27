package tw.RC.tutor;

public class RC07 {

	public static void main(String[] args) {
		//任何變數第一次給值就是初始化,只要有初始化給值就可以使用
		int[] ary1; //給一個整數陣列
		ary1 = new int[3]; //初始化變數,陣列裡有3個元素
		System.out.println(ary1[0]); //0
		System.out.println(ary1[1]); //0
		System.out.println(ary1[2]); //0
		System.out.println(ary1.length); //3
		boolean[] x ; //型別為布林值的陣列
		x = new boolean[3]; //給初始值,陣列裡有3個元素
		System.out.println(x[2]); // false
		System.out.println("----");
		ary1[1] = 100; //在陣列[1]裡帶入值: 100
		ary1[2] = 123; //在陣列[2]裡帶入值: 123
//		ary1[3214] = 44; //陣列裡的值只要符合: >=0的整數就可以
		for (int i = 0; i < ary1.length; i++ ) {
			System.out.println(ary1[i]); // 0,100,123
		}
		System.out.println("----");
		//尋訪陣列的方法 for-each
		for (int v : ary1) {//方法:要尋訪的陣列型別+變數:要尋訪的陣列
			System.out.println(v); // 0,100,123
		}
	}

}
