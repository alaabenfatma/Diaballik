package Diaballik.Vue;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.*;

public class ChargerPartie extends JPanel implements ActionListener{
	
	JLabel titre = new JLabel("Charger partie");
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
		titre.setBounds(240, 0, 300, 100);
		jouer.setBounds(350, 390, 120, 40);
		retour.setBounds(210, 390, 120, 40);
		this.add(jouer);
		this.add(retour);
		this.add(titre);
		
		Font font = new Font("Arial",Font.BOLD,30);
		titre.setFont(font);
		retour.addActionListener(this);		
		JTable tableau = new JTable(donnees, entetes);
		tableau.setBounds(210, 390, 120, 40);
		this.add(tableau.getTableHeader(), BorderLayout.NORTH);
		this.add(tableau, BorderLayout.CENTER);
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
