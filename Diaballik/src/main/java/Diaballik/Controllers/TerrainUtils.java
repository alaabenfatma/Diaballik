package Diaballik.Controllers;

import Diaballik.Models.Piece;

public class TerrainUtils {
    /**
     * Échangez deux cellules de la matrice
     */
    public static void Swap(Piece a, Piece b) {
        Piece c = a.Clone();
        a = b.Clone();
        b = c;
    }

    public static void ExchangeBall(Piece a, Piece b){
        if(a.Type == b.Type){
            if(a.HasBall){
                a.removeBall();
                b.addBall();
            }
            else if(b.HasBall){
                a.addBall();
                b.removeBall();
            }
            else{
                throw new IllegalAccessError("Aucun des deux joueurs n'a le ballon");
            }
        }
    }
}