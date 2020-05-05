package Diaballik.Models;

import javax.management.RuntimeErrorException;

//import java.util.*;

public class Terrain implements ITerrain {
    public Piece[][] _terrain;

    /**
     * Mettre tous les case = vide
     */
    private int taille;

    private void init() {
        this.taille = 7;
        _terrain = new Piece[taille][taille];
        for (int l = 0; l < taille; l++) {
            for (int c = 0; c < taille; c++) {
                _terrain[l][c] = new Piece(PieceType.Empty, false, l, c, this);
            }
        }
        for (int i = 0; i < taille; i++) {
            _terrain[0][i].Type = PieceType.Black;
        }
        for (int i = 0; i < taille; i++) {
            _terrain[6][i].Type = PieceType.White;
        }

    }

    @Override
    public Piece[][] Create() {
        init();
        Setup();
        return _terrain;
    }

    @Override
    public void Setup() {
        /*** B ***/
        /*******/
        /*******/
        /*******/
        /*******/
        /*** B ***/
        _terrain[0][3].addBall();
        _terrain[6][3].addBall();
    }

    @Override
    public void Destroy() {
        System.gc();
    }

    @Override
    public void PrintTerrain() {
        for (int l = 0; l < 7; l++) {
            for (int c = 0; c < 7; c++) {
                if (_terrain[l][c].Type == PieceType.Empty) {
                    System.out.print(". ");
                } else {
                    if (_terrain[l][c].Type == PieceType.Black) {
                        if (_terrain[l][c].HasBall == true) {
                            System.out.print("⍟ ");
                        } else {
                            System.out.print("B ");
                        }
                    } else {
                        if (_terrain[l][c].HasBall == true) {
                            System.out.print("☆ ");
                        } else {
                            System.out.print("W ");
                        }
                    }
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    @Override
    public Piece[][] getTerrain() {
        return _terrain;
    }

    public int taille() {
        return this.taille;
    }

    public boolean isOccupied(Position pos) {
        return (_terrain[pos.l][pos.c].Type != PieceType.Empty);
    }

    //Comme son nom l'indique
    public Piece getPieceWithBall(PieceType tp) {
        if(tp == PieceType.Empty){
            throw new RuntimeException("Erreur dans getPieceWithBall, une piece de type Empty ne peut avoir la balle");
        }
        for (int i = 0; i < taille(); i++) {
            for (Piece p : _terrain[i]) {
                if (p.HasBall && p.Type == tp) {
                    return p;
                }
            }
        }
        throw new RuntimeException("Erreur dans getPieceWithBall, aucune piece ne semble avoir la balle??");
    }

}