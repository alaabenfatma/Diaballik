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

    void initVariante() {
        _terrain[0][1].Type = PieceType.White;
        _terrain[0][5].Type = PieceType.White;
        _terrain[6][1].Type = PieceType.Black;
        _terrain[6][5].Type = PieceType.Black;
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
            System.out.println("");
        }
        
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
    /**
     * @author Wassim
     * Permet de créer un objet clone (et non une reference) du terrain
     */
    public Terrain clone() {
        Terrain copy = new Terrain();
        copy._jeuParent = this._jeuParent;
        copy.taille = this.taille;
        copy._terrain = new Piece[this.taille()][this.taille()];
        for (int i = 0; i < this.taille(); i++) {
            for (int j = 0; j < this.taille(); j++) {
                Piece piece = this._terrain[i][j];
                //On fait attention que les Pieces soit également clonés, et non passé en référence
                copy._terrain[i][j] = new Piece(piece.Type, piece.HasBall, piece.Position.l, piece.Position.c,
                        piece.Parent);
            }
        }
        return copy;
    }

    public Terrain() {

    }

    public Terrain(Jeu j) {
        this._jeuParent = j;
    }

    /**
     * @author Wassim
     * Retourne le type de piece qui a gagné
     */
    public PieceType victoire() {
        for (int i = 0; i < this.taille(); i++) {
            if ((this.getTerrain()[0][i].Type == PieceType.White) && (this.getTerrain()[0][i].HasBall)) {
                return PieceType.White;
            }
            if ((this.getTerrain()[this.taille() - 1][i].Type == PieceType.Black)
                    && (this.getTerrain()[this.taille() - 1][i].HasBall)) {
                return PieceType.Black;
            }
        }
        return PieceType.Empty;
    }

    /**
     * @author Wassim
     * Met a jour la pile
     */
    public void updateStack(int moves, int passes) {
        //A chaque mouvement, on empile le terrain courant dans la pile coups
        //(Pour le ctrl-z)
        coups.add(new InfCoups(clone(), _jeuParent.joueurCourant, moves, passes));
    }

    public boolean antijeuIA(Terrain terr, PieceType actuel) {
        int cpt = 0;
        int nbAdverseColonne = 0;
        // PieceType actuel = joueurCourant.couleur;
        PieceType adverse = (actuel == PieceType.Black) ? PieceType.White : PieceType.Black;
        int l = -1, c = -1;

        for (int colonne = 0; colonne < terr.taille(); colonne++) {
            nbAdverseColonne = 0;
            // verifie s'il y a un pion adverse dans la premiere colonne
            if (colonne == 0) {
                for (int ligne = 0; ligne < terr.taille(); ligne++) {
                    if (terr.getTerrain()[ligne][colonne].Type == adverse) {
                        nbAdverseColonne++;
                        l = ligne;
                        c = colonne;
                        if ((l + 1 < terr.taille()) && terr.getTerrain()[l + 1][c].Type == actuel)
                            cpt++;
                        if ((l - 1 >= 0) && terr.getTerrain()[l - 1][c].Type == actuel) {
                            cpt++;
                        }
                    }
                    if (nbAdverseColonne > 1)
                        return false;
                }
                if (nbAdverseColonne == 0)
                    return false;

            } else {
                // verifie dans les cases adjacente à droite la presence de pions adverses
                int ligne = l;
                if (ligne - 1 >= 0 && terr.getTerrain()[ligne - 1][colonne].Type == adverse) {
                    nbAdverseColonne++;
                    l = ligne - 1;
                    c = colonne;
                    if (l + 1 < terr.taille() && terr.getTerrain()[l + 1][c].Type == actuel)
                        cpt++;
                    if ((l - 1 >= 0) && terr.getTerrain()[l - 1][c].Type == actuel) {
                        cpt++;
                    }
                } else if (terr.getTerrain()[ligne][colonne].Type == adverse) {
                    nbAdverseColonne++;
                    l = ligne;
                    c = colonne;
                    if (l + 1 < terr.taille() && terr.getTerrain()[l + 1][c].Type == actuel)
                        cpt++;
                    if ((l - 1 >= 0) && terr.getTerrain()[l - 1][c].Type == actuel) {
                        cpt++;
                    }
                } else if (ligne + 1 < 7 && terr.getTerrain()[ligne + 1][colonne].Type == adverse) {
                    nbAdverseColonne++;
                    l = ligne + 1;
                    c = colonne;
                    if (l + 1 < terr.taille() && terr.getTerrain()[l + 1][c].Type == actuel)
                        cpt++;
                    if ((l - 1 >= 0) && terr.getTerrain()[l - 1][c].Type == actuel) {
                        cpt++;
                    }
                }
                if (nbAdverseColonne != 1)
                    return false;
            }
        }
        if (cpt >= 3) {
            // 
            return true;
        } else
            return false;
    }

    /**
     * @author Wassim
     * Reviens à l'état précedent du terrain 
     */
    public void ctrl_z() {

        if (coups.size() == 0) {
            return;
        }
        ctrly.add(new InfCoups(clone(), _jeuParent.joueurCourant, _jeuParent.joueurCourant.nbMove,
                _jeuParent.joueurCourant.passeDispo));
        _terrain = new Piece[taille][taille];
        //On reprend le terrain precedent
        for (int l = 0; l < taille; l++) {
            for (int c = 0; c < taille; c++) {
                _terrain[l][c] = coups.peek().tr.getTerrain()[l][c];
            }
        }
        //On remet le nombre de passes/deplacements comme il était avant
        _jeuParent.joueurCourant = coups.peek().jCourant;
        _jeuParent.joueurCourant.nbMove = coups.peek().moves;
        _jeuParent.joueurCourant.passeDispo = coups.peek().passes;
        coups.pop();
        //Si on joue contre une IA, on ne peut annuler que nos coups à nous..
        if (_jeuParent.joueur2.n == TypeJoueur.IA) {
            while (_jeuParent.joueurCourant != _jeuParent.joueur1) {
                ctrl_z();
            }
        }
    }

    /**
     * @author Wassim
     * Annule le ctrl-z
     */
    public void ctrl_y() {

        //plus ou moin parreil que ctrl-z (voir plus haut)
        if (ctrly.size() == 0) {
            return;
        }
        updateStack(_jeuParent.joueurCourant.nbMove, _jeuParent.joueurCourant.passeDispo);
        _terrain = new Piece[taille][taille];
        for (int l = 0; l < taille; l++) {
            for (int c = 0; c < taille; c++) {
                _terrain[l][c] = ctrly.peek().tr.getTerrain()[l][c];
            }
        }
        _jeuParent.joueurCourant = ctrly.peek().jCourant;
        _jeuParent.joueurCourant.nbMove = ctrly.peek().moves;
        _jeuParent.joueurCourant.passeDispo = ctrly.peek().passes;
        ctrly.pop();
        if (_jeuParent.joueur2.n == TypeJoueur.IA) {
            while (_jeuParent.joueurCourant != _jeuParent.joueur1) {
                ctrl_y();
            }
        }
    }

    public boolean UndoStackNotEmpty() {
        return (coups.size() != 0);
    }

    public boolean RedoStackNotEmpty() {
        return (ctrly.size() != 0);
    }

    public char[][] toChar() {
        char[][] pieces = new char[7][7];
        for (int i = 0; i < 7; i++) {
            for (int k = 0; k < 7; k++) {
                if (this._terrain[i][k].Type == PieceType.White) {
                    if (this._terrain[i][k].HasBall) {
                        pieces[i][k] = '1';
                    } else {
                        pieces[i][k] = 'W';
                    }
                } else if (this._terrain[i][k].Type == PieceType.Black) {
                    if (this._terrain[i][k].HasBall) {
                        pieces[i][k] = '0';
                    } else {
                        pieces[i][k] = 'B';
                    }
                } else {
                    pieces[i][k] = ' ';
                }
            }
        }
        return pieces;
    }

    public Piece[][] toPieces(char[][] chars) {
        Piece[][] pieces = new Piece[7][7];
        for (int i = 0; i < 7; i++) {
            for (int k = 0; k < 7; k++) {
                if (chars[i][k] == 'W') {
                    pieces[i][k] = new Piece(PieceType.White, false, i, k, this);
                }
                if (chars[i][k] == 'B') {
                    pieces[i][k] = new Piece(PieceType.Black, false, i, k, this);
                }
                if (chars[i][k] == '1') {
                    pieces[i][k] = new Piece(PieceType.White, true, i, k, this);
                }
                if (chars[i][k] == '0') {
                    pieces[i][k] = new Piece(PieceType.Black, true, i, k, this);
                }
                if (chars[i][k] == ' ') {
                    pieces[i][k] = new Piece(PieceType.Empty, false, i, k, this);
                }
            }
        }
        return pieces;
    }

    public Piece[][] Copy(Terrain pTerrain) {
        Piece[][] pieces = new Piece[7][7];
        for (int i = 0; i < 7; i++) {
            for (int k = 0; k < 7; k++) {
                if (this._terrain[i][k].Type == PieceType.White) {
                    pieces[i][k] = new Piece(PieceType.White, false, i, k, pTerrain);
                }
                if (this._terrain[i][k].Type == PieceType.Black) {
                    pieces[i][k] = new Piece(PieceType.Black, false, i, k, pTerrain);
                }
                if (this._terrain[i][k].Type == PieceType.White && this._terrain[i][k].HasBall) {
                    pieces[i][k] = new Piece(PieceType.White, true, i, k, pTerrain);
                }
                if (this._terrain[i][k].Type == PieceType.Black && this._terrain[i][k].HasBall) {
                    pieces[i][k] = new Piece(PieceType.Black, true, i, k, pTerrain);
                }
                if (this._terrain[i][k].Type == PieceType.Empty) {
                    pieces[i][k] = new Piece(PieceType.Empty, false, i, k, pTerrain);
                }
            }
        }
        return pieces;
    }

}
