package pkg;

import static org.junit.Assert.*;

import java.io.IOException;

import javax.swing.*;

import org.junit.*;

public class GameBoardControlTest {

	private GameData game_data;
	private GameBoardControl gbc;
	private GameBoardPanel gbp;
	private ClientGUI cgui1;
	
	
	@Before
	public void setUp() {

		cgui1 = new ClientGUI();
		gbc = cgui1.getGameBoardControl();
		gbp = (GameBoardPanel)cgui1.getGameBoardPanel();
		game_data = gbc.getGameData();


	}

	@Test
	public void testWinPlayerOne()
	{

		String expected = "P1win";

		cgui1.setView("5");
		
		game_data.newBoard();

		JButton[] buttons1 = gbp.P1_buttons;
		JButton[] buttons2 = gbp.P2_buttons;

		for (int i = 0; i < buttons1.length; i++) {
			buttons1[i].setEnabled(true);
			buttons2[i].setEnabled(true);
			
		}
		
		
		buttons1[2].doClick(500);
		buttons1[3].doClick(500);
		buttons2[3].doClick(500);
		buttons2[2].doClick(500);
		buttons1[1].doClick(500);
		buttons1[0].doClick(500);
		buttons2[4].doClick(500);
		buttons2[5].doClick(500);
		buttons1[2].doClick(500);
		buttons2[3].doClick(500);
		buttons1[3].doClick(500);
		buttons1[1].doClick(500);
		buttons2[4].doClick(500);
		buttons1[4].doClick(500);
		buttons2[2].doClick(500);
		buttons1[5].doClick(500);
		buttons2[1].doClick(500);
		buttons2[0].doClick(500);
		buttons1[5].doClick(500);

		game_data.checkWin();
		String actual = game_data.getState();

		assertEquals(actual, expected);

	}

	@Test
	public void testWinPlayerTwo()
	{

		String expected = "P2win";

		cgui1.setView("5");

		game_data.newBoard();
		
		JButton[] buttons1 = gbp.P1_buttons;
		JButton[] buttons2 = gbp.P2_buttons;
		
		for (int i = 0; i < buttons1.length; i++) {
			buttons1[i].setEnabled(true);
			buttons2[i].setEnabled(true);
			
		}

		buttons1[0].doClick(500);
		buttons2[3].doClick(500);
		buttons2[0].doClick(500);
		buttons1[0].doClick(500);
		buttons2[4].doClick(500);
		buttons1[1].doClick(500);
		buttons2[0].doClick(500);
		buttons2[1].doClick(500);
		buttons1[3].doClick(500);
		buttons2[0].doClick(500);
		buttons2[5].doClick(500);
		buttons2[0].doClick(500);
		buttons2[1].doClick(500);
		buttons2[0].doClick(500);
		buttons2[3].doClick(500);
		buttons1[1].doClick(500);
		buttons2[4].doClick(500);
		buttons1[5].doClick(500);
		buttons2[1].doClick(500);
		buttons1[0].doClick(500);
		buttons2[0].doClick(500);
		buttons2[2].doClick(500);
		buttons1[0].doClick(500);
		buttons2[0].doClick(500);
		buttons2[3].doClick(500);
		buttons1[1].doClick(500);
		buttons2[1].doClick(500);
		buttons2[0].doClick(500);
		buttons2[4].doClick(500);
		buttons1[2].doClick(500);
		buttons2[5].doClick(500);
		buttons1[5].doClick(500);
		buttons2[2].doClick(500);
		buttons2[0].doClick(500);
		buttons2[1].doClick(500);
		buttons2[0].doClick(500);
		buttons2[3].doClick(500);
		buttons1[4].doClick(500);
		buttons2[0].doClick(500);

		game_data.checkWin();
		String actual = game_data.getState();

		assertEquals(actual, expected);

	}


}
