package test;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

class SavingFile extends JPanel implements ActionListener {
	private JButton button = new JButton("확인");
	private JLabel label = new JLabel("점수를 저장합니다.");
	private JPanel panel = new JPanel();
	private JPanel panel2 = new JPanel();
	private JTextField textField = new JTextField("ID를 입력해주세요", 10);
	private int score = 0;
	private UI ui;
	
	public SavingFile(UI _ui) {
		System.out.println("SavingFile(int _score) 실행됨");
		
		ui = _ui;
		
		panel2.add(label);
		panel2.add(button);
		panel2.add(textField);
		
		panel.setLayout(new BorderLayout());
		panel.add(panel2, BorderLayout.CENTER); //TODO 완벽하게 CENTER로 위치시키기
		this.add(panel);

		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				saveFile();
			}
		});
	}

	public void saveFile() {
		final String FILE = "src/test/SaveFile.txt";
		
		System.out.println("saveFile() 실행됨");
		
		score = ui.getScore();
	
		try{
			BufferedWriter out = new BufferedWriter(new FileWriter(FILE, true));
			String str = textField.getText();
			
			long time = System.currentTimeMillis(); 
			SimpleDateFormat dayTime = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
			String date = dayTime.format(new Date(time));

			out.write(date +" " + str + " : " + score);
			out.newLine();
			
			out.close();

		} catch (IOException e){
			e.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {}


}
