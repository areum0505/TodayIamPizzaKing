package code;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.StringTokenizer;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import bag.SlotMachine;

public class ResultPanel extends JPanel {
	private Image backgroundImage = new ImageIcon("images/main/resultBack.png").getImage();
	private ImageIcon backButtonImg = new ImageIcon("images/main/backButton.png");

	// 피자 이미지
	private ImageIcon doughImg = new ImageIcon("images/result/dough.png");
	private ImageIcon sauceImg = new ImageIcon("images/result/sauce.png");
	private ImageIcon mushroomImg = new ImageIcon("images/result/mushroom.png");
	private ImageIcon paprikaImg = new ImageIcon("images/result/paprika.png");
	private ImageIcon onionImg = new ImageIcon("images/result/onion.png");
	private ImageIcon pepperoniImg = new ImageIcon("images/result/pepperoni.png");
	private ImageIcon cheeseImg = new ImageIcon("images/result/cheese.png");
	private ImageIcon colaImg = new ImageIcon("images/result/cola.png");

	private JButton firstButton;

	private JLabel dough, sauce, mush, paprika, onion, pepper, cheese; // 피자 재료들
	private JLabel cola;
	private JLabel money;

	private JLabel total;

	private JLabel title;

	private String[] name_a, pizza_a;
	private int[] score_a;
	private JLabel first, second, third;
	private JLabel fail_l;

	private int plusScore = 0;

	public ResultPanel(Game game) {
		setLayout(null);
		setBounds(0, 0, 1280, 720);

		paprika = new JLabel(paprikaImg);
		paprika.setBounds(570, 167, 350, 350);
		paprika.setVisible(true);
		add(paprika);

		onion = new JLabel(onionImg);
		onion.setBounds(570, 167, 350, 350);
		onion.setVisible(false);
		add(onion);

		pepper = new JLabel(pepperoniImg);
		pepper.setBounds(570, 167, 350, 350);
		pepper.setVisible(false);
		add(pepper);

		mush = new JLabel(mushroomImg);
		mush.setBounds(570, 167, 350, 350);
		mush.setVisible(false);
		add(mush);

		cheese = new JLabel(cheeseImg);
		cheese.setBounds(570, 167, 350, 350);
		cheese.setVisible(false);
		add(cheese);

		sauce = new JLabel(sauceImg);
		sauce.setBounds(570, 167, 350, 350);
		sauce.setVisible(false);
		add(sauce);

		dough = new JLabel(doughImg);
		dough.setBounds(570, 167, 350, 350);
		dough.setVisible(true);
		add(dough);

		cola = new JLabel(colaImg);
		cola.setBounds(890, 167, 85, 85);
		cola.setVisible(false);
		add(cola);

		total = new JLabel();
		total.setBounds(940, 160, 300, 100);
		total.setVisible(true);
		total.setFont(new Font("나눔바른고딕", Font.PLAIN, 35));
		total.setHorizontalAlignment(JLabel.CENTER);
		add(total);

		money = new JLabel("00000원");
		money.setBounds(940, 330, 300, 100);
		money.setFont(new Font("나눔바른고딕", Font.PLAIN, 65));
		money.setHorizontalAlignment(JLabel.CENTER);
		add(money);

		title = new JLabel();
		title.setBounds(550, 30, 700, 100);
		title.setFont(new Font("나눔바른고딕", Font.PLAIN, 50));
		title.setHorizontalAlignment(JLabel.CENTER);
		add(title);

		first = new JLabel();
		first.setBounds(20, 170, 490, 100);
		first.setFont(new Font("나눔바른고딕", Font.PLAIN, 36));
		first.setHorizontalAlignment(JLabel.CENTER);
		first.setVisible(true);
		add(first);

		second = new JLabel();
		second.setBounds(20, 290, 490, 100);
		second.setFont(new Font("나눔바른고딕", Font.PLAIN, 36));
		second.setHorizontalAlignment(JLabel.CENTER);
		second.setVisible(true);
		add(second);

		third = new JLabel();
		third.setBounds(20, 410, 490, 100);
		third.setFont(new Font("나눔바른고딕", Font.PLAIN, 36));
		third.setHorizontalAlignment(JLabel.CENTER);
		third.setVisible(true);
		add(third);

		fail_l = new JLabel();
		fail_l.setBounds(30, 560, 480, 100);
		fail_l.setHorizontalAlignment(JLabel.CENTER);
		fail_l.setFont(new Font("나눔바른고딕", Font.PLAIN, 30));
		fail_l.setVisible(false);
		fail_l.setHorizontalAlignment(JLabel.CENTER);
		add(fail_l);

		firstButton = new JButton();
		firstButton.setBounds(790, 564, 269, 102);
		firstButton.setVisible(true);
		firstButton.setBorderPainted(false);
		firstButton.setContentAreaFilled(false);
		firstButton.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (Main.buttonEffect) {
					Music buttonClick = new Music("buttonClick1.mp3", false);
					buttonClick.start();
				}
				setVisible(false);
				game.startPanel.setVisible(true);
			}
		});
		add(firstButton);
	}

	public void setplusScore(String s) {
		plusScore = Integer.parseInt(s);

		if (plusScore == 777) {
			plusScore = 10000;
		}
	}

	public void make(String text1, String text2, boolean cola_b) {
		String name = text1;
		String pizza = text2;
		int score = 0;

		try {
			BufferedReader br = new BufferedReader(new FileReader("bag.txt"));
			BufferedWriter bw = new BufferedWriter(new FileWriter("ranking.txt", true));

			String s = br.readLine();

			if (cola_b) {
				cola.setVisible(true);
			} else {
				cola.setVisible(false);
			}

			if (s == null) {
				sauce.setVisible(false);
				mush.setVisible(false);
				paprika.setVisible(false);
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
					paprika.setVisible(true);
					score += 2000;
				} else {
					paprika.setVisible(false);
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

			total.setText("<html> 피자 : " + score + "원<br> 콜라 : " + plusScore + "원</html>");

			score += plusScore;

			bw.write(name + "\t" + pizza + "\t" + score + "\n");

			br.close();
			bw.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// 랭킹 구하기(정렬)
		getRanking();

		title.setText(name + "의 " + pizza);

		// 점수
		money.setText(String.valueOf(score) + "원");

		// 순위(1위 ~ 3위)
		first.setText("<html>1위 " + name_a[0] + "의<br>" + pizza_a[0] + "은(는) " + score_a[0] + "원</html>");
		if (name_a.length > 1)
			second.setText("<html>2위 " + name_a[1] + "의<br>" + pizza_a[1] + "은(는) " + score_a[1] + "원</html>");
		else
			second.setText("");
		if (name_a.length > 2)
			third.setText("<html>3위 " + name_a[2] + "의<br>" + pizza_a[2] + "은(는) " + score_a[2] + "원</html>");
		else
			third.setText("");

		// 순위에 들었는지 확인
		boolean check = true;
		if (name_a.length > 3) {
			if (name_a[0].equals(name) && pizza_a[0].equals(pizza) && score_a[0] == score)
				check = false;
			else if (name_a[1].equals(name) && pizza_a[1].equals(pizza) && score_a[1] == score)
				check = false;
			else if (name_a[2].equals(name) && pizza_a[2].equals(pizza) && score_a[2] == score)
				check = false;
		} else {
			check = false;
		}

		if (check) {
			fail_l.setText("<html>" + name + "님은 <br>순위에 들지 못했습니다.</html>");
			fail_l.setVisible(true);
		}

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
			int length = parse.countTokens() / 3;
			name_a = new String[length];
			pizza_a = new String[length];
			score_a = new int[length];

			for (int i = 0; i < length; i++) {
				name_a[i] = parse.nextToken();
				pizza_a[i] = parse.nextToken();
				score_a[i] = Integer.valueOf(parse.nextToken());
			}

			String tempName = "", tempPizza = "";
			int tempScore = 0;
			for (int i = 0; i < score_a.length - 1; i++) {
				for (int j = i + 1; j < score_a.length; j++) {
					if (score_a[i] < score_a[j]) {
						tempName = name_a[i];
						tempPizza = pizza_a[i];
						tempScore = score_a[i];
						name_a[i] = name_a[j];
						pizza_a[i] = pizza_a[j];
						score_a[i] = score_a[j];
						name_a[j] = tempName;
						pizza_a[j] = tempPizza;
						score_a[j] = tempScore;
					}
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), null);
	}
}
