package pkg;

import java.awt.*;
import javax.swing.*;

//gui for initial panel to login/create account
@SuppressWarnings("serial")
public class ConnectionPanel extends JPanel {
	private JTextField host;
	private JTextField port;
	private JLabel label;
	
	public String getHost() {
		return host.getText();
	}
	
	public int getPort() {
		return Integer.parseInt(port.getText());
	}
	
	public void setLabel(String text) {
		label.setText(text);
	}
	
	// Constructor for the initial panel.
	public ConnectionPanel(ConnectionControl cc) {
		
		// Create the information label.
		label = new JLabel("Connect to a listening server", JLabel.CENTER);

		// Create the host IP label and textbox
		JLabel hostLabel = new JLabel("Host name/IP: ");
		host = new JTextField(15);
		// Create the port label and text box
		JLabel portLabel = new JLabel("Port: ");
		port = new JTextField(5);
		// Add them to a buffer
		JPanel fieldBuffer = new JPanel(new GridLayout(2, 2, 5, 5));
		fieldBuffer.add(hostLabel);
		fieldBuffer.add(host);
		fieldBuffer.add(portLabel);
		fieldBuffer.add(port);

		// Create the connect button.
		JButton connectButton = new JButton("Connect");
		connectButton.addActionListener(cc);
		JPanel connectButtonBuffer = new JPanel();
		connectButtonBuffer.add(connectButton);

		// Arrange the components in a grid.
		JPanel grid = new JPanel(new GridLayout(3, 1, 5, 5));
		grid.add(label);
		grid.add(fieldBuffer);
		grid.add(connectButtonBuffer);
		this.add(grid);
	}
}
