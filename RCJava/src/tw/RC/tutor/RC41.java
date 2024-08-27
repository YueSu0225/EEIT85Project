package tw.RC.tutor;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class RC41 {
//same RC34
	public static void main(String[] args)  {
		try {
			File source = new File("./dir1/file2.txt");
			FileInputStream fin = new FileInputStream(source);
			
			
			int len; byte[] buf = new byte[(int)source.length()];
			fin.read(buf);
			System.out.println(new String(buf));
			
			
//			int c;
//			while ( (c = fin.read()) != -1) {
//				System.out.print((char)c);
//			}
			
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
