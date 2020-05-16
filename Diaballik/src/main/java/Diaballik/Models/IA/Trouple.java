package Diaballik.Models.IA;

import Diaballik.Models.Terrain;

public class Trouple {
    int nbMove = 2;
    int nbPasse = 1;
    Terrain tr;
    
    public Trouple(int n,int b,Terrain t){
        nbMove = n;
        nbPasse = b;
        tr = t;
    }
}