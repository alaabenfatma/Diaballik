package Diaballik.Models.IA;

import Diaballik.Models.Position;
/**
 * This class saves two positions.
 * These position represent a movement or a pass.
 * i.e : (0,1) => (1,1)
 */
public class Couple {
    public Position p1;
    public Position p2;

    public Couple(Position p, Position pp) {
        this.p1 = p;
        this.p2 = pp;
    }
}