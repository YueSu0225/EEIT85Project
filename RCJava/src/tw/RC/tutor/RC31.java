package tw.RC.tutor;

import java.util.LinkedList;
import java.util.List;

public class RC31 {

	public static void main(String[] args) {
		List<String> times = new LinkedList<String>();
		times.add(0,"10:20:30");
		times.add(0,"10:20:34");
		times.add(0,"10:20:37");
		times.add(0,"10:20:47");
		times.add(0,"10:20:51");
		times.add(0,"10:20:59");
		System.out.println("----");		
		for ( String time: times ) {
			System.out.println(time); 
			//因為times.add()每個都插入第一位0,新資料會插隊在舊資料前面
			//所以變成資料由新至舊,也就是時間從慢到快
		}
		
		
	}

}
