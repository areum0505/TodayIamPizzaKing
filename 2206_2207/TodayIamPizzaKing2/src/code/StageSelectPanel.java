package code;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class StageSelectPanel extends JPanel{
	private Image backgroundImage = new ImageIcon("images/main/stageSelectBackground.png").getImage();
	
	public StageSelectPanel(Game game) {
		setLayout(null);
		setBounds(0, 0, 1280, 720);
	
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), null);
	}
}
