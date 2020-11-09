package onion;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class OnionFailPanel extends JPanel {
	
	private Image pauseImg = new ImageIcon("images/stage/pauseImg.png").getImage();
	
	public OnionFailPanel(Onion onion) {
		
		setLayout(null);
		setBounds((1280/2)-(pauseImg.getWidth(null)/2) , (720/2)-(pauseImg.getHeight(null)/2), 800, 500);
		JLabel result = new JLabel("¾È³çÇÏ¼¼¿ä");
		result.setFont(new Font("³ª´®°íµñ ExtraBold", Font.BOLD, 30));
		result.setBounds(450,200, 200, 50);
		add(result);
		setBackground(Color.blue);
	}
	/*
	 * @Override public void paintComponent(Graphics g) { super.paintComponent(g);
	 * 
	 * g.drawImage(pauseImg, 0, 0, getWidth(), getHeight(), null); }
	 */
}
