package pkg;

import java.awt.GridLayout;
import javax.swing.*;

@SuppressWarnings("serial")
public class GameBoardPanel extends JPanel {
	
	public GameBoardPanel(GameBoardControl gbc) {
		// New information label
		JLabel label = new JLabel("Player", JLabel.CENTER);
		
		// Create buttons for the 6 pits on the player side
		JButton first = new JButton("1"); // 1
		first.addActionListener(gbc);
		JButton second = new JButton("2"); // 2
		second.addActionListener(gbc);
		JButton third = new JButton("3"); // 3
		third.addActionListener(gbc);
		JButton fourth = new JButton("4"); // 4
		fourth.addActionListener(gbc);
		JButton fifth = new JButton("5"); // 5
		fifth.addActionListener(gbc);
		JButton sixth = new JButton("6"); // 6
		sixth.addActionListener(gbc);
		// Add them to a buffer
	    JPanel buttonBuffer = new JPanel();
	    buttonBuffer.add(first);
	    buttonBuffer.add(second);
	    buttonBuffer.add(third);
	    buttonBuffer.add(fourth);
	    buttonBuffer.add(fifth);
	    buttonBuffer.add(sixth);

	    // Create exit button
	    JButton exit = new JButton("exit");
	    exit.addActionListener(gbc);
	    JPanel exitBuffer = new JPanel();
	    exitBuffer.add(exit);
		
	    // Arrange the components in a grid.
	    JPanel grid = new JPanel(new GridLayout(3, 1, 5, 5));
	    grid.add(label);
	    grid.add(buttonBuffer);
	    grid.add(exitBuffer);
	    this.add(grid);
	}
}
