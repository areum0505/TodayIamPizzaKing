package pepperoni;

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

public class PepperoniExplain extends JDialog{

	Game game;
	private ImageIcon explainImg = new ImageIcon("images/pepperoni/pepperoniExplain.png");
	public JButton pepGameBtn = new JButton();
	JPanel jp;
	JLabel back;
	
	
	public PepperoniExplain(Game game) {
		
		setTitle("Pepperoni Stage Explain");
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
		
		pepGameBtn.setVisible(true);
		pepGameBtn.setBounds(525, 460, 328, 92);
		pepGameBtn.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				setVisible(false);
				game.stageSelectPanel.setVisible(false);
				game.pepperoniPanel.setVisible(true);
				game.pepperoniPanel.setFocusable(true);		
				game.pepperoniPanel.requestFocus();
				game.pepperoniPanel.startGame();
			}
		});
		jp.add(pepGameBtn);
		
		pepGameBtn.setBorderPainted(false);
		pepGameBtn.setContentAreaFilled(false);
		
		add(jp);
		jp.setVisible(true);
	}


}
