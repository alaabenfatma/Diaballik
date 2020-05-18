package Diaballik.Models.IA;

import java.util.ArrayList;

import Diaballik.Models.*;
/**
 * Heuristics
 */
class EvaluationConfig {

    int valueOfPiece = 10;
    int diagonalBonus = 5;
    int hasBallBonus = 10;
    int onOtherSide = 50;

    /**
     * Configures the values that are needed to calculate the final score of the
     * board.
     */
    public EvaluationConfig() {
        // Ignore
    }

    /**
     * Configures the values that are needed to calculate the final score of the
     * board.
     * 
     * @param valueOfPiece
     * @param diagonalBonus
     * @param hasBallBonus
     */
    public EvaluationConfig(int valueOfPiece, int diagonalBonus, int hasBallBonus, int onOtherSide) {
        this.valueOfPiece = valueOfPiece;
        this.diagonalBonus = diagonalBonus;
        this.hasBallBonus = hasBallBonus;
        this.onOtherSide = onOtherSide;
    }
}

/**
 * A class that is used to calculate the score of the board. The higher the
 * score, the higher the chance of the white players of winning. The lower, the
 * higher the chance of the black players winning.
 */
public class Evaluator {

    private static EvaluationConfig heuristics = new EvaluationConfig();

    /**
     * Configure the heuristics.
     * 
     * @param valueOfPiece
     * @param diagonalBonus
     * @param hasBallBonus
     */
    public static void init(int valueOfPiece, int diagonalBonus, int hasBallBonus, int onOtherSide) {
        heuristics = new EvaluationConfig(valueOfPiece, diagonalBonus, hasBallBonus, onOtherSide);
    }

    /**
     * The score of this piece. |score| = how dominant it is on the board.
     * 
     * @param p The piece to calculate.
     * @return Integer = a score.
     */
    public static int scoreOfPiece(Piece p) {
        int score = 0;
        if (p.Type == PieceType.White) {
            score += heuristics.valueOfPiece;
            if (p.HasBall) {
                score += heuristics.hasBallBonus;
            }
            score += score * (6 - p.Position.l);
            ArrayList<Position> passesPossibles = p.Parent.getPieceWithBall(p.Type).passesPossibles();
            if (passesPossibles.contains(p.Position)) {
                score += heuristics.diagonalBonus;
                if (p.Position.l == 0) {
                    score += 999;
                }
            }
            if (p.Position.l == 0) {
                score += heuristics.onOtherSide;
            }
        } else if (p.Type == PieceType.Black) {
            score -= heuristics.valueOfPiece;
            if (p.HasBall) {
                score -= heuristics.hasBallBonus;
            }
            score += score * (p.Position.l);
            ArrayList<Position> passesPossibles = p.Parent.getPieceWithBall(p.Type).passesPossibles();
            if (passesPossibles.contains(p.Position)) {
                score -= heuristics.diagonalBonus;
                if (p.Position.l == 6) {
                    score -= 999;
                }
            }
            if (p.Position.l == 6) {
                score -= heuristics.onOtherSide;
            }
        }
        return score;
    }

    public static int scoreOfBoard(Terrain t) {
        if (t.victoire() == PieceType.Black) {
            return -9999;
        } else if (t.victoire() == PieceType.White) {
            return 9999;
        }

        if (t.antijeuIA(t, PieceType.Black)) {
            return 9999;
        } else if (t.antijeuIA(t, PieceType.White)) {
            return -9999;
        }
        int score = 0;
        Piece[][] board = t.getTerrain();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                int innerScore = scoreOfPiece(board[i][j]);
                if(Math.abs(innerScore)>999){
                    score = innerScore;
                    return innerScore;
                }
                else{
                    score += innerScore;
                }
                
            }
        }
        return score;
    }
}