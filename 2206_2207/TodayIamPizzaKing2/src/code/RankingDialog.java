package code;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class RankingDialog extends JDialog {
	private ImageIcon backgroundImage = new ImageIcon("images/main/ranking.png");
	private ImageIcon first = new ImageIcon("images/main/first.png");
	private ImageIcon second = new ImageIcon("images/main/second.png");
	private ImageIcon third = new ImageIcon("images/main/third.png");

	private JPanel p;

	private JLabel back;

	private JScrollPane scrollPane;
	private JPanel outer;

	private String[] name, pizza;
	private int[] score;
	
	public RankingDialog() {
		setTitle("Ranking");
		setSize(900, 600);
		setLocationRelativeTo(null);
		setModal(true);
		setResizable(false);
		setLayout(null);
		
		p = new JPanel();
		p.setLayout(null);
		p.setBounds(0, 0, 900, 600);
		
		add(p);
	}

	public void draw() {
		getRanking();		

		int y = 10;
		
		back = new JLabel(backgroundImage);
		back.setBounds(0, 0, 900, 600);
		p.add(back);
		
		outer = new JPanel();
		outer.setLayout(null);

		for (int i = 0; i < name.length; i++) {
			JPanel panel = new JPanel(new BorderLayout());
			panel.setBounds(25, y, 800, 50);
			panel.setBackground(Color.WHITE);

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
			panel.add(rank, BorderLayout.WEST);

			JLabel label = new JLabel();
			label.setFont(new Font("³ª´®¹Ù¸¥°íµñ", Font.PLAIN, 35));
			label.setText(name[i] + "ÀÇ " + pizza[i] + " - " + score[i] + "¿ø");
			panel.add(label, BorderLayout.EAST);

			outer.add(panel);

			y += 100;
		}
		outer.setPreferredSize(new Dimension(800, y));
		outer.setBackground(Color.WHITE);
		scrollPane = new JScrollPane(outer, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setBounds(0, 115, 894, 450);
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
			name = new String[length];
			pizza = new String[length];
			score = new int[length];

			for (int i = 0; i < length; i++) {
				name[i] = parse.nextToken();
				pizza[i] = parse.nextToken();
				score[i] = Integer.valueOf(parse.nextToken());
			}

			String tempName = "", tempPizza = "";
			int tempScore = 0;
			for (int i = 0; i < score.length - 1; i++) {
				for (int j = i + 1; j < score.length; j++) {
					if (score[i] < score[j]) {
						tempName = name[i];
						tempPizza = pizza[i];
						tempScore = score[i];
						name[i] = name[j];
						pizza[i] = pizza[j];
						score[i] = score[j];
						name[j] = tempName;
						pizza[j] = tempPizza;
						score[j] = tempScore;
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
