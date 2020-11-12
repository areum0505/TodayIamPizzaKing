package cheese;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Exit extends JLabel {
	private ImageIcon img =  new ImageIcon("images/cheese/exit.png");

	private int x, y;
	
	public Exit() {
		setIcon(img);
		
		x = 1280 - img.getIconWidth() - 15;
		y = 5;
        setBounds(x, y, img.getIconWidth(), img.getIconHeight());
        
        setVisible(true);
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
}
