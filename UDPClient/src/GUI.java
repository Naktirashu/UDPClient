import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.File;
import java.util.Observable;
import java.util.Observer;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JScrollBar;
import java.awt.ScrollPane;

public class GUI extends JFrame implements Observer {

	//test comment

	private JPanel contentPane;
	
	protected File selectedFile;
	private JTextField fileNameField;
	private JButton btnSendFile;
	
	private JTextField messageBox;
	private String messageReceived = "";
	private String messageSend = "";
	private JButton btnSendMessage;
	
	
	private JTextArea feedBackArea;

	private ScrollPane scrollPane;


	/**
	 * Launch the application.
	 */
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
		});
	}*/

	/**
	 * Create the frame.
	 */
	public GUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 603, 606);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		feedBackArea = new JTextArea();
		feedBackArea.setBounds(10, 229, 568, 328);
		
		messageBox = new JTextField();
		messageBox.setBounds(20, 33, 211, 20);
		contentPane.add(messageBox);
		messageBox.setColumns(10);
		
		btnSendMessage = new JButton("Send");
		/*btnSendMessage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				message = messageBox.getText();
			}
		});*/
		btnSendMessage.setBounds(19, 78, 89, 44);
		contentPane.add(btnSendMessage);
		
		JLabel lblMessage = new JLabel("Message:");
		lblMessage.setBounds(10, 8, 98, 14);
		contentPane.add(lblMessage);
		
		JLabel lblSelectFile = new JLabel("File:");
		lblSelectFile.setBounds(254, 8, 81, 14);
		contentPane.add(lblSelectFile);
		
		JButton btnChooseFile = new JButton("Select");
		btnChooseFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//File chooser, starts in working folder
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setCurrentDirectory(new java.io.File("."));
				int returnValue = fileChooser.showOpenDialog(null);
				if (returnValue == JFileChooser.APPROVE_OPTION) {
					//when you selected a file, store it
					selectedFile = fileChooser.getSelectedFile();
					}
				//set the GUI nameField to show which file was selected
				fileNameField.setText(selectedFile.getName());
			}
		});
		btnChooseFile.setBounds(254, 23, 98, 45);
		contentPane.add(btnChooseFile);
		
		fileNameField = new JTextField();
		fileNameField.setEditable(false);
		fileNameField.setBounds(362, 35, 135, 20);
		contentPane.add(fileNameField);
		fileNameField.setColumns(10);
		
		JButton btnSendFile = new JButton("Send File");
		btnSendFile.setBounds(254, 79, 98, 43);
		contentPane.add(btnSendFile);
		
		scrollPane = new ScrollPane();
		scrollPane.add(feedBackArea);
		scrollPane.setBounds(20, 186, 540, 372);
		contentPane.add(scrollPane);
		
		//contentPane.setVisible(true);
	}

	public String getMessageReceived() {
		return messageReceived;
	}

	public void setMessageReceived() {
		messageReceived = messageBox.getText();
	}

	//FIXME add a "Sending: " message to the textArea for the string, we can just do a getText and print it, but for file it would be packet number
	@Override
	public void update(Observable arg0, Object arg1) {
		
		
		//print the received message to the textArea
		feedBackArea.append("Response from Server: " + arg1.toString() +"\n");
		//Scrolls with the incoming new data
		scrollPane.setScrollPosition(0,feedBackArea.getDocument().getLength());
		
	}
	
	public void addController(ActionListener controller){
		btnSendMessage.addActionListener(controller);

	}
}