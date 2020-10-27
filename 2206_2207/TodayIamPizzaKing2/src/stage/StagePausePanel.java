package stage;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import code.Game;
import sauce.Beam;

public class StagePausePanel extends JPanel{
	Game game;
	
	private Image pauseImg = new ImageIcon("images/stage/pauseImg.png").getImage();
	
	JButton quitButton = new JButton();
	JButton continueButton = new JButton();
	
	public StagePausePanel(Game game) {
		setLayout(null);
		setBounds((1280/2)-(pauseImg.getWidth(null)/2) , (720/2)-(pauseImg.getHeight(null)/2), 800, 500);
		
		this.game = game;
		
		quitButton.setVisible(true);
		quitButton.setBounds(138, 300, 225, 100);
		quitButton.setBorderPainted(false);
		quitButton.setContentAreaFilled(false);
		quitButton.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				game.saucePanel.getPlayer().stop();
				for(Beam b : game.saucePanel.getBeam()) {
					b.setEmptyImg();
					b.setStop(true);
				}
				game.stageSelectPanel.setVisible(true);
				game.saucePanel.setVisible(false);
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
				game.saucePanel.getPlayer().setPause(false);
				for(Beam b : game.saucePanel.getBeam()) {
					b.setPause(false);
				}
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
