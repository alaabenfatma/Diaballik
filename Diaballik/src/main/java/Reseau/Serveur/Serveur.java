package Reseau.Serveur;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;

import Diaballik.Models.ConfigJeu;

import java.net.InetAddress;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.Iterator;
import java.util.Map;
import java.util.Collections;


public class Serveur {
	// Serveur = Joueur qui créer une partie
	private static int port = 4242;// si 0 prend le premier libre
	private static InetAddress add;
	private HashMap<Integer ,PrintWriter> numC_out = new HashMap<Integer ,PrintWriter>();
	private HashMap<String,HashMap<Integer ,PrintWriter>> AllConnexion = new HashMap<String,HashMap<Integer ,PrintWriter>>(); 
	private static int nbC = -1;
	private static ServerSocket sS;
	PrintWriter out;
	private static Connexion C;
	
	public static void main(String[] args) {
		System.out.println("Démarage du serveur");
		Serveur Serv = new Serveur();
		try {
			new Commandes( Serv ); // Lance le thread de la gestion des commandes
		
			sS = new ServerSocket( port ); // Ouverture d'une socket
			port = sS.getLocalPort(); // récupère le port 
			info();
			while(true) { // Attente d'une connexion
				if(nbC +1==0) {
					sS.setSoTimeout(60000); // attente d'une connexion (1 min) avant de crash(se règle avec le catch)
				}
				else if(nbC +1 == 1) {
					sS.setSoTimeout(0); // attente d'une connexion (infini)
				}
				C = new Connexion(sS.accept(),Serv); // Lance le thread du nouveau client
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
		catch (SocketException sE) {
			System.out.println(" --- Client déconnecté ---");
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	private static void info() {
		System.out.println(" --- Server Ok ---");
		System.out.println(" --- Démaré sur le port : " + port + "---");
	}
	
	synchronized public void supp_client(int i,String m,boolean hote) {
		if(AllConnexion.containsKey(m)) {
			numC_out = AllConnexion.get(m);
			if(numC_out.containsKey(i)) {
				numC_out.remove(i);
				if(hote && numC_out.size() != 0) {
					numC_out = AllConnexion.get(m);
					
					Set<Integer> k = numC_out.keySet();
					Iterator<Integer> it = k.iterator();
					
					int j = it.next();
					out = numC_out.get(j);
					out.println("quit");
					out.flush();
					
					numC_out.remove(j);
					
					nbC--;
				}
				if(numC_out.size() == 0 || hote) {
					AllConnexion.remove(m);
				}
				nbC--;
				if(nbC == -1) {
					try {
						System.out.println(" --- Aucun client sur le serveur ---");
						System.out.println(" --- Extinction du serveur ---");
						sS.close();
						System.exit(0);
					} catch (IOException e) {
						e.printStackTrace();
					}
					
				}
			}
		}
	}
	synchronized public void ajout_client(PrintWriter out,int numC,String m) {
		System.out.println("Connecté à la partie N° : "+m);
		if(AllConnexion.containsKey(m)) {
			if(AllConnexion.get(m).size()<2) {
				numC_out = AllConnexion.get(m);
				numC_out.put(numC, out);
				nbC++;
				
				out.println("j2");
				out.flush();
			}
			else {
				System.out.println("Déconnexion forcée du Client : " + numC);
				out.println("Déconnexion");
				out.flush();
			}
		}
		else {
			numC_out = new HashMap<Integer ,PrintWriter>();
			numC_out.put(numC,out);
			AllConnexion.put(m,numC_out);
			nbC++;
			
			out.println("j1");
			out.flush();
		}
	}
	synchronized public int nbClient() {
		return nbC;
	}
	synchronized public void sendAllClient(String message,String sLast){
		Iterator<Map.Entry<String,HashMap<Integer ,PrintWriter>>> A_it = AllConnexion.entrySet().iterator();
		while(A_it.hasNext()) {
			Map.Entry<String,HashMap<Integer ,PrintWriter>> Key = A_it.next();
			numC_out = Key.getValue();
			
			Iterator<Map.Entry<Integer ,PrintWriter>> it = numC_out.entrySet().iterator();
			while (it.hasNext()) {
				Map.Entry<Integer ,PrintWriter> entry = it.next();
			  
				out = entry.getValue(); // extraction de l'élément courant (type PrintWriter)
		      if (out != null) {// sécurité, l'élément ne doit pas être vide
		        // écriture du texte passé en paramètre (et concaténation d'une string de fin de chaine si besoin)
		        out.println(message+sLast);
		        out.flush(); // envoi dans le flux de sortie
		      }
			}	
		}
	}
	synchronized public void sendClient(String message,int Client,String m) {
		if(AllConnexion.containsKey(m) && AllConnexion.get(m).containsKey(Client)) {
			if(message.equals("Connexion établie")) {
				if(AllConnexion.get(m).size()==1) {
					C.sethote(true);
				}else {
					C.sethote(false);
				}
			}
			out = AllConnexion.get(m).get(Client);
			if(out != null) {
				out.println(message);
				out.flush();
			}
			if(AllConnexion.get(m).size() == 2) {
				out.println("j2_ok");
				out.flush();
				
				Set<Integer> k = AllConnexion.get(m).keySet();
				Iterator<Integer> it = k.iterator();
				out = AllConnexion.get(m).get(it.next());
				out.println("j2_ok");
				out.flush();
			}
		}
	}
	synchronized public void C_total(int Client,String m) {
		if(AllConnexion.containsKey(m) && AllConnexion.get(m).containsKey(Client)) {
			out = AllConnexion.get(m).get(Client);
			if(out != null) {
				out.println(numC_out.size());
				out.flush();
			}
		}
	}
synchronized public void C_initialisation(int Client,String message,String m) {
	if(AllConnexion.containsKey(m)) {
		numC_out = AllConnexion.get(m);
		Enumeration<Integer> k = Collections.enumeration(numC_out.keySet());
		Integer C1 = k.nextElement();
		Integer C2 = k.nextElement();
		
		if(Client == C1) {
			out = numC_out.get(C2);
			//out = AllClient.get(1);
			if(out != null) {
				out.println("init");
				out.flush();
				
				out.println(message);
				out.flush();
				
				out = numC_out.get(Client);
				out.println("ok");
				out.flush();
			}
		}else {
			out = numC_out.get(C1);
			if(out != null) {
				out.println("init");
				out.flush();
				
				out.println(message);
				out.flush();
				
				out = numC_out.get(Client);
				out.println("ok");
				out.flush();
			}
		}
	}
	}
	synchronized public void C_test_Json(int Client,String message,String m){
		if(AllConnexion.containsKey(m)) {
			numC_out = AllConnexion.get(m);
			Enumeration<Integer> k = Collections.enumeration(numC_out.keySet());
			Integer C1 = k.nextElement();
			Integer C2 = k.nextElement();
			
			if(Client == C1) {
				out = numC_out.get(C2);
				//out = AllClient.get(1);
				if(out != null) {
					out.println("test_json");
					out.flush();
					
					out.println(message);
					out.flush();
					
					out = numC_out.get(Client);
					out.println("ok");
					out.flush();
				}
			}else {
				out = numC_out.get(C1);
				if(out != null) {
					out.println("test_json");
					out.flush();
					
					out.println(message);
					out.flush();
					
					out = numC_out.get(Client);
					out.println("ok");
					out.flush();
				}
			}
		}
		
	}

}
