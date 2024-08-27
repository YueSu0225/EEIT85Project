package tw.RC.tutor;

import java.net.ServerSocket;
import java.net.Socket;

public class RC57 {

	public static void main(String[] args) {//Server端
		try (	ServerSocket server = new ServerSocket(80);
				Socket socket = server.accept();	
				) {
			System.out.println("OK");
		}catch (Exception e) {
			
		}
	}	
}
