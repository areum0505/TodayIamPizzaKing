package pepperoni;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import code.Game;
import code.Main;
import code.Music;

public class Pepperoni extends JLabel implements Runnable {
	private static int count = 0;

	private int x, y;
	private int d;
	private int floor;

	private boolean stop = false;
	private boolean pause = false;

	private ImageIcon pepperoni = new ImageIcon("images/pepperoni/pepperoni.png");

	private Game game;

	private Thread prevTh;
	private PepperoniPanel pp;

	public Pepperoni(int x, int d, int floor, Thread prevTh, PepperoniPanel pp, Game game) {
		this.prevTh = prevTh;
		Init(x, d, floor, pp, game);
	}

	public Pepperoni(int x, int d, int floor, PepperoniPanel pp, Game game) {
		Init(x, d, floor, pp, game);
	}

	public void Init(int x, int d, int floor, PepperoniPanel pp, Game game) {
		setLayout(null);

		count++;

		this.x = x;
		this.d = d;
		this.floor = floor;
		y = 50;

		this.pp = pp;
		this.game = game;

		setIcon(pepperoni);
		setBounds(x, y, pepperoni.getIconWidth(), pepperoni.getIconHeight());

		setVisible(true);
	}

	public int[] drop() {
		stop = true;
		if (count <= 10)
			floor -= 30;
		int[] arr = { x, d, floor };
		return arr;
	}

	@Override
	public void run() {
		if (count > 1)
			waitPepperoni();
		movePepperoni();
		dropPepperoni();
		checkPepperoni();
	}

	public void waitPepperoni() {
		while (prevTh.isAlive()) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void movePepperoni() {
		while (!stop) {
			if (pause) {
				while (pause) {
					try {
						Thread.sleep(1000);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}

			if (d == 1) {
				x += (5 + count);
			} else if (d == 0) {
				x -= (5 + count);
			}

			if (x < 300) {
				d = 1;
			} else if (x > 870) {
				d = 0;
			}

			setLocation(x, y);
			getParent().repaint();

			try {
				Thread.sleep(15);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void dropPepperoni() {
		while (stop) {
			y += 15;
			if (y > floor)
				stop = false;

			setLocation(x, y);
			getParent().repaint();

			try {
				Thread.sleep(17);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		if (Main.buttonEffect) {
			Music dropsound = new Music("pepperonidrop.mp3", false);
			dropsound.start();
		}
	}

	public void checkPepperoni() {
		if (!(535 < x && x < 655)) {
			while (true) {
				if (y > 655 && count <= 12)
					break;
				else if (count > 12 && y > 720)
					break;
				y += 15;
				setLocation(x, y);
				getParent().repaint();
				try {
					Thread.sleep(25);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			count -= 1;
			pp.setStop();
			pp.setSadface();
			pp.pepperoniEnd.Fail();
		} else {
			if (count > 10) {
				ArrayList<Pepperoni> pepperonis = pp.getPepperonis();
				for (int i = 0; i < pepperonis.size() - 1; i++) {
					Pepperoni temp = pepperonis.get(i);
					temp.setLocation(temp.getX(), temp.getY() + 30);
					// System.out.print(temp.getX() + ", " + temp.getY() + "\t");
					getParent().repaint();
				}
			}
		}

		if (count > 11) {
			pp.setBackUp(true);
		}
		if (count == 11) {
			pp.setPlateUp(true);
		} else {
			pp.setPlateUp(false);
		}

		if (count > 15) {
			pp.setStop();

			try {
				BufferedWriter bw = new BufferedWriter(new FileWriter("bag.txt", true));
				bw.write("∆‰∆€∑Œ¥œ ");
				bw.flush();
				bw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

			pp.pepperoniEnd.Success();
			System.out.println("∆‰∆€∑Œ¥œ »πµÊ");
		}
	}

	public int getCount() {
		return count;
	}

	public void setCount() {
		count = 0;
	}

	public void setFloor(int floor) {
		this.floor = floor;
	}

	public int getFloor() {
		return floor;
	}

	public void setPause(boolean b) {
		pause = b;
	}
}
