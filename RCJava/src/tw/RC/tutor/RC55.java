package tw.RC.tutor;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream.GetField;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class RC55 {

	public static void main(String[] args) {
		File source = new File("dir1/4.jpg");
		try (	BufferedInputStream bin = new BufferedInputStream(new FileInputStream(source));
				Socket client = new Socket(InetAddress.getByName("10.0.100.157"), 7777);				
				BufferedOutputStream bout =new BufferedOutputStream(client.getOutputStream()) ;) {
			
			byte[] buf = new byte[(int)source.length()];
			
			bin.read(buf);
			bout.write(buf);
	
			System.out.println("send OK");
		} catch (Exception e) {
			System.out.println(e);
		} 
		
	}

//	public static void main(String[] args) {// 傳文字
//		try (Socket client = new Socket(InetAddress.getByName("10.0.100.101"), 9999);				
//			BufferedOutputStream bout =new BufferedOutputStream(client.getOutputStream()) ;) {
//			
//			String mesg = "66666666";
//			bout.write(mesg.getBytes());
//			
//			System.out.println("send OK");
//		} catch (Exception e) {
//			System.out.println(e);
//		} 
//		
//	}
	
	
	
//	public static void main(String[] args) {
//		for (int i = 0; i < 65536; i++) {
//		try {
//			Socket client = new Socket(InetAddress.getByName("10.0.100.101"), i);
//			System.out.println(i);
//			client.close();
//		} catch (Exception e) {
//			System.out.println(e);
//		} 
//	}	
//	}
}
