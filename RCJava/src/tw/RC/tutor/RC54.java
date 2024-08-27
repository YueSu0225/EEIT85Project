package tw.RC.tutor;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class RC54 {

	public static void main(String[] args) {
		byte[] buf = new byte[4*1024];//建立封包大小
		try {
			DatagramSocket socket = new DatagramSocket(8888);//建立接收通道
			DatagramPacket packet = new DatagramPacket(buf, buf.length);//建立接收的封包(建立包大小buf)
			socket.receive(packet);
			socket.close();
			
			String urIP = packet.getAddress().getHostAddress();
			byte[] data = packet.getData();
			int len = packet.getLength();
			
			System.out.println("Receive OK");
			System.out.printf("%s => %s\n", urIP, new String(data, 0, len));
			System.out.println(data.length);
			System.out.println(len);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
