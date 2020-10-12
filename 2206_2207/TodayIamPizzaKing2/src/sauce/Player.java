package sauce;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import code.Game;

public class Player extends Thread {	
	private int x;
	private int y;
	
	private int alpha = 255;
	
	public boolean left, right, jump, attack;	
	
	private boolean isDead = false;
	
	private int hp = 3;
	
	private int count = 0;

	private ImageIcon img;
	private ImageIcon standImage =  new ImageIcon("images/character/pizza.png");
	private ImageIcon runIamge =  new ImageIcon("images/character/runPizza.png");
	private ImageIcon attackedImage =  new ImageIcon("images/character/attackedPizza.png");
	private ImageIcon deadImage =  new ImageIcon("images/character/deadPizza.png");
	
	private int hitCount = 0;
	private boolean isHit = false;
	
	private boolean unableMove = false;

	public Player() {
		super();
		img = standImage;
		x = 20;
		y = 720 - img.getIconHeight() - 20;
	}	

	@Override
	public void run() {
		while(!isDead) {
			count++;
			check();
			if(left && !unableMove) {
				moveLeft();
			} else if (right && !unableMove) {
				moveRight();
			}
			try {
				sleep(30);
			} catch (Exception e) {
				System.out.println("Player - run " + e);
			}
			//System.out.println(count);
		}
	}

	public void moveLeft() {
		System.out.println("left");
		if(x > 10) x -= 10;
		if(count % 3 == 1) {
			img = standImage;
		} else {
			img = runIamge;
		}
	}
	public void moveRight() {
		System.out.println("right");
		if(x < 1280 - img.getIconWidth() - 20) x += 10;
		if(count % 3 == 1) {
			img = standImage;
		} else {
			img = runIamge;
		}
	}
	
	public void check() {
//		if (!isHit && b.getX() < x + img.getIconWidth() && x < b.getX() + b.getImg().getWidth(null)) {
//			isHit = true;
//			System.out.println("¼Ò½º¿¡ ´êÀ½ " + hp);
//		} else if(!isHit && b.horizontal.isStart() && b.horizontal.getY() < y + img.getIconHeight()) {
//			isHit = true;
//			System.out.println("°¡·Îºö " + hp);
//		} else if(!isHit && b.vertical.get(0).isStart()) {
//			for(int i = 0; i < 4; i++) {
//				Beam tempBeam = b.vertical.get(i);
//				if(tempBeam.getX() < x + img.getIconWidth() && x < tempBeam.getX() + tempBeam.getImg().getWidth(null)) {
//					isHit = true;
//					System.out.println("¼¼·Îºö " + hp);
//				}
//			}
//		}
//		
//		if(isHit) {
//			invincibility();
//			hitCount++;
//		}
//		
//		if(hp == 0) {
//			img = deadImage;
//			y = 750 - deadImage.getIconHeight() - 20;
//			isDead = true;
//			System.out.println("dead");
//		}
	}
	public void invincibility() {
		if(hitCount == 0) {
			new Thread(new Runnable() {	
				@Override
				public void run() {
					unableMove = true;
					hp -= 1;
					x -= 20;
					if(hp != 0) {
						img = attackedImage;
						try {
							sleep(500);
						} catch (Exception e) {
							System.out.println("Player - invincibility" + e);
						}
						if(img == attackedImage) {
							img = standImage;
						}
						unableMove = false;
						for(int i = 0; i < 10; i++) {
							if(alpha == 255) {
								alpha = 150;
							} else {
								alpha = 255;
							}
							try {
								sleep(250);
							} catch (Exception e) {
								System.out.println("Player - invincibility" + e);
							}
						}
						alpha = 255;
						isHit = false;
						hitCount = 0;
					}
				}
			}).start();
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
	
	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
	
	public ImageIcon getImg() {
		return img;
	}

	public void setImg(ImageIcon img) {
		this.img = img;
	}
	
	public ImageIcon getStandImage() {
		return standImage;
	}

	public void setStandImage(ImageIcon standImage) {
		this.standImage = standImage;
	}

	public ImageIcon getRunIamge() {
		return runIamge;
	}

	public void setRunIamge(ImageIcon runIamge) {
		this.runIamge = runIamge;
	}
	
	public int getAlpha() {
		return alpha;
	}
	
	public void setAlpha(int alpha) {
		this.alpha = alpha;
	}
	
	public boolean isDead() {
		return isDead;
	}
	
	public void setDead(boolean isDead) {
		this.isDead = isDead;
	}

	public void setLeft(boolean b, JLabel avatar) {
		left = b;
		avatar.setLocation(x, y);
		avatar.setIcon(img);
	}
	public void setRight(boolean b, JLabel avatar) {
		right = b;
		avatar.setLocation(x, y);
		avatar.setIcon(img);
	}
}
