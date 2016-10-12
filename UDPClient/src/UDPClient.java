
import java.awt.EventQueue;
import java.io.BufferedReader;

import java.io.IOException;

import java.io.InputStreamReader;

import java.net.*;

public class UDPClient {
	
	public GUI gui;
	
	public final static int PORT = 7;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI frame = new GUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

		String hostname = "localhost";

		if (args.length > 0) hostname = args[0];

		try {

			InetAddress ia = InetAddress.getByName(hostname);

			DatagramSocket socket = new DatagramSocket();

			SenderThread sender = new SenderThread(socket, ia, PORT);

			sender.start();

			Thread receiver = new ReceiverThread(socket);

			receiver.start();

		} catch (UnknownHostException ex) { System.err.println(ex);

		} catch (SocketException ex) { System.err.println(ex);

		}

	}

}
