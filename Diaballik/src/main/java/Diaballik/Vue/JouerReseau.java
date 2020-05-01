package Diaballik.Vue;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

public class JouerReseau extends Menu implements ActionListener{
	JPanel panel1 = new JPanel();
	
	
	public JouerReseau() {
		this.setTitle("Jouer en réseau");
		this.remove(panel);
		this.add(panel1);
		this.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent arg0) {
		
	}
}
