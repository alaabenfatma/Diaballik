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
	public String retour = "Return";
	public String jouer = "Play";
	
	
	@JsonCreator
	public words() {
		
	}
}
