package bag;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import code.Game;

public class BagPanel extends JPanel {
	private Image backgroundImage = new ImageIcon("images/stage/endImg.png").getImage();

	private ImageIcon backButtonImg = new ImageIcon("images/main/backButton.png");
	private ImageIcon sauceImg = new ImageIcon("images/main/sauce.png");
	private ImageIcon mushImg = new ImageIcon("images/main/mushroom.png");
	private ImageIcon papImg = new ImageIcon("images/main/paprika.png");
	private ImageIcon onionImg = new ImageIcon("images/main/onion.png");
	private ImageIcon pepperImg = new ImageIcon("images/main/pepperoni.png");
	private ImageIcon cheeseImg = new ImageIcon("images/main/cheese.png");

	private JButton backButton;
	private JButton makeButton;

	private JLabel sauceLabel, mushLabel, papLabel, onionLabel, pepperLabel, cheeseLabel;

	public BagPanel(Game game) {
		setLayout(null);
		setBounds(250, 110, 800, 500);

		sauceLabel = new JLabel(sauceImg);
		sauceLabel.setBounds(10, 10, 100, 100);
		sauceLabel.setVisible(false);
		add(sauceLabel);
		mushLabel = new JLabel(mushImg);
		mushLabel.setBounds(270, 10, 100, 100);
		mushLabel.setVisible(false);
		add(mushLabel);
		papLabel = new JLabel("파프리카");
		papLabel.setBounds(530, 10, 100, 100);
		papLabel.setVisible(false);
		add(papLabel);
		onionLabel = new JLabel(onionImg);
		onionLabel.setBounds(10, 260, 100, 100);
		onionLabel.setVisible(false);
		add(onionLabel);
		pepperLabel = new JLabel(pepperImg);
		pepperLabel.setBounds(270, 260, 100, 100);
		pepperLabel.setVisible(false);
		add(pepperLabel);
		cheeseLabel = new JLabel(cheeseImg);
		cheeseLabel.setBounds(530, 260, 100, 100);
		cheeseLabel.setVisible(false);
		add(cheeseLabel);

		backButton = new JButton(backButtonImg);
		backButton.setVisible(true);
		backButton.setBounds(800 - 64, 0, 64, 64);
		backButton.setBorderPainted(false);
		backButton.setContentAreaFilled(false);
		backButton.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				game.stageSelectPanel.btnOn();
				setVisible(false);
			}
		});
		add(backButton);

		makeButton = new JButton("만들기");
		makeButton.setVisible(true);
		makeButton.setBounds(325, 425, 150, 55);
		makeButton.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				setVisible(false);

				String[] options = { "확인" };
				JPanel panel = new JPanel();
				JLabel lbl = new JLabel("이름을 입력해 주세요");
				JTextField txt = new JTextField(10);
				panel.add(lbl);
				panel.add(txt);
				int selectedOption = JOptionPane.showOptionDialog(null, panel, "피자 만들기", JOptionPane.NO_OPTION,
						JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
				String text = txt.getText();

				if (selectedOption == -1) {
					return;
				}
				if (text.trim().length() == 0) {
					JOptionPane.showMessageDialog(null, "공백은 안됩니다.", "이름 입력", JOptionPane.ERROR_MESSAGE);
					return;
				}

				sauceLabel.setVisible(false);
				mushLabel.setVisible(false);
				papLabel.setVisible(false);
				onionLabel.setVisible(false);
				pepperLabel.setVisible(false);
				cheeseLabel.setVisible(false);

				game.resultPanel.make(text);
				game.stageSelectPanel.setVisible(false);
				game.resultPanel.setVisible(true);
			}
		});
		add(makeButton);

	}

	public void check() {
		try {
			BufferedReader br = new BufferedReader(new FileReader("bag.txt"));
			String s = br.readLine();

			if (s == null) {
				sauceLabel.setVisible(false);
				mushLabel.setVisible(false);
				papLabel.setVisible(false);
				onionLabel.setVisible(false);
				pepperLabel.setVisible(false);
				cheeseLabel.setVisible(false);

				return;
			}

			if (s.contains("소스"))
				sauceLabel.setVisible(true);
			if (s.contains("버섯"))
				mushLabel.setVisible(true);
			if (s.contains("파프리카"))
				papLabel.setVisible(true);
			if (s.contains("양파"))
				onionLabel.setVisible(true);
			if (s.contains("페퍼로니"))
				pepperLabel.setVisible(true);
			if (s.contains("치즈"))
				cheeseLabel.setVisible(true);

			br.close();
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
