package tw.RC.tutor;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class RC52 {

	public static void main(String[] args) {
		try {
			InetAddress ip = InetAddress.getByName("www.google.com.tw");
			System.out.println(ip);
		} catch (UnknownHostException e) {
			System.out.println(e);
		}
	}

}
