package tw.RC.tutor;



import java.io.File;
import java.util.HashMap;
import java.util.Set;

import javax.swing.text.FieldView;

public class RC32 {

	public static void main(String[] args) {
		HashMap<String, Object> map = new HashMap<>();
		map.put("name", "RC");
		map.put("age", 18);
		map.put("gender", true);
		map.put("weight", 80.1);
		
		System.out.println(map.get("age"));
		System.out.println("---");
		Set<String> keys = map.keySet();
		for (String key: keys) {
			System.out.printf("%s => %s\n", key, map.get(key));
		
			
		}
		
		System.out.println(File.pathSeparator);
		System.out.println(File.separator);
	}

}
