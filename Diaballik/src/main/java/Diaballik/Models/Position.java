package Diaballik.Models;

public class Position {
    public int l, c;

    public Position(int _l, int _c) {
        this.l = _l;
        this.c = _c;
    }

    public String toString(){
        return "("+l+","+c+")";
    }
}