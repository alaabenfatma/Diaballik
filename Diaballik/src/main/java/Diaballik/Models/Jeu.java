package Diaballik.Models;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Scanner;
import Diaballik.Vue.*;

import Diaballik.Controllers.TerrainUtils;
import Diaballik.IA.IA;
//import Diaballik.Controllers.TerrainUtils;
import Diaballik.Models.*;
import Diaballik.Patterns.Observable;

public class Jeu extends Observable {
    public Terrain tr;
    public Joueur joueur1;
    public Joueur joueur2;
    public Joueur joueurCourant;
    public boolean gameOver = false;

    public Jeu(){
        tr = new Terrain();
        tr.Create();
        //Joueur 1 (Là le joueur 1 joue forcement les blancs, mais on pourra modifier dans les 
        //parametres ci-dessous)
        joueur1 = new Joueur(TypeJoueur.Joueur1, PieceType.White, 2, 1);
        //Pareil ici, on pourra changer les parametres pour jouer contre l'IA par exemple..
        joueur2 = new Joueur(TypeJoueur.Joueur2, PieceType.Black, 2, 1);
        //joueur courant
        joueurCourant = joueur1;
        //white_to_move(tr);
    }

    public Jeu(Terrain terrain){
        tr = terrain;
        //white_to_move(tr);

    }

    //retourne vrai si il y a antijeu
    //TODO (ça marche pas)
    public boolean antijeu(){
        boolean antijeu = true;
        for(int i=1;i<tr.taille()-1;i++){
            for(int j=0;j<tr.taille();j++){
                antijeu = antijeu && (tr.getTerrain()[i][j].Type == PieceType.White);
            }
        }
        if(antijeu){
            System.out.println("Les blancs ont fait un antijeu");
            return true;
        }
        antijeu = true;
        for(int i=1;i<tr.taille()-1;i++){
            for(int j=0;j<tr.taille();j++){
                antijeu = antijeu && (tr.getTerrain()[i][j].Type == PieceType.Black);
            }
        }
        if(antijeu){
            System.out.println("Les noirs ont fait un antijeu");
            return true;
        }
        return false;
    }

    //retourne vrai si un joueur a gagné
    public boolean victoire(){
        for(int i=0;i<tr.taille();i++){
            if((tr.getTerrain()[0][i].Type == PieceType.White) && (tr.getTerrain()[0][i].HasBall)){
                System.out.println("Les blancs ont gagné !");
                return true;
            }
            if((tr.getTerrain()[tr.taille()-1][i].Type == PieceType.Black) && (tr.getTerrain()[tr.taille()-1][i].HasBall)){
                System.out.println("Les noirs ont gagné !");
                return true;
            }
        }
        return false;
    }

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

    // Ici encore on utilise l'entrée standard. A modifier plus tard pour l'ihm et
    // l'ia
    public void move() {
        //victoire
        if(gameOver){
            System.exit(0); //la j'ai mis system.exit mais on changera si necessaire
        }
        Scanner sc = new Scanner(System.in);
        char choix;
        Piece from;
        Piece to;
        // tant qu'il y a un truc à faire
        while (true) {
            tr.PrintTerrain();
            System.out.println("tour des "+joueurCourant.couleur);
            System.out.println("Nombre de mouvements restants : " + joueurCourant.nbMove);
            System.out.println("Nombre de passes restantes : " + joueurCourant.passe_faite);
            System.out.println(
                    "entrez 'p' pour faire une passe, 'm' pour faire un mouvement, ou 'q' pour passer votre tour");
            choix = sc.nextLine().charAt(0);
            switch (choix) {
                case 'p':
                    // passe
                    if (joueurCourant.passe_faite == 1) {
                        from = tr.getPieceWithBall(joueurCourant.couleur);
                        System.out.println("Les passes possibles sont : " + from.passesPossibles());
                        to = getPiece(joueurCourant.couleur);
                        TerrainUtils.passeWrapper(from, to);
                        joueurCourant.passe_faite = 0;
                        gameOver = victoire();
                    } else {
                        System.out.println("Vous ne pourrez faire qu'une seule passe");
                    }
                    break;
                case 'm':
                    // mouvement
                    from = getPiece(joueurCourant.couleur);
                    ArrayList<Position> ar = from.PossiblePositions();
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
                            joueurCourant.nbMove -= Math.abs((from.Position.l + from.Position.c)-(to.Position.l + to.Position.c));
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
                    joueurCourant.nbMove = 2;
                    joueurCourant.passe_faite = 1;
                    if(joueurCourant.n == TypeJoueur.Joueur1){
                        joueurCourant = joueur2;
                    }else{
                        joueurCourant = joueur1; // a modifier pour joueur contre l'ia
                    }
                    move();
                    break;
                default:
                    System.out.println("Choix invalide");
                    break;
            }
        }
    }

    public static void main(String[] args){
        Jeu j = new Jeu();
        j.move();
    }
    
}