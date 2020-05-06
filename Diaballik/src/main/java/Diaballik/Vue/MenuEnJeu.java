package Diaballik.Vue;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import Diaballik.Controllers.ControleurMediateur;
import Diaballik.Models.Jeu;

public class MenuEnJeu extends JPanel implements ActionListener{
    JButton reprendre = new JButton("Reprendre");
	JButton sauvegarde = new JButton("Sauvegarder");
	JButton nouvelle = new JButton("Nouvelle Partie");
	JButton charger = new JButton("Charger");
	JButton menup = new JButton("Menu principal");
    JButton quit = new JButton("Quitter");

    public MenuEnJeu(JFrame frame){
        this.setSize(700,530);
        this.setLayout(null);
        reprendre.setBounds(350,150,120,40);
        sauvegarde.setBounds(350,250,120,40);
        nouvelle.setBounds(350,350,120,40);
        charger.setBounds(350,450,120,40);
        menup.setBounds(350,550,120,40);
        quit.setBounds(350,650,120,40);
        this.add(reprendre);
        this.add(sauvegarde);
        this.add(nouvelle);
        this.add(charger);
        this.add(menup);
        this.add(quit);

        this.setVisible(true);


    }

   


    public void actionPerformed(ActionEvent arg0) {
        if(arg0.getSource() == reprendre) {
            
        }
    }
    
}