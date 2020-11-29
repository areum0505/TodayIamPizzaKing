package bag;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import code.Game;
import code.JTextFieldLimit;

class numLabel extends JLabel implements Runnable {
	int a = 0;
	String[] num = new String[]{ "-", "+" };

	public numLabel(String text) {
		setText(text);
	}

	public numLabel(String text, int a) {
		this.a = 1;
		setText(text);
	}

	@Override
	public void run() {
		while (true) {
			if (a == 1) {
				setText(num[(int) (Math.random() * 2)]);
			} else {
				int n = (int) (Math.random() * 10);
				setText(String.valueOf(n));
			}
			
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

public class SlotMachine extends JPanel implements ActionListener {
	ImageIcon backgroundImage = new ImageIcon("images/main/slotBack.png");
	JLabel back;

	Game game;
	BagPanel bag;

	numLabel[] labels;
	JButton button;
	int[] numbers;

	Thread[] th;

	public SlotMachine(Game game, BagPanel bag) {
		setLayout(null);
		setBounds(0, 0, 900, 600);
		setBackground(Color.GREEN);

		this.game = game;
		this.bag = bag;

		labels = new numLabel[4];
		numbers = new int[4];
		th = new Thread[4];

		for (int i = 0; i < 4; i++) {
			if (i == 0) {
				labels[i] = new numLabel(String.valueOf(numbers[i]), 1);
			} else {
				labels[i] = new numLabel(String.valueOf(numbers[i]));
			}
			labels[i].setFont(new Font("Serif", Font.BOLD, 100));
			labels[i].setSize(100, 100);
			labels[i].setLocation(155 + 183 * i, 245);

			th[i] = new Thread(labels[i]);
			th[i].start();

			add(labels[i]);
		}

		button = new JButton();
		button.setSize(196, 70);
		button.setBorderPainted(false);
		button.setContentAreaFilled(false);
		button.setLocation(360, 462);
		add(button);

		back = new JLabel(backgroundImage);
		back.setBounds(0, 0, 900, 600);
		add(back);

		button.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		for (int i = 0; i < 4; i++) {
			th[i].stop();
		}

		try {
			Thread.sleep(2000);
		} catch (Exception e2) {
			e2.printStackTrace();
		}

		bag.setVisible(false);

		String[] options = { "확인" };
		JPanel panel = new JPanel(new GridLayout(2, 2));
		JLabel name_l = new JLabel("당신 이름 : ");
		JTextField name_tf = new JTextField(7);
		name_tf.setDocument(new JTextFieldLimit(10));
		JLabel pizza_l = new JLabel("피자 이름 : ");
		JTextField pizza_tf = new JTextField(7);
		pizza_tf.setDocument(new JTextFieldLimit(6));
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

		String score = "";
		for (int i = 0; i < 4; i++) {
			score += labels[i].getText();
		}

		setVisible(false);

		game.resultPanel.setplusScore(score);
		game.resultPanel.make(name, pizza, true);
		game.stageSelectPanel.setVisible(false);
		game.resultPanel.setVisible(true);
	}

//	public static void main(String[] args) {
//		JFrame frame = new JFrame();
//		frame.setSize(900, 600);
//		frame.setVisible(true);
//		SlotMachine sm = new SlotMachine();
//		frame.add(sm);
//	}
}