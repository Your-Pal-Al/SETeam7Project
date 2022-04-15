package pkg;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JPanel;

public class GameBoardControl implements ActionListener {
	
	// Private data fields for the container and chat client.
	private JPanel container;
	private MancalaClient client;
	
	public GameBoardControl(JPanel container, MancalaClient client) {
	  this.container = container;
	  this.client = client;
	}
	  
	// Handle button clicks.
	public void actionPerformed(ActionEvent ae) {
		// Get the name of the button clicked.
	    String command = ae.getActionCommand();
	    
	    // The Login button takes the user to the login panel.
	    if (command.equals("exit"))
	    {
	    	CardLayout cardLayout = (CardLayout)container.getLayout();
	        cardLayout.show(container, "1");
	    }
	    else if (command.equals("1"))
	    {
	    	GameData data = new GameData(1);
	    	try {
				client.sendToServer(data);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	    else if (command.equals("2"))
	    {
	    	GameData data = new GameData(2);
	    	try {
				client.sendToServer(data);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	    else if (command.equals("3"))
	    {
	    	GameData data = new GameData(3);
	    	try {
				client.sendToServer(data);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	    else if (command.equals("4"))
	    {
	    	GameData data = new GameData(4);
	    	try {
				client.sendToServer(data);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	    else if (command.equals("5"))
	    {
	    	GameData data = new GameData(5);
	    	try {
				client.sendToServer(data);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	    else if (command.equals("6"))
	    {
	    	GameData data = new GameData(6);
	    	try {
				client.sendToServer(data);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	}
}
