package Reseau.Serveur;

import java.util.ArrayList;
import java.net.InetAddress;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;


public class Serveur {
	// Serveur = Joueur qui créer une partie
	private static int port = 4242;// si 0 prend le premier libre
	private static InetAddress add;
	private ArrayList<PrintWriter> AllClient = new ArrayList<PrintWriter>(); // contient tous les flux de sortie vers les clients
	private static int nbC = 0;
	private static ServerSocket sS;
	
	public static void main(String[] args) {
		System.out.println("Démarage du server");
		Serveur Serv = new Serveur();
		try {
			new Commandes( Serv ); // Lance el thread de la gestion des commandes
		
			sS = new ServerSocket( port ); // Ouverture d'une socket
			port = sS.getLocalPort(); // récupère le port 
			info();
			while(true) { // Attente d'une connexion
				if(nbC==0) {
					sS.setSoTimeout(10000); // attente d'une connexion (10s) avant de crash(se règle avec le catch)
				}
				else if(nbC == 1) {
					sS.setSoTimeout(0); // attente d'une connexion (infini)
				}
				new Connexion(sS.accept(),Serv); // Lance le thread du nouveau client
			}
		}
		catch (SocketTimeoutException e){
			System.out.println(" --- Temps de connexion dépassé ---");
			System.out.println(" --- Extinction du serveur ---");
			try {
				sS.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	private static void info() {
		System.out.println(" --- Server Ok ---");
		System.out.println(" --- Démaré sur le port : " + port + "---");
	}
	synchronized public void supp_client(int i) {
		if(AllClient.get(i) != null) {
			AllClient.remove(i);
			nbC--;
			if(nbC == 0) {
				System.out.println(" --- Aucun client sur le serveur ---");
				System.out.println(" --- Extinction du serveur ---");
				try {
					sS.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			}
		}
	}
	synchronized public void ajout_client(PrintWriter out) {
		AllClient.add(out);
		nbC++;
	}
	synchronized public int nbClient() {
		return nbC;
	}
	synchronized public void sendAllClient(String message,String sLast){
	    PrintWriter out;
	    for (int i = 0; i < AllClient.size(); i++){
	      out = AllClient.get(i); // extraction de l'élément courant (type PrintWriter)
	      if (out != null) {// sécurité, l'élément ne doit pas être vide
	        // écriture du texte passé en paramètre (et concaténation d'une string de fin de chaine si besoin)
	        out.print(message+sLast);
	        out.flush(); // envoi dans le flux de sortie
	      }
	    }
	  }

}
