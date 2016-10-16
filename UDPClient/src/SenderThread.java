import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class SenderThread extends Thread {
	
	UDPClient udpClient;
	
	private InetAddress server;
	private DatagramSocket socket;
	private int port;
	private volatile boolean stopped = false;
	
	//test for sending strings
	
	
	public SenderThread(DatagramSocket socket, InetAddress address, int port, UDPClient udpClient) {
		this.server = address;
		this.port = port;
		this.socket = socket;
		this.socket.connect(server, port);
		
		//unfinished 
		this.udpClient = udpClient;
	}

	public void halt(){
		this.stopped = true;
	}
	
	
	@Override
	public void run() {
		try{
			//BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
			//research and implement linked blocking queue
			/*String value = null;
			try {
				value = udpClient.getQueue().take();
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
			
			while(true){
				if(stopped) return;
				String theLine = null;
				try {
					theLine = udpClient.getQueue().take();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(theLine.equals("."))break;
				byte[] data = theLine.getBytes("UTF-8");
				DatagramPacket output = new DatagramPacket(data, data.length, server, port);
				socket.send(output);
				Thread.yield();
				
			}
		}
		catch(IOException ex){System.err.println(ex);}
		
	}

}
