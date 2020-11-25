package onion;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import code.Game;
import code.Main;
import code.Music;

public class OnionExplain extends JDialog{

	Game game;
	
	private ImageIcon explainImg = new ImageIcon("images/onion/newOnionExplain.png");
	
	
	public JButton onionGameBtn = new JButton();
	JPanel jp;
	JLabel back;
	
	
	public OnionExplain(Game game) {
		
		setTitle("Onion Stage Explain");
		setSize(900, 630);
		setLocationRelativeTo(null);	
		setModal(true);
		setResizable(false);
		
		this.game = game;
		
		jp = new JPanel();
		
		jp.setLayout(null);
		jp.setBounds(0,0, 900, 600);
		
		back = new JLabel(explainImg);
		back.setBounds(0,0, 900, 600);
		jp.add(back);
		
		onionGameBtn.setVisible(true);
		onionGameBtn.setBounds(525, 460, 328, 92);
		onionGameBtn.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (Main.buttonEffect) {
					Music buttonClick = new Music("buttonClick1.mp3", false);
					buttonClick.start();
				}
				setVisible(false);
				game.stageSelectPanel.setVisible(false);
				game.onionPanel.setVisible(true);
				game.onionPanel.setFocusable(true);	
				game.onionPanel.requestFocus();
				
			}
		});
		jp.add(onionGameBtn);
		
		onionGameBtn.setBorderPainted(false);
		onionGameBtn.setContentAreaFilled(false);
		
		add(jp);
		jp.setVisible(true);
	}


}
