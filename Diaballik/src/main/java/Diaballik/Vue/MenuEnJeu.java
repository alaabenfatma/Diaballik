package Diaballik.Vue;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class MenuEnJeu extends JPanel {

    private static final long serialVersionUID = 1L;
    JButton reprendre = new JButton("Reprendre");
    JButton sauvegarde = new JButton("Sauvegarder");
    JButton nouvelle = new JButton("Nouvelle Partie");
    JButton charger = new JButton("Charger");
    JButton menup = new JButton("Menu principal");
    JButton quit = new JButton("Quitter");
    boolean mute = false;
    playSound ps = new playSound();

    public MenuEnJeu(final JFrame frame, final JFrame plateau) {
        frame.setSize(600, 530);
        frame.setTitle("Menu");
        this.setLayout(null);
        reprendre.setBounds(220, 20, 150, 50);
        sauvegarde.setBounds(220, 95, 150, 50);
        nouvelle.setBounds(220, 170, 150, 50);
        charger.setBounds(220, 245, 150, 50);
        menup.setBounds(220, 320, 150, 50);
        quit.setBounds(220, 395, 150, 50);

        this.add(reprendre);
        this.add(sauvegarde);
        this.add(nouvelle);
        this.add(charger);
        this.add(menup);
        this.add(quit);

        frame.setLocationRelativeTo(null);
        this.setVisible(true);

        reprendre.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (Plateau.timer != null)
                    Plateau.timer.start();
                plateau.setVisible(true);
                frame.setVisible(false);
            }
        });

        sauvegarde.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });

        nouvelle.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (Plateau.timer != null)
                    Plateau.timer.restart();
                frame.setVisible(false);
                plateau.setVisible(false);
                ihm i = new ihm();
                NewGame m = new NewGame(i);
                i.setSize(601, 550);
                i.setContentPane(m);
                i.repaint();
                i.revalidate();
            }
        });

        charger.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                ihm i = new ihm();
                ChargerPartie m = new ChargerPartie(i, true);
                i.setSize(800, 550);
                i.setContentPane(m);
                i.repaint();
                i.revalidate();
            }
        });

        menup.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                plateau.setVisible(false);
                ihm i = new ihm();
                i.retourMenuPrincipal();
            }
        });

        quit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ps.play("son/buttonClick.wav", mute);
                msgBox.MessageBox("Voulez-vous quitter le jeu ? ", "Quitter", frame);
            }
        });

    }

}