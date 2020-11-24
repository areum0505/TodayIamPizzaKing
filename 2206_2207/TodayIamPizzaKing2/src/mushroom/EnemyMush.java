package mushroom;

import java.awt.Font;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

class EnemyMush extends JLabel implements Runnable{
	
	ImageIcon eMush = new ImageIcon("images/mushroom/eMush.png");
	ImageIcon emptyMush = new ImageIcon("images/mushroom/emptyMush.png");
	
	static JLabel scoreLabel;
	private int x, y = 0;
	private int width = eMush.getIconWidth();
	private int height = eMush.getIconHeight();
	

	private static ArrayList<EnemyMush> e_MushList = new ArrayList<>();
	private boolean stop = false;
	private int eMushCount = 0;
	static int score =0;
	public boolean pause = false;
	
	Pizza p;
	JPanel jp;
	MushroomPanel mp;
	
	public EnemyMush(MushroomPanel mp) {
		setLayout(null);
		this.mp = mp;
		
		setIcon(eMush);
		ranX();
		setBounds(getX(), getY(), eMush.getIconWidth(), eMush.getIconHeight());
		
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
				Thread.sleep(80);
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
	public void setPause(boolean b) {
		pause = b;
	}
	
}

