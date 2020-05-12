package Diaballik.IA;

import java.util.ArrayList;

import Diaballik.Controllers.TerrainUtils;
import Diaballik.Models.*;

public class IA_medium {

    int nbMove = 2;
    int nbPasse = 1;
    PieceType type = PieceType.White;
    Terrain tr;

    public IA_medium(Terrain tr, PieceType type){
        this.tr = tr;
        this.type = type;
    }

    //Renvoi le meilleur coup avec une profondeur de 1
    public void getNextMove(){
        
        ArrayList<Couple_piece_pos> possibleMoves = IA_utils.getAllPossibleMoves(type, tr, nbMove);
        for(Couple_piece_pos pp : possibleMoves){
            Terrain trTemp = tr.clone();
            int max = 0;


        }
        
        
    }

    public static void main(String args[]){
        Terrain tr = new Terrain();
        tr.Create();
        System.out.println(IA_utils.getAllPossibleMoves(PieceType.White, tr, 2));
    }
}

class From_to{
    Position from;
    Position to;
    public From_to(Position from, Position to){
        this.from = from;
        this.to = to;
    }
    @Override
    public String toString(){
        return from.toString() + " -> " + to.toString();
    }
}
