package tw.rc.hi1.app;

import java.lang.reflect.Method;

public class RC05 {

	public static void main(String[] args) {
		Class<String> stringClass = String.class;
		
		Method[] methods = stringClass.getMethods();
		for (Method method : methods) {
			System.out.println(method.getName() + ":" + method.getParameterCount());
		}
		System.out.println("----------------");
		try {
			String s1 = stringClass.newInstance();
			System.out.println("OK");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}

}
