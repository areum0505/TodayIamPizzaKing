package mushroom;

import java.awt.Font;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

class HeartMinusMush extends JLabel implements Runnable{
	
	ImageIcon hmMush = new ImageIcon("images/mushroom/hmMush.png");
	ImageIcon emptyMush = new ImageIcon("images/mushroom/emptyMush.png");
	
	static JLabel scoreLabel;
	private int x, y = 0;
	private int width = hmMush.getIconWidth();
	private int height = hmMush.getIconHeight();
	

	private static ArrayList<HeartMinusMush> hm_MushList = new ArrayList<>();
	private boolean stop = false;
	private int hmMushCount = 0;
	static int score = 0;
	public boolean pause = false;
	
	Pizza p;
	JPanel jp;
	MushroomPanel mp;
	
	public HeartMinusMush(MushroomPanel mp) {
		setLayout(null);
		this.mp = mp;
		
		setIcon(hmMush);
		ranX();
		setBounds(getX(), getY(), hmMush.getIconWidth(), hmMush.getIconHeight());
		
		setVisible(true);
	
		
	}

	@Override
	public void run() {
		int count = 0;
		// TODO Auto-generated method stub
		while(!stop) {
			count++;
			
			drop();
			
			if (pause) {
				while (pause) {
					try {
						Thread.sleep(1000);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
			
			if(hit()) {
				check();
			}
			
			try {
				Thread.sleep(130);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
	}
	
	public void drop() {
		int ran = ((int)(Math.random()*20)+1);
		
		setX(getX());
		setY(getY()+ran);
	
		if(getY()>=600) {
			ranX();	
			setY(0);
			setLocation(getX(),getY());
		}
		else{
			setLocation(getX(), getY());
		}
		
		hmMushCount++;
		getParent().repaint();
	}
	public void check() {
	
		mp.minusHeart();
		
	}
	public void setEmptyImg() {
		setIcon(emptyMush);
	}
	boolean hit() {
		if (getX()+getWidth() > p.getX() && p.getX() + p.getWidth()>getX()&& getY()+getHeight()>p.getY()&& p.getY()+ 10>getY()) {
			ranX();
			setY(0);
			setLocation(getX(), getY());
			return true;
		}else {
			return false;
		}	
	}
	public void ranX() {
		x = (int)(Math.random()*1210+20);
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

	public void setHeight(int height) {
		this.height = height;
	}

	public void setWidth(int width) {
		this.width = width;
	}
	public int getHmMushCount() {
		return hmMushCount;
	}

	public void setHmMushCount(int hmMushCount) {
		this.hmMushCount = hmMushCount;
	}
	public void setStop(boolean stop) {
		this.stop = stop;
	}
	public ArrayList<HeartMinusMush> getHm_MushList() {
		return hm_MushList;
	}

	public void setHm_MushList(ArrayList<HeartMinusMush> hm_MushList) {
		this.hm_MushList = hm_MushList;
	}
	public void setPizza(Pizza pizzaMan) {
		// TODO Auto-generated met
		this.p = pizzaMan;
	}
	public void setPause(boolean b) {
		pause = b;
	}
	
}

