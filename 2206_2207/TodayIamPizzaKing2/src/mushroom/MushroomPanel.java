package mushroom;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import code.Game;

public class MushroomPanel extends JPanel{

	private static final int LEFT = 0;
	private static final int RIGHT = 1;
	private Image backgroundImage = new ImageIcon("images/mushroom/mushroomBackground.png").getImage();
	Mushroom mushroom;
	JLabel score;
	
	public MushroomPanel(Game game) {
		setLayout(null);
		setBounds(0, 0, 1280, 720);
		
		
		mushroom = new Mushroom();
	
		
		score = new JLabel("000");
		score.setFont(new Font("³ª´®°íµñ ExtraBold", Font.BOLD, 30));
		score.setBounds(1200, 0, 500, 50);
		
		
		
		add(score);
		setVisible(true);
		
	}
	class Mushroom {
		private int x;
		private int y;
		
		public Mushroom() {
			ImageIcon basket = new ImageIcon("images/mushroom/basket.png");
			JLabel bas = new JLabel(basket);
			bas.setBounds(100,100, 300,200);
		}
	
	}
	
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), null);
		}
}
