/*
MIT License

Copyright (c) 2020 Alaa Ben Fatma

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
*/
package Diaballik.Models.IA;

import static java.util.Collections.*;
import java.util.*;
import Diaballik.Controllers.*;
import Diaballik.Models.*;
import Diaballik.Vue.LoadingScreen.LoadingScreen;

public class MiniMax {
    public PieceType AI_TYPE = PieceType.Black;
    public Jeu Game;
    public LoadingScreen loadingScreen = new LoadingScreen();
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
                Terrain tmp = new Terrain(Game);
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

                Terrain tmp = new Terrain(Game);
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
        boolean foundVictory = false;
        ArrayList<State> innerMMPStates = new ArrayList<State>();
        ArrayList<Couple_piece_pos> possibleMoves = IA_utils.getAllPossibleMovesDistance(AI_TYPE, currentState.Game.tr,
                1);
        for (Couple_piece_pos couple : possibleMoves) {
            if (!foundVictory)
                for (Position pos : couple.pos) {
                    Position posMain = couple.piece.Position;
                    Terrain tmp = new Terrain(Game);
                    tmp.Create();
                    tmp._terrain = currentState.Terrain.Copy(tmp);
                    tmp._terrain[posMain.l][posMain.c].move(pos.l, pos.c);
                    State s = new State(tmp);
                    s.GameMode = Sequence.MMP;
                    s.firstMove = new FromTo(posMain, pos);
                    innerMMPStates.add(s);
                    if (Math.abs(s.score()) == 9999) {
                        foundVictory = true;
                        break;
                    }
                }
        }
        ArrayList<State> deepMMPStates = new ArrayList<State>();

        for (State state : innerMMPStates) {
            ArrayList<Couple_piece_pos> innerPossibleMoves = IA_utils.getAllPossibleMovesDistance(AI_TYPE,
                    state.Terrain, 1);
            for (Couple_piece_pos couple : innerPossibleMoves) {
                if (!foundVictory)
                    for (Position pos : couple.pos) {
                        Position posMain = couple.piece.Position;
                        Terrain tmp = new Terrain(Game);
                        tmp.Create();
                        tmp._terrain = state.Terrain.Copy(tmp);
                        tmp._terrain[posMain.l][posMain.c].move(pos.l, pos.c);
                        State s = new State(tmp);
                        s.GameMode = Sequence.MMP;
                        s.firstMove = state.firstMove;
                        s.secondMove = new FromTo(posMain, pos);
                        deepMMPStates.add(s);
                        if (Math.abs(s.score()) == 9999) {
                            foundVictory = true;
                            break;
                        }
                    }
            }
        }

        for (State state : deepMMPStates) {
            Piece ballHolder = state.Terrain.getPieceWithBall(AI_TYPE);
            ArrayList<Position> passes = ballHolder.passesPossibles();
            for (Position pos : passes) {
                if (foundVictory)
                    break;
                Terrain tmp = new Terrain(Game);
                tmp.Create();
                tmp._terrain = state.Terrain.Copy(tmp);
                TerrainUtils.passeWrapper(tmp._terrain[ballHolder.Position.l][ballHolder.Position.c],
                        tmp._terrain[pos.l][pos.c]);

                State s = new State(tmp);
                s.GameMode = Sequence.MMP;
                s.firstMove = state.firstMove;
                s.secondMove = state.secondMove;
                s.pass = new FromTo(ballHolder.Position, pos);
                AllMMPStates.add(s);
                if (Math.abs(s.score()) == 9999) {
                    foundVictory = true;
                    break;
                }
            }
        }
        // Merge the three lists of states
        AllMMPStates.addAll(deepMMPStates);
        AllMMPStates.addAll(innerMMPStates);
    }

    ArrayList<State> AllMPMStates = new ArrayList<State>();

    /**
     * MPM = Move Pass Move
     * 
     * @param currentState
     */
    public void MPM(State currentState) {
        boolean foundVictory = false;
        ArrayList<State> innerMPMStates = new ArrayList<State>();
        ArrayList<Couple_piece_pos> possibleMoves = IA_utils.getAllPossibleMovesDistance(AI_TYPE, currentState.Game.tr,
                1);
        for (Couple_piece_pos couple : possibleMoves) {
            if (foundVictory)
                break;
            for (Position pos : couple.pos) {
                Position posMain = couple.piece.Position;
                Terrain tmp = new Terrain(Game);
                tmp.Create();
                tmp._terrain = currentState.Terrain.Copy(tmp);
                tmp._terrain[posMain.l][posMain.c].move(pos.l, pos.c);
                State s = new State(tmp);
                s.GameMode = Sequence.MPM;
                s.firstMove = new FromTo(posMain, pos);
                innerMPMStates.add(s);
                if (Math.abs(s.score()) == 9999) {
                    foundVictory = true;
                    break;
                }
            }
        }
        ArrayList<State> deepMPMStates = new ArrayList<State>();
        for (State state : innerMPMStates) {
            if (foundVictory)
                break;
            Piece ballHolder = state.Terrain.getPieceWithBall(AI_TYPE);
            ArrayList<Position> passes = ballHolder.passesPossibles();
            for (Position pos : passes) {
                Terrain tmp = new Terrain(Game);
                tmp.Create();
                tmp._terrain = state.Terrain.Copy(tmp);
                TerrainUtils.passeWrapper(tmp._terrain[ballHolder.Position.l][ballHolder.Position.c],
                        tmp._terrain[pos.l][pos.c]);

                State s = new State(tmp);
                s.GameMode = Sequence.MPM;
                s.firstMove = state.firstMove;
                s.pass = new FromTo(ballHolder.Position, pos);
                deepMPMStates.add(s);
                if (Math.abs(s.score()) == 9999) {
                    foundVictory = true;
                    break;
                }
            }
        }
        for (State state : deepMPMStates) {
            ArrayList<Couple_piece_pos> innerPossibleMoves = IA_utils.getAllPossibleMovesDistance(AI_TYPE,
                    state.Terrain, 1);
            for (Couple_piece_pos couple : innerPossibleMoves) {
                if (foundVictory)
                    break;
                for (Position pos : couple.pos) {
                    Position posMain = couple.piece.Position;
                    Terrain tmp = new Terrain(Game);
                    tmp.Create();
                    tmp._terrain = state.Terrain.Copy(tmp);
                    tmp._terrain[posMain.l][posMain.c].move(pos.l, pos.c);
                    State s = new State(tmp);
                    s.GameMode = Sequence.MPM;
                    s.firstMove = state.firstMove;
                    s.secondMove = new FromTo(posMain, pos);
                    s.pass = state.pass;
                    AllMPMStates.add(s);
                    if (Math.abs(s.score()) == 9999) {
                        foundVictory = true;
                        break;
                    }
                }
            }
        }
        // Merge the three lists of states
        AllMPMStates.addAll(deepMPMStates);
        AllMPMStates.addAll(innerMPMStates);
    }

    ArrayList<State> AllPMMStates = new ArrayList<State>();

    /**
     * PMM = Pass Move Move
     * 
     * @param currentState
     */
    public void PMM(State currentState) {
        boolean foundVictory = false;

        Piece ballHolder = currentState.Terrain.getPieceWithBall(AI_TYPE);
        ArrayList<Position> passes = ballHolder.passesPossibles();
        ArrayList<State> innerPMMStates = new ArrayList<State>();

        for (Position pos : passes) {
            if (foundVictory)
                break;
            Terrain tmp = new Terrain(Game);
            tmp.Create();
            tmp._terrain = currentState.Terrain.Copy(tmp);
            TerrainUtils.passeWrapper(tmp._terrain[ballHolder.Position.l][ballHolder.Position.c],
                    tmp._terrain[pos.l][pos.c]);
            State s = new State(tmp);
            s.GameMode = Sequence.PMM;
            s.pass = new FromTo(ballHolder.Position, pos);

            innerPMMStates.add(s);
            if (Math.abs(s.score()) == 9999) {
                foundVictory = true;
                break;
            }
        }
        ArrayList<State> deepPMMStates = new ArrayList<State>();

        for (State state : innerPMMStates) {
            ArrayList<Couple_piece_pos> innerPossibleMoves = IA_utils.getAllPossibleMovesDistance(AI_TYPE,
                    state.Terrain, 1);
            for (Couple_piece_pos couple : innerPossibleMoves) {
                if (foundVictory)
                    break;
                for (Position pos : couple.pos) {
                    Position posMain = couple.piece.Position;
                    Terrain tmp = new Terrain(Game);
                    tmp.Create();
                    tmp._terrain = state.Terrain.Copy(tmp);
                    tmp._terrain[posMain.l][posMain.c].move(pos.l, pos.c);
                    State s = new State(tmp);
                    s.GameMode = Sequence.PMM;
                    s.pass = state.pass;
                    s.firstMove = new FromTo(posMain, pos);
                    deepPMMStates.add(s);
                    if (Math.abs(s.score()) == 9999) {
                        foundVictory = true;
                        break;
                    }
                }
            }
        }
        for (State state : deepPMMStates) {
            ArrayList<Couple_piece_pos> innerPossibleMoves = IA_utils.getAllPossibleMovesDistance(AI_TYPE,
                    state.Terrain, 1);
            for (Couple_piece_pos couple : innerPossibleMoves) {
                if (foundVictory)
                    break;
                for (Position pos : couple.pos) {
                    Position posMain = couple.piece.Position;
                    Terrain tmp = new Terrain(Game);
                    tmp.Create();
                    tmp._terrain = state.Terrain.Copy(tmp);
                    tmp._terrain[posMain.l][posMain.c].move(pos.l, pos.c);
                    State s = new State(tmp);
                    s.GameMode = Sequence.PMM;
                    s.pass = state.pass;
                    s.firstMove = state.firstMove;
                    s.secondMove = new FromTo(posMain, pos);
                    AllPMMStates.add(s);
                    if (Math.abs(s.score()) == 9999) {
                        foundVictory = true;
                        break;
                    }
                }
            }
        }
        // Merge the three lists of states
        AllPMMStates.addAll(deepPMMStates);
        AllPMMStates.addAll(innerPMMStates);
    }

    public MiniMax(Jeu j, PieceType type) {
        AI_TYPE = type;
        Game = j;
    }

    public State bestMove;

    /**
     * This is a function that calculates all the possible outcomes of the current
     * state of the game. It will try to make the most relevant move in the end.
     * 
     * @param currentState The current state of the game.
     * @param maxDepth     The higher this value, the smarter the IA. Must be higher
     *                     than 0.
     * @param maxPlayer    true = AI. false = Human
     * @return
     * @author Alaa
     */
    public int VanillaMiniMax(State currentState, int maxDepth, boolean maxPlayer) {
        if (maxDepth == 0) {
            bestMove = currentState;
            System.out.println("Score : "+currentState.score());
            return Evaluator.scoreOfBoard(currentState.Terrain);
        }
        if (!maxPlayer) {
            int bestScore = Integer.MIN_VALUE;
            this.AI_TYPE = PieceType.White;
            int score = VanillaMiniMax(winningMove(currentState.Game), maxDepth - 1, false);
            bestScore = Math.max(bestScore, score);
            return bestScore;
        } else {
            int bestScore = Integer.MAX_VALUE;
            this.AI_TYPE = PieceType.Black;

            int score = VanillaMiniMax(winningMove(currentState.Game), maxDepth - 1, true);
            bestScore = Math.min(bestScore, score);
            return bestScore;
        }
    }

    /**
     * This function returns the most relevant "next" move.
     * 
     * @param game
     * @return
     */
    public State winningMove(Jeu game) {
        AllMMPStates.clear();
        AllMMPStates.clear();
        AllPMMStates.clear();
        State s = new State(game);
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
            if (AllPMMStates.size() > 0)
                bestOptions.add(AllPMMStates.get(AllPMMStates.size() - 1));
            if (AllMPMStates.size() > 0)
                bestOptions.add(AllMPMStates.get(AllMPMStates.size() - 1));
            if (AllMMPStates.size() > 0)
                bestOptions.add(AllMMPStates.get(AllMMPStates.size() - 1));

            sort(bestOptions);
            return bestOptions.get(2);
        } else {
            if (AllPMMStates.size() > 0)
                bestOptions.add(AllPMMStates.get(0));
            if (AllMPMStates.size() > 0)
                bestOptions.add(AllMPMStates.get(0));
            if (AllMMPStates.size() > 0)
                bestOptions.add(AllMMPStates.get(0));
            sort(bestOptions);
            return bestOptions.get(0);
        }

    }

}