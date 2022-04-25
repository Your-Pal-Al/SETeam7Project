package pkg;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.BevelBorder;

@SuppressWarnings("serial")
public class StatsPanel extends JPanel
{
  private JLabel winsLabel;
  private JLabel lossesLabel;
  
  // Constructor for the contacts panel.
  public StatsPanel(StatsControl sc)
  {
    // Create the contacts label in the north.
    JLabel label = new JLabel("Stats", JLabel.CENTER);    
    
    // Win labels
    winsLabel = new JLabel("1");
    JPanel winLabelsBuffer = new JPanel();
    winLabelsBuffer.add(new JLabel("Wins: ", JLabel.RIGHT));
    winLabelsBuffer.add(winsLabel);
    winLabelsBuffer.setOpaque(false);
    
    // Loss labels
    lossesLabel = new JLabel("2");
    JPanel loseLabelsBuffer = new JPanel();
    loseLabelsBuffer.add(new JLabel("Losses: ", JLabel.RIGHT));
    loseLabelsBuffer.add(lossesLabel);
    loseLabelsBuffer.setOpaque(false);
	
	// Create exit button
    JButton exit = new JButton("exit");
    exit.addActionListener(sc);
    JPanel exitBuffer = new JPanel();
    exitBuffer.add(exit);
    exitBuffer.setOpaque(false);
    
    JPanel grid = new JPanel(new GridLayout(4, 1, 5, 5));
    grid.add(label);
    grid.add(winLabelsBuffer);
    grid.add(loseLabelsBuffer);
    grid.add(exitBuffer);
    grid.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
	grid.setBackground(new Color(210,180,140));
	grid.setPreferredSize(new Dimension(800,200));
    this.add(grid);
    this.setOpaque(false);
  }
  
  public void setWins(String wins) {
	  winsLabel.setText(wins);
  }
  
  public void setLosses(String losses) {
	  lossesLabel.setText(losses);
  }
}
