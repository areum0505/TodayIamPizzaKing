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
		x = random.nextInt(1155);
		if(143 < x)
			x += 143;
		y = random.nextInt(565);
		if(y < 10)
			y += 10;
        setBounds(x, y, mine.getIconWidth(), mine.getIconHeight());
        
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
