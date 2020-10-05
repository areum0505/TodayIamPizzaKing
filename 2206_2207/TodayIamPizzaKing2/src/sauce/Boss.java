package sauce;

import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Boss extends Thread {
	private int x;
	private int y;
	
	private int hp;
	
	private boolean isDead = false;
	
	private Image img;
	private Image standImage =  new ImageIcon("images/sauce/sauce.png").getImage();
	private Image deadImage =  new ImageIcon("images/sauce/deadSauce.png").getImage();
	private Image attackImage =  new ImageIcon("images/sauce/attackSauce.gif").getImage();
	
	private boolean isAttack = false;
	private int attackCount = 0;
	
	public Beam horizontal = new Beam("horizontal");
	public ArrayList<Beam> vertical = new ArrayList<>();	
	
	private long t1, t2;
	
	public Boss() {
		img = standImage;
		x = 1280 - img.getWidth(null) - 20;
		y = 750 - img.getHeight(null) - 20;
		hp = 30;
		
		for(int i = 0; i < 4; i++) {
			vertical.add(new Beam("vertical"));
		}
	}
	
	@Override
	public void run() {
		t1 = System.currentTimeMillis();
		while(!isDead) {
			// System.out.println((t2 - t1)/1000.0);
			
			t2 = System.currentTimeMillis();
			if(hp <= 0) {
				img = deadImage;
				y = 750 - deadImage.getHeight(null) - 20;
				isDead = true;
				break;
			}
			
			if((t2 - t1)/1000.0 > 3) {
				horizontal.beam();
			} 
			if ((t2 - t1)/1000.0 > 5) {
				attack();
			}
			if((t2 - t1)/1000.0 > 7) {
				for(int i = 0; i < 4; i++) {
					vertical.get(i).beam();
				}
			}
			if((t2 - t1)/1000.0 > 10) {
				attack();
			}
			
			if((t2 - t1)/1000.0 > 12) {
				t1 = System.currentTimeMillis();
				horizontal.setBeamCount(0);
				for(int i = 0; i < 4; i++) {
					vertical.get(i).setBeamCount(0);
					vertical.get(i).changeX();
				}
			}
			try {
				sleep(150);
			} catch (Exception e) {
				System.out.println("Boss - run" + e);
			}
		}
	}
	
	public void attack() {
		if(attackCount < 4) {
			isAttack = true;
			img = attackImage;
		}
		if(attackCount > 4) {
			isAttack = false;
			img = standImage;
		}
		attackCount++;
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

	public boolean isDead() {
		return isDead;
	}
	public void setDead(boolean isDead) {
		this.isDead = isDead;
	}
	
}
