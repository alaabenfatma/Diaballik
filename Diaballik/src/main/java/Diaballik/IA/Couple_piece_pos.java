package Diaballik.IA;
import java.util.ArrayList;

import Diaballik.Models.*;

public class Couple_piece_pos {
    Piece p;
    ArrayList<Position> pos;
    public Couple_piece_pos(Piece p, ArrayList<Position> pos){
        this.p = p;
        this.pos = pos;
    }
    @Override
    public String toString(){
        return p.toString() + "\n" + pos.toString() + "\n";
    }
}