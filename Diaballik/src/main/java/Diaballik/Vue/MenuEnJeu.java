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
	playSound ps = new playSound();
    
    
    public MenuEnJeu(JFrame frame){
        this.setSize(700,530);
        this.setLayout(null);
        reprendre.setBounds(280,50,150,50);
        sauvegarde.setBounds(280,125,150,50);
        nouvelle.setBounds(280,200,150,50);
        charger.setBounds(280,275,150,50);
        menup.setBounds(280,350,150,50);
        quit.setBounds(280,425,150,50);
        
        this.add(reprendre);
        this.add(sauvegarde);
        this.add(nouvelle);
        this.add(charger);
        this.add(menup);
        this.add(quit);
        
        reprendre.addActionListener(this);
        sauvegarde.addActionListener(this);
        nouvelle.addActionListener(this);
        charger.addActionListener(this);
        quit.addActionListener(this);
        menup.addActionListener(this);
        this.setVisible(true);


    }

   


    public void actionPerformed(ActionEvent arg0) {
		
    	if(arg0.getSource() == quit) {
    		
        }
        if(arg0.getSource() == menup) {
        	ihm i = new ihm();
            i.retourMenuPrincipal();
        }
    }
    
}