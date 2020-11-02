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
	
	public JButton sauceBtn = new JButton();
	public JButton cheeseBtn = new JButton();
	public JButton onionBtn = new JButton();
	public JButton pimangBtn = new JButton();
	public JButton pepperoniBtn = new JButton();
	public JButton mushroomBtn = new JButton();
	public JButton BagBtn = new JButton();
	
	public StageSelectPanel(Game game) {
		setLayout(null);
		setBounds(0, 0, 1280, 720);
		
		sauceBtn.setVisible(true);
		sauceBtn.setBounds(165, 155, 275, 170);
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
		
		sauceBtn.setContentAreaFilled(false);
		
		cheeseBtn.setVisible(true);
		cheeseBtn.setBounds(520, 45, 275, 160);
		cheeseBtn.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				
			}
		});
		this.add(cheeseBtn);
		
		onionBtn.setVisible(true);
		onionBtn.setBounds(875, 145, 270, 195);
		onionBtn.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				game.stageSelectPanel.setVisible(false);
				game.onionMainPanel.setVisible(true);
				
			}
		});
		this.add(onionBtn);
		
		pimangBtn.setVisible(true);
		pimangBtn.setBounds(155, 415, 265, 165);
		pimangBtn.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				
			}
		});
		this.add(pimangBtn);
		
		pepperoniBtn.setVisible(true);
		pepperoniBtn.setBounds(520, 505, 270, 165);
		pepperoniBtn.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				
			}
		});
		this.add(pepperoniBtn);
		
		mushroomBtn.setVisible(true);
		mushroomBtn.setBounds(875, 430, 250, 155);
		mushroomBtn.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				
			}
		});
		this.add(mushroomBtn);
		
		BagBtn.setVisible(true);
		BagBtn.setBounds(520, 270, 275, 173);
		BagBtn.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				
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
