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
	
	public JLabel avatar;
	
	private Player player;
	
	private Exit exit;

	public SaucePanel(Game game) {
		setLayout(null);
		setBounds(0, 0, 1280, 720);
		
		addKeyListener(new MyKeyListener()); // 키 리스너 등록
		
		player = new Player();
		
		exit = new Exit();
		
		avatar = new JLabel(player.getImg());
		avatar.setVisible(true);
		avatar.setBounds(30, 23, player.getImg().getIconWidth(), player.getImg().getIconHeight());
		add(avatar);
	}
	
	public void startGame() {
		player.start();
	}
	
	class MyKeyListener extends KeyAdapter {
		@Override
		public void keyPressed(KeyEvent e) {
			int keyCode = e.getKeyCode(); // 상, 하, 좌, 우 키는 유니코드 키가 아님
			
			switch(keyCode) {
			case KeyEvent.VK_UP: 
				if(avatar.getY() > 23) {
					avatar.setLocation(avatar.getX(), avatar.getY()-143); 
					player.setX(avatar.getX()); player.setY(avatar.getY());
				}
				break;
			case KeyEvent.VK_DOWN: 
				if(avatar.getY() < 595) {
					avatar.setLocation(avatar.getX(), avatar.getY()+143);
					player.setX(avatar.getX()); player.setY(avatar.getY());
				}
				break;
			case KeyEvent.VK_LEFT: 
				
				break;
			case KeyEvent.VK_RIGHT:
				break;
			}
			
			avatar.getParent().repaint(); 	// 아바타의 위치가 변경되었으므로 다시 그리기
											// 아바타가 있는 패널에는 이전의 위치에 있었던 아바타를 지워야 하기 때문에
											// 아바타의 부모 패널에게 다시그리기를 지시함
		}
		@Override
		public void keyReleased(KeyEvent e) {
			
		}
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), null);
		g.drawImage(exit.getImg(), exit.getX(), exit.getY(), exit.getImg().getWidth(null), exit.getImg().getHeight(null), null);
	}
}