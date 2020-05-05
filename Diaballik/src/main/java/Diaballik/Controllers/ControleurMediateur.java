package Diaballik.Controllers;

import Diaballik.Models.Jeu;
import Diaballik.Models.Joueur;
import Diaballik.Models.PieceType;
import Diaballik.Vue.CollecteurEvenements;
import Diaballik.Vue.Plateau;
import Diaballik.Vue.PlateauGraphique;

public class ControleurMediateur implements CollecteurEvenements {

	Jeu jeu;
	Plateau vue;

	public ControleurMediateur(Jeu j) {
		jeu = j;
	}

	@Override
	public void clicSouris(int l, int c) {
		/*
		 * if(jeu.tr._terrain[l][c].Type == PieceType.Black && jeu.joueur ==
		 * Joueur.Joueur1 ){ if (jeu.tr._terrain[l][c].HasBall == true) passe(l, c);
		 * else move(l,c);
		 * 
		 * }
		 */

	}

	public void annule() {
		// TODO : annule
	}

	public void refait() {
		// TODO : refait
	}

	@Override
	public void toucheClavier(String touche) {
		switch (touche) {
			case "Undo":
				annule();
				break;
			case "Redo":
				refait();
				break;
			case "Quit":
				System.exit(0);
				break;
			case "Full":
				vue.toggleFullscreen();
				break;
			default:
				System.out.println("Touche inconnue : " + touche);
		}
	}

	@Override
	public void ajouteInterfaceUtilisateur(Plateau v) {
		vue = v;
	}

	@Override
	public void tictac() {
		// TODO Auto-generated method stub

	}

}
