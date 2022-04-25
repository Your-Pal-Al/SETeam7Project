package pkg;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.BevelBorder;

//GUI class for loginPanel
@SuppressWarnings("serial")
public class LoginPanel extends JPanel {
	
	// Private data fields for the important GUI components.
	private JTextField usernameField;
	private JPasswordField passwordField;
	private JLabel errorLabel;

	// Getter for the text in the username field.
	public String getUsername() {
		return usernameField.getText();
	}

	// Getter for the text in the password field.
	public String getPassword() {
		return new String(passwordField.getPassword());
	}

	// Setter for the error text.
	public void setError(String error) {
		errorLabel.setText(error);
	}

	// Constructor for the login panel.
	public LoginPanel(LoginControl lc) {

		// Create a panel for the labels at the top of the GUI.
		JPanel labelPanel = new JPanel(new GridLayout(2, 1, 5, 5));
		errorLabel = new JLabel("", JLabel.CENTER);
		errorLabel.setForeground(Color.RED);
		JLabel instructionLabel = new JLabel("Enter your username and password to log in.", JLabel.CENTER);
		labelPanel.add(errorLabel);
		labelPanel.add(instructionLabel);
		labelPanel.setOpaque(false);

		// Create a panel for the login information form.
		JPanel loginPanel = new JPanel(new GridLayout(2, 2, 5, 5));
		JLabel usernameLabel = new JLabel("Username:", JLabel.RIGHT);
		usernameField = new JTextField(10);
		JPanel usernameBuffer = new JPanel(new FlowLayout(FlowLayout.LEFT));
		usernameBuffer.setOpaque(false);
		usernameBuffer.add(usernameField);
		JLabel passwordLabel = new JLabel("Password:", JLabel.RIGHT);
		passwordField = new JPasswordField(10);
		JPanel passwordBuffer = new JPanel(new FlowLayout(FlowLayout.LEFT));
		passwordBuffer.setOpaque(false);
		passwordBuffer.add(passwordField);
		loginPanel.add(usernameLabel);
		loginPanel.add(usernameBuffer);
		loginPanel.add(passwordLabel);
		loginPanel.add(passwordBuffer);
		loginPanel.setOpaque(false);

		// Create a panel for the buttons.
		JPanel buttonPanel = new JPanel();
		JButton submitButton = new JButton("Submit");
		submitButton.addActionListener(lc);
		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(lc);
		buttonPanel.add(submitButton);
		buttonPanel.add(cancelButton);
		buttonPanel.setOpaque(false);

		// Arrange the three panels in a grid.
		JPanel grid = new JPanel(new GridLayout(3, 1, 0, 10));
		grid.add(labelPanel);
		grid.add(loginPanel);
		grid.add(buttonPanel);
		grid.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
		grid.setBackground(new Color(210,180,140));
		grid.setPreferredSize(new Dimension(800,200));
		this.add(grid);
		this.setOpaque(false);
	}
}
