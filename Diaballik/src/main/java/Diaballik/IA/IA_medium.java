package Diaballik.IA;

import java.util.ArrayList;
import java.util.Stack;

import Diaballik.Controllers.TerrainUtils;
import Diaballik.Models.*;

public class IA_medium {

    int nbMove = 2;
    int nbPasse = 1;
    PieceType type = PieceType.Black;
    Terrain tr;
    Stack<Trouple> S = new Stack<Trouple>();;

    public IA_medium(Terrain tr, PieceType type){
        this.tr = tr;
        this.type = type;
    }

    //Renvoi le meilleur coup avec une profondeur de 1
    public From_to getNextMove(){
        
        ArrayList<Couple_piece_pos> possibleMoves = IA_utils.getAllPossibleMoves(type, tr, nbMove);
        int max = 0;
        From_to bestMove = new From_to(possibleMoves.get(0).piece.Position,possibleMoves.get(0).pos.get(0));
        for(Couple_piece_pos pp : possibleMoves){
            System.out.println("Best move : "+bestMove);
            Terrain trTemp = tr.clone();
            for(Position p : pp.pos){
                pp.piece.move(p.l, p.c);
                int sc = Evaluator.scoreOfBoard(tr);
                if(max<sc){
                    max = sc;
                    bestMove = new From_to(pp.piece.Position, p);
                }
                tr.PrintTerrain();
                //tr = trTemp;
                //tr.PrintTerrain();
                //trTemp = tr.clone();
            }

        }
        //System.out.println(bestMove);
        return bestMove;
    }
    /**
     * PPE = Player Player Exchange
     */
    private void push(){
        S.push(new Trouple(nbMove, nbPasse, tr));
    }
    private void pop(){
        Trouple t = S.pop();
        nbMove = t.nbMove;
        nbPasse = t.nbPasse;
        tr = t.tr;
    }
    private void swap(Position p1,Position p2){
        TerrainUtils.Swap(tr.getTerrain()[p1.l][p1.c],tr.getTerrain()[p2.l][p2.c]);
        nbMove = Math.abs(p1.l - p2.l) + Math.abs(p1.c - p2.c);

    }
    //TODO faire fonction qui regarde les déplacement de 1 pour chaque pièce
    /*
    private int PPE_REC(int level){
        
        int res = 0;
        if(level == 0){ // Premier déplacement
            ArrayList<Couple_piece_pos> poss = IA_utils.getAllPossibleMoves(type, tr, nbMove);
            //System.out.println(poss);
            for(int i = 0;i<poss.size();i++){
                for(int j = 0 ; j < poss.get(i).pos.size() ; j++){
                    Position pos = poss.get(i).pos.get(j);
                    push();
                    swap(poss.get(i).p.Position,pos);
                    res = PPE_REC(level++);
                    pop();
                }
            }
        }
        else if(level == 1){ // Deuxième déplacement

        }
        else{ // Passe

        }
        return res;
        
    }
    */
    private void Strategy_PPE(){
       // int score = PPE_REC(new Position(0,0),new Position(0,0),0);
    }
    /**
     * PEP = Player Exchance Player
     */
    private void Strategy_PEP(){

    }
    /**
     * EPP = Exchange Player Player
     */
    private void Strategy_EPP(){

    }

    public static void main(String args[]){
        Jeu jeu = new Jeu();
        Terrain tr = jeu.tr;
        tr._jeuParent = jeu;
        IA_medium ia_med = new IA_medium(tr, PieceType.Black);
        //ia_med.Strategy_PPE();
        //ia_med.getNextMove();
        ia_med.getNextMove();
    }
}

class From_to{
    Position from;
    Position to;

    public From_to(){
        this.from = null;
        this.to = null;
    }

    public From_to(Position from, Position to){
        this.from = from;
        this.to = to;
    }
    @Override
    public String toString(){
        return from.toString() + " -> " + to.toString();
    }
}
