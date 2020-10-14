package sauce;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Player extends Thread {	
	private int x;
	private int y;
	
	public boolean left, right;	
	
	private int count = 0;
	
	private JLabel avatar;
	
	private ImageIcon img;
	private ImageIcon standImage =  new ImageIcon("images/character/pizza.png");
	private ImageIcon runIamge =  new ImageIcon("images/character/runPizza.png");
	private ImageIcon deadImage =  new ImageIcon("images/character/deadPizza.png");
	
	public Player(JLabel avatar) {
		super();
		x = avatar.getX();
		y = avatar.getY();
		this.avatar = avatar;
	}	

	@Override
	public void run() {
		while(true) {
			count++;
			
			if (left) {
				left();
			} else if (right) {
				right();
			}
			
			avatar.setLocation(x, y);
			avatar.getParent().repaint();
		
			try {
				sleep(25);
			}catch(InterruptedException e) {
				return;
			}
		}
	}
	
	public void up() {
		if(y > 23) {
			y -= 143;
		}
	}
	public void down() {
		if(y < 595) {
			y += 143;
		}
	}
	public void left() {
		if(x > 10) {
			x -= 5;
		}
		if(count % 3 == 1) {
			img = standImage;
		} else {
			img = runIamge;
		}
		avatar.setIcon(img);
	}
	public void right() {
		if(x < 1280 - avatar.getWidth() - 10) {
			x += 5;
		}
		if(count % 3 == 1) {
			img = standImage;
		} else {
			img = runIamge;
		}
		avatar.setIcon(img);
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
}
