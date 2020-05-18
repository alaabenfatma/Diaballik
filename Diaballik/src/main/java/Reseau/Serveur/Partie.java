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
        Menu menu = new Menu();
		Scanner saisie = new Scanner(System.in);
		System.out.println("Veuillez saisir la durée du timer : 1min, 2min, 3min, illimite");
		String timer = saisie.next();
		System.out.println("Veuillez saisir le nom du joueur1 :");
		String name1 = saisie.next();
		System.out.println("Veuillez saisir le nom du joueur2 :");
		String name2 = saisie.next();
		System.out.println("Veuillez saisir j1 si le joueur1 joue en premier, j2 sinon :");
        String premier = saisie.next();

        if(timer == "1min"){
            C.setTimer(Diaballik.Models.ConfigJeu.Timer.un);
        }else if(timer == "2min"){
            C.setTimer(Diaballik.Models.ConfigJeu.Timer.deux);
        }else if(timer == "3min"){
            C.setTimer(Diaballik.Models.ConfigJeu.Timer.trois);
        }else if(timer == "illimite"){
            C.setTimer(Diaballik.Models.ConfigJeu.Timer.illimite);
        }
        C.setName1(name1);
        C.setName2(name2);
        if(premier == "j1"){
            C.setP1First(false);
        }else {
            C.setP1First(true);
        }
    }
    
    public void run() {
        Diaballik.Models.Jeu j = new Diaballik.Models.Jeu();
        Diaballik.Models.ConfigJeu C = new Diaballik.Models.ConfigJeu();
        paraPartie(C);
        j.configurer(C);
        j.start();
		Diaballik.Vue.CollecteurEvenements control = new Diaballik.Controllers.ControleurMediateur(j);
		Diaballik.Vue.Plateau.demarrer(j, control, C);
    }
    
    
}