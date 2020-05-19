package Reseau.Serveur;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;

public class Connexion implements Runnable {
	private Thread T;
	private Socket s;
	private PrintWriter out;
	private BufferedReader in;
	private Serveur Serv;
	private int numClient;
	private String numPartie;
	public boolean hote;
	
	Connexion(Socket sock, Serveur S){
		Serv = S;
		s = sock;
		try {
			out = new PrintWriter(s.getOutputStream());
			in = new BufferedReader(new InputStreamReader(s.getInputStream()));
			numPartie = in.readLine(); // numéro de la partie
			numClient = Serv.nbClient() +1;
			Serv.ajout_client(out,numClient,numPartie);
		}catch(IOException e) {
			e.printStackTrace();
		}
		T = new Thread(this);
		T.start();
	}
	public void sethote(boolean h) {
		hote = h;
	}
	public void run() {
		String message;
		System.out.println(" --- Connexion d'un nouveau client ---");
		System.out.println(" --- Numéro attribué au client : "+ numClient +" --- ");
		try {
			Serv.sendClient("Connexion établie", numClient,numPartie);
			message = in.readLine();
			System.out.println(" --- Commande du client recu : " + message +" ---");
			while(!message.equals("quit")) {
				if(message.equals("total")) {
					Serv.C_total(numClient,numPartie);
				}
				else if(message.equals("init")) {
					Serv.C_initialisation(numClient,in.readLine(),numPartie);
				}
				else if(message.equals("test_json")) {
					System.out.println("Json recu:");
					Serv.C_test_Json(numClient,in.readLine(),numPartie);
				}
				else {
					System.out.println("Message du client :"+ numClient);
					System.out.println(message);
				}
				message = in.readLine();
			}
			in.close();
			out.close();
			System.exit(0);
		}
		catch(SocketException sE) {
			System.out.println("Client " + numClient + " déconnecté");
		}
		catch(NullPointerException p) {
			System.out.println("Client " + numClient + " déconnecté");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		finally { // Se produit lors de la déconnexion du client
			try {
				System.out.println(" --- Déconnexion du client : "+numClient + " ---");
				Serv.supp_client(numClient,numPartie,hote);
				s.close();
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
	}
}
