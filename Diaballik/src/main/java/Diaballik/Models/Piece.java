package Diaballik.Models;

import javax.swing.*;

import Diaballik.Controllers.TerrainUtils;

public class Piece extends JButton implements IPiece{
    public PieceType Type = PieceType.Empty;
    public Position Position;
    public boolean HasBall = false;
    public Terrain Parent;
    public Piece(PieceType type, boolean hasBall, int l, int c, Terrain parent) {
        this.Type = type;
        this.Position = new Position(l, c);
        this.HasBall = hasBall;
        this.Parent = parent;
    }

    @Override
    public void addBall() {
        if (this.HasBall) {
            throw new IllegalStateException("Le joueur ne peut avoir qu'une seule balle.");
        }
        this.HasBall = true;
    }

    @Override
    public void removeBall() {
        if (!this.HasBall) {
            throw new IllegalStateException("le joueur n'a pas déjà de balle.");
        }
        this.HasBall = false;
    }

    @Override
    public void move(int l, int c) {
        TerrainUtils.Swap(this, );
    }

    @Override
    public Piece Clone() {
        return new Piece(this.Type, this.HasBall, this.Position.l, this.Position.c,this.Parent);
    }

}