package tw.RC.tutor;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.TreeSet;

public class RC28 {

	public static void main(String[] args) {
		TreeSet<Integer> power = new TreeSet<Integer>();
//		power.add(12); //可放進去
//		power.add("RC");//因為HashSet<Integer>換型只能放整數
//		power.add(127);//可放進去
//		power.add((byte)12); // auto-boxing 只能一次,所以只轉成int
						   // byte12 => int12 => Integer(12);
		
		while (power.size()<6) {//威力彩電腦選號機制
			power.add(new Random().nextInt(1, 39));
		}
		
		System.out.println(power);
		System.out.println("---");

		Iterator<Integer> it = power.iterator();
		while (it.hasNext()) {
			Integer num = it.next();
			System.out.println(num);	
		}
		
		System.out.println("---");
		
		for (Integer v : power) {
			System.out.println(v);
		}
	}

}
