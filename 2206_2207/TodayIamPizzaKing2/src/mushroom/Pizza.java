package mushroom;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

class Pizza extends JLabel implements Runnable {
	ImageIcon basket = new ImageIcon("images/mushroom/mushroomBasket.png");
	
	public boolean left, right;	
	int x= 640 ,y = 500;
	
	
	int width = basket.getIconWidth(), height = basket.getIconHeight();
	
	private boolean stop = false;
	
	public Pizza() {
		setIcon(basket);
		setBounds(getX(), getY(),150,170);
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
	
	public int getWidth() {
		return width;
	}
	public int getHeight() {
		return height;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(!stop) { 
			if(left) {
				left();
			}else if(right) {
				right();
			}
			setLocation(x,y);
			getParent().repaint();
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
	public void left() {
		if(0<getX()) {
			x -=20;
		}
	}
	public void right() {
		if(getX()<1120){
			x +=20;
		}	
	}
	public void setStop(boolean stop) {
		this.stop = stop;
	}
	
}	
