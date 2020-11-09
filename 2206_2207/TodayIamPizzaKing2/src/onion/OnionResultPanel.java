package onion;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import code.Game;

public class OnionResultPanel extends JDialog{
	
	private JButton ok;
	
	private ImageIcon endImg = new ImageIcon("images/stage/endImg.png");
	
	public OnionResultPanel(Game game) {
		// TODO Auto-generated constructor stub
		setSize(endImg.getIconWidth(), endImg.getIconHeight());
		setLocationRelativeTo(null);	
		setModal(true);
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		add(new JLabel(endImg));
		pack();
		
		ok = new JButton("»Æ¿Œ");
		ok.setSize(200, 100);
		add(ok);
		ok.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				game.onionPanel.setVisible(false);
				game.stageSelectPanel.setVisible(true);
			}
		});
		
		
	}

}
