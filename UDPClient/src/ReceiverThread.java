import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class ReceiverThread extends Thread {
	UDPClient udpClient;
	
	private DatagramSocket socket;
	private volatile boolean stopped = false;
	
	public ReceiverThread(DatagramSocket socket, UDPClient udpClient) {
		this.socket = socket;
		this.udpClient = udpClient;
	}

	public void halt(){
		this.stopped = true;
	}
	
	public void run(){
		byte[] buffer = new byte[65507];
		while(true){
			if (stopped) return;
			DatagramPacket dp = new DatagramPacket(buffer, buffer.length);
			try{
				socket.receive(dp);
				String s = new String(dp.getData(),0,dp.getLength(), "UTF-8");
				udpClient.setMessageReceived(s);
				
				Thread.yield();
			}catch(IOException ex){
				System.err.println(ex);
			}
		}
	}
}
