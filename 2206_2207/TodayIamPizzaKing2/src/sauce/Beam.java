package sauce;

import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Beam extends JLabel implements Runnable {
	private String type;
	
	private int x, y;
	
	private boolean isBeam = false;
	private boolean isStart = false;
	private int beamCount = 0;
		
	private int floor = 5;
	private int[] beamY = {55, 197, 339, 481, 623};
	
	private int count = 0;
	
	private boolean stop = false;
	private ArrayList<Beam> beamList = new ArrayList<>();
	
	private boolean pause = false;

	private Player p;

	private ImageIcon emptyHorizontalBeam =  new ImageIcon("images/sauce/emptyHorizontalBeam.png");
	private ImageIcon emptyVerticalBeam =  new ImageIcon("images/sauce/emptyVerticalBeam.png");
	private ImageIcon horizontalBeam =  new ImageIcon("images/sauce/horizontalBeam.png");
	private ImageIcon verticalBeam =  new ImageIcon("images/sauce/verticalBeam.png");
	private ImageIcon preVerticalBeam =  new ImageIcon("images/sauce/preVerticalBeam.gif");
	private ImageIcon preHorizontalBeam =  new ImageIcon("images/sauce/preHorizontalBeam.gif");
	private ImageIcon doingVerticalBeam =  new ImageIcon("images/sauce/doingVerticalBeam.png");
	private ImageIcon doingHorizontalBeam =  new ImageIcon("images/sauce/doingHorizontalBeam.png");
	
	public Beam(String type) {
		setLayout(null);
		this.type = type;
		if(type.equals("horizontal")) {
			x = 7;
			changeY();
			setIcon(emptyHorizontalBeam);
			setBounds(x, y, emptyHorizontalBeam.getIconWidth(), emptyHorizontalBeam.getIconHeight());
		} else {
			changeX();
			y = 9;
			setIcon(emptyVerticalBeam);
			setBounds(x, y, emptyVerticalBeam.getIconWidth(), emptyVerticalBeam.getIconHeight());
		}
		setVisible(true);
	}
	
	@Override
	public void run() {
		while(!stop) {	
			count++;
			
			check();
		
			if(count > 5) {
				beam();
			} 			
			if(count > 12) {
				beamCount = 0;
				count = 0;
				if(type.equals("horizontal")) {
					changeY();
				} else {
					changeX();
				}
			} 
			
			if(pause) {
				if(isBeam && !isStart) {
					if(type.equals("horizontal"))
						setIcon(doingHorizontalBeam);
					else
						setIcon(doingVerticalBeam);
				}
				while (pause) {
					try {
						Thread.sleep(1000);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
			
			try {
				Thread.sleep(150);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		setEmptyImg();
	}
	
	public void beam() {
		if(beamCount < 4) {
			isBeam = true;
			if(type.equals("horizontal")) setIcon(preHorizontalBeam);
			else setIcon(preVerticalBeam);
		} else {
			isStart = true;
			if(type.equals("horizontal")) setIcon(horizontalBeam);
			else setIcon(verticalBeam);
		}
		if(beamCount > 5) {
			isBeam = false;
			isStart = false;
			if(type.equals("horizontal")) setIcon(emptyHorizontalBeam);
			else setIcon(emptyVerticalBeam);
		}
		beamCount++;
		getParent().repaint();
	}
	
	public void changeX() {
		x = (int)(Math.random()*1210+20);
	}
	public void changeY() {
		floor = (int)(Math.random()*5);
		y = beamY[floor];
	}
	
	public void check() {
		if(isStart && type.equals("horizontal")) {
			if(p.getFloor() == floor) {
				for(int i = 0; i < beamList.size(); i++) {
					beamList.get(i).setStop(true);
				}
				p.dead();
			}
		} 
		if(isStart && type.equals("vertical")) {
			if(p.getX() < x + 40 && x + 10 < p.getX() + 92) {
				for(int i = 0; i < beamList.size(); i++) {
					beamList.get(i).setStop(true);
				}
				p.dead();
			}
		}
	}
	
	public void setEmptyImg() {
		if(type.equals("horizontal")) {
			setIcon(emptyHorizontalBeam);
		} else {
			setIcon(emptyVerticalBeam);
		}
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
	
	public void setPlayer(Player p) {
		this.p = p;
	}
	
	public void setStop(boolean stop) {
		this.stop = stop;
	}
	
	public void setPause(boolean pause) {
		this.pause = pause;
	}
	
	public ArrayList<Beam> getBeamList() {
		return beamList;
	}

	public void setBeamList(ArrayList<Beam> beamList) {
		this.beamList = beamList;
	}
}
