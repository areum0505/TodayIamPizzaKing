package mushroom;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import code.Game;


class MushroomEnd extends JDialog {
	
	private ImageIcon winImg = new ImageIcon("images/stage/winImg.png");
	private ImageIcon loseImg = new ImageIcon("images/stage/loseImg.png");
	JPanel jp;
	private JButton ok, replay;
	JLabel winBack, loseBack, flagBack;
	
	public MushroomEnd(Game game) {
		
		setSize(815, 545);
		setLocationRelativeTo(null);	
		setModal(true);
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
	
		jp = new JPanel();
		
		jp.setLayout(null);
		jp.setBounds(0,0, 800, 500);
		
		
		flagBack = new JLabel();
		flagBack.setBounds(0,0, 800, 500);
		jp.add(flagBack);
		
		
		ok = new JButton();
		add(ok);
		ok.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				game.mushroomPanel.setVisible(false);
				game.stageSelectPanel.setVisible(true);
				game.mushroomPanel.endGame();
			}
		});
		replay = new JButton();
		add(replay);
		replay.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				game.mushroomPanel.setVisible(false);
				game.stageSelectPanel.setVisible(true);
				game.mushroomPanel.endGame();
			}
		});
		
		
		ok.setBorderPainted(false);
		ok.setContentAreaFilled(false);
		ok.setVisible(false);
		
		replay.setBorderPainted(false);
		replay.setContentAreaFilled(false);
		replay.setVisible(false);
		
		add(jp);
	}
	public void Success() {
		setTitle("success");	
		
		flagBack.setIcon(winImg);
		
		ok.setBounds(235, 306, 330, 110);
		ok.setVisible(true);
	
		jp.setVisible(true);
		
		setVisible(true);
		
	}
	
	public void Fail() {
		
		setTitle("fail");
		
		flagBack.setIcon(loseImg);
		
		replay.setBounds(55, 317, 330, 110);
		replay.setVisible(true);
		
		ok.setBounds(415, 317, 330, 110);
		ok.setVisible(true);
		
		jp.setVisible(true);
		
		setVisible(true);
	}
	
	
}	