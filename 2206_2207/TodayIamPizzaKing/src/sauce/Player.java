package sauce;

import java.awt.Image;

import javax.swing.ImageIcon;

import code.Game;

public class Player extends Thread {	
	private int x;
	private int y;
	
	private int alpha = 255;
	
	public boolean left, right, jump, attack;	
	
	private boolean isDead = false;
	
	private boolean jumping, falling;
	private int[] jumpY = {30, 20, 10, 7, 5, 3, 1};		// 점프를 할 때의 높이 (점점 줄어듦)
	private int jumpCount = 0;
	
	private int attackCount = 0;
	
	private int d;	// player가 보는 방향 - 0 : 왼쪽, 1 : 오른쪽
	
	private int hp = 3;
	private int attackDamage = 1;
	
	private int count = 0;

	private Image img;
	private Image standImage =  new ImageIcon("images/character/pizza.png").getImage();
	private Image runIamge =  new ImageIcon("images/character/runPizza.png").getImage();
	private Image leftAttackImage =  new ImageIcon("images/character/leftAttackPizza.png").getImage();
	private Image rightAttackImage =  new ImageIcon("images/character/rightAttackPizza.png").getImage();
	private Image attackedImage =  new ImageIcon("images/character/attackedPizza.png").getImage();
	private Image deadImage =  new ImageIcon("images/character/deadPizza.png").getImage();
	public Image leftEffect = new ImageIcon("images/character/pizza.png").getImage();
	public Image rightEffect = new ImageIcon("images/character/pizza.png").getImage();
	
	private int hitCount = 0;
	private boolean isHit = false;
	
	private boolean unableMove = false;
	
	private Boss b;

	public Player(Boss b) {
		super();
		img = standImage;
		x = 20;
		y = 760 - img.getHeight(null) - 20;
		d = 0;
		this.b = b;
	}	

	@Override
	public void run() {
		while(!isDead) {
			count++;
			check();
			if(left && !unableMove) {
				moveLeft();
				d = 0;
			} else if (right && !unableMove) {
				moveRight();
				d = 1;
			} else if(jump && !unableMove) {
				jump();
			} else if(attack && !unableMove) {
				attackCount++;
				attack();
				try {			// attack 할때 너무 빠른것 같아서 이거 추가했는데 이 방법은 아닌 것 같지만 다른 방법이 생각 안나서 썼음
					sleep(70);
				} catch (Exception e) {System.out.println("Player - run" + e);}
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
		if(x > 10) x -= 10;
		if(count % 3 == 1) {
			img = standImage;
		} else {
			img = runIamge;
		}
	}
	public void moveRight() {
		if(x < 1280 - img.getWidth(null) - 20) x += 10;
		if(count % 3 == 1) {
			img = standImage;
		} else {
			img = runIamge;
		}
	}
	public void jump() {
		img = standImage;
		jumping = true;
		falling = false;
		
		while(y > 350 && jump) {
			if(0 <= jumpCount && jumpCount < 6) y -= jumpY[jumpCount];
			else y -= 1;
			
			if (attack) {
				attack();
				break;
			}
			
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				System.out.println("Player - jump" + e);
			}
			jumpCount++;
		}
		
		jumping = false;
		falling = true;
		
		while(y < 760 - img.getHeight(null) - 20) {
			y += 1;
			
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				System.out.println("Player - jump" + e);
			}
			jumpCount--;
		}
	}
	public void attack() {
		if (d == 0) {	// 플레이어가 보는 방향이 왼쪽이면
			if(attackCount % 3 == 1) {
				img = leftAttackImage;
				if(x - 200 < b.getX() && b.getX() < x) {
					b.setHp(b.getHp() - attackDamage);
					// System.out.println("attack " + b.getHp());
				}
			} else {
				img = standImage;
			}
		} else {		// 오른쪽이면
			if(attackCount % 3 == 1) {
				img = rightAttackImage;
				if(x < b.getX() && b.getX() < x + 200) {
					b.setHp(b.getHp() - attackDamage);
					// System.out.println("attack " + b.getHp());
				}
			} else {
				img = standImage;
			}
		}
		if(attackCount >= 3) {	// attackCount가 3이 넘어가면 0으로 바꿔줌
			attackCount = 0;
		}
	}
	public void check() {
		if (!isHit && b.getX() < x + img.getWidth(null) && x < b.getX() + b.getImg().getWidth(null)) {
			isHit = true;
			System.out.println("소스에 닿음 " + hp);
		} else if(!isHit && b.horizontal.isStart() && b.horizontal.getY() < y + img.getHeight(null)) {
			isHit = true;
			System.out.println("가로빔 " + hp);
		} else if(!isHit && b.vertical.get(0).isStart()) {
			for(int i = 0; i < 4; i++) {
				Beam tempBeam = b.vertical.get(i);
				if(tempBeam.getX() < x + img.getWidth(null) && x < tempBeam.getX() + tempBeam.getImg().getWidth(null)) {
					isHit = true;
					System.out.println("세로빔 " + hp);
				}
			}
		}
		
		if(isHit) {
			invincibility();
			hitCount++;
		}
		
		if(hp == 0) {
			img = deadImage;
			y = 760 - deadImage.getHeight(null) - 20;
			isDead = true;
		}
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
	
	public int getD() {
		return d;
	}
	
	public void setD(int d) {
		this.d = d;
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public int getAttackDamage() {
		return attackDamage;
	}

	public void setAttackDamage(int attackDamage) {
		this.attackDamage = attackDamage;
	}
	
	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
	
	public Image getImg() {
		return img;
	}

	public void setImg(Image img) {
		this.img = img;
	}
	
	public Image getStandImage() {
		return standImage;
	}

	public void setStandImage(Image standImage) {
		this.standImage = standImage;
	}

	public Image getRunIamge() {
		return runIamge;
	}

	public void setRunIamge(Image runIamge) {
		this.runIamge = runIamge;
	}

	public Image getLeftAttackImage() {
		return leftAttackImage;
	}

	public void setLeftAttackImage(Image leftAttackImage) {
		this.leftAttackImage = leftAttackImage;
	}

	public Image getRightAttackImage() {
		return rightAttackImage;
	}

	public void setRightAttackImage(Image rightAttackImage) {
		this.rightAttackImage = rightAttackImage;
	}
	public int getAlpha() {
		return alpha;
	}
	public void setAlpha(int alpha) {
		this.alpha = alpha;
	}
}
