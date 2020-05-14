package Reseau;

import java.io.BufferedReader;
import java.net.UnknownHostException;
import java.net.InetAddress;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Serveur {
	// Serveur = Joueur qui créer une partie
	private static int port = 4242;// si 0 prend le premier libre
	
	public static void main(String[] args) {
		System.out.println("Je suis le serveur");
		//http://www.mon-ip.com/     (4.41 de la vid�o)
		try {
			InetAddress add = InetAddress.getLocalHost();
			System.out.println(add + " Est l'addresse Ip de mon pc a l'int�rieur de mon reseau");
			InetAddress add2 = InetAddress.getByName("localhost");
			System.out.println(add2 + "Est l'addresse Ip");
			
		}catch(UnknownHostException e) {
			e.printStackTrace();
		}
		/*
		try {
			ServerSocket sS = new ServerSocket(port); // si ajout d'un deuxième paramètre -> limite le nb de connexion
			
			sS.setSoTimeout(10000); // attente d'une connexion (10s) avant de crash(se règle avec le catch)
			
			Socket s = sS.accept();
			
			PrintWriter out = new PrintWriter(s.getOutputStream());
			out.println("J'envoie du Serveur au Client");
			out.flush();
			
		
			BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
			System.out.println(in.readLine());
			
			sS.close();
			s.close();
		}catch(IOException e) {
			e.printStackTrace();
		}*/
	}

}
