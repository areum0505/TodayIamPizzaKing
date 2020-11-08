package test01;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main extends JFrame{
	/*��ư*/
	static JButton b1=new JButton("��ư1");
	/*�г�1*/
	static JPanel page1=new JPanel() {
		/*�̹���*/
		Image background=new ImageIcon("images/main/setBackground.png").getImage();
		public void paint(Graphics g) {//�׸��� �Լ�
				g.drawImage(background, 0, 0, null);//background�� �׷���		
		}
	};
	/*�г�2*/
	static JPanel page2=new JPanel() {
		/*�̹���*/
		Image background=new ImageIcon("images/main/stageSelectBackground.png").getImage();
		public void paint(Graphics g) {//�׸��� �Լ�
				g.drawImage(background, 0, 0, null);//background�� �׷���		
		}
	};
	public Main() {
		homeframe();//homeframe�Լ��� ����
		setpanel();//setpanel�Լ��� ����
		customcursor();//customcursor�Լ��� ����
		cg();//cg�Լ��� ����
	}
	/*Ŀ������*/
	public void customcursor(){
		/*Ŀ���������*/
		Toolkit tk = Toolkit.getDefaultToolkit();
		Image cursorimage=tk.getImage("images/main/backButton.png");
		Point point=new Point(20,20);
		Cursor cursor=tk.createCustomCursor(cursorimage, point, "haha");
		page1.setCursor(cursor); 
	}
	/*������ ����*/
	public void homeframe() {
		setTitle("1");//Ÿ��Ʋ
		setSize(400,400);//�������� ũ��
		setResizable(false);//â�� ũ�⸦ �������� ���ϰ�
		setLocationRelativeTo(null);//â�� ��� ������
		setLayout(null);//���̾ƿ� ����
		setVisible(true);//â�� ���̰�	
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//JFrame�� ���������� ����ǰ�
	}
	/*�г� ���� ����*/
	public void setpanel() {
		/*��ġ ����*/
		b1.setBounds(0, 0, 100, 100);//��ư1�� ��ġ ����
		page1.setBounds(0, 0, 400, 400);//�г�1�� ��ġ ����
		page2.setBounds(0, 0, 400, 400);//�г�2�� ��ġ ����
		/*���̾ƿ� ����*/
		page2.setLayout(null);//���̾ƿ� ����
		page1.setLayout(null);//���̾ƿ� ����
		/*visible*/
		page2.setVisible(false);//â�� ������ �ʰ�
		/*�г��̳� �����ӿ� �߰�*/
		add(page1);//�����ӿ� �г��� �߰�
		add(page2);//�����ӿ� �г��� �߰�
		page1.add(b1);//�г�1�� ��ư�� �߰�
	}
	/*���콺 �̺�Ʈ*/
	public void cg(){
		b1.addMouseListener(new MouseAdapter() { // ���콺 �̺�Ʈ 
			@Override public void mouseEntered(MouseEvent e) { // ���콺 �������� 
			} 
			@Override public void mouseExited(MouseEvent e) { // ���콺 �������� 	
			}
			@Override public void mousePressed(MouseEvent e) { // Ŭ�������� 
				page1.setVisible(false);//â�� ���̰�
				page2.setVisible(true);//â�� ���̰�
				System.out.println("������");//���ȴ��� Ȯ���Ϸ��� ����.
			} 
		});
	}
	/*�����Լ�*/
	public static void main(String[] args){
		new Main(); //�����ϸ� �����ڰ� �����.
	}
}