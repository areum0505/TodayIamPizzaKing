package code;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import sauce.Beam;
import sauce.Exit;
import sauce.Player;

public class SaucePanel extends JPanel{
	private Image backgroundImage = new ImageIcon("images/stage/stage6Back.png").getImage();
	private ImageIcon pizzaImg =  new ImageIcon("images/character/pizza.png");
	
	public JLabel avatar;
	private Player player;
	private ArrayList<Beam> beamList = new ArrayList<>();	
	private Exit exit;

	public SaucePanel(Game game) {
		setLayout(null);
		setBounds(0, 0, 1280, 720);
				
		avatar = new JLabel(pizzaImg);
		avatar.setVisible(true);
		avatar.setBounds(30, 23, 92, 120);
		add(avatar);
		
		for(int i = 0; i < 4; i ++) {
			Beam b = new Beam("horizontal");
			beamList.add(b);
			add(b);
		}
				
		exit = new Exit();
	}
	
	public void startGame() {
		player = new Player(avatar, exit.getExitY());
		player.start();

		Thread th = new Thread(beamList.get(0));
		th.start();
		
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
			case KeyEvent.VK_SPACE:
				player.checkExit();
			}
		}
		@Override
		public void keyReleased(KeyEvent e) {
			int keyCode = e.getKeyCode(); // 상, 하, 좌, 우 키는 유니코드 키가 아님
			
			switch(keyCode) {
			case KeyEvent.VK_LEFT: 
				player.left = false;
				avatar.setIcon(pizzaImg);
				player.setCount(0);
				break;
			case KeyEvent.VK_RIGHT:
				player.right = false;
				avatar.setIcon(pizzaImg);
				player.setCount(0);
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