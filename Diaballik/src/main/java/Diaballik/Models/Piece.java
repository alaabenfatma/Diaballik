package Diaballik.Models;

public class Piece implements IPiece {
    public PieceType Type = PieceType.Empty;
    public Position Position;
    public boolean HasBall = false;
    public Piece(PieceType type, boolean hasBall, int l, int c){
        this.Type  = type;
        this.Position = new Position(l, c);
        this.HasBall = hasBall;
    }
    @Override
    public void addBall() {
        // TODO Auto-generated method stub

    }

    @Override
    public void removeBall() {
        // TODO Auto-generated method stub

    }

    @Override
    public void move(int l, int c) {
        // TODO Auto-generated method stub

    }

}