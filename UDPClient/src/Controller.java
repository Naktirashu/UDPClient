import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller implements ActionListener {
	
	GUI gui;
	UDPClient udpClient;
	
	
	public void addModel(UDPClient udpClient){
		System.out.println("Adding Model: " + udpClient.getClass());
		this.udpClient = udpClient;
	}
	
	public void addView(GUI gui){
		System.out.println("Adding View: " + gui.getClass());
		this.gui = gui;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		//debug on which button was pressed (based on button label for some reason)
		System.out.println("Button was: " + e.getActionCommand());
		
		//doesn't work yet
		switch(e.getActionCommand()){
		case "Send":
			gui.setMessageReceived();
			String tmp = gui.getMessageReceived();
			
			try {
				udpClient.getQueue().put(tmp);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			break;
		
		}
		// TODO Auto-generated method stub
		
	}

	
}
