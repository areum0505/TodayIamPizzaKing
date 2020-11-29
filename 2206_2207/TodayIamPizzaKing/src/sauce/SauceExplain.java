package sauce;

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

public class SauceExplain extends JDialog{

	Game game;
	
	private ImageIcon explainImg = new ImageIcon("images/sauce/sauceExplain.png");
	
	public JButton sauceGameBtn = new JButton();
	JPanel jp;
	JLabel back;
	
	public SauceExplain(Game game) {
		
		setTitle("Sauce Stage Explain");
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
		
		sauceGameBtn.setVisible(true);
		sauceGameBtn.setBounds(525, 460, 328, 92);
		sauceGameBtn.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (Main.buttonEffect) {
					Music buttonClick = new Music("buttonClick1.mp3", false);
					buttonClick.start();
				}
				setVisible(false);
				game.stageSelectPanel.setVisible(false);
				game.saucePanel.setVisible(true);
				game.saucePanel.setFocusable(true);	
				game.saucePanel.requestFocus();
				game.saucePanel.startGame();
				
			}
		});
		jp.add(sauceGameBtn);
		
		sauceGameBtn.setBorderPainted(false);
		sauceGameBtn.setContentAreaFilled(false);
		
		add(jp);
		jp.setVisible(true);
	}


}
