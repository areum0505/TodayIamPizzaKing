package onion;

import javax.swing.JFrame;

import code.Game;



public class Onion extends JFrame{
	
	public OnionFailPanel onionFailPanel = new OnionFailPanel(this);
	
	public Onion(){
		setSize(800,500);
		add(onionFailPanel);
		onionFailPanel.setVisible(true);
		
	}

}
