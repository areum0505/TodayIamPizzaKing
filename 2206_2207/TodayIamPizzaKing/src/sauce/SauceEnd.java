package sauce;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import code.Game;
import code.Main;
import code.Music;


class SauceEnd extends JDialog {
	
	private ImageIcon winImg = new ImageIcon("images/sauce/sauce_win.png");
	private ImageIcon loseImg = new ImageIcon("images/sauce/sauce_lose.png");
	JPanel jp;
	private JButton ok;
	JLabel winBack, loseBack, flagBack;
	
	public SauceEnd(Game game) {
		
		setSize(800, 530);
		setLocationRelativeTo(null);	
		setModal(true);
		setResizable(false);
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
				if (Main.buttonEffect) {
					Music buttonClick = new Music("buttonClick1.mp3", false);
					buttonClick.start();
				}
				setVisible(false);
				game.saucePanel.getPlayer().stop();
				for(Beam b : game.saucePanel.getBeam()) {
					b.setEmptyImg();
					b.setStop(true);
				}
				game.stageSelectPanel.setVisible(true);
				game.saucePanel.setVisible(false);
				setVisible(false);
			}
		});		
		
		ok.setBorderPainted(false);
		ok.setContentAreaFilled(false);
		ok.setVisible(false);
		
		
		add(jp);
	}
	public void Success() { //성공했을시
		setTitle("Success");	
		
		flagBack.setIcon(winImg);
		
		ok.setBounds(394, 362, 223, 79);
		ok.setVisible(true);
	
		jp.setVisible(true);
		
		setVisible(true);
		
	}
	
	public void Fail() { //실패했을시
		
		setTitle("Fail");
		
		flagBack.setIcon(loseImg);
		
		ok.setBounds(394, 362, 223, 79);
		ok.setVisible(true);
		
		jp.setVisible(true);
		
		setVisible(true);
	}
	
	
}	