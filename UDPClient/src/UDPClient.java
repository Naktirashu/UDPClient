
import java.awt.EventQueue;
import java.io.BufferedReader;

import java.io.IOException;

import java.io.InputStreamReader;

import java.net.*;
import java.util.Observable;

public class UDPClient extends Observable implements Runnable{
	private String messageReceived = "";
	private String messageSend = "";
	ReceiverThread receiverThread;
	

	public static int DEFAULT_PORT = 7;
	

	public void run(){
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI frame = new GUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});*/
		

		String hostname = "localhost";

		//if (args.length > 0) hostname = args[0];

		try {

			InetAddress ia = InetAddress.getByName(hostname);

			DatagramSocket socket = new DatagramSocket();

			SenderThread sender = new SenderThread(socket, ia, DEFAULT_PORT, this);

			sender.start();

			Thread receiver = new ReceiverThread(socket, this);

			receiver.start();

		} catch (UnknownHostException ex) { System.err.println(ex);

		} catch (SocketException ex) { System.err.println(ex);

		}

	}

	public String getMessageReceived() {
		return messageReceived;
	}

	public void setMessageReceived(String messageReceived) {
		this.messageReceived = messageReceived;
		setChanged();
		notifyObservers(messageReceived);
	}

	public String getMessageSend() {
		return messageSend;
	}
	
	public void setMessageSend(String messageSend) {
		this.messageSend = messageSend;
	}
	
	public void setPort(int start_port) {
		DEFAULT_PORT = start_port;
		setChanged();
		notifyObservers(DEFAULT_PORT);
		
	}

}
