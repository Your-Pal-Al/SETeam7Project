package pkg;

import java.awt.*;
import javax.swing.*;
import java.io.IOException;
import ocsf.server.AbstractServer;
import ocsf.server.ConnectionToClient;

// Mancala Server object
public class MancalaServer extends AbstractServer {
	
	// Data fields for this chat server.
	private JTextArea log;
	private JLabel status;
	private boolean running = false;
	private Database database;
	private GameData game_data;
	private ConnectionToClient[] clients;
	private int queue;

	// Constructor for initializing the server with default settings.
	public MancalaServer() {
		super(12345);
		this.setTimeout(500);
		game_data = new GameData();
		clients = new ConnectionToClient[2];
		queue = 0;
	}

	// Getter that returns whether the server is currently running.
	public boolean isRunning() {
		return running;
	}

	// Setter for database
	public void setDatabase(Database db) {
		this.database = db;
	}

	// Setters for server GUI log
	public void setLog(JTextArea log) {
		this.log = log;
	}

	// setter for server GUI status
	public void setStatus(JLabel status) {
		this.status = status;
	}

	// When the server starts, update the GUI.
	@Override
	public void serverStarted() {
		running = true;
		status.setText("Listening");
		status.setForeground(Color.GREEN);
		log.append("Server started\n");
	}

	// When the server stops listening, update the GUI.
	@Override
	public void serverStopped() {
		status.setText("Stopped");
		status.setForeground(Color.RED);
		log.append("Server stopped accepting new clients - press Listen to start accepting new clients\n");
	}

	// When the server closes completely, update the GUI.
	@Override
	public void serverClosed() {
		running = false;
		status.setText("Close");
		status.setForeground(Color.RED);
		log.append("Server and all current clients are closed - press Listen to restart\n");
	}

	// When a client connects or disconnects, display a message in the log.
	@Override
	public void clientConnected(ConnectionToClient client) {
		log.append("Client " + client.getId() + " connected\n");
		System.out.println(client);
		System.out.println(getNumberOfClients() + " clients connected");
		int count = getNumberOfClients();
		
		if (count == 1) {
			clients[0] = client;
			System.out.println(clients[0]);
		} else if (count == 2){
			clients[1] = client;
			System.out.println(clients[1]);
		} else {
			System.out.println("More than 2 clients connected.");
		}
	}

	//method when a client is disconnected
	@Override
	public void clientDisconnected(ConnectionToClient client) {
		System.out.println("Client disconnected"); //TODO: Delete -  Debug
	}

	// When a message is received from a client, handle it.
	public void handleMessageFromClient(Object arg0, ConnectionToClient arg1) {
		
		// If we received LoginData, verify the account information.
		if (arg0 instanceof LoginData) {
			// Check the username and password with the database.
			LoginData data = (LoginData) arg0;
			Object result;
			if (database.verifyAccount(data.getUsername(), data.getPassword())) {
				result = "LoginSuccessful";
				log.append("Client " + arg1.getId() + " successfully logged in as " + data.getUsername() + "\n");
			} 
			else {
				result = new Error("The username and password are incorrect.", "Login");
				log.append("Client " + arg1.getId() + " failed to log in\n");
			}

			// Send the result to the client.
			try {
				arg1.sendToClient(result);
			} catch (IOException e) {
				return;
			}
		}

		// If we received CreateAccountData, create a new account.
		else if (arg0 instanceof CreateAccountData) {

			// Try to create the account.
			CreateAccountData data = (CreateAccountData) arg0;
			Object result;
			if (database.createNewAccount(data.getUsername(), data.getPassword())) {
				result = "CreateAccountSuccessful";
				log.append("Client " + arg1.getId() + " created a new account called " + data.getUsername() + "\n");
			} 
			else {
				result = new Error("The username is already in use.", "CreateAccount");
				log.append("Client " + arg1.getId() + " failed to create a new account\n");
			}

			// Send the result to the client.
			try {
				arg1.sendToClient(result);
			} catch (IOException e) {
				return;
			}
		}
		
		// if message from client GameData type
		else if (arg0 instanceof GameData) {
			System.out.println("Server recieved instance of game data. DO NOT SEND GAMEDATA	"); //TODO: Delete - Debug
		} 
		
		// String commands from client
		else if (arg0 instanceof String) {
			String data = (String)arg0;
			
			System.out.println("Debug String recieved: " + data); // Debug
			String player = data.substring(0, 2);
			
			// Checks command for queue
			if (data.equals("Queue")) {
				queue = queue + 1;
				String player_info = "Player" + queue;
				try {
					arg1.sendToClient(player_info);
					System.out.println("sent player information"); //TODO: Delete - Debug
				} catch (IOException e) {
					System.out.println("Player information was not sent to client"); //TODO: Delete - Debug
					e.printStackTrace();
				}
				 
				if (queue > 1) {
					startGame();
				}
			} 
			// Checks command string for exit
			if (data.equals("exit")) {
				queue = queue - 1;
				sendToAllClients("dequeue");
			}
			// Checks command for moves
			else if (player.equals("P1")) { // Check for player 1
				System.out.println("Server detected Player 1 and " + data.substring(2,6)); 			//TODO: Delete - Debug
				if (data.substring(2,6).equals("move")) {
					System.out.println("Server detected a move: " + data.substring(6)); 		//TODO: Delete - Debug
					game_data.makeMove(Integer.parseInt(data.substring(6)));
					sendToAllClients("update" + game_data.getPits());		// Send update to clients
					
					if (game_data.getState().equals("sameTurn")) {
						sendToAllClients("P1Turn");								
					}
					else if (game_data.getState().equals("nextTurn")) {
						sendToAllClients("P2Turn");
					}
				}
			}
			else if (player.equals("P2")) { // Check for player 2
				if (data.substring(2,6).equals("move")) { // Move command
					game_data.makeMove(Integer.parseInt(data.substring(6)));
					sendToAllClients("update" + game_data.getPits());

					if (game_data.getState().equals("sameTurn")) {	
						sendToAllClients("P2Turn");
					} else if (game_data.getState().equals("nextTurn")) {
						sendToAllClients("P1Turn");
					}
				}
			}
			else {
				System.out.println("Server did not expect string: " + arg0); //TODO: Delete - Debug
			}
		} 
		else {
			System.out.println("Did not expect object passed to server"); //TODO: Delete -Debug
		}

	}

	//Start turn method
	public void startGame() {
		log.append("2 players queued, starting game\n");
		game_data.newBoard();
		sendToAllClients("P1Turn");
	}

	// Method that handles listening exceptions by displaying exception information.
	@Override
	public void listeningException(Throwable exception) {
		running = false;
		status.setText("Exception occurred while listening");
		status.setForeground(Color.RED);
		log.append("Listening exception: " + exception.getMessage() + "\n");
		log.append("Press Listen to restart server\n");
	}
}
