import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

public class GUI extends JFrame {

	private JPanel contentPane;
	private JTextField messageBox;
	private String message = "";

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
		setBounds(100, 100, 450, 475);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTextArea feedBackArea = new JTextArea();
		feedBackArea.setBounds(10, 123, 414, 302);
		contentPane.add(feedBackArea);
		
		messageBox = new JTextField();
		messageBox.setBounds(10, 41, 234, 20);
		contentPane.add(messageBox);
		messageBox.setColumns(10);
		
		JButton btnSend = new JButton("Send");
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				message = messageBox.getText();
			}
		});
		btnSend.setBounds(254, 40, 89, 23);
		contentPane.add(btnSend);
		
		JLabel lblMessage = new JLabel("Message");
		lblMessage.setBounds(10, 26, 98, 14);
		contentPane.add(lblMessage);
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	
}