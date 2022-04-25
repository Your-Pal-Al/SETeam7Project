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
	}

	// waitTurn method,
	public void waitTurn() {
		//updateDisplayedPits();
		try {
			GameBoardPanel gameBoardPanel = (GameBoardPanel) container.getComponent(4);
			gameBoardPanel.waitTurn();
		} catch (Exception e) {
			System.out.println("GBC failed to get container componenet 5");
			e.printStackTrace();
		}
		
		System.out.println("GBC is waiting."); // TODO: Delete - Debug
	}

	public void takeTurn() {
		//updateDisplayedPits();
		try {
			GameBoardPanel gameBoardPanel = (GameBoardPanel) container.getComponent(4);
			gameBoardPanel.takeTurn();
		} catch (Exception e){
			System.out.println("GBC failed to take turn.");
			e.printStackTrace();
		}
		System.out.println("GBC is taking turn."); // TODO Debug
	}
	
	public void exit() {
		CardLayout cardLayout = (CardLayout) container.getLayout();
		cardLayout.show(container, "4");
		player = 0;
		try {
			client.sendToServer("exit");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// Method to handle button clicks.
	public void actionPerformed(ActionEvent ae) {

		// Get the name of the button clicked.
		String command = ae.getActionCommand();

		// The exit button sends updated game data to server and takes the user to the
		// lobby panel.
		if (command.equals("exit")) {
			exit();
		}

		//move button press
		else {
			int move = Integer.parseInt(command);
			makeMove(move);
			if (player == 1) {
				try {
					String data = "P1move" + move;
					client.sendToServer(data);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					System.out.println("GBC failed to send move to server."); // TODO: Delete - Debug
					e.printStackTrace();
				}
			} else if (player == 2) {
				try {
					String data = "P2move" + move;
					client.sendToServer(data);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					System.out.println("GBC failed to send move to server."); // TODO: Delete - Debug
					e.printStackTrace();
				}
			}
		}

	}
	
	public void updateDisplayedPits() {
		GameBoardPanel gameBoardPanel = (GameBoardPanel) container.getComponent(4);
		for (int i = 0; i < 14; i++) {
			gameBoardPanel.setPit(i, game_data.getPit(i));
		}	
	}
	
	public void win() {
		GameBoardPanel gameBoardPanel = (GameBoardPanel) container.getComponent(4);
		gameBoardPanel.win();
	}
	public void lose() {
		GameBoardPanel gameBoardPanel = (GameBoardPanel) container.getComponent(4);
		gameBoardPanel.lose();
	}
	
	public void newBoard() {
		game_data.newBoard();
		updateDisplayedPits();
	}
	
	public void makeMove(int pit) {
		game_data.makeMove(pit);
		updateDisplayedPits();
	}

	// Setter to set Player 1
	public void setPlayer1() {
		this.player = 1;
		GameBoardPanel gameBoardPanel = (GameBoardPanel) container.getComponent(4);
		gameBoardPanel.setPlayer1();
		System.out.println("GBC set Player 1"); // TODO: Delete - Debug
		//waitTurn();
	}

	// Setter to set Player 2
	public void setPlayer2() {
		this.player = 2;
		GameBoardPanel gameBoardPanel = (GameBoardPanel) container.getComponent(4);
		gameBoardPanel.setPlayer2();
		System.out.println("GBC set Player 2"); // TODO: Delete - Debug
		waitTurn();
	}

	// TODO: Update - setter to get Player (current?)
	public int getPlayer() {
		return player;
	}

	// Setter to set game_data
	public void setPits(int[] pits) {
		game_data.setPits(pits);
		updateDisplayedPits();
	}
	
	public String getState() {
		return game_data.getState();
	}
	
	public GameData getGameData() {
		
		return game_data;
	}
}
