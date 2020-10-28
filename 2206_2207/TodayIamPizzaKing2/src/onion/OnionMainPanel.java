package onion;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import code.Game;

public class OnionMainPanel extends JPanel {
	Image background=new ImageIcon("images/stage/stage1Back.png").getImage();
	
	public OnionMainPanel(Game game){
		setLayout(null);
		setBounds(0, 0, 1280, 720);
		
		
	}
	public void paint(Graphics g) {//그리는 함수
			g.drawImage(background, 0, 0, null);//background를 그려줌		
	}
}
