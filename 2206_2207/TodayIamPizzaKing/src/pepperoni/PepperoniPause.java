package pepperoni;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import code.Game;
import code.Main;
import code.Music;

public class PepperoniPause extends JPanel{
	Game game;
	
	private Image pauseImg = new ImageIcon("images/stage/pauseImg.png").getImage();
	
	JButton quitButton = new JButton();
	JButton continueButton = new JButton();
	
	public PepperoniPause(Game game) {
		setLayout(null);
		setBounds((1280/2)-(pauseImg.getWidth(null)/2) , (720/2)-(pauseImg.getHeight(null)/2), 800, 500);
		
		this.game = game;
		
		quitButton.setVisible(true);
		quitButton.setBounds(138, 300, 225, 100);
		quitButton.setBorderPainted(false);
		quitButton.setContentAreaFilled(false);
		quitButton.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (Main.buttonEffect) {
					Music buttonClick = new Music("buttonClick1.mp3", false);
					buttonClick.start();
				}
				game.pepperoniPanel.getTh().stop();
				game.pepperoniPanel.reset();
				game.stageSelectPanel.setVisible(true);
				game.pepperoniPanel.setVisible(false);
				setVisible(false);
			}
		});
		this.add(quitButton);
		
		continueButton.setVisible(true);
		continueButton.setBounds(437, 300, 225, 100);
		continueButton.setBorderPainted(false);
		continueButton.setContentAreaFilled(false);
		continueButton.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (Main.buttonEffect) {
					Music buttonClick = new Music("buttonClick1.mp3", false);
					buttonClick.start();
				}
				game.pepperoniPanel.getPepperoni().setPause(false);
				setVisible(false);
			}
		});
		this.add(continueButton);
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.drawImage(pauseImg, 0, 0, getWidth(), getHeight(), null);
	}
}
