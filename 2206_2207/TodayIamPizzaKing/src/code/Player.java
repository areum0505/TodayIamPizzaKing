package code;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Player {
	private int x = 75;
	private int y = 330;
	
	private int d = 0;	// player가 보는 방향 - 0 : 왼쪽, 1 : 오른쪽
	
	public boolean jumping = false;
	
	private int hp = 3;
	private int attackDamage = 1;

	private Image img;
	private Image standImage =  new ImageIcon("images/character/pizza.png").getImage();
	private Image runIamge =  new ImageIcon("images/character/runPizza.png").getImage();
	private Image leftAttackImage =  new ImageIcon("images/character/pizza.png").getImage();
	private Image rightAttackImage =  new ImageIcon("images/character/pizza.png").getImage();
	public Image leftEffect = new ImageIcon("images/character/pizza.png").getImage();
	public Image rightEffect = new ImageIcon("images/character/pizza.png").getImage();

	public Player() {
		super();
		img = standImage;
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

}
