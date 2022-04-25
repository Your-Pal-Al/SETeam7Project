package pkg;

import ocsf.client.AbstractClient;

public class MancalaClient extends AbstractClient {
	
	// Private data fields for storing the GUI controllers.
	private ConnectionControl connectionControl;
	private LoginControl loginControl;
	private CreateAccountControl createAccountControl;
	private GameBoardControl gameBoardControl;
	private StatsControl statsControl;
	private String username;

	// Setter for connection controller
	public void setConnectionControl(ConnectionControl connectionControl) {
		this.connectionControl = connectionControl;
	}
	
	// Setters for login controller 
	public void setLoginControl(LoginControl loginControl) {
		this.loginControl = loginControl;
	}

	// Setter for CreateAccount controller
	public void setCreateAccountControl(CreateAccountControl createAccountControl) {
		this.createAccountControl = createAccountControl;
	}

	// Setter for GameBoard controller
	public void setGameBoardControl(GameBoardControl gameBoardControl) {
		this.gameBoardControl = gameBoardControl;
	}
	
	// Setter for Stats controller
	public void setStatsControl(StatsControl statsControl) {
		this.statsControl = statsControl;
	}

	// Constructor for initializing the client with default settings.
	public MancalaClient() {
		super("localhost", 8300);
	}

	// Method that handles messages from the server.
	public void handleMessageFromServer(Object arg0) {
		// If we received a String, figure out what this event is.
		if (arg0 instanceof String) {
			// Get the text of the message.
			String message = (String) arg0;
			System.out.println("Client recieved string: " + message); //TODO: Delete - Debug

			// If we successfully logged in, tell the login controller.
			if (message.equals("ConnectionSuccessful")) {
				connectionControl.connectionAchieved();
			}
			
			else if (message.equals("LoginSuccessful")) {
				setUsername(loginControl.getUser());
				loginControl.loginSuccess();
			}

			// If we successfully created an account, tell the create account controller.
			else if (message.equals("CreateAccountSuccessful")) {
				setUsername(createAccountControl.getUser());
				createAccountControl.createAccountSuccess();
			}

			// Set player message
			else if (message.substring(0, 6).equals("Player")) {
				if (message.substring(message.length() - 1).equals("1")) {
					gameBoardControl.setPlayer1();
				} else if (message.substring(message.length() - 1).equals("2")) {
					gameBoardControl.setPlayer2();
				}
				gameBoardControl.newBoard();
			}
			
			// Handle dequeue message
			else if (message.equals("dequeue")) {
				if (gameBoardControl.getPlayer() > 0) {
					gameBoardControl.exit();
				}
			}
			
			else if (message.substring(0,5).equals("Stats")) {
				String[] temp = message.split(",");
				String wins = temp[0].substring(5);
				String losses = temp[1];
				statsControl.setStats(wins, losses);
			}
			
			// Make a move message
			else if (message.substring(0,4).equals("move")) {
				String pit = message.substring(5);
				gameBoardControl.makeMove(Integer.parseInt(pit));
			}
			
			// Update gameboard message
			else if (message.substring(0,6).equals("update")) {
				String[] items = message.substring(6).split(",");
				int[] pits = new int[items.length];
				for (int i = 0; i < items.length; i++) {
					pits[i] = Integer.parseInt(items[i]);
				} 
				gameBoardControl.setPits(pits);
			}
			
			// Win message
			else if (message.equals("P1winner")) {
				System.out.println("Client detected P1win command"); //TODO: Delete - debug
				if (gameBoardControl.getPlayer() == 1) {
					System.out.println("Client detected a win command"); //TODO: Delete - debug
					gameBoardControl.win();
				}
				else {
					System.out.println("Client detected a lose command"); //TODO: Delete - debug
					gameBoardControl.lose();
				}
				
			}
			
			else if (message.equals("P2winner")) {
				System.out.println("Client detected P2win"); //TODO: Delete - debug
				if (gameBoardControl.getPlayer() == 2) {
					System.out.println("Client detected a win command"); //TODO: Delete - debug
					gameBoardControl.win();
				}
				else {
					System.out.println("Client detected a lose command"); //TODO: Delete - debug
					gameBoardControl.lose();
				}
			}
			
			// Take a turn message
			else if (message.equals("P1Turn") && gameBoardControl.getPlayer() == 1) {
				gameBoardControl.takeTurn();
			}
			
			else if (message.equals("P2Turn") && gameBoardControl.getPlayer() == 2) {
				gameBoardControl.takeTurn();
			} 
			
			else {
				System.out.println("Client 'elsed' waiting turn. Player: " + gameBoardControl.getPlayer()); //TODO: Debug - Delete?
				gameBoardControl.waitTurn();
			}
		}

		// If we received a GameBoard, update the gameboard
		else if (arg0 instanceof GameData) {
			System.out.println("Error: Server should not be sending GameData"); //TODO: Delete - Debug
		}

		// If we received an Error, figure out where to display it.
		else if (arg0 instanceof Error) {
			// Get the Error object.
			Error error = (Error) arg0;

			// Display login errors using the login controller.
			if (error.getType().equals("Login")) {
				loginControl.displayError(error.getMessage());
			}

			// Display account creation errors using the create account controller.
			else if (error.getType().equals("CreateAccount")) {
				createAccountControl.displayError(error.getMessage());
			}
		}
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}
