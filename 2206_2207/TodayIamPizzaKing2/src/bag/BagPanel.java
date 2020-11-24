package bag;

import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import code.Game;

public class BagPanel extends JDialog {
	private Image backgroundImage = new ImageIcon("images/stage/endImg.png").getImage();

	private ImageIcon backButtonImg = new ImageIcon("images/main/backButton.png");
	private ImageIcon sauceImg = new ImageIcon("images/main/sauce.png");
	private ImageIcon mushImg = new ImageIcon("images/main/mushroom.png");
	private ImageIcon papImg = new ImageIcon("images/main/paprika.png");
	private ImageIcon onionImg = new ImageIcon("images/main/onion.png");
	private ImageIcon pepperImg = new ImageIcon("images/main/pepperoni.png");
	private ImageIcon cheeseImg = new ImageIcon("images/main/cheese.png");

	private JPanel jp;

	private JButton makeButton;

	private JLabel sauceLabel, mushLabel, papLabel, onionLabel, pepperLabel, cheeseLabel;

	public BagPanel(Game game) {
		setTitle("Bag");
		setSize(915, 645);
		setLocationRelativeTo(null);
		setModal(true);
		setResizable(false);

		jp = new JPanel();
		jp.setLayout(null);
		jp.setBounds(0, 0, 900, 600);

		sauceLabel = new JLabel(sauceImg);
		sauceLabel.setBounds(10, 10, 100, 100);
		sauceLabel.setVisible(false);
		jp.add(sauceLabel);
		mushLabel = new JLabel(mushImg);
		mushLabel.setBounds(270, 10, 100, 100);
		mushLabel.setVisible(false);
		jp.add(mushLabel);
		papLabel = new JLabel(papImg);
		papLabel.setBounds(530, 10, 100, 100);
		papLabel.setVisible(false);
		jp.add(papLabel);
		onionLabel = new JLabel(onionImg);
		onionLabel.setBounds(10, 260, 100, 100);
		onionLabel.setVisible(false);
		jp.add(onionLabel);
		pepperLabel = new JLabel(pepperImg);
		pepperLabel.setBounds(270, 260, 100, 100);
		pepperLabel.setVisible(false);
		jp.add(pepperLabel);
		cheeseLabel = new JLabel(cheeseImg);
		cheeseLabel.setBounds(530, 260, 100, 100);
		cheeseLabel.setVisible(false);
		jp.add(cheeseLabel);

		makeButton = new JButton("만들기");
		makeButton.setVisible(true);
		makeButton.setBounds(325, 425, 150, 55);
		makeButton.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				setVisible(false);

				String[] options = { "확인" };
				JPanel panel = new JPanel(new GridLayout(2, 2));
				JLabel name_l = new JLabel("당신 이름 : ");
				JTextField name_tf = new JTextField(7);
				JLabel pizza_l = new JLabel("피자 이름 : ");
				JTextField pizza_tf = new JTextField(7);
				panel.add(name_l);
				panel.add(name_tf);
				panel.add(pizza_l);
				panel.add(pizza_tf);
				int selectedOption = JOptionPane.showOptionDialog(null, panel, "피자 만들기", JOptionPane.NO_OPTION,
						JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
				String name = name_tf.getText();
				String pizza = pizza_tf.getText();

				if (selectedOption == -1) {
					return;
				}
				if (name.trim().length() == 0) {
					JOptionPane.showMessageDialog(null, "이름을 입력해주세요", "이름 입력", JOptionPane.ERROR_MESSAGE);
					return;
				} else if (pizza.trim().length() == 0) {
					JOptionPane.showMessageDialog(null, "피자의 이름을 입력해주세요", "피자 입력", JOptionPane.ERROR_MESSAGE);
					return;
				}

				sauceLabel.setVisible(false);
				mushLabel.setVisible(false);
				papLabel.setVisible(false);
				onionLabel.setVisible(false);
				pepperLabel.setVisible(false);
				cheeseLabel.setVisible(false);

				game.resultPanel.make(name, pizza);
				game.stageSelectPanel.setVisible(false);
				game.resultPanel.setVisible(true);
			}
		});
		jp.add(makeButton);

		add(jp);
		jp.setVisible(true);
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

}
