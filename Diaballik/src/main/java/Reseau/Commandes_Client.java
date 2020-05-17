package Reseau;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Commandes_Client {
	Socket s;
	Scanner s_in;
	BufferedReader in;
	PrintWriter out;
	public Commandes_Client(Socket _S,Scanner _s_in,BufferedReader _in,PrintWriter _out){
		s = _S;
		s_in = _s_in;
		in = _in;
		out = _out;
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
}
