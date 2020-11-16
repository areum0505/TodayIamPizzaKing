package onion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import code.Game;

class OnionEnd extends JDialog {
	
	private ImageIcon endImg = new ImageIcon("images/paprika/endImg.png");
	private JButton ok;
	
	public OnionEnd(Game game) {
		setSize(endImg.getIconWidth(), endImg.getIconHeight());
		setLocationRelativeTo(null);	
		setModal(true);
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		add(new JLabel(endImg));
		pack();
		
		ok = new JButton("»Æ¿Œ");
		ok.setBounds(0, 0, 10, 10);
		add(ok);
		ok.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				game.onionPanel.setVisible(false);
				game.onionPanel.reset();
				game.stageSelectPanel.setVisible(true);
			}
		});
	}
	public void Success() {
		setTitle("success");
		setVisible(true);
		
	}
	
	public void Fail() {
		
		setTitle("fail");
		setVisible(true);
	}
	
}	