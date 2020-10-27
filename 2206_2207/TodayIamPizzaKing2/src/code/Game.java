package code;

import java.awt.Toolkit;

import javax.swing.JFrame;

import stage.SaucePanel;
import stage.StageSelectPanel;

public class Game extends JFrame {
	private int width = 1280, height = 750;
	
	public StartPanel startPanel = new StartPanel(this);
	public SettingPanel settingPanel = new SettingPanel(this);
	public StageSelectPanel stageSelectPanel = new StageSelectPanel(this);
	
	public SaucePanel saucePanel = new SaucePanel(this);
	
	public Game() {
		super("Today I'm Pizza King");
		setSize(width, height);
		setLocationRelativeTo(null);
		setResizable(false);
		setLayout(null);
		setVisible(true);	
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane();
		
		Toolkit kit = Toolkit.getDefaultToolkit();
		kit.getImage("images/main/icon.png");
		setIconImage(Toolkit.getDefaultToolkit().getImage("images/main/icon.png"));
		
		add(startPanel);
		startPanel.setVisible(true);
		
		add(settingPanel);
		settingPanel.setVisible(false);
		
		add(stageSelectPanel);
		stageSelectPanel.setVisible(false);
		
		add(saucePanel);
		saucePanel.setVisible(false);
	}
	
}