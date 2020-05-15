package Diaballik.IA;

import static java.util.Collections.*;

import java.lang.reflect.Array;
import java.util.ArrayList;

import Diaballik.Controllers.TerrainUtils;
import Diaballik.Models.FromTo;
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
     * M2P = Move 2 times & Pass the Ball. DISCARDED : We can use MMP!!
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
     * 
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
                s.firstMove = new FromTo(posMain, pos);
                innerMMPStates.add(s);
            }
        }
        ArrayList<State> deepMMPStates = new ArrayList<State>();

        for (State state : innerMMPStates) {
            ArrayList<Couple_piece_pos> innerPossibleMoves = IA_utils.getAllPossibleMovesDistance(AI_TYPE,
                    state.Terrain, 1);
            for (Couple_piece_pos couple : innerPossibleMoves) {
                for (Position pos : couple.pos) {
                    Position posMain = couple.piece.Position;
                    Terrain tmp = new Terrain();
                    tmp.Create();
                    tmp._terrain = state.Terrain.Copy(tmp);
                    tmp._terrain[posMain.l][posMain.c].move(pos.l, pos.c);
                    State s = new State(tmp);
                    s.firstMove = state.firstMove;
                    s.secondMove = new FromTo(posMain, pos);
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
                s.firstMove = state.firstMove;
                s.secondMove = state.secondMove;
                s.pass = new FromTo(ballHolder.Position, pos);
                AllMMPStates.add(s);
            }
        }
        /*
         * for (State state : AllMMPStates) { state.Terrain.PrintTerrain();
         * System.out.println(state.score()); }
         */
    }

    ArrayList<State> AllMPMStates = new ArrayList<State>();

    /**
     * MPM = Move Pass Move
     * 
     * @param currentState
     */
    public void MPM(State currentState) {
        ArrayList<State> innerMPMStates = new ArrayList<State>();
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
                s.firstMove = new FromTo(posMain, pos);
                innerMPMStates.add(s);
            }
        }
        ArrayList<State> deepMPMStates = new ArrayList<State>();
        for (State state : innerMPMStates) {
            Piece ballHolder = state.Terrain.getPieceWithBall(AI_TYPE);
            ArrayList<Position> passes = ballHolder.passesPossibles();
            for (Position pos : passes) {

                Terrain tmp = new Terrain();
                tmp.Create();
                tmp._terrain = state.Terrain.Copy(tmp);
                TerrainUtils.passeWrapper(tmp._terrain[ballHolder.Position.l][ballHolder.Position.c],
                        tmp._terrain[pos.l][pos.c]);

                State s = new State(tmp);
                s.firstMove = state.firstMove;
                s.pass = new FromTo(ballHolder.Position, pos);
                deepMPMStates.add(s);
            }
        }
        for (State state : deepMPMStates) {
            ArrayList<Couple_piece_pos> innerPossibleMoves = IA_utils.getAllPossibleMovesDistance(AI_TYPE,
                    state.Terrain, 1);
            for (Couple_piece_pos couple : innerPossibleMoves) {
                for (Position pos : couple.pos) {
                    Position posMain = couple.piece.Position;
                    Terrain tmp = new Terrain();
                    tmp.Create();
                    tmp._terrain = state.Terrain.Copy(tmp);
                    tmp._terrain[posMain.l][posMain.c].move(pos.l, pos.c);
                    State s = new State(tmp);
                    s.firstMove = state.firstMove;
                    s.secondMove = new FromTo(posMain, pos);
                    s.pass = state.pass;
                    AllMPMStates.add(s);
                }
            }
        }
        /*
         * for (State state : AllMPMStates) { state.Terrain.PrintTerrain();
         * System.out.println(state.score()); }
         */
    }

    ArrayList<State> AllPMMStates = new ArrayList<State>();

    /**
     * PMM = Pass Move Move
     * 
     * @param currentState
     */
    public void PMM(State currentState) {

        Piece ballHolder = currentState.Terrain.getPieceWithBall(AI_TYPE);
        ArrayList<Position> passes = ballHolder.passesPossibles();
        ArrayList<State> innerPMMStates = new ArrayList<State>();

        for (Position pos : passes) {

            Terrain tmp = new Terrain();
            tmp.Create();
            tmp._terrain = currentState.Terrain.Copy(tmp);
            TerrainUtils.passeWrapper(tmp._terrain[ballHolder.Position.l][ballHolder.Position.c],
                    tmp._terrain[pos.l][pos.c]);
            State s = new State(tmp);
            s.pass = new FromTo(ballHolder.Position, pos);

            innerPMMStates.add(s);
        }
        ArrayList<State> deepPMMStates = new ArrayList<State>();

        for (State state : innerPMMStates) {
            ArrayList<Couple_piece_pos> innerPossibleMoves = IA_utils.getAllPossibleMovesDistance(AI_TYPE,
                    state.Terrain, 1);
            for (Couple_piece_pos couple : innerPossibleMoves) {
                for (Position pos : couple.pos) {
                    Position posMain = couple.piece.Position;
                    Terrain tmp = new Terrain();
                    tmp.Create();
                    tmp._terrain = state.Terrain.Copy(tmp);
                    tmp._terrain[posMain.l][posMain.c].move(pos.l, pos.c);
                    State s = new State(tmp);
                    s.pass = state.pass;
                    s.firstMove = new FromTo(posMain, pos);
                    deepPMMStates.add(s);
                }
            }
        }
        for (State state : deepPMMStates) {
            ArrayList<Couple_piece_pos> innerPossibleMoves = IA_utils.getAllPossibleMovesDistance(AI_TYPE,
                    state.Terrain, 1);
            for (Couple_piece_pos couple : innerPossibleMoves) {
                for (Position pos : couple.pos) {
                    Position posMain = couple.piece.Position;
                    Terrain tmp = new Terrain();
                    tmp.Create();
                    tmp._terrain = state.Terrain.Copy(tmp);
                    tmp._terrain[posMain.l][posMain.c].move(pos.l, pos.c);
                    State s = new State(tmp);
                    s.pass = state.pass;
                    s.firstMove = state.firstMove;
                    s.secondMove = new FromTo(posMain, pos);
                    AllPMMStates.add(s);
                }
            }
        }
        /*
         * for (State state : AllPMMStates) { state.Terrain.PrintTerrain();
         * System.out.println(state.score()); }
         */
    }

    public MiniMax(Jeu j, PieceType type) {
        AI_TYPE = type;
    }

    public State winningMove(Jeu game) {
        AllMMPStates.clear();
        AllMMPStates.clear();
        AllPMMStates.clear();
        Terrain newTer = new Terrain();
        State s = new State(game);
        s.Terrain.PrintTerrain();
        ArrayList<State> bestOptions = new ArrayList<State>();
        MMP(s);
        MPM(s);
        PMM(s);

        shuffle(AllMMPStates);
        shuffle(AllMPMStates);
        shuffle(AllPMMStates);

        sort(AllMMPStates);
        sort(AllMPMStates);
        sort(AllPMMStates);

        if (this.AI_TYPE == PieceType.White) {
            bestOptions.add(AllMMPStates.get(AllMMPStates.size() - 1));
            bestOptions.add(AllMMPStates.get(AllMPMStates.size() - 1));
            bestOptions.add(AllMMPStates.get(AllPMMStates.size() - 1));
            sort(bestOptions);
            return bestOptions.get(2);
        } else {
            bestOptions.add(AllMMPStates.get(0));
            bestOptions.add(AllMMPStates.get(0));
            bestOptions.add(AllMMPStates.get(0));
            sort(bestOptions);
            return bestOptions.get(0);
        }

    }

}