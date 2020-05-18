package Diaballik.Vue;

import com.fasterxml.jackson.annotation.JsonCreator;

public class words {
	public String quit = "Quit";
	public String newgame = "New Game";
	public String charger = "Load Game";
	public String reseau = "Play online";
	public String regles = "Rules";
	public String humain = "Human";
	public String ordinateur = "Computer";
	public String jouerContre = "Jouer contre : ";
	public String nomJoueur1 = "Nom du joueur 1 : ";
	public String nomJoueur2 = "Nom du joueur 2 : ";
	public String joueur1 = "Joueur 1";
	public String joueur2 = "Joueur 2";
	public String variante = "Variante";
	public String duree = "Durée d'un tour : ";
	public String illimite = "Illimité";
	public String priorite = "Joue en premier :";
	public String personnaliser = "Personnaliser";
	public String IAFacile = "Facile";
	public String IAMedium = "Moyen";
	public String IADifficile = "Difficile";
	public String niveauIAlabel = "Niveau de l'IA : ";
	public String name1 = "Joueur 1";
	public String name2 = "Joueur 2";
	public String name3 = "IA";
	public String choisirTerrainlabel = "Choisir un terrain : ";
	public String choisirTerrain = "Terrain par défaut";
	public String retour = "Return";
	public String jouer = "Play";
	
	public String validerPersonnaliser = "Valider";

	public String creerPartieReseau = "Create online game";
	public String rejoindrePartieReseau = "Join online game";
	public String menuPrincipal = "Main menu";
	
	public String nomJoueurlabel = "Nom du joueur :";
	public String nomJoueurTextArea = "Joueur 1";
	
	public String attenteTitre = "Attente du 2nd joueur";
	public String codelabel = "Code";
	
	public String finTour = "Fin de tour";
	public String deplacements = "Déplacements";
	public String passe = "Passe";
	public String indicateur = "Indicateur coups joués";
	public String sauvegarde = "Sauvegarde";
	public String reprendre = "Reprendre";
	
	@JsonCreator
	public words() {
		
	}
}
