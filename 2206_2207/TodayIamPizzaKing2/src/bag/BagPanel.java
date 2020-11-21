package bag;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class BagPanel extends JPanel {
	private Image backgroundImage = new ImageIcon("images/stage/endImg.png").getImage();

	private ImageIcon backButtonImg = new ImageIcon("images/main/backButton.png");

	private JButton backButton = new JButton(backButtonImg);
	
	private JLabel sauceLabel, mushLabel, papLabel, onionLabel, pepperLabel, cheeseLabel;

	public BagPanel() {
		setLayout(null);
		setBounds(250, 110, 800, 500);

		sauceLabel = new JLabel("소스");
		sauceLabel.setBounds(10, 10, 100, 50);
		sauceLabel.setVisible(false);
		add(sauceLabel);
		mushLabel = new JLabel("버섯");
		mushLabel.setBounds(270, 10, 100, 50);
		mushLabel.setVisible(false);
		add(mushLabel);
		papLabel = new JLabel("파프리카");
		papLabel.setBounds(530, 10, 100, 50);
		papLabel.setVisible(false);
		add(papLabel);
		onionLabel = new JLabel("양파");
		onionLabel.setBounds(10, 260, 100, 50);
		onionLabel.setVisible(false);
		add(onionLabel);
		pepperLabel = new JLabel("페퍼로니");
		pepperLabel.setBounds(270, 260, 100, 50);
		pepperLabel.setVisible(false);
		add(pepperLabel);
		cheeseLabel = new JLabel("치즈");
		cheeseLabel.setBounds(530, 260, 100, 50);
		cheeseLabel.setVisible(false);
		add(cheeseLabel);
		
		backButton.setVisible(true);
		backButton.setBounds(800-64, 0, 64, 64);
		backButton.setBorderPainted(false);
		backButton.setContentAreaFilled(false);
		backButton.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				setVisible(false);
			}
		});
		add(backButton);

	}
	
	public void check() {
		try {
			BufferedReader br = new BufferedReader(new FileReader("bag.txt"));
			String s = br.readLine();

			if(s.contains("소스")) {
				sauceLabel.setVisible(true);
			}
			if(s.contains("버섯")) {
				mushLabel.setVisible(true);
			}
			if(s.contains("파프리카")) {
				papLabel.setVisible(true);
			}
			if(s.contains("양파")) {
				onionLabel.setVisible(true);
			}
			if(s.contains("페퍼로니")) {
				pepperLabel.setVisible(true);
			}
			if(s.contains("치즈")) {
				cheeseLabel.setVisible(true);
			}
			
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), null);
	}

}
