package Diaballik.Models;
import Diaballik.IA.*;

public class State implements Comparable{
    public Jeu Game;
    public Terrain Terrain;
    public FromTo firstMove;
    public FromTo secondMove;
    public FromTo pass;
    public int score(){
        return Evaluator.scoreOfBoard(this.Terrain);
    }
    public State(Jeu j){
        Game = j;
        Terrain = j.tr;
    }
    public State(Terrain tr){
        Terrain = tr;
    }

    @Override
    public int compareTo(Object o) {
        State s = (State)o;
        return this.score() - s.score();
    }
}