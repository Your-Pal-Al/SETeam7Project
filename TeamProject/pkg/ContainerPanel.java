package pkg;

import java.awt.*;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.*;

//gui for initial panel to login/create account
@SuppressWarnings("serial")
public class ContainerPanel extends JPanel {
	private Image backgroundImage;
	
	public ContainerPanel(LayoutManager layout) {
		super(layout);
		// Create background image
		Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
		try {
			Image temp = ImageIO.read(new File("background.png"));
			backgroundImage = temp.getScaledInstance((int)size.getWidth(),(int)size.getHeight(),Image.SCALE_SMOOTH);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void paintComponent(Graphics g) {
	    super.paintComponent(g);

	    // Draw the background image.
	    g.drawImage(backgroundImage, 0, 0, this);
	}
}