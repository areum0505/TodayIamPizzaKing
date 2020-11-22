package sauce;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Exit {
	private int x;
	private int y;
	
	private int floor;
	private int[] exitY = {15, 157, 300, 440, 583};
	
	private Image img =  new ImageIcon("images/sauce/exit.png").getImage();

	public Exit() {
		x = 1280 - img.getWidth(null) - 5;
		floor = (int)(Math.random() * 5);
		y = exitY[floor];
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
	
	public void changeY() {
		floor = (int)(Math.random() * 5);
		y = exitY[floor];
	}
	
	public int getExitY() {
		return exitY[floor];
	}

	public Image getImg() {
		return img;
	}

	public void setImg(Image img) {
		this.img = img;
	}
}
