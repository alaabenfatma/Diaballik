package Diaballik;

import java.util.ArrayList;
import java.util.Scanner;

import Diaballik.Controllers.TerrainUtils;
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
    public static Piece getWhitePiece(Terrain tr) {
        Scanner sc = new Scanner(System.in);
        String ligne;
        String[] ligne_split;
        int pieceL;
        int pieceC;
        //On boucle si l'utilisateur choisie une piece qui n'est pas blanche
        while (true) {
            System.out.println("Entrez les coordonnées de la pièce : l c ");
            ligne = sc.nextLine();
            ligne_split = ligne.split(" ");
            pieceL = Integer.parseInt(ligne_split[0]);
            pieceC = Integer.parseInt(ligne_split[1]);
            if ((pieceL > tr.taille()) || (pieceC > tr.taille())) {
                sc.close();
                throw new IllegalAccessError("Erreur dans getWhitePiece : l ou c > taille");
            }
            Piece res = tr.getTerrain()[pieceL][pieceC];
            if (res.Type == PieceType.White) {
                sc.close();
                return res;
            }
        }
    }

    //mm chose
    public static Piece getBlackPiece(Terrain tr) {
        Scanner sc = new Scanner(System.in);
        String ligne;
        String[] ligne_split;
        int pieceL;
        int pieceC;
        while (true) {
            System.out.println("Entrez les coordonnées de la pièce : l c ");
            ligne = sc.nextLine();
            ligne_split = ligne.split(" ");
            pieceL = Integer.parseInt(ligne_split[0]);
            pieceC = Integer.parseInt(ligne_split[1]);
            if ((pieceL > tr.taille()) || (pieceC > tr.taille())) {
                sc.close();
                throw new IllegalAccessError("Erreur dans getBlackPiece : l ou c > taille");
            }
            Piece res = tr.getTerrain()[pieceL][pieceC];
            if (res.Type == PieceType.Black) {
                sc.close();
                return res;
            }
        }
    }

    //mm chose
    public static Piece getEmptyPiece(Terrain tr) {
        Scanner sc = new Scanner(System.in);
        String ligne;
        String[] ligne_split;
        int pieceL;
        int pieceC;
        while (true) {
            System.out.println("Entrez les coordonnées de la pièce : l c ");
            ligne = sc.nextLine();
            ligne_split = ligne.split(" ");
            pieceL = Integer.parseInt(ligne_split[0]);
            pieceC = Integer.parseInt(ligne_split[1]);
            if ((pieceL > tr.taille()) || (pieceC > tr.taille())) {
                sc.close();
                throw new IllegalAccessError("Erreur dans getEmptyPiece : l ou c > taille");
            }
            Piece res = tr.getTerrain()[pieceL][pieceC];
            if (res.Type == PieceType.Empty) {
                sc.close();
                return res;
            }
        }
    }

    public static Piece getPiece(Terrain tr, PieceType t) {
        Scanner sc = new Scanner(System.in);
        String ligne;
        String[] ligne_split;
        int pieceL;
        int pieceC;
        Piece res = tr.getTerrain()[0][0];
        boolean done = false;
        while (!done) {
            System.out.println("Entrez les coordonnées de la pièce "+t+" : l c ");
            ligne = sc.nextLine();
            ligne_split = ligne.split(" ");
            pieceL = Integer.parseInt(ligne_split[0]);
            pieceC = Integer.parseInt(ligne_split[1]);
            if ((pieceL > tr.taille()) || (pieceC > tr.taille())) {
                sc.close();
                throw new IllegalAccessError("Erreur dans getPiece : l ou c > taille");
            }
            res = tr.getTerrain()[pieceL][pieceC];
            if (res.Type == t) {
                done = true;
            }
        }
        //sc.close();
        return res;
    }

    //Ici encore on utilise l'entrée standard. A modifier plus tard pour l'ihm et l'ia
    public static void white_to_move(Terrain tr) {
        int nbMove = 2; //on a droit à deux mouvements max
        int passe_faite = 1; // une passe
        Scanner sc = new Scanner(System.in);
        char choix;
        Piece from;
        Piece to;
        //tant qu'il y a un truc à faire
        while(nbMove>0 || passe_faite>0){
            tr.PrintTerrain();
            System.out.println("tour des blancs");
            System.out.println("Nombre de mouvements restants : "+nbMove);
            System.out.println("Nombre de passes restantes : "+passe_faite);
            System.out.println("entrez 'p' pour faire une passe, 'm' pour faire un mouvement, ou 'q' pour passer votre tour");
            choix = sc.nextLine().charAt(0);
            switch(choix){
                case 'p':
                //passe
                from = getPiece(tr,PieceType.White);
                to = getPiece(tr,PieceType.White);
                TerrainUtils.passeWrapper(from, to);
                passe_faite=0;
                break;
                case 'm':
                //mouvement
                from = getPiece(tr,PieceType.White);
                ArrayList<Position> ar = from.PossiblePositions();
                System.out.println("Positions possibles : "+ar);
                to = getPiece(tr,PieceType.Empty);
                System.out.println("Position selectionnée : "+to.Position);
                //On verifie si c est bien un mouvement legal
                
                if(ar.contains(to.Position)){
                    ArrayList<Position> diag = from.getDiagonals();
                    if(diag.contains(to.Position)){
                        //Si c'est un mouvement en diagonale, on prend deux coups
                        nbMove-=2;  
                    }else{
                        //Sinon 1 seul
                        nbMove-=1;
                    }
                    from.move(to.Position.l, to.Position.c);
                }else{
                    throw new IllegalAccessError("Mouvement illegal");
                }
                break;
                case 'q':
                //end turn
                nbMove = 0;
                passe_faite = 0;
                black_to_move(tr);
                //TODO : Condition de victoire
                break;
                default:
                System.out.println("Choix invalide");
                break;
            }
        }
        sc.close();
    }

    public static void black_to_move(Terrain tr) {
        int nbMove = 2; //on a droit à deux mouvements max
        int passe_faite = 1; // une passe
        Scanner sc = new Scanner(System.in);
        char choix;
        Piece from;
        Piece to;
        //tant qu'il y a un truc à faire
        while(nbMove>0 || passe_faite>0){
            tr.PrintTerrain();
            System.out.println("tour des noirs");
            System.out.println("Nombre de mouvements restants : "+nbMove);
            System.out.println("Nombre de passes restantes : "+passe_faite);
            System.out.println("entrez 'p' pour faire une passe, 'm' pour faire un mouvement, ou 'q' pour passer votre tour");
            choix = sc.nextLine().charAt(0);
            switch(choix){
                case 'p':
                //passe
                from = getPiece(tr,PieceType.Black);
                to = getPiece(tr,PieceType.Black);
                TerrainUtils.passeWrapper(from, to);
                passe_faite=0;
                break;
                case 'm':
                //mouvement
                from = getPiece(tr,PieceType.Black);
                to = getPiece(tr,PieceType.Empty);
                ArrayList<Position> ar = from.PossiblePositions();
                //On verifie si c est bien un mouvement legal
                if(ar.contains(to.Position)){
                    ArrayList<Position> diag = from.getDiagonals();
                    if(diag.contains(to.Position)){
                        //Si c'est un mouvement en diagonale, on prend deux coups
                        nbMove-=2;  
                    }else{
                        //Sinon 1 seul
                        nbMove-=1;
                    }
                    from.move(to.Position.l, to.Position.c);
                }else{
                    throw new IllegalAccessError("Mouvement illegal");
                }
                break;
                case 'q':
                //end turn
                nbMove = 0;
                passe_faite = 0;
                white_to_move(tr);
                //TODO : Condition de victoire
                break;
                default:
                System.out.println("Choix invalide");
                break;
            }
        }
        sc.close();
    }

    public static void main(String[] args) {
        Terrain tr = new Terrain();
        tr.Create();
        white_to_move(tr);
        
    }
}
