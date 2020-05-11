package Diaballik.Vue;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import javax.swing.JFrame;

public class ihm extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Menu menu = new Menu(this);
	NewGame ng = new NewGame(this);
	JouerReseau jr = new JouerReseau(this);
	Image icon = Toolkit.getDefaultToolkit().getImage("src/main/java/Diaballik/Vue/img/pionA_ballon.png");  
	Dimension DimMax = Toolkit.getDefaultToolkit().getScreenSize();
	
	playSound ps = new playSound();
	

	public ihm() {
		 
		this.setIconImage(icon); 
		this.setTitle("Menu principal");
		this.setSize(600, 510);
		
		this.addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent windowEvent) {
				if(msgBox.msgYesNo("Voulez-vous quitter?", "Quitter") == 0){
					System.exit(0);
                }
                else{
                    return;
                }
			}
        });
		
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	    this.setLocationRelativeTo(null);
	    this.add(menu);
	    this.setVisible(true);
	}
	
	public void fenetreNouvellePartie() {
		ps.play("son/buttonClick.wav");
		//if (this.getWidth() == 600 && this.getHeight() == 510) {
			this.setSize(601, 550);
			
		//} else {
			
		//	this.setMaximumSize(DimMax);

			//this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		//}
		
		NewGame ng = new NewGame(this);	
		this.setContentPane(ng);
		this.setTitle("Nouvelle partie");
		this.repaint();
		this.revalidate();
	}

	public void fenetreChargerPartie() {
		ps.play("son/buttonClick.wav");
		this.setSize(600, 510);
		ChargerPartie cp = new ChargerPartie(this);		
		this.setContentPane(cp);
		this.setTitle("Charger partie");
		this.repaint();
		this.revalidate();
	}
	
	public void fenetreJouerEnReseau() {
		ps.play("son/buttonClick.wav");
		this.setSize(600, 511);
		this.setSize(600, 510);
		JouerReseau jr = new JouerReseau(this);
		this.setContentPane(jr);
		this.setTitle("Jouer en réseau");
		this.repaint();
		this.revalidate();
	}
	
	public void retourMenuPrincipal() {
		ps.play("son/buttonClick.wav");
		this.setSize(600, 511);
		this.setSize(600, 510);
		this.setLocationRelativeTo(null);
		Menu m = new Menu(this);
		this.setContentPane(m);
		this.setTitle("Menu principal");
		this.repaint();
		this.revalidate();
	}
	
	public void fenetreCreerPartieReseau() {
		ps.play("son/buttonClick.wav");
		this.setSize(600, 401);
		this.setSize(600, 400);
		CreerPartieReseau crr = new CreerPartieReseau(this);
		this.setContentPane(crr);
		this.setTitle("Créer une partie en réseau");
		this.repaint();
		this.revalidate();
	}
	
	public void fenetreRejoindrePartieReseau() {
		ps.play("son/buttonClick.wav");
		this.setSize(600, 401);
		this.setSize(600, 400);
		RejoindrePartieReseau rpr = new RejoindrePartieReseau(this);
		this.setContentPane(rpr);
		this.setTitle("Rejoindre une partie");
		this.repaint();
		this.revalidate();
	}
	
	public void fenetreAttenteJoueurReseau() {
		ps.play("son/buttonClick.wav");
		this.setSize(600, 401);
		this.setSize(600, 400);
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
		msgBox.MessageBox("Voulez-vous quitter le jeu ? ", "Quitter", this);
	}
	
}
