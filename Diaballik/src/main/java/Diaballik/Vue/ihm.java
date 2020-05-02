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
		this.setTitle("Charger partie");
		this.setContentPane(cp);
		this.repaint();
		this.revalidate();
	}
	
	public void fenetreJouerEnReseau() {
		this.remove(menu);
		JouerReseau jr = new JouerReseau(this);
		this.setContentPane(jr);
		this.setTitle("Jouer en réseau");
		this.repaint();
		this.revalidate();
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
