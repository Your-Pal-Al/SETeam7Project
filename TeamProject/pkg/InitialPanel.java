package pkg;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.BevelBorder;

//gui for initial panel to login/create account
@SuppressWarnings("serial")
public class InitialPanel extends JPanel {
	
	// Constructor for the initial panel.
	public InitialPanel(InitialControl ic) {
		
		// Create the information label.
		JLabel label = new JLabel("Account Information", JLabel.CENTER);

		// Create the login button.
		JButton loginButton = new JButton("Login");
		loginButton.addActionListener(ic);
		JPanel loginButtonBuffer = new JPanel();
		loginButtonBuffer.add(loginButton);
		loginButtonBuffer.setOpaque(false);

		// Create the create account button.
		JButton createButton = new JButton("Create");
		createButton.addActionListener(ic);
		JPanel createButtonBuffer = new JPanel();
		createButtonBuffer.add(createButton);
		createButtonBuffer.setOpaque(false);

		// Arrange the components in a grid.
		JPanel grid = new JPanel(new GridLayout(3, 1, 5, 5));
		grid.add(label);
		grid.add(loginButtonBuffer);
		grid.add(createButtonBuffer);
		grid.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
		grid.setBackground(new Color(210,180,140));
		grid.setPreferredSize(new Dimension(800,200));
		this.add(grid);
		this.setOpaque(false);
	}
}
