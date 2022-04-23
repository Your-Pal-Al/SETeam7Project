//Database Unit Test
package pkg;

import static org.junit.Assert.*;
import java.util.*;
import java.sql.*;

import org.junit.Before;
import org.junit.Test;


public class DatabaseTest {

	private Database db;
	private ArrayList<String> a;
	private int random;
	private String[] users = {"alex","santos","jacob"};
	private String[] passwords = {"abcde","abcde","abcde"};

	@Before
	public void setUp() throws Exception {

		//sets private data fields
		db = new Database();
		random = ((int)Math.random()*users.length);
		a = new ArrayList<String>();
	}

	@Test 
	public void testQuery() {

		//random username/password pair
		String username = users[random];
		String expected = passwords[random];
		String s = "";
		
		//creates query
		String query = "SELECT password, aes_decrypt(password,'key') from users WHERE username = '" + username + "'";

		//returns query results
		try {
			a = db.query(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//splits query results by "," delimeter
		for (String string : a) {

			s = string;
		}
		
		String[] tokens = s.split(",");

		String actual = tokens[1];
		
		//fails if actual and expected passwords are not equal
		assertEquals(actual, expected);

	}

	@Test //fails if exception is thrown
	public void testExecuteDML() throws SQLException{

		//new username/password pair
		String username = "newuser";
		String password = "password123";
		
		//creates dml statement
		String dml = "insert into users values ('" + username + "', aes_encrypt('" + password + "','key'))";

		//executes dml statement
		db.executeDML(dml);

	}

	@Test //fails if account does not exist in database
	public void testVerify() {
		
		String username = users[random];
		String password = passwords[random];
		
		boolean b = db.verifyAccount(username, password);
		
		assertTrue(b);
		
	}
	
	@Test //fails if account isn't be added to database
	public void testCreate() {
		
		String username = "testuser";
		String password = "hello";
		
		boolean b = db.createNewAccount(username, password);
		
		assertTrue(b);
		
	}
	
	
	
}
