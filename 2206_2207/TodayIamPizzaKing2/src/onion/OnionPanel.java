package onion;

import java.awt.Color;
import java.awt.Desktop.Action;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
<<<<<<< HEAD
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
=======
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
>>>>>>> origin/master

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.ImageIcon;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import code.Game;
import code.Main;
import code.Music;

class onionLabel extends JLabel {
	private int barSize = 0; // ���� �׸� ���� ũ��
	private int maxBarSize; // ���� �ִ� ũ��

	public onionLabel(int maxBarSize) {
		super();
		this.maxBarSize = maxBarSize;
	}

	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		g.setColor(Color.MAGENTA);
		int width = (int) (((double) (this.getWidth())) / maxBarSize * barSize); // ?�ǹ��ؼ�
		if (width == 0)
			return; // ũ�Ⱑ 0�� ��� �ٸ� �׸� ������
		g.fillRect(0, 0, width, this.getHeight());
	}

	public void fill() { // �ٰ� �����ϴ� �޼���

		barSize += 100;
		repaint();

	}

	public void consume() { // �ٰ� �����ϴ� �޼���
		if (barSize >= 50) {
			barSize -= 50;
		}
		repaint();
	}

	public void clear() {
		barSize = 0;
		repaint();
	}

}// end of MyLabel

class onionLifeLabel extends JLabel {

	private int width = 50; // ���� �ִ� ũ��

	ImageIcon noHeart = new ImageIcon("images/onion/noHeart.png");
	Image fillHeart = new ImageIcon("images/onion/fillHeart.png").getImage();

	public onionLifeLabel() {
		super();
	}

	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		g.setColor(Color.red);
		g.fillRect(0, 0, width, this.getHeight());
		// g.drawImage(fillHeart, 0, 0, getWidth(), getHeight(), null);
	}

	public void heartDead() { // �ٰ� �����ϴ� �޼���

		width -= 50;
		repaint();
	}

	public void clear() {
		width = 50;
		repaint();
	}
}

public class OnionPanel extends JPanel{

	private static final Object DISABLE_CLICKER = null;
	private Image backgroundImage = new ImageIcon("images/onion/onionBackground.png").getImage();
	ImageIcon pizzaIcon[] = { new ImageIcon("./images/onion/rockPizza.png"),
			new ImageIcon("./images/onion/scissorsPizza.png"), new ImageIcon("./images/onion/paperPizza.png") };

	ImageIcon comIcon[] = { new ImageIcon("./img/mook.png"), new ImageIcon("./img/jji.png"),
			new ImageIcon("./img/bba.png") };

	private onionLabel bar;
	onionLifeLabel life1, life2, life3;
	JLabel lspScore, lspVS, lspCom, lspUser, lspResult;
	JButton btnRock, btnScissors, btnPaper;

	OnionEnd onionEnd;
	OnionPause onionPause;

	int win = 0;
	int draw = 0;
	int lose = 0;
	int gameScore = 0;

	public OnionPanel(Game game) {

		setLayout(null);
		setBounds(0, 0, 1280, 720);
		setBackground(Color.white);

		onionEnd = new OnionEnd(game);
		onionPause = new OnionPause(game);
		add(onionPause);
		onionPause.setVisible(false);

		lspScore = new JLabel("0�� 0�� 0��");
		lspScore.setFont(new Font("������� ExtraBold", Font.BOLD, 30));
		lspScore.setBounds(600, 70, 500, 50);

		btnScissors = new JButton();
		btnScissors.setBounds(174, 592, 250, 85);
		btnScissors.setBorderPainted(false);
		btnScissors.setContentAreaFilled(false);

		btnRock = new JButton();
		btnRock.setBounds(514, 592, 250, 85);
		btnRock.setBorderPainted(false);
		btnRock.setContentAreaFilled(false);

		btnPaper = new JButton();
		btnPaper.setBounds(855, 592, 250, 85);
		btnPaper.setBorderPainted(false);
		btnPaper.setContentAreaFilled(false);

		lspCom = new JLabel(new ImageIcon("./img/first.png"));
		lspCom.setBounds(300, 200, 300, 300);
		add(lspCom);

		lspUser = new JLabel(new ImageIcon("./images/onion/justPizza.png"));
		lspUser.setBounds(700, 200, 300, 350);
		add(lspUser);

		bar = new onionLabel(300);
		bar.setOpaque(true);
		bar.setBackground(Color.pink);
		bar.setSize(300, 50);
		bar.setLocation(100, 70);
		add(bar);

//		lspResult = new JLabel();
//		lspResult.setFont(new Font("����", Font.BOLD, 30));
//		lspResult.setBounds(350, 180, 200, 300);
//		
//		add(lspResult);
		
		add(lspScore);
		add(btnRock);
		add(btnScissors);
		add(btnPaper);

		life1 = new onionLifeLabel();
		life1.setOpaque(true);
		life1.setBackground(Color.black);
		life1.setSize(50, 50);
		life1.setLocation(950, 70);
		add(life1);

		life2 = new onionLifeLabel();
		life2.setOpaque(true);
		life2.setBackground(Color.black);
		life2.setSize(50, 50);
		life2.setLocation(1050, 70);
		add(life2);

		life3 = new onionLifeLabel();
		life3.setOpaque(true);
		life3.setBackground(Color.black);
		life3.setSize(50, 50);
		life3.setLocation(1150, 70);
		add(life3);

		
		  MyMouseListener listener = new MyMouseListener();
		  
		  btnRock.addMouseListener(listener); 
		  btnScissors.addMouseListener(listener);
		  btnPaper.addMouseListener(listener);
		  
		  AbstractAction escEvent = new AbstractAction() {
			    public void actionPerformed(ActionEvent e) {
			        
			    	onionPause.setVisible(true);
					System.out.println("������");
			    }
			};
			InputMap im = this.getInputMap(this.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
		
			im.put(KeyStroke.getKeyStroke("ESCAPE"),
			                            "esc");
			getActionMap().put("esc", escEvent);
		
		  

	}
	
	public void chkResult(int user) {

		int com = (int) (Math.random() * 3) + 1;
		lspCom.setIcon(comIcon[com - 1]);
		// 1:�� 2:�� 3:��

		if (com == user) {
			draw++;
			System.out.println("���");

		} else if ((com == 1 && user == 3) || (com == 2 && user == 1) || (com == 3 && user == 2)) {
			win++;
			gameScore += 100;
			bar.fill();
			System.out.println("���� �̱�");

		} else if ((com == 1 && user == 2) || (com == 2 && user == 3) || (com == 3 && user == 1)) {
			if (gameScore >= 50) {
				gameScore -= 50;
			}
			lose++;
			loseCheck();
			bar.consume();
			System.out.println("���� ����");
		} else {
			System.out.println("����ġ���ѿ����߻�");
		}
		lspScore.setText(win + "�� " + draw + "�� " + lose + "�� ");
	}

	public void chkOnionResult() {
<<<<<<< HEAD
		if (gameScore >= 300) {
=======
		if(gameScore>=300) {
			try {
				BufferedWriter bw = new BufferedWriter(new FileWriter("bag.txt", true));
				bw.write("���� ");
				bw.flush();
				bw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
>>>>>>> origin/master
			onionEnd.Success();
		}
		System.out.println("�����");
	}

	public void loseCheck() {
		switch (lose) {
		case 1:
			life1.heartDead();
			break;
		case 2:
			life2.heartDead();
			break;
		case 3:
			life3.heartDead();
			onionEnd.Fail();
			break;

		}
	}

	public void reset() {
		gameScore = 0;
		win = 0;
		draw = 0;
		lose = 0;
		gameScore = 0;
		lspScore.setText(win + "�� " + draw + "�� " + lose + "�� ");

		bar.clear();
		life1.clear();
		life2.clear();
		life3.clear();
	}

	
	  class MyMouseListener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			Object ob = e.getSource();
			
			if (ob == btnRock) {
				lspUser.setIcon(pizzaIcon[0]);
				chkResult(1);
				chkOnionResult();
				System.out.println(gameScore);

			} else if (ob == btnScissors) {
				lspUser.setIcon(pizzaIcon[1]);
				chkResult(2);
				chkOnionResult();
				System.out.println(gameScore);

			} else if (ob == btnPaper) {
				lspUser.setIcon(pizzaIcon[2]);
				chkResult(3);
				chkOnionResult();
				System.out.println(gameScore);
			}
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
	  
	  }
	  
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), null);

	}

}
