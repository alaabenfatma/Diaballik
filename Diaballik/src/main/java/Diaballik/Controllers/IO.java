package Diaballik.Controllers;


import Diaballik.Models.Jeu;
import Diaballik.Models.PieceType;

public class IO {
    public static void ExportGameToJSON(Jeu j){
        String player1 = j.joueur1.name;
        String player2 = j.joueur2.name;
        char[][] pieces = new char[7][7];
        for (int i = 0; i < 7; i++) {
            for (int k = 0; k < 7; k++) {
                if(j.tr._terrain[i][k].Type==PieceType.White){
                    if(j.tr._terrain[i][k].HasBall){
                        pieces[i][k] = '1';
                    }
                    else{
                        pieces[i][k] = 'W';
                    }
                }
                else if(j.tr._terrain[i][k].Type==PieceType.Black){
                    if(j.tr._terrain[i][k].HasBall){
                        pieces[i][k] = '0';
                    }
                    else{
                        pieces[i][k] = 'B';
                    }
                }
                else{
                    pieces[i][k] = ' ';
                }
            }
        }
        StringBuilder json = new StringBuilder();
        json.append("Game{");
        json.append("Player1='"+player1+'\'');
        json.append(",Player2='"+player2+'\'');
        json.append(",Terrain='"+pieces.toString()+'\'');

    }
}