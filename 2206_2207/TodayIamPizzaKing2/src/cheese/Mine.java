package cheese;

import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import code.Game;

public class Mine extends JLabel {
    private ImageIcon mine =  new ImageIcon("images/cheese/mine.png");
    
    private int x, y;
	
	public Mine() {
		setIcon(mine);
		
		Random random = new Random();
		x = random.nextInt(1280);
		if(250 > x)
			x += 143;
		else if(1000 < x)
			x -= 300;
		y = random.nextInt(720);
		if(y < 10)
			y += 20;
		else if(y > 600)
			y -= 120;
        setBounds(x, y, mine.getIconWidth(), mine.getIconHeight());
        
        setVisible(false);
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
