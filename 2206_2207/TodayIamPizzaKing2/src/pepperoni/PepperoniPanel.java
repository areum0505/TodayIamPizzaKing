package pepperoni;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import code.Game;
import code.Main;

public class PepperoniPanel extends JPanel {
	private Image backgroundImage = new ImageIcon("images/stage/stage4Back.png").getImage();
	private Image backgroundImage1 = new ImageIcon("images/stage/stage4Back1.png").getImage();
	private Image plate = new ImageIcon("images/pepperoni/plate.png").getImage();
	private ImageIcon happyPizza = new ImageIcon("images/pepperoni/happyPizza.png");
	private ImageIcon sadPizza = new ImageIcon("images/pepperoni/sadPizza.png");
	
	private Game game;
	
	private PepperoniPause pausePanel;
	
	private JLabel pepperoniCount;
	private JLabel pizzaFace;
	private JLabel remainingCount;
	
	private boolean isSpace = false;
	private boolean backUp = false, plateUp = false;
	
    private Pepperoni pepperoni;
    private ArrayList<Pepperoni> pepperonis;
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
		
		draw();
		
        x = (1280/2)-62;
        d = 1;
        floor = 665;
        
        pepperonis = new ArrayList<Pepperoni>();
        
        addKeyListener(new MyKeyListener());
	}
	
	public void startGame() {
		if(isFirst) {
			pepperoni = new Pepperoni(x, d, floor, this, game);
			isFirst = false;
		} else {
			pepperoni = new Pepperoni(x, d, floor, th, this, game);
		}
        add(pepperoni);
        pepperonis.add(pepperoni);
        
        pepperoniCount.setText(pepperoni.getCount() - 1 + "°³");
        remainingCount.setText("<html>ÆäÆÛ·Î´Ï È¹µæ±îÁö<br/>&emsp;&emsp;&emsp;" + (17 - pepperoni.getCount() - 1) + "°³</html>");
        
        th = new Thread(pepperoni);
        th.start();
        prevTh = th;
	}
	
	public void reset() {
		x = (1280/2)-62;
        d = 1;
        floor = 665;
        backUp = false;
        plateUp = false;
		removeAll();
		setFirst();
		pepperoni.setCount();
		draw();
	}
	
	public void draw() {		
		pepperoniCount = new JLabel();
		pepperoniCount.setFont(pepperoniCount.getFont().deriveFont(65.0f));
		pepperoniCount.setBounds(1100, 120, 150, 100);
		add(pepperoniCount);
		
		pizzaFace = new JLabel(happyPizza);
		pizzaFace.setBounds(50, 50, happyPizza.getIconWidth(), happyPizza.getIconHeight());
		add(pizzaFace);
		
		remainingCount = new JLabel();
		
		remainingCount.setFont(new Font("³ª´®¹Ù¸¥°íµñ", Font.PLAIN, 25));
		remainingCount.setBounds(1015, 35, 260, 100);
		remainingCount.setHorizontalAlignment(JLabel.CENTER);
		add(remainingCount);
		
		pausePanel = new PepperoniPause(game);
		add(pausePanel);
		pausePanel.setVisible(false);
	}
	
	public ArrayList<Pepperoni> getPepperonis() {
		return pepperonis;
	}
	
	public void setSadface() {
		pizzaFace.setIcon(sadPizza);
	}
	
	public void setFirst() {
		isFirst = true;
	}
	
	public void setStop() {
		th.stop();
	}
	
	public void setBackUp(boolean b) {
		backUp = b;
	}
	public void setPlateUp(boolean b) {
		plateUp = b;
	}
	
	public Pepperoni getPepperoni() {
		return pepperoni;
	}
	public Thread getTh() {
		return th;
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
            case KeyEvent.VK_ESCAPE:
            	pausePanel.setVisible(true);
				pepperoni.setPause(true);
            	break;
            }
		}
		@Override
		public void keyReleased(KeyEvent e) {
			int keyCode = e.getKeyCode(); // »ó, ÇÏ, ÁÂ, ¿ì Å°´Â À¯´ÏÄÚµå Å°°¡ ¾Æ´Ô
			
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
		
		if(!backUp)
			g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), null);
		else
			g.drawImage(backgroundImage1, 0, 0, getWidth(), getHeight(), null);
		
		if(pepperoni != null && !plateUp && pepperoni.getCount() <= 11)
			g.drawImage(plate, 528+20, 650, plate.getWidth(null), plate.getHeight(null), null);
		else if(plateUp)
			g.drawImage(plate, 528+20, 680, plate.getWidth(null), plate.getHeight(null), null);
	}
}
