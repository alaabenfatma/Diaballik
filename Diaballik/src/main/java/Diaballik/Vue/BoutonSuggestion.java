package Diaballik.Vue;

import Diaballik.Models.Jeu;
import Diaballik.Patterns.Observateur;

import javax.swing.JButton;

@SuppressWarnings("serial")
public class BoutonSuggestion extends JButton implements Observateur {
	Jeu jeu;

	public BoutonSuggestion(Jeu j) {
		jeu = j;
		setText("Coup conseillé");
		jeu.ajouteObservateur(this);
		setEnabled(jeu.DebutTour());
	}

	@Override
	public void miseAJour() {
		setEnabled(jeu.DebutTour());
	}
}
