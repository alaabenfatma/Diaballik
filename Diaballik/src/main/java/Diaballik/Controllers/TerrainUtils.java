package Diaballik.Controllers;

import java.util.ArrayList;

import Diaballik.Models.*;

public class TerrainUtils {
    /**
     * Échangez deux cellules de la matrice
     */
    public static void Swap(Piece a, Piece b) {
        if(a.Parent != b.Parent){
            throw new IllegalStateException("Not on the same terrain.");
        }
        Piece x = a;
        a.Parent.getTerrain()[a.Position.l][a.Position.c] = b;
        b.Parent.getTerrain()[b.Position.l][b.Position.c] = x;
        SwapPosition(a, b);
    }


    public static void SwapPosition(Piece a, Piece b) {
        Position temp = a.Position;
        a.Position = b.Position;
        b.Position = temp;
    }

    public static void ExchangeBall(Piece a, Piece b) {
        if (a.Type == b.Type) {
            if (a.HasBall) {
                a.removeBall();
                b.addBall();
            } else if (b.HasBall) {
                a.addBall();
                b.removeBall();
            } else {
                throw new IllegalAccessError("Aucun des deux joueurs n'a le ballon");
            }
        }
        else{
            throw new IllegalAccessError("Les deux joueurs sont de deux types différents");
        }
    }

    //Basically ExchangeBall mais on empeche l'utilisateur de faire du n'importe quoi
    public static void passeWrapper(Piece a, Piece b){
        ArrayList<Position> ar = a.passesPossibles();
        if(a.equals(b)){
            //throw new IllegalAccessError("Le joueur a tenté de se passer à lui meme la balle");
        }
        else if(ar.contains(b.Position)){
            ExchangeBall(a, b);
        }else{
            //throw new IllegalAccessError("Erreur coup illegal: les pieces choisies ne devraient pas faire de passes");
        }
    }

    public static boolean passeWrapper2(Piece a, Piece b){
        ArrayList<Position> ar = a.passesPossibles();
        if(a.equals(b)){
            return false;
            //throw new IllegalAccessError("Le joueur a tenté de se passer à lui meme la balle");
        }
        else if(ar.contains(b.Position)){
            ExchangeBall(a, b);
            return true;
        }else{
            return false;
            //throw new IllegalAccessError("Erreur coup illegal: les pieces choisies ne devraient pas faire de passes");
        }
    }

    public static int Distance(Position p1,Position p2){
        return (int)(Math.hypot(p1.c - p2.c, p1.l - p2.l));
    }
}