package onion;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import code.Game;

public class OnionSuccessPanel extends JPanel{
	
	private Image pauseImg = new ImageIcon("images/stage/pauseImg.png").getImage();
	
	public OnionSuccessPanel(Onion onion) {
		// TODO Auto-generated constructor stub
		setLayout(null);
		setBounds((1280/2)-(pauseImg.getWidth(null)/2) , (720/2)-(pauseImg.getHeight(null)/2), 800, 500);
		
	}
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.drawImage(pauseImg, 0, 0, getWidth(), getHeight(), null);
	}
}
