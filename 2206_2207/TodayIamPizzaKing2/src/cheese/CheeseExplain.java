package cheese;

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

public class CheeseExplain extends JDialog{

	Game game;
	
	private ImageIcon explainImg = new ImageIcon("images/cheese/newCheeseExplain.png");
	
	
	public JButton cheeseGameBtn = new JButton();
	JPanel jp;
	JLabel back;
	
	
	public CheeseExplain(Game game) {
		setTitle("Cheese Stage Explain");
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
		
		cheeseGameBtn.setVisible(true);
		cheeseGameBtn.setBounds(525, 460, 328, 92);
		cheeseGameBtn.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				setVisible(false);
				game.stageSelectPanel.setVisible(false);
				game.cheesePanel.setVisible(true);
				game.cheesePanel.setFocusable(true);	
				game.cheesePanel.requestFocus();
				game.cheesePanel.startGame();
				
			}
		});
		jp.add(cheeseGameBtn);
		
		cheeseGameBtn.setBorderPainted(false);
		cheeseGameBtn.setContentAreaFilled(false);
		
		add(jp);
		jp.setVisible(true);
	}


}
