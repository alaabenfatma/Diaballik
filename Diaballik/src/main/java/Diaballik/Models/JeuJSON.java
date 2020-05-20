package Diaballik.Models;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonCreator;

public class JeuJSON {
    /**
     * The name of the first player
     */
    public String player1;
    /**
     * The name of the second player
     */
    public String player2;
    /**
     * A textual presentation of the board
     */
    public char[][] Terrain = new char[7][7];
    
    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    Date date = new Date();
    /**
     * Current date of the 'save' plays the role of a primary care.
     */
    public String CreationDate = formatter.format(date);
    public ConfigJeu config;
    public Joueur joueurCourant;
    /**
     * This plays as a container for the game.
     * @param j The game
     */
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