package tw.RC.tutor;

import java.util.LinkedList;
import java.util.List;

public class RC30 {

	public static void main(String[] args) {
		List list = new LinkedList();
		list.add("RC");
		list.add(123);
		list.add(1, 12.3);//前面係數為要在LIST里第幾個位置
		list.add(true);
		list.add(123);
		list.add("RC");
		
		list.add(list.remove(3));//將第3位的true先remove出來再add變成最後面
		
		System.out.println(list.size());
		System.out.println("---");
		for (Object obj : list ) {
			System.out.println(obj);
		}
		
		
	}

}
