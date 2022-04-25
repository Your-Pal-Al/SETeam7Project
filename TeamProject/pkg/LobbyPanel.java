package pkg;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.BevelBorder;

@SuppressWarnings("serial")
public class LobbyPanel extends JPanel
{
  // Constructor for the contacts panel.
  public LobbyPanel(LobbyControl lc)
  {
    // Create the contacts label in the north.
    JLabel label = new JLabel("Lobby", JLabel.CENTER);    
    
    // Create the queue button.
	JButton queueButton = new JButton("Queue");
	queueButton.addActionListener(lc);
	JPanel queueButtonBuffer = new JPanel();
	queueButtonBuffer.add(queueButton);
	queueButtonBuffer.setOpaque(false);
	
	// Create view stats button
	JButton statsButton = new JButton("My Stats");
	statsButton.addActionListener(lc);
	JPanel statsButtonBuffer = new JPanel();
	statsButtonBuffer.add(statsButton);
	statsButtonBuffer.setOpaque(false);
	
	// Create the logout button.
	JButton logoutButton = new JButton("Log Out");
	logoutButton.addActionListener(lc);
	JPanel logoutButtonBuffer = new JPanel();
	logoutButtonBuffer.add(logoutButton);
	logoutButtonBuffer.setOpaque(false);
    
    JPanel grid = new JPanel(new GridLayout(4, 1, 5, 5));
    grid.add(label);
    grid.add(queueButtonBuffer);
    grid.add(statsButtonBuffer);
    grid.add(logoutButtonBuffer);
    grid.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
	grid.setBackground(new Color(210,180,140));
	grid.setPreferredSize(new Dimension(800,200));
    this.add(grid);
    this.setOpaque(false);
  }
}
