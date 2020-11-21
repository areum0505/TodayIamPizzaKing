package code;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ResultPanel extends JPanel {
	private Image backgroundImage = new ImageIcon("images/main/setBackground.png").getImage();
	private ImageIcon backButtonImg = new ImageIcon("images/main/backButton.png");
	
	private ImageIcon doughImg = new ImageIcon("images/character/dough.png");
	private ImageIcon sauceImg = new ImageIcon("images/character/sauce.png");
	private ImageIcon mushroomImg = new ImageIcon("images/character/mushroom.png");
	private ImageIcon paprikaImg = new ImageIcon("images/character/paprika.png");
	private ImageIcon onionImg = new ImageIcon("images/character/onion.png");
	private ImageIcon pepperoniImg = new ImageIcon("images/character/pepperoni.png");
	private ImageIcon cheeseImg = new ImageIcon("images/character/cheese.png");

	private JButton backButton = new JButton(backButtonImg);
	
	private JLabel dough, sauce, mush, paprika, onion, pepper, cheese;
	private JLabel money, won;

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
		money.setFont(new Font("나눔바른고딕", Font.PLAIN, 45));
		money.setHorizontalAlignment(JLabel.RIGHT);
		add(money);
		won = new JLabel("원");
		won.setBounds(1110, 65, 150, 100);
		won.setFont(new Font("나눔바른고딕", Font.PLAIN, 45));
		won.setVisible(true);
		add(won);
	}
	
	public void make() {
		int score = 0;
		
		try {
			BufferedReader br = new BufferedReader(new FileReader("bag.txt"));
			String s = br.readLine();

			if(s.contains("소스")) {
				sauce.setVisible(true);
				score += 2000;
			}
			if(s.contains("버섯")) {
				mush.setVisible(true);
				score += 1500;
			}
			if(s.contains("파프리카")) {
				// paprika.setVisible(true);
				score += 2000;
			}
			if(s.contains("양파")) {
				onion.setVisible(true);
				score += 1000;
			}
			if(s.contains("페퍼로니")) {
				pepper.setVisible(true);
				score += 1500;
			}
			if(s.contains("치즈")) {
				cheese.setVisible(true);
				score += 1000;
			}
			
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		if(score == 9000) {
			score += 1000;
		}
		
		money.setText(String.valueOf(score));
	}

	/*
	 * @Override public void paintComponent(Graphics g) { super.paintComponent(g);
	 * g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), null); }
	 */
}
