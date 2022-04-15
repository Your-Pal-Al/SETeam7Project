//Alex Salas
//Software Engineering
//Lab 7 in

//Packages
package pkg;

import java.io.FileInputStream;
import java.io.IOException;
//Imports
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

public class Database {
	private Connection conn;

	// Add any other data fields you like – at least a Connection object is
	// mandatory
	public Database() throws IOException {

		String url = "";
		String user = "";
		String password = "";
		FileInputStream fis;
		Properties property = new Properties();

		// Create a FileInput stream
		fis = new FileInputStream("pkg/db.properties");

		// init the Properties object
		property.load(fis);

		// get the url/url/pass
		url = property.getProperty("url");
		user = property.getProperty("user");
		password = property.getProperty("password");

		// Create the conn
		try {
			conn = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<String> query(String query) throws SQLException {
		//Create a statement from the conn object
		Statement stmt = conn.createStatement(); 
		
		//Run the query using the executeQuery return a ResultSet
		ResultSet rs = stmt.executeQuery(query);  
		
		//Need to retreieve each row from ResultSet using getString
		//method to retrieve each field
		//Create a String for each row so each field is comma delimited
		//Store each String into an arrayList
		ArrayList<String> retList = new ArrayList<String>();
	    while(rs.next()){
	        //retList.add(new String(rs.getString(1)+","+rs.getString(2)+","+rs.getString(3)));
	    	retList.add(rs.getString(1)+","+rs.getString(2));
	      }

		//Return the arrayList
		return retList; 
	}

	public void executeDML(String dml) throws SQLException {
		// Create a statement from the connection
		Statement stmt = conn.createStatement();

		// invoke the execute method on the statement
		stmt.execute(dml);
	}
	
	//verify is account exists in database
	public boolean verifyAccount(String username, String password){
		
		//create string for the query
		String query_str =   "SELECT username, password "
						   + "FROM users "
						   + "WHERE username = '" 
						   + username + "' AND "
						   + "AES_DECRYPT(password, 'key') = '" 
						   + password + "'";
		
		//execute query and store results in list
		ArrayList<String> retList = null;
		try {
			retList = query(query_str);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//return 'true' if retList IS NOT empty, 'false' if retList IS empty
		return !retList.isEmpty();
	}
	
	public boolean createNewAccount(String username, String password){
		
		//create string for the query
		String query_str =   "SELECT * "
						   + "FROM users "
						   + "WHERE username = '" 
						   + username
						   + "'";
		
		//execute query and store results in list
		ArrayList<String> retList = null;
		try {
			retList = query(query_str);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//if username exists in database, don't create account & return 'false'
		if(!retList.isEmpty()) {
			return false;
		}
		
		//string to create account in database
		query_str =  "INSERT INTO users "
				   + "VALUES('" 
				   + username 
				   + "', AES_ENCRYPT('"
				   + password
				   + "', 'key'))";
		
		//create account with password in database
		try {
			executeDML(query_str);
			
			//return 'true' to server
			return true;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}		
	}
}
