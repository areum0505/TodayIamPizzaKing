package code;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

class StartPanel extends JPanel {
	private Image backgroundImage = new ImageIcon("images/main/startBackground.png").getImage();			// 배경이미지
	
	private ImageIcon startButtonImg = new ImageIcon("images/main/startBtn.png");			// 버튼들 이미지
	private ImageIcon settingButtonImg = new ImageIcon("images/main/settingBtn.png");
	private ImageIcon quitButtonImg = new ImageIcon("images/main/quitBtn.png");
	public JButton startButton = new JButton(startButtonImg);										// 시작화면의 버튼들
	public JButton settingButton = new JButton(settingButtonImg);
	public JButton quitButton = new JButton(quitButtonImg);
	
	public StartPanel(Game game) {
		setLayout(null);
		setBounds(0, 0, 1280, 720);

		startButton.setVisible(true);
		startButton.setBounds(953, 265, 300, 125);
		startButton.setBorderPainted(false);
		startButton.setContentAreaFilled(false);
		startButton.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) { // 마우스가 눌렸을때 
				game.startPanel.setVisible(false);
				game.stageSelectPanel.setVisible(true);
			}
		});
		this.add(startButton);
		
		settingButton.setVisible(true);
		settingButton.setBounds(953, 418, 300, 125);
		settingButton.setBorderPainted(false);
		settingButton.setContentAreaFilled(false);
		this.add(settingButton);
		
		quitButton.setVisible(true);
		quitButton.setBounds(953, 565, 300, 125);
		quitButton.setBorderPainted(false);
		quitButton.setContentAreaFilled(false);
		this.add(quitButton);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), null);
	}
}
