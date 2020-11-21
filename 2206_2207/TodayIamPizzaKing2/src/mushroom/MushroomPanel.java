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
		scoreLabel.setFont(new Font("������� ExtraBold", Font.BOLD, 30));
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
		checkScore();
	}
	void minus() {
		scoreLabel.setText(String.valueOf(score -= 100));
		checkScore();
	}
	class MyKeyListener extends KeyAdapter {

		@Override
		public void keyPressed(KeyEvent e) {
			int keyCode = e.getKeyCode(); // ��, ��, ��, �� Ű�� �����ڵ� Ű�� �ƴ�
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
	public void checkScore() {
		if(score == 200) {
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
			
			try {
				BufferedWriter bw = new BufferedWriter(new FileWriter("bag.txt", true));
				bw.write("���� ");
				bw.flush();
				bw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			mushroomEnd.Success();
		}
	}
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), null);
	}
}
