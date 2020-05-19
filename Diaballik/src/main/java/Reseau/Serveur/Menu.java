package Reseau.Serveur;

import javax.swing.*;

import Diaballik.Models.ConfigJeu;

import java.awt.Color;
import java.awt.event.*;

public class Menu {

    public Menu(final ConfigJeu C){
        final JFrame frame = new JFrame();
        final JPanel panel = new JPanel();
        final JLabel timer = new JLabel("Timer");
        panel.setLayout(null);
        frame.setSize(515, 410);
        frame.setLocationRelativeTo(null);
        frame.setTitle("Parametrage de la partie");
        final JButton Unemin = new JButton("1min");
        final JButton Deuxmin = new JButton("2min");
        final JButton Troismin = new JButton("3min");
        final JButton illimite = new JButton("illimite");
        final JButton joueur1 = new JButton("joueur1");
        final JButton joueur2 = new JButton("joueur2");
        final JButton jouer = new JButton("jouer");
        final JButton retour = new JButton("retour");
        final JLabel n1 = new JLabel("nom du joueur 1 :");
        final JTextArea nom1 = new JTextArea("joueur1");
        final JLabel premier = new JLabel("Joue en premier");
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
        jouer.setBounds(280,280,100,40);
        retour.setBounds(120,280,100,40);

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
        panel.add(jouer);
        panel.add(retour);
        frame.setContentPane(panel);
        frame.setVisible(true);

        

        illimite.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                illimite.setBackground(Color.pink);
                Unemin.setBackground(null);
                Deuxmin.setBackground(null);
                Troismin.setBackground(null);
                C.setTimer(Diaballik.Models.ConfigJeu.Timer.illimite);
            }
        });
    
        Unemin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Unemin.setBackground(Color.pink);
                illimite.setBackground(null);
                Deuxmin.setBackground(null);
                Troismin.setBackground(null);
                C.setTimer(Diaballik.Models.ConfigJeu.Timer.un);
            }
        });
    
        Deuxmin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Deuxmin.setBackground(Color.pink);
                Unemin.setBackground(null);
                illimite.setBackground(null);
                Troismin.setBackground(null);
                C.setTimer(Diaballik.Models.ConfigJeu.Timer.deux);
            }
        });
    
        Troismin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Troismin.setBackground(Color.pink);
                Unemin.setBackground(null);
                Deuxmin.setBackground(null);
                illimite.setBackground(null);
                C.setTimer(Diaballik.Models.ConfigJeu.Timer.trois);
            }
        });

        joueur1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                joueur1.setBackground(Color.pink);
                joueur2.setBackground(null);
                C.setP1First(true);
            }
        });

        joueur2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                joueur2.setBackground(Color.pink);
                joueur1.setBackground(null);
                C.setP1First(false);
            }
        });

        jouer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                Diaballik.Models.Jeu j = new Diaballik.Models.Jeu(); 
                String nom = nom1.getText();
                C.setName1(nom);       
                j.configurer(C);
                j.start();
                Diaballik.Vue.CollecteurEvenements control = new Diaballik.Controllers.ControleurMediateur(j);
		        Diaballik.Vue.Plateau.demarrer(j, control, C);
            }
        });

        retour.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
            }
        });


    }  

    

}