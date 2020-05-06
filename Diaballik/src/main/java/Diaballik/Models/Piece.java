package Diaballik.Models;

import java.util.ArrayList;

import javax.swing.*;

import Diaballik.Controllers.TerrainUtils;

public class Piece extends JButton implements IPiece {
    private static final long serialVersionUID = 1L;

    public PieceType Type = PieceType.Empty;
    public Position Position;
    public boolean HasBall = false;
    public Terrain Parent;
    public boolean SelectionPasse = false;
    public boolean SelectionDeplacement = false;
    public boolean PossiblePasse = false;
    public boolean PossibleDeplacement = false;

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
        TerrainUtils.Swap(this, Parent.getTerrain()[l][c]);
    }

    @Override
    public Piece Clone() {
        return new Piece(this.Type, this.HasBall, this.Position.l, this.Position.c, this.Parent);
    }

    public String toString() {
        return "[" + this.Type + "," + this.HasBall + "," + this.Position + "]";
    }

    // retourne toutes les diagonales
    public ArrayList<Position> getDiagonals() {
        ArrayList<Position> result = new ArrayList<Position>();
        result.addAll(getDiagonals_bd());
        result.addAll(getDiagonals_bg());
        result.addAll(getDiagonals_hd());
        result.addAll(getDiagonals_hg());
        return result;
    }

    // retourne les positions diagonales bas droite
    public ArrayList<Position> getDiagonals_bd() {
        ArrayList<Position> result = new ArrayList<Position>();
        int i = Position.l;
        int j = Position.c;
        // bas droite
        while ((i + 1 < Parent.taille()) && (j + 1 < Parent.taille())) {
            i = i + 1;
            j = j + 1;
            result.add(new Position(i, j));
        }
        return result;
    }

    // retourne les positions diagonales bas gauche
    public ArrayList<Position> getDiagonals_bg() {
        ArrayList<Position> result = new ArrayList<Position>();
        int i = Position.l;
        int j = Position.c;
        // bas gauche
        while ((i + 1 < Parent.taille()) && (j - 1 >= 0)) {
            i = i + 1;
            j = j - 1;
            result.add(new Position(i, j));
        }
        return result;
    }

    // retourne les positions diagonales haut gauche
    public ArrayList<Position> getDiagonals_hg() {
        ArrayList<Position> result = new ArrayList<Position>();
        int i = Position.l;
        int j = Position.c;
        // haut gauche
        while ((i - 1 >= 0) && (j - 1 >= 0)) {
            i = i - 1;
            j = j - 1;
            result.add(new Position(i, j));
        }
        return result;
    }

    // retourne les positions diagonales haut droite
    public ArrayList<Position> getDiagonals_hd() {
        ArrayList<Position> result = new ArrayList<Position>();
        int i = Position.l;
        int j = Position.c;
        // haut droite
        while ((i - 1 >= 0) && (j + 1 < Parent.taille())) {
            i = i - 1;
            j = j + 1;
            result.add(new Position(i, j));
        }
        return result;
    }

    // @Override
    public ArrayList<Position> PossiblePositions() {
        ArrayList<Position> result = new ArrayList<Position>();

        if (((this.Position.l - 1) >= 0)
                && !this.Parent.isOccupied(new Position(this.Position.l - 1, this.Position.c))) {
            result.add(new Position(this.Position.l - 1, this.Position.c));
            if (((this.Position.l - 2) >= 0)
                    && !this.Parent.isOccupied(new Position(this.Position.l - 2, this.Position.c))) {
                result.add(new Position(this.Position.l - 2, this.Position.c));
            }
        }
        if (((this.Position.l + 1) < this.Parent.taille())
                && !this.Parent.isOccupied(new Position(this.Position.l + 1, this.Position.c))) {
            result.add(new Position(this.Position.l + 1, this.Position.c));
            if (((this.Position.l + 2) < this.Parent.taille())
                    && !this.Parent.isOccupied(new Position(this.Position.l + 2, this.Position.c))) {
                result.add(new Position(this.Position.l + 2, this.Position.c));
            }
        }
        if (((this.Position.c + 1) < this.Parent.taille())
                && !this.Parent.isOccupied(new Position(this.Position.l, this.Position.c + 1))) {
            result.add(new Position(this.Position.l, this.Position.c + 1));
            if (((this.Position.c + 2) < this.Parent.taille())
                    && !this.Parent.isOccupied(new Position(this.Position.l, this.Position.c + 2))) {
                result.add(new Position(this.Position.l, this.Position.c + 2));
            }
        }
        if (((this.Position.c - 1) >= 0)
                && !this.Parent.isOccupied(new Position(this.Position.l, this.Position.c - 1))) {
            result.add(new Position(this.Position.l, this.Position.c - 1));
            if (((this.Position.c - 2) >= 0)
                    && !this.Parent.isOccupied(new Position(this.Position.l, this.Position.c - 2))) {
                result.add(new Position(this.Position.l, this.Position.c - 2));
            }
        }
        if (((this.Position.c - 1) >= 0 && (this.Position.l - 1) >= 0)
                && !this.Parent.isOccupied(new Position(this.Position.l - 1, this.Position.c - 1))) {
            result.add(new Position(this.Position.l - 1, this.Position.c - 1));
        }
        if (((this.Position.c + 1) < this.Parent.taille() && (this.Position.l + 1) < this.Parent.taille())
                && !this.Parent.isOccupied(new Position(this.Position.l + 1, this.Position.c + 1))) {
            result.add(new Position(this.Position.l + 1, this.Position.c + 1));
        }
        if (((this.Position.c - 1) >= 0 && (this.Position.l + 1) < this.Parent.taille())
                && !this.Parent.isOccupied(new Position(this.Position.l + 1, this.Position.c - 1))) {
            result.add(new Position(this.Position.l + 1, this.Position.c - 1));
        }
        if (((this.Position.c + 1) < this.Parent.taille() && (this.Position.l - 1) >= 0)
                && !this.Parent.isOccupied(new Position(this.Position.l - 1, this.Position.c + 1))) {
            result.add(new Position(this.Position.l - 1, this.Position.c + 1));
        }
        return result;
    }

    public ArrayList<Position> passesPossibles() {
        int i = this.Position.l;
        int j = this.Position.c;
        ArrayList<Position> result = new ArrayList<Position>();

        // horizontales + verticales
        for (int l = i + 1; l < Parent.taille(); l++) {

            if (Parent.isOccupied(new Position(l, j))) {
                if (Parent.getTerrain()[l][j].Type != this.Type) {
                    // On ne peut pas aller plus loin dans les passes
                    break;
                } else {
                    result.add(new Position(l, j));
                }
            }
        }
        for (int l = i - 1; l >= 0; l--) {
            if (Parent.isOccupied(new Position(l, j))) {
                if (Parent.getTerrain()[l][j].Type != this.Type) {
                    // On ne peut pas aller plus loin dans les passes
                    break;
                } else {
                    result.add(new Position(l, j));
                }
            }
        }
        for (int c = j + 1; c < Parent.taille(); c++) {
            if (Parent.isOccupied(new Position(i, c))) {
                if (Parent.getTerrain()[i][c].Type != this.Type) {
                    // On ne peut pas aller plus loin dans les passes
                    break;
                } else {
                    result.add(new Position(i, c));
                }
            }
        }

        for (int c = j - 1; c >= 0; c--) {
            if (Parent.isOccupied(new Position(i, c))) {
                if (Parent.getTerrain()[i][c].Type != this.Type) {
                    // On ne peut pas aller plus loin dans les passes
                    break;
                } else {
                    result.add(new Position(i, c));
                }
            }
        }

        // diagonales
        ArrayList<Position> diag = getDiagonals_hg();
        for (Position pos : diag) {
            if (Parent.isOccupied(pos)) {
                if (Parent.getTerrain()[pos.l][pos.c].Type != this.Type) {
                    // On ne peut pas aller plus loin dans les passes
                    break;
                } else {
                    result.add(pos);
                }
            }
        }

        diag = getDiagonals_bd();
        for (Position pos : diag) {
            if (Parent.isOccupied(pos)) {
                if (Parent.getTerrain()[pos.l][pos.c].Type != this.Type) {
                    // On ne peut pas aller plus loin dans les passes
                    break;
                } else {
                    result.add(pos);
                }
            }
        }

        diag = getDiagonals_hd();
        for (Position pos : diag) {
            if (Parent.isOccupied(pos)) {
                if (Parent.getTerrain()[pos.l][pos.c].Type != this.Type) {
                    // On ne peut pas aller plus loin dans les passes
                    break;
                } else {
                    result.add(pos);
                }
            }
        }

        diag = getDiagonals_bg();
        for (Position pos : diag) {
            if (Parent.isOccupied(pos)) {
                if (Parent.getTerrain()[pos.l][pos.c].Type != this.Type) {
                    // On ne peut pas aller plus loin dans les passes
                    break;
                } else {
                    result.add(pos);
                }
            }
        }

        return result;
    }
}