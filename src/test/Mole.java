package test;

import java.awt.CardLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Mole extends JFrame {
	private CardLayout cards = new CardLayout();
	private JPanel panel = new JPanel(cards);
	private JButton button = new JButton();
	private UI ui;
	private SavingFile sf;
	private UI.Function func;
	
	public Mole() {
		System.out.println("Mole() ½ÇÇàµÊ");
		
		panel.add("First", ui = new UI(this));
		panel.add("Second", sf = new SavingFile(ui));
		
		ui.setTime(60);
		
		this.add(panel);
		this.setSize(900, 900);
		this.setVisible(true);
	}
	
	public void start() {
		System.out.println("Mole.start() ½ÇÇàµÊ");
		
		func = ui.new Function();
		func.stratMoleEmerging();
		
	}
	
	public void changePanel() {
		cards.next(this.panel);
	}
	
	public static void main(String[] args) {
		Mole m = new Mole();
		
		m.start();
	}
}
