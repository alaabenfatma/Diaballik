package Diaballik.Vue;

import java.awt.Color;
import java.awt.Font;

import java.awt.event.*;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import com.fasterxml.jackson.databind.ObjectMapper;

import Diaballik.Controllers.ControleurMediateur;
import Diaballik.Models.ConfigJeu;
import Diaballik.Models.Jeu;
import Diaballik.Models.ConfigJeu.IALevel;
import Diaballik.Models.ConfigJeu.Mode;
import Diaballik.Models.ConfigJeu.Timer;

public class NewGame extends JPanel {
	
	private static final long serialVersionUID = 1L;
	ObjectMapper objectMapper = new ObjectMapper();
	JLabel titre = new JLabel("Nouvelle partie");
	JLabel duree = new JLabel("Durée d'un tour :");
	JLabel priorite = new JLabel("Joue en premier : ");
	JButton retour = new JButton("Retour");
	JButton jouer = new JButton("Jouer");
	JButton humain = new JButton("Humain");
	JButton ordinateur = new JButton("Ordinateur");
	JButton illimite = new JButton("Illimité");
	JButton uneMin = new JButton("10 sec");
	JButton deuxMin = new JButton("30 sec");
	JButton troisMin = new JButton("1 min");
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
	JCheckBox varianteCheckbox = new JCheckBox("Variante");

	ButtonGroup b = new ButtonGroup();
	JRadioButton br1 = new JRadioButton("Facile");
	JRadioButton br2 = new JRadioButton("Moyen");
	JRadioButton br3 = new JRadioButton("Difficile");
	boolean bHumain = true;
	int persoJoueur;
	playSound ps = new playSound();
	ihm i;
	Jeu j;
	ConfigJeu configJeu;
	
	JButton choixPersoJoueur1 = new JButton("Personnaliser");
	JButton choixPersoJoueur2 = new JButton("Personnaliser");
	JButton choisirTerrain = new JButton("Terrain par défaut");
	JLabel choisirTerrainlabel = new JLabel("Choisir terrain : ");

	// Parametres de la nouvelle partie
	/*
	 * public enum Joueur { humain, ordinateur; }
	 * 
	 * public enum temps { illimite, 0 un, 1 deux, 2 trois; 3 }
	 * 
	 * public enum premier { joueurun, true joueurdeux; false }
	 */

	int tempschrono = 0; // illimite
	boolean human = true; // joueur humain
	boolean first = true; // joueur 1

	public NewGame(ihm ihm) {

		i = ihm;
		j = new Jeu();
		configJeu = new ConfigJeu();
		m1.add(mi1);
		mb.add(m1);
		mb.add(m2);
		mb.setBounds(0, 0, 600, 20);
		i.setLocationRelativeTo(null);
		this.add(mb);
		this.setLayout(null);
	
		i.addComponentListener(new ComponentAdapter() {
			public void componentResized(ComponentEvent evt) {
				jouerContre.setBounds((i.getWidth() / 2) - 240, (i.getHeight() / 4) - 80, 100, 100);
				name1.setBounds((i.getWidth() / 2) - 90, (i.getHeight() / 4) + 50, 200, 20);
				name2.setBounds((i.getWidth() / 2) - 90, (i.getHeight() / 4) + 80, 200, 20);
				nomJoueur.setBounds((i.getWidth() / 2) - 240, (i.getHeight() / 4) + 50, 110, 20);
				nomJoueur2.setBounds((i.getWidth() / 2) - 240, (i.getHeight() / 4) + 80, 110, 20);
				
				choixPersoJoueur1.setBounds((i.getWidth() / 2) + 120, (i.getHeight() / 4) + 50, 130, 20);
				choixPersoJoueur2.setBounds((i.getWidth() / 2) + 120, (i.getHeight() / 4) + 80, 130, 20);
				choisirTerrainlabel.setBounds((i.getWidth() / 2) - 240, (i.getHeight() / 4) + 120, 120, 30);
				choisirTerrain.setBounds((i.getWidth() / 2) - 90, (i.getHeight() / 4) + 120, 200, 30);
				
				titre.setBounds((i.getWidth() / 2) - 100, (i.getHeight() / 4) - 120, 300, 100);
				duree.setBounds((i.getWidth() / 2) - 240, (i.getHeight() / 4) + 120, 100, 100);
				priorite.setBounds((i.getWidth() / 2) - 240, (i.getHeight() / 4) + 190, 150, 120);

				jouer.setBounds((i.getWidth() / 2) + 20, (i.getHeight() / 4) + 350, 120, 40);
				retour.setBounds((i.getWidth() / 2) - 130, (i.getHeight() / 4) + 350, 120, 40);

				humain.setBounds((i.getWidth() / 2) - 130, (i.getHeight() / 4) - 20, 120, 40);
				ordinateur.setBounds((i.getWidth() / 2) + 20, (i.getHeight() / 4) - 20, 120, 40);

				illimite.setBounds((i.getWidth() / 2) - 240, (i.getHeight() / 4) + 185, 100, 40);
				uneMin.setBounds((i.getWidth() / 2) - 120, (i.getHeight() / 4) + 185, 100, 40);
				deuxMin.setBounds((i.getWidth() / 2), (i.getHeight() / 4) + 185, 100, 40);
				troisMin.setBounds((i.getWidth() / 2) + 120, (i.getHeight() / 4) + 185, 100, 40);

				joueur2.setBounds((i.getWidth() / 2) + 20, (i.getHeight() / 4) + 270, 120, 40);
				joueur1.setBounds((i.getWidth() / 2) - 130, (i.getHeight() / 4) + 270, 120, 40);

				niveauIA.setBounds((i.getWidth() / 2) - 240, (i.getHeight() / 4) + 40, 110, 20);
				br1.setBounds((i.getWidth() / 2) - 100, (i.getHeight() / 4) + 40, 70, 20);
				br2.setBounds((i.getWidth() / 2) - 30, (i.getHeight() / 4) + 40, 70, 20);
				br3.setBounds((i.getWidth() / 2) + 40, (i.getHeight() / 4) + 40, 70, 20);
				i.sound.setBounds(i.getWidth() - 80, 75, 40, 40);
				varianteCheckbox.setBounds((i.getWidth() / 2) + 150, (i.getHeight() / 4) + 125, 100, 20);
			}
		});

		Font font = new Font("Arial", Font.BOLD, 30);
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
				configJeu.setName1(name1.getText());
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
				configJeu.setName2(name2.getText());
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
		});

		humain.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				humain.setBackground(Color.pink);
				ordinateur.setBackground(null);
				// j.IA = false;
				configJeu.setMode(Mode.humain);
				name2.setText("Joueur 2");

				if (bHumain == false) {
					i.setSize(i.getWidth(), i.getHeight());
					name1.setBounds((i.getWidth() / 2) - 90, (i.getHeight() / 4) + 50, 200, 20);
					name2.setBounds((i.getWidth() / 2) - 90, (i.getHeight() / 4) + 80, 200, 20);
					nomJoueur.setBounds((i.getWidth() / 2) - 240, (i.getHeight() / 4) + 50, 110, 20);
					nomJoueur2.setBounds((i.getWidth() / 2) - 240, (i.getHeight() / 4) + 80, 110, 20);
					choixPersoJoueur1.setBounds((i.getWidth() / 2) + 120, (i.getHeight() / 4) + 50, 130, 20);
					choixPersoJoueur2.setBounds((i.getWidth() / 2) + 120, (i.getHeight() / 4) + 80, 130, 20);
					choisirTerrainlabel.setBounds((i.getWidth() / 2) - 240, (i.getHeight() / 4) + 120, 120, 30);
					choisirTerrain.setBounds((i.getWidth() / 2) - 90, (i.getHeight() / 4) + 120, 200, 30);
					varianteCheckbox.setBounds((i.getWidth() / 2) + 150, (i.getHeight() / 4) + 125, 100, 20);
					
					bHumain = true;
				}
				niveauIA.setVisible(false);
				br1.setVisible(false);
				br2.setVisible(false);
				br3.setVisible(false);
			}
		});

		ordinateur.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ordinateur.setBackground(Color.pink);
				humain.setBackground(null);
				human = false; // joueur IA
				// j.IA = true;
				configJeu.setMode(Mode.ordinateur);

				name2.setText("IA");

				if (bHumain == true) {
					i.setSize(i.getWidth(), i.getHeight());
					name1.setBounds((i.getWidth() / 2) - 90, (i.getHeight() / 4) + 70, 200, 20);
					name2.setBounds((i.getWidth() / 2) - 90, (i.getHeight() / 4) + 100, 200, 20);
					nomJoueur.setBounds((i.getWidth() / 2) - 240, (i.getHeight() / 4) + 70, 110, 20);
					nomJoueur2.setBounds((i.getWidth() / 2) - 240, (i.getHeight() / 4) + 100, 110, 20);
					choixPersoJoueur1.setBounds((i.getWidth() / 2) + 120, (i.getHeight() / 4) + 70, 130, 20);
					choixPersoJoueur2.setBounds((i.getWidth() / 2) + 120, (i.getHeight() / 4) + 100, 130, 20);
					choisirTerrainlabel.setBounds((i.getWidth() / 2) - 240, (i.getHeight() / 4) + 130, 120, 30);
					choisirTerrain.setBounds((i.getWidth() / 2) - 90, (i.getHeight() / 4) + 130, 200, 30);
					varianteCheckbox.setBounds((i.getWidth() / 2) + 150, (i.getHeight() / 4) + 135, 100, 20);
					
					bHumain = false;
				}

				niveauIA.setVisible(true);
				br1.setVisible(true);
				br2.setVisible(true);
				br3.setVisible(true);
			}
		});

		illimite.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				illimite.setBackground(Color.pink);
				uneMin.setBackground(null);
				deuxMin.setBackground(null);
				troisMin.setBackground(null);
				configJeu.setTimer(Timer.illimite);
			}
		});

		uneMin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				uneMin.setBackground(Color.pink);
				illimite.setBackground(null);
				deuxMin.setBackground(null);
				troisMin.setBackground(null);
				tempschrono = 1;
				configJeu.setTimer(Timer.un);
			}
		});

		deuxMin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deuxMin.setBackground(Color.pink);
				illimite.setBackground(null);
				uneMin.setBackground(null);
				troisMin.setBackground(null);
				tempschrono = 2;
				configJeu.setTimer(Timer.deux);
			}
		});

		troisMin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				troisMin.setBackground(Color.pink);
				illimite.setBackground(null);
				deuxMin.setBackground(null);
				uneMin.setBackground(null);
				tempschrono = 3;
				configJeu.setTimer(Timer.trois);
			}
		});

		joueur1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				joueur1.setBackground(Color.pink);
				joueur2.setBackground(null);
				configJeu.setP1First(true);
			}
		});

		joueur2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				joueur2.setBackground(Color.pink);
				joueur1.setBackground(null);
				first = false; // joueur 2
				configJeu.setP1First(false);
			}
		});

		br1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				configJeu.setIALevel(IALevel.facile);

			}
		});

		br2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				configJeu.setIALevel(IALevel.moyen);

			}
		});

		br3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				configJeu.setIALevel(IALevel.difficile);

			}
		});

		varianteCheckbox.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					configJeu.setVariante(true);
				} else {
					configJeu.setVariante(false);
				}
				;
			}
		});

		jouer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ps.play("son/buttonClick.wav",  staticConfig.bmute);
				j.configurer(configJeu);
				j.start();
				// SwingUtilities.getWindowAncestor(this).dispose();
				// super.setVisible(false);
				CollecteurEvenements control = new ControleurMediateur(j);
				Plateau.setIHM(i);
				Plateau.demarrer(j, control, configJeu);
				i.setVisible(false);

			}
		});
		
		choixPersoJoueur1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				persoJoueur = 1;
				new choisirPerso(persoJoueur);
				
			}
		});
		
		choixPersoJoueur2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				persoJoueur = 2;
				new choisirPerso(persoJoueur);
				
			}
		});
		
		
		choisirTerrain.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new choisirTerrain();
				
			}
		});
		
		
		String sInfo = "Mode de jeu alternatif avec 2 pions dans le camps adverse au départ";
        varianteCheckbox.setToolTipText(sInfo);

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
		this.add(varianteCheckbox);
		this.add(choixPersoJoueur1);
		this.add(choixPersoJoueur2);
		this.add(choisirTerrainlabel);
		this.add(choisirTerrain);
		this.setVisible(true);
	}

}
