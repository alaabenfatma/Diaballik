package Diaballik.IA;

import java.util.ArrayList;
import java.util.Random;

import Diaballik.Controllers.TerrainUtils;
import Diaballik.Models.Piece;
import Diaballik.Models.PieceType;
import Diaballik.Models.Position;
import Diaballik.Models.Terrain;
import Diaballik.Models.Jeu;

public class Random_IA {
    PieceType Couleur_IA;
    Random R;
    Terrain tr;
    public Boolean Victoire_IA;

    
    public Random_IA(PieceType p, Terrain t){
        Couleur_IA = p;
        R = new Random();
        Victoire_IA =false;
        tr = t;
    }
    private ArrayList<Piece> find_piece(){
        ArrayList<Piece> list = new ArrayList<Piece>();
        for(int l=0;l<tr.taille();l++){
            for(int c=0;c<tr.taille();c++){
                if(tr.getTerrain()[l][c].Type == Couleur_IA && !tr.getTerrain()[l][c].HasBall){
                    list.add(tr.getTerrain()[l][c]);
                }
            }
        }
        return list;
    }
    public void IA(){
        int nbMove = 2; // on a droit à deux mouvements max
        int passe_faite = 1; // une passe
        int binf = 0; // Permet de refermer l'interval pour rendre un choix inaccessible
        int i;
        Piece from,to;
        Position pos;
        ArrayList<Piece> list_piece;
        ArrayList<Position> diag;
        int nb_tour = 0;
        System.out.println("L'IA est en train de jouer ");
        while((nbMove > 0 || passe_faite > 0) && nb_tour < 10){
            int choix = R.nextInt(2)+binf;
            System.out.println("nbMove : "+nbMove+" passe_faite : "+passe_faite + " Choix : "+choix);
            switch (choix) {
                case 0: // Passe
                    if(passe_faite == 0){break;}
                    from = tr.getPieceWithBall(Couleur_IA);
                    if(from.passesPossibles().size() > 0){
                        if(from.passesPossibles().size() == 1){i =0;}
                        else{i = R.nextInt(from.passesPossibles().size()-1);}
                        pos = from.passesPossibles().get(i);
                        to = tr.getTerrain()[pos.l][pos.c];
                        TerrainUtils.passeWrapper(from, to);
                        if (Couleur_IA == PieceType.White && to.Position.l == 0) {
                            System.out.println("Victoire IA !");
                            Victoire_IA = true;
                        }
                        else if (Couleur_IA == PieceType.Black && to.Position.l == tr.taille()-1) {
                            System.out.println("Victoire IA !");
                            Victoire_IA = true;
                        }
                        passe_faite--;
                        binf++;
                    }
                    break;
                case 1: // Mouvement
                    boolean ok = false;
                    list_piece = find_piece();
                    from = list_piece.get(R.nextInt(list_piece.size()));
                    diag = from.getDiagonals();
                    while(!ok && nbMove > 0){
                        if(from.PossiblePositions(nbMove).size() == 0){break;}
                        if(from.PossiblePositions(nbMove).size() == 1){i=0;}
                        else{i = R.nextInt(from.PossiblePositions(nbMove).size()-1);}
                        pos = from.PossiblePositions(nbMove).get(i);
                        to = tr.getTerrain()[pos.l][pos.c];
                        if(diag.contains(pos) && nbMove==2){
                            nbMove-=2;
                        }
                        else{
                            nbMove--;
                        }
                        if (nbMove >= 0) {
                            from.move(pos.l, pos.c);
                            ok = true;
                        }
                    }
                    break;
                case 2: // fin du tour
                    nbMove = 0;
                    passe_faite = 0;
                    break;
                default:
                    System.out.println("Erreur IA");
                    break;
            }
            nb_tour++;
        }
    }
}