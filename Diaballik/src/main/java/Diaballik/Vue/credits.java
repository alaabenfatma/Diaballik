package Diaballik.Vue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class credits {
	
	JFrame frame = new JFrame();
	JPanel panel = new JPanel();
	JLabel hedi = new JLabel ("Hedi TURKI SANEKLI");
	JLabel alaa = new JLabel ("Alaa BEN FATMA");
	JLabel ludo = new JLabel ("Ludovic LANG");
	JLabel wassim = new JLabel ("Wassim AYARI");
	JLabel thomas = new JLabel ("Thomas NOËL-LARDIN");
	JLabel yohan = new JLabel ("Yohan LANDREAUD");
	
	public credits() {
		frame.setSize(300, 300);
		frame.setTitle("Credits");
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		panel.setLayout(null);
		
		alaa.setBounds(90, 10, 150, 30);
		hedi.setBounds(90, 50, 150, 30);
		ludo.setBounds(90, 90, 150, 30);
		wassim.setBounds(90, 130, 150, 30);
		thomas.setBounds(90, 170, 150, 30);
		yohan.setBounds(90, 210, 150, 30);
		
		panel.add(hedi);
		panel.add(alaa);
		panel.add(ludo);
		panel.add(thomas);
		panel.add(wassim);
		panel.add(yohan);
		
		panel.setVisible(true);
		frame.add(panel);
		frame.setVisible(true);
	}
}
