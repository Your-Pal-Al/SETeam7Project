package pkg;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JPanel;

//GameBoardController class, controls GameBoard GUI
public class GameBoardControl implements ActionListener {

	// Private data fields for the container and chat client.
	private JPanel container;
	private MancalaClient client;
	private GameData game_data;
	private int player;

	// GameBoardControl constructor
	public GameBoardControl(JPanel container, MancalaClient client) {
		this.container = container;
		this.client = client;
		this.player = 1;
		this.game_data = new GameData();
		try {
			client.sendToServer(game_data); // TODO: Delete - Debug
			client.sendToServer("bubble"); // TODO: Delete - Debug
		} catch (IOException e) {
			System.out.println("GBC failed to send initial game data."); // TODO: Delete - Debug
			e.printStackTrace();
		}
	}

	// waitTurn method,
	public void waitTurn() {
		GameBoardPanel gameBoardPanel = (GameBoardPanel) container.getComponent(5);
		gameBoardPanel.waitTurn();
		System.out.println("GBC is waiting."); // TODO: Delete - Debug
	}

	public void takeTurn() {
		// TODO: Delete - System.out.println(client.isConnected());
		GameBoardPanel gameBoardPanel = (GameBoardPanel) container.getComponent(5);
		System.out.println("GBC is taking turn."); // TODO Debug
		gameBoardPanel.takeTurn();
		// TODO: Delete - CardLayout cardLayout = (CardLayout)container.getLayout();
		// TODO: Delete - cardLayout.show(container, "5");
	}

	// Method to handle button clicks.
	public void actionPerformed(ActionEvent ae) {

		// Get the name of the button clicked.
		String command = ae.getActionCommand();

		// The exit button sends updated game data to server and takes the user to the
		// lobby panel.
		if (command.equals("exit")) {
			CardLayout cardLayout = (CardLayout) container.getLayout();
			cardLayout.show(container, "4");
		}

		//move button press
		else {
			int move = Integer.parseInt(command) - 1;
			game_data.makeMove(move);
			try {
				client.openConnection();
				client.sendToServer(game_data);
			} catch (IOException e) {
				System.out.println("GBC failed to send move to server."); // TODO: Delete - Debug
				e.printStackTrace();
			}
		}
	}

	// Setter to set Player 1
	public void setPlayer1() {
		this.player = 1;
		System.out.println("GBC set Player 1"); // TODO: Delete - Debug
		waitTurn();
	}

	// Setter to set Player 2
	public void setPlayer2() {
		this.player = 2;
		System.out.println("GBC set Player 2"); // TODO: Delete - Debug
		waitTurn();
	}

	// TODO: Update - setter to get Player (current?)
	public int getPlayer() {
		return player;
	}

	// Setter to set game_data
	public void setData(GameData data) {
		game_data = data;
	}
}
