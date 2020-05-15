package Diaballik.Models;

public class FromTo {
    public Position From = new Position(0,0);
    public Position To = new Position(0,0);

    /**
     * This is a class that saves the coordinates of two pieces before moving them.
     * 
     * @param f The starting position
     * @param t The new coordinates
     */
    public FromTo(Position f, Position t) {
        f.CopyCoordinates(From);
        t.CopyCoordinates(To);
    }
}