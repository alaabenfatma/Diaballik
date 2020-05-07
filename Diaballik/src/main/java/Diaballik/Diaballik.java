package Diaballik;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Scanner;
import Diaballik.Vue.*;

import Diaballik.Controllers.TerrainUtils;
import Diaballik.IA.IA;
//import Diaballik.Controllers.TerrainUtils;
import Diaballik.Models.*;

/**
 * Hello world!
 *
 */
public class Diaballik {

    // A changer ensuite pour l'adapter à l'ia et l'ihm
    // Ici pour l'instant, la position est saisie au clavier sous la forme "l c"
    // sans les guillemets
    // comme pour la gauffre

    public static Piece getPiece(Terrain tr, PieceType t) {
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


    private static void test_Random_IA_P(Terrain tr) {
        IA A = new IA(PieceType.Black, tr);
        PieceType tour = PieceType.White;
        Boolean victoire = false;
        int nbMove = 2; // on a droit à deux mouvements max
        int passe_faite = 1; // une passe
        Scanner sc = new Scanner(System.in);
        char choix;
        Piece from;
        Piece to;
        while (!victoire) {
            System.out.println(" Tour = " + tour);
            if (tour == PieceType.White) {
                // tant qu'il y a un truc à faire
                while (nbMove > 0 || passe_faite > 0) {
                    tr.PrintTerrain();
                    System.out.println("tour des " + tour);
                    System.out.println("Nombre de mouvements restants : " + nbMove);
                    System.out.println("Nombre de passes restantes : " + passe_faite);
                    System.out.println(
                            "entrez 'p' pour faire une passe, 'm' pour faire un mouvement, ou 'q' pour passer votre tour");

                    String s = sc.nextLine();

                    if (s.length() == 0) {
                        System.out.println("Choix invalide");
                        continue;
                    }
                    choix = s.charAt(0);
                    switch (choix) {
                        case 'p':
                            // passe
                            if (passe_faite == 1) {
                                from = tr.getPieceWithBall(tour);
                                System.out.println("Les passes possibles sont : " + from.passesPossibles());
                                to = getPiece(tr, tour);
                                TerrainUtils.passeWrapper(from, to);
                                passe_faite = 0;
                                // victoire?
                                if (to.Position.l == tr.taille() - 1) {
                                    System.out.println("Les noirs ont gagnés !");
                                    victoire = true;
                                    // System.exit(0); // La j'ai fais un system.exit mais on changera plus tard si
                                    // il le faut
                                }
                            } else {
                                System.out.println("Vous ne pourrez faire qu'une seule passe");
                            }
                            break;
                        case 'm':
                            // mouvement
                            System.out.println("nb moves : " + nbMove);
                            if (nbMove == 0) {
                                System.out.println("Vous n'avez plus de mouvements");
                                nbMove = 0;
                                continue;
                            }
                            from = getPiece(tr, tour);
                            ArrayList<Position> ar = from.PossiblePositions(nbMove);
                            System.out.println("Positions possibles : " + ar);
                            to = getPiece(tr, PieceType.Empty);
                            // On verifie si c est bien un mouvement legal
                            if (ar.contains(to.Position)) {
                                ArrayList<Position> diag = from.getDiagonals();
                                if (diag.contains(to.Position)) {
                                    // Si c'est un mouvement en diagonale, on prend deux coups
                                    nbMove -= 2;
                                } else {
                                    // Sinon 1 seul ou deux selon si on a avancé d'une ou deux cases
                                    nbMove -= Math
                                            .abs((from.Position.l + from.Position.c) - (to.Position.l + to.Position.c));
                                }
                                if (nbMove >= 0) {
                                    from.move(to.Position.l, to.Position.c);
                                } else {
                                    System.out.println("Vous n'avez plus de mouvements");
                                    nbMove = 0;
                                }
                            } else {
                                throw new IllegalAccessError("Mouvement illegal");
                            }
                            break;

                        case 'q':
                            // end turn
                            nbMove = 0;
                            passe_faite = 0;
                            break;
                        default:
                            System.out.println("Choix invalide");
                            break;
                    }
                }
                tour = PieceType.Black;
            } else {
                A.Random_IA();
                victoire = A.Victoire_IA;
                tour = PieceType.White;
                nbMove = 2;
                passe_faite = 1;
            }
        }
        sc.close();
    }

    private static void test_Random_IA_IA(Terrain tr) {
        IA A = new IA(PieceType.Black, tr);
        IA B = new IA(PieceType.White, tr);
        PieceType tour = PieceType.White;
        Boolean victoire = false;
        while (!victoire) {
            tr.PrintTerrain();
            System.out.println(" Tour = " + tour);
            if (tour == PieceType.White) {
                B.Random_IA();
                victoire = B.Victoire_IA;
                tour = PieceType.Black;
            } else {
                A.Random_IA();
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

    public static void main(String[] args) {

        Terrain tr = new Terrain();
        tr.Create();
        //test_Random_IA_P(tr);
        //test_Random_IA_IA(tr);
        //white_to_move(tr);

        ihm i = new ihm();
    }
}