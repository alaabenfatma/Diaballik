package Diaballik.Vue;

import Diaballik.Models.Jeu;
import Diaballik.Patterns.Observateur;

import javax.swing.JButton;

@SuppressWarnings("serial")
public class BoutonRefaire extends JButton implements Observateur {
	Jeu jeu;

	public BoutonRefaire(Jeu j) {
		jeu = j;
		setText(">");
		jeu.ajouteObservateur(this);
		setEnabled(jeu.tr.RedoStackNotEmpty());
	}

	@Override
	public void miseAJour() {
		setEnabled(jeu.tr.RedoStackNotEmpty());
	}
}
