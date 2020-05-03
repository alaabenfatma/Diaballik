package Diaballik.Vue;

public class SauvegarderPartie {

    int Sauvegarde[][] = new int[7][7];
    //valeurs possible : 0 = case vide, 1 = joueur1 sans ballon, 2 = joueur1 avec ballon, 3 = joueur2 sans ballon, 4 = joueur2 avec ballon
    boolean joueur1 = true; //true = le joueur est un humain
    boolean joueur2 = true; // true = le joueur est un humain
    int chrono = 0; // 0 = illimite, 1 = uneMin, 2 = deuxMin ect
    char date[] = new char[50];
    char nom1 [] = new char[30]; //nom du joueur 1
    char nom2[] = new char[30]; // nom du joueur 2
}