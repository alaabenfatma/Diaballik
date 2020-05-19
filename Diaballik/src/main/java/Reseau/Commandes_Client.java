package Reseau;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import com.fasterxml.jackson.databind.ObjectMapper;

import Diaballik.Models.Jeu;
import Diaballik.Models.JeuJSON;
import Diaballik.Controllers.ControleurMediateur;

public class Commandes_Client {
	Socket s;
	Scanner s_in;
	BufferedReader in;
	PrintWriter out;
	Jeu j;
	ControleurMediateur CM;
	ObjectMapper mapper = new ObjectMapper();
	JeuJSON j_json;
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
	public void C_test_json(Boolean att) {
		if(att) {
			System.out.println(" --- Décodage du String JSON --- ");
			String message;
			try {
				message = in.readLine();
				System.out.println(message);
				j_json = mapper.readValue(message, JeuJSON.class);
				System.out.println(" --- MAJ des données --- ");
				
				j.tr._terrain = j.tr.toPieces(j_json.Terrain); //MAJ du Terrain
				j.joueurCourant = j.joueurCourant; //MAJ du joueur a jouer
				
				System.out.println(" --- MAJ terminée ---");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			try {
				out.println("test_json");
				out.flush();
				String message;
				CM.save();
				message=j.JSONfromGame(j);
				//System.out.println(message);
				out.println(message);
				out.flush();
				System.out.println(in.readLine());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
