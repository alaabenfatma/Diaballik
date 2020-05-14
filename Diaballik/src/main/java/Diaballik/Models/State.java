package Diaballik.Models;
import Diaballik.IA.*;

public class State implements Comparable{
    public Jeu Game;
    public Terrain Terrain;
    public int nbmoves = 2;
    public int nbpasses = 1;
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
        if (this.score()==(s.score()))
            return 0;
        else if(this.score()>s.score()){
            return 1;
        }
        return this.compareTo(s);
    }
}