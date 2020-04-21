package Diaballik.Models;

import java.util.*;

public interface IPiece {
    /**
     * Donne le ballon à ce joueur.
     */
    public void addBall();

    /**
     * retire la balle à ce joueur.
     */
    public void removeBall();

    /**
     * Déplacer cette pièce dans une autre position
     * 
     * @param l L'indice de ligne
     * @param c L'index de la colonne
     * @author Alaa
     */
    public void move(int l, int c);

    public String toString();

    /**
     * Nous ne pouvons pas échanger de variable en Java. Nous devons créer une
     * nouvelle instance.
     * 
     * @return Un clone de cette pièce
     * @author Alaa
     */
    public Piece Clone();

    /**
     * All the possible positions around this piece
     * 
     * @return Toutes les positions possibles autour de cette pièce
     * @author Alaa
     */
    public ArrayList<Position> PossiblePositions();
}