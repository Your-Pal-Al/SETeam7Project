package pkg;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;

//Controller class for CreateAccountPanel
public class CreateAccountControl implements ActionListener {
	// Private data fields for the container and chat client.
	private JPanel container;
	private MancalaClient client;

	// Constructor for the create account controller.
	public CreateAccountControl(JPanel container, MancalaClient client) {
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

		// The Submit button creates a new account.
		else if ("Submit".equals(command)) {
			// Get the text the user entered in the three fields.
			CreateAccountPanel createAccountPanel = (CreateAccountPanel) container.getComponent(2);
			String username = createAccountPanel.getUsername();
			String password = createAccountPanel.getPassword();
			String passwordConfirm = createAccountPanel.getPasswordVerify();

			// Username nor password should be empty
			if (username.equals("") || password.equals("")) {
				displayError("You must enter a username and password.");
				return;
			}

			// password and passwordConfirm must match
			else if (!password.equals(passwordConfirm)) {
				displayError("The two passwords did not match.");
				return;
			}

			// passwords must be at least 6 characters long
			if (password.length() < 6) {
				displayError("The password must be at least 6 characters.");
				return;
			}

			// Submit the new account information to the server.
			CreateAccountData data = new CreateAccountData(username, password);
			try {
				client.sendToServer(data);
			} catch (IOException e) {
				displayError("Error connecting to the server.");
			}
		}
	}

	// After account is created, display lobby panel
	public void createAccountSuccess() {
		CardLayout cardLayout = (CardLayout) container.getLayout();
		cardLayout.show(container, "4");
	}

	// Method that displays a message in the error label.
	public void displayError(String error) {
		CreateAccountPanel createAccountPanel = (CreateAccountPanel) container.getComponent(2);
		createAccountPanel.setError(error);
	}
}
