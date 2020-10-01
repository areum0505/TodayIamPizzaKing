package sauce;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Boss extends Thread {
	private int x;
	private int y;
	
	private int hp;
	private int attackDamage = 1;
	
	private Image img;
	private Image standImage =  new ImageIcon("images/sauce/sauce.png").getImage();
	private Image deadImage =  new ImageIcon("images/sauce/deadSauce.png").getImage();
	
	public Boss() {
		img = standImage;
		x = 1280 - img.getWidth(null) - 20;
		y = 760 - img.getHeight(null) - 20;
		hp = 30;
	}
	
	@Override
	public void run() {
		while(true) {
			// System.out.println(hp);
			try {
				if(hp <= 0) {
					img = deadImage;
				}
				sleep(30);
			} catch (Exception e) {
				System.out.println("Boss - " + e);
			}
		}
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
	public int getHp() {
		return hp;
	}
	public void setHp(int hp) {
		this.hp = hp;
	}
	public Image getImg() {
		return img;
	}
	public void setImg(Image img) {
		this.img = img;
	}
}
