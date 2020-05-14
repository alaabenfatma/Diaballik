package Diaballik.Models;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonCreator;

public class JeuJSON {
    public String player1;
    public String player2;
    
    public char[][] Terrain = new char[7][7];
    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    Date date = new Date();
    public String CreationDate = formatter.format(date);
    public ConfigJeu config;
    public Joueur joueurCourant;
    public JeuJSON(Jeu j) {
        player1 = j.joueur1.name;
        player2 = j.joueur2.name;
        Terrain = j.tr.toChar();
        config = j.config;
        joueurCourant = j.joueurCourant;
    }
    @JsonCreator
    public JeuJSON() {
        
    }
}