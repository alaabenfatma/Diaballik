package Diaballik.Models;

import javax.annotation.processing.SupportedOptions;

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
    public boolean equals(Object o) {
        Position p = (Position)o;
        return (this.l == p.l && this.c == p.c);
    }

    @Override
    public String toString(){
        return "("+l+","+c+")";
    }
}