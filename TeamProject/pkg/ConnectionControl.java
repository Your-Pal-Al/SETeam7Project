package pkg;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;

//controller for initial panel
public class ConnectionControl implements ActionListener {
	
	// Private data field for storing the container.
	private JPanel container;
	private MancalaClient client;

	// Constructor for the initial controller.
	public ConnectionControl(JPanel container, MancalaClient client) {
		this.container = container;
		this.client = client;
	}

	// Handle button clicks.
	public void actionPerformed(ActionEvent ae) {
		// Get the name of the button clicked.
		String command = ae.getActionCommand();

		// The Login button takes the user to the login panel.
		if (command.equals("Connect")) {
			ConnectionPanel connectionPanel = (ConnectionPanel) container.getComponent(5);
			client.setHost(connectionPanel.getHost());
			client.setPort(connectionPanel.getPort());
			
			try {
				client.openConnection();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			CardLayout cardLayout = (CardLayout) container.getLayout();
			cardLayout.show(container, "1");

		}
	}
}
