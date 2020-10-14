package code;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import sauce.Exit;
import sauce.Player;

public class SaucePanel extends JPanel{
	private Image backgroundImage = new ImageIcon("images/stage/stage6Back.png").getImage();
	private ImageIcon standImage =  new ImageIcon("images/character/pizza.png");
	private ImageIcon runIamge =  new ImageIcon("images/character/runPizza.png");
	
	public JLabel avatar;
	
	private Player player;
	
	private Exit exit;

	public SaucePanel(Game game) {
		setLayout(null);
		setBounds(0, 0, 1280, 720);
				
		avatar = new JLabel(standImage);
		avatar.setBounds(30, 23, 92, 120);
		add(avatar);
		
		exit = new Exit();
	}
	
	public void startGame() {
		player = new Player(avatar);
		player.start();
		addKeyListener(new MyKeyListener()); // 키 리스너 등록
	}
	
	class MyKeyListener extends KeyAdapter {
		@Override
		public void keyPressed(KeyEvent e) {
			int keyCode = e.getKeyCode(); // 상, 하, 좌, 우 키는 유니코드 키가 아님
			
			switch(keyCode) {
			case KeyEvent.VK_UP: 
				player.up();
				break;
			case KeyEvent.VK_DOWN: 
				player.down();
				break;
			case KeyEvent.VK_LEFT: 
				player.left = true;
				break;
			case KeyEvent.VK_RIGHT:
				player.right = true;
				break;
			}
		}
		@Override
		public void keyReleased(KeyEvent e) {
			int keyCode = e.getKeyCode(); // 상, 하, 좌, 우 키는 유니코드 키가 아님
			
			switch(keyCode) {
			case KeyEvent.VK_LEFT: 
				player.left = false;
				break;
			case KeyEvent.VK_RIGHT:
				player.right = false;
				break;
			}
		}
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), null);
		g.drawImage(exit.getImg(), exit.getX(), exit.getY(), exit.getImg().getWidth(null), exit.getImg().getHeight(null), null);
	}
}