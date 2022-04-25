package pkg;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.BevelBorder;

// GUI class for CreateAccount
@SuppressWarnings("serial")
public class CreateAccountPanel extends JPanel {
	
	// Private data fields for the important GUI components.
	private JTextField usernameField;
	private JPasswordField passwordField;
	private JPasswordField passwordConfirmField;
	private JLabel errorLabel;

	// Getter for the text in the username field.
	public String getUsername() {
		return usernameField.getText();
	}

	// Getter for the text in the password field.
	public String getPassword() {
		return new String(passwordField.getPassword());
	}

	// Getter for the text in the second password field.
	public String getPasswordVerify() {
		return new String(passwordConfirmField.getPassword());
	}

	// Setter for the error text.
	public void setError(String error) {
		errorLabel.setText(error);
	}

	// Constructor for the create account panel.
	public CreateAccountPanel(CreateAccountControl cac) {

		// Create a panel for the labels at the top of the GUI.
		JPanel labelPanel = new JPanel(new GridLayout(3, 1, 5, 5));
		errorLabel = new JLabel("", JLabel.CENTER);
		errorLabel.setForeground(Color.RED);
		JLabel instructionLabel = new JLabel("Enter a username and password to create an account.", JLabel.CENTER);
		JLabel instructionLabel2 = new JLabel("Your password must be at least 6 characters.", JLabel.CENTER);
		labelPanel.add(errorLabel);
		labelPanel.add(instructionLabel);
		labelPanel.add(instructionLabel2);
		labelPanel.setOpaque(false);

		// Create a panel for the account information form.
		JPanel accountPanel = new JPanel(new GridLayout(3, 2, 5, 5));
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
		JLabel passwordVerifyLabel = new JLabel("Verify Password:", JLabel.RIGHT);
		passwordConfirmField = new JPasswordField(10);
		JPanel passwordConfirmBuffer = new JPanel(new FlowLayout(FlowLayout.LEFT));
		passwordConfirmBuffer.setOpaque(false);
		passwordConfirmBuffer.add(passwordConfirmField);
		accountPanel.add(usernameLabel);
		accountPanel.add(usernameBuffer);
		accountPanel.add(passwordLabel);
		accountPanel.add(passwordBuffer);
		accountPanel.add(passwordVerifyLabel);
		accountPanel.add(passwordConfirmBuffer);
		accountPanel.setOpaque(false);

		// Create a panel for the buttons.
		JPanel buttonPanel = new JPanel();
		JButton submitButton = new JButton("Submit");
		submitButton.addActionListener(cac);
		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(cac);
		buttonPanel.add(submitButton);
		buttonPanel.add(cancelButton);
		buttonPanel.setOpaque(false);

		// Arrange the three panels in a grid.
		JPanel grid = new JPanel(new GridLayout(3, 1, 0, 10));
		grid.add(labelPanel);
		grid.add(accountPanel);
		grid.add(buttonPanel);
		grid.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
		grid.setBackground(new Color(210,180,140));
		grid.setPreferredSize(new Dimension(800,300));
		this.add(grid);
		this.setOpaque(false);
	}
}
