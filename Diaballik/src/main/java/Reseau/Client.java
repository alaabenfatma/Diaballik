package Reseau;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;

public class Client {
	private static int port = 4242;
	private static String host = "127.0.0.1";
	static Socket s;
	public static void main(String[] args) {
		System.out.println("je suis le client");
		
		try {
			s = new Socket(host,port);
			
			BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
			System.out.println(in.readLine());
			
			/*PrintWriter out = new PrintWriter(s.getOutputStream());
			out.println("J'envoie du Client au Serveur");
			out.flush();*/
			
			s.close();
		}
		catch(IOException e) {
			if(e.getMessage().equalsIgnoreCase("Connection reset")) {
				System.out.println("Serveur fermée, deconnexion du client");
				try {
					s.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			else {
			e.printStackTrace();
			}
		}
	}

}
