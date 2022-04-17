package pkg;

import java.io.Serializable;

//Error class, sent between server and client
@SuppressWarnings("serial")
public class Error implements Serializable {
	
	// Data field for storing the error message and type.
	private String message;
	private String type;

	// Getter for the error message
	public String getMessage() {
		return message;
	}

	// Getter for the error type
	public String getType() {
		return type;
	}

	// Settersfor the error message
	public void setMessage(String message) {
		this.message = message;
	}
	
	// Setter for the error type
	public void setType(String type) {
		this.type = type;
	}

	// Constructor for creating a new Error object with a message and type.
	public Error(String message, String type) {
		setMessage(message);
		setType(type);
	}
}
