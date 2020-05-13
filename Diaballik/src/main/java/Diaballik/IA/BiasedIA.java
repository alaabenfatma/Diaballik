package Diaballik.IA;

import Diaballik.Models.Terrain;

public class BiasedIA {
    
    public static void main(String[] args) {
        Terrain t = new Terrain();
        t.Create();
        System.out.println(Evaluator.scoreOfBoard(t));
    }
}