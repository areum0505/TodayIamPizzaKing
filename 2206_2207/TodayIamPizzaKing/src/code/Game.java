package code;

import java.awt.Cursor;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;

import javax.swing.JFrame;

import cheese.CheesePanel;
import mushroom.MushroomPanel;
import onion.OnionPanel;
import paprika.PaprikaPanel;
import pepperoni.PepperoniPanel;
import sauce.SaucePanel;

public class Game extends JFrame {
	private int width = 1280, height = 750;

	public StartPanel startPanel = new StartPanel(this);
	public SettingPanel settingPanel = new SettingPanel(this);
	public StageSelectPanel stageSelectPanel = new StageSelectPanel(this);
	public ResultPanel resultPanel = new ResultPanel(this);

	public CheesePanel cheesePanel = new CheesePanel(this);
	public OnionPanel onionPanel = new OnionPanel(this);
	public MushroomPanel mushroomPanel = new MushroomPanel(this);
	public PepperoniPanel pepperoniPanel = new PepperoniPanel(this);
	public SaucePanel saucePanel = new SaucePanel(this);
	public PaprikaPanel paprikaPanel = new PaprikaPanel(this);

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
		setIconImage(Toolkit.getDefaultToolkit().getImage("images/main/pizzaIcon.png"));

		Toolkit tk = Toolkit.getDefaultToolkit();
		Image cursorimage = tk.getImage("images/main/cursor1.png");
		Point point = new Point(20, 20);
		Cursor cursor = tk.createCustomCursor(cursorimage, point, "haha");

		this.setCursor(cursor);

		add(startPanel);
		startPanel.setVisible(true);

		add(settingPanel);
		settingPanel.setVisible(false);

		add(stageSelectPanel);
		stageSelectPanel.setVisible(false);

		add(resultPanel);
		resultPanel.setVisible(false);

		add(cheesePanel);
		cheesePanel.setVisible(false);

		add(onionPanel);
		onionPanel.setVisible(false);

		add(mushroomPanel);
		mushroomPanel.setVisible(false);

		add(pepperoniPanel);
		pepperoniPanel.setVisible(false);

		add(saucePanel);
		saucePanel.setVisible(false);

		add(paprikaPanel);
		paprikaPanel.setVisible(false);

	}
}