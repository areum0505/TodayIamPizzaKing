package code;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

class StartPanel extends JPanel {
	private Image backgroundImage = new ImageIcon("images/main/startBackground.png").getImage();			// ����̹���
	
	private ImageIcon startButtonImg = new ImageIcon("images/main/startBtn.png");			// ��ư�� �̹���
	private ImageIcon settingButtonImg = new ImageIcon("images/main/settingBtn.png");
	private ImageIcon quitButtonImg = new ImageIcon("images/main/quitBtn.png");
	private ImageIcon enteredStartButtonImg = new ImageIcon("images/main/enteredStartBtn.png");			// ��ư�� �̹���
	private ImageIcon enteredSettingButtonImg = new ImageIcon("images/main/enteredSettingBtn.png");
	private ImageIcon enteredQuitButtonImg = new ImageIcon("images/main/enteredQuitBtn.png");
	
	public JButton startButton = new JButton(startButtonImg);										// ����ȭ���� ��ư��
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
			public void mouseEntered(MouseEvent e) {
				startButton.setIcon(enteredStartButtonImg);
			}
			public void mouseExited(MouseEvent e) {
				startButton.setIcon(startButtonImg);
			}
			public void mousePressed(MouseEvent e) { // ���콺�� �������� 
				game.startPanel.setVisible(false);
				game.stageSelectPanel.setVisible(true);
			}
		});
		this.add(startButton);
		
		settingButton.setVisible(true);
		settingButton.setBounds(953, 418, 300, 125);
		settingButton.setBorderPainted(false);
		settingButton.setContentAreaFilled(false);
		settingButton.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				settingButton.setIcon(enteredSettingButtonImg);
			}
			public void mouseExited(MouseEvent e) {
				settingButton.setIcon(settingButtonImg);
			}
			public void mousePressed(MouseEvent e) { // ���콺�� �������� 
				game.startPanel.setVisible(false);
				game.settingPanel.setVisible(true);

			}
		});
		this.add(settingButton);
		
		quitButton.setVisible(true);
		quitButton.setBounds(953, 565, 300, 125);
		quitButton.setBorderPainted(false);
		quitButton.setContentAreaFilled(false);
		quitButton.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				quitButton.setIcon(enteredQuitButtonImg);
			}
			public void mouseExited(MouseEvent e) {
				quitButton.setIcon(quitButtonImg);
			}
			public void mousePressed(MouseEvent e) { // ���콺�� �������� 
				System.exit(0);
			}
		});
		this.add(quitButton);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), null);
	}
}