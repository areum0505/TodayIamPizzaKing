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

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import code.Game;

public class CheesePanel extends JPanel {
	private Image backgroundImage = new ImageIcon("images/stage/stage1Back.png").getImage();
	private ImageIcon pizzaImg = new ImageIcon("images/cheese/pizza.png");
	
	private JLabel pizza;
	
	private Game game;
	
	public CheesePanel(Game game) {
		setLayout(null);
		setBounds(0, 0, 1280, 720);
		
		this.game = game;
		
		pizza = new JLabel(pizzaImg);
		pizza.setBounds(50, 50, pizzaImg.getIconWidth(), pizzaImg.getIconHeight());
		add(pizza);		
		
		Toolkit tk = Toolkit.getDefaultToolkit();
		Image cursorimage = tk.getImage("images/cheese/emptyCursor.png");//커서로 사용할 이미지
		Point point=new Point(0,0);
		Cursor cursor=tk.createCustomCursor(cursorimage, point, "cheese");
		setCursor(cursor);
		
		addMouseMotionListener(new MyMouseListener());
	}
	
	public void startGame() {
		setMouseCursor(550, 700);
		pizza.setLocation(getMousePosition().x - pizza.getWidth()/2, getMousePosition().y - pizza.getHeight()/2);
		getParent().repaint();
	}
	
	public void setMouseCursor(int x, int y) {
		try {
			Robot robot = new Robot();
			robot.mouseMove(x, y);
		} catch (AWTException e1) {
			e1.printStackTrace();
		}
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
		}
	}
}
