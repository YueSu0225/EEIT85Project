package tw.RC.tutor;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class RC34 {

	public static void main(String[] args)  {
		try {
			FileInputStream fin = new FileInputStream("./dir1/file1.txt");
			
			int c;
			while ( (c = fin.read()) != -1) {
				System.out.print((char)c);
			}
			
			fin.close();
			System.out.println();
			System.out.println("OK");
		} catch (FileNotFoundException e) {
			System.out.println(e);			
		}catch (IOException e) {
			System.out.println(e);
		}
		
	}

}
