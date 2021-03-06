package pkg;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;

//Controller for login
public class LoginControl implements ActionListener {
	
	// Private data fields for the container and chat client.
	private JPanel container;
	private MancalaClient client;

	// Constructor for the login controller.
	public LoginControl(JPanel container, MancalaClient client) {
		this.container = container;
		this.client = client;
	}

	// Handle button clicks.
	public void actionPerformed(ActionEvent ae) {
		// Get the name of the button clicked.
		String command = ae.getActionCommand();

		// The Cancel button takes the user back to the initial panel.
		if ("Cancel".equals(command)) {
			CardLayout cardLayout = (CardLayout) container.getLayout();
			cardLayout.show(container, "1");
		}

		// The Submit button submits the login information to the server.
		else if ("Submit".equals(command)) {
			// Get the username and password the user entered.
			LoginPanel loginPanel = (LoginPanel) container.getComponent(1);
			LoginData data = new LoginData(loginPanel.getUsername(), loginPanel.getPassword());

			// Check the validity of the information locally first.
			if (data.getUsername().equals("") || data.getPassword().equals("")) {
				displayError("You must enter a username and password.");
				return;
			}

			// Submit the login information to the server.
			try {
				client.sendToServer(data);
			} catch (IOException e) {
				e.printStackTrace();
				displayError("Error connecting to the server.");
			}
		}
	}

	// After the login is successful, set the User object and display the contacts
	// screen.
	public void loginSuccess() {
		CardLayout cardLayout = (CardLayout) container.getLayout();
		cardLayout.show(container, "4");
	}

	// Method that displays a message in the error label.
	public void displayError(String error) {
		LoginPanel loginPanel = (LoginPanel) container.getComponent(1);
		loginPanel.setError(error);
	}
	
	public String getUser() {
		LoginPanel loginPanel = (LoginPanel) container.getComponent(1);
		return loginPanel.getUsername();
	}
}
