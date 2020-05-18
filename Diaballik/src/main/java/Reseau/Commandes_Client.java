package Reseau;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import Diaballik.Models.Jeu;
import Diaballik.Controllers.ControleurMediateur;

public class Commandes_Client {
	Socket s;
	Scanner s_in;
	BufferedReader in;
	PrintWriter out;
	Jeu j;
	ControleurMediateur CM;
	
	public Commandes_Client(Socket _S,Scanner _s_in,BufferedReader _in,PrintWriter _out,Jeu _j){
		s = _S;
		s_in = _s_in;
		in = _in;
		out = _out;
		j = _j;
		CM = new ControleurMediateur(j);
	}
	public void C_total(String message) {
		System.out.println("Envoi du message : "+message);
		out.println(message);
		out.flush();
		try {
			System.out.println("Nombre de client connecté au serveur : " + in.readLine());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void C_test_json() {
		out.println("test_json"); // prépare l'autre joueur à recevoir
		out.flush();
		String message;
		CM.save();
		message=j.JSONfromGame(j);
		//System.out.println(message);
		out.println(message);
		out.flush();
		
		C_reponse_Serv();
	}
	public void C_reponse_Serv() {
		try {
			System.out.println(in.readLine());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
