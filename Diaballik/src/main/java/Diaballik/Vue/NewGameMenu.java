package Diaballik.Vue;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import Diaballik.Controllers.ControleurMediateur;
import Diaballik.Models.Jeu;

public class NewGameMenu extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JLabel titre = new JLabel("Nouvelle partie");
	JLabel duree = new JLabel("Duree d'un tour :");
	JLabel priorite = new JLabel("Joue en premier : ");
	JButton retour = new JButton("Retour");
	JButton jouer = new JButton("Jouer");
	JButton humain = new JButton("Humain");
	JButton ordinateur = new JButton("Ordinateur");
	JButton illimite = new JButton("Illimité");
	JButton uneMin = new JButton("1 min");
	JButton deuxMin = new JButton("2 min");
	JButton troisMin = new JButton("3 min");
	JButton joueur1 = new JButton("Joueur 1");
	JButton joueur2 = new JButton("Joueur 2");
	playSound ps = new playSound();
	ihm i;

	//Parametres de la nouvelle partie
	/*
	public enum Joueur {
		humain,
		ordinateur;
	}

	public enum temps {
		illimite, 0
		un, 1
		deux, 2
		trois; 3
	}

	public enum premier {
		joueurun, true
		joueurdeux; false
	}	
	*/

	int tempschrono = 0; //illimite
	boolean human = true; //joueur humain
	boolean first = true; //joueur 1


	public NewGameMenu(ihm ihm) {
		i = ihm;
		this.setLayout(null);
		titre.setBounds(240, 0, 300, 100);
		duree.setBounds(100, 120, 100, 100);
		priorite.setBounds(100, 210, 150, 120);
		jouer.setBounds(350, 390, 120, 40);
		retour.setBounds(210, 390, 120, 40);
		humain.setBounds(210, 100, 120, 40);
		ordinateur.setBounds(350, 100, 120, 40);
		illimite.setBounds(100, 200, 100, 40);
		uneMin.setBounds(220, 200, 100, 40);
		deuxMin.setBounds(340, 200, 100, 40);
		troisMin.setBounds(460, 200, 100, 40);
		joueur2.setBounds(350, 300, 120, 40);
		joueur1.setBounds(210, 300, 120, 40);
		

		Font font = new Font("Arial",Font.BOLD,30);
		titre.setFont(font);
		humain.setBackground(Color.pink);
		illimite.setBackground(Color.pink);
		joueur1.setBackground(Color.pink);
		
		retour.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) { 
            	MenuEnJeu m = new MenuEnJeu(i);
                i.setContentPane(m);
                i.repaint();
                i.revalidate();
            } 
        } );
		
		humain.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) { 
            	humain.setBackground(Color.pink);
				ordinateur.setBackground(null);		
            } 
        } );
		
		ordinateur.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) { 
            	ordinateur.setBackground(Color.pink);
				humain.setBackground(null);
				human = false; //joueur IA
            } 
        } );
		
		illimite.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) { 
            	illimite.setBackground(Color.pink);
				uneMin.setBackground(null);
				deuxMin.setBackground(null);
				troisMin.setBackground(null);
            } 
        } );
		
		uneMin.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) { 
            	uneMin.setBackground(Color.pink);
				illimite.setBackground(null);
				deuxMin.setBackground(null);
				troisMin.setBackground(null);
				tempschrono = 1;
			} 
        } );
		
		deuxMin.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) { 
            	deuxMin.setBackground(Color.pink);
				illimite.setBackground(null);
				uneMin.setBackground(null);
				troisMin.setBackground(null);
				tempschrono = 2;
            } 
        } );
		
		troisMin.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) { 
            	troisMin.setBackground(Color.pink);
				illimite.setBackground(null);
				deuxMin.setBackground(null);
				uneMin.setBackground(null);
				tempschrono = 3;
            } 
        } );
		
		joueur1.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) { 
            	joueur1.setBackground(Color.pink);
				joueur2.setBackground(null);
            } 
        } );
		
		joueur2.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) { 
            	joueur2.setBackground(Color.pink);
				joueur1.setBackground(null);
				first = false; //joueur 2
            } 
        } );
		
		jouer.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) { 
            	ps.play("son/buttonClick.wav");
				//SwingUtilities.getWindowAncestor(this).dispose();
				//super.setVisible(false);
				Jeu j = new Jeu();
				CollecteurEvenements control = new ControleurMediateur(j);
				Plateau.demarrer(j,control);
				
            } 
        } );
		
		this.add(joueur1);
		this.add(joueur2);
		this.add(priorite);
		this.add(illimite);
		this.add(uneMin);
		this.add(deuxMin);
		this.add(troisMin);
		this.add(duree);
		this.add(ordinateur);
		this.add(humain);
		this.add(jouer);
		this.add(retour);
		this.add(titre);
		this.setVisible(true);
	}
	

}
