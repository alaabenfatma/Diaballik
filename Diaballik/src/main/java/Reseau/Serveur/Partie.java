package Reseau.Serveur;

import java.util.Scanner;



public class Partie implements Runnable{
    private Thread T;

    public Partie(){
        T = new Thread(this);
		T.start();
    }

    

    public static void paraPartie(Diaballik.Models.ConfigJeu C){
        C.setMode(Diaballik.Models.ConfigJeu.Mode.humain);
        Menu menu = new Menu(C);
    }
    
    public void run() {
        Diaballik.Models.Jeu j = new Diaballik.Models.Jeu();
        Diaballik.Models.ConfigJeu C = new Diaballik.Models.ConfigJeu();
        paraPartie(C);
		
    }
    
    
}