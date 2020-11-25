package mushroom;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import java.util.ArrayList;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import code.Game;

public class MushroomPanel extends JPanel {

	private Image backgroundImage = new ImageIcon("images/mushroom/newMushroomBack.png").getImage();
	ImageIcon fullHeart = new ImageIcon("images/mushroom/fullHeart.png");
	ImageIcon deadHeart = new ImageIcon("images/mushroom/deadHeart.png");
	
	JLabel scoreLabel, man, heart1, heart2, heart3;

	MushroomEnd mushroomEnd;
	Pizza pizzaMan;
	ArrayList<FriendMush> f_MushList;
	ArrayList<EnemyMush> e_MushList;
	ArrayList<HeartMinusMush> hm_MushList;
	int score = 0;
	int heartCnt = 0;
	
	Thread th, th2,th3;
	MushroomPause mushroomPause;
	
	public MushroomPanel(Game game) {
		setLayout(null);
		setBounds(0, 0, 1280, 720);
		
		mushroomPause = new MushroomPause(game);
		mushroomEnd = new MushroomEnd(game);
		
		f_MushList = new ArrayList<>();
		e_MushList = new ArrayList<>();
		hm_MushList = new ArrayList<>();

		
		scoreLabel = new JLabel("000");
		scoreLabel.setFont(new Font("³ª´®°íµñ ExtraBold", Font.BOLD, 35));
		scoreLabel.setBounds(1150, 35, 500, 50);

		add(mushroomPause);
		add(scoreLabel);

		mushroomPause.setVisible(false);
		
		heart1 = new JLabel(fullHeart);
		heart1.setBounds(50,30,fullHeart.getIconWidth(), fullHeart.getIconHeight());
		add(heart1);
		
		heart2 = new JLabel(fullHeart);
		heart2.setBounds(150,30,fullHeart.getIconWidth(), fullHeart.getIconHeight());
		add(heart2);
		
		heart3 = new JLabel(fullHeart);
		heart3.setBounds(250,30,fullHeart.getIconWidth(), fullHeart.getIconHeight());
		add(heart3);
		
		addKeyListener(new MyKeyListener());
		
		man = new JLabel();
		add(man);
	}

	public void startGame() {
		score = 0;
		scoreLabel.setText("000");
		heartCnt = 3;
		checkHeart();
		
		
		pizzaMan = new Pizza(man);
		pizzaMan.setX(560); pizzaMan.setY(500);
		pizzaMan.start();
		
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

		for (int i = 0; i < 3; i++) {
			HeartMinusMush hm_Mush = new HeartMinusMush(this);
			hm_MushList.add(hm_Mush);
			add(hm_Mush);
		}

		for (HeartMinusMush hm_Mush : hm_MushList) {
			hm_Mush.setHm_MushList(hm_MushList);
		}
	
		for (HeartMinusMush hm_Mush : hm_MushList) {
			th3 = new Thread(hm_Mush);
			hm_Mush.setPizza(pizzaMan);
			th3.start();
		}


	}
	void plus() {
		scoreLabel.setText(String.valueOf(score += 100));
		checkScore();
	}
	void minus() {
		scoreLabel.setText(String.valueOf(score -= 100));
		checkScore();
	}
	void minusHeart() {
		heartCnt-=1;
		checkHeart();
		checkScore();
	}
	void checkHeart() {
		switch(heartCnt) {
			case 3:
				heart3.setIcon(fullHeart);
				heart2.setIcon(fullHeart);
				heart1.setIcon(fullHeart);
				break;
			case 2:
				heart3.setIcon(deadHeart);
				break;
			case 1:
				heart2.setIcon(deadHeart);
				break;
			case 0:
				heart1.setIcon(deadHeart);
				break;
		}
	}
	class MyKeyListener extends KeyAdapter {

		@Override
		public void keyPressed(KeyEvent e) {
			int keyCode = e.getKeyCode(); // »ó, ÇÏ, ÁÂ, ¿ì Å°´Â À¯´ÏÄÚµå Å°°¡ ¾Æ´Ô
			switch (keyCode) {
			
			case KeyEvent.VK_ESCAPE:
				mushroomPause.setVisible(true);
				pizzaMan.setPause(true);
				for (FriendMush f_Mush : f_MushList) {
					f_Mush.setPause(true);
				}
				for (EnemyMush e_Mush : e_MushList) {
					e_Mush.setPause(true);
				}
				for (HeartMinusMush hm_Mush : hm_MushList) {
					hm_Mush.setPause(true);
				}
				break;
			case KeyEvent.VK_LEFT:
				pizzaMan.left=true;
				
				break;
			case KeyEvent.VK_RIGHT:
				pizzaMan.right=true;
				
				break;
			}
		}
		@Override
		public void keyReleased(KeyEvent e) {
			int keyCode = e.getKeyCode(); // »ó, ÇÏ, ÁÂ, ¿ì Å°´Â À¯´ÏÄÚµå Å°°¡ ¾Æ´Ô
			
			switch(keyCode) {
			case KeyEvent.VK_LEFT: 
				pizzaMan.left = false;
				
				break;
			case KeyEvent.VK_RIGHT:
				pizzaMan.right = false;
				break;
			}
		}
	}
	public void checkScore() {
		
		
		if(heartCnt == 0) {
			pizzaMan.stop();
			for (FriendMush f_Mush : f_MushList) {
				f_Mush.setStop(true);
			}
			for (EnemyMush e_Mush : e_MushList) {
				e_Mush.setStop(true);
			}
			for (HeartMinusMush hm_Mush : hm_MushList) {
				hm_Mush.setStop(true);
			}
			mushroomEnd.Fail();
		}
		if(score == 2000) {
			
			pizzaMan.stop();
			for (FriendMush f_Mush : f_MushList) {
				f_Mush.setStop(true);
			}
			for (EnemyMush e_Mush : e_MushList) {
				e_Mush.setStop(true);
			}
			for (HeartMinusMush hm_Mush : hm_MushList) {
				hm_Mush.setStop(true);
			}
			mushroomEnd.Success();
			System.out.println("¹ö¼¸ È¹µæ");
			try {
				BufferedWriter bw = new BufferedWriter(new FileWriter("bag.txt", true));
				bw.write("¹ö¼¸ ");
				bw.flush();
				bw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
	}
	public void endGame() {
		pizzaMan.stop();
		for (FriendMush f_Mush : f_MushList) {
			f_Mush.setStop(true);
			f_Mush.setEmptyImg();

		}
		for (EnemyMush e_Mush : e_MushList) {
			e_Mush.setStop(true);
			e_Mush.setEmptyImg();
		}
		for (HeartMinusMush hm_Mush : hm_MushList) {
			hm_Mush.setStop(true);
			hm_Mush.setEmptyImg();
		}	
		
	}
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), null);
	}
}
