package Diaballik.IA;

import java.lang.reflect.Array;
import java.util.ArrayList;

import Diaballik.Controllers.TerrainUtils;
import Diaballik.Models.Jeu;
import Diaballik.Models.Piece;
import Diaballik.Models.PieceType;
import Diaballik.Models.Position;
import Diaballik.Models.State;
import Diaballik.Models.Terrain;

public class MiniMax {
    public PieceType AI_TYPE = PieceType.Black;
    ArrayList<State> AllM2PStates = new ArrayList<State>();

    /**
     * M2P = Move 2 times & Pass the Ball.
     * 
     * @param currentState
     */
    public void M2P(State currentState) {
        ArrayList<State> innerM2PStates = new ArrayList<State>();
        ArrayList<Couple_piece_pos> possibleMoves = IA_utils.getAllPossibleMovesDistance(AI_TYPE, currentState.Game.tr,
                2);
        /**
         * Move the ball X times by a margin of 2
         */
        for (Couple_piece_pos couple : possibleMoves) {
            for (Position pos : couple.pos) {
                Position posMain = couple.piece.Position;
                Terrain tmp = new Terrain();
                tmp.Create();
                tmp._terrain = currentState.Terrain.Copy(tmp);
                tmp._terrain[posMain.l][posMain.c].move(pos.l, pos.c);
                State s = new State(tmp);
                innerM2PStates.add(s);
            }
        }
        /**
         * For every new state, pass the ball X times
         */
        for (State state : innerM2PStates) {
            Piece ballHolder = state.Terrain.getPieceWithBall(AI_TYPE);
            ArrayList<Position> passes = ballHolder.passesPossibles();
            for (Position pos : passes) {

                Terrain tmp = new Terrain();
                tmp.Create();
                tmp._terrain = state.Terrain.Copy(tmp);
                TerrainUtils.passeWrapper(tmp._terrain[ballHolder.Position.l][ballHolder.Position.c],
                        tmp._terrain[pos.l][pos.c]);

                State s = new State(tmp);
                AllM2PStates.add(s);
            }
        }
    }

    ArrayList<State> AllMMPStates = new ArrayList<State>();
    /**
     * MMP = Move Move Pass
     * @param currentState
     */
    public void MMP(State currentState) {
        ArrayList<State> innerMMPStates = new ArrayList<State>();
        ArrayList<Couple_piece_pos> possibleMoves = IA_utils.getAllPossibleMovesDistance(AI_TYPE, currentState.Game.tr,
                1);
        for (Couple_piece_pos couple : possibleMoves) {
            for (Position pos : couple.pos) {
                Position posMain = couple.piece.Position;
                Terrain tmp = new Terrain();
                tmp.Create();
                tmp._terrain = currentState.Terrain.Copy(tmp);
                tmp._terrain[posMain.l][posMain.c].move(pos.l, pos.c);
                State s = new State(tmp);
                innerMMPStates.add(s);
            }
        }
        ArrayList<State> deepMMPStates = new ArrayList<State>();

        for (State state : innerMMPStates) {
            ArrayList<Couple_piece_pos> innerPossibleMoves = IA_utils.getAllPossibleMovesDistance(AI_TYPE,
                    state.Terrain,1);
            for (Couple_piece_pos couple : innerPossibleMoves) {
                for (Position pos : couple.pos) {
                    Position posMain = couple.piece.Position;
                    Terrain tmp = new Terrain();
                    tmp.Create();
                    tmp._terrain = state.Terrain.Copy(tmp);
                    tmp._terrain[posMain.l][posMain.c].move(pos.l, pos.c);
                    State s = new State(tmp);
                    deepMMPStates.add(s);
                }
            }
        }
        
        for (State state : deepMMPStates) {
            Piece ballHolder = state.Terrain.getPieceWithBall(AI_TYPE);
            ArrayList<Position> passes = ballHolder.passesPossibles();
            for (Position pos : passes) {

                Terrain tmp = new Terrain();
                tmp.Create();
                tmp._terrain = state.Terrain.Copy(tmp);
                TerrainUtils.passeWrapper(tmp._terrain[ballHolder.Position.l][ballHolder.Position.c],
                        tmp._terrain[pos.l][pos.c]);

                State s = new State(tmp);
                AllMMPStates.add(s);
            }
        }
        for (State state : AllMMPStates) {
            state.Terrain.PrintTerrain();
            System.out.println(state.score());
        }
    }

    public static void main(String[] args) {
        MiniMax mm = new MiniMax();
        Terrain tr = new Terrain();

        tr.Create();
        Jeu j = new Jeu(tr);
        State s = new State(j);
        //mm.M2P(s);
        mm.MMP(s);
    }
}