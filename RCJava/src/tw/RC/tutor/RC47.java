package tw.RC.tutor;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class RC47 {

	public static void main(String[] args) {
		//自動關閉方法:try(宣告方法;)
		try (FileReader fr = new FileReader("./dir1/file2.txt")){			
			int c;
			while ((c = fr.read()) != -1) {
				System.out.print((char)c);
			}
			
			System.out.println("OK");	
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
