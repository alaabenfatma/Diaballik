package Diaballik.Vue;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class RejoindrePartieReseau extends JPanel {
	JLabel titre = new JLabel("Rejoindre une partie");
	JLabel nomJoueur = new JLabel("Nom du joueur");
	JLabel codelabel = new JLabel("Code");
	JTextArea name = new JTextArea(5, 10);
	JTextArea code = new JTextArea(5, 10);
	JButton ok = new JButton("Ok");
	JButton retour = new JButton("Retour");
	ihm i;
	
	public RejoindrePartieReseau(ihm ihm) {
		i = ihm;
		this.setLayout(null);
		titre.setBounds(210, 0, 300, 100);
		nomJoueur.setBounds(180, 150, 150, 100);
		codelabel.setBounds(180, 180, 150, 100);
		name.setBounds(310, 190, 150, 20);
		code.setBounds(310, 220, 150, 20);
		ok.setBounds(350, 390, 120, 40);
		retour.setBounds(210, 390, 120, 40);
		this.add(nomJoueur);
		this.add(name);
		this.add(codelabel);
		this.add(code);
		this.add(ok);
		this.add(retour);
		this.add(titre);
		
		Font fonttitre = new Font("Arial",Font.BOLD,30);
		Font fontnomJoueur = new Font("Arial",Font.BOLD,15);
		nomJoueur.setFont(fontnomJoueur);
		codelabel.setFont(fontnomJoueur);
		titre.setFont(fonttitre);
		
		ok.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) { 
            } 
        } );
		
		
		retour.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) { 
            	i.fenetreJouerEnReseau();
            } 
        } );
		
		this.setVisible(true);
	}

}
