package Reseau.Serveur;

import java.util.Scanner;

import javax.swing.*;

public class Partie implements Runnable{
    private Thread T;

    public Partie(){
        T = new Thread(this);
		T.start();
    }

    public static void menu(){
        JFrame menu = new JFrame();
        menu.setTitle("Parametrage de la partie");
        JButton Unemin = new JButton("1min");
        JButton Deuxmin = new JButton("2min");
        JButton Troismin = new JButton("3min");
        JButton illimite = new JButton("illimite");
        JButton joueur1 = new JButton("joueur1");
        JButton joueur2 = new JButton("joueur2");

        Unemin.setBounds((menu.getWidth() / 5), (menu.getHeight() / 4) ,100,20);
        Deuxmin.setBounds((menu.getWidth() / 2*5), (menu.getHeight() / 4) ,100,20);
        Troismin.setBounds((menu.getWidth() / 3*5), (menu.getHeight() / 4) ,100,20);
        illimite.setBounds((menu.getWidth() / 4*5), (menu.getHeight() / 4) ,100,20);
        joueur1.setBounds((menu.getWidth() / 4), (menu.getHeight() / 4) + 140 ,100,20);
        joueur2.setBounds((menu.getWidth() / 4), (menu.getHeight() / 4) + 170 ,100,20);
        menu.add(Unemin);
        menu.add(Deuxmin);
        menu.add(Troismin);
        menu.add(illimite);
        menu.add(joueur1);
        menu.add(joueur2);
        menu.setVisible(true);
    }
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
        //paraPartie(C);
        j.configurer(C);
        j.start();
		Diaballik.Vue.CollecteurEvenements control = new Diaballik.Controllers.ControleurMediateur(j);
		Diaballik.Vue.Plateau.demarrer(j, control, C);
    }
    
    
}