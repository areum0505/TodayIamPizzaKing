package bag;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
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
	private ImageIcon backgroundImage = new ImageIcon("images/main/bagBack.png");

	private ImageIcon backButtonImg = new ImageIcon("images/main/backButton.png");
	private ImageIcon sauceImg = new ImageIcon("images/main/sauce.png");
	private ImageIcon mushImg = new ImageIcon("images/main/mushroom.png");
	private ImageIcon papImg = new ImageIcon("images/main/paprika.png");
	private ImageIcon onionImg = new ImageIcon("images/main/onion.png");
	private ImageIcon pepperImg = new ImageIcon("images/main/pepperoni.png");
	private ImageIcon cheeseImg = new ImageIcon("images/main/cheese.png");

	private JPanel bagPanel;
	ColaPanel colaPanel = new ColaPanel(this);
	ColaGamePanel colaGamePanel;

	private JLabel jl;

	private JButton makeButton;

	private JLabel sauceLabel, mushLabel, papLabel, onionLabel, pepperLabel, cheeseLabel;

	public BagPanel(Game game) {
		setTitle("Bag");
		setSize(900, 630);
		setLocationRelativeTo(null);
		setModal(true);
		setResizable(false);

		colaGamePanel = new ColaGamePanel(game, this);
		colaPanel.setVisible(false);
		add(colaPanel);
		colaGamePanel.setVisible(false);
		add(colaGamePanel);

		bagPanel = new JPanel();
		bagPanel.setLayout(null);
		bagPanel.setBounds(0, 0, 900, 600);

		jl = new JLabel(backgroundImage);
		jl.setBounds(0, 0, 900, 600);
		add(jl);

		sauceLabel = new JLabel(sauceImg);
		sauceLabel.setBounds(10, 10, 100, 100);
		sauceLabel.setVisible(false);
		bagPanel.add(sauceLabel);
		mushLabel = new JLabel(mushImg);
		mushLabel.setBounds(270, 10, 100, 100);
		mushLabel.setVisible(false);
		bagPanel.add(mushLabel);
		papLabel = new JLabel(papImg);
		papLabel.setBounds(530, 10, 100, 100);
		papLabel.setVisible(false);
		bagPanel.add(papLabel);
		onionLabel = new JLabel(onionImg);
		onionLabel.setBounds(10, 260, 100, 100);
		onionLabel.setVisible(false);
		bagPanel.add(onionLabel);
		pepperLabel = new JLabel(pepperImg);
		pepperLabel.setBounds(270, 260, 100, 100);
		pepperLabel.setVisible(false);
		bagPanel.add(pepperLabel);
		cheeseLabel = new JLabel(cheeseImg);
		cheeseLabel.setBounds(530, 260, 100, 100);
		cheeseLabel.setVisible(false);
		bagPanel.add(cheeseLabel);

		makeButton = new JButton("�����");
		makeButton.setVisible(true);
		makeButton.setBounds(325, 425, 150, 55);
		makeButton.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				bagPanel.setVisible(false);

				int result = JOptionPane.showConfirmDialog(null, "������ �Ҹ� �� �ִ� ��ȸ�� �־����� �����Ͻðڽ��ϱ�?", "�λ������� ��ȸ",
						JOptionPane.YES_NO_OPTION);
				if (result == JOptionPane.CLOSED_OPTION) {
					return;
				} else if (result == JOptionPane.YES_OPTION) {
					colaPanel.setVisible(true);
				} else {
					setVisible(false);

					String[] options = { "Ȯ��" };
					JPanel panel = new JPanel(new GridLayout(2, 2));
					JLabel name_l = new JLabel("��� �̸� : ");
					JTextField name_tf = new JTextField(7);
					JLabel pizza_l = new JLabel("���� �̸� : ");
					JTextField pizza_tf = new JTextField(7);
					panel.add(name_l);
					panel.add(name_tf);
					panel.add(pizza_l);
					panel.add(pizza_tf);
					int selectedOption = JOptionPane.showOptionDialog(null, panel, "���� �����", JOptionPane.NO_OPTION,
							JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
					String name = name_tf.getText();
					String pizza = pizza_tf.getText();

					if (selectedOption == -1) {
						return;
					}
					if (name.trim().length() == 0) {
						JOptionPane.showMessageDialog(null, "�̸��� �Է����ּ���", "�̸� �Է�", JOptionPane.ERROR_MESSAGE);
						return;
					} else if (pizza.trim().length() == 0) {
						JOptionPane.showMessageDialog(null, "������ �̸��� �Է����ּ���", "���� �Է�", JOptionPane.ERROR_MESSAGE);
						return;
					}

					labelOff();

					game.resultPanel.make(name, pizza);
					game.stageSelectPanel.setVisible(false);
					game.resultPanel.setVisible(true);
				}
			}
		});
		bagPanel.add(makeButton);

		add(bagPanel);
		bagPanel.setVisible(true);
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

	public void labelOff() {
		sauceLabel.setVisible(false);
		mushLabel.setVisible(false);
		papLabel.setVisible(false);
		onionLabel.setVisible(false);
		pepperLabel.setVisible(false);
		cheeseLabel.setVisible(false);
	}

}

class ColaPanel extends JPanel {

	ImageIcon colaStartImg = new ImageIcon("images/cola/colaStart.png");

	JLabel back;
	JButton startBtn;

	public ColaPanel(BagPanel bagPanel) {
		setLayout(null);
		setBounds(0, 0, 900, 600);
		setBackground(Color.white);

		back = new JLabel(colaStartImg);
		back.setBounds(0, 0, 900, 600);
		add(back);

		startBtn = new JButton();
		startBtn.setBorderPainted(false);
		startBtn.setContentAreaFilled(false);
		startBtn.setBounds(285, 450, 330, 110);
		add(startBtn);

		startBtn.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				bagPanel.colaPanel.setVisible(false);
				bagPanel.colaGamePanel.setVisible(true);
				bagPanel.colaGamePanel.requestFocus();
				bagPanel.colaGamePanel.startGame();
			}
		});
		this.add(startBtn);
	}

}

class ColaLabel extends JLabel {
	int width = 281, height = 0;
	int x = 300, y = 580; // 250����
	Color colaColor = new Color(48, 29, 7);
	ColaGamePanel cgp;

	public ColaLabel(ColaGamePanel cgp) {
		this.cgp = cgp;
		setBackground(colaColor);
		setOpaque(true);
		setSize(width, height);
		setLocation(x, y);

	}

	public void fill() {
		if (getHeight() <= 350) { // 334������ �ݶ��� ��
			setY(getY() - 1);
			setHeight(getHeight() + 1);
			setBounds(getX(), getY(), width, getHeight());
		} else {
			cgp.changeExplain("�����");
		}
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

}

class CutLabel extends JLabel {

	int width = 281, height = 20;
	int ran = (int) (Math.random() * 200 + 1) + 250;
	int x = 300, y = ran;

	ColaGamePanel cgp;

	public CutLabel(ColaGamePanel cgp) {

		this.cgp = cgp;
		setBackground(Color.red);
		setOpaque(true);
		setSize(width, height);
		setLocation(x, y);

	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

}

class ColaThread extends Thread {
	private ColaLabel colaBar;

	public ColaThread(ColaLabel colaBar) {
		super();
		this.colaBar = colaBar;

	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			try {
				sleep(15);
				colaBar.fill();
			} catch (InterruptedException e) {
				// TODO: handle exception
				return;
			}
		}

	}
}

class ColaGamePanel extends JPanel {

	ImageIcon colaBackImg = new ImageIcon("images/cola/colaColorBack.png");

	Game game;
	BagPanel cola;
	
	SlotMachine sm;

	JLabel back, colaBack, explain;
	JButton startBtn;
	ColaLabel colaLabel;
	CutLabel cutLabel;
	ColaThread ct;

	public ColaGamePanel(Game game, BagPanel cola) {
		setLayout(null);
		setBounds(0, 0, 900, 600);
		setBackground(Color.white);

		this.game = game;
		this.cola = cola;
		
		sm = new SlotMachine(game, cola);
		sm.setVisible(false);
		add(sm);

		colaLabel = new ColaLabel(this);
		add(colaLabel);

		cutLabel = new CutLabel(this);
		add(cutLabel);

		explain = new JLabel("��ȸ�� �� ���� ���غ���");
		explain.setFont(new Font("������� ExtraBold", Font.BOLD, 30));
		explain.setBounds(400, 170, 500, 50);
		add(explain);

		back = new JLabel(colaBackImg);
		back.setBounds(0, 0, 900, 600);
		add(back);

		addKeyListener(new MyKeyListener());
	}

	public void startGame() {
		ct = new ColaThread(colaLabel);
		ct.start();
	}

	public void changeExplain(String s) {
		explain.setText(s);
	}

	public void crashCheck() {

		if (colaLabel.getY() < cutLabel.getY()) {
			changeExplain("����");
			System.out.println("�й�");
			ct.stop();

			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			defeat();
		} else if (colaLabel.getY() >= cutLabel.getY()) {
			if (colaLabel.getX() + colaLabel.getWidth() > cutLabel.getX()
					&& cutLabel.getX() + cutLabel.getWidth() > colaLabel.getX()
					&& colaLabel.getY() + colaLabel.getHeight() > cutLabel.getY()
					&& cutLabel.getY() + cutLabel.getHeight() > colaLabel.getY()) {
				changeExplain("���Ըӽ� ������ ����Ƥ���");

				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				System.out.println("�¸�");
				ct.stop();

				cola.labelOff();
				sm.setVisible(true);

			} else {
				changeExplain("����");
				System.out.println("�й�");
				ct.stop();

				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				defeat();
			}
		}

	}

	public void defeat() {
		cola.setVisible(false);

		String[] options = { "Ȯ��" };
		JPanel panel = new JPanel(new GridLayout(2, 2));
		JLabel name_l = new JLabel("��� �̸� : ");
		JTextField name_tf = new JTextField(7);
		JLabel pizza_l = new JLabel("���� �̸� : ");
		JTextField pizza_tf = new JTextField(7);
		panel.add(name_l);
		panel.add(name_tf);
		panel.add(pizza_l);
		panel.add(pizza_tf);
		int selectedOption = JOptionPane.showOptionDialog(null, panel, "���� �����", JOptionPane.NO_OPTION,
				JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
		String name = name_tf.getText();
		String pizza = pizza_tf.getText();

		if (selectedOption == -1) {
			return;
		}
		if (name.trim().length() == 0) {
			JOptionPane.showMessageDialog(null, "�̸��� �Է����ּ���", "�̸� �Է�", JOptionPane.ERROR_MESSAGE);
			return;
		} else if (pizza.trim().length() == 0) {
			JOptionPane.showMessageDialog(null, "������ �̸��� �Է����ּ���", "���� �Է�", JOptionPane.ERROR_MESSAGE);
			return;
		}

		cola.labelOff();

		game.resultPanel.make(name, pizza);
		game.stageSelectPanel.setVisible(false);
		game.resultPanel.setVisible(true);
	}

	class MyKeyListener extends KeyAdapter {

		@Override
		public void keyPressed(KeyEvent e) {
			int keyCode = e.getKeyCode(); // ��, ��, ��, �� Ű�� �����ڵ� Ű�� �ƴ�
			switch (keyCode) {

			case KeyEvent.VK_SPACE:
				crashCheck();
				break;
			}
		}
	}
}
