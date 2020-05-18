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

public class Client implements Runnable  {
	private static int port = 4242;
	private static String host = "127.0.0.1";
	static Socket s;
	static Scanner s_in = new Scanner(System.in);
	static Jeu j;
	static String Joueur;
	static boolean attente;
<<<<<<< HEAD
	static String numP = "123";
	static PrintWriter out;
	static BufferedReader in;
	
=======
	private Thread T;
	public Client(String c){
		T = new Thread(this);
		T.start();
	}
>>>>>>> 3f430a6943e750f139867206ae91b70a89f7507c
	static void init_test_jeu() {
		Reseau.Serveur.Partie p = new Reseau.Serveur.Partie();
	}
	
	public void run() {
		System.out.println("je suis le client");
		System.out.println("Connexion au serveur");
		try {
			s = new Socket(host,port);
			in = new BufferedReader(new InputStreamReader(s.getInputStream()));
			out = new PrintWriter(s.getOutputStream());
			out.println(numP);
			out.flush();
			
			init_test_jeu();
			
			
			Joueur = in.readLine(); // definition du joueur
			
			if(Joueur.equals("j1")){attente = false;}
			else {attente = true;}
			System.out.println("attente : "+ attente);
			
			System.out.println("test "+in.readLine()); // Connexion établie
			
			PrintWriter out = new PrintWriter(s.getOutputStream());
			
			Commandes_Client C = new Commandes_Client(s,s_in,in,out,j);
			
			System.out.print(">");
			System.out.println(Joueur);
			
			while(true) {
				if(attente) {
					String cmp = in.readLine();
					if(cmp.equals("rep")) {
						System.out.println(in.readLine());
					}
					attente = !attente;
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
						C.C_test_json();
						attente = !attente;
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
