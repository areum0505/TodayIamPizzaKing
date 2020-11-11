package cheese;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import code.Game;

public class CheesePanel extends JPanel {
	private Image backgroundImage = new ImageIcon("images/stage/stage1Back.png").getImage();
	
	private Game game;
	
	public CheesePanel(Game game) {
		setLayout(null);
		setBounds(0, 0, 1280, 720);
		
		this.game = game;
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), null);
	}
}
