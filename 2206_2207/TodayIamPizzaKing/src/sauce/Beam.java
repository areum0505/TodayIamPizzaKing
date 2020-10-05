package sauce;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Beam {
	private String type;
	private boolean isBeam = false;
	private boolean isStart = false;

	private int beamCount = 0;
	
	private int x, y;
	
	private Image img;
	private Image verticalBeam =  new ImageIcon("images/sauce/verticalBeam.png").getImage();
	private Image preVerticalBeam =  new ImageIcon("images/sauce/preVerticalBeam.gif").getImage();
	private Image horizontalBeam =  new ImageIcon("images/sauce/horizontalBeam.png").getImage();
	private Image preHorizontalBeam =  new ImageIcon("images/sauce/preHorizontalBeam.gif").getImage();
	
	public Beam(String type) {
		this.type = type;
		if(type.equals("horizontal")) {
			x = 0;
			y = 760 - 80;
		} else {
			x = (int)(Math.random()*1260+20);
			y = 20;
		}
	}
	
	public void beam() {
		if(beamCount < 4) {
			isBeam = true;
			if(type.equals("horizontal")) img = preHorizontalBeam;
			else img = preVerticalBeam;
		} else {
			isStart = true;
			if(type.equals("horizontal")) img = horizontalBeam;
			else img = verticalBeam;
		}
		if(beamCount > 4) {
			isBeam = false;
			isStart = false;
		}
		beamCount++;
	}
	
	public void changeX() {
		x = (int)(Math.random()*1260+20);
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public boolean isBeam() {
		return isBeam;
	}

	public void setBeam(boolean isBeam) {
		this.isBeam = isBeam;
	}
	
	public boolean isStart() {
		return isStart;
	}

	public void setStart(boolean isStart) {
		this.isStart = isStart;
	}

	public int getBeamCount() {
		return beamCount;
	}

	public void setBeamCount(int beamCount) {
		this.beamCount = beamCount;
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

	public Image getImg() {
		return img;
	}

	public void setImg(Image img) {
		this.img = img;
	}
}
