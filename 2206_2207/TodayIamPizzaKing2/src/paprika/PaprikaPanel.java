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
		this.width = 500;
		this.height = 500;
		setBounds(250,100,width,height);
	
	}
}
class ChoiceBar extends JLabel{
	int width, height;
	
	public ChoiceBar() {
		this.width = 10;
		this.height = 500;
		setBounds(250,100,width,height);
		setOpaque(true); 
	
	}
}
class EnemyBar extends JLabel{
	
	int width, height;
	
	int ran = ((int)(Math.random()*3)+1)*20;
	
	public EnemyBar() {
		this.width = ran;
		this.height = 500;
		setSize(width,height);
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
	public void clear() {
		int ran = ((int)(Math.random()*3)+1)*30;
		this.width = ran;
		this.height = 500;
		setSize(width,height);
	}
}
class Game implements Runnable {

	private Bar bar;
	private ChoiceBar choiceBar;
	EnemyBar enemy1, enemy2, enemy3; 
	JLabel knife;
	public boolean running = true;
	
	public Game(Bar bar, ChoiceBar choiceBar, JLabel knife) {
		this.bar = bar;
		this.choiceBar = choiceBar;
		this.knife = knife;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(running) {
			moveChoiceBar();
		}
	}
	public void moveChoiceBar() {	
			while(choiceBar.getX()<=bar.getX()+500) { 
				try {
					int x = choiceBar.getX()+10;
					int y = choiceBar.getY();
				
					
						choiceBar.setLocation(x,y);
						choiceBar.getParent().repaint();
						knife.setLocation(x,y);
						knife.getParent().repaint();
					
					
					Thread.sleep(30);
				}catch(InterruptedException e) {
					e.printStackTrace();
				}
			}
			while(choiceBar.getX()>=bar.getX()) {
				try {
					int x = choiceBar.getX()-10;
					int y = choiceBar.getY();
				
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
	private Image backgroundImage = new ImageIcon("images/paprika/paprikaBackground.png").getImage();
	private ImageIcon knifeIcon = new ImageIcon("images/paprika/newKnife.png");
	
	private ImageIcon redPap = new ImageIcon("images/paprika/redPaprika90.png");
	private ImageIcon yellowPap = new ImageIcon("images/paprika/yellowPaprika90.png");
	private ImageIcon greenPap = new ImageIcon("images/paprika/greenPaprika90.png");
	
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
	Thread prevTh;
	private PaprikaEnd paprikaEnd;
	private PaprikaPause paprikaPause;
	
	public PaprikaPanel(code.Game game) {
		setLayout(null);
		setBounds(0, 0, 1280, 720);
		
		paprikaEnd = new PaprikaEnd(game);
		paprikaPause = new PaprikaPause(game);
		add(paprikaPause);
		paprikaPause.setVisible(false);
		
		knife.setBounds(500,300,knifeIcon.getIconWidth(),knifeIcon.getIconHeight());
		add(knife);
		add(choiceBar);
		
		enemy1.setLocation(250, 100);
		add(enemy1);
		enemy2.setLocation(450, 100);
		add(enemy2);
		enemy3.setLocation(700, 100);
		add(enemy3);
		
		paprika = new JLabel();
		paprika.setIcon(redPap);
		paprika.setBounds(250,140, redPap.getIconWidth(), redPap.getIconHeight());
		add(paprika);
		
		
		//add(bar);
		
		score = new JLabel("000");
		score.setFont(new Font("나눔고딕 ExtraBold", Font.BOLD, 30));
		score.setForeground(Color.red);
		score.setBounds(1100, 70, 500, 50);
		
		add(score);
		
		g = new Game(bar, choiceBar, knife);
		td = new Thread(g);
		td.start();
		
		
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
					g.running = false;
					paprikaPause.setVisible(true);
				
					break;
				
			}
		}
	}
	
	public void reset() {
		win = 0;
		g.running = true;
	}
	public void paprikaCheck() {
		
		if(win>=600) {
			paprika.setIcon(greenPap);
			paprika.setBounds(250,140, greenPap.getIconWidth(), greenPap.getIconHeight());
			score.setForeground(Color.green);
			enemy1.setBackground(Color.green);
			enemy2.setBackground(Color.green);
			enemy3.setBackground(Color.green);
			
		}else if(win>=300) {
			paprika.setIcon(yellowPap);
			paprika.setBounds(250,140, yellowPap.getIconWidth(), yellowPap.getIconHeight());
			score.setForeground(Color.yellow);
			enemy1.setBackground(Color.yellow);
			enemy2.setBackground(Color.yellow);
			enemy3.setBackground(Color.yellow);
		}
	}
	public void crashCheck() {
		
		if (enemy1.getX()+ enemy1.getWidth() > choiceBar.getX() && choiceBar.getX() + choiceBar.getWidth()>enemy1.getX()&& enemy1.getY()+enemy1.getHeight()>choiceBar.getY()&& choiceBar.getY()+ choiceBar.getHeight()>enemy1.getY()) {
			win+=100;
			scoreCheck();
			System.out.println(win);
			score.setText(Integer.toString(win));
			paprikaCheck();
			enemy1.clear();
			enemy1.setLocation(250,100);
		}
		else if (enemy2.getX()+ enemy2.getWidth() > choiceBar.getX() && choiceBar.getX() + choiceBar.getWidth()>enemy2.getX()&& enemy2.getY()+enemy2.getHeight()>choiceBar.getY()&& choiceBar.getY()+ choiceBar.getHeight()>enemy2.getY()) {
			win+=100;
			scoreCheck();
			System.out.println(win);
			score.setText(Integer.toString(win));
			enemy2.clear();
			paprikaCheck();
			enemy2.setLocation(450,100);
		}
		else if (enemy3.getX()+ enemy3.getWidth() > choiceBar.getX() && choiceBar.getX() + choiceBar.getWidth()>enemy3.getX()&& enemy3.getY()+enemy3.getHeight()>choiceBar.getY()&& choiceBar.getY()+ choiceBar.getHeight()>enemy3.getY()) {
			win+=100;
			System.out.println(win);
			score.setText(Integer.toString(win));
			scoreCheck();
			paprikaCheck();
			enemy3.clear();
			enemy3.setLocation(700,100);
			
		}else {
				g.running = false;
				paprikaEnd.Fail();
			
		}
	}
	public void scoreCheck() {
		if(win==900) {
			g.running=false;
			System.out.println("피망클리어");
			score.setText(Integer.toString(win));
			paprikaEnd.Success();
			
		}
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), null);
	
	}
}
