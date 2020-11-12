package code;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class SettingPanel extends JPanel{
	private Image backgroundImage = new ImageIcon("images/main/setBackground.png").getImage();
	
	private ImageIcon backButtonImg = new ImageIcon("images/main/backButton.png");
	
	public JButton backButton = new JButton(backButtonImg);
	
	public SettingPanel(Game game) {
		setLayout(null);
		setBounds(0, 0, 1280, 720);
		
		backButton.setVisible(true);
		backButton.setBounds(10, 10, 64, 64);
		backButton.setBorderPainted(false);
		backButton.setContentAreaFilled(false);
		backButton.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) { // 마우스가 눌렸을때 
				game.settingPanel.setVisible(false);
				game.startPanel.setVisible(true);
			}
		});
		add(backButton);
		
		
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), null);
	}
}