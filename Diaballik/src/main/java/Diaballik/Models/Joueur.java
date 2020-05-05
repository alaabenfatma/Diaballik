package Diaballik.Models;

public class Joueur{
    public TypeJoueur n; //joueur1, 2 ou IA
    public PieceType couleur; //sa couleur
    public int nbMove;
    public int passe_faite;
    public Joueur(TypeJoueur n, PieceType couleur, int nbMove, int passe_faite){
        this.n = n;
        this.couleur = couleur;
        this.nbMove = nbMove;
        this.passe_faite = passe_faite;
    }
}

enum TypeJoueur {
    Joueur1, Joueur2, IA
}