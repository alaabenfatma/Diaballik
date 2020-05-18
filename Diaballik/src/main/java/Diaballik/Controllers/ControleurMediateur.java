package Diaballik.Controllers;

import Diaballik.Models.ConfigJeu;
import Diaballik.Models.Jeu;
import Diaballik.Vue.CollecteurEvenements;
import Diaballik.Vue.Plateau;
import Diaballik.Vue.msgBox;
import Diaballik.Models.IA.*;

public class ControleurMediateur implements CollecteurEvenements {

	Jeu jeu;
	Plateau vue;

	public ControleurMediateur(Jeu j) {
		jeu = j;
	}

	@Override
	public void clicSouris(int l, int c) {
		jeu.SelectionPiece(l, c);
		

	}

	public void annule() {
		if (jeu.tr.UndoStackNotEmpty()) {
			jeu.jctrl_z();
			
		}
	}

	public void refait() {
		if (jeu.tr.RedoStackNotEmpty()) {
			jeu.jctrl_y();
			
		}
	}

	public void finTour() {
		jeu.FinTour();
	}

	public void save() {
		jeu.ExportGameToJSON(jeu);
		msgBox.Confirm("La partie a été enregistrée.", "Information");
	}

	public void replay() {
		if (jeu.config.getTimer() != ConfigJeu.Timer.illimite) {
            if (jeu.config.getTimer() == ConfigJeu.Timer.un)
                Plateau.setX(10000);
            else if (jeu.config.getTimer() == ConfigJeu.Timer.deux)
                Plateau.setX(30000);
            else if (jeu.config.getTimer() == ConfigJeu.Timer.trois)
                Plateau.setX(60000);
            else
                Plateau.setX(60000);
        }
		if (Plateau.timer != null)
			Plateau.timer.restart();
		jeu.init();
		jeu.start();
	}

	public void IAvsIA() {
		jeu.IaVSIa = true;
	}

	public void suggestion(){
		jeu.suggestion();
		
	}
	
	@Override
	public void toucheClavier(String touche) {
		switch (touche) {
			case "Undo":
				annule();
				break;
			case "FinTour":
				finTour();
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
			case "Save":
				save();
				break;
			case "Replay":
				replay();
				break;
			case "IAvsIA":
				IAvsIA();
				break;
			case "Suggestion":
				suggestion();
			default:
				
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
