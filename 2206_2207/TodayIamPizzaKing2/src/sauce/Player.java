package sauce;

import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Player extends Thread {	
	private int x;
	private int y;
	
	public boolean left, right;	
		
	private int floor;
	
	private int count = 0;
	
	private JLabel avatar;
	
	private int exitY;
	
	private boolean pause = false;
	
	private ArrayList<Beam> beamList = new ArrayList<>();
	
	private ImageIcon img;
	private ImageIcon standImage =  new ImageIcon("images/character/pizza.png");
	private ImageIcon runIamge =  new ImageIcon("images/character/runPizza.png");
	private ImageIcon clearImage =  new ImageIcon("images/character/happyPizza.png");
	private ImageIcon deadImage =  new ImageIcon("images/character/deadPizza.png");
	
	public Player(JLabel avatar, int exitY) {
		super();
		x = avatar.getX();
		y = avatar.getY();
		floor = 0;
		this.avatar = avatar;
		this.exitY = exitY;
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
			
			if(pause) {
				while (pause) {
					try {
						Thread.sleep(1000);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
			
			avatar.setLocation(x, y);
			avatar.getParent().repaint();
		
			try {
				sleep(20);
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void up() {
		if(y > 23) {
			y -= 143;
			floor--;
		}
	}
	public void down() {
		if(y < 595) {
			y += 143;
			floor++;
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
	
	public void checkExit() {
		if(1155 < x && (exitY < y && y < exitY + 133)) {
			System.out.println("clear");
			avatar.setIcon(clearImage);
			stop();
			for(int i = 0; i < beamList.size(); i++) {
				beamList.get(i).setStop(true);
				
			}
			
			// panel
			
		}
	}
	
	public void dead() {
		System.out.println("dead");
		avatar.setIcon(deadImage);
		stop();
		
		// panel
		
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
	
	public int getFloor() {
		return floor;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
	
	public void setPause(boolean pause) {
		this.pause = pause;
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

	public ArrayList<Beam> getBeamList() {
		return beamList;
	}

	public void setBeamList(ArrayList<Beam> beamList) {
		this.beamList = beamList;
	}
	
}
