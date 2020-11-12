package paprika;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

class Bar extends JLabel {
	
	int width, height;
	
	public Bar() {
		this.width = 100;
		this.height = 500;
		setBounds(700,100,width,height);
		setOpaque(true); 
		setBackground(Color.black);
	}
	
}
class ChoiceBar extends JLabel{
	int width, height;
	
	public ChoiceBar() {
		this.width = 100;
		this.height = 10;
		setBounds(700,110,width,height);
		setOpaque(true); 
		setBackground(Color.white);
	}
}
class EnemyBar extends JLabel{
	
	int width, height;
	
	int ran = ((int)(Math.random()*3)+1)*20;
	
	public EnemyBar() {
		this.width = 100;
		this.height = ran;
		setSize(width,height);
		setOpaque(true); 
		setBackground(Color.CYAN);
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
	public void reset() {
		int ran = ((int)(Math.random()*3)+1)*30;
		this.width = 100;
		this.height = ran;
		setSize(width,height);
		setOpaque(true); 
		setBackground(Color.CYAN);
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
		while(true) {
			moveChoiceBar();
		}
	}
	public void moveChoiceBar() {	
			while(choiceBar.getY()<=bar.getY()+480) { 
				try {
					int x = choiceBar.getX();
					int y = choiceBar.getY()+10;
				
					
						choiceBar.setLocation(x,y);
						choiceBar.getParent().repaint();
						knife.setLocation(x,y);
						knife.getParent().repaint();
					
					
					Thread.sleep(30);
				}catch(InterruptedException e) {
					e.printStackTrace();
				}
			}
			while(choiceBar.getY()>=bar.getY()) {
				try {
					int x = choiceBar.getX();
					int y = choiceBar.getY()-10;
				
						choiceBar.setLocation(x,y);
						choiceBar.getParent().repaint();
						knife.setLocation(x,y);
						knife.getParent().repaint();
					
					Thread.sleep(20);
				}catch(InterruptedException e) {
					e.printStackTrace();
				}
			
		}	
	}
	
	
	
}


public class PaprikaPanel extends JPanel{
	private Image backgroundImage = new ImageIcon("images/mushroom/mushroomBackground.png").getImage();
	private ImageIcon knifeIcon = new ImageIcon("images/paprika/knife.png");
	private ImageIcon paprikaIcon = new ImageIcon("images/paprika/paprika.png");
	
	JLabel paprika, score;

	Bar bar = new Bar();
	ChoiceBar choiceBar = new ChoiceBar();
	 
	EnemyBar enemy1 = new EnemyBar();
	EnemyBar enemy2 = new EnemyBar();
	EnemyBar enemy3 = new EnemyBar();
	
	JLabel knife = new JLabel(knifeIcon);
	
	int win=0;
	Thread td;
	Game g;
	
	private PaprikaEnd paprikaEnd;
	
	public PaprikaPanel(code.Game game) {
		setLayout(null);
		setBounds(0, 0, 1280, 720);
		
		paprika = new JLabel(paprikaIcon);
		paprika.setBounds(50,100, paprikaIcon.getIconWidth(), paprikaIcon.getIconHeight());
		add(paprika);
		
		knife.setBounds(700,100,200,150);
		add(knife);
		add(choiceBar);
		
		enemy1.setLocation(700, 150);
		add(enemy1);
		enemy2.setLocation(700, 300);
		add(enemy2);
		enemy3.setLocation(700, 450);
		add(enemy3);
		
		add(bar);
		
		score = new JLabel("000");
		score.setFont(new Font("나눔고딕 ExtraBold", Font.BOLD, 30));
		score.setBounds(1100, 70, 500, 50);
		
		add(score);
		
		g = new Game(bar, choiceBar, knife);
		td = new Thread(g);
		td.start();
		
		paprikaEnd = new PaprikaEnd(game);
		
		setVisible(true);
		addKeyListener(new MyKeyListener());
	}
	

	class MyKeyListener extends KeyAdapter {
		@Override
		public void keyPressed(KeyEvent e) {
			int keyCode = e.getKeyCode(); // 상, 하, 좌, 우 키는 유니코드 키가 아님
			
			switch(keyCode) {
				case KeyEvent.VK_SPACE:
					System.out.println("스페이스 누름");
					crashCheck();
					break;
				case KeyEvent.VK_ESCAPE:
					System.exit(0);
					break;
				case KeyEvent.VK_LEFT:
					System.out.println("왼쪽");
					break;
			}
		}
	}
	public void crashCheck() {
		int ran = ((int)(Math.random()*3)+1)*30;
		
		
		if (enemy1.getX()+ enemy1.getWidth() > choiceBar.getX() && choiceBar.getX() + choiceBar.getWidth()>enemy1.getX()&& enemy1.getY()+enemy1.getHeight()>choiceBar.getY()&& choiceBar.getY()+ choiceBar.getHeight()>enemy1.getY()) {
			win+=100;
			System.out.println(win);
			score.setText(Integer.toString(win));
			enemy1.setBackground(Color.BLUE);
			enemy1.reset();
			enemy1.setLocation(700,150);
		}
		else if (enemy2.getX()+ enemy2.getWidth() > choiceBar.getX() && choiceBar.getX() + choiceBar.getWidth()>enemy2.getX()&& enemy2.getY()+enemy2.getHeight()>choiceBar.getY()&& choiceBar.getY()+ choiceBar.getHeight()>enemy2.getY()) {
			win+=100;
			System.out.println(win);
			score.setText(Integer.toString(win));
			enemy2.setBackground(Color.BLUE);
			enemy2.reset();
			enemy2.setLocation(700,300);
		}
		else if (enemy3.getX()+ enemy3.getWidth() > choiceBar.getX() && choiceBar.getX() + choiceBar.getWidth()>enemy3.getX()&& enemy3.getY()+enemy3.getHeight()>choiceBar.getY()&& choiceBar.getY()+ choiceBar.getHeight()>enemy3.getY()) {
			win+=100;
			System.out.println(win);
			score.setText(Integer.toString(win));
			enemy3.setBackground(Color.BLUE);
			enemy3.reset();
			enemy3.setLocation(700,450);
			
		}else {
			td.stop();
			System.out.println("오잉");
			paprikaEnd.Fail();
		}
	}	// 플레이어와 코인 충돌 체크
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), null);
	
	}
}
