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

	private JButton backButton;
	private JButton makeButton;

	private JLabel sauceLabel, mushLabel, papLabel, onionLabel, pepperLabel, cheeseLabel;

	public BagPanel(Game game) {
		setLayout(null);
		setBounds(250, 110, 800, 500);

		sauceLabel = new JLabel("�ҽ�");
		sauceLabel.setBounds(10, 10, 100, 50);
		sauceLabel.setVisible(false);
		add(sauceLabel);
		mushLabel = new JLabel("����");
		mushLabel.setBounds(270, 10, 100, 50);
		mushLabel.setVisible(false);
		add(mushLabel);
		papLabel = new JLabel("������ī");
		papLabel.setBounds(530, 10, 100, 50);
		papLabel.setVisible(false);
		add(papLabel);
		onionLabel = new JLabel("����");
		onionLabel.setBounds(10, 260, 100, 50);
		onionLabel.setVisible(false);
		add(onionLabel);
		pepperLabel = new JLabel("���۷δ�");
		pepperLabel.setBounds(270, 260, 100, 50);
		pepperLabel.setVisible(false);
		add(pepperLabel);
		cheeseLabel = new JLabel("ġ��");
		cheeseLabel.setBounds(530, 260, 100, 50);
		cheeseLabel.setVisible(false);
		add(cheeseLabel);

		backButton = new JButton(backButtonImg);
		backButton.setVisible(true);
		backButton.setBounds(800 - 64, 0, 64, 64);
		backButton.setBorderPainted(false);
		backButton.setContentAreaFilled(false);
		backButton.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				setVisible(false);
			}
		});
		add(backButton);

		makeButton = new JButton("�����");
		makeButton.setVisible(true);
		makeButton.setBounds(325, 425, 150, 55);
		makeButton.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				setVisible(false);

				String[] options = { "Ȯ��" };
				JPanel panel = new JPanel();
				JLabel lbl = new JLabel("�̸��� �Է��� �ּ���");
				JTextField txt = new JTextField(10);
				panel.add(lbl);
				panel.add(txt);
				int selectedOption = JOptionPane.showOptionDialog(null, panel, "���� �����", JOptionPane.NO_OPTION,
						JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
				String text = txt.getText();

				if (selectedOption == -1) {
					return;
				}
				if (text.trim().length() == 0) {
					JOptionPane.showMessageDialog(null, "������ �ȵ˴ϴ�.", "�̸� �Է�", JOptionPane.ERROR_MESSAGE);
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

			if (s.contains("�ҽ�"))
				sauceLabel.setVisible(true);
			if (s.contains("����"))
				mushLabel.setVisible(true);
			if (s.contains("������ī"))
				papLabel.setVisible(true);
			if (s.contains("����"))
				onionLabel.setVisible(true);
			if (s.contains("���۷δ�"))
				pepperLabel.setVisible(true);
			if (s.contains("ġ��"))
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
