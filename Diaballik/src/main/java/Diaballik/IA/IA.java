package Diaballik.IA;

import java.util.ArrayList;
import java.util.Random;

import Diaballik.Controllers.TerrainUtils;
import Diaballik.Models.Piece;
import Diaballik.Models.PieceType;
import Diaballik.Models.Position;
import Diaballik.Models.Terrain;

public class IA {
    PieceType Couleur_IA;
    Random R;
    Terrain tr;
    
    public IA(PieceType p){
        Couleur_IA = p;
        R = new Random();
        
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
    public void Random_IA(){
        int nbMove = 2; // on a droit à deux mouvements max
        int passe_faite = 1; // une passe
        int binf = 0; // Permet de refermer l'interval pour rendre un choix inaccessible
        int i;
        Piece from,to;
        Position pos;
        ArrayList<Piece> list_piece;
        ArrayList<Position> diag;
        while(nbMove > 0 || passe_faite > 0){
            int choix = R.nextInt(3)+binf;
            switch (choix) {
                case 0: // Passe
                    from = tr.getPieceWithBall(Couleur_IA);
                    i = R.nextInt(from.passesPossibles().size());
                    pos = from.passesPossibles().get(i);
                    to = tr.getTerrain()[pos.l][pos.c];
                    TerrainUtils.passeWrapper(from, to);
                    if (Couleur_IA == PieceType.White && to.Position.l == 0) {
                        System.out.println("Victoire IA !");
                        System.exit(0);
                    }
                    else if (Couleur_IA == PieceType.Black && to.Position.l == tr.taille()-1) {
                        System.out.println("Victoire IA !");
                        System.exit(0);
                    }
                    passe_faite--;
                    binf++;
                    break;
                case 1: // Mouvement
                    boolean ok = false;
                    list_piece = find_piece();
                    from = list_piece.get(R.nextInt(list_piece.size()));
                    diag = from.getDiagonals();
                    while(!ok){
                        pos = from.PossiblePositions().get(R.nextInt(from.PossiblePositions().size()));
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
        }
    }
}