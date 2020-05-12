package Diaballik.Vue;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import Diaballik.Controllers.ControleurMediateur;
import Diaballik.Models.Jeu;

public class NewGame extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JLabel titre = new JLabel("Nouvelle partie");
	JLabel duree = new JLabel("Durée d'un tour :");
	JLabel priorite = new JLabel("Joue en premier : ");
	JButton retour = new JButton("Retour");
	JButton jouer = new JButton("Jouer");
	JButton humain = new JButton("Humain");
	JButton ordinateur = new JButton("Ordinateur");
	JButton illimite = new JButton("Illimité");
	JButton uneMin = new JButton("1 min");
	JButton deuxMin = new JButton("2 min");
	JButton troisMin = new JButton("3 min");
	JButton joueur1 = new JButton("Joueur 1");
	JButton joueur2 = new JButton("Joueur 2");
	JLabel nomJoueur = new JLabel("Nom du joueur 1 : ");
	JLabel nomJoueur2 = new JLabel("Nom du joueur 2 : ");
	JLabel jouerContre = new JLabel("Jouer Contre :");
	JLabel niveauIA = new JLabel("Niveau de l'IA :");
	JTextArea name1 = new JTextArea("Joueur 1");
	JTextArea name2 = new JTextArea("Joueur 2");
	JTextArea name3 = new JTextArea("IA");
	JMenuBar mb = new JMenuBar();
	JMenu m1 = new JMenu("Thèmes");
	JMenu m2 = new JMenu("Options");
	JMenuItem mi1 = new JMenuItem("Daltonien");
	
	ButtonGroup b = new ButtonGroup();
	JRadioButton br1 = new JRadioButton("Facile");
	JRadioButton br2 = new JRadioButton("Moyen");
	JRadioButton br3 = new JRadioButton("Difficile");
	
	boolean bHumain = true;
	boolean mute = false;
	playSound ps = new playSound();
	ihm i;
	Jeu j;

	//Parametres de la nouvelle partie
	/*
	public enum Joueur {
		humain,
		ordinateur;
	}

	public enum temps {
		illimite, 0
		un, 1
		deux, 2
		trois; 3
	}

	public enum premier {
		joueurun, true
		joueurdeux; false
	}	
	*/

	int tempschrono = 0; //illimite
	boolean human = true; //joueur humain
	boolean first = true; //joueur 1


	public NewGame(ihm ihm) {
		
		i = ihm;
		j = new Jeu();
		m1.add(mi1);
		mb.add(m1);
		mb.add(m2);
		mb.setBounds(0, 0, 600, 20);
		this.add(mb);
		this.setLayout(null);
		
		 i.addComponentListener(new ComponentAdapter() {
             public void componentResized(ComponentEvent evt) {
            	jouerContre.setBounds((i.getWidth()/2) - 240, (i.getHeight()/4) - 80, 100, 100);
            	name1.setBounds((i.getWidth()/2) - 90, (i.getHeight()/4) + 50, 200, 20);
            	name2.setBounds((i.getWidth()/2) - 90, (i.getHeight()/4) + 80, 200, 20);
            	nomJoueur.setBounds((i.getWidth()/2) - 240, (i.getHeight()/4) + 50, 110, 20);
            	nomJoueur2.setBounds((i.getWidth()/2) - 240, (i.getHeight()/4) + 80, 110, 20);
            	
            	titre.setBounds((i.getWidth()/2) - 100, (i.getHeight()/4) - 120, 300, 100);
         		duree.setBounds((i.getWidth()/2) - 240, (i.getHeight()/4) + 90, 100, 100);
         		priorite.setBounds((i.getWidth()/2) - 240, (i.getHeight()/4) + 160, 150, 120);
         		
         		jouer.setBounds((i.getWidth()/2) + 20, (i.getHeight()/4) + 320, 120, 40);
         		retour.setBounds((i.getWidth()/2) - 130, (i.getHeight()/4) + 320, 120, 40);
         		
         		humain.setBounds((i.getWidth()/2) - 130, (i.getHeight()/4) - 20, 120, 40);
         		ordinateur.setBounds((i.getWidth()/2) + 20, (i.getHeight()/4) - 20, 120, 40);
         		
         		illimite.setBounds((i.getWidth()/2) - 240, (i.getHeight()/4) + 155, 100, 40);
         		uneMin.setBounds((i.getWidth()/2) - 120, (i.getHeight()/4) + 155, 100, 40);
         		deuxMin.setBounds((i.getWidth()/2), (i.getHeight()/4) + 155, 100, 40);
         		troisMin.setBounds((i.getWidth()/2) + 120, (i.getHeight()/4) + 155, 100, 40);
         		
         		joueur2.setBounds((i.getWidth()/2) + 20, (i.getHeight()/4) + 240, 120, 40);
         		joueur1.setBounds((i.getWidth()/2) - 130, (i.getHeight()/4) + 240, 120, 40);
         		
         		niveauIA.setBounds((i.getWidth()/2) - 240, (i.getHeight()/4) + 40, 110, 20);
         		br1.setBounds((i.getWidth()/2) - 100, (i.getHeight()/4) + 40, 70, 20);
         		br2.setBounds((i.getWidth()/2) - 30, (i.getHeight()/4) + 40, 70, 20);
         		br3.setBounds((i.getWidth()/2) + 40, (i.getHeight()/4) + 40, 70, 20);
             }
     });

		Font font = new Font("Arial",Font.BOLD,30);
		titre.setFont(font);
		humain.setBackground(Color.pink);
		illimite.setBackground(Color.pink);
		joueur1.setBackground(Color.pink);
		
		name1.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void changedUpdate(DocumentEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void insertUpdate(DocumentEvent arg0) {
				// TODO Auto-generated method stub
				joueur1.setText(name1.getText());
			}

			@Override
			public void removeUpdate(DocumentEvent arg0) {
				// TODO Auto-generated method stub
			}
			   
			});
		
		name2.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void changedUpdate(DocumentEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void insertUpdate(DocumentEvent arg0) {
				// TODO Auto-generated method stub
				joueur2.setText(name2.getText());
			}

			@Override
			public void removeUpdate(DocumentEvent arg0) {
				// TODO Auto-generated method stub
			}
			   
			});
		
		
		retour.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) { 
            	i.retourMenuPrincipal();
            } 
        } );
		
		humain.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) { 
            	humain.setBackground(Color.pink);
				ordinateur.setBackground(null);	
				j.IA = false;
				name2.setText("Joueur 2");
				
				
				if (bHumain == false) {
					i.setSize(i.getWidth(), i.getHeight());
					name1.setBounds((i.getWidth()/2) - 90, (i.getHeight()/4) + 50, 200, 20);
	            	name2.setBounds((i.getWidth()/2) - 90, (i.getHeight()/4) + 80, 200, 20);
	            	nomJoueur.setBounds((i.getWidth()/2) - 240, (i.getHeight()/4) + 50, 110, 20);
	            	nomJoueur2.setBounds((i.getWidth()/2) - 240, (i.getHeight()/4) + 80, 110, 20);
					bHumain = true;
				}
				niveauIA.setVisible(false);
				br1.setVisible(false);
				br2.setVisible(false);
				br3.setVisible(false);
            } 
        } );
		
		ordinateur.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) { 
            	ordinateur.setBackground(Color.pink);
				humain.setBackground(null);
				human = false; //joueur IA
				j.IA = true;
				
				name2.setText("IA");
				
				if (bHumain == true) {
					i.setSize(i.getWidth(), i.getHeight());
					name1.setBounds((i.getWidth()/2) - 90, (i.getHeight()/4) + 70, 200, 20);
	            	name2.setBounds((i.getWidth()/2) - 90, (i.getHeight()/4) + 100, 200, 20);
	            	nomJoueur.setBounds((i.getWidth()/2) - 240, (i.getHeight()/4) + 70, 110, 20);
	            	nomJoueur2.setBounds((i.getWidth()/2) - 240, (i.getHeight()/4) + 100, 110, 20);
					bHumain = false;
				}
				
				niveauIA.setVisible(true);
				br1.setVisible(true);
				br2.setVisible(true);
				br3.setVisible(true);
            } 
        } );
		
		illimite.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) { 
            	illimite.setBackground(Color.pink);
				uneMin.setBackground(null);
				deuxMin.setBackground(null);
				troisMin.setBackground(null);
            } 
        } );
		
		uneMin.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) { 
            	uneMin.setBackground(Color.pink);
				illimite.setBackground(null);
				deuxMin.setBackground(null);
				troisMin.setBackground(null);
				tempschrono = 1;
			} 
        } );
		
		deuxMin.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) { 
            	deuxMin.setBackground(Color.pink);
				illimite.setBackground(null);
				uneMin.setBackground(null);
				troisMin.setBackground(null);
				tempschrono = 2;
            } 
        } );
		
		troisMin.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) { 
            	troisMin.setBackground(Color.pink);
				illimite.setBackground(null);
				deuxMin.setBackground(null);
				uneMin.setBackground(null);
				tempschrono = 3;
            } 
        } );
		
		joueur1.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) { 
            	joueur1.setBackground(Color.pink);
				joueur2.setBackground(null);
            } 
        } );
		
		joueur2.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) { 
            	joueur2.setBackground(Color.pink);
				joueur1.setBackground(null);
				first = false; //joueur 2
            } 
        } );
		
		jouer.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) { 
				ps.play("son/buttonClick.wav", mute);
				j.start();
				//SwingUtilities.getWindowAncestor(this).dispose();
				//super.setVisible(false);
				CollecteurEvenements control = new ControleurMediateur(j);
				Plateau.demarrer(j,control);
				i.setVisible(false);
				
            } 
        } );
		
		b.add(br1);
		b.add(br2);
		b.add(br3);
		this.add(niveauIA);
		niveauIA.setVisible(false);
		this.add(br1);
		br1.setSelected(true);
		br1.setVisible(false);
		this.add(br2);
		br2.setVisible(false);
		this.add(br3);
		br3.setVisible(false);
		this.add(jouerContre);
		this.add(nomJoueur);
		this.add(nomJoueur2);
		this.add(name1);
		this.add(name2);
		this.add(joueur1);
		this.add(joueur2);
		this.add(priorite);
		this.add(illimite);
		this.add(uneMin);
		this.add(deuxMin);
		this.add(troisMin);
		this.add(duree);
		this.add(ordinateur);
		this.add(humain);
		this.add(jouer);
		this.add(retour);
		this.add(titre);
		this.setVisible(true);
	}
	
}
