package Diaballik.IA;

import java.util.ArrayList;

import Diaballik.Models.*;

public class Evaluation {
    /**
     * The score of this piece. |score| = how dominant it is on the board.
     * 
     * @param p The piece to calculate.
     * @return Integer = a score.
     */
    public static int scoreOfPiece(Piece p) {
        int score = 0;
        if (p.Type == PieceType.White) {
            score += 10;
            if (p.HasBall) {
                score += 10;
            }
            score += score * (6 - p.Position.l);
            ArrayList<Position> passesPossibles = p.Parent.getPieceWithBall(p.Type).passesPossibles();
            if (passesPossibles.contains(p.Position)) {
                score += 5;
            }
        } else if (p.Type == PieceType.Black) {
            score -= 10;
            if (p.HasBall) {
                score -= 10;
            }
            score += score * (p.Position.l);
            ArrayList<Position> passesPossibles = p.Parent.getPieceWithBall(p.Type).passesPossibles();
            if (passesPossibles.contains(p.Position)) {
                score -= 5;
            }
        }
        return score;
    }

    public static int scoreOfBoard(Terrain t) {
        if (t._jeuParent.victoire() == PieceType.Black) {
            return -9999;
        } else if (t._jeuParent.victoire() == PieceType.White) {
            return 9999;
        }
        int score = 0;
        Piece[][] board = t.getTerrain();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                score += scoreOfPiece(board[i][j]);
            }
        }
        return score;
    }
}