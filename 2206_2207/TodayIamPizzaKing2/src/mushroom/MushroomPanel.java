package mushroom;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
<<<<<<< HEAD
import java.util.ArrayList;
=======
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
>>>>>>> origin/master

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import code.Game;

public class MushroomPanel extends JPanel {

	private Image backgroundImage = new ImageIcon("images/mushroom/mushroomBackground.png").getImage();

	JLabel scoreLabel;

	MushroomEnd mushroomEnd;
	Pizza pizzaMan;
	ArrayList<FriendMush> f_MushList;
	ArrayList<EnemyMush> e_MushList;

	int score = 0;

	Thread th, th2,th3;
	MushroomPause mushroomPause;
	
	public MushroomPanel(Game game) {
		setLayout(null);
		setBounds(0, 0, 1280, 720);
		
		mushroomPause = new MushroomPause(game);
		mushroomEnd = new MushroomEnd(game);
		
		f_MushList = new ArrayList<>();
		e_MushList = new ArrayList<>();

		scoreLabel = new JLabel("000");
		scoreLabel.setFont(new Font("³ª´®°íµñ ExtraBold", Font.BOLD, 30));
		scoreLabel.setBounds(1150, 0, 500, 50);

		add(mushroomPause);
		add(scoreLabel);

		mushroomPause.setVisible(false);
		
		pizzaMan = new Pizza();
		add(pizzaMan);
		
		addKeyListener(new MyKeyListener());
		
	}

	public void startGame() {

		for (int i = 0; i < 4; i++) {
			FriendMush f_Mush = new FriendMush(this);
			f_MushList.add(f_Mush);
			add(f_Mush);
		}

		for (FriendMush f_Mush : f_MushList) {
			f_Mush.setF_MushList(f_MushList);
		}
		
		for (FriendMush f_Mush : f_MushList) {
			th = new Thread(f_Mush);
			f_Mush.setPizza(pizzaMan);
			th.start();
		}

		for (int i = 0; i < 4; i++) {
			EnemyMush e_Mush = new EnemyMush(this);
			e_MushList.add(e_Mush);
			add(e_Mush);
		}

		for (EnemyMush e_Mush : e_MushList) {
			e_Mush.setE_MushList(e_MushList);
		}
		
		for (EnemyMush e_Mush : e_MushList) {
			th2 = new Thread(e_Mush);
			e_Mush.setPizza(pizzaMan);
			th2.start();
		}

		th3 = new Thread(pizzaMan);
		th3.start();
		

	}
	void plus() {
		scoreLabel.setText(String.valueOf(score += 100));
		scoreCheck();
	}
	void minus() {
		scoreLabel.setText(String.valueOf(score -= 100));
		scoreCheck();
	}
	void scoreCheck() {
		if(score>=500) {
			for (FriendMush f_Mush : f_MushList) {
				f_Mush.setStop(true);
			}
			for (EnemyMush e_Mush : e_MushList) {
				e_Mush.setStop(true);
			}	
			pizzaMan.setStop(true);
			mushroomEnd.setVisible(true);
			score = 0;
			scoreLabel.setText("000");
		}
	}

	class MyKeyListener extends KeyAdapter {

		@Override
		public void keyPressed(KeyEvent e) {
			int keyCode = e.getKeyCode(); // »ó, ÇÏ, ÁÂ, ¿ì Å°´Â À¯´ÏÄÚµå Å°°¡ ¾Æ´Ô
			switch (keyCode) {
			
			case KeyEvent.VK_ESCAPE:
				mushroomPause.setVisible(true);
				for (FriendMush f_Mush : f_MushList) {
					f_Mush.setStop(true);
				}
				for (EnemyMush e_Mush : e_MushList) {
					e_Mush.setStop(true);
				}
				pizzaMan.setStop(true);
				break;
			case KeyEvent.VK_LEFT:
				pizzaMan.left();
				// System.out.println(score);
				break;
			case KeyEvent.VK_RIGHT:
				pizzaMan.right();
				// System.out.println(score);
				break;
			}
		}
	}

<<<<<<< HEAD
=======
	class BulletThread extends Thread {
		JComponent target;
		Pizza pizza;
		Thread targetThread;
	
		public BulletThread(Pizza pizza, JComponent target, Thread targetThread) {
			this.pizza = pizza;
			this.target = target;
			this.targetThread = targetThread;
		}
		@Override
		public void run() {
			while(true) {
				
				if(hit()) {
					targetThread.interrupt();
					System.out.println("¸íÁßÇÔ");
					winScore+=100;
					scoreLabel.setText(Integer.toString(winScore));
					return;
				}
				
				try {
					sleep(20);
				}
				catch(InterruptedException e) {}
			}
		}
		
		boolean hit() {
			if(targetContains(pizza.getX(), pizza.getY()) || 
					targetContains(pizza.getX() + pizza.getWidth() - 1, pizza.getY()) ||
					targetContains(pizza.getX() + pizza.getWidth() - 1, pizza.getY()+pizza.getHeight() - 1) ||
					targetContains(pizza.getX(), pizza.getY()+pizza.getHeight() - 1))
				return true;
			else
				return false;					
		}
		
		boolean targetContains(int x, int y) {
			if(((target.getX() <= x) && (target.getX() + target.getWidth() - 1 >= x)) &&
					((target.getY() <= y)&& (target.getY() + target.getHeight() - 1 >= y))) {
				return true;
			}
			else
				return false;
			
		}
	}
	public void checkScore() {
		if(winScore == 200) {
			targetThread.stop();
			
			try {
				BufferedWriter bw = new BufferedWriter(new FileWriter("bag.txt", true));
				bw.write("¹ö¼¸ ");
				bw.flush();
				bw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			mushroomEnd.Success();
		}
	}
>>>>>>> origin/master
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), null);
	}
}
