package onion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import code.Game;

class OnionEnd extends JDialog {
	
	private ImageIcon winImg = new ImageIcon("images/stage/winImg.png");
	private ImageIcon loseImg = new ImageIcon("images/stage/loseImg.png");
	JPanel jp;
	private JButton ok;
	JLabel winBack, loseBack;
	
	public OnionEnd(Game game) {
		
		setSize(800, 540);
		setLocationRelativeTo(null);	
		setModal(true);
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
	
		ok = new JButton("»Æ¿Œ");
		ok.setBounds(235, 356, 330, 110);
		add(ok);
		ok.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				game.onionPanel.reset();
				game.onionPanel.setVisible(false);
				game.stageSelectPanel.setVisible(true);
			}
		});
		
		ok.setVisible(false);

	}
	public void Success() {
		setTitle("success");
		
		jp = new JPanel();
		
		jp.setLayout(null);
		jp.setBounds(0,0, 800, 500);
		
		winBack = new JLabel(winImg);
		winBack.setBounds(0,0, 800, 500);
		jp.add(winBack);
		
		
		ok.setVisible(true);
		
		add(jp);
		jp.setVisible(true);
		
		setVisible(true);
		
		
	}
	
	public void Fail() {
		
		setTitle("fail");
		jp = new JPanel();
		
		jp.setLayout(null);
		jp.setBounds(0,0, 800, 500);
		
		loseBack = new JLabel(loseImg);
		loseBack.setBounds(0,0, 800, 500);
		jp.add(loseBack);
		
		
		add(jp);
		jp.setVisible(true);
		
		setVisible(true);
	}
	
}	