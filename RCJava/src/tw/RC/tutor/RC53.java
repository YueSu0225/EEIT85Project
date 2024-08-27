package tw.RC.tutor;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class RC53 {

	public static void main(String[] args) {
		String mesg = "666666666666";//建立一個資料
		byte[] mesgBuf = mesg.getBytes();
		try {//DatagramSocket建立負責資料傳遞的通道(UDP)
			DatagramSocket socket = new DatagramSocket();
			DatagramPacket packet = new DatagramPacket(mesgBuf, mesgBuf.length,
					InetAddress.getByName("10.0.100.101"), 8888);//建立要傳送的packet封包(包含資料.資料length.對方ip.對方port號)
			
			socket.send(packet);
			socket.close();
			System.out.println("OK");
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
