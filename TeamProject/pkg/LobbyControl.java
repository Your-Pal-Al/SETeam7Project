package pkg;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JPanel;

public class LobbyControl implements ActionListener {
	// Private data fields for the container and chat client.
	private JPanel container;
	private MancalaClient client;
	 
	// Constructor for the login controller.
	public LobbyControl(JPanel container, MancalaClient client)
	{
	  this.container = container;
	  this.client = client;
	}
	
	
	// Handle button clicks.
	public void actionPerformed(ActionEvent ae)
	{
	  // Get the name of the button clicked.
	  String command = ae.getActionCommand();
	  
	  // The Cancel button takes the user back to the initial panel.
	  if ("Logout".equals(command))
	  {
	    CardLayout cardLayout = (CardLayout)container.getLayout();
	    cardLayout.show(container, "1");
	  }
	  else if ("Queue".equals(command))
	  {
	    //GameBoardPanel gameBoardPanel = (GameBoardPanel)container.getComponent(5);
	    //gameBoardPanel.setError("");
	    CardLayout cardLayout = (CardLayout)container.getLayout();
	    cardLayout.show(container, "5");
	    try {
			client.sendToServer("Queue");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  }
	}

}
