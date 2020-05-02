package Diaballik.Vue;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class JouerReseau extends JPanel implements ActionListener{
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
		
		this.add(creer);
		this.add(rejoindre);
		this.add(menuPrincipal);
		this.add(titre);
		creer.addActionListener(this);
		rejoindre.addActionListener(this);
		menuPrincipal.addActionListener(this);
		
		this.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == creer) {
	 		i.fenetreCreerPartieReseau();
		}
		
		if (arg0.getSource() == rejoindre) {
		
		}
		if (arg0.getSource() == menuPrincipal) {
			i.retourMenuPrincipal();
		}
	}
}
