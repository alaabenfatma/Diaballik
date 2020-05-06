package Diaballik.Vue;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class JouerReseau extends JPanel {
	
	JLabel titre = new JLabel("Jouer en réseau");
	JButton creer = new JButton("Créer une partie");
	JButton rejoindre = new JButton("Rejoindre une partie");
	JButton menuPrincipal = new JButton("Menu principal");
	ihm i;
	
	
	public JouerReseau(ihm ihm) {
		i = ihm;
		this.setLayout(null);
		titre.setBounds(240, 0, 300, 100);
		creer.setBounds(270, 150, 150, 50);
		rejoindre.setBounds(270, 220, 150, 50);
		menuPrincipal.setBounds(270, 290, 150, 50);
		
		Font font = new Font("Arial",Font.BOLD,30);
		titre.setFont(font);
		
		creer.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) { 
            	i.fenetreCreerPartieReseau();
            } 
        } );
		
		rejoindre.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) { 
            	i.fenetreRejoindrePartieReseau();
            } 
        } );
		
		menuPrincipal.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) { 
            	i.retourMenuPrincipal();
            } 
        } );
		
		this.add(creer);
		this.add(rejoindre);
		this.add(menuPrincipal);
		this.add(titre);
		this.setVisible(true);
	}

}
