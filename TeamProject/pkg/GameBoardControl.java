package pkg;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JPanel;

public class GameBoardControl implements ActionListener {
	
	// Private data fields for the container and chat client.
	private JPanel container;
	private MancalaClient client;
	private GameData game_data;
	private int player;
	
	public GameBoardControl(JPanel container, MancalaClient client) {
	  this.container = container;
	  this.client = client;
	  this.player = 1;
	  this.game_data = new GameData();
	  try {
		client.sendToServer(game_data); // Debug
		client.sendToServer("bubble"); // Debug
	} catch (IOException e) {
		// TODO Auto-generated catch block
		System.out.println("GBC failed to send initial game data."); // Debug
		e.printStackTrace();
	}
	}
	  
	public void waitTurn() {
		
		GameBoardPanel gameBoardPanel = (GameBoardPanel)container.getComponent(5);
	    gameBoardPanel.waitTurn();
	    System.out.println("GBC is waiting."); // Debug
	    //CardLayout cardLayout = (CardLayout)container.getLayout();
	    //cardLayout.show(container, "5");
	}
	
	public void takeTurn() {
		//System.out.println(client.isConnected());
		GameBoardPanel gameBoardPanel = (GameBoardPanel)container.getComponent(5);
		System.out.println("GBC is taking turn."); // Debug
	    gameBoardPanel.takeTurn();
	    //CardLayout cardLayout = (CardLayout)container.getLayout();
	    //cardLayout.show(container, "5");
	}
	
	// Handle button clicks.
	public void actionPerformed(ActionEvent ae) {
		// Get the name of the button clicked.
	    String command = ae.getActionCommand();
	    
	    // The exit button sends updated game data to server and takes the user to the lobby panel.
	    if (command.equals("exit"))
	    {
	    	CardLayout cardLayout = (CardLayout)container.getLayout();
	        cardLayout.show(container, "4");
	    }
	    else if (command.equals("1"))
	    {
	    	game_data.makeMove(0);
	    	try {
	    		client.openConnection();
				client.sendToServer(game_data);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("GBC failed to send move to server."); // Debug
				e.printStackTrace();
			}
	    }
	    else if (command.equals("2"))
	    {
	    	game_data.makeMove(1);
	    	try {
				client.sendToServer(game_data);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	    else if (command.equals("3"))
	    {
	    	game_data.makeMove(2);
	    	try {
				client.sendToServer(game_data);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	    else if (command.equals("4"))
	    {
	    	game_data.makeMove(3);
	    	try {
				client.sendToServer(game_data);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	    else if (command.equals("5"))
	    {
	    	game_data.makeMove(4);
	    	try {
				client.sendToServer(game_data);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	    else if (command.equals("6"))
	    {
	    	game_data.makeMove(5);
	    	try {
				client.sendToServer(game_data);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	}

	public void setPlayer1() {
		this.player = 1;
		System.out.println("GBC set Player 1"); // Debug
		waitTurn();
	}

	public void setPlayer2() {
		this.player = 2;
		System.out.println("GBC set Player 2"); // Debug
		waitTurn();
	}
	
	public int getPlayer() {
		return player;
	}
	
	public void setData(GameData data) {
		game_data = data;
	}
}
