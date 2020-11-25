package onion;

import java.awt.Color;
import java.awt.Desktop.Action;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;


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
	
	private int barSize = 0; // 현재 그릴 바의 크기
	private int maxBarSize; // 바의 최대 크기
	
	Color color = new Color(83, 255, 15);
	
	public onionLabel(int maxBarSize) {
		super();
		this.maxBarSize = maxBarSize;
	}

	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		g.setColor(color);
		int width = (int) (((double) (this.getWidth())) / maxBarSize * barSize); 
		if (width == 0)
			return; 
		g.fillRect(0, 0, width, this.getHeight());
	}

	public void fill() { // 가위바위보를 이겼을 때 바 +100

		barSize += 100;
		repaint();

	}

	public void clear() { // 바 0으로 초기화
		barSize = 0;
		repaint();
	}

}// end of MyLabel


public class OnionPanel extends JPanel{
	
	private Image backgroundImage = new ImageIcon("images/onion/onionBack.png").getImage();
	ImageIcon fullHeart = new ImageIcon("images/mushroom/fullHeart.png");
	ImageIcon deadHeart = new ImageIcon("images/mushroom/deadHeart.png");
	
	ImageIcon firstDrop = new ImageIcon("images/onion/firstDrop.png");
	ImageIcon secondDrop = new ImageIcon("images/onion/secondDrop.png");
	
	ImageIcon tear = new ImageIcon("images/onion/tears.png");
	
	ImageIcon pizzaIcon[] = { new ImageIcon("./images/onion/pizza_rock.png"),
			new ImageIcon("./images/onion/pizza_sissor.png"), new ImageIcon("./images/onion/pizza_paper.png") };

	ImageIcon comIcon[] = { new ImageIcon("./images/onion/enemy_rock.png"), new ImageIcon("./images/onion/enemy_sissor.png"),
			new ImageIcon("./images/onion/enemy_paper.png") };

	private onionLabel bar;
	
	JLabel lspScore, lspVS, lspCom, lspUser, lspResult;
	JLabel heart1, heart2, heart3, onionSays, drop1, drop2, drop3, comTears, pTears;
	JButton btnRock, btnScissors, btnPaper;

	OnionEnd onionEnd;
	OnionPause onionPause;

	int win = 0;
	int draw = 0;
	int lose = 0;
	int gameScore = 0;
	int heartCnt = 3;
	int dropCnt = 0;
	
	public OnionPanel(Game game) {

		setLayout(null);
		setBounds(0, 0, 1280, 720);
		setBackground(Color.white);

		onionEnd = new OnionEnd(game);
		onionPause = new OnionPause(game);
		add(onionPause);
		onionPause.setVisible(false);

		lspScore = new JLabel("0승 0무 0패");
		lspScore.setFont(new Font("나눔고딕 ExtraBold", Font.BOLD, 30));
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

		drop1 = new JLabel(firstDrop);
		drop1.setBounds(280, 450, 300, 80);
		add(drop1);
		drop1.setVisible(false);
		
		drop2 = new JLabel(secondDrop);
		drop2.setBounds(230, 460, 300, 80);
		add(drop2);
		drop2.setVisible(false);
		
		drop3 = new JLabel(firstDrop);
		drop3.setBounds(330, 450, 300, 80);
		add(drop3);
		drop3.setVisible(false);
		
		comTears = new JLabel(tear);
		comTears.setBounds(270, 330, 300, 80);
		add(comTears);
		comTears.setVisible(false);
		
		
		pTears = new JLabel(tear);
		pTears.setBounds(695, 360, 300, 80);
		add(pTears);
		pTears.setVisible(false);
		
		lspCom = new JLabel(new ImageIcon("./images/onion/enemy_first.png"));
		lspCom.setBounds(280, 180, 300, 350);
		add(lspCom);

		lspUser = new JLabel(new ImageIcon("./images/onion/pizza_first.png"));
		lspUser.setBounds(700, 200, 300, 350);
		add(lspUser);

		bar = new onionLabel(300);
		bar.setOpaque(true);
		bar.setBackground(Color.lightGray);
		bar.setSize(300,50);
		bar.setLocation(100,70);
		add(bar);

		
		add(lspScore);
		add(btnRock);
		add(btnScissors);
		add(btnPaper);

		
		heart1 = new JLabel(fullHeart);
		heart1.setBounds(900,70,fullHeart.getIconWidth(), fullHeart.getIconHeight());
		add(heart1);
		
		heart2 = new JLabel(fullHeart);
		heart2.setBounds(1000,70,fullHeart.getIconWidth(), fullHeart.getIconHeight());
		add(heart2);
		
		heart3 = new JLabel(fullHeart);
		heart3.setBounds(1100,70,fullHeart.getIconWidth(), fullHeart.getIconHeight());
		add(heart3);
		
		  MyMouseListener listener = new MyMouseListener();
		  
		  btnRock.addMouseListener(listener); 
		  btnScissors.addMouseListener(listener);
		  btnPaper.addMouseListener(listener);
		 
		  @SuppressWarnings("serial")
		AbstractAction escEvent = new AbstractAction() {

				public void actionPerformed(ActionEvent e) { 
			    	onionPause.setVisible(true);
			    }
			};
			InputMap im = this.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
		
			im.put(KeyStroke.getKeyStroke("ESCAPE"),"esc");
			getActionMap().put("esc", escEvent);
		
		  

	}
	
	public void chkResult(int user) {

		int com = (int) (Math.random() * 3) + 1;
		lspCom.setIcon(comIcon[com - 1]);
		// 1:묵 2:찌 3:빠

		if (com == user) {
			draw++;

		} else if ((com == 1 && user == 3) || (com == 2 && user == 1) || (com == 3 && user == 2)) {
			win++;
			gameScore += 100;
			bar.fill();
			dropCnt += 1;
			checkDrop();

		} else if ((com == 1 && user == 2) || (com == 2 && user == 3) || (com == 3 && user == 1)) {
		
			lose++;
			heartCnt +=-1;
			checkHeart();
		
		} else {
			System.out.println("예상치못한오류발생");
		}
		lspScore.setText(win + "승 " + draw + "무 " + lose + "패 ");
	}

	public void chkOnionResult() {

		if(gameScore>=300) {
			try {
				BufferedWriter bw = new BufferedWriter(new FileWriter("bag.txt", true));
				bw.write("양파 ");
				bw.flush();
				bw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			onionEnd.Success();
		}
		System.out.println("실행됨");
	}
	
	public void checkDrop() {
		switch(dropCnt) {
		case 3:
			comTears.setVisible(true);
			drop3.setVisible(true);
			break;
		case 2:
			comTears.setVisible(true);
			drop2.setVisible(true);
			break;
		case 1:
			comTears.setVisible(true);
			drop1.setVisible(true);
			break;
	}
	}
	void checkHeart() {
		switch(heartCnt) {
			case 3:
				heart3.setIcon(fullHeart);
				heart2.setIcon(fullHeart);
				heart1.setIcon(fullHeart);
				break;
			case 2:
				heart3.setIcon(deadHeart);
				pTears.setVisible(true);
				break;
			case 1:
				heart2.setIcon(deadHeart);
				pTears.setVisible(true);
				break;
			case 0:
				heart1.setIcon(deadHeart);
				pTears.setVisible(true);
				onionEnd.Fail();
				break;
		}
	}
	
	public void reset() {
		heartCnt = 3;
		dropCnt = 0;
		gameScore = 0;
		win = 0;
		draw = 0;
		lose = 0;
		gameScore = 0;
		lspScore.setText(win + "승 " + draw + "무 " + lose + "패 ");
		
		lspCom = new JLabel(new ImageIcon("./images/enemy_first.png"));
		lspUser = new JLabel(new ImageIcon("./images/onion/pizza_first.png"));
		
		comTears.setVisible(false);
		pTears.setVisible(false);
		
		drop1.setVisible(false);
		drop2.setVisible(false);
		drop3.setVisible(false);
		
		bar.clear();
	}

	
	  class MyMouseListener extends MouseAdapter {

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
		
			
			
		}


		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			Object ob = e.getSource();
			Music buttonClick = new Music("buttonClick1.mp3", false);
			
			if (ob == btnRock) {
				lspUser.setIcon(pizzaIcon[0]);
				buttonClick.start();
				comTears.setVisible(false);
				pTears.setVisible(false);
				chkResult(1);
				chkOnionResult();

			} else if (ob == btnScissors) {
				buttonClick.start();
				comTears.setVisible(false);
				pTears.setVisible(false);
				lspUser.setIcon(pizzaIcon[1]);
				chkResult(2);
				chkOnionResult();

			} else if (ob == btnPaper) {
				buttonClick.start();
				comTears.setVisible(false);
				pTears.setVisible(false);
				lspUser.setIcon(pizzaIcon[2]);
				chkResult(3);
				chkOnionResult();
			}
		}

	  
	  }
	  
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), null);

	}

}
