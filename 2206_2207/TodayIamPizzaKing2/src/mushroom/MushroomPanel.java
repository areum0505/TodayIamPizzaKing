package mushroom;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

import code.Game;

public class MushroomPanel extends JPanel{
	TargetThread targetThread=null;
	private Image backgroundImage = new ImageIcon("images/mushroom/mushroomBackground.png").getImage();
	JLabel targetLabel;
	int winScore =0;
	public static JLabel scoreLabel; 
	JLabel baseLabel = new JLabel();
	
	public MushroomPanel(Game game) {
		setLayout(null);
		setBounds(0, 0, 1280, 720);

		baseLabel.setSize(40,40);
		baseLabel.setOpaque(true);
		baseLabel.setBackground(Color.BLACK);
		
		ImageIcon img = new ImageIcon("images/mushroom/plusMushroom.png");
		targetLabel = new JLabel(img);
		targetLabel.setSize(img.getIconWidth(),img.getIconWidth());

		scoreLabel = new JLabel("000");
		scoreLabel.setFont(new Font("나눔고딕 ExtraBold", Font.BOLD, 30));
		scoreLabel.setBounds(1150, 0, 500, 50);
		
		add(scoreLabel);
		add(baseLabel);
		add(targetLabel);
		
		this.startGame();
		
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				baseLabel.setFocusable(true);
				baseLabel.requestFocus(); // 키보드 입력을 받도록 포커스 강제 지정			
			}
		});
		
	}
	public void startGame() {
		Bullet bulletLabel = new Bullet();
		bulletLabel.addIn(this);
		
		baseLabel.setLocation(this.getWidth()/2-20, this.getHeight()-40);			
		targetLabel.setLocation(0, 0);
		
		targetThread = new TargetThread(targetLabel);
		targetThread.start();
		
		baseLabel.setFocusable(true);
		baseLabel.requestFocus(); // 키보드 입력을 받도록 포커스 강제 지정			
		baseLabel.addKeyListener(new KeyAdapter() {
			BulletThread  bulletThread = null;
			
			@Override
			public void keyPressed(KeyEvent e) {
				int keyCode = e.getKeyCode();
				
				if(keyCode == KeyEvent.VK_SPACE) {
					if(bulletThread==null || !bulletThread.isAlive()) {
						bulletThread = new BulletThread(bulletLabel, targetLabel, targetThread);
						bulletThread.start();
					}
				}else if(keyCode == KeyEvent.VK_LEFT) {
					bulletLabel.left();
				}else if(keyCode == KeyEvent.VK_RIGHT) {
					bulletLabel.right();
				}
			}
		});
		
	}
	class Bullet{
		ImageIcon basket = new ImageIcon("images/mushroom/mushroomBasket.png");
		JLabel bullet;
		int x= 640 ,y = 470;
		public Bullet() {
			bullet = new JLabel(basket);
			bullet.setBounds(getX(), getY(),150,170);
		}
		public void addIn(JPanel p) {
			p.add(bullet);
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
		public void left() {
			if(-80<getX()) {
				setX(bullet.getX()-10);
				setY(bullet.getY());
				bullet.setLocation(getX(), getY());
			}
		}
		public void right() {
			if(getX()<1200){
				setX(bullet.getX()+10);
				setY(bullet.getY());
				bullet.setLocation(getX(), getY());
			}	
		}
		public int getWidth() {
			return basket.getIconWidth();
		}
		public int getHeight() {
			return basket.getIconHeight();
		}
	}
	class TargetThread extends Thread {
		JComponent target;
		
		int mix = ((int)(Math.random()*13))*100;
		
		public TargetThread(JComponent target) {
			this.target = target;
			target.setLocation(mix, 0);
			target.getParent().repaint();
		}	
		
		@Override
		public void run() {
			while(true) {
				int x = target.getX();
				int y = target.getY()+5;
				
				if(y >= MushroomPanel.this.getHeight()-100) {
					mix = ((int)(Math.random()*13))*100;
					target.setLocation(mix,0);
				}else {
					target.setLocation(x, y);
				}
				target.getParent().repaint();
				try {
					sleep(20);
				}
				catch(InterruptedException e) {
					// the case of hit by a bullet
					target.setLocation(0, 0);
					target.getParent().repaint();
					try {
						sleep(500); // 0.5초 기다린 후에 계속한다.
					}catch(InterruptedException e2) {}					
				}
			}
		}
	}

	class BulletThread extends Thread {
		JComponent target;
		Bullet bullet;
		Thread targetThread;
	
		public BulletThread(Bullet bullet, JComponent target, Thread targetThread) {
			this.bullet = bullet;
			this.target = target;
			this.targetThread = targetThread;
		}
		@Override
		public void run() {
			while(true) {
				// 명중하였는지 확인
				if(hit()) {
					targetThread.interrupt();
					System.out.println("명중함");
					winScore-=100;
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
			if(targetContains(bullet.getX(), bullet.getY()) || 
					targetContains(bullet.getX() + bullet.getWidth() - 1, bullet.getY()) ||
					targetContains(bullet.getX() + bullet.getWidth() - 1, bullet.getY()+bullet.getHeight() - 1) ||
					targetContains(bullet.getX(), bullet.getY()+bullet.getHeight() - 1))
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
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), null);
		}
}
