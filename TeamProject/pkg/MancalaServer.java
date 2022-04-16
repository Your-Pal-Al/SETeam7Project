package pkg;

import java.awt.*;
import javax.swing.*;
import java.io.IOException;
import ocsf.server.AbstractServer;
import ocsf.server.ConnectionToClient;

public class MancalaServer extends AbstractServer
{
  //Data fields for this chat server.
  private JTextArea log;
  private JLabel status;
  private boolean running = false;
  private Database database;
  private GameData game_data;
  private ConnectionToClient[] clients;
  private int queue;

  // Constructor for initializing the server with default settings.
  public MancalaServer()
  {
    super(12345);
    this.setTimeout(500);
    game_data = new GameData();
    clients = new ConnectionToClient[2];
    queue = 0;
  }

  // Getter that returns whether the server is currently running.
  public boolean isRunning()
  {
    return running;
  }
  
  public void setDatabase(Database db) 
  {
	 this.database = db; 
  }
  
  // Setters for the data fields corresponding to the GUI elements.
  public void setLog(JTextArea log)
  {
    this.log = log;
  }
  public void setStatus(JLabel status)
  {
    this.status = status;
  }

  // When the server starts, update the GUI.
  @Override
  public void serverStarted()
  {
    running = true;
    status.setText("Listening");
    status.setForeground(Color.GREEN);
    log.append("Server started\n");
  }
  
  // When the server stops listening, update the GUI.
   @Override
   public void serverStopped()
   {
     status.setText("Stopped");
     status.setForeground(Color.RED);
     log.append("Server stopped accepting new clients - press Listen to start accepting new clients\n");
   }
 
  // When the server closes completely, update the GUI.
  @Override
  public void serverClosed()
  {
    running = false;
    status.setText("Close");
    status.setForeground(Color.RED);
    log.append("Server and all current clients are closed - press Listen to restart\n");
  }

  // When a client connects or disconnects, display a message in the log.
  @Override
  public void clientConnected(ConnectionToClient client)
  {
    log.append("Client " + client.getId() + " connected\n");
    if (clients.length == 0) {
    	clients[0] = client;
    } else {
    	clients[1] = client;
    }
  }
  
  @Override
  public void clientDisconnected(ConnectionToClient client)
  {
	  System.out.println("Client disconnected");
  }
  
  public void startGame() {
	  game_data.setState("takeTurn");
	  try {
		clients[0].sendToClient(game_data);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
  }

  // When a message is received from a client, handle it.
  public void handleMessageFromClient(Object arg0, ConnectionToClient arg1)
  {
    // If we received LoginData, verify the account information.
    if (arg0 instanceof LoginData)
    {
      // Check the username and password with the database.
      LoginData data = (LoginData)arg0;
      Object result;
      if (database.verifyAccount(data.getUsername(), data.getPassword()))
      {
        result = "LoginSuccessful";
        log.append("Client " + arg1.getId() + " successfully logged in as " + data.getUsername() + "\n");
      }
      else
      {
        result = new Error("The username and password are incorrect.", "Login");
        log.append("Client " + arg1.getId() + " failed to log in\n");
      }
      
      // Send the result to the client.
      try
      {
        arg1.sendToClient(result);
      }
      catch (IOException e)
      {
        return;
      }
    }
    
    // If we received CreateAccountData, create a new account.
    else if (arg0 instanceof CreateAccountData)
    {
    	
      // Try to create the account.
      CreateAccountData data = (CreateAccountData)arg0;
      Object result;
      if (database.createNewAccount(data.getUsername(), data.getPassword()))
      {
        result = "CreateAccountSuccessful";
        log.append("Client " + arg1.getId() + " created a new account called " + data.getUsername() + "\n");
      }
      else
      {
        result = new Error("The username is already in use.", "CreateAccount");
        log.append("Client " + arg1.getId() + " failed to create a new account\n");
      }
      
      
      // Send the result to the client.
      try
      {
        arg1.sendToClient(result);
      }
      catch (IOException e)
      {
        return;
      }
    }
    
    
    else if (arg0 instanceof GameData) {
    	GameData data = (GameData)arg0;
    	// Set server game data to client game data, reversing it for player 2
    	if (arg1 == clients[0]) { // If GameData from Player 1
    		game_data = data;
    	} else if (arg1 == clients[1]) { // If GameData from Player 2
    		data.invert();
    		game_data = data;
    	}
    	
    	System.out.println("Got game data. Status: " + game_data.getState()); // Debug
    	
    	// Check for win
    	if (game_data.checkWin()) {
    		// Win
    	}
    	
    	if (game_data.getTask().equals("sameTurn")) {
    		try {
				arg1.sendToClient("Take Turn");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    	else if (game_data.getTask().equals("nextTurn")) {
    		
    		game_data.setState("waitTurn");
			try {
				arg1.sendToClient(game_data); 	// Send false turn state back
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			game_data.setState("takeTurn");		// Set turn state to true and send to other player
    		if (arg1 == clients[0]) { 	// If player 1 sent
    			try {
    				game_data.invert();  		// Make sure to invert data before sending to player 2!!!
					clients[1].sendToClient(game_data);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    		} else if (arg1 == clients[1]) { // If player 2 sent
    			try {
					clients[0].sendToClient(game_data);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    		}
    	}
    }  
    else if (arg0 instanceof String) {
    	System.out.println(arg0); // Debug
    	if (arg0.equals("Queue")) {
    		queue = queue + 1;
    		if (queue > 1) {
    			// send data to second client
    			game_data.setState("takeTurn");
    			try {
					clients[1].sendToClient(game_data);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    			// send data to first client
    			game_data.setState("waitTurn");
    			try {
					clients[0].sendToClient(game_data);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					System.out.println("First client was not sent game_data for inital turn");
					e.printStackTrace();
				}
				System.out.println("Start data sent to clients."); // Debug
    		}
    	}
    }
    else {
    	System.out.println("Did not expect object passed to server");
    }
    
  }

  // Method that handles listening exceptions by displaying exception information.
  @Override
  public void listeningException(Throwable exception) 
  {
    running = false;
    status.setText("Exception occurred while listening");
    status.setForeground(Color.RED);
    log.append("Listening exception: " + exception.getMessage() + "\n");
    log.append("Press Listen to restart server\n");
  }
}
