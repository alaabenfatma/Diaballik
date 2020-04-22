package Diaballik.Models;

public class Position {
    public int l, c;

    public Position(int _l, int _c) {
        this.l = _l;
        this.c = _c;
    }

    public boolean equals(Position p) {
        return (this.l == p.l && this.c == p.c);
    }

    @Override
    public String toString(){
        return "("+l+","+c+")";
    }
}