package tw.RC.tutor;

import java.util.HashSet;

import tw.RC.apis.Bike;

public class RC27 {

	public static void main(String[] args) {
		HashSet set = new HashSet();
		
		set.add("RC");
		set.add(12);   //auto-boxing自動封箱 12 => new Integer(12):自動把int12包成物件實體 
		set.add(12.3);//auto-boxing自動封箱 double物件實體
		set.add("RC");
		set.add(new Bike());
		set.add(12);
		System.out.println(set.size());
		System.out.println(set);
		
		
	}

}
