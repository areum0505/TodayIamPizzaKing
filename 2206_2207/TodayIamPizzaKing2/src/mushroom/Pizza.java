package mushroom;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

class Pizza extends Thread {
	ImageIcon basket = new ImageIcon("images/mushroom/mushroomBasket.png");
	
	public boolean left, right;	
	int x= 560 ,y = 500;
	
	JLabel p;
	int width = basket.getIconWidth(), height = basket.getIconHeight();
	
	private boolean stop = false;
	 private boolean pause = false;
	 
	public Pizza(JLabel p) {
		this.p = p;
		p.setIcon(basket);
		p.setSize(150,170);
		p.setLocation(getX(),getY());
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
	int cnt=0;
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(!stop) { 
			cnt++;
			if (pause) {
				while (pause) {
					try {
						System.out.println("pause ´©¸§");
						Thread.sleep(1000);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
			
			if (left) {
				left();
			} else if (right) {
				right();			
			}
			
			p.setLocation(x, y);
			p.getParent().repaint();
			
			try {
				Thread.sleep(50);
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
	public void setPause(boolean b) {
		pause = b;
	}
	
}	
