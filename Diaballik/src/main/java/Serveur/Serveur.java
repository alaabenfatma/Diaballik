package Reseau;

import java.io.BufferedReader;
import java.net.UnknownHostException;
import java.net.InetAddress;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import Diaballik.Models.Jeu;

public class Serveur {
	// Serveur = Joueur qui créer une partie
	private static int port = 4242;// si 0 prend le premier libre
	private static InetAddress add;
	private static Socket s;
	private static ServerSocket sS;
	private static Jeu j;
	
	private static boolean connexion() {
		boolean connected = false;
		try {
			// Récupère l'adresse du serv
			add = InetAddress.getLocalHost(); //TODO afficher l'addresse IP pour le deuxième joueur
			System.out.println(add + " Est l'addresse Ip de mon pc a l'int�rieur de mon reseau");
			
			//Initialisation d'une socket
			sS = new ServerSocket(port); // si ajout d'un deuxième paramètre -> limite le nb de connexion
			sS.setSoTimeout(10000); // attente d'une connexion (10s) avant de crash(se règle avec le catch)
			s = sS.accept();
			connected = true;
		}catch(UnknownHostException e) { // Exception de add
			//e.printStackTrace();
		}catch(IOException e) { // Exception de sS et de s
			//e.printStackTrace();
		}
		return connected;
	}
	private static void init() {
		j = new Jeu();
		PrintWriter out;
		try {
			out = new PrintWriter(s.getOutputStream());
			//TODO méthode conversion terrain en String et inversement
			out.println();
			out.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		System.out.println("Je suis le serveur");
		//http://www.mon-ip.com/     (4.41 de la vid�o)
		if(connexion()){
			//test();
			init();
		}else {
			System.out.println("Aucun joueur ne s'est connecté, déconnexion du serveur");
			try {
				sS.close();
				s.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
	}
	private static void test() {
		try {
			PrintWriter out = new PrintWriter(s.getOutputStream());
			out.println("J'envoie du Serveur au Client");
			out.flush();
			BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
			System.out.println(in.readLine());
			
			sS.close();
			s.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}

}
