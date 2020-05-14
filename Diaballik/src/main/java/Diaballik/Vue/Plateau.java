package Diaballik.Vue;

import java.awt.event.*;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GraphicsEnvironment;
import java.awt.GraphicsDevice;
import java.awt.*;
import java.util.Date;

import javax.swing.JButton;
import javax.imageio.ImageIO;
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
    JLabel boutonInfo;
    JLabel joueur, mouvements, passe;
    PlateauGraphique plat;
    Jeu j;
    ConfigJeu conf;
    CollecteurEvenements control;
    boolean maximized;
    static ihm interHM;
    Timer timer;

    JCheckBox buttonViewArrow = new JCheckBox("Indicateur coups joués");


    private static JLabel clock;
    private static long x;

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
                    if (msgBox.msgYesNo("Voulez-vous sauvegarder votre partie", "Sauvegarder") == 0) {
                        j.ExportGameToJSON(j);
                    }
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

        // bouton info
        Image img = null;
        try {
            img = ImageIO.read(this.getClass().getResourceAsStream(("img/info2.png"))).getScaledInstance(30, 30,
                    Image.SCALE_DEFAULT);
            ;
        } catch (Exception e) {
            System.out.println(e);
        }
        boutonInfo = new JLabel(new ImageIcon(img));
        String sInfo = "<html> <b>Raccourcis : </b> <br> f : fin du tour <br> z : annuler <br> y : refaire  <br> s : sauvegarder <br> r : recommencer <br> echap : plein écran <br> q : quitter  </html>  ";
        boutonInfo.setToolTipText(sInfo);
        boutonInfo.setFocusable(false);

        // Bouton Menu
        boutonMenu.setFocusable(false);
        // boiteTexte.add(boutonMenu);
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

        // Ajout des boutons en haut
        Box topButton = Box.createHorizontalBox();
        topButton.add(boutonInfo);
        topButton.add(Box.createRigidArea(new Dimension(5, 0)));
        topButton.add(boutonMenu);
        topButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        boiteTexte.add(topButton);
        boiteTexte.add(Box.createRigidArea(new Dimension(0, 10)));

        buttonViewArrow.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					VuePlateau.viewArrow = true;
				} else {
					VuePlateau.viewArrow = false;
				}
				;
			}
        });
        buttonViewArrow.setFocusable(false);
        buttonViewArrow.setAlignmentX(Component.CENTER_ALIGNMENT);
        boiteTexte.add(buttonViewArrow);
        boiteTexte.add(Box.createRigidArea(new Dimension(0, 90)));

        // Info joueur
        joueur = new JLabel("Joue : " + j.joueurCourant.name);
        joueur.setAlignmentX(Component.CENTER_ALIGNMENT);
        joueur.setAlignmentY(Component.CENTER_ALIGNMENT);
        joueur.setOpaque(true);
        joueur.setBackground(Color.white);
        boiteTexte.add(joueur);
        boiteTexte.add(Box.createRigidArea(new Dimension(0, 20)));

        // Info mouvements
        mouvements = new JLabel("Déplacements : " + j.joueurCourant.nbMove);
        mouvements.setAlignmentX(Component.CENTER_ALIGNMENT);
        mouvements.setOpaque(true);
        mouvements.setBackground(Color.white);
        boiteTexte.add(mouvements);

        // Info passe
        passe = new JLabel("Passe : " + j.joueurCourant.passeDispo);
        passe.setAlignmentX(Component.CENTER_ALIGNMENT);
        passe.setOpaque(true);
        passe.setBackground(Color.white);
        boiteTexte.add(passe);
        boiteTexte.add(Box.createRigidArea(new Dimension(0, 20)));

        // TODO : ajouter timer
        final long temps;
        if (conf.getTimer() != ConfigJeu.Timer.illimite) {
            if (conf.getTimer() == ConfigJeu.Timer.un)
                temps = 60000;
            else if (conf.getTimer() == ConfigJeu.Timer.deux)
                temps = 120000;
            else if (conf.getTimer() == ConfigJeu.Timer.trois)
                temps = 180000;
            else
                temps = 180000;

            final java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("mm : ss");
            clock = new JLabel(sdf.format(new Date(temps)), JLabel.CENTER);
            //clock.setPreferredSize(new Dimension(250, 100));
            clock.setOpaque(true);
            clock.setBackground(Color.black);
            clock.setForeground(Color.white);
            clock.setFont(new Font("Serif", Font.BOLD, 20));
            clock.setAlignmentX(Component.CENTER_ALIGNMENT);
            x = temps - 1000;
            ActionListener al = new ActionListener() {

                public void actionPerformed(ActionEvent ae) {
                    if (x <= 0) {
                        control.toucheClavier("FinTour");
                        x = temps;
                    }
                    clock.setText(sdf.format(new Date(x)));
                    x -= 1000;

                }
            };
            new javax.swing.Timer(1000, al).start();
            boiteTexte.add(clock);

        }

        boiteTexte.add(Box.createRigidArea(new Dimension(0, 10)));
        boutonFinTour.setFocusable(false);
        boutonFinTour.setAlignmentX(Component.CENTER_ALIGNMENT);
        boiteTexte.add(boutonFinTour);

        // bouton recommencer
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

        /*
         * //bouton rejouer boutonRejouer.setFocusable(false);
         * boiteTexte.add(boutonRejouer); //boiteTexte.add(Box.createRigidArea(new
         * Dimension(0, 100))); boutonRejouer.addActionListener(new ActionListener() {
         * public void actionPerformed(ActionEvent e) { interHM.setVisible(true);
         * interHM.fenetreNouvellePartie();
         * 
         * } });
         */

        // Retransmission des évènements au contrôleur
        plat.addMouseListener(new AdaptateurSouris(plat, control));
        frame.addKeyListener(new AdaptateurClavier(control));
        // Timer chrono = new Timer(dureeTour, new AdaptateurTemps(control));
        boutonFinTour.addActionListener(new AdaptateurFinTour(control));

        // Mise en place de l'interface
        boiteTexte.setPreferredSize(new Dimension(170, 600));
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

    public static void applyQualityRenderingHints(Graphics2D g2d) {

        g2d.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
        g2d.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);

    }
    
    /**
     * @param x the x to set
     */
    public static void setX(long x) {
        Plateau.x = x;
    }

    @Override
    public void miseAJour() {
        if (j.gameOver) {
            mouvements.setVisible(false);
            passe.setVisible(false);
            boutonFinTour.setVisible(false);
            // joueur.setSize(20, 20);
            joueur.setText("Victoire de " + j.joueurCourant.name + " ! ");
            boutonRecommencer.setVisible(true);
            clock.setVisible(false);
        } else {
            boutonRecommencer.setVisible(false);
            mouvements.setVisible(true);
            passe.setVisible(true);
            boutonFinTour.setVisible(true);
            joueur.setText("Joue : " + j.joueurCourant.name);
            mouvements.setText("Déplacements : " + j.joueurCourant.nbMove);
            passe.setText("Passe : " + j.joueurCourant.passeDispo);
            clock.setVisible(true);
        }

    }

}