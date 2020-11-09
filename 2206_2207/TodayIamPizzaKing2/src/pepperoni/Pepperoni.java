package pepperoni;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Pepperoni extends JLabel implements Runnable {
	private int x, y;
	private int d;
	private int drop;
	private int floor;
	
	private ImageIcon pepperoni =  new ImageIcon("images/pepperoni/pepperoni.png");
	
	public Pepperoni(int x, int d, int floor) {
		setLayout(null);
		
		this.x = x;
		this.d = d;
		this.floor = floor;
		y = 50;
		
		setIcon(pepperoni);
		setBounds(x, y, pepperoni.getIconWidth(), pepperoni.getIconHeight());
		
		setVisible(true);
	}
	
	@Override
	public void run() {
		while(true) {
			if(drop != 1) {
				if(d == 1) {
					x += 5;
				} else if(d == 0) {
					x -= 5;
				} 
			} else {
				y += 15;
				if(y > floor) 
					break;
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
	
	public int[] drop() {
		drop = 1;
		floor -= 30;
		int[] arr = {x, d, floor};
		return arr;
	}

}
