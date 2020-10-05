package code;

import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;

public class Game extends JFrame {
	private int width = 1280, height = 750;
	
	public StartPanel startPanel = new StartPanel(this);
	public StageSelectPanel stageSelectPanel = new StageSelectPanel(this);
	
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
		
		add(stageSelectPanel);
		stageSelectPanel.setVisible(false);
		
	}
	
}