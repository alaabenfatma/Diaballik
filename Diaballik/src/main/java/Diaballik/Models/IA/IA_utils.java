package Diaballik.Models.IA;

import java.util.ArrayList;

import Diaballik.Controllers.TerrainUtils;
import Diaballik.Models.*;

public class IA_utils {
    // Renvoi tous les couples piece:positions_possibles
    public static ArrayList<Couple_piece_pos> getAllPossibleMoves(PieceType type, Terrain tr, int movesLeft) {
        ArrayList<Couple_piece_pos> result = new ArrayList<Couple_piece_pos>();
        for (int i = 0; i < tr.taille(); i++) {
            for (Piece p : tr.getTerrain()[i]) {
                if (p.Type == type && !p.HasBall) {
                    result.add(new Couple_piece_pos(p, p.PossiblePositions(movesLeft)));
                }
            }
        }
        return result;
    }

    public static ArrayList<Couple_piece_pos> getAllPossibleMovesDistance(PieceType type, Terrain tr, int distance) {
        ArrayList<Couple_piece_pos> result = new ArrayList<Couple_piece_pos>();
        for (int i = 0; i < tr.taille(); i++) {
            for (Piece p : tr.getTerrain()[i]) {
                if (p.Type == type && !p.HasBall) {
                    ArrayList<Position> positions = p.PossiblePositions(distance);
                    Couple_piece_pos cpl = new Couple_piece_pos(p, new ArrayList<Position>());
                    for (Position pos : positions) {
                        if (TerrainUtils.Distance(p.Position, pos) == distance && p.Position!=pos) {
                            cpl.pos.add(pos);
                        }
                    }
                    result.add(cpl);

                }
            }
        }
        return result;
    }

}