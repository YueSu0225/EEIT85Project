package tw.RC.tutor;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;


public class RC48 {

	public static void main(String[] args) {
		//自動關閉語法:try(只能是能自動關閉的語法,不能有額外的敘述句)
		try (FileInputStream fin = new FileInputStream("dir1/ns1hosp.csv");
			InputStreamReader reader = new InputStreamReader(fin);
			BufferedReader br = new BufferedReader(reader);) {


			String line;
			while ((line = br.readLine()) !=null ) {
				//System.out.println(line);
				String[] data = line.split(",");
				System.out.printf("%s:%s:%s:%s\n", data[1], data[2], data[4], data[7]);
			}						
	
			System.out.println("OK");
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
