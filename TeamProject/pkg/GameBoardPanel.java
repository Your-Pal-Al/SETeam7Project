package pkg;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.*;
import javax.swing.border.BevelBorder;

//GameBoard GUI panel
@SuppressWarnings("serial")
public class GameBoardPanel extends JPanel {
	
	private JLabel label;
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
			P1_buttons[i].setEnabled(false);
			P2_buttons[i] = new JButton("0");
			P2_buttons[i].addActionListener(gbc);
			P2_buttons[i].setActionCommand(Integer.toString((12 - i)));
			P2_buttons[i].setEnabled(false);
		}
		
		// add the elements to buffers
		JPanel topBuffer = new JPanel();
		JPanel botBuffer = new JPanel();
		JPanel leftBuffer = new JPanel();
		leftBuffer.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
		JPanel centerBuffer = new JPanel(new GridLayout(0, 1));
		JPanel rightBuffer = new JPanel();
		rightBuffer.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
		JPanel gameBoardBuffer = new JPanel(new GridLayout(1, 0));
		
		for (int i = 0; i < P1_buttons.length; i++) {
			botBuffer.add(P1_buttons[i]);
			topBuffer.add(P2_buttons[i]);
		}
		topBuffer.setOpaque(false);
		botBuffer.setOpaque(false);
		leftBuffer.add(P2_mancala);
		leftBuffer.setOpaque(false);
		centerBuffer.add(topBuffer);
		centerBuffer.add(botBuffer);
		centerBuffer.setOpaque(false);
		rightBuffer.add(P1_mancala);
		rightBuffer.setOpaque(false);
		gameBoardBuffer.add(leftBuffer);
		gameBoardBuffer.add(centerBuffer);
		gameBoardBuffer.add(rightBuffer);
		gameBoardBuffer.setOpaque(false);

	    // Create exit button
	    JButton exit = new JButton("exit");
	    exit.addActionListener(gbc);
	    JPanel exitBuffer = new JPanel();
	    exitBuffer.add(exit);
	    exitBuffer.setOpaque(false);
		
	    // Arrange the components in a grid.
	    JPanel grid = new JPanel(new GridLayout(0, 1, 5, 5));
	    grid.add(label);
	    grid.add(gameBoardBuffer);
	    grid.add(exitBuffer);
	    grid.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
		grid.setBackground(new Color(210,180,140));
		grid.setPreferredSize(new Dimension(850,200));
	    this.add(grid);
	    this.setOpaque(false);
	}
	
	public void setPlayer1() {
		player = 1;
	}
	
	public void setPlayer2() {
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
		label.setText("Your move!");
	}
	
	public void win() {
		System.out.println("GBP is winning"); //TODO: Delete - debug
		for (int i = 0; i < P1_buttons.length; i++) {
			P1_buttons[i].setEnabled(false);
			P2_buttons[i].setEnabled(false);
		}
		label.setText("You Won!!!");
	}
	
	public void lose() {
		System.out.println("GBP is losing"); //TODO: Delete - debug
		for (int i = 0; i < P1_buttons.length; i++) {
			P1_buttons[i].setEnabled(false);
			P2_buttons[i].setEnabled(false);
		}
		label.setText("You lost :(");
	}
	
	public void setPit(int pit_index, int pit_amount) {
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
