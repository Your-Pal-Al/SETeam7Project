package pkg;

import ocsf.client.AbstractClient;

public class MancalaClient extends AbstractClient {
	
	// Private data fields for storing the GUI controllers.
	private LoginControl loginControl;
	private CreateAccountControl createAccountControl;
	private GameBoardControl gameBoardControl;

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
			if (message.equals("LoginSuccessful")) {
				loginControl.loginSuccess();
			}

			// If we successfully created an account, tell the create account controller.
			else if (message.equals("CreateAccountSuccessful")) {
				createAccountControl.createAccountSuccess();
			}

			else if (message.substring(0, 6).equals("Player")) {
				System.out.println("Client found Player substring in message"); //TODO: Delete - Debug
				if (message.substring(message.length() - 1).equals("1")) {
					gameBoardControl.setPlayer1();
				} else if (message.substring(message.length() - 1).equals("2")) {
					gameBoardControl.setPlayer2();
				}
			}
		}

		// If we received a GameBoard, update the gameboard
		else if (arg0 instanceof GameData) {
			GameData game_data = (GameData) arg0;
			if (gameBoardControl.getPlayer() == 1) {
				gameBoardControl.setData(game_data);
			} else {
				game_data.invert();
				gameBoardControl.setData(game_data);
			}
			System.out.println(game_data.getState()); //TODO: Delete - Debug

			if (game_data.getState().equals("p1Turn") && gameBoardControl.getPlayer() == 1) {
				gameBoardControl.setData(game_data);
				gameBoardControl.takeTurn();
			} else if (game_data.getState().equals("p2Turn") && gameBoardControl.getPlayer() == 2) {
				gameBoardControl.setData(game_data);
				gameBoardControl.takeTurn();
			} else {
				gameBoardControl.waitTurn();
			}
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
}
