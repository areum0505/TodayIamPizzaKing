package mushroom;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import code.Game;

public class MushroomPause extends JPanel{
	Game game;
	
	private Image pauseImg = new ImageIcon("images/stage/pauseImg.png").getImage();
	
	JButton quitButton = new JButton();
	JButton continueButton = new JButton();
	
	public MushroomPause(Game game) {
		setLayout(null);
		setBounds((1280/2)-(pauseImg.getWidth(null)/2) , (720/2)-(pauseImg.getHeight(null)/2), 800, 500);
		
		this.game = game;
		
		quitButton.setVisible(true);
		quitButton.setBounds(138, 300, 225, 100);
		quitButton.setBorderPainted(false);
		quitButton.setContentAreaFilled(false);
		quitButton.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				game.stageSelectPanel.setVisible(true);
				game.mushroomPanel.setVisible(false);
				game.mushroomPanel.endGame();
				
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
				
				game.mushroomPanel.pizzaMan.setPause(false);
				
				for (FriendMush f_Mush : game.mushroomPanel.f_MushList) {
					f_Mush.setPause(false);

				}
				for (EnemyMush e_Mush : game.mushroomPanel.e_MushList) {
					e_Mush.setPause(false);
					
				}
				for (HeartMinusMush hm_Mush : game.mushroomPanel.hm_MushList) {
					hm_Mush.setPause(false);
					
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
