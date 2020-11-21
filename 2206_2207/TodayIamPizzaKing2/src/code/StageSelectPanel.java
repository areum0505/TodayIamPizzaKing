package code;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import bag.BagPanel;

public class StageSelectPanel extends JPanel{	
	private Image backgroundImage = new ImageIcon("images/stage/stageSelectBackground.png").getImage();
	
	public JButton sauceBtn = new JButton();
	public JButton cheeseBtn = new JButton();
	public JButton onionBtn = new JButton();
	public JButton pimangBtn = new JButton();
	public JButton pepperoniBtn = new JButton();
	public JButton mushroomBtn = new JButton();
	public JButton BagBtn = new JButton();
	
	private BagPanel bagPanel; 
	
	public StageSelectPanel(Game game) {
		setLayout(null);
		setBounds(0, 0, 1280, 720);
		
		bagPanel = new BagPanel(game);
		bagPanel.setVisible(false);
		add(bagPanel);
		
		cheeseBtn.setVisible(true);
		cheeseBtn.setBounds(979, 558, 202, 116);
		cheeseBtn.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				game.stageSelectPanel.setVisible(false);
				game.cheesePanel.setVisible(true);
				game.cheesePanel.setFocusable(true);		
				game.cheesePanel.requestFocus();
				game.cheesePanel.startGame();
			}
		});
		this.add(cheeseBtn);
		
		onionBtn.setVisible(true);
		onionBtn.setBounds(857, 131, 202, 156);
		onionBtn.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				game.stageSelectPanel.setVisible(false);
				game.onionPanel.setVisible(true);
				
			}
		});
		this.add(onionBtn);
		
		pimangBtn.setVisible(true);
		pimangBtn.setBounds(515, 64, 256, 136);
		pimangBtn.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				game.stageSelectPanel.setVisible(false);
				game.paprikaPanel.setVisible(true);
				game.paprikaPanel.requestFocus();
			}
		});
		this.add(pimangBtn);
		
		pepperoniBtn.setVisible(true);
		pepperoniBtn.setBounds(960, 353, 202, 133);
		pepperoniBtn.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				game.stageSelectPanel.setVisible(false);
				game.pepperoniPanel.setVisible(true);
				game.pepperoniPanel.setFocusable(true);		
				game.pepperoniPanel.requestFocus();
				game.pepperoniPanel.startGame();
			}
		});
		this.add(pepperoniBtn);
		
		mushroomBtn.setVisible(true);
		mushroomBtn.setBounds(176, 157, 222, 160);
		mushroomBtn.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				game.stageSelectPanel.setVisible(false);
				game.mushroomPanel.setVisible(true);
				game.mushroomPanel.setFocusable(true);	
				game.mushroomPanel.requestFocus();
				game.mushroomPanel.startGame();
			}
		});
		this.add(mushroomBtn);
		
		sauceBtn.setVisible(true);
		sauceBtn.setBounds(103, 394, 202, 266);
		sauceBtn.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				game.stageSelectPanel.setVisible(false);
				game.saucePanel.setVisible(true);
				game.saucePanel.setFocusable(true);		
				game.saucePanel.requestFocus();
				game.saucePanel.startGame();
			}
		});
		this.add(sauceBtn);
		
		BagBtn.setVisible(true);
		BagBtn.setBounds(482, 273, 315, 447);
		BagBtn.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				bagPanel.check();
				bagPanel.setVisible(true);
			}
		});
		this.add(BagBtn);
		
		sauceBtn.setBorderPainted(false);
		sauceBtn.setContentAreaFilled(false);
	
		cheeseBtn.setBorderPainted(false);
		cheeseBtn.setContentAreaFilled(false);
	
		onionBtn.setBorderPainted(false);
		onionBtn.setContentAreaFilled(false);
	
		pimangBtn.setBorderPainted(false);
		pimangBtn.setContentAreaFilled(false);
	
		pepperoniBtn.setBorderPainted(false);
		pepperoniBtn.setContentAreaFilled(false);
	
		mushroomBtn.setBorderPainted(false);
		mushroomBtn.setContentAreaFilled(false);
	
		BagBtn.setBorderPainted(false);
		BagBtn.setContentAreaFilled(false);
	
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), null);
	}
}
