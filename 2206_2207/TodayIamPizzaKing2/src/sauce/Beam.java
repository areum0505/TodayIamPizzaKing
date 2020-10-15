package sauce;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Beam extends JLabel implements Runnable {
	private String type;
	private boolean isBeam = false;
	private boolean isStart = false;

	private int beamCount = 0;
	
	private int x, y;
	
	private int[] beamY = {55, 197, 339, 481, 623};
		
	private ImageIcon img;
	private ImageIcon emptyBeam =  new ImageIcon("images/sauce/emptyBeam.png");
	private ImageIcon horizontalBeam =  new ImageIcon("images/sauce/horizontalBeam.png");
	private ImageIcon preHorizontalBeam =  new ImageIcon("images/sauce/preHorizontalBeam.gif");
	private ImageIcon verticalBeam =  new ImageIcon("images/sauce/verticalBeam.png");
	private ImageIcon preVerticalBeam =  new ImageIcon("images/sauce/preVerticalBeam.gif");
	
	public Beam(String type) {
		this.type = type;
		if(type.equals("horizontal")) {
			img = emptyBeam;
			x = 7;
			y = beamY[(int)(Math.random()*5)];
		} else {
			img = emptyBeam;
			x = (int)(Math.random()*1260+20);
			y = 20;
		}
		setVisible(true);
		setIcon(img);
		setBounds(x, y, img.getIconWidth(), img.getIconHeight());
		System.out.println(x + " " + y);
	}
	
	@Override
	public void run() {
		while(true) {		
			beam();
			getParent().repaint();
			try {
				Thread.sleep(25);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void beam() {
		if(type.equals("horizontal")) {
			img = horizontalBeam;
		}
	}
	
	public void changeX() {
		x = (int)(Math.random()*1260+20);
	}
	public void changeY() {
		
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

	public ImageIcon getImg() {
		return img;
	}

	public void setImg(ImageIcon img) {
		this.img = img;
	}
}
