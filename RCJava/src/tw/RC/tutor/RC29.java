package tw.RC.tutor;

import java.util.LinkedHashSet;

public class RC29 {

	public static void main(String[] args) {
		LinkedHashSet<String> names = new LinkedHashSet<String>();
		
		System.out.println(names.add("RC"));
		System.out.println(names.add("Eric"));
		System.out.println(names.add("Andy"));
		System.out.println(names.add("Mark"));
		System.out.println(names.add("Eric"));//因為前面有一個Eric,所以這個進不來,所以false
		System.out.println(names.add("Tony"));
		System.out.println(names.add("Eric"));
		System.out.println("----");
		
		for (String name : names) {
			System.out.println(name);
		}
	}

}
