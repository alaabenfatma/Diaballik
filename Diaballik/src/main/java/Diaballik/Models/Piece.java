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

    private boolean getDiagonals_utils2(int i, int j, int d1, int d2){
        boolean b;
        if(d1<0){
            b = i + d1 >= 0;
        }
        else{
            b = i + d1 < Parent.taille(); 
        }
        if(d2<0){
            b = b && (j + d2 >= 0);
        }
        else{
            b = b && (j + d2 < Parent.taille());
        }
        return b;
    }

    private ArrayList<Position> getDiagonals_utils(int d1, int d2) { 
        ArrayList<Position> result = new ArrayList<Position>();
        int i = Position.l;
        int j = Position.c;
        boolean b = getDiagonals_utils2(i, j, d1, d2);
        while (b) {
            i = i + d1;
            j = j + d2;
            result.add(new Position(i, j));
            b = getDiagonals_utils2(i, j, d1, d2);
        }
        return result;
    }

    // retourne toutes les diagonales
    public ArrayList<Position> getDiagonals() {
        ArrayList<Position> result = new ArrayList<Position>();
        result.addAll(getDiagonals_utils(1, 1)); //bas droit
        result.addAll(getDiagonals_utils(1, -1)); //bas gauche
        result.addAll(getDiagonals_utils(-1, -1)); //haut gauche
        result.addAll(getDiagonals_utils(-1, 1)); //haut droit
        return result;
    }

    public ArrayList<Position> PossiblePositions(int deplacements) {
        ArrayList<Position> result = new ArrayList<Position>();

        if (((this.Position.l - 1) >= 0)
                && !this.Parent.isOccupied(new Position(this.Position.l - 1, this.Position.c)) && deplacements>=1) {
            result.add(new Position(this.Position.l - 1, this.Position.c));
            if (((this.Position.l - 2) >= 0)
                    && !this.Parent.isOccupied(new Position(this.Position.l - 2, this.Position.c)) && deplacements>=2) {
                result.add(new Position(this.Position.l - 2, this.Position.c));
            }
        }
        if (((this.Position.l + 1) < this.Parent.taille())
                && !this.Parent.isOccupied(new Position(this.Position.l + 1, this.Position.c)) && deplacements>=1) {
            result.add(new Position(this.Position.l + 1, this.Position.c));
            if (((this.Position.l + 2) < this.Parent.taille())
                    && !this.Parent.isOccupied(new Position(this.Position.l + 2, this.Position.c)) && deplacements>=2) {
                result.add(new Position(this.Position.l + 2, this.Position.c));
            }
        }
        if (((this.Position.c + 1) < this.Parent.taille())
                && !this.Parent.isOccupied(new Position(this.Position.l, this.Position.c + 1)) && deplacements>=1) {
            result.add(new Position(this.Position.l, this.Position.c + 1));
            if (((this.Position.c + 2) < this.Parent.taille())
                    && !this.Parent.isOccupied(new Position(this.Position.l, this.Position.c + 2)) && deplacements>=2) {
                result.add(new Position(this.Position.l, this.Position.c + 2));
            }
        }
        if (((this.Position.c - 1) >= 0)
                && !this.Parent.isOccupied(new Position(this.Position.l, this.Position.c - 1)) && deplacements>=1) {
            result.add(new Position(this.Position.l, this.Position.c - 1));
            if (((this.Position.c - 2) >= 0)
                    && !this.Parent.isOccupied(new Position(this.Position.l, this.Position.c - 2)) && deplacements>=2) {
                result.add(new Position(this.Position.l, this.Position.c - 2));
            }
        }

        if (((this.Position.c - 1) >= 0 && (this.Position.l - 1) >= 0)
                && !this.Parent.isOccupied(new Position(this.Position.l - 1, this.Position.c - 1)) && deplacements>=2) {
            if (!(this.Parent.isOccupied(new Position(this.Position.l - 1, this.Position.c))
                    && this.Parent.isOccupied(new Position(this.Position.l, this.Position.c - 1)))) {
                result.add(new Position(this.Position.l - 1, this.Position.c - 1));
            }
        }
        if (((this.Position.c + 1) < this.Parent.taille() && (this.Position.l + 1) < this.Parent.taille())
                && !this.Parent.isOccupied(new Position(this.Position.l + 1, this.Position.c + 1)) && deplacements>=2) {
            if (!(this.Parent.isOccupied(new Position(this.Position.l + 1, this.Position.c))
                    && this.Parent.isOccupied(new Position(this.Position.l, this.Position.c + 1)))) {
                result.add(new Position(this.Position.l + 1, this.Position.c + 1));
            }
        }
        if (((this.Position.c - 1) >= 0 && (this.Position.l + 1) < this.Parent.taille())
                && !this.Parent.isOccupied(new Position(this.Position.l + 1, this.Position.c - 1)) && deplacements>=2) {
            if (!(this.Parent.isOccupied(new Position(this.Position.l, this.Position.c - 1))
                    && this.Parent.isOccupied(new Position(this.Position.l + 1, this.Position.c)))) {
                result.add(new Position(this.Position.l + 1, this.Position.c - 1));
            }
        }
        if (((this.Position.c + 1) < this.Parent.taille() && (this.Position.l - 1) >= 0)
                && !this.Parent.isOccupied(new Position(this.Position.l - 1, this.Position.c + 1)) && deplacements>=2) {
            if (!(this.Parent.isOccupied(new Position(this.Position.l - 1, this.Position.c))
                    && this.Parent.isOccupied(new Position(this.Position.l, this.Position.c + 1)))) {
                result.add(new Position(this.Position.l - 1, this.Position.c + 1));
            }
        }
        
        return result;
    }

    private void addPossible_diag(ArrayList<Position> result, ArrayList<Position> diag){
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
    }

    private int addPossible_hv(ArrayList<Position> result, int a, int b){
        if (Parent.isOccupied(new Position(a, b))) {
            if (Parent.getTerrain()[a][b].Type != this.Type) {
                // On ne peut pas aller plus loin dans les passes
                return -1;
            } else {
                result.add(new Position(a, b));
            }
        }
        return 0;
    }

    public ArrayList<Position> passesPossibles() {
        int i = this.Position.l;
        int j = this.Position.c;
        ArrayList<Position> result = new ArrayList<Position>();

        // horizontales + verticales
        for (int l = i + 1; l < Parent.taille(); l++) {
            if(addPossible_hv(result, l, j)==-1){
                break;
            }
        }
        for (int l = i - 1; l >= 0; l--) {
            if(addPossible_hv(result, l, j)==-1){
                break;
            }
        }
        for (int c = j + 1; c < Parent.taille(); c++) {
            if(addPossible_hv(result, i, c)==-1){
                break;
            }
        }

        for (int c = j - 1; c >= 0; c--) {
            if(addPossible_hv(result, i, c)==-1){
                break;
            }
        }

        // diagonales
        ArrayList<Position> diag = getDiagonals_utils(-1, -1);
        addPossible_diag(result, diag);

        diag = getDiagonals_utils(1, 1);
        addPossible_diag(result, diag);

        diag = getDiagonals_utils(-1, 1);
        addPossible_diag(result, diag);

        diag = getDiagonals_utils(1, -1);
        addPossible_diag(result, diag);

        return result;
    }
}