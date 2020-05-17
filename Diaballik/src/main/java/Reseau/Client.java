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

public class Client  {
	private static int port = 4242;
	private static String host = "127.0.0.1";
	static Socket s;
	static Scanner s_in = new Scanner(System.in);
	static Jeu j;
	
	static void init_test_jeu() {
		j = new Jeu();
		j.configurer(new ConfigJeu());
		j.start();
	}
	
	public static void main(String []args) {
		System.out.println("je suis le client");
		System.out.println("Connexion au serveur");
		try {
			init_test_jeu();
			s = new Socket(host,port);
			BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
			System.out.println(in.readLine());
			
			PrintWriter out = new PrintWriter(s.getOutputStream());
			
			Commandes_Client C = new Commandes_Client(s,s_in,in,out,j);
			
			System.out.print(">");
			String message = s_in.nextLine();
			while(!message.equals("quit")) {
				if(message.equals("total")) {
					C.C_total(message);
				}
				else if(message.equals("test_json")){
					C.C_test_json();
				}
				else if(message.equals("rep")) {
					C.C_reponse_Serv();
				}
				else {
					System.out.println("Envoi du message : "+message);
					out.println(message);
					out.flush();
				}
				System.out.print(">");
				message = s_in.nextLine();
			}
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
