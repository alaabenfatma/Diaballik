package Diaballik.IA;

import java.util.ArrayList;
import java.util.Random;

import Diaballik.Controllers.TerrainUtils;
import Diaballik.Models.Piece;
import Diaballik.Models.PieceType;
import Diaballik.Models.Position;
import Diaballik.Models.Terrain;

public class Coup_Gagnant {
    PieceType Couleur_IA;
    public Boolean Victoire_IA;
    Boolean Player_on_goal;
    Terrain tr;
    Random R;
    int nbMove;
    int passe_faite;
    Piece from,to;
    Position Goal;
    ArrayList<Position> res_cross = new ArrayList<Position>();
    
    public Coup_Gagnant(PieceType p, Terrain t){
        Couleur_IA = p;
        Victoire_IA =false;
        tr = t;
        R = new Random();
        nbMove = 2;
        passe_faite = 1;
        if(Couleur_IA == PieceType.Black){Goal = new Position(6,30);}
        else{Goal = new Position(0,30);}
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
    

    private void first_move(){
        int nb_tour = 0;
        int binf = 0;
        ArrayList<Piece> list_piece;
        ArrayList<Position> diag;
        Position pos;
        int i;
        while((nbMove > 0 || passe_faite > 0) && nb_tour < 10){
            int choix = R.nextInt(2) + binf;
            switch(choix){
            case 0: // Passe
                if(passe_faite == 0){break;}
                from = tr.getPieceWithBall(Couleur_IA);
                if(from.passesPossibles().size() > 0){
                    if(from.passesPossibles().size() == 1){i =0;}
                    else{i = R.nextInt(from.passesPossibles().size()-1);}
                    pos = from.passesPossibles().get(i);
                    to = tr.getTerrain()[pos.l][pos.c];
                    TerrainUtils.passeWrapper(from, to);
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
                break;
            default:
                System.out.println("Erreur IA");
                break;
            }
            nb_tour++;
        }
        nbMove = 2;
        passe_faite = 1;

    }
    
    private ArrayList<Position> find_goals(){
        int j;
        ArrayList<Position> list = new ArrayList<Position>();
        Player_on_goal = false;
        if(PieceType.Black == Couleur_IA){j = 6;}
        else{j = 0;}
        for(int i=0;i<tr.taille();i++){
            if(tr.getTerrain()[j][i].Type == PieceType.Empty){
                list.add(new Position(j,i));
            }
            else if(tr.getTerrain()[j][i].Type == Couleur_IA){
                list.add(new Position(j,i));
                Player_on_goal = true;
            }
        }
        if(list.size()==0){return null;}
        return list;
    }

    private void search_player_on_goal(){
        int j;
        Piece A = tr.getPieceWithBall(Couleur_IA);
        if(PieceType.Black == Couleur_IA){j = 6;}
        else{j = 0;}
        for(int i=0;i<tr.taille();i++){
            if(tr.getTerrain()[j][i].Type == Couleur_IA){
                Piece B = tr.getTerrain()[j][i];
                if(Math.abs(A.Position.c - B.Position.c) < Math.abs(A.Position.c - Goal.c)){
                    Goal.c = B.Position.c;
                }
            }
        }
    }

    private void cross_dir(){
        Piece A = tr.getTerrain()[Goal.l][Goal.c];
        Piece B = tr.getPieceWithBall(Couleur_IA);
        ArrayList<Position> list_A = A.direction();
        ArrayList<Position> list_B = B.direction();
        res_cross= new ArrayList<Position>();
        if(!list_A.containsAll(list_B)){res_cross = null;}
        else{
            for(int j=0;j<list_A.size();j++){
                for(int i=0;i<list_B.size();i++){
                    if(list_A.get(j).equals(list_B.get(i))){
                        res_cross.add(list_B.get(i));
                        break;
                    }
                }
            }
        }
    }
    
    private Boolean pass_win(){
        Piece A = tr.getTerrain()[Goal.l][Goal.c];
        Piece B = tr.getPieceWithBall(Couleur_IA);
        ArrayList<Position> list = A.direction();
        if(list.contains(B.Position)){
            return true;
        }
        return false;
    }
    
    public void IA(){
        ArrayList<Position> Goal_list;
        // Chercher si il y a des "buts" disponible
        Goal_list = find_goals();
        if(Goal_list==null){
            // on bouge aléatoirement
            first_move();
        }
        else{
            if(Player_on_goal){
                // Cherche le Joueur sur but le plus proche de la balle
                search_player_on_goal();
                if(pass_win()){
                    TerrainUtils.passeWrapper(tr.getPieceWithBall(Couleur_IA), tr.getTerrain()[Goal.l][Goal.c]);
                }
                else{
                    // Cherche si il existe un croissement entre les directions des deux joueurs
                    cross_dir();
                    if(res_cross == null ){
                        // je sais pas quoi faire
                    }
                    else{
                        
                    }
                }
            }
            else{
                Piece P_B = tr.getPieceWithBall(Couleur_IA);
                Position But = Goal_list.get(0);
                if(Goal_list.size()!=0){ // un seul but

                }
                else{ // plusieurs buts
                    ArrayList<Piece> list_piece = find_piece();
                    Piece plus_proche = list_piece.get(0);
                    // cherche joueur le plus proche du but
                    for(int i=1;i<list_piece.size();i++){
                        if(Math.abs(But.c - plus_proche.Position.c) < Math.abs(But.c - list_piece.get(i).Position.c)){
                            if(Math.abs(But.l - plus_proche.Position.l) < Math.abs(But.l - list_piece.get(i).Position.l)){
                                plus_proche = list_piece.get(i);
                            }
                        }
                    }
                    
                }
            }
        }
    
        // faire une fonction victoire ?

    }
}