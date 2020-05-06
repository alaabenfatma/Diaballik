package Diaballik.Vue;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.*;

public class ChargerPartie extends JPanel {
	
	JLabel titre = new JLabel("Charger partie");
	JLabel save = new JLabel("Sauvegardes");
	JButton retour = new JButton("Retour");
	JButton jouer = new JButton("Jouer");
	
	ihm i;
	Object[][] donnees = {
		{"Alaa", "Yohan", "20/05/20"},
		{"Wassim", "Thomas", "21/05/20"},
		{"Hedi", "Thomas", "21/05/20"},
		{"Wassim", "IA", "21/05/20"},
		{"Wassim", "Thomas", "21/05/20"},
		{"IA", "Thomas", "21/05/20"},
		{"Wassim", "Ludo", "21/05/20"},
		{"Wassim", "Thomas", "21/05/20"}
	};
	/*Pour la date, il faut recuperer la date de l'os
	 * et pour il faut rendre chaque ligne cliquable 
	 * Il faut enregistrer une image à chaque fois qu'on
	 * sauvegarde pour pouvoir faire l'apercu
	 * et enfin il faut pouvoir ajouter une fonction qui
	 * ajoute un ligne dans le tableau lorqu'on enregistre une partie
	 * et un bouton pour supprimer qq parties*/

	String[] entetes = {"Joueur 1", "Joueur 2", "Date"};
	public ChargerPartie(ihm ihm) {
		i = ihm;
		this.setLayout(null);
		JTable tableau = new JTable(donnees, entetes);
		titre.setBounds(240, 0, 300, 100);
		save.setBounds(150, 90, 100, 20);
		jouer.setBounds(350, 390, 120, 40);
		retour.setBounds(210, 390, 120, 40);
		tableau.setBounds(40, 140, 300, 240);
		tableau.getTableHeader().setBounds(40, 110, 300, 30);
		
		Font font = new Font("Arial",Font.BOLD,30);
		titre.setFont(font);
		
		jouer.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) { 
            } 
        } );
		
		retour.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) { 
            	i.retourMenuPrincipal();
            } 
        } );
		
		this.add(jouer);
		this.add(retour);
		this.add(save);
		this.add(titre);
		this.add(tableau.getTableHeader());
		this.add(tableau);
		this.setVisible(true);

	}
	
}
