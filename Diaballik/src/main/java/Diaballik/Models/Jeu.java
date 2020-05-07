package Diaballik.Models;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import Diaballik.Vue.*;

import Diaballik.Controllers.TerrainUtils;
import Diaballik.IA.Random_IA;
//import Diaballik.Controllers.TerrainUtils;
import Diaballik.Models.*;
import Diaballik.Patterns.Observable;

public class Jeu extends Observable {
    public Terrain tr;
    public Joueur joueur1;
    public Joueur joueur2;
    public Joueur joueurCourant;
    public boolean gameOver = false;
    Piece from = null;
    Piece to = null;
    private ArrayList<Position> listeMarque = new ArrayList<Position>();
    private ArrayList<Position> listePositionsPossibles = new ArrayList<Position>();
    public boolean IA;

    public Jeu() {
        tr = new Terrain();
        tr.Create();

    }

    public void start() {

        // Joueur 1 (Là le joueur 1 joue forcement les blancs, mais on pourra modifier
        // dans les
        // parametres ci-dessous)

        if (IA) {
            joueur1 = new Joueur(TypeJoueur.Joueur1, PieceType.White, 2, 1);
            joueur2 = new Joueur(TypeJoueur.IA, PieceType.Black, 2, 1);
        } else {
            joueur1 = new Joueur(TypeJoueur.Joueur1, PieceType.White, 2, 1);
            joueur2 = new Joueur(TypeJoueur.Joueur2, PieceType.Black, 2, 1);
        }

        // joueur courant
        joueurCourant = joueur1;
        tr._jeuParent = this;

    }

    public Jeu(Terrain terrain) {
        tr = terrain;

    }

    public void FinTour() {
        if (joueurCourant == joueur1) {
            joueurCourant = joueur2;
        } else {
            joueurCourant = joueur1;
        }
        joueurCourant.nbMove = 2;
        joueurCourant.passeDispo = 1;
        metAJour();
        RetirerMarque();

    }

    private boolean PieceAuJoueur(Piece select) {
        if (select.Type == PieceType.White && joueurCourant == joueur1)
            return true;
        else if (select.Type == PieceType.Black && joueurCourant == joueur2 && !IA)
            return true;
        else
            return false;
    }

    public void SelectionPiece(int l, int c) {
        Piece select = tr._terrain[l][c];
        // première sélection d'une piece qui nous appartient si aucune sélection
        // précedemment
        if (from == null && PieceAuJoueur(select)) {
            from = select;
            if ((!select.HasBall))
                SelectionDeplacement(l, c);
            else {
                SelectionPasse(from);
            }
        } else if (from != null) {
            to = select;
            if ((from.HasBall) && PieceAuJoueur(select)) {
                if (from != to)
                    Passe();
                else {
                    RetirerMarque();
                    from = to = null;
                }
            }

            else {
                Deplacement();

            }
        }
        // mauvaise selection, reinitialisation de from et to
        else {
            RetirerMarque();
            from = to = null;
        }
        if (joueurCourant.nbMove == 0 && joueurCourant.passeDispo == 0)
            FinTour();
        metAJour();

    }


    public void SelectionPieceIA(int l, int c) {
        Piece select = tr._terrain[l][c];
        if (from == null ) {
            from = select;
            if ((!select.HasBall))
                SelectionDeplacement(l, c);
            else {
                SelectionPasse(from);
            }
        } else if (from != null) {
            to = select;
            if ((from.HasBall)) {
                if (from != to)
                    Passe();
                else {
                    RetirerMarque();
                    from = to = null;
                }
            }
            else {
                Deplacement();
            }
        }
        // mauvaise selection, reinitialisation de from et to
        else {
            RetirerMarque();
            from = to = null;
        }
        if (joueurCourant.nbMove == 0 && joueurCourant.passeDispo == 0)
            FinTour();
        metAJour();

    }

    public void SelectionDeplacement(int l, int c) {
        tr._terrain[l][c].SelectionDeplacement = true;
        // getPiecePos(select).SelectionDeplacement = true;
        Piece select = tr._terrain[l][c];
        System.out.printf("Selection déplacement : Piece position (%d,%d)\n", select.Position.l, select.Position.c);
        listeMarque.add(new Position(l, c));
        listePositionsPossibles = from.PossiblePositions(joueurCourant.nbMove);
        for (Position pos : listePositionsPossibles) {
            int li = pos.l;
            int co = pos.c;
            tr._terrain[li][co].PossibleDeplacement = true;
            listeMarque.add(pos);
        }

    }

    public void SelectionPasse(Piece select) {
        getPiecePos(select).SelectionPasse = true;
        listeMarque.add(select.Position);
        if (joueurCourant.passeDispo == 1) {
            ArrayList<Position> ar = from.passesPossibles();
            for (Position pos : ar) {
                int l = pos.l;
                int c = pos.c;
                tr._terrain[l][c].PossiblePasse = true;
                listeMarque.add(pos);
            }
        }
    }

    public void Passe() {
        if (joueurCourant.passeDispo == 1) {
            if (TerrainUtils.passeWrapper2(from, to) == true) {
                joueurCourant.passeDispo = 0;
                gameOver = victoire() != PieceType.Empty;
            }
        }
        RetirerMarque();
        from = to = null;
    }

    public void Deplacement() {
        if (listePositionsPossibles.contains(to.Position)) {
            ArrayList<Position> diag = from.getDiagonals();
            int temp = joueurCourant.nbMove;
            if (diag.contains(to.Position)) {
                // Si c'est un mouvement en diagonale, on prend deux coups
                joueurCourant.nbMove -= 2;
            } else {
                // Sinon 1 seul ou 2 selon si on a avancé d'une ou de deux cases
                joueurCourant.nbMove -= Math.abs((from.Position.l + from.Position.c) - (to.Position.l + to.Position.c));
            }
            if (joueurCourant.nbMove >= 0) {
                from.move(to.Position.l, to.Position.c);
                gameOver = antijeu();
            } else {

                joueurCourant.nbMove = temp;
            }
        }
        listePositionsPossibles.clear();
        RetirerMarque();
        from = to = null;
    }

    public void RetirerMarque() {

        Iterator itr = listeMarque.iterator();
        while (itr.hasNext()) {
            Position pos = (Position) itr.next();
            int l = pos.l;
            int c = pos.c;
            tr._terrain[l][c].PossiblePasse = false;
            tr._terrain[l][c].PossibleDeplacement = false;
            tr._terrain[l][c].SelectionPasse = false;
            tr._terrain[l][c].SelectionDeplacement = false;
            itr.remove();
        }
    }

    private Piece getPiecePos(Piece piece) {
        return tr._terrain[piece.Position.l][piece.Position.c];
    }

    // retourne vrai si il y a antijeu
    public boolean antijeu() {
        boolean antijeu = true;
        for (int i = 1; i < tr.taille() - 1; i++) {
            antijeu = true;
            for (int j = 0; j < tr.taille(); j++) {
                antijeu = antijeu && (tr.getTerrain()[i][j].Type == PieceType.White);
            }
        }
        if (antijeu) {
            System.out.println("Les blancs ont fait un antijeu");
            return true;
        }
        antijeu = true;
        for (int i = 1; i < tr.taille() - 1; i++) {
            antijeu = true;
            for (int j = 0; j < tr.taille(); j++) {
                antijeu = antijeu && (tr.getTerrain()[i][j].Type == PieceType.Black);
            }
        }
        if (antijeu) {
            System.out.println("Les noirs ont fait un antijeu");
            return true;
        }
        return false;
    }

    // retourne le type de piece qui a gagnée
    public PieceType victoire() {
        for (int i = 0; i < tr.taille(); i++) {
            if ((tr.getTerrain()[0][i].Type == PieceType.White) && (tr.getTerrain()[0][i].HasBall)) {
                System.out.println("Les blancs ont gagné !");
                return PieceType.White;
            }
            if ((tr.getTerrain()[tr.taille() - 1][i].Type == PieceType.Black)
                    && (tr.getTerrain()[tr.taille() - 1][i].HasBall)) {
                System.out.println("Les noirs ont gagné !");
                return PieceType.Black;
            }
        }
        return PieceType.Empty;
    }

    // mode textuelle

    public Piece getPiece(PieceType t) {
        Scanner sc = new Scanner(System.in);
        String ligne;
        String[] ligne_split;
        int pieceL;
        int pieceC;
        Piece res = tr.getTerrain()[0][0];
        boolean done = false;
        while (!done) {
            System.out.println("Entrez les coordonnées de la pièce " + t + " : l c ");
            ligne = sc.nextLine();
            ligne_split = ligne.split(" ");
            pieceL = Integer.parseInt(ligne_split[0]);
            pieceC = Integer.parseInt(ligne_split[1]);
            if ((pieceL > tr.taille()) || (pieceC > tr.taille())) {
                sc.close();
                throw new IllegalAccessError("Erreur dans getPiece : l ou c > taille");
            }
            res = tr.getTerrain()[pieceL][pieceC];
            if ((res.Type == t) && (!res.HasBall)) {
                done = true;
            } else {
                if (res.HasBall) {
                    System.out.println("Erreur : Vous ne pouvez pas bouger la piece qui a la balle");
                }
                if (res.Type != t) {
                    System.out.println("Erreur : Veuillez choisir une piece de type " + t);
                }
            }
        }

        // Je sais pas pourquoi, j'ai pas envie de savoir pourquoi, je ne veux même plus
        // chercher à savoir pourquoi,
        // mais pour une raison que j'ignore, si on ferme le scanner ici alors plus rien
        // ne marche
        // Du coup, juste laissez-le ouvert et osef du warning
        // sc.close();
        return res;
    }

    // fin de tour
    public void endTurn() {
        joueurCourant.nbMove = 2;
        joueurCourant.passeDispo = 1;
        if (joueurCourant.n == TypeJoueur.Joueur1) {
            joueurCourant = joueur2;
        } else {
            joueurCourant = joueur1; // a modifier pour joueur contre l'ia
        }
        move();
    }

    // Ici encore on utilise l'entrée standard. A modifier plus tard pour l'ihm et
    // l'ia
    public void move() {
        // victoire
        if (gameOver) {
            System.exit(0); // la j'ai mis system.exit mais on changera si necessaire
        }
        Scanner sc = new Scanner(System.in);
        char choix;
        Piece from;
        Piece to;
        // tant qu'il y a un truc à faire
        while (!gameOver) {
            if ((joueurCourant.nbMove == 0 && joueurCourant.passeDispo == 0)) {
                // end turn
                endTurn();
            }
            tr.PrintTerrain();
            System.out.println("tour des " + joueurCourant.couleur);
            System.out.println("Nombre de mouvements restants : " + joueurCourant.nbMove);
            System.out.println("Nombre de passes restantes : " + joueurCourant.passeDispo);
            System.out.println(
                    "entrez 'p' pour faire une passe, 'm' pour faire un mouvement, ou 'q' pour passer votre tour");
            choix = sc.nextLine().charAt(0);
            switch (choix) {
                case 'p':
                    // passe
                    if (joueurCourant.passeDispo == 1) {
                        from = tr.getPieceWithBall(joueurCourant.couleur);
                        System.out.println("Les passes possibles sont : " + from.passesPossibles());
                        to = getPiece(joueurCourant.couleur);
                        TerrainUtils.passeWrapper(from, to);
                        joueurCourant.passeDispo = 0;
                        gameOver = victoire() != PieceType.Empty;
                    } else {
                        System.out.println("Vous ne pourrez faire qu'une seule passe");
                    }
                    break;
                case 'm':
                    // mouvement
                    from = getPiece(joueurCourant.couleur);
                    ArrayList<Position> ar = from.PossiblePositions(joueurCourant.nbMove);
                    System.out.println("Positions possibles : " + ar);
                    to = getPiece(PieceType.Empty);
                    // On verifie si c est bien un mouvement legal

                    if (ar.contains(to.Position)) {
                        ArrayList<Position> diag = from.getDiagonals();
                        int temp = joueurCourant.nbMove;
                        if (diag.contains(to.Position)) {
                            // Si c'est un mouvement en diagonale, on prend deux coups
                            joueurCourant.nbMove -= 2;
                        } else {
                            // Sinon 1 seul ou 2 selon si on a avancé d'une ou de deux cases
                            joueurCourant.nbMove -= Math
                                    .abs((from.Position.l + from.Position.c) - (to.Position.l + to.Position.c));
                        }
                        if (joueurCourant.nbMove >= 0) {
                            from.move(to.Position.l, to.Position.c);
                        } else {
                            System.out.println("Vous n'avez plus de mouvements");
                            joueurCourant.nbMove = temp;
                        }
                    } else {
                        throw new IllegalAccessError("Mouvement illegal");
                    }
                    gameOver = antijeu();
                    break;
                case 'q':
                    // end turn
                    endTurn();
                    break;
                default:
                    System.out.println("Choix invalide");
                    break;
            }
        }
    }

    private void test_Random_IA_IA(Terrain tr) {
        Random_IA A = new  Random_IA(PieceType.Black, tr);
        Random_IA B = new  Random_IA(PieceType.White, tr);
        PieceType tour = PieceType.White;
        Boolean victoire = false;
        while (!victoire) {
            tr.PrintTerrain();
            System.out.println(" Tour = " + tour);
            if (tour == PieceType.White) {
                B.IA();
                victoire = B.Victoire_IA;
                tour = PieceType.Black;
            } else {
                A.IA();
                victoire = A.Victoire_IA;
                tour = PieceType.White;
            }
        }
        if (A.Victoire_IA) {
            System.out.println("Victoire IA A");
        } else {
            System.out.println("Victoire IA B");
        }
    }

    public ArrayList<Position> winningMove(Piece p){
        ArrayList<Position> list = p.passesPossibles();
        ArrayList<Position> res = new ArrayList<Position>();
        if(list.size()==0){return null;}
        else{
            for(int i=0;i<list.size();i++){
                if(list.get(i).l == 0 && p.Type == PieceType.White){
                    res.add(list.get(i));
                }
                if(list.get(i).l == 6 && p.Type == PieceType.Black){
                    res.add(list.get(i));
                }
            }
            return res;
        }
    }
    public static void main(String[] args) {
        Jeu j = new Jeu();
        j.test_Random_IA_IA(j.tr);
        // j.move();
    }

}