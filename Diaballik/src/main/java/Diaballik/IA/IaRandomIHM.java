package Diaballik.IA;

import Diaballik.Models.Jeu;
import java.util.ArrayList;
import java.util.Random;
import Diaballik.Models.Piece;
import Diaballik.Models.Position;
import Diaballik.Models.Terrain;

public class IaRandomIHM {
    Jeu j;
    Terrain tr;
    Random rand;

    public IaRandomIHM(Jeu jeu) {
        j = jeu;
        tr = j.tr;
        rand = new Random();

    }

    private ArrayList<Piece> find_piece() {
        ArrayList<Piece> list = new ArrayList<Piece>();
        for (int l = 0; l < tr.taille(); l++) {
            for (int c = 0; c < tr.taille(); c++) {
                if (tr.getTerrain()[l][c].Type == j.joueur2.couleur && !tr.getTerrain()[l][c].HasBall) {
                    list.add(tr.getTerrain()[l][c]);
                }
            }
        }
        return list;
    }

    public void JoueTourIARand() {
        System.out.println("here");
        Piece from, to;
        Position pos;
        ArrayList<Piece> list_piece;
        int i;

        int choix = rand.nextInt(3);
        switch (choix) {
            case 0: // 1 passe
                from = tr.getPieceWithBall(j.joueur2.couleur);
                if (from.passesPossibles().size() > 0) {
                    if (from.passesPossibles().size() == 1) {
                        i = 0;
                    } else {
                        i = rand.nextInt(from.passesPossibles().size() - 1);
                    }
                    pos = from.passesPossibles().get(i);
                    to = tr.getTerrain()[pos.l][pos.c];
                    j.SelectionPieceIA(from);
                    j.SelectionPieceIA(to);

                }
                break;
            case 1: // deplacement
                list_piece = find_piece();
                from = list_piece.get(rand.nextInt(list_piece.size()));
                if (from.PossiblePositions(j.joueurCourant.nbMove).size() == 0) {
                    break;
                }
                if (from.PossiblePositions(j.joueurCourant.nbMove).size() == 1) {
                    i = 0;
                } else {
                    i = rand.nextInt(from.PossiblePositions(j.joueurCourant.nbMove).size() - 1);
                }
                pos = from.PossiblePositions(j.joueurCourant.nbMove).get(i);
                to = tr.getTerrain()[pos.l][pos.c];
                j.SelectionPieceIA(from);
                j.SelectionPieceIA(to);
                break;

            case 2: // deplacement + passe
                // move
                list_piece = find_piece();
                from = list_piece.get(rand.nextInt(list_piece.size()));
                if (from.PossiblePositions(j.joueurCourant.nbMove).size() == 0) {
                    break;
                }
                if (from.PossiblePositions(j.joueurCourant.nbMove).size() == 1) {
                    i = 0;
                } else {
                    i = rand.nextInt(from.PossiblePositions(j.joueurCourant.nbMove).size() - 1);
                }
                pos = from.PossiblePositions(j.joueurCourant.nbMove).get(i);
                to = tr.getTerrain()[pos.l][pos.c];
                j.SelectionPieceIA(from);
                j.SelectionPieceIA(to);

                // passe
                from = tr.getPieceWithBall(j.joueur2.couleur);
                if (from.passesPossibles().size() > 0) {
                    if (from.passesPossibles().size() == 1) {
                        i = 0;
                    } else {
                        i = rand.nextInt(from.passesPossibles().size() - 1);
                    }
                    pos = from.passesPossibles().get(i);
                    to = tr.getTerrain()[pos.l][pos.c];
                    j.SelectionPieceIA(from);
                    j.SelectionPieceIA(to);
                }
                break;
            default:
                System.out.println("Erreur IA");
                break;

        }
        j.FinTour();

    }

}