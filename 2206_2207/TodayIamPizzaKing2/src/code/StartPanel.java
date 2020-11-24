package code;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class StartPanel extends JPanel {
	private Image backgroundImage = new ImageIcon("images/main/startBackground.png").getImage(); // 배경이미지

	private ImageIcon startButtonImg = new ImageIcon("images/main/startBtn.png"); // 버튼들 이미지
	private ImageIcon settingButtonImg = new ImageIcon("images/main/settingBtn.png");
	private ImageIcon quitButtonImg = new ImageIcon("images/main/quitBtn.png");
	private ImageIcon rankButtonImg = new ImageIcon("images/main/RankBtn.png");
	private ImageIcon enteredStartButtonImg = new ImageIcon("images/main/enteredStartBtn.png"); // 버튼들 이미지
	private ImageIcon enteredSettingButtonImg = new ImageIcon("images/main/enteredSettingBtn.png");
	private ImageIcon enteredQuitButtonImg = new ImageIcon("images/main/enteredQuitBtn.png");
	private ImageIcon enteredRankButtonImg = new ImageIcon("images/main/enteredRankBtn.png");

	public JButton startButton = new JButton(startButtonImg); // 시작화면의 버튼들
	public JButton settingButton = new JButton(settingButtonImg);
	public JButton quitButton = new JButton(quitButtonImg);

	private JButton rankingButton = new JButton(rankButtonImg);
	private RankingDialog rankingDialog = new RankingDialog();

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

			public void mousePressed(MouseEvent e) { // 마우스가 눌렸을때
				if (Main.buttonEffect) {
					Music buttonClick = new Music("buttonClick1.mp3", false);
					buttonClick.start();
				}
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

			public void mousePressed(MouseEvent e) { // 마우스가 눌렸을때
				if (Main.buttonEffect) {
					Music buttonClick = new Music("buttonClick1.mp3", false);
					buttonClick.start();
				}
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

			public void mousePressed(MouseEvent e) { // 마우스가 눌렸을때
				if (Main.buttonEffect) {
					Music buttonClick = new Music("buttonClick1.mp3", false);
					buttonClick.start();
				}
				System.exit(0);
			}
		});
		this.add(quitButton);

		rankingButton.setVisible(true);
		rankingButton.setBounds(85, 580, 200, 81);
		rankingButton.setBorderPainted(false);
		rankingButton.setContentAreaFilled(false);
		rankingButton.addMouseListener(new MouseAdapter() {

			public void mouseEntered(MouseEvent e) {
				rankingButton.setIcon(enteredRankButtonImg);
			}

			public void mouseExited(MouseEvent e) {
				rankingButton.setIcon(rankButtonImg);
			}

			public void mousePressed(MouseEvent e) { // 마우스가 눌렸을때
				if (Main.buttonEffect) {
					Music buttonClick = new Music("buttonClick1.mp3", false);
					buttonClick.start();
				}
				rankingDialog.draw();
				rankingDialog.setVisible(true);
			}
		});
		this.add(rankingButton);
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), null);
	}
}
