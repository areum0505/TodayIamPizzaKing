package sauce;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Label;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import code.Game;

public class SauceEndPanel extends JDialog {
	private Container pane;
	
	private JButton ok;
	private JLabel text;
	
	private ImageIcon endImg = new ImageIcon("images/stage/endImg.png");
	private ImageIcon successImg = new ImageIcon("images/stage/successText.png");
	private ImageIcon failImg = new ImageIcon("images/stage/failText.png");
	
	public SauceEndPanel(Game game) {
		setSize(endImg.getIconWidth(), endImg.getIconHeight());
		setLocationRelativeTo(null);	
		setModal(true);
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		add(new JLabel(endImg));
		setResizable(false);
		//pack();
		
		Container pane = getContentPane();
		pane.setLayout(null);
		
		JLabel l = new JLabel(endImg);
		l.setBounds(0, 0, 800, 500);
		l.setVisible(true);
		
		text = new JLabel();
		text.setBounds((int)(800/2)-(successImg.getIconWidth()/2)+5, 5, successImg.getIconWidth(), successImg.getIconHeight());
		text.setVisible(false);
		
		ok = new JButton("»Æ¿Œ");
		ok.setBounds((int)(800/2)-(125/2), 500-75-45, 150, 75);
		ok.setVisible(false);
		ok.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				game.saucePanel.setVisible(false);
				game.stageSelectPanel.setVisible(true);
			}
		});
		
		//pane.add(l);
		pane.add(ok);
		pane.add(text);
	}	
	
	public void Success() {
		setTitle("success");
		
		text.setIcon(successImg);
		
		text.setVisible(true);
		ok.setVisible(true);
		
		setVisible(true);
	}
	
	public void Fail() {
		setTitle("fail");
		
		text.setIcon(failImg);
		
		text.setVisible(true);
		ok.setVisible(true);
		
		setVisible(true);
	}
}
