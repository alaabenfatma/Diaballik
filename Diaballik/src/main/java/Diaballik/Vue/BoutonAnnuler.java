package Diaballik.Vue;

import Diaballik.Models.Jeu;
import Diaballik.Patterns.Observateur;

import javax.swing.JButton;

@SuppressWarnings("serial")
public class BoutonAnnuler extends JButton implements Observateur {
	Jeu jeu;

	public BoutonAnnuler(Jeu j) {
		jeu = j;
		setText("<");
		jeu.ajouteObservateur(this);
		setEnabled(jeu.tr.UndoStackNotEmpty());
	}

	@Override
	public void miseAJour() {
		setEnabled(jeu.tr.UndoStackNotEmpty());
	}
}
