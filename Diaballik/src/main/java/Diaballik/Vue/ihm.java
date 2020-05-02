package Diaballik.Vue;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class ihm extends JFrame implements ActionListener{
	
	Menu menu = new Menu(this);
	NewGame ng = new NewGame(this);
	JouerReseau jr = new JouerReseau(this);

	public ihm() {
	
		this.setTitle("Menu principal");
		this.setSize(700, 500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setLocationRelativeTo(null);
	    this.add(menu);
	    this.setVisible(true);
	}
	
	public void fenetreNouvellePartie() {
		this.remove(menu);
		NewGame ng = new NewGame(this);		
		this.setContentPane(ng);
		this.setTitle("Nouvelle partie");
		this.repaint();
		this.revalidate();
	}

	public void fenetreChargerPartie() {
		this.remove(menu);
		ChargerPartie cp = new ChargerPartie(this);
		this.setSize(699, 499);
		this.setTitle("Charger partie");
		this.add(cp);
		this.validate();
		this.getContentPane().add(cp);
		this.repaint();
	}
	
	public void fenetreJouerEnReseau() {
		this.remove(menu);
		JouerReseau jr = new JouerReseau(this);
		this.setSize(699, 499);
		this.setTitle("Jouer en réseau");
		this.add(jr);
		this.validate();
		this.getContentPane().add(jr);
		this.repaint();
	}
	
	public void retourMenuPrincipal() {
		this.remove(ng);
		this.remove(jr);
		Menu m = new Menu(this);
		this.setContentPane(m);
		this.setTitle("Menu principal");
		this.repaint();
		this.revalidate();
	}
	
	
	public void actionPerformed(ActionEvent arg0) {
		
	}
	
}
