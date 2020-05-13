package Diaballik.Vue;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GraphicsEnvironment;
import java.awt.GraphicsDevice;

import javax.swing.JButton;
import javax.swing.*;

import Diaballik.Models.ConfigJeu;
import Diaballik.Models.Jeu;
import Diaballik.Patterns.Observateur;

public class Plateau implements Runnable, Observateur {
    JFrame frame;
    JButton boutonMenu = new JButton("Menu");
    JButton boutonFinTour = new JButton("Fin du tour");
    JButton boutonRecommencer = new JButton("Recommencer");
    JButton boutonRejouer = new JButton("Rejouer");
    JLabel joueur, mouvements, passe;
    PlateauGraphique plat;
    Jeu j;
    ConfigJeu conf;
    CollecteurEvenements control;
    boolean maximized;
    static ihm interHM;

    static void setIHM(ihm i) {
        interHM = i;
    }

    Plateau(Jeu jeu, CollecteurEvenements c, ConfigJeu cj) {
        j = jeu;
        control = c;
        conf = cj;
    }

    public static void demarrer(Jeu j, CollecteurEvenements c, ConfigJeu cj) {
        Plateau vue = new Plateau(j, c, cj);
        c.ajouteInterfaceUtilisateur(vue);
        SwingUtilities.invokeLater(vue);
    }

    public void run() {
        // Creation d'une fenetre
        frame = new JFrame("Plateau");
        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                if (msgBox.msgYesNo("Voulez-vous quitter?", "Quitter") == 0) {
                    msgBox.msgYesNo("Voulez-vous sauvegarder votre partie", "Sauvegarder");
                    System.exit(0);
                    // ajouter la sauvegarde
                } else {
                    return;
                }
            }
        });
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        // Ajout de notre composant de dessin dans la fenetre
        plat = new VuePlateau(j);

        // Texte et contrôles à droite de la fenêtre
        Box boiteTexte = Box.createVerticalBox();
        boiteTexte.setOpaque(true);
        boiteTexte.setBackground(Color.lightGray);

        // Bouton Menu
        boutonMenu.setFocusable(false);
        boiteTexte.add(boutonMenu);
        boiteTexte.add(Box.createRigidArea(new Dimension(0, 100)));
        boutonMenu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFrame f = new JFrame();
                f.setSize(700, 530);
                f.setLayout(null);
                MenuEnJeu m = new MenuEnJeu(f, frame);
                f.setContentPane(m);
                f.setVisible(true);
            }
        });

        // Info joueur
        joueur = new JLabel("Joue : " + j.joueurCourant.name);
        joueur.setAlignmentX(Component.CENTER_ALIGNMENT);
        joueur.setAlignmentY(Component.CENTER_ALIGNMENT);
        joueur.setOpaque(true);
        joueur.setBackground(Color.white);
        boiteTexte.add(joueur);
        boiteTexte.add(Box.createRigidArea(new Dimension(0, 20)));

        // Info mouvements
        mouvements = new JLabel("Déplacements : 2");
        mouvements.setAlignmentX(Component.CENTER_ALIGNMENT);
        mouvements.setOpaque(true);
        mouvements.setBackground(Color.white);
        boiteTexte.add(mouvements);

        // Info passe
        passe = new JLabel("Passe : 1");
        passe.setAlignmentX(Component.CENTER_ALIGNMENT);
        passe.setOpaque(true);
        passe.setBackground(Color.white);
        boiteTexte.add(passe);

        // TODO : ajouter timer
        boiteTexte.add(Box.createRigidArea(new Dimension(0, 40)));
        boutonFinTour.setFocusable(false);
        boutonFinTour.setAlignmentX(Component.CENTER_ALIGNMENT);
        boiteTexte.add(boutonFinTour);

        //bouton recommencer
        boiteTexte.add(Box.createRigidArea(new Dimension(0, 20)));
        boutonRecommencer.setFocusable(false);
        boutonRecommencer.setAlignmentX(Component.CENTER_ALIGNMENT);
        boiteTexte.add(boutonRecommencer);
        boutonRecommencer.setVisible(false);
        boutonRecommencer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                j.init();
                j.start();
            }
        });

        /*//bouton rejouer
        boutonRejouer.setFocusable(false);
        boiteTexte.add(boutonRejouer);
        //boiteTexte.add(Box.createRigidArea(new Dimension(0, 100)));
        boutonRejouer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                interHM.setVisible(true);
                interHM.fenetreNouvellePartie();
				
            }
        }); */


        // Retransmission des évènements au contrôleur
        plat.addMouseListener(new AdaptateurSouris(plat, control));
        frame.addKeyListener(new AdaptateurClavier(control));
        // Timer chrono = new Timer(dureeTour, new AdaptateurTemps(control));
        boutonFinTour.addActionListener(new AdaptateurFinTour(control));

        // Mise en place de l'interface
        boiteTexte.setPreferredSize(new Dimension(150, 600));
        frame.add(boiteTexte, BorderLayout.EAST);
        plat.setPreferredSize(new Dimension(600, 600));
        frame.add(plat, BorderLayout.CENTER);
        j.ajouteObservateur(this);
        // chrono.start();
        frame.pack();
        // frame.setSize(700, 600);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }

    public void toggleFullscreen() {
        GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice device = env.getDefaultScreenDevice();
        if (maximized) {
            device.setFullScreenWindow(null);
            maximized = false;
        } else {
            device.setFullScreenWindow(frame);
            maximized = true;
        }
    }

    @Override
    public void miseAJour() {
        if (j.gameOver) {
            mouvements.setVisible(false);
            passe.setVisible(false);
            boutonFinTour.setVisible(false);
            //joueur.setSize(20, 20);
            joueur.setText("Victoire de " + j.joueurCourant.name + " ! ");
            boutonRecommencer.setVisible(true);
        } else{
            boutonRecommencer.setVisible(false);
            mouvements.setVisible(true);
            passe.setVisible(true);
            boutonFinTour.setVisible(true);
            joueur.setText("Joue : " + j.joueurCourant.name);
            mouvements.setText("Déplacements : " + j.joueurCourant.nbMove);
            passe.setText("Passe : " + j.joueurCourant.passeDispo);
        }
            
       
    }

}