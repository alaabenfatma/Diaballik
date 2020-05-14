package Diaballik.Models;

import java.util.Stack;

public class Terrain implements ITerrain {
    public Piece[][] _terrain;

    /**
     * Mettre tous les case = vide
     */
    private int taille;
    public Jeu _jeuParent;
    Stack<InfCoups> coups;
    Stack<InfCoups> ctrly;

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
        coups = new Stack<InfCoups>();
        ctrly = new Stack<InfCoups>();
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

    // Comme son nom l'indique
    public Piece getPieceWithBall(PieceType tp) {
        if (tp == PieceType.Empty) {
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

    public Terrain clone() {
        Terrain copy = new Terrain();
        copy._jeuParent = this._jeuParent;
        copy.taille = this.taille;
        copy._terrain = new Piece[this.taille()][this.taille()];
        for (int i = 0; i < this.taille(); i++) {
            for (int j = 0; j < this.taille(); j++) {
                Piece piece = this._terrain[i][j];
                copy._terrain[i][j] = new Piece(piece.Type,piece.HasBall,piece.Position.l,piece.Position.c,piece.Parent);
            }
        }
        return copy;
    }
    // retourne le type de piece qui a gagnée
    public PieceType victoire() {
        for (int i = 0; i < this.taille(); i++) {
            if ((this.getTerrain()[0][i].Type == PieceType.White) && (this.getTerrain()[0][i].HasBall)) {
                System.out.println("Les blancs ont gagné !");
                return PieceType.White;
            }
            if ((this.getTerrain()[this.taille() - 1][i].Type == PieceType.Black)
                    && (this.getTerrain()[this.taille() - 1][i].HasBall)) {
                System.out.println("Les noirs ont gagné !");
                return PieceType.Black;
            }
        }
        return PieceType.Empty;
    }

    public void updateStack(int moves, int passes){
        coups.add(new InfCoups(clone(),_jeuParent.joueurCourant,moves,passes));
        ctrly = new Stack<InfCoups>();
    }

    public void ctrl_z(){
        
        if(coups.size() == 0){
            return;
        }
        _terrain = new Piece[taille][taille];
        for (int l = 0; l < taille; l++) {
            for (int c = 0; c < taille; c++) {
                _terrain[l][c] = coups.peek().tr.getTerrain()[l][c];
            }
        }
        _jeuParent.joueurCourant = coups.peek().jCourant;
        //_jeuParent.joueurCourant.nbMove = _jeuParent.infc.moves;
        _jeuParent.joueurCourant.nbMove = coups.peek().moves;
        _jeuParent.joueurCourant.passeDispo = coups.peek().passes;
        ctrly.add(coups.pop());
        
        
    }

    public void ctrl_y(){
        
        Terrain copy = ctrly.peek().tr;
        _terrain = copy._terrain;
        coups.add(coups.pop());
        
    }

    
    public char[][] toChar(){
        char[][] pieces = new char[7][7];
        for (int i = 0; i < 7; i++) {
            for (int k = 0; k < 7; k++) {
                if(this._terrain[i][k].Type==PieceType.White){
                    if(this._terrain[i][k].HasBall){
                        pieces[i][k] = '1';
                    }
                    else{
                        pieces[i][k] = 'W';
                    }
                }
                else if(this._terrain[i][k].Type==PieceType.Black){
                    if(this._terrain[i][k].HasBall){
                        pieces[i][k] = '0';
                    }
                    else{
                        pieces[i][k] = 'B';
                    }
                }
                else{
                    pieces[i][k] = ' ';
                }
            }
        }
        return pieces;
    }

}
