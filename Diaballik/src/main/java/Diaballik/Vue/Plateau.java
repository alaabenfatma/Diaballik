package Diaballik.Vue;

import java.awt.*;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GraphicsEnvironment;
import java.awt.GraphicsDevice;

import java.awt.event.*;
import javax.imageio.ImageIO;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.*;

import Diaballik.Models.Jeu;
import Diaballik.Patterns.Observateur;

public class Plateau implements Runnable, Observateur {
    JFrame frame;
    JButton boutonMenu = new JButton("Menu");
    JButton boutonFinTour = new JButton("Fin du tour");
    JLabel joueur, mouvements, passe;
    PlateauGraphique plat;
    Jeu j;
    CollecteurEvenements control;
    boolean maximized;

    Plateau(Jeu jeu, CollecteurEvenements c) {
        j = jeu;
        control = c;
    }

    public static void demarrer(Jeu j, CollecteurEvenements c) {
        Plateau vue = new Plateau(j, c);
        c.ajouteInterfaceUtilisateur(vue);
        SwingUtilities.invokeLater(vue);
    }

    public void run() {
        // Creation d'une fenetre
        frame = new JFrame("Plateau");
        frame.addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent windowEvent) {
				if(msgBox.msgYesNo("Voulez-vous quitter?", "Quitter") == 0){
					msgBox.msgYesNo("Voulez-vous sauvegarder votre partie", "Sauvegarder");
					System.exit(0);
					// ajouter la sauvegarde
                }
                else{
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
                
                MenuEnJeu m = new MenuEnJeu(frame);
                frame.setContentPane(m);
                frame.repaint();
                frame.revalidate();
            }
        });

        // Info joueur
        joueur = new JLabel("Joue : Joueur1");
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

        // Retransmission des évènements au contrôleur
        plat.addMouseListener(new AdaptateurSouris(plat, control));
        frame.addKeyListener(new AdaptateurClavier(control));
        // Timer chrono = new Timer(dureeTour, new AdaptateurTemps(control));
        boutonFinTour.addActionListener(new AdaptateurFinTour(control));

        // Mise en place de l'interface
        frame.add(boiteTexte, BorderLayout.EAST);
        frame.add(plat, BorderLayout.CENTER);
        j.ajouteObservateur(this);
        // chrono.start();
        frame.setSize(700, 600);
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
        joueur.setText("Joue : " + j.joueurCourant.n);
        mouvements.setText("Déplacements : " + j.joueurCourant.nbMove);
        passe.setText("Passe : " + j.joueurCourant.passeDispo);
    }

}