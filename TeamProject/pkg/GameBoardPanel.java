package pkg;

import java.awt.GridLayout;

import javax.swing.*;

//GameBoard GUI panel
@SuppressWarnings("serial")
public class GameBoardPanel extends JPanel {
	
	private JLabel label;
	//private JButton first, second, third, fourth, fifth, sixth;
	//private JLabel[] labels;
	JLabel P1_mancala, P2_mancala;
	JButton[] P1_buttons, P2_buttons;
	int player;
	
	public GameBoardPanel(GameBoardControl gbc) {
		// New information label
		label = new JLabel("Queueing", JLabel.CENTER);
		
		player = 1;
		
		// Create buttons and labels for the game board
		P1_mancala = new JLabel("0");
		P2_mancala = new JLabel("0");
		P1_buttons = new JButton[6];
		P2_buttons = new JButton[6];
		
		// instantiate the buttons and add their action listeners
		for (int i = 0; i < P1_buttons.length; i++) {
			P1_buttons[i] = new JButton("0");
			P1_buttons[i].addActionListener(gbc);
			P1_buttons[i].setActionCommand(Integer.toString(i));
			P2_buttons[i] = new JButton("0");
			P2_buttons[i].addActionListener(gbc);
			P2_buttons[i].setActionCommand(Integer.toString((12 - i)));
			P2_buttons[i].setEnabled(false);
		}
		
		// add the elements to buffers
		JPanel topBuffer = new JPanel();
		JPanel botBuffer = new JPanel();
		JPanel leftBuffer = new JPanel();
		JPanel centerBuffer = new JPanel(new GridLayout(0, 1));
		JPanel rightBuffer = new JPanel();
		JPanel gameBoardBuffer = new JPanel(new GridLayout(1, 0));
		
		for (int i = 0; i < P1_buttons.length; i++) {
			botBuffer.add(P1_buttons[i]);
			topBuffer.add(P2_buttons[i]);
		}
		leftBuffer.add(P2_mancala);
		centerBuffer.add(topBuffer);
		centerBuffer.add(botBuffer);
		rightBuffer.add(P1_mancala);
		gameBoardBuffer.add(leftBuffer);
		gameBoardBuffer.add(centerBuffer);
		gameBoardBuffer.add(rightBuffer);
		/*
		// Instantiate the labels array to 14 then fill it with "0" labels
		labels = new JLabel[14];
		for (int i = 0; i < labels.length; i++) {
			labels[i] = new JLabel("0");
		}
		// Add the labels to the buffers
		JPanel labelsTopBuffer = new JPanel();
		JPanel labelsBotBuffer = new JPanel();
		for (int i = 0; i < labels.length; i++) {
			if (i > 6) {
				labelsTopBuffer.add(labels[(20 - i)]);
			} else {
				labelsBotBuffer.add(labels[i]);
			}
		}
		
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
	    */

	    // Create exit button
	    JButton exit = new JButton("exit");
	    exit.addActionListener(gbc);
	    JPanel exitBuffer = new JPanel();
	    exitBuffer.add(exit);
		
	    // Arrange the components in a grid.
	    JPanel grid = new JPanel(new GridLayout(0, 1, 5, 5));
	    grid.add(label);
	    //grid.add(labelsTopBuffer);
	    //grid.add(labelsBotBuffer);
	    //grid.add(buttonBuffer);
	    grid.add(gameBoardBuffer);
	    grid.add(exitBuffer);
	    this.add(grid);
	}
	
	public void setPlayer2() {
		/*
		first.setActionCommand("13");
		first.setText("13");
		second.setActionCommand("12");
		second.setText("12");
		third.setActionCommand("11");
		third.setText("11");
		fourth.setActionCommand("10");
		fourth.setText("10");
		fifth.setActionCommand("9");
		fifth.setText("9");
		sixth.setActionCommand("8");
		sixth.setText("8");
		*/
		for (int i = 0; i < P1_buttons.length; i++) {
			P1_buttons[i].setEnabled(false);
			P2_buttons[i].setEnabled(true);
		}
		player = 2;
	}
	public void waitTurn() {
		if (player == 1) {
			for (int i = 0; i < P1_buttons.length; i++) {
				P1_buttons[i].setEnabled(false);
			}
		} else if (player == 2) {
			for (int i = 0; i < P2_buttons.length; i++) {
				P2_buttons[i].setEnabled(false);
			}
		} else {
			System.out.println("GBP waitTurn failed?"); //TODO: Delete - debug
		}
		/*
		first.setEnabled(false);
		second.setEnabled(false);
		third.setEnabled(false);
		fourth.setEnabled(false);
		fifth.setEnabled(false);
		sixth.setEnabled(false);
		*/
		label.setText("Waiting for other players...");
	}
	public void takeTurn() {
		if (player == 1) {
			for (int i = 0; i < P1_buttons.length; i++) {
				P1_buttons[i].setEnabled(true);
			}
		} else if (player == 2) {
			for (int i = 0; i < P2_buttons.length; i++) {
				P2_buttons[i].setEnabled(true);
			}
		} else {
			System.out.println("GBP takeTurn failed?"); //TODO: Delete - debug
		}
		/*
		first.setEnabled(true);
		second.setEnabled(true);
		third.setEnabled(true);
		fourth.setEnabled(true);
		fifth.setEnabled(true);
		sixth.setEnabled(true);
		*/
		label.setText("Your move!");
	}
	public void win() {
		System.out.println("GBP is winning"); //TODO: Delete - debug
		for (int i = 0; i < P1_buttons.length; i++) {
			P1_buttons[i].setEnabled(false);
			P2_buttons[i].setEnabled(false);
		}
		/*
		first.setEnabled(false);
		second.setEnabled(false);
		third.setEnabled(false);
		fourth.setEnabled(false);
		fifth.setEnabled(false);
		sixth.setEnabled(false);
		*/
		label.setText("You Won!!!");
	}
	public void lose() {
		System.out.println("GBP is losing"); //TODO: Delete - debug
		for (int i = 0; i < P1_buttons.length; i++) {
			P1_buttons[i].setEnabled(false);
			P2_buttons[i].setEnabled(false);
		}
		/*
		first.setEnabled(false);
		second.setEnabled(false);
		third.setEnabled(false);
		fourth.setEnabled(false);
		fifth.setEnabled(false);
		sixth.setEnabled(false);
		*/
		label.setText("You lost :(");
	}
	public void setPit(int pit_index, int pit_amount) {
		//labels[pit_index].setText(Integer.toString(pit_amount));
		
		if (pit_index < 6) {
			P1_buttons[pit_index].setText(Integer.toString(pit_amount));
		}
		else if (pit_index == 6) {
			P1_mancala.setText(Integer.toString(pit_amount));
		}
		else if (pit_index > 6 && pit_index < 13) {
			P2_buttons[5 - (pit_index - 7)].setText(Integer.toString(pit_amount));
		}
		else if (pit_index == 13) {
			P2_mancala.setText(Integer.toString(pit_amount));
		}
	}
}
