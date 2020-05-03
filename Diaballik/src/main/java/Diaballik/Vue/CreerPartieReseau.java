package Diaballik.Vue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class CreerPartieReseau extends JPanel implements ActionListener{
	JLabel titre = new JLabel("Créer une partie");
	JLabel nomJoueur = new JLabel("Nom du joueur");
	JTextArea name = new JTextArea(5, 10);
	JButton ok = new JButton("Ok");
	JButton retour = new JButton("Retour");
	ihm i;
	
	public CreerPartieReseau(ihm ihm) {
		i = ihm;
		this.setLayout(null);
		titre.setBounds(230, 0, 300, 100);
		nomJoueur.setBounds(180, 150, 150, 100);
		name.setBounds(310, 190, 150, 20);
		ok.setBounds(350, 390, 120, 40);
		retour.setBounds(210, 390, 120, 40);
		this.add(nomJoueur);
		this.add(name);
		this.add(ok);
		this.add(retour);
		this.add(titre);
		
		Font fonttitre = new Font("Arial",Font.BOLD,30);
		Font fontnomJoueur = new Font("Arial",Font.BOLD,15);
		nomJoueur.setFont(fontnomJoueur);
		titre.setFont(fonttitre);
		ok.addActionListener(this);
		retour.addActionListener(this);
		this.setVisible(true);
	}
	
	
	
	
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getSource() == retour) {
			i.fenetreJouerEnReseau();
		}
		if(arg0.getSource() == ok) {
			i.fenetreAttenteJoueurReseau();
		}
		
	}

}
