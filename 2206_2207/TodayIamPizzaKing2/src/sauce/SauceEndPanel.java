package sauce;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.WindowConstants;

import code.Game;

public class SauceEndPanel extends JDialog {
	private JButton ok;
	
	private ImageIcon endImg = new ImageIcon("images/stage/endImg.png");
	
	public SauceEndPanel(Game game) {
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
				game.saucePanel.setVisible(false);
				game.stageSelectPanel.setVisible(true);
			}
		});
	}	
	
	public void Clear() {
		
		setTitle("clear");
		setVisible(true);
	}
	
	public void Dead() {
		setTitle("fail");
		setVisible(true);
	}
}
