package code;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.ScrollPane;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.StringTokenizer;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class RankingDialog extends JDialog {
	private ImageIcon backgroundImage = new ImageIcon("images/main/ranking.png");
	private ImageIcon first = new ImageIcon("images/main/first.png");
	private ImageIcon second = new ImageIcon("images/main/second.png");
	private ImageIcon third = new ImageIcon("images/main/third.png");

	private JPanel p;

	private JLabel back;

	private ScrollPane scrollPane;
	private JPanel outer;

	private String[] name_a, pizza_a;
	private int[] score_a;

	public RankingDialog() {
		setTitle("Ranking");
		setSize(900, 600);
		setLocationRelativeTo(null);
		setModal(true);
		setResizable(false);

		p = new JPanel();

		p.setLayout(null);
		p.setBounds(0, 0, 900, 600);

		back = new JLabel(backgroundImage);
		back.setBounds(0, 0, 900, 600);
		p.add(back);

		scrollPane = new ScrollPane();
		scrollPane.setBounds(9, 115, 872, 435);
		outer = new JPanel();
		outer.setLayout(null);

		add(p);
		p.setVisible(true);
	}

	public void draw() {
		getRanking();

		int y = 10;

		for (int i = 0; i < name_a.length; i++) {
			JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
			panel.setBounds(10, y, 800, 50);

			JLabel rank = new JLabel();
			switch (i) {
			case 0:
				rank.setIcon(first);
				break;
			case 1:
				rank.setIcon(second);
				break;
			case 2:
				rank.setIcon(third);
				break;
			default:
				rank.setText((i + 1) + "À§");
			}
			rank.setFont(new Font("³ª´®¹Ù¸¥°íµñ", Font.PLAIN, 35));
			panel.add(rank);

			JLabel label = new JLabel();
			label.setFont(new Font("³ª´®¹Ù¸¥°íµñ", Font.PLAIN, 35));
			label.setText(name_a[i] + "ÀÇ " + pizza_a[i] + " - " + score_a[i] + "¿ø");
			panel.add(label);

			outer.add(panel);

			y += 100;
		}

		scrollPane.add(outer);
		p.add(scrollPane);
		p.add(back);
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
}
