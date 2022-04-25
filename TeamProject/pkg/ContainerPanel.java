package pkg;

import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
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
		
		addComponentListener(new ComponentAdapter() {

            @Override
            public void componentResized(ComponentEvent e) {
                Dimension size2 = getSize();
                backgroundImage = backgroundImage.getScaledInstance(size2.width, size2.height, java.awt.Image.SCALE_SMOOTH);
            }

        });
	}
	
	@Override
	public void paintComponent(Graphics g) {
	    super.paintComponent(g);

	    // Draw the background image.
	    g.drawImage(backgroundImage, 0, 0, this);
	}
}