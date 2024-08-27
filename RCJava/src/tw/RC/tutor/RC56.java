package tw.RC.tutor;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.Buffer;

public class RC56 {

	public static void main(String[] args) {
		try (	
			ServerSocket server = new ServerSocket(7777);
			Socket socket = server.accept();	
			) {
			
			String urIP = socket.getInetAddress().getHostAddress();
			
			
			String fname = String.format("dir2/%s.jpg", urIP); 
			BufferedOutputStream bout = new BufferedOutputStream(new FileOutputStream(fname));
			
			BufferedInputStream bin = new BufferedInputStream(socket.getInputStream());
			byte[] buf = new byte[4*1024];
			int len;
			while( (len = bin.read(buf)) != -1 ) {
				bout.write(buf, 0, len);
			}
			
			
			bout.flush();
			bout.close();
			System.out.println(urIP + " => ");
			System.out.println("Server OK");
		} catch (IOException e) {
			System.out.println(e);
		}
	}
	
//	public static void main(String[] args) {
//		try (ServerSocket server = new ServerSocket(9999);
//			Socket socket = server.accept();	
//			BufferedReader reader =new BufferedReader(new InputStreamReader(socket.getInputStream()));) {
//			
//			String urIP = socket.getInetAddress().getHostAddress();
//			System.out.println(urIP + " => ");
//			
//			String line;
//			while ((line = reader.readLine()) != null) {
//				System.out.println(line);
//			}
//			
//			System.out.println("Server OK");
//		} catch (IOException e) {
//			System.out.println(e);
//		}
//	}
	
	
//	public static void main(String[] args) {
//		while(true) {
//			try (ServerSocket server = new ServerSocket(9999);
//				Socket socket = server.accept();
//				BufferedReader reader = new BufferedReader(
//						new InputStreamReader(socket.getInputStream()))) {
//	
//				String urIp = socket.getInetAddress().getHostAddress();
//				System.out.print(urIp + " => ");
//				
//				String line; StringBuffer sb = new StringBuffer();
//				while ((line = reader.readLine()) != null) {
//					sb.append(line + "\n");
//				}
//				String mesg = sb.toString();
//				System.out.println(mesg);
//				if (mesg.trim().equals("exit")) {
//					break;
//				}
//			} catch (IOException e) {
//				System.out.println(e);
//			}
//		}
//	}
	
}
