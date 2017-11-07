package test;


import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

class UI extends JPanel {
	UI.Function func = new Function();

	private JButton[] holes = new JButton[9];
	private JLabel scoreLabel = new JLabel();
	private final ImageIcon MOLE_IMG = new ImageIcon("src/img/mole.png"); 
	private final ImageIcon HOLE_IMG = new ImageIcon("src/img/hole.png");
	private final ImageIcon BOOM_IMG = new ImageIcon("src/img/boom.png");
	private int score = 0;
	private Mole m;
	private int time = 0;

	public UI() {
		System.out.println("UI() �����");

		this.setLayout(new GridLayout(3,2)); 

		int count = 0;
		for(JButton i:holes){
			holes[count] = new JButton(Integer.toString(count), HOLE_IMG);
			holes[count].addActionListener(new Function());
			this.add(holes[count]);
			count++;
		}
	}

	public UI(Mole _m) {
		System.out.println("UI(Mole _m) �����");

		m = _m;
		this.setLayout(new GridLayout(3,2)); 

		int count = 0;
		for(JButton i:holes){
			holes[count] = new JButton(Integer.toString(count), HOLE_IMG);
			holes[count].addActionListener(new Function());
			this.add(holes[count]);
			count++;
		}
	}

	class Function implements ActionListener {
		private Random random = new Random();


		public void stratMoleEmerging(){
			System.out.println("startMoleEmerging() �����");
			Thread thread;


			class Emerging implements Runnable {	
				@Override 
				public void run() {

					for(int j=0; j<time; j++) {
						try{
							Thread.sleep(random.nextInt(1000));
						} catch (Exception e) {
							e.printStackTrace();
						}

						for(int i=0; i<random.nextInt(9); i++) {
							int moleAppearInHoleNum = random.nextInt(9);

							holes[moleAppearInHoleNum].setIcon(MOLE_IMG);
						}

						for(int i=0; i<random.nextInt(9); i++) {
							int moleAppearInHoleNum = random.nextInt(9);

							holes[moleAppearInHoleNum].setIcon(HOLE_IMG);
						}
					}
				}
			}
			System.out.println("Thread ����");
			(thread = new Thread(new Emerging())).start();

			//���� thread�� ����ִ��� Ȯ��
			new Thread(new Runnable() {
				public void run(){
					while(thread.isAlive()){}
					m.changePanel();
					System.out.println("Thread ����");
				}
		}).start();

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(! holes[Integer.parseInt(e.getActionCommand())].getIcon().equals(HOLE_IMG)){ //Hole ������ �ƴ� ��
			if(! holes[Integer.parseInt(e.getActionCommand())].getIcon().equals(BOOM_IMG)){ //Boom ������ �ƴ� �� 
				System.out.println(holes[Integer.parseInt(e.getActionCommand())].getActionCommand() + "Ŭ����");
				addScore(10); 
				holes[Integer.parseInt(e.getActionCommand())].setIcon(BOOM_IMG);
			}
		}
	}


}//function class end

public void setTime(int _time) {
	time = _time;
}

public void addScore(int _score) {
	score += _score;
}

public int getScore() {
	return score;
}
}//UI class end

