package Diaballik.Vue;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import javax.swing.table.DefaultTableModel;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import Diaballik.Controllers.ControleurMediateur;
import Diaballik.Models.ConfigJeu;
import Diaballik.Models.Jeu;
import Diaballik.Models.JeuJSON;
import Diaballik.Models.PieceType;

public class ChargerPartie extends JPanel {

	private static final long serialVersionUID = 1L;
	JLabel titre = new JLabel("Charger partie");
	ObjectMapper mapper = new ObjectMapper();
	String column[] = { "Joueur 1", "Joueur 2", "Date" };
	DefaultTableModel data = new DefaultTableModel(column, 0);
	JButton retour = new JButton("Retour");
	JButton jouer = new JButton("Jouer");
	Scanner scan;
	JTable table = new JTable(data);
	JScrollPane sp = new JScrollPane(table);
	JPanel sample = new JPanel();
	ihm i;

	public ChargerPartie(ihm ihm, final boolean menu) {
		i = ihm;
		table.setDefaultEditor(Object.class, null);

		i.addComponentListener(new ComponentAdapter() {
			public void componentResized(ComponentEvent evt) {
				table.setBounds(10, 90, i.getWidth() / 2 - 20, i.getHeight() / 2);
				titre.setBounds((i.getWidth() / 2) - 100, (i.getHeight() / 6) - 90, 300, 100);
				retour.setBounds((i.getWidth() / 2) - 130, table.getHeight() + 120, 120, 40);
				jouer.setBounds((i.getWidth() / 2) + 20, table.getHeight() + 120, 120, 40);
				sp.setBounds(10, 70, i.getWidth() / 2 - 20, i.getHeight() / 2);
				sample.setBounds(table.getWidth() + 20, 70, i.getWidth() / 2 - 90, i.getHeight() / 2);
			}
		});
		
		this.setSize(600, 600);
		try {
			scan = new Scanner(new FileInputStream(this.getClass().getResource("../data/history.json").getFile()));
			while (scan.hasNextLine()) {
				String line = scan.nextLine();
				try {
					JeuJSON j = mapper.readValue(line, JeuJSON.class);
					Object[] innerData = { j.player1, j.player2, j.CreationDate };
					data.addRow(innerData);
				} catch (JsonParseException e) {
					e.printStackTrace();
				} catch (JsonMappingException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		retour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (menu == false) {
					i.retourMenuPrincipal();
				} else {
					MenuEnJeu m = new MenuEnJeu(i, i);
					i.setContentPane(m);
					i.repaint();
					i.revalidate();
				}
			}
		});

		table.addMouseListener(new MouseInputAdapter() {
			public void mouseClicked(final MouseEvent me) {
				JTable target = (JTable) me.getSource();
				int row = target.getSelectedRow();
				if (me.getClickCount() == 1) {
					showSample(target.getValueAt(row, 2).toString());
				}
				if (me.getClickCount() == 2) {
					startGame(target.getValueAt(row, 2).toString());
				}
			}
		});
		
		jouer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				final int row = table.getSelectedRow();
				startGame(table.getValueAt(row, 2).toString());
			}
		});
		
		Font font = new Font("Arial", Font.BOLD, 30);
		titre.setFont(font);
		this.add(sp);
		this.add(retour);
		this.add(jouer);
		this.add(titre);
		this.add(sample);
	}

	private void showSample(String date) {
		Scanner scan;
		try {
			scan = new Scanner(new FileInputStream(this.getClass().getResource("../data/history.json").getFile()));
			while (scan.hasNextLine()) {
				String line = scan.nextLine();
				if (line.contains(date)) {
					try {
						JeuJSON j = mapper.readValue(line, JeuJSON.class);
						Jeu realJeu = new Jeu();
						ConfigJeu cfg = new ConfigJeu();
						realJeu.configurer(cfg);
						realJeu.tr._terrain = realJeu.tr.toPieces(j.Terrain);
						realJeu.start();
						PlateauGraphique plat;
						plat = new VuePlateau(realJeu);
						plat.setSize(sample.getSize());
						sample.remove(sample.findComponentAt(0, 0));
						sample.add(plat);
						plat.repaint();
						plat.setVisible(true);
					} catch (JsonParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (JsonMappingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void startGame(String date) {
		Scanner scan;
		try {
			scan = new Scanner(new FileInputStream(this.getClass().getResource("../data/history.json").getFile()));
			while (scan.hasNextLine()) {
				String line = scan.nextLine();
				if (line.contains(date)) {
					try {
						JeuJSON j = mapper.readValue(line, JeuJSON.class);
						Jeu realJeu = new Jeu();

						ConfigJeu cfg = new ConfigJeu();
						cfg.setName1(j.config.getName1());
						cfg.setName2(j.config.getName2());
						cfg.setMode(j.config.getMode());
						cfg.setP1First(j.joueurCourant.couleur == PieceType.White);
						cfg.setVariante(j.config.getVariante());
						cfg.setTimer(j.config.getTimer());
						cfg.setIALevel(j.config.getIALevel());
						CollecteurEvenements control = new ControleurMediateur(realJeu);

						realJeu.configurer(cfg);
						realJeu.tr._terrain = realJeu.tr.toPieces(j.Terrain);
						realJeu.start();
						realJeu.joueurCourant.couleur = j.joueurCourant.couleur;
						realJeu.joueurCourant.n = j.joueurCourant.n;
						realJeu.joueurCourant.name = j.joueurCourant.name;
						realJeu.joueurCourant.nbMove = j.joueurCourant.nbMove;
						realJeu.joueurCourant.passeDispo = j.joueurCourant.passeDispo;
						Plateau.demarrer(realJeu, control, cfg);

					} catch (JsonParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (JsonMappingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}