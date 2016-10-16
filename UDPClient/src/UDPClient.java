
import java.awt.EventQueue;
import java.io.BufferedReader;

import java.io.IOException;

import java.io.InputStreamReader;

import java.net.*;
import java.util.Observable;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class UDPClient extends Observable implements Runnable{
	
	private MessageHandler messageHandler;
	private String messageReceived = "";
	

	private String messageSend = "";
	private String sendingMessage = "";
	private BlockingQueue<String> queue = new ArrayBlockingQueue<String>(10);

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
		messageHandler = new MessageHandler(this);

		String hostname = "localhost";

		//if (args.length > 0) hostname = args[0];

		try {

			InetAddress ia = InetAddress.getByName(hostname);

			DatagramSocket socket = new DatagramSocket();

			//FIXME need to research/ implement a blocking queue
			
			SenderThread sender = new SenderThread(socket, ia, DEFAULT_PORT, this);

			sender.start();

			Thread receiver = new ReceiverThread(socket, this);

			receiver.start();
			
			/*Thread t1 = new Thread(new Runnable(){
				public void run(){
					try{
						messageHandler.producer();
					}catch (InterruptedException e){
						e.printStackTrace();
					}
				}
			});
			
			Thread t2 = new Thread(new Runnable(){
				public void run(){
					messageHandler.consumer();
				}
			});
			
			t1.start();
			t2.start();*/
			

		} catch (UnknownHostException ex) { System.err.println(ex);

		} catch (SocketException ex) { System.err.println(ex);

		}

	}
	
	public String getSendingMessage() {
		return sendingMessage;
	}

	public void setSendingMessage(String sendingMessage) {
		this.sendingMessage = sendingMessage;
		setChanged();
		notifyObservers(sendingMessage);
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
	
	public BlockingQueue<String> getQueue() {
		return queue;
	}

	public void setQueue(BlockingQueue<String> queue) {
		this.queue = queue;
	}

}
