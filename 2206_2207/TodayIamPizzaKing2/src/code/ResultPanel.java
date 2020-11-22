package code;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.StringTokenizer;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ResultPanel extends JPanel {
	private Image backgroundImage = new ImageIcon("images/main/setBackground.png").getImage();
	private ImageIcon backButtonImg = new ImageIcon("images/main/backButton.png");

	// 피자 이미지
	private ImageIcon doughImg = new ImageIcon("images/character/dough.png");
	private ImageIcon sauceImg = new ImageIcon("images/character/sauce.png");
	private ImageIcon mushroomImg = new ImageIcon("images/character/mushroom.png");
	private ImageIcon paprikaImg = new ImageIcon("images/character/paprika.png");
	private ImageIcon onionImg = new ImageIcon("images/character/onion.png");
	private ImageIcon pepperoniImg = new ImageIcon("images/character/pepperoni.png");
	private ImageIcon cheeseImg = new ImageIcon("images/character/cheese.png");

	private JButton firstButton;

	private JLabel dough, sauce, mush, paprika, onion, pepper, cheese; // 피자 재료들
	private JLabel money, won; // 점수

	private String[] name_a;
	private int[] score_a;
	private JLabel rank; // 순위
	private JLabel first_l, second_l, third_l; // 1위 ~ 3위
	private JLabel first, second, third;

	public ResultPanel(Game game) {
		setLayout(null);
		setBounds(0, 0, 1280, 720);

		/*
		 * paprika = new JLabel(paprikaImg); paprika.setBounds(50, 50, 350, 350);
		 * paprika.setVisible(true); add(paprika);
		 */

		onion = new JLabel(onionImg);
		onion.setBounds(50, 50, 350, 350);
		onion.setVisible(false);
		add(onion);

		pepper = new JLabel(pepperoniImg);
		pepper.setBounds(50, 50, 350, 350);
		pepper.setVisible(false);
		add(pepper);

		mush = new JLabel(mushroomImg);
		mush.setBounds(50, 50, 350, 350);
		mush.setVisible(false);
		add(mush);

		cheese = new JLabel(cheeseImg);
		cheese.setBounds(50, 50, 350, 350);
		cheese.setVisible(false);
		add(cheese);

		sauce = new JLabel(sauceImg);
		sauce.setBounds(50, 50, 350, 350);
		sauce.setVisible(false);
		add(sauce);

		dough = new JLabel(doughImg);
		dough.setBounds(50, 50, 350, 350);
		dough.setVisible(true);
		add(dough);

		money = new JLabel("00000");
		money.setBounds(800, 65, 300, 100);
		money.setFont(new Font("나눔바른고딕", Font.PLAIN, 65));
		money.setHorizontalAlignment(JLabel.RIGHT);
		add(money);
		won = new JLabel("원");
		won.setBounds(1110, 65, 150, 100);
		won.setFont(new Font("나눔바른고딕", Font.PLAIN, 65));
		won.setVisible(true);
		add(won);

		rank = new JLabel("가장 비싼 피자 가게");
		rank.setBounds(750, 200, 500, 50);
		rank.setFont(new Font("나눔바른고딕", Font.PLAIN, 45));
		rank.setVisible(true);
		add(rank);

		first_l = new JLabel("1위");
		first_l.setBounds(630, 265, 100, 50);
		first_l.setFont(new Font("나눔바른고딕", Font.PLAIN, 40));
		first_l.setVisible(true);
		add(first_l);
		first = new JLabel();
		first.setBounds(710, 265, 500, 50);
		first.setFont(new Font("나눔바른고딕", Font.PLAIN, 40));
		first.setVisible(true);
		add(first);

		second_l = new JLabel("2위");
		second_l.setBounds(630, 320, 100, 50);
		second_l.setFont(new Font("나눔바른고딕", Font.PLAIN, 40));
		second_l.setVisible(true);
		add(second_l);
		second = new JLabel();
		second.setBounds(710, 320, 500, 50);
		second.setFont(new Font("나눔바른고딕", Font.PLAIN, 40));
		second.setVisible(true);
		add(second);

		third_l = new JLabel("3위");
		third_l.setBounds(630, 375, 100, 50);
		third_l.setFont(new Font("나눔바른고딕", Font.PLAIN, 40));
		third_l.setVisible(true);
		add(third_l);
		third = new JLabel();
		third.setBounds(710, 375, 500, 50);
		third.setFont(new Font("나눔바른고딕", Font.PLAIN, 40));
		third.setVisible(true);
		add(third);

		firstButton = new JButton("처음으로");
		firstButton.setBounds(1030, 610, 200, 75);
		firstButton.setVisible(true);
		firstButton.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				
				setVisible(false);
				game.startPanel.setVisible(true);
			}
		});
		add(firstButton);
	}

	public void make(String text) {
		String name = text;
		int score = 0;

		try {
			BufferedReader br = new BufferedReader(new FileReader("bag.txt"));
			BufferedWriter bw = new BufferedWriter(new FileWriter("ranking.txt", true));

			String s = br.readLine();

			if (s == null) {
				sauce.setVisible(false);
				mush.setVisible(false);
				// paprika.setVisible(false);
				onion.setVisible(false);
				pepper.setVisible(false);
				cheese.setVisible(false);
			} else {
				if (s.contains("소스")) {
					sauce.setVisible(true);
					score += 2000;
				} else {
					sauce.setVisible(false);
				}
				if (s.contains("버섯")) {
					mush.setVisible(true);
					score += 1500;
				} else {
					mush.setVisible(false);
				}
				if (s.contains("파프리카")) {
					// paprika.setVisible(true);
					score += 2000;
				} else {
					// paprika.setVisible(false);
				}
				if (s.contains("양파")) {
					onion.setVisible(true);
					score += 1000;
				} else {
					onion.setVisible(false);
				}
				if (s.contains("페퍼로니")) {
					pepper.setVisible(true);
					score += 1500;
				} else {
					pepper.setVisible(false);
				}
				if (s.contains("치즈")) {
					cheese.setVisible(true);
					score += 1000;
				} else {
					cheese.setVisible(false);
				}
			}

			if (score == 9000) {
				score += 1000;
			}

			bw.write(name + "\t" + score + "\n");

			br.close();
			bw.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		getRanking();

		money.setText(String.valueOf(score));

		first.setText(name_a[0] + " 의 피자가게 - " + score_a[0] + "원");
		if (name_a.length > 1)
			second.setText(name_a[1] + " 의 피자가게 - " + score_a[1] + "원");
		else
			second.setText("");
		if (name_a.length > 2)
			third.setText(name_a[2] + " 의 피자가게 - " + score_a[2] + "원");
		else
			third.setText("");
		
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter("bag.txt"));
			bw.write("");
			bw.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	public void getRanking() {
		try {
			BufferedReader br = new BufferedReader(new FileReader("ranking.txt"));

			String csvStr = "";
			String tmpStr = "";

			do {
				tmpStr = br.readLine();
				if (tmpStr != null) {
					csvStr += tmpStr + "\t";
				}
			} while (tmpStr != null);

			StringTokenizer parse = new StringTokenizer(csvStr, "\t");
			int length = parse.countTokens() / 2;
			name_a = new String[length];
			score_a = new int[length];

			for (int i = 0; i < length; i++) {
				name_a[i] = parse.nextToken();
				score_a[i] = Integer.valueOf(parse.nextToken());
			}

			String tempName = "";
			int tempScore = 0;
			for (int i = 0; i < score_a.length - 1; i++) {
				for (int j = i + 1; j < score_a.length; j++) {
					if (score_a[i] < score_a[j]) {
						tempName = name_a[i];
						tempScore = score_a[i];
						name_a[i] = name_a[j];
						score_a[i] = score_a[j];
						name_a[j] = tempName;
						score_a[j] = tempScore;
					}
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*
	 * @Override public void paintComponent(Graphics g) { super.paintComponent(g);
	 * g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), null); }
	 */
}
