package pepperoni;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import code.Game;

public class PepperoniPanel extends JPanel{
	private Game game;
	
	private Pepperoni pepperoni;
	
	private Image backgroundImage = new ImageIcon("images/stage/stage4Back.png").getImage();

	public PepperoniPanel(Game game) {
		setLayout(null);
		setBounds(0, 0, 1280, 720);
		
		this.game = game;
		
		addKeyListener(new MyKeyListener());
	}
	
	public void startGame() {
		pepperoni = new Pepperoni();
		add(pepperoni);
		Thread th = new Thread(pepperoni);
		th.start();
	}
	
	class MyKeyListener extends KeyAdapter {
		@Override
		public void keyPressed(KeyEvent e) {
			int keyCode = e.getKeyCode(); // 상, 하, 좌, 우 키는 유니코드 키가 아님
			
			switch(keyCode) {
			case KeyEvent.VK_SPACE:
				pepperoni.drop();
				break;
			}
		}
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), null);
	}
}