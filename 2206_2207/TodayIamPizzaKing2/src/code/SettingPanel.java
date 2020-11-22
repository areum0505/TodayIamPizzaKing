package code;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

public class SettingPanel extends JPanel{
	private Image backgroundImage = new ImageIcon("images/main/setBackground.png").getImage();
	
	private ImageIcon backButtonImg = new ImageIcon("images/main/backButton.png");
	private ImageIcon onButtonImg = new ImageIcon("images/main/onbutton.png");
	private ImageIcon offButtonImg = new ImageIcon("images/main/offbutton.png");
	
	private JLabel settingLabel;
	
	private JButton backButton;
	
	private JLabel musicLabel;
	private JToggleButton musicButton;
	
	public SettingPanel(Game game) {
		setLayout(null);
		setBounds(0, 0, 1280, 720);
		
		settingLabel = new JLabel("����");
		settingLabel.setBounds(95, 10, 150, 64);
		settingLabel.setFont(new Font("�����ٸ�����", Font.PLAIN, 45));
		settingLabel.setForeground(Color.BLACK);
		add(settingLabel);
		
		backButton = new JButton(backButtonImg);
		backButton.setVisible(true);
		backButton.setBounds(10, 10, 64, 64);
		backButton.setBorderPainted(false);
		backButton.setContentAreaFilled(false);
		backButton.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) { // ���콺�� �������� 
				Music buttonClick = new Music("buttonClick1.mp3", false);
				buttonClick.start();
				game.settingPanel.setVisible(false);
				game.startPanel.setVisible(true);
			}
		});
		add(backButton);
		
		musicLabel = new JLabel("�������");
		musicLabel.setBounds(850, 135, 200, 75);
		musicLabel.setFont(new Font("�����ٸ�����", Font.PLAIN, 35));
		musicLabel.setForeground(Color.BLACK);
		add(musicLabel);
		musicButton = new JToggleButton(onButtonImg);
		musicButton.setBounds(1050, 135, 75, 75);
		musicButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(musicButton.isSelected()) {
					Main.introMusic.stop();
					musicButton.setIcon(offButtonImg);
				} else {
					Main.introMusic = new Music("introMusic.mp3",true);
			        Main.introMusic.start();
			        musicButton.setIcon(onButtonImg);
				}
			}
		});
		add(musicButton);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), null);
	}
}