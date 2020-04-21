package Diaballik.Models;

public interface IPiece {
    public void addBall();
    public void removeBall();
    public void move(int l, int c);
    public String toString();
}