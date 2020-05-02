package Diaballik.Vue;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class ihm extends JFrame implements ActionListener{
	
	Menu menu = new Menu();
	NewGame ng = new NewGame();

	public ihm() {
	
		this.setTitle("Menu principal");
		this.setSize(700, 500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setLocationRelativeTo(null);
	    this.add(menu);
	    this.setVisible(true);
	}
	
	public void changementfenetre() {
		this.remove(menu);
		NewGame ng = new NewGame();
		this.setSize(699, 499);
		this.setTitle("Nouvelle partie");
		this.add(ng);
		this.validate();
		this.getContentPane().add(ng);
		this.repaint();
	}

	
	
	public void actionPerformed(ActionEvent arg0) {
		
	}
	
}
