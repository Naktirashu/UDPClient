import java.awt.EventQueue;

public class RunUDP {

	//private int start_port = 7; 
	
	public RunUDP(){
		
		Controller myController = new Controller();
		UDPClient udpClient = new UDPClient();
		GUI gui = new GUI();
		
		udpClient.addObserver(gui);
		
		//udpClient.setPort(start_port);
		//gui.setMessage("");
		
		myController.addModel(udpClient);
		myController.addView(gui);
		gui.addController(myController);
		
		udpClient.run();
		
		gui.setVisible(true);
		
		
		
	}
	
	public static void main(String[] args) {
			RunUDP mainRunUDP = new RunUDP();
			
			
	}
}
