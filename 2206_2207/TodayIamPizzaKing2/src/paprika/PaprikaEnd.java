package paprika;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.WindowConstants;

class PaprikaEnd extends JDialog {
	
	private ImageIcon endImg = new ImageIcon("images/paprika/endImg.png");
	private JButton ok;
	
	public PaprikaEnd(code.Game game) {
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
				game.paprikaPanel.setVisible(false);
				game.stageSelectPanel.setVisible(true);
				game.paprikaPanel.reset();
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