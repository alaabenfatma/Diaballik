package Diaballik.IA;
import java.util.ArrayList;

import Diaballik.Models.*;

public class Couple_piece_pos {
    Piece piece;
    ArrayList<Position> pos;
    public Couple_piece_pos(Piece p, ArrayList<Position> pos){
        this.piece = p;
        this.pos = pos;
    }
    @Override
    public String toString(){
        return piece.toString() + "\n" + pos.toString() + "\n";
    }
}