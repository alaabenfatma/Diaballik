package Reseau.Serveur;

import java.util.Scanner;

public class Partie {

    

    public static void paraPartie(Diaballik.Models.ConfigJeu C){		
		C.setMode(Diaballik.Models.ConfigJeu.Mode.humain);
		Scanner saisie = new Scanner(System.in);
		System.out.println("Veuillez saisir la durée du timer : 1min, 2min, 3min, illimite");
		String timer = saisie.next();
		System.out.println("Veuillez saisir le nom du joueur1 :");
		String name1 = saisie.next();
		System.out.println("Veuillez saisir le nom du joueur2 :");
		String name2 = saisie.next();
		System.out.println("Veuillez saisir j1 si le joueur1 joue en premier, j2 sinon :");
        String premier = saisie.next();
        
        Diaballik.Models.ConfigJeu.Timer T ;

        if(timer == "1min"){
            //T = T.un;
            //C.setTimer(T);
        }else if(timer == "2min"){
            //T = T.deux;
            //C.setTimer(T);
        }else if(timer == "3min"){
            //T = T.trois;
            //C.setTimer(T);
        }else if(timer == "illimite"){
            //T = T.illimite;
            //C.setTimer(T);
        }

	}
    
}