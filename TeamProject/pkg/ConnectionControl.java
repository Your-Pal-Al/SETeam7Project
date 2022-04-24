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
			String host = connectionPanel.getHost();
			int port = connectionPanel.getPort();
			if (host.equals("")) {
				displayError("Please specify a host adress");
			} else if (port == 0) {
				displayError("Please specify a host port");
			} else {
				client.setHost(connectionPanel.getHost());
				client.setPort(connectionPanel.getPort());
			
				try {
					client.openConnection();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					displayError("Error connecting to host");
					e.printStackTrace();
				}
			}
		}
	}
	
	public void connectionAchieved() {
		CardLayout cardLayout = (CardLayout) container.getLayout();
		cardLayout.show(container, "1");
	}
	
	public void displayError(String error) {
		ConnectionPanel connectionPanel = (ConnectionPanel) container.getComponent(5);
		connectionPanel.setError(error);
	}
	
}
