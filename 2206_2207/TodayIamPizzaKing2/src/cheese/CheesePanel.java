package cheese;

import java.awt.AWTException;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import code.Game;
import pepperoni.PepperoniPause;

public class CheesePanel extends JPanel {
	private Image backgroundImage = new ImageIcon("images/stage/stage1Back.png").getImage();
	private ImageIcon pizzaImg = new ImageIcon("images/cheese/pizza.png");

	private JLabel pizza;
	private ArrayList<Mine> mineList;
	private Exit exit;

	private Game game;
	private CheesePause pausePanel;
	private CheeseEnd endPanel;
	
	private int mousex, mousey;
	
	public CheesePanel(Game game) {
		setLayout(null);
		setBounds(0, 0, 1280, 720);

		this.game = game;
		
		pausePanel = new CheesePause(game);
		endPanel = new CheeseEnd(game);

		draw();

		setCursorImage(true);

		addMouseMotionListener(new MyMouseListener());
		addKeyListener(new MyKeyListener());
	}

	public void startGame() {
		setMouseCursor(550, 700);
		pizza.setLocation(getMousePosition().x - pizza.getWidth() / 2, getMousePosition().y - pizza.getHeight() / 2);
		
		mineList = new ArrayList<Mine>();
		for (int i = 0; i < 10; i++) {
			mineList.add(new Mine());
			add(mineList.get(i));
		}
		
		System.out.println(mineList.size());
	}

	public void setMouseCursor(int x, int y) {
		try {
			Robot robot = new Robot();
			robot.mouseMove(x, y);
		} catch (AWTException e1) {
			e1.printStackTrace();
		}
	}
	
	public int getMouseX() {
		return mousex;
	}
	public int getMouseY() {
		return mousey;
	}

	public void setCursorImage(boolean isEmpty) {
		Toolkit tk = Toolkit.getDefaultToolkit();
		Image cursorimage;
		if (isEmpty)
			cursorimage = tk.getImage("images/cheese/emptyCursor.png");
		else
			cursorimage = tk.getImage("images/main/pizzaCursor.png");
		Point point = new Point(0, 0);
		Cursor cursor = tk.createCustomCursor(cursorimage, point, "haha");
		setCursor(cursor);
	}

	public void checkMine(int x, int y) {
		if(mineList == null) return;
		for (Mine m : mineList) {
			if(IsIntersect(x-5, y-5, pizza.getWidth()-10, pizza.getHeight()-10, m.getX(), m.getY(), m.getWidth(), m.getHeight())) {
				endPanel.Fail();
			}
			if(IsIntersect(x - 50, y - 50, pizza.getWidth() + 100, pizza.getHeight() + 100, m.getX(), m.getY(), m.getWidth(), m.getHeight())) {
				m.setVisible(true);
			}
		}
	}

	public void checkExit(int x, int y) {
		if (1155 < x && (exit.getY() < y && y < exit.getY() + 133)) {
			endPanel.Success();
		}
	}

	boolean IsIntersect(int x1, int y1, int w1, int h1, int x2, int y2, int w2, int h2) {
		if (x1 + w1 >= x2 && x1 <= x2 + w2 && y1 + h1 >= y2 && y1 <= y2 + h2) {
			return true;
		}
		return false;
	}
	
	public void reset() {
		removeAll();
		draw();
	}
	
	public void draw() {
		pizza = new JLabel(pizzaImg);
		pizza.setSize(pizzaImg.getIconWidth(), pizzaImg.getIconHeight());
		add(pizza);

		exit = new Exit();
		add(exit);
		
		pausePanel = new CheesePause(game);
		add(pausePanel);
		pausePanel.setVisible(false);
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), null);
	}

	class MyMouseListener extends MouseMotionAdapter {
		public void mouseMoved(MouseEvent e) {
			if(!pausePanel.isVisible()) {
				Dimension frameSize = game.getSize();
				Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	
				PointerInfo pi = MouseInfo.getPointerInfo();
	
				int x = pi.getLocation().x - (screenSize.width - frameSize.width) / 2 - pizza.getWidth() / 2;
				int y = pi.getLocation().y - (screenSize.height - frameSize.height) / 2 - pizza.getHeight() / 2;
	
				pizza.setLocation(x, y);
				getParent().repaint();
	
				checkMine(x, y);
				checkExit(x, y);
			}
		}
	}
	class MyKeyListener extends KeyAdapter {
		@Override
		public void keyPressed(KeyEvent e) {
			int keyCode = e.getKeyCode();
			
			switch(keyCode) {
			case KeyEvent.VK_ESCAPE:
				PointerInfo pi = MouseInfo.getPointerInfo();
				mousex = pi.getLocation().x;
				mousey = pi.getLocation().y;
				setCursorImage(false);
            	pausePanel.setVisible(true);
            	break;
			}
		}
	}
}
