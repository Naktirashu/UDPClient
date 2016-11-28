import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class MessageHandler {
	/*
	 * This isnt correct to be able to send something to the senderThread. But can be adapted potentially, save and move on. 
	 * 
	 */
	
	
	private BlockingQueue<String> queue = new ArrayBlockingQueue<String>(10);
	private UDPClient udpClient;
	
	public MessageHandler(UDPClient udpClient){
		this.udpClient= udpClient;
	}
	
	public void producer() throws InterruptedException{
		
		while(true){
			if(udpClient.getMessageSend() != null){
				queue.put(udpClient.getMessageSend());
				udpClient.setMessageSend(null);				
			}
		}
	}
	
	

	public void consumer() {
		while(true){
			try {
				Thread.sleep(100);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			String value = null;
			try {
				value = queue.take();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//udpClient.setSendingMessage(value);
			
		}
		
	}
	
	
}
