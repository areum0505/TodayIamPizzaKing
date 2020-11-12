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
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import code.Game;

public class CheesePanel extends JPanel {
	private Image backgroundImage = new ImageIcon("images/stage/stage1Back.png").getImage();
	private ImageIcon pizzaImg = new ImageIcon("images/cheese/pizza.png");
	
	private JLabel pizza;
	private ArrayList<Mine> mineList;
	private Exit exit;
	
	private Game game;
	
	public CheesePanel(Game game) {
		setLayout(null);
		setBounds(0, 0, 1280, 720);
		
		this.game = game;
		
		pizza = new JLabel(pizzaImg);
		pizza.setSize(pizzaImg.getIconWidth(), pizzaImg.getIconHeight());
		add(pizza);	
		
		mineList = new ArrayList<Mine>();
		for(int i = 0; i < 15; i++) {
			mineList.add(new Mine());
			add(mineList.get(i));
		}
		
		exit = new Exit();
		add(exit);
		
		setCursorImage(true);
		
		addMouseMotionListener(new MyMouseListener());
	}
	
	public void startGame() {
		setMouseCursor(550, 700);
		pizza.setLocation(getMousePosition().x - pizza.getWidth()/2, getMousePosition().y - pizza.getHeight()/2);
	}
	
	public void setMouseCursor(int x, int y) {
		try {
			Robot robot = new Robot();
			robot.mouseMove(x, y);
		} catch (AWTException e1) {
			e1.printStackTrace();
		}
	}
	
	public void setCursorImage(boolean isEmpty) {
		Toolkit tk = Toolkit.getDefaultToolkit();
		Image cursorimage;
		if(isEmpty)
			cursorimage = tk.getImage("images/cheese/emptyCursor.png");
		else
			cursorimage = tk.getImage("images/main/pizzaCursor.png");
		Point point = new Point(0,0);
		Cursor cursor = tk.createCustomCursor(cursorimage, point, "haha");
		setCursor(cursor);
	}
	
	public void checkMine(int x, int y) {
		for(Mine m : mineList) {
//			if( x - 25 < m.getX() + m.getWidth() && 
//					x + 25 + pizza.getWidth() > m.getX() && 
//					y - 25 < m.getY() + m.getHeight() && 
//					pizza.getHeight() < y + 25 && y  + 25 > m.getY() ) {
//				if(m.isVisible())
//					m.setVisible(false);
//				else
//					m.setVisible(true);
//			}
			

		}
	}
	public void checkExit(int x, int y) {
		if(1155 < x && (exit.getY() < y && y < exit.getY() + 133)) {
			System.out.println("clear");
		}
	}
	
	boolean checkUp(int x, int y, Mine m) {
		if(y - 25 < m.getY()+m.getHeight() && m.getY()+m.getHeight() < y - 1)
			return true;
		else
			return false;
	}
	boolean checkRight(int x, int y, Mine m) {
		if(x + pizza.getWidth() + 1 < m.getX() && m.getX() < x + pizza.getWidth() + 25)
			return true;
		else
			return false;
	}
	boolean checkDown(int x, int y, Mine m) {
		if(y + pizza.getHeight() + 1 < m.getY() && m.getY() < y + pizza.getHeight() + 25)
			return true;
		else
			return false;
	}
	boolean checkLeft(int x, int y, Mine m) {
		if(x - 25 < m.getX() + m.getWidth() && m.getX() + m.getWidth() < x - 1)
			return true;
		else
			return false;
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), null);
	}
	
	class MyMouseListener extends MouseMotionAdapter {
		public void mouseMoved(MouseEvent e) { 
			Dimension frameSize = game.getSize();
		    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			
			PointerInfo pi = MouseInfo.getPointerInfo();
			
			int x = pi.getLocation().x - (screenSize.width - frameSize.width) /2 - pizza.getWidth()/2;
			int y = pi.getLocation().y - (screenSize.height - frameSize.height) /2 - pizza.getHeight()/2;
			
			pizza.setLocation(x, y);
			getParent().repaint();
			
			checkMine(x, y);
			checkExit(x, y);
		}
	}
}
