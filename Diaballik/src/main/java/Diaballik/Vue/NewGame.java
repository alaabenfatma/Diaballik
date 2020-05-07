package Diaballik.Vue;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;


import Diaballik.Controllers.ControleurMediateur;
import Diaballik.Models.Jeu;

public class NewGame extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JLabel titre = new JLabel("Nouvelle partie");
	JLabel duree = new JLabel("Durée d'un tour :");
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
	Jeu j;

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


	public NewGame(ihm ihm) {
		
		i = ihm;
		j = new Jeu();
		this.setLayout(null);
		 i.addComponentListener(new ComponentAdapter() {
             public void componentResized(ComponentEvent evt) {
            	titre.setBounds((i.getWidth()/2) - 100, (i.getHeight()/4) - 130, 300, 100);
         		duree.setBounds((i.getWidth()/2) - 240, (i.getHeight()/4) - 10, 100, 100);
         		priorite.setBounds((i.getWidth()/2) - 240, (i.getHeight()/4) + 80, 150, 120);
         		jouer.setBounds((i.getWidth()/2) + 20, (i.getHeight()/4) + 250, 120, 40);
         		retour.setBounds((i.getWidth()/2) - 130, (i.getHeight()/4) + 250, 120, 40);
         		humain.setBounds((i.getWidth()/2) - 130, (i.getHeight()/4) - 30, 120, 40);
         		ordinateur.setBounds((i.getWidth()/2) + 20, (i.getHeight()/4) - 30, 120, 40);
         		illimite.setBounds((i.getWidth()/2) - 240, (i.getHeight()/4) + 70, 100, 40);
         		uneMin.setBounds((i.getWidth()/2) - 120, (i.getHeight()/4) + 70, 100, 40);
         		deuxMin.setBounds((i.getWidth()/2), (i.getHeight()/4) + 70, 100, 40);
         		troisMin.setBounds((i.getWidth()/2) + 120, (i.getHeight()/4) + 70, 100, 40);
         		joueur2.setBounds((i.getWidth()/2) + 20, (i.getHeight()/4) + 170, 120, 40);
         		joueur1.setBounds((i.getWidth()/2) - 130, (i.getHeight()/4) + 170, 120, 40);
             }
     });

		Font font = new Font("Arial",Font.BOLD,30);
		titre.setFont(font);
		humain.setBackground(Color.pink);
		illimite.setBackground(Color.pink);
		joueur1.setBackground(Color.pink);
		
		retour.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) { 
            	i.retourMenuPrincipal();
            } 
        } );
		
		humain.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) { 
            	humain.setBackground(Color.pink);
				ordinateur.setBackground(null);	
				j.IA = false;	
            } 
        } );
		
		ordinateur.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) { 
            	ordinateur.setBackground(Color.pink);
				humain.setBackground(null);
				human = false; //joueur IA
				j.IA = true;
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
				j.start();
				//SwingUtilities.getWindowAncestor(this).dispose();
				//super.setVisible(false);
				CollecteurEvenements control = new ControleurMediateur(j);
				Plateau.demarrer(j,control);
				i.setVisible(false);
				
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
