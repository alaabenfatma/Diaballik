package Diaballik.Vue;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.*;

public class ChargerPartie extends JPanel implements ActionListener{
	
	JLabel titre = new JLabel("Charger partie");
	JLabel save = new JLabel("Sauvegardes");
	JButton retour = new JButton("Retour");
	JButton jouer = new JButton("Jouer");
	
	ihm i;
	Object[][] donnees = {
		{"Alaa", "Yohan", "20/05/20"},
		{"Wassim", "Thomas", "21/05/20"}
	};

	String[] entetes = {"Joueur1", "Joueur2", "Date"};
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
		retour.addActionListener(this);		
		
		this.add(jouer);
		this.add(retour);
		this.add(save);
		this.add(titre);
		this.add(tableau.getTableHeader());
		this.add(tableau);
		this.setVisible(true);

	}
	
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getSource() == retour) {
			i.retourMenuPrincipal();
		}
		if(arg0.getSource() == jouer) {
			
		}
	}
}
