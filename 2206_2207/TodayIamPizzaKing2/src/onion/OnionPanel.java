package onion;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import code.Game;
import code.Main;
import code.Music;

class onionLabel extends JLabel{
	private int barSize = 0; //���� �׸� ���� ũ��
	private int maxBarSize; //���� �ִ� ũ�� 
	
	public onionLabel(int maxBarSize) {
		super();
		this.maxBarSize = maxBarSize;
	}
	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		g.setColor(Color.MAGENTA);
		int width = (int)  (((double)(this.getWidth()))/maxBarSize*barSize);  //?�ǹ��ؼ� 
		if(width==0) return; //ũ�Ⱑ 0�� ��� �ٸ� �׸� ������
		g.fillRect(0, 0, width, this.getHeight());
	}
	public void fill() { //�ٰ� �����ϴ� �޼��� 
		
		barSize+=100;
		repaint();  
	
	}
	public void consume() { //�ٰ� �����ϴ� �޼��� 
		if(barSize>=50) {
			barSize-=50;
		}
		repaint();
	}
	public void clear() {
		barSize = 0;
		repaint();
	}
	
}//end of MyLabel

class onionLifeLabel extends JLabel{
		
	
	private int width = 50; //���� �ִ� ũ�� 
	
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
		//g.drawImage(fillHeart, 0, 0, getWidth(), getHeight(), null);
	}	
	public void heartDead() { //�ٰ� �����ϴ� �޼��� 
	
		width-=50;
		repaint();
	}	
	
	public void clear() {
		width=50;
		repaint();
	}
}

public class OnionPanel extends JPanel implements ActionListener{

	private Image backgroundImage = new ImageIcon("images/onion/onionBackground.png").getImage();
	ImageIcon pizzaIcon[] = {
			new ImageIcon("./images/onion/rockPizza.png"),
			new ImageIcon("./images/onion/scissorsPizza.png"),
			new ImageIcon("./images/onion/paperPizza.png")
	};
	
	ImageIcon comIcon[] = {
			new ImageIcon("./img/mook.png"),
			new ImageIcon("./img/jji.png"),
			new ImageIcon("./img/bba.png")
	};

	private onionLabel bar = new onionLabel(300);
	onionLifeLabel life1, life2, life3;
	JLabel lspScore,lspVS, lspCom, lspUser, lspResult;
	JButton btnRock, btnScissors, btnPaper;

	OnionEnd onionEnd;
	
	int win=0;
	int draw=0;
	int lose=0;
	int gameScore=0;
	
	public OnionPanel(Game game) {
		
		setLayout(null);
		setBounds(0, 0, 1280, 720);
		setBackground(Color.white);
		
		
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
		
		lspUser = new JLabel(new ImageIcon("./images/onion/justPizza.png"));
		lspUser.setBounds(700, 200, 300, 350);
		
		bar.setOpaque(true);
		bar.setBackground(Color.pink);
		bar.setSize(300,50);
		bar.setLocation(100,70);
		add(bar);
	
		
		lspResult = new JLabel();
		lspResult.setFont(new Font("����", Font.BOLD, 30));
		lspResult.setBounds(350, 180, 200, 300);
		
		btnRock.addActionListener(this);
		btnScissors.addActionListener(this);
		btnPaper.addActionListener(this);
		
		life1 = new onionLifeLabel();
		life1.setOpaque(true);
		life1.setBackground(Color.black);
		life1.setSize(50,50);
		life1.setLocation(950,70);
		add(life1);
		
		life2 = new onionLifeLabel();
		life2.setOpaque(true);
		life2.setBackground(Color.black);
		life2.setSize(50,50);
		life2.setLocation(1050,70);
		add(life2);
		
		life3 = new onionLifeLabel();
		life3.setOpaque(true);
		life3.setBackground(Color.black);
		life3.setSize(50,50);
		life3.setLocation(1150,70);
		add(life3);

		
		
		
		add(lspScore);
		add(btnRock);
		add(btnScissors);
		add(btnPaper);
		add(lspCom);
		add(lspUser);
		add(lspResult);
		
		onionEnd = new OnionEnd(game);
		
		
	}
	public void chkResult(int user){
		
		int com = (int)(Math.random()*3)+1;
		lspCom.setIcon(comIcon[com-1]);
		//1:�� 2:�� 3:��
		
		if(com==user){
			draw++;
			lspResult.setText("����!");
			System.out.println("���");
			loseCheck();
		}else if((com==1 && user==3) || (com==2 && user==1) || (com==3 && user==2)){
			win++;
			gameScore+=100;
			lspResult.setText("�̰��!");
			bar.fill();
			
			loseCheck();
			System.out.println("���� �̱�");
		}else if((com==1 && user==2) || (com==2 && user==3) || (com==3 && user==1)){
			lose++;
			if(gameScore>=50) {
				gameScore-=50;
			}
			lspResult.setText("����");
			loseCheck();
			System.out.println("���� ����");
			bar.consume();

		}else{
			lspResult.setText("����");
		}
		lspScore.setText(win + "�� " + draw + "�� " + lose + "�� ");
	}	
	
	public void chkOnionResult() {
		if(gameScore>=300) {
			onionEnd.Success();
		}
		System.out.println("�����");
	}
	
	public void loseCheck() {
		switch(lose) {
		case 1: life1.heartDead(); break;
		case 2: life2.heartDead(); break;
		case 3: life3.heartDead(); 
				onionEnd.Fail();
				break;
		
		}
	}
	public void reset() {
		gameScore = 0; 
		win=0;
		draw=0;
		lose=0;
		gameScore=0;

		bar.clear();
		life1.clear();
		life2.clear();
		life3.clear();
	}
	public void actionPerformed(ActionEvent e) {
		
		Object ob = e.getSource();
        
		if(ob == btnRock){
			lspUser.setIcon(pizzaIcon[0]);
			chkResult(1);
			chkOnionResult();
			System.out.println(gameScore);
		
		}else if(ob == btnScissors){
			lspUser.setIcon(pizzaIcon[1]);
			chkResult(2);
			chkOnionResult();
			System.out.println(gameScore);
			
		}else if(ob == btnPaper){
			lspUser.setIcon(pizzaIcon[2]);
			chkResult(3);
			chkOnionResult();
			System.out.println(gameScore);
		
		}
	}
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), null);
		
	}
}
