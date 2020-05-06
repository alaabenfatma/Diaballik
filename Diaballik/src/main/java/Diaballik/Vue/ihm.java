package Diaballik.Vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class ihm extends JFrame implements ActionListener{
	
	Menu menu = new Menu(this);
	NewGame ng = new NewGame(this);
	JouerReseau jr = new JouerReseau(this);
	Image icon = Toolkit.getDefaultToolkit().getImage("src/main/java/Diaballik/Vue/img/pionA_ballon.png");   
	JMenuBar mb = new JMenuBar();
	JMenu m1 = new JMenu("Thèmes");
	JMenu m2 = new JMenu("Options");
	JMenuItem mi1 = new JMenuItem("Daltonien");
	playSound ps = new playSound();
	

	public ihm() {
		 
		this.setIconImage(icon); 
		this.setTitle("Menu principal");
		this.setSize(700, 530);
		this.addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent windowEvent) {
				if(msgBox.msgYesNo("Voulez-vous quitter?", "Quitter")==0){
					System.exit(0);
                }
                else{
                    return;
                }
			}
        });
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	    this.setLocationRelativeTo(null);
	    m1.add(mi1);
	    mb.add(m1);
	    mb.add(m2);
	    this.add(mb, BorderLayout.NORTH);
	    this.add(menu);
	    this.setVisible(true);
	}
	
	public void fenetreNouvellePartie() {
		ps.play("son/buttonClick.wav");
		NewGame ng = new NewGame(this);		
		this.setContentPane(ng);
		this.setTitle("Nouvelle partie");
		this.repaint();
		this.revalidate();
	}

	public void fenetreChargerPartie() {
		ps.play("son/buttonClick.wav");
		ChargerPartie cp = new ChargerPartie(this);		
		this.setContentPane(cp);
		this.setTitle("Charger partie");
		this.repaint();
		this.revalidate();
	}
	
	public void fenetreJouerEnReseau() {
		ps.play("son/buttonClick.wav");
		JouerReseau jr = new JouerReseau(this);
		this.setContentPane(jr);
		this.setTitle("Jouer en réseau");
		this.repaint();
		this.revalidate();
	}
	
	public void retourMenuPrincipal() {
		ps.play("son/buttonClick.wav");
		this.setSize(700, 530);
		this.setLocationRelativeTo(null);
		Menu m = new Menu(this);
		this.setContentPane(m);
		this.setTitle("Menu principal");
		this.repaint();
		this.revalidate();
	}
	
	public void fenetreCreerPartieReseau() {
		ps.play("son/buttonClick.wav");
		CreerPartieReseau crr = new CreerPartieReseau(this);
		this.setContentPane(crr);
		this.setTitle("Créer une partie en réseau");
		this.repaint();
		this.revalidate();
	}
	
	public void fenetreRejoindrePartieReseau() {
		ps.play("son/buttonClick.wav");
		RejoindrePartieReseau rpr = new RejoindrePartieReseau(this);
		this.setContentPane(rpr);
		this.setTitle("Rejoindre une partie");
		this.repaint();
		this.revalidate();
	}
	
	public void fenetreAttenteJoueurReseau() {
		ps.play("son/buttonClick.wav");
		AttenteJoueurReseau ajr = new AttenteJoueurReseau(this);
		this.setContentPane(ajr);
		this.setTitle("Attente du 2ème joueur");
		this.repaint();
		this.revalidate();
	}
	
	public void fenetreRegles() {
		ps.play("son/buttonClick.wav");
		this.setSize(800, 620);
		this.setLocationRelativeTo(null);
		Regles r = new Regles(this);
		this.setContentPane(r);
		this.setTitle("Règles du jeu");
		this.repaint();
		this.revalidate();
	}
	
	
	public void quit() {
		ps.play("son/buttonClick.wav");
		msgBox msg = new msgBox();
		msg.MessageBox("Voulez-vous quitter le jeu ? ", "Quitter", this);
	}
	
	public void actionPerformed(ActionEvent arg0) {
		
	}
	
}
