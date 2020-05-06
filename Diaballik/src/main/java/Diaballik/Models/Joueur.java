package Diaballik.Models;

public class Joueur{
    public TypeJoueur n; //joueur1, 2 ou IA
    public PieceType couleur; //sa couleur
    public int nbMove;
    public int passeDispo;
    public Joueur(TypeJoueur n, PieceType couleur, int nbMove, int passeDispo){
        this.n = n;
        this.couleur = couleur;
        this.nbMove = nbMove;
        this.passeDispo = passeDispo;
    }
}

enum TypeJoueur {
    Joueur1, Joueur2, IA
}