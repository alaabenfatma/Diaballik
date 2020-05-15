package Reseau.Serveur;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Connexion implements Runnable {
	private Thread T;
	private Socket s;
	private PrintWriter out;
	private BufferedReader in;
	private Serveur Serv;
	private int numClient;
	
	Connexion(Socket sock, Serveur S){
		Serv = S;
		s = sock;
		try {
			out = new PrintWriter(s.getOutputStream());
			in = new BufferedReader(new InputStreamReader(s.getInputStream()));
			Serv.ajout_client(out);
			numClient = Serv.nbClient();
		}catch(IOException e) {
			e.printStackTrace();
		}
		T = new Thread(this);
		T.start();
	}
	
	public void run() {
		String message;
		System.out.println("Connexion d'un nouveau client");
		System.out.println("Numéro attribué au client : "+ numClient);
		try {
			message = in.readLine();
			Serv.sendAllClient(message, "\n\0");
			message ="";
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		finally { // Se produit lors de la déconnexion du client
			try {
				System.out.println("Déconnexion du client : "+numClient);
				Serv.supp_client(numClient);
				s.close();
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
	}
}
