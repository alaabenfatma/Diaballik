package Diaballik.IA;

import Diaballik.Models.Piece;
import Diaballik.Models.PieceType;
import Diaballik.Models.Terrain;

/**
 * Evalutation
 */


public class MiniMax {
    public static void main(String[] args) {
        Terrain tr = new Terrain();
        tr.Create();
        System.out.println(Evaluator.scoreOfBoard(tr));
    }
}