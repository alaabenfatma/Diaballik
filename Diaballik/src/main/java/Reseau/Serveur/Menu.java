package Reseau.Serveur;

import javax.swing.*;
import java.awt.Color;

public class Menu {

    public Menu(){
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();
        JLabel timer = new JLabel("Timer");
        panel.setLayout(null);
        frame.setSize(515, 410);
        frame.setLocationRelativeTo(null);
        frame.setTitle("Parametrage de la partie");
        JButton Unemin = new JButton("1min");
        JButton Deuxmin = new JButton("2min");
        JButton Troismin = new JButton("3min");
        JButton illimite = new JButton("illimite");
        JButton joueur1 = new JButton("joueur1");
        JButton joueur2 = new JButton("joueur2");
        JLabel n1 = new JLabel("nom du joueur 1 :");
        JTextArea nom1 = new JTextArea("joueur1");
        JLabel n2 = new JLabel("nom du joueur 2 :");
        JTextArea nom2 = new JTextArea("joueur2");
        JLabel premier = new JLabel("Joue en premier");
        timer.setBounds(230,10,100,40);
        illimite.setBounds(20,50,100,40);
        Unemin.setBounds(140,50,100,40);
        Deuxmin.setBounds(260,50,100,40);
        Troismin.setBounds(380,50,100,40);        
        n1.setBounds(20,120,100,40);
        nom1.setBounds(150,120,100,40);;
        premier.setBounds(200,180,100,40);
        joueur1.setBounds(120,220,100,40);
        joueur2.setBounds(280,220,100,40);

        illimite.setBackground(Color.pink);
        joueur1.setBackground(Color.pink);
            

        panel.add(timer);
        panel.add(Unemin);
        panel.add(Deuxmin);
        panel.add(Troismin);
        panel.add(illimite);
        panel.add(n1);
        panel.add(nom1);
        panel.add(premier);
        panel.add(joueur1);
        panel.add(joueur2);
        frame.setContentPane(panel);
        frame.setVisible(true);
    }
    
}