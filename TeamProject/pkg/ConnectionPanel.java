package pkg;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.BevelBorder;

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
		return Integer.parseInt("0" + port.getText());
	}
	
	public void setError(String text) {
		label.setText(text);
		label.setForeground(Color.RED);
	}
	
	// Constructor for the initial panel.
	public ConnectionPanel(ConnectionControl cc) {
		
		// Create the information label.
		label = new JLabel("Connect to a listening server", JLabel.CENTER);
		
		// Create the host IP label and textbox
		JLabel hostLabel = new JLabel("Host name/IP: ", JLabel.RIGHT);
		host = new JTextField(15);
		JPanel hostFieldBuffer = new JPanel(new FlowLayout(FlowLayout.LEFT));
		hostFieldBuffer.setOpaque(false);
		hostFieldBuffer.add(host);
		// Create the port label and text box
		JLabel portLabel = new JLabel("Port: ", JLabel.RIGHT);
		port = new JTextField(5);
		JPanel portFieldBuffer = new JPanel(new FlowLayout(FlowLayout.LEFT));
		portFieldBuffer.setOpaque(false);
		portFieldBuffer.add(port);
		// Add them to a buffer
		JPanel fieldBuffer = new JPanel(new GridLayout(2, 2, 5, 5));
		fieldBuffer.add(hostLabel);
		fieldBuffer.add(hostFieldBuffer);
		//fieldBuffer.add(host);
		fieldBuffer.add(portLabel);
		//fieldBuffer.add(port);
		fieldBuffer.add(portFieldBuffer);
		fieldBuffer.setOpaque(false);

		// Create the connect button.
		JButton connectButton = new JButton("Connect");
		connectButton.addActionListener(cc);
		JPanel connectButtonBuffer = new JPanel();
		connectButtonBuffer.add(connectButton);
		connectButtonBuffer.setOpaque(false);

		// Arrange the components in a grid.
		JPanel grid = new JPanel(new GridLayout(3, 1, 5, 5));
		grid.add(label);
		grid.add(fieldBuffer);
		grid.add(connectButtonBuffer);
		grid.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
		grid.setBackground(new Color(210,180,140));
		grid.setPreferredSize(new Dimension(800,200));
		this.add(grid);
		this.setOpaque(false);
	}
}
