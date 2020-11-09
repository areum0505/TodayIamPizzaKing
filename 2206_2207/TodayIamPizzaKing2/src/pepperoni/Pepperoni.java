package pepperoni;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Pepperoni extends JLabel implements Runnable {
	private static int count = 0;
	
	private int x, y;
    private int d;
    private int floor;
    
    private boolean stop = false;

    private ImageIcon pepperoni =  new ImageIcon("images/pepperoni/pepperoni.png");
    private ImageIcon leftPepperoni =  new ImageIcon("images/pepperoni/leftfall.gif");
    private ImageIcon rightPepperoni =  new ImageIcon("images/pepperoni/rightfall.gif");
    
    private Thread prevTh;
    private PepperoniPanel pp;
    
    public Pepperoni(int x, int d, int floor, Thread prevTh, PepperoniPanel pp) {
    	this.prevTh = prevTh;
    	Init(x, d, floor, pp);
        
        setVisible(true);
	}
    public Pepperoni(int x, int d, int floor, PepperoniPanel pp) {
        Init(x, d, floor, pp);
	}
    
    public void Init(int x, int d, int floor, PepperoniPanel pp) {
    	setLayout(null);
    	
    	count++;
        
        this.x = x;
        this.d = d;
        this.floor = floor;
        y = 50;
        
        this.pp = pp;
        
        setIcon(pepperoni);
        setBounds(x, y, pepperoni.getIconWidth(), pepperoni.getIconHeight());
        
        setVisible(true);
    }
    
    public int[] drop() {
        stop = true;
        floor -= 30;
        int[] arr = {x, d, floor};
        return arr;
    }
	
	@Override
	public void run() {
		if(count > 1)
			waitPepperoni();
		movePepperoni();
		dropPepperoni();
		checkPepperoni();
	}
	
	public void waitPepperoni() {
		while(prevTh.isAlive()) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void movePepperoni() {
		while(!stop) {    
            if(d == 1) {
                x += (5+count);
            } else if(d == 0) {
                x -= (5+count);
            } 
            
            if(x < 300) {
                d = 1;
            } else if(x > 870) {
                d = 0;
            }
            
            setLocation(x, y);
            getParent().repaint();
            
            try {
                Thread.sleep(25);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
	}
	
	public void dropPepperoni() {
		while(stop) {
        	y += 15;
        	if(y > floor) 
        		stop = false;
        	
        	setLocation(x, y);
            getParent().repaint();
            
            try {
                Thread.sleep(25);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
		// System.out.println(x + " " + y);
	}
	
	public void checkPepperoni() {
		if(!(540 < x && x < 655)) {
			while(true) {
				if(y > 655)
					break;
				y += 15;
				setLocation(x, y);
	            getParent().repaint();
	            try {
	                Thread.sleep(25);
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }
	            
			}
			pp.setSadface();
		}
	}
	
	public int getCount() {
		return count;
	}
}
