package mushroom;

import java.awt.Font;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

class EnemyMush extends JLabel implements Runnable{
	
	ImageIcon mMush = new ImageIcon("images/mushroom/mMush.png");
	static JLabel scoreLabel;
	private int x, y = 0;
	private int width = mMush.getIconWidth();
	private int height = mMush.getIconHeight();
	

	private static ArrayList<EnemyMush> e_MushList = new ArrayList<>();
	private boolean stop = false;
	private int eMushCount = 0;
	static int score =0;
	
	Pizza p;
	JPanel jp;
	MushroomPanel mp;
	
	public EnemyMush(MushroomPanel mp) {
		setLayout(null);
		this.mp = mp;
		
		setIcon(mMush);
		ranX();
		setBounds(getX(), getY(), mMush.getIconWidth(), mMush.getIconHeight());
		
		setVisible(true);
	
		
	}

	@Override
	public void run() {
		int count = 0;
		// TODO Auto-generated method stub
		while(!stop) {
			count++;
			
			drop();
			if(hit()) {
				check();
			}
			try {
				Thread.sleep(150);
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
		
		eMushCount++;
		getParent().repaint();
	}
	public void check() {
	
		mp.minus();
		
	}
	
	boolean hit() {
		if (getX()+getWidth() > p.getX() && p.getX() + p.getWidth()>getX()&& getY()+getHeight()>p.getY()&& p.getY()+ p.getHeight()>getY()) {
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
	public int getEMushCount() {
		return eMushCount;
	}

	public void setEMushCount(int eMushCount) {
		this.eMushCount = eMushCount;
	}
	public void setStop(boolean stop) {
		this.stop = stop;
	}
	public ArrayList<EnemyMush> getE_MushList() {
		return e_MushList;
	}

	public void setE_MushList(ArrayList<EnemyMush> e_MushList) {
		this.e_MushList = e_MushList;
	}
	public void setPizza(Pizza pizzaMan) {
		// TODO Auto-generated met
		this.p = pizzaMan;
	}
	
}

