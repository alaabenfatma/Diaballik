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
    Stack<Sauve> S;
    ArrayList<Position> Courant,Sauve;

    public IA_medium(Terrain tr, PieceType type){
        this.tr = tr;
        this.type = type;
        S = new Stack<Sauve>();
        Courant = new ArrayList<Position>();
    }

    //Still working on it
    public void getNextMove(){
        /*
        ArrayList<Couple_piece_pos> possibleMoves = IA_utils.getAllPossibleMoves(type, tr, nbMove);
        for(Couple_piece_pos pp : possibleMoves){
            Terrain trTemp = tr.clone();
            int max = 0;
        }
        
        */
    }


    /**
     * PPE = Player Player Exchange
     */
    private void push(){
        S.push(new Sauve(nbMove, nbPasse, tr,Courant));
    }
    private void pop(){
        Sauve t = S.pop();
        nbMove = t.nbMove;
        nbPasse = t.nbPasse;
        tr = t.tr;
        Courant = t.Mouvement;
    }
    private void swap(Position p1,Position p2){
        TerrainUtils.Swap(tr.getTerrain()[p1.l][p1.c],tr.getTerrain()[p2.l][p2.c]);
        nbMove = Math.abs(p1.l - p2.l) + Math.abs(p1.c - p2.c);

    }
    private int PPE_REC(int level){
        int res = 0;
        if(level == 0 || level == 1){ // Premier déplacement ou deuxième déplacement
            ArrayList<Couple_piece_pos> poss = IA_utils.getAllPossibleMoves(type, tr, 1);
            for(int i = 0;i<poss.size();i++){
                for(int j = 0 ; j < poss.get(i).pos.size() ; j++){
                    Position pos = poss.get(i).pos.get(j);
                    push();
                    if(level == 0){Courant.add(poss.get(i).p.Position);}
                    Courant.add(pos);
                    swap(poss.get(i).p.Position,pos);
                    res = PPE_REC(level++);
                    pop();
                }
            }
        }
        else{ // Passe
            Piece p = tr.getPieceWithBall(type);
            ArrayList<Position> l = p.passesPossibles();
            res = Evaluator.scoreOfBoard(tr);
            for(int i = 0;i<l.size();i++){
                push();
                TerrainUtils.passeWrapper(p, tr.getTerrain()[l.get(i).l][l.get(i).c]);
                nbPasse--;
                int eval =Evaluator.scoreOfBoard(tr);
                if ( eval < res && type == PieceType.Black){
                    res = eval;
                    Sauve = Courant;
                }
                else if ( eval > res && type == PieceType.White){
                    res = eval;
                    Sauve = Courant;
                }
                pop();
            }
        }
        return res;
    }
    private void Strategy_PPE(){
        int score = PPE_REC(0);
        System.out.println("Score = "+score+" Movement = "+Sauve);
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
        Terrain tr = new Terrain();
        tr.Create();
        IA_medium ia_med = new IA_medium(tr, PieceType.Black);
        ia_med.Strategy_PPE();
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
