package Diaballik.Models;
import Diaballik.IA.*;

public class State {
    public Jeu Game;
    public int score(){
        return Evaluator.scoreOfBoard(this.Game.tr);
    }
    public State(Jeu j){
        Game = j;
    }
}