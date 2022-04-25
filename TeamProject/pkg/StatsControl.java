package pkg;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

//Controller for lobby
public class StatsControl implements ActionListener {

	// Private data fields for the container and chat client.
	private JPanel container;
	private MancalaClient client;

	// Constructor for the login controller.
	public StatsControl(JPanel container, MancalaClient client) {
		this.container = container;
		this.client = client;
	}

	// Handle button clicks.
	public void actionPerformed(ActionEvent ae) {
		// Get the name of the button clicked.
		String command = ae.getActionCommand();

		if (command.equals("exit")) {
			CardLayout cardLayout = (CardLayout) container.getLayout();
			cardLayout.show(container, "4");
		}
	}
	
	public void setStats(String wins, String losses) {
		StatsPanel statsPanel = (StatsPanel) container.getComponent(6);
		statsPanel.setWins(wins);
		statsPanel.setLosses(losses);
	}

}
