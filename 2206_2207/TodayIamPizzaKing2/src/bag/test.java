package bag;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

class numLabel extends JLabel implements Runnable {
	public numLabel(String text) {
		setText(text);
	}

	@Override
	public void run() {
		while(true) {
			int n = (int) (Math.random() * 10);
			setText(String.valueOf(n));
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

class SlotMachine extends JPanel implements ActionListener {
	numLabel[] labels;
	JButton button;
	int[] numbers;
	
	Thread[] th;

	public SlotMachine() {
		setLayout(null);
		setBounds(0, 0, 900, 600);
		setBackground(Color.GREEN);

		labels = new numLabel[3];
		numbers = new int[3];
		th = new Thread[3];

		for (int i = 0; i < 3; i++) {
			labels[i] = new numLabel(String.valueOf(numbers[i]));
			labels[i].setFont(new Font("Serif", Font.BOLD, 100));
			labels[i].setSize(100, 100);
			labels[i].setLocation(100 + 100 * i, 20);

			th[i] = new Thread(labels[i]);
			th[i].start();
			
			add(labels[i]);
		}

		button = new JButton();
		button.setSize(250, 50);
		button.setLocation(100, 150);
		add(button);

		button.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		for (int i = 0; i < 3; i++) {
			th[i].stop();
		}
	}
}

public class test extends JFrame {
	public test() {
		setSize(900, 600);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);

		SlotMachine sm = new SlotMachine();

		add(sm);
	}

	public static void main(String[] args) {
		new test();
	}
}