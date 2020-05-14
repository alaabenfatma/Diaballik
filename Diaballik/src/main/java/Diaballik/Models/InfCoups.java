package Diaballik.Models;

public class InfCoups{
    Terrain tr;
    Joueur jCourant;
    int moves;
    int passes;
    public InfCoups(Terrain tr, Joueur jCourant, int moves, int passes){
        this.tr = tr;
        this.jCourant = jCourant;
        this.moves = moves;
        this.passes = passes;
    }
}