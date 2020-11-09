package pepperoni;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import code.Game;

public class PepperoniPanel extends JPanel {
	private Image backgroundImage = new ImageIcon("images/stage/stage4Back.png").getImage();
	private ImageIcon happyPizza = new ImageIcon("images/pepperoni/happyPizza.png");
	private ImageIcon sadPizza = new ImageIcon("images/pepperoni/sadPizza.png");

	private Game game;
	
	private JLabel pepperoniCount;
	private JLabel pizzaFace;
	
	private boolean isSpace = false;
	
    private Pepperoni pepperoni;
    private Thread prevTh;
    private Thread th;
    private boolean isFirst = true;
    private long t1 = 0, t2;
    
    private int x;
    private int d;
    private int floor;
	
	public PepperoniPanel(Game game) {
		setLayout(null);
		setBounds(0, 0, 1280, 720);
		
		this.game = game;
		
		pepperoniCount = new JLabel();
		pepperoniCount.setFont(pepperoniCount.getFont().deriveFont(65.0f));
		pepperoniCount.setBounds(1100, 50, 150, 100);
		add(pepperoniCount);
		
		pizzaFace = new JLabel(happyPizza);
		pizzaFace.setBounds(50, 50, happyPizza.getIconWidth(), happyPizza.getIconHeight());
		add(pizzaFace);
		
        x = (1280/2)-62;
        d = 1;
        floor = 665;
        
        addKeyListener(new MyKeyListener());
	}
	
	public void startGame() {
		if(isFirst) {
			pepperoni = new Pepperoni(x, d, floor, this);
			isFirst = false;
		} else {
			pepperoni = new Pepperoni(x, d, floor, th, this);
		}
        add(pepperoni);
        
        pepperoniCount.setText(pepperoni.getCount() - 1 + "개");
        
        th = new Thread(pepperoni);
        th.start();
        prevTh = th;
	}
	
	public void setSadface() {
		pizzaFace.setIcon(sadPizza);
	}
	
	class MyKeyListener extends KeyAdapter {
		@Override
		public void keyPressed(KeyEvent e) {
			int keyCode = e.getKeyCode();
			
            switch(keyCode) {
            case KeyEvent.VK_SPACE:
            	t1 = System.currentTimeMillis();
            	if(!isSpace && (t1 - t2)/1000 > 0.3) {
	                int[] arr = pepperoni.drop();
	                x = arr[0];
	                d = arr[1];
	                floor = arr[2];
	                startGame();
	                isSpace = true;
	                t2 = System.currentTimeMillis();
	                t1 = t2;
            	}
                break;
            }
		}
		@Override
		public void keyReleased(KeyEvent e) {
			int keyCode = e.getKeyCode(); // 상, 하, 좌, 우 키는 유니코드 키가 아님
			
			switch(keyCode) {
			case KeyEvent.VK_SPACE:
				isSpace = false;
				break;
			}
		}
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), null);
	}
}
