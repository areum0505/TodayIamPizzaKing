package mushroom;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

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
	MushroomEnd mushroomEnd;
	Pizza pizzaLabel;
	BulletThread bulletThread=null;
	
	public MushroomPanel(Game game) {
		setLayout(null);
		setBounds(0, 0, 1280, 720);
		
		ImageIcon img = new ImageIcon("images/mushroom/plusMushroom.png");
		targetLabel = new JLabel(img);
		targetLabel.setSize(img.getIconWidth(),img.getIconWidth());
		

		scoreLabel = new JLabel("000");
		scoreLabel.setFont(new Font("나눔고딕 ExtraBold", Font.BOLD, 30));
		scoreLabel.setBounds(1150, 0, 500, 50);
		
		add(scoreLabel);
		add(targetLabel);

		
		mushroomEnd = new MushroomEnd(game);
		
		addKeyListener(new MyKeyListener());
		
	}
	public void startGame() {
		pizzaLabel = new Pizza();
		add(pizzaLabel);
				
		
		targetThread = new TargetThread(targetLabel);
		targetThread.start();
		
		
	}
	class MyKeyListener extends KeyAdapter{

		
		@Override
		public void keyPressed(KeyEvent e) {
			int keyCode = e.getKeyCode(); // 상, 하, 좌, 우 키는 유니코드 키가 아님
			switch(keyCode) {
				/*case KeyEvent.VK_SPACE:
					if(bulletThread==null || !bulletThread.isAlive()) {
						bulletThread = new BulletThread(bulletLabel, targetLabel, targetThread);
						bulletThread.start();
					}
					break;
				*/	
				case KeyEvent.VK_LEFT:
					if(bulletThread==null || !bulletThread.isAlive()) {
						bulletThread = new BulletThread(pizzaLabel, targetLabel, targetThread);
						bulletThread.start();
					}
					pizzaLabel.left();
					checkScore();
					break;
				case KeyEvent.VK_RIGHT:
					if(bulletThread==null || !bulletThread.isAlive()) {
						bulletThread = new BulletThread(pizzaLabel, targetLabel, targetThread);
						bulletThread.start();
					}
					pizzaLabel.right();
					checkScore();
					break;
			}
		}
	}
	
	
	class Pizza extends JLabel{
		ImageIcon basket = new ImageIcon("images/mushroom/mushroomBasket.png");
	
		int x= 640 ,y = 500;
		public Pizza() {
			setIcon(basket);
			setBounds(getX(), getY(),150,170);
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
				setX(getX()-10);
				setY(getY());
				setLocation(getX(), getY());
			}
		}
		public void right() {
			if(getX()<1200){
				setX(getX()+10);
				setY(getY());
				setLocation(getX(), getY());
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
			
				try {
					sleep(20);
				}
				catch(InterruptedException e) {
					// the case of hit by a pizza
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
					System.out.println("명중함");
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
				bw.write("버섯 ");
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
