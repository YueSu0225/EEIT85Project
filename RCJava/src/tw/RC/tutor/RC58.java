package tw.RC.tutor;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class RC58 {

	public static void main(String[] args) {
		try {//拿到底層的頁面原始碼
			URL url = new URL("https://www.iii.org.tw");
			URLConnection conn = url.openConnection();
			
			BufferedInputStream bin = new BufferedInputStream(conn.getInputStream());
			BufferedReader reader = new BufferedReader(new InputStreamReader(bin));
			
			String line;
			while( (line = reader.readLine()) != null ) {
				System.out.println(line);
			}
			bin.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
