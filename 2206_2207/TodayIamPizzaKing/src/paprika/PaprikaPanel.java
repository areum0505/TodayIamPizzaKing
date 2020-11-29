package paprika;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import code.Main;
import code.Music;

class Bar extends JLabel {

	int width, height;

	public Bar() {
		this.width = 500;
		this.height = 500;
		setBounds(250, 100, width, height);

	}
}

class ChoiceBar extends JLabel {
	int width, height;

	public ChoiceBar() {
		this.width = 10;
		this.height = 500;
		setBounds(250, 100, width, height);
		setOpaque(true);

	}
}

class EnemyBar extends JLabel {

	int width, height;

	// int ran = ((int)(Math.random()*2)+1)*30;
	int levelWidth = 0;

	public EnemyBar() {
		this.width = 50;
		this.height = 500;
		setSize(width, height);
		setOpaque(true);
		setBackground(Color.red);
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public void clear(int levelWidth) {

		this.width = levelWidth;
		this.height = 500;
		setSize(width, height);
	}
}

class Game implements Runnable {

	private Bar bar;
	private ChoiceBar choiceBar;
	EnemyBar enemy1, enemy2, enemy3;
	JLabel knife;

	public Game(Bar bar, ChoiceBar choiceBar, JLabel knife) {
		this.bar = bar;
		this.choiceBar = choiceBar;
		this.knife = knife;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			moveChoiceBar();
		}
	}

	public void moveChoiceBar() {
		while (choiceBar.getX() <= bar.getX() + 500) {
			try {
				int x = choiceBar.getX() + 10;
				int y = choiceBar.getY();

				choiceBar.setLocation(x, y);
				choiceBar.getParent().repaint();
				knife.setLocation(x, y);
				knife.getParent().repaint();

				Thread.sleep(30);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		while (choiceBar.getX() >= bar.getX()) {
			try {
				int x = choiceBar.getX() - 10;
				int y = choiceBar.getY();

				choiceBar.setLocation(x, y);
				choiceBar.getParent().repaint();
				knife.setLocation(x, y);
				knife.getParent().repaint();

				Thread.sleep(20);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
	}

}

public class PaprikaPanel extends JPanel {
	private Image backgroundImage = new ImageIcon("images/paprika/newPaprikaBack.png").getImage();
	private ImageIcon knifeIcon = new ImageIcon("images/paprika/newKnife.png");

	private ImageIcon redPap90 = new ImageIcon("images/paprika/redPaprika90.png");
	private ImageIcon yellowPap90 = new ImageIcon("images/paprika/yellowPaprika90.png");
	private ImageIcon greenPap90 = new ImageIcon("images/paprika/greenPaprika90.png");

	private ImageIcon redPap = new ImageIcon("images/paprika/redPaprika.png");
	private ImageIcon yellowPap = new ImageIcon("images/paprika/yellowPaprika.png");
	private ImageIcon greenPap = new ImageIcon("images/paprika/greenPaprika.png");

	JLabel paprika, score, showRedPap, showYellowPap, showGreenPap;

	Bar bar = new Bar();
	ChoiceBar choiceBar = new ChoiceBar();

	EnemyBar enemy1 = new EnemyBar();
	EnemyBar enemy2 = new EnemyBar();
	EnemyBar enemy3 = new EnemyBar();

	JLabel knife = new JLabel(knifeIcon);

	int win = 0;
	Thread td;
	Game g;
	Thread prevTh;
	private PaprikaEnd paprikaEnd;
	private PaprikaPause paprikaPause;
	int ran = ((int) (Math.random() * 100) + 1) + 20;

	public PaprikaPanel(code.Game game) {
		setLayout(null);
		setBounds(0, 0, 1280, 720);

		paprikaEnd = new PaprikaEnd(game);
		paprikaPause = new PaprikaPause(game);
		add(paprikaPause);
		paprikaPause.setVisible(false);

		knife.setBounds(500, 300, knifeIcon.getIconWidth(), knifeIcon.getIconHeight());
		add(knife);
		add(choiceBar);

		enemy1.setLocation(230 + ran, 100);
		add(enemy1);
		enemy2.setLocation(400 + ran, 100);
		add(enemy2);
		enemy3.setLocation(550 + ran, 100);
		add(enemy3);

		paprika = new JLabel();
		paprika.setIcon(redPap90);
		paprika.setBounds(250, 140, redPap90.getIconWidth(), redPap90.getIconHeight());
		add(paprika);

		score = new JLabel("000");
		score.setFont(new Font("³ª´®°íµñ ExtraBold", Font.BOLD, 50));
		score.setForeground(Color.red);
		score.setBounds(700, 20, 500, 50);
		add(score);

		showYellowPap = new JLabel(yellowPap);
		showYellowPap.setBounds(950, 200, yellowPap.getIconWidth(), yellowPap.getIconHeight());
		add(showYellowPap);

		showGreenPap = new JLabel(greenPap);
		showGreenPap.setBounds(950, 50, greenPap.getIconWidth(), greenPap.getIconHeight());
		add(showGreenPap);

		showRedPap = new JLabel(redPap);
		showRedPap.setBounds(950, 350, redPap.getIconWidth(), redPap.getIconHeight());
		add(showRedPap);

		showGreenPap.setVisible(false);
		showYellowPap.setVisible(false);
		showRedPap.setVisible(false);

		setVisible(true);

		addKeyListener(new MyKeyListener());
	}

	public void startGame() {

		win = 0;
		score.setText("000");
		g = new Game(bar, choiceBar, knife);
		td = new Thread(g);
		td.start();
		paprikaCheck();
		showGreenPap.setVisible(false);
		showYellowPap.setVisible(false);
		showRedPap.setVisible(false);
		

	}

	class MyKeyListener extends KeyAdapter {
		@Override
		public void keyPressed(KeyEvent e) {

			int keyCode = e.getKeyCode();

			switch (keyCode) {
			case KeyEvent.VK_SPACE:
				if (Main.buttonEffect) {
					Music dropsound = new Music("knife.mp3", false);
					dropsound.start();
				}
				crashCheck();
				break;
			case KeyEvent.VK_ESCAPE:
				td.stop();
				paprikaPause.setVisible(true);
				break;

			}
		}
	}

	public void paprikaCheck() {

		if (win >= 600) {
			paprika.setIcon(greenPap90);
			paprika.setBounds(250, 140, greenPap90.getIconWidth(), greenPap90.getIconHeight());
			score.setForeground(Color.green);
			enemy1.setBackground(Color.green);
			enemy2.setBackground(Color.green);
			enemy3.setBackground(Color.green);

			enemy1.clear(30);
			enemy2.clear(30);
			enemy3.clear(30);

		} else if (win >= 300) {
			paprika.setIcon(yellowPap90);
			paprika.setBounds(250, 140, yellowPap90.getIconWidth(), yellowPap90.getIconHeight());
			score.setForeground(Color.yellow);
			enemy1.setBackground(Color.yellow);
			enemy2.setBackground(Color.yellow);
			enemy3.setBackground(Color.yellow);

			enemy1.clear(40);
			enemy2.clear(40);
			enemy3.clear(40);

		} else {
			paprika.setIcon(redPap90);
			paprika.setBounds(250, 140, redPap90.getIconWidth(), redPap90.getIconHeight());
			score.setForeground(Color.red);
			enemy1.setBackground(Color.red);
			enemy2.setBackground(Color.red);
			enemy3.setBackground(Color.red);

			enemy1.clear(50);
			enemy2.clear(50);
			enemy3.clear(50);

		}
	}

	public void showPaprika() {
		if (win >= 900) {
			showGreenPap.setVisible(true);
		} else if (win >= 600) {
			showYellowPap.setVisible(true);
		} else if (win >= 300) {
			showRedPap.setVisible(true);

		}
	}

	public void crashCheck() {

		if (enemy1.getX() + enemy1.getWidth() > choiceBar.getX()
				&& choiceBar.getX() + choiceBar.getWidth() > enemy1.getX()
				&& enemy1.getY() + enemy1.getHeight() > choiceBar.getY()
				&& choiceBar.getY() + choiceBar.getHeight() > enemy1.getY()) {
			win += 100;
			scoreCheck();
			score.setText(Integer.toString(win));
			paprikaCheck();
			int ran = ((int) (Math.random() * 100) + 1) + 50;
			enemy1.setLocation(250 + ran, 100);
			
		} else if (enemy2.getX() + enemy2.getWidth() > choiceBar.getX()
				&& choiceBar.getX() + choiceBar.getWidth() > enemy2.getX()
				&& enemy2.getY() + enemy2.getHeight() > choiceBar.getY()
				&& choiceBar.getY() + choiceBar.getHeight() > enemy2.getY()) {
			win += 100;
			scoreCheck();
			score.setText(Integer.toString(win));
			paprikaCheck();
			int ran = ((int) (Math.random() * 100) + 1) + 50;
			enemy2.setLocation(400 + ran, 100);
		} else if (enemy3.getX() + enemy3.getWidth() > choiceBar.getX()
				&& choiceBar.getX() + choiceBar.getWidth() > enemy3.getX()
				&& enemy3.getY() + enemy3.getHeight() > choiceBar.getY()
				&& choiceBar.getY() + choiceBar.getHeight() > enemy3.getY()) {
			win += 100;
			score.setText(Integer.toString(win));
			scoreCheck();
			paprikaCheck();
			int ran = ((int) (Math.random() * 100) + 1) + 50;
			enemy3.setLocation(550 + ran, 100);

		} else {
			td.stop();
			paprikaEnd.Fail();
			win = 0;
			score.setText("000");
		}
	}

	public void scoreCheck() {
		showPaprika();
		if (win == 900) {
			td.stop();
			System.out.println("ÆÄÇÁ¸®Ä« È¹µæ");
			score.setText(Integer.toString(win));

			paprikaEnd.Success();
			win = 0;
			score.setText("000");

			try {
				BufferedWriter bw = new BufferedWriter(new FileWriter("bag.txt", true));
				bw.write("ÆÄÇÁ¸®Ä« ");
				bw.flush();
				bw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), null);

	}
}
