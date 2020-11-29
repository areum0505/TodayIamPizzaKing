package code;

import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

public class SettingPanel extends JPanel {
	private Image backgroundImage = new ImageIcon("images/main/setBackground.png").getImage();

	private ImageIcon backButtonImg = new ImageIcon("images/main/backButton.png");
	private ImageIcon onButtonImg = new ImageIcon("images/main/onbutton.png");
	private ImageIcon offButtonImg = new ImageIcon("images/main/offbutton.png");

	private JButton backButton;

	private JToggleButton musicButton, effectButton;

	private JButton cursor1, cursor2, cursor3;
	private JButton resetBag, resetRanking;

	public SettingPanel(Game game) {
		setLayout(null);
		setBounds(0, 0, 1280, 720);

		backButton = new JButton(backButtonImg);
		backButton.setVisible(true);
		backButton.setBounds(10, 10, 64, 64);
		backButton.setBorderPainted(false);
		backButton.setContentAreaFilled(false);
		backButton.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) { // 마우스가 눌렸을때
				if (Main.buttonEffect) {
					Music buttonClick = new Music("buttonClick1.mp3", false);
					buttonClick.start();
				}
				game.settingPanel.setVisible(false);
				game.startPanel.setVisible(true);
			}
		});
		add(backButton);

		musicButton = new JToggleButton();
		musicButton.setBounds(721, 100, 70, 70);
		musicButton.setIcon(onButtonImg);
		musicButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (musicButton.isSelected()) {
					Main.introMusic.stop();
					musicButton.setIcon(offButtonImg);
				} else {
					Main.introMusic = new Music("introMusic.mp3", true);
					Main.introMusic.start();
					musicButton.setIcon(onButtonImg);
				}
			}
		});
		add(musicButton);
		effectButton = new JToggleButton();
		effectButton.setBounds(721, 215, 70, 70);
		effectButton.setIcon(onButtonImg);
		effectButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (effectButton.isSelected()) {
					Main.buttonEffect = false;
					effectButton.setIcon(offButtonImg);
				} else {
					Main.buttonEffect = true;
					effectButton.setIcon(onButtonImg);
				}
			}
		});
		add(effectButton);

		cursor1 = new JButton();
		cursor1.setBounds(590, 335, 70, 70);
		cursor1.setBorderPainted(false);
		cursor1.setContentAreaFilled(false);
		cursor1.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				Toolkit tk = Toolkit.getDefaultToolkit();
				Image cursorimage = tk.getImage("images/main/cursor1.png");
				Point point = new Point(20, 20);
				Cursor cursor = tk.createCustomCursor(cursorimage, point, "haha");
				game.setCursor(cursor);
			}
		});
		add(cursor1);
		cursor2 = new JButton();
		cursor2.setBounds(721, 335, 70, 70);
		cursor2.setBorderPainted(false);
		cursor2.setContentAreaFilled(false);
		cursor2.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				Toolkit tk = Toolkit.getDefaultToolkit();
				Image cursorimage = tk.getImage("images/main/cursor2.png");
				Point point = new Point(20, 20);
				Cursor cursor = tk.createCustomCursor(cursorimage, point, "haha");
				game.setCursor(cursor);
			}
		});
		add(cursor2);
		cursor3 = new JButton();
		cursor3.setBounds(852, 335, 70, 70);
		cursor3.setBorderPainted(false);
		cursor3.setContentAreaFilled(false);
		cursor3.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				Toolkit tk = Toolkit.getDefaultToolkit();
				Image cursorimage = tk.getImage("images/main/cursor3.png");
				Point point = new Point(20, 20);
				Cursor cursor = tk.createCustomCursor(cursorimage, point, "haha");
				game.setCursor(cursor);
			}
		});
		add(cursor3);

		resetBag = new JButton();
		resetBag.setBounds(832, 492, 152, 65);
		resetBag.setBorderPainted(false);
		resetBag.setContentAreaFilled(false);
		resetBag.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				int result = JOptionPane.showConfirmDialog(null, "정말로 초기화하시겠습니까?", "가방 초기화", JOptionPane.YES_NO_OPTION);

				if (result == JOptionPane.CLOSED_OPTION || result == JOptionPane.NO_OPTION) {
					return;
				}

				try {
					BufferedWriter bw = new BufferedWriter(new FileWriter("bag.txt"));
					bw.write("");
					bw.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		add(resetBag);
		resetRanking = new JButton();
		resetRanking.setBounds(832, 567, 152, 65);
		resetRanking.setBorderPainted(false);
		resetRanking.setContentAreaFilled(false);
		resetRanking.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				int result = JOptionPane.showConfirmDialog(null, "정말로 초기화하시겠습니까?", "랭킹 초기화", JOptionPane.YES_NO_OPTION);

				if (result == JOptionPane.CLOSED_OPTION || result == JOptionPane.NO_OPTION) {
					return;
				}

				try {
					BufferedWriter bw = new BufferedWriter(new FileWriter("ranking.txt"));
					bw.write("");
					bw.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		add(resetRanking);
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), null);
	}
}