package sauce;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import code.Game;

public class SaucePanel extends JPanel{
	private Game game;
	
	public SaucePausePanel pausePanel;
	
	private Image backgroundImage = new ImageIcon("images/stage/stage6Back1.png").getImage();
	private ImageIcon pizzaImg =  new ImageIcon("images/character/pizza.png");
	
	public JLabel avatar;
	private Player player;
	private ArrayList<Beam> beamList;	
	private Exit exit = new Exit();

	public SaucePanel(Game game) {
		setLayout(null);
		setBounds(0, 0, 1280, 720);
		
		this.game = game;
		
		beamList = new ArrayList<>();
		
		pausePanel = new SaucePausePanel(game);
		add(pausePanel);
		pausePanel.setVisible(false);
				
		avatar = new JLabel(pizzaImg);
		avatar.setVisible(true);
		avatar.setBounds(30, 11, 92, 120);
		add(avatar);
		
		addKeyListener(new MyKeyListener()); // 키 리스너 등록
	}
	
	public void startGame() {
		for(int i = 0; i < 3; i ++) {
			Beam b = new Beam("horizontal");
			beamList.add(b);
			add(b);
		}
		for(int i = 3; i < 8; i ++) {
			Beam b = new Beam("vertical");
			beamList.add(b);
			add(b);
		}
		
		for(Beam b : beamList) {
			b.setBeamList(beamList);
		}
		
		exit.changeY();
		
		player = new Player(avatar, exit.getExitY(), game);
		player.setBeamList(beamList);
		player.setX(30); player.setY(11);
		player.start();

		for(Beam b : beamList) {
			Thread th = new Thread(b);
			b.setPlayer(player);
			th.start();
		}
	}
	
	public Player getPlayer() {
		return player;
	}
	
	public ArrayList<Beam> getBeam() {
		return beamList;
	}
	
	class MyKeyListener extends KeyAdapter {
		@Override
		public void keyPressed(KeyEvent e) {
			int keyCode = e.getKeyCode(); // 상, 하, 좌, 우 키는 유니코드 키가 아님
			
			if(player.isAlive()) {
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
					break;
				case KeyEvent.VK_ESCAPE:
					pausePanel.setVisible(true);
					player.setPause(true);
					for(Beam b : beamList) {
						b.setPause(true);
					}
					break;
				}
			}
		}
		@Override
		public void keyReleased(KeyEvent e) {
			int keyCode = e.getKeyCode(); // 상, 하, 좌, 우 키는 유니코드 키가 아님
			
			switch(keyCode) {
			case KeyEvent.VK_LEFT: 
				player.left = false;
				if(player.isAlive()) 
					avatar.setIcon(pizzaImg);
				player.setCount(0);
				break;
			case KeyEvent.VK_RIGHT:
				player.right = false;
				if(player.isAlive()) 
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
		g.drawImage(exit.getImg(), exit.getX(), exit.getY(), 85, 115, null);
	}
}