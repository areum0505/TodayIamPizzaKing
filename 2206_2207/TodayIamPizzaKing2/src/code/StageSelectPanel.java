package code;

import java.awt.Graphics;
import java.awt.Image;

import java.awt.Robot;
import java.awt.event.ActionEvent;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import bag.BagPanel;
import mushroom.MushroomExplain;

public class StageSelectPanel extends JPanel{	
	
	private Image backgroundImage = new ImageIcon("images/stage/stageSelectBackground.png").getImage();
	
	public JButton sauceBtn = new JButton();
	public JButton cheeseBtn = new JButton();
	public JButton onionBtn = new JButton();
	public JButton paprikaBtn = new JButton();
	public JButton pepperoniBtn = new JButton();
	public JButton mushroomBtn = new JButton();
	public JButton BagBtn = new JButton();
	public JButton BackBtn = new JButton();
	
	private MushroomExplain mushroomExplain;
	private BagPanel bagPanel; 
	
	public StageSelectPanel(Game game) {
		setLayout(null);
		setBounds(0, 0, 1280, 720);
		
		bagPanel = new BagPanel(game);
		bagPanel.setVisible(false);
		add(bagPanel);
		
		mushroomExplain = new MushroomExplain(game);
		mushroomExplain.setVisible(false);

		
		
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
				game.onionPanel.setFocusable(true);
				game.onionPanel.requestFocus();

			}
		});
		this.add(onionBtn);
		
		paprikaBtn.setVisible(true);
		paprikaBtn.setBounds(515, 64, 256, 136);
		paprikaBtn.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				game.stageSelectPanel.setVisible(false);
				game.paprikaPanel.setVisible(true);
				game.paprikaPanel.setFocusable(true);		
				game.paprikaPanel.requestFocus();
				game.paprikaPanel.startGame();
			}
		});
		this.add(paprikaBtn);
		
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
				mushroomExplain.setVisible(true);
				
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
		
		BackBtn.setVisible(true);
		BackBtn.setBounds(30, 30, 60, 60);
		BackBtn.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				game.stageSelectPanel.setVisible(false);
				game.startPanel.setVisible(true);
			}
		});
		this.add(BackBtn);
		
		sauceBtn.setBorderPainted(false);
		sauceBtn.setContentAreaFilled(false);
	
		cheeseBtn.setBorderPainted(false);
		cheeseBtn.setContentAreaFilled(false);
	
		onionBtn.setBorderPainted(false);
		onionBtn.setContentAreaFilled(false);
	
		paprikaBtn.setBorderPainted(false);
		paprikaBtn.setContentAreaFilled(false);
	
		pepperoniBtn.setBorderPainted(false);
		pepperoniBtn.setContentAreaFilled(false);
	
		mushroomBtn.setBorderPainted(false);
		mushroomBtn.setContentAreaFilled(false);
	
		BagBtn.setBorderPainted(false);
		BagBtn.setContentAreaFilled(false);
		
		BackBtn.setBorderPainted(false);
		BackBtn.setContentAreaFilled(false);
	
	}
	public void btnOn() {
		cheeseBtn.setVisible(true);
		onionBtn.setVisible(true);
		paprikaBtn.setVisible(true);
		pepperoniBtn.setVisible(true);
		mushroomBtn.setVisible(true);
		sauceBtn.setVisible(true);
		BagBtn.setVisible(true);
	}
	public void btnOff() {
		cheeseBtn.setVisible(false);
		onionBtn.setVisible(false);
		paprikaBtn.setVisible(false);
		pepperoniBtn.setVisible(false);
		mushroomBtn.setVisible(false);
		sauceBtn.setVisible(false);
		BagBtn.setVisible(false);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), null);
	}
}
