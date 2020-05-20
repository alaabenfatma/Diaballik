package Diaballik.Vue;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

import Diaballik.Models.Jeu;

public class choisirPerso extends JFrame {

	private static final long serialVersionUID = 1L;
	JButton suivant = new JButton(">");
	JButton precedent = new JButton("<");
	JButton valider = new JButton("Valider");
	final choisirPersoPanel panel;
	int numJoueur;

	public choisirPerso(int persoJoueur) {
		numJoueur = persoJoueur;
		panel = new choisirPersoPanel(this, persoJoueur, numJoueur);
		this.setTitle("Joueur : " + persoJoueur);
		this.setSize(300, 300);
		this.setLocationRelativeTo(null);
		if (persoJoueur == 1)
			this.setLocation(this.getLocation().x - this.getWidth()/2, this.getLocation().y);
		else{
			this.setLocation(this.getLocation().x + this.getWidth()/2, this.getLocation().y);
		}
		this.setResizable(false);

		addComponentListener(new ComponentAdapter() {
			public void componentResized(ComponentEvent evt) {
				precedent.setBounds(0, 100, 45, 30);
				suivant.setBounds(240, 100, 45, 30);
				valider.setBounds(100, 230, 100, 30);
			}
		});

		valider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		precedent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (panel.persoJoueur == 2) {
					panel.persoJoueur = 1;
					repaint();
					revalidate();
				} else if (panel.persoJoueur == 1) {
					panel.persoJoueur = 4;
					repaint();
					revalidate();
				} else if (panel.persoJoueur == 3) {
					panel.persoJoueur = 2;
					repaint();
					revalidate();
				} else if (panel.persoJoueur == 4) {
					panel.persoJoueur = 3;
					repaint();
					revalidate();
				}
			}
		});

		suivant.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (panel.persoJoueur == 1) {
					panel.persoJoueur = 2;
					repaint();
					revalidate();
				} else if (panel.persoJoueur == 2) {
					panel.persoJoueur = 3;
					repaint();
					revalidate();
				} else if (panel.persoJoueur == 3) {
					panel.persoJoueur = 4;
					repaint();
					revalidate();
				} else if (panel.persoJoueur == 4) {
					panel.persoJoueur = 1;
					repaint();
					revalidate();
				}
			}
		});

		this.add(valider);
		this.add(suivant);
		this.add(precedent);
		this.add(panel);

		this.setVisible(true);
	}

}
