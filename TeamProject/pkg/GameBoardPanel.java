package pkg;

import java.awt.GridLayout;
import javax.swing.*;

@SuppressWarnings("serial")
public class GameBoardPanel extends JPanel {
	
	private JLabel label;
	private JButton first, second, third, fourth, fifth, sixth;
	
	public GameBoardPanel(GameBoardControl gbc) {
		// New information label
		label = new JLabel("Waiting for other players", JLabel.CENTER);
		
		// Create buttons for the 6 pits on the player side
		first = new JButton("1"); // 1
		first.addActionListener(gbc);
		second = new JButton("2"); // 2
		second.addActionListener(gbc);
		third = new JButton("3"); // 3
		third.addActionListener(gbc);
		fourth = new JButton("4"); // 4
		fourth.addActionListener(gbc);
		fifth = new JButton("5"); // 5
		fifth.addActionListener(gbc);
		sixth = new JButton("6"); // 6
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
	
	public void waitTurn() {
		first.setEnabled(false);
		second.setEnabled(false);
		third.setEnabled(false);
		fourth.setEnabled(false);
		fifth.setEnabled(false);
		sixth.setEnabled(false);
		label.setText("Waiting for other players...");
	}
	public void takeTurn() {
		first.setEnabled(true);
		second.setEnabled(true);
		third.setEnabled(true);
		fourth.setEnabled(true);
		fifth.setEnabled(true);
		sixth.setEnabled(true);
		label.setText("Your move!");
		System.out.println("GBP is taking turn.");
	}
}
