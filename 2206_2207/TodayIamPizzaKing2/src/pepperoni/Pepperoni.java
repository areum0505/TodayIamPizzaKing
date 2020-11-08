package pepperoni;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Pepperoni extends JLabel implements Runnable {
	private int x, y;
	private int d = 1;
	
	private ImageIcon pepperoni =  new ImageIcon("images/pepperoni/pepperoni.png");
	
	public Pepperoni() {
		setLayout(null);
		
		x = (1280/2)-(pepperoni.getIconWidth()/2);
		y = 50;
		
		setIcon(pepperoni);
		setBounds(x, y, pepperoni.getIconWidth(), pepperoni.getIconHeight());
		
		setVisible(true);
	}
	
	@Override
	public void run() {
		while(true) {	
			if(d == 1) {
				x += 5;
			} else if(d == 0) {
				x -= 5;
			} else if(d == 2) {
				y += 15;
				if(y > 600)
					d = 3;
			}
			
			if(x < 380) {
				d = 1;
			} else if(x > 780) {
				d = 0;
			}
			
			setLocation(x, y);
			getParent().repaint();
			
			try {
				Thread.sleep(25);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void drop() {
		d = 2;
	}

}
