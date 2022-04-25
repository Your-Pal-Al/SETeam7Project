package pkg;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;

//Driver class that creates all GUIS for the players
@SuppressWarnings("serial")
public class ClientGUI extends JFrame {

	private JPanel container;
	private CardLayout cardLayout;
	private GameBoardControl gbc;
	private JPanel view5;

	// Constructor that creates the client GUI.
	public ClientGUI() {

		// Set up the chat client.
		MancalaClient client = new MancalaClient();

		// Set the title and default close operation.
		this.setTitle("Mancala Clone");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Create the card layout container.
		cardLayout = new CardLayout();
		container = new JPanel(cardLayout);

		// Create the Controllers next
		InitialControl ic = new InitialControl(container, client);
		LoginControl lc = new LoginControl(container, client);
		CreateAccountControl cac = new CreateAccountControl(container, client);
		LobbyControl lbc = new LobbyControl(container, client);
		gbc = new GameBoardControl(container, client);
		ConnectionControl cc = new ConnectionControl(container, client);

		// Set the client info
		client.setLoginControl(lc);
		client.setCreateAccountControl(cac);
		client.setGameBoardControl(gbc);
		client.setConnectionControl(cc);

		// Create the four views. (need the controller to register with the Panels
		JPanel view1 = new InitialPanel(ic);
		JPanel view2 = new LoginPanel(lc);
		JPanel view3 = new CreateAccountPanel(cac);
		JPanel view4 = new LobbyPanel(lbc);
		view5 = new GameBoardPanel(gbc);
		JPanel view6 = new ConnectionPanel(cc);

		// Add the views to the card layout container.
		container.add(view1, "1");
		container.add(view2, "2");
		container.add(view3, "3");
		container.add(view4, "4");
		container.add(view5, "5");
		container.add(view6, "6");

		// Show the initial view in the card layout.
		cardLayout.show(container, "6");

		// Add the card layout container to the JFrame.
		// GridBagLayout makes the container stay centered in the window.
		this.setLayout(new GridBagLayout());
		this.add(container);

		// Show the JFrame.
		this.setSize(900, 350);
		this.setVisible(true);
	}

	public GameBoardControl getGameBoardControl() {

		return gbc;

	}

	public JPanel getGameBoardPanel() {

		return view5;

	}

	public void setView(String view) {
		
		cardLayout.show(container, view);
		
	}
	
	// Main function that creates the client GUI when the program is started.
	public static void main(String[] args) {
		new ClientGUI();
	}
}
