package Diaballik;

import java.util.Scanner;
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
                throw new IllegalAccessError("Erreur dans getWhitePiece : l ou c > taille");
            }
            Piece res = tr.getTerrain()[pieceL][pieceC];
            if (res.Type == PieceType.Black) {
                sc.close();
                return res;
            }
        }
    }

    //Ici encore on utilise l'entrée standard. A modifier plus tard pour l'ihm et l'ia
    public static void white_to_move(Terrain tr) {
        int nbMove = 2; //on a droit à deux mouvements max
        int passe_faite = 1; // une passe
        Scanner sc = new Scanner(System.in);
        char choix;
        //tant qu'il y a un truc à faire
        while(nbMove>0 || passe_faite>0){
            System.out.println("tour des blancs");
            System.out.println("");
            System.out.println("entrez 'p' pour faire une passe, 'm' pour faire un mouvement, ou 'q' pour passer votre tour");
            choix = sc.nextLine().charAt(0);
            switch(choix){
                case 'p':
                //passe
                break;
                case 'm':
                //mouvement
                break;
                case 'q':
                //end turn
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
        tr.PrintTerrain();
        tr.getTerrain()[6][2].move(1,0);
        tr.getTerrain()[0][2].move(3,3);
        tr.getTerrain()[0][1].move(2,0);
        tr.getTerrain()[6][4].move(2,2);
        tr.getTerrain()[0][5].move(3,4);
        tr.getTerrain()[0][6].move(4,2);
        tr.PrintTerrain();
        System.out.println(tr.getTerrain()[3][3].passesPossibles());
        
    }
}
