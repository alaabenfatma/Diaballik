package Reseau.Serveur;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Commandes implements Runnable {
	Thread T;
	BufferedReader in;
	Serveur Serv;
	String cmd;
	Commandes(Serveur S){
		Serv = S;
		// flux d'entrée de la console (peut être changer)
		in = new BufferedReader(new InputStreamReader(System.in));
		T = new Thread(this);
		T.start(); // lancement de la fonction run
	}
	public void run() {
		try {
			while((cmd = in.readLine()) != null) {
				if(cmd.equalsIgnoreCase("quit")) {
					System.exit(0);
				}
				else if(cmd.equalsIgnoreCase("total")) {
					System.out.println("Nombre de connexions : " + Serv.nbClient());
				}
				else if(cmd.equalsIgnoreCase("para")) {					
					//Partie.paraPartie(C);
				}
				else {
					System.out.println("Commande inconnue !");
					System.out.println(" - quit");
					System.out.println(" - total");
					System.out.println(" - para");
				}
				System.out.flush();
			}
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
}
