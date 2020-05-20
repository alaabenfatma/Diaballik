package Reseau;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;
import java.util.Scanner;

import Diaballik.Models.ConfigJeu;
import Diaballik.Models.Jeu;

public class Client implements Runnable {
	private static int port = 4242;
	private static String host = "127.0.0.1";
	static Socket s;
	static Scanner s_in = new Scanner(System.in);
	static Jeu _j;
	static String Joueur;
	static boolean attente;
	static String numP;
	static PrintWriter out;
	static BufferedReader in;
	static boolean hote;
	private Thread T;
	public Client(String c){
		numP = c;
		T = new Thread(this);
		T.start();
	}
	static void init_test_jeu() {
		Reseau.Serveur.Partie p = new Reseau.Serveur.Partie(hote);
		_j = p.j;
		/*_j = new Jeu();
		_j.configurer(new ConfigJeu());
		_j.start();*/
	}
	/*
	static void init_test_jeu() {
		j = new Jeu();
		j.configurer(new ConfigJeu());
		j.start();
		numP="123";
	}
	*/
	public void run() {
	//public static void main(String arg[]) {
		System.out.println("je suis le client");
		System.out.println("Connexion au serveur");
		try {
			s = new Socket(host,port);
			in = new BufferedReader(new InputStreamReader(s.getInputStream()));
			out = new PrintWriter(s.getOutputStream());
			System.out.println("nump2 :"+numP);
			out.println(numP);
			out.flush();
			
			Joueur = in.readLine(); // definition du joueur
			if(Joueur.equals("Déconnexion")) {
				System.out.println("Déconnexion du client");
				System.exit(0);
			}
			else if(Joueur.equals("j1")){
				System.out.println("--- je suis le client 1");
				attente = false;
			}
			else {
				System.out.println("--- je suis le client 2");
				attente = true;
			}
			System.out.println(in.readLine()); // Connexion établie
			
			PrintWriter out = new PrintWriter(s.getOutputStream());

			Commandes_Client C = new Commandes_Client(s,s_in,in,out,_j);
			if(Joueur.equals("j1")) {
				hote = true;
			}else {
				hote = false;
			}
			init_test_jeu();
			if(Joueur.equals("j1") && in.readLine().equals("j2_ok")) {
				System.out.println("Initialisation de la partie");
				C.C_init_partie(attente);
				System.out.println("Initialisation de la partie terminée");
			}else if(Joueur.equals("j2")) {
				in.readLine();
			}
			while(true) {
				if(in == null || out == null) {break;}
				if(attente) {
					System.out.println("Attente de l'autre Client...");
					String cmd = in.readLine();
					System.out.println("Commande reçu = "+ cmd);
					if(cmd.equals("rep")) {
						System.out.println(in.readLine());
					}else if(cmd.equals("test_json")) {
						C.C_test_json(attente);
						attente = !attente;
					}
					else if(cmd.equals("init")){
						C.C_init_partie(attente);	
					}
					else if(cmd.equals("quit")) {break;}
					
				}else {
					System.out.print(">");
					String message = s_in.nextLine();
					if(message.equals("quit")) {
						break;
					}
					else if(message.equals("total")) {
						C.C_total(message);
					}
					else if(message.equals("test_json")){
						C.C_test_json(attente);
						System.out.println(attente = !attente);
					}
					else {
						System.out.println("Envoi du message : "+message);
						out.println(message);
						out.flush();
					}

				}
				
				
			}
			out.close();
			s.close();
			s_in.close();
			System.exit(0);
		}
		catch (SocketException sE) {
			System.out.println("Serveur fermée, deconnexion du client");
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}

}