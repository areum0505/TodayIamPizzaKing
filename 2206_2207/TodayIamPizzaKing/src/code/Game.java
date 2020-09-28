package code;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

public class Game extends JFrame{
	private int width = 1280, height = 745;		// 가로, 세로
	private int count = 0;						
	
	private Image bufferImage;
	private Graphics screenGraphic;
	
	Player p;
	
	// 시작화면
	private Image backgroundImage = new ImageIcon("image/main/mainBackground.png").getImage();			// 배경이미지
	
	private JButton startButton = new JButton();														// 시작화면의 버튼들
	private JButton settingButton = new JButton();
	private JButton quitButton = new JButton();
	private Image startButtonImg = new ImageIcon("image/main/startButtonImg.png").getImage();			// 버튼들 이미지
	private Image settingButtonImg = new ImageIcon("image/main/settingButtonImg.png").getImage();
	private Image quitButtonImg = new ImageIcon("image/main/quitButtonImg.png").getImage();
	
	// 선택화면	
	private ArrayList<Stage> stageList = new ArrayList<>();												// 스테이지
	
	private Image stageSelectBackground = new ImageIcon("image/main/stageSelectBackground.png").getImage();
	
	private JButton stage1Button = new JButton();	// 치즈
	private JButton stage2Button = new JButton();	// 양파
	private JButton stage3Button = new JButton();	// 버섯
	private JButton stage4Button = new JButton();	// 페퍼로니
	private JButton stage5Button = new JButton();	// 피망
	private JButton stage6Button = new JButton();	// 소스
	private JButton bagButton = new JButton();		// 가방
	
	private Image enterImg = new ImageIcon("image/stage/enterImg.png").getImage();
	private Image explainImg;
	private Image enterStageButtonImg = new ImageIcon("image/stage/enterStageButton.png").getImage();
	private String stageName;
	private boolean isEnter = false;
	private boolean isBag = false;
	private JButton enterQuitButton = new JButton();
	private JButton gameStartButton = new JButton();
	
	// 게임화면
	private boolean jump, fall, attack;																	// 점프중인지, 떨어지는 중인지, 공격
	private int[] jumpY = {15, 12, 10, 7, 5, 3, 1};																// 점프를 할 때의 높이 (점점 줄어듦)
	int jumpCount = 0;
	private int attackCount = 0;
	
	private boolean isPause;
	private Image pauseImg = new ImageIcon("image/stage/pauseImg.png").getImage();
	
	private JButton gameQuitButton = new JButton();
	private JButton gameContinueButton = new JButton();
	
	private boolean up, down, left, right;																// 키
	
	// 페이지
	private boolean isStartPage = true;		
	private boolean isSettingPage = false;
	private boolean isStageSelectPage = false;
	private boolean isGamePage = false;
	
	private JButton backButton = new JButton();
	
	public Game() {
		setTitle("Today I'm Pizza King");	
		setVisible(true);		
		setSize(width, height);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		// 아이콘
		Toolkit kit = Toolkit.getDefaultToolkit();
		kit.getImage("image/main/icon.png");
		setIconImage(Toolkit.getDefaultToolkit().getImage("image/main/icon.png"));
		
		p = new Player();
		
		keyListener(); // 방향키 입력
		
		stageList.add(new Stage("치즈"));
		stageList.add(new Stage("양파"));
		stageList.add(new Stage("버섯"));
		stageList.add(new Stage("페퍼로니"));
		stageList.add(new Stage("피망"));
		stageList.add(new Stage("소스"));

		// 시작화면의 버튼들
		startButton.setVisible(true);
		startButton.setBounds(953, 265, 300, 125);
		startButton.setFocusable(false);
		startButton.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				isStartPage = false;
				isStageSelectPage = true;
				startButton.setVisible(false);
				settingButton.setVisible(false);
				quitButton.setVisible(false);
				setStageButton(true);
				backgroundImage = stageSelectBackground;
			}
		});
		getContentPane().add(startButton);
		
		settingButton.setVisible(true);
		settingButton.setBounds(953, 418, 300, 125);
		settingButton.setFocusable(false);
		settingButton.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				isStartPage = false;
				isSettingPage = true;
				startButton.setVisible(false);
				settingButton.setVisible(false);
				quitButton.setVisible(false);
				backgroundImage = new ImageIcon("image/main/setBackground.png").getImage();
			}
		});
		getContentPane().add(settingButton);
		
		quitButton.setVisible(true);
		quitButton.setBounds(953, 565, 300, 125);
		quitButton.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				System.exit(0);
			}
		});
		getContentPane().add(quitButton);	
		
		// 뒤로가기 버튼
		backButton.setVisible(true);
		backButton.setBounds(10, 10, 50, 50);
		backButton.setFocusable(false);
		backButton.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if(isSettingPage) isSettingPage = false;
				if(isStageSelectPage) {
					isStageSelectPage = false;
					setStageButton(false);
				}
				isStartPage = true;
				startButton.setVisible(true);
				settingButton.setVisible(true);
				quitButton.setVisible(true);
				backgroundImage = new ImageIcon("image/main/mainBackground.png").getImage();
			}
		});
		getContentPane().add(backButton);	
		
		// 스테이지 버튼
		stage1Button.setVisible(false);
		stage1Button.setBounds(520, 45, 275, 160);
		stage1Button.setFocusable(false);
		stage1Button.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				stageName = stageList.get(0).getStageName();
				explainImg = stageList.get(0).getStageExplain();
				isEnter = true;
				setStageButton(false);
				backButton.setVisible(false);
				enterQuitButton.setVisible(true);
				gameStartButton.setVisible(true);
			}
		});
		getContentPane().add(stage1Button);
		
		stage2Button.setVisible(false);
		stage2Button.setBounds(875, 145, 270, 195);
		stage2Button.setFocusable(false);
		stage2Button.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				stageName = stageList.get(1).getStageName();
				isEnter = true;
				setStageButton(false);
				backButton.setVisible(false);
				enterQuitButton.setVisible(true);
				gameStartButton.setVisible(true);
			}
		});
		getContentPane().add(stage2Button);
		
		stage3Button.setVisible(false);
		stage3Button.setBounds(875, 430, 250, 155);
		stage3Button.setFocusable(false);
		stage3Button.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				stageName = stageList.get(2).getStageName();
				isEnter = true;
				setStageButton(false);
				backButton.setVisible(false);
				enterQuitButton.setVisible(true);
				gameStartButton.setVisible(true);
			}
		});
		getContentPane().add(stage3Button);
		
		stage4Button.setVisible(false);
		stage4Button.setBounds(520, 505, 270, 165);
		stage4Button.setFocusable(false);
		stage4Button.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				stageName = stageList.get(3).getStageName();
				isEnter = true;
				setStageButton(false);
				backButton.setVisible(false);
				enterQuitButton.setVisible(true);
				gameStartButton.setVisible(true);
			}
		});
		getContentPane().add(stage4Button);

		stage5Button.setVisible(false);
		stage5Button.setBounds(155, 415, 265, 165);
		stage5Button.setFocusable(false);
		stage5Button.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				stageName = stageList.get(4).getStageName();
				isEnter = true;
				setStageButton(false);
				backButton.setVisible(false);
				enterQuitButton.setVisible(true);
				gameStartButton.setVisible(true);
			}
		});
		getContentPane().add(stage5Button);
		
		stage6Button.setVisible(false);
		stage6Button.setBounds(165, 155, 275, 170);
		stage6Button.setFocusable(false);
		stage6Button.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				stageName = stageList.get(5).getStageName();
				isEnter = true;
				setStageButton(false);
				backButton.setVisible(false);
				enterQuitButton.setVisible(true);
				gameStartButton.setVisible(true);
			}
		});
		getContentPane().add(stage6Button);
		
		bagButton.setVisible(false);
		bagButton.setBounds(520, 270, 275, 173);
		bagButton.setFocusable(false);
		bagButton.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				stageName = "가방";
				isBag = true;
				setStageButton(false);
				backButton.setVisible(false);
				enterQuitButton.setVisible(true);
				gameStartButton.setVisible(true);
			}
		});
		getContentPane().add(bagButton);
		
		enterQuitButton.setVisible(false);
		enterQuitButton.setBounds(1025, 95, 35, 35);
		enterQuitButton.setFocusable(false);
		enterQuitButton.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				isEnter = false;
				isBag = false;
				enterQuitButton.setVisible(false);
				gameStartButton.setVisible(false);
				backButton.setVisible(true);
				setStageButton(true);
			}
		});
		getContentPane().add(enterQuitButton);
		
		gameStartButton.setVisible(false);
		gameStartButton.setBounds(560, 455, 200, 60);
		gameStartButton.setFocusable(false);
		gameStartButton.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				isEnter = false;
				enterQuitButton.setVisible(false);
				gameStartButton.setVisible(false);
				isStageSelectPage = false;
				isGamePage = true;
				switch(stageName) {
				case "치즈":
					backgroundImage = stageList.get(0).getStageBackground();
					break;
				case "양파":
					backgroundImage = stageList.get(1).getStageBackground();
					break;
				case "버섯":
					backgroundImage = stageList.get(2).getStageBackground();
					break;
				case "페퍼로니":
					backgroundImage = stageList.get(3).getStageBackground();
					break;
				case "피망":
					backgroundImage = stageList.get(4).getStageBackground();
					break;
				case "소스":
					backgroundImage = stageList.get(5).getStageBackground();
					break;
				}
			}
		});
		getContentPane().add(gameStartButton);
		
		
		
		gameQuitButton.setVisible(false);
		gameQuitButton.setBounds(375, 455, 200, 60);
	    gameQuitButton.setFocusable(false);
	    gameQuitButton.addMouseListener(new MouseAdapter() {
	    public void mousePressed(MouseEvent e) {
			isGamePage = false;
			isPause = false;
			isStageSelectPage = true;
			backgroundImage = stageSelectBackground;
			backButton.setVisible(true);
			gameQuitButton.setVisible(false);
			gameContinueButton.setVisible(false);
			setStageButton(true);
	    	}
	    });
	    getContentPane().add(gameQuitButton);
	    gameContinueButton.setVisible(false);
	    gameContinueButton.setBounds(755, 455, 200, 60);
	    gameContinueButton.setFocusable(false);
	    gameContinueButton.addMouseListener(new MouseAdapter() {
	    public void mousePressed(MouseEvent e) {
	    	isPause = false;
			gameQuitButton.setVisible(false);
			gameContinueButton.setVisible(false);
	    	}
	    });
	    getContentPane().add(gameContinueButton);
	}
	
	public void setStageButton(boolean flag) {		// 코드가 긴 것 같아서 추가함 - 스테이지 버튼들 보이게하거나 숨기기
		stage1Button.setVisible(flag);
		stage2Button.setVisible(flag);
		stage3Button.setVisible(flag);
		stage4Button.setVisible(flag);
		stage5Button.setVisible(flag);
		stage6Button.setVisible(flag);
		bagButton.setVisible(flag);
	}
	
	public void pause() {
		isPause = true;
		gameQuitButton.setVisible(true);
		gameContinueButton.setVisible(true);
	}
	
	
	public void paint(Graphics g) {
		bufferImage = createImage(width, height);
		screenGraphic = bufferImage.getGraphics();
		screenDraw(screenGraphic);
		g.drawImage(bufferImage, 0, 0, null);
	}
	
	public void screenDraw(Graphics g) {
		g.drawImage(backgroundImage, 0, 25, null);
		if(isStartPage) {											// 지금이 시작페이지면			
//			g.drawImage(startButtonImg, 957, 307, null);
//			g.drawImage(settingButtonImg, 957, 416, null);
//			g.drawImage(quitButtonImg, 957, 524, null);
		}
		if(isSettingPage) {
			g.drawImage(new ImageIcon("image/main/backButton.png").getImage(), 10, 35, null);
		}
		if(isStageSelectPage) {										// 스테이지 선택페이지면
			g.drawImage(new ImageIcon("image/main/backButton.png").getImage(), 10, 35, null);
			if(isEnter) {
				g.drawImage(enterImg, 260, 120, null);
				g.drawImage(explainImg, 260, 120, null);
				g.drawImage(enterStageButtonImg, 560, 480, null);
				g.setFont(new Font("둥근모꼴", Font.PLAIN, 45));
				g.drawString(stageName, (width/2)-(stageName.length()/2*45)+30, 180);
			}
			if(isBag) {
				g.drawImage(enterImg, 260, 120, null);
				g.setFont(new Font("둥근모꼴", Font.PLAIN, 45));
				g.drawString(stageName, (width/2)-(stageName.length()/2*45)+30, 180);
			}
		}
		if(isGamePage) {
			g.drawImage(p.getImg(), 10, height-198-10, null);
			if(isPause) {
				g.drawImage(pauseImg, 260, 120, null);
			}
		}
		this.repaint();
	}
	
	public void keyListener() {
		addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {	// 키가 눌렸을 때
				switch(e.getKeyCode()) {
				case KeyEvent.VK_ESCAPE:
					if(isGamePage) {
						pause();
					} else if(isStageSelectPage) {
						if(isEnter) {
							setStageButton(true);
							isEnter = false;
							enterQuitButton.setVisible(false);
							gameStartButton.setVisible(false);
							backButton.setVisible(true);
						} else if(isBag) {
							setStageButton(true);
							isBag = false;
							enterQuitButton.setVisible(false);
							backButton.setVisible(true);
						} else {
							setStageButton(false);
							isStageSelectPage = false;
							isStartPage = true;
							startButton.setVisible(true);
							settingButton.setVisible(true);
							quitButton.setVisible(true);
							backgroundImage = new ImageIcon("image/main/mainBackground.png").getImage();
						}
					} else {
						System.exit(0);
					}
					break;
				}
			}
			public void keyReleased(KeyEvent e) {	// 키가 떼졌을 때
				switch(e.getKeyCode()) {
				case KeyEvent.VK_ESCAPE:
					break;
				}
			}
		});
	}
}
