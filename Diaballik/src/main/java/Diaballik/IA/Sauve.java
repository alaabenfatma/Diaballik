package Diaballik.IA;

import java.util.ArrayList;

import Diaballik.Models.Position;
import Diaballik.Models.Terrain;

public class Sauve {
    int nbMove = 2;
    int nbPasse = 1;
    Terrain tr;
    ArrayList<Position> Mouvement;
    
    public Sauve(int n,int b,Terrain t,ArrayList<Position> m){
        nbMove = n;
        nbPasse = b;
        tr = t;
        Mouvement = m;
    }
}