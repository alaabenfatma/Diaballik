package Diaballik.Models;

public class Piece implements IPiece {
    public PieceType Type = PieceType.Empty;
    public Position Position;
    public boolean HasBall = false;
    public Piece(PieceType type, boolean hasBall, int l, int c){
        this.Type  = type;
        this.Position = new Position(l, c);
        this.HasBall = hasBall;
        this.move(l, c);
    }
    @Override
    public void addBall() {
        if(this.HasBall){
            throw new IllegalStateException("Le joueur ne peut avoir qu'une seule balle.");
        }
        this.HasBall = true;
    }

    @Override
    public void removeBall() {
        if(!this.HasBall){
            throw new IllegalStateException("le joueur n'a pas déjà de balle.");
        }
        this.HasBall = false;
    }

    @Override
    public void move(int l, int c) {
        
    }

}