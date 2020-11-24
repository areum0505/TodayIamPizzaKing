package paprika;

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

public class PaprikaExplain extends JDialog{

	Game game;
	
	private ImageIcon explainImg = new ImageIcon("images/paprika/paprikaExplain.png");
	
	
	public JButton papGameBtn = new JButton();
	JPanel jp;
	JLabel back;
	
	
	public PaprikaExplain(Game game) {
		
		setTitle("Paprika Stage Explain");
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
		
		papGameBtn.setVisible(true);
		papGameBtn.setBounds(525, 460, 328, 92);
		papGameBtn.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				setVisible(false);
				game.stageSelectPanel.setVisible(false);
				game.paprikaPanel.setVisible(true);
				game.paprikaPanel.setFocusable(true);		
				game.paprikaPanel.requestFocus();
				game.paprikaPanel.startGame();
			}
		});
		jp.add(papGameBtn);
		
		papGameBtn.setBorderPainted(false);
		papGameBtn.setContentAreaFilled(false);
		
		add(jp);
		jp.setVisible(true);
	}


}
