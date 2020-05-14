package Diaballik.IA;

import java.util.ArrayList;

import Diaballik.Controllers.TerrainUtils;
import Diaballik.Models.Jeu;
import Diaballik.Models.PieceType;
import Diaballik.Models.Position;
import Diaballik.Models.State;
import Diaballik.Models.Terrain;


public class MiniMax {
    public PieceType IA_TYPE = PieceType.Black;
    ArrayList<State> AllM2PStates = new ArrayList<State>();
    
    public void M2P(State currentState){
        currentState.Game.tr.PrintTerrain();
        ArrayList<Couple_piece_pos> possibleMoves= IA_utils.getAllPossibleMovesDistance(IA_TYPE, currentState.Game.tr   ,2);
        System.out.println(possibleMoves);
        for (Couple_piece_pos couple : possibleMoves) {
            for (Position pos : couple.pos) {
                Terrain tmp  = new Terrain();
                tmp._terrain = currentState.Game.tr.Copy();
                System.out.println(couple.piece.Position+" "+pos+" distance ="+TerrainUtils.Distance(couple.piece.Position, pos));
            }
        }
    }
    public static void main(String[] args) {
        MiniMax mm = new MiniMax();
        Terrain tr = new Terrain();
        
        tr.Create();
        Jeu j = new Jeu(tr);
        State s = new State(j);
        mm.M2P(s);
        System.out.println(Evaluator.scoreOfBoard(tr));
    }
}