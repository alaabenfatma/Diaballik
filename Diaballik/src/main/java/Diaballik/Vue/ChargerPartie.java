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
	String column[] = { "Player1", "Player2", "Date" };
	DefaultTableModel data = new DefaultTableModel(column, 0);
	JButton retour = new JButton("Retour");
	JLabel lbl = new JLabel("Double cliques pour lancer la partie");
	Scanner scan;
	JTable table = new JTable(data);
	JScrollPane sp = new JScrollPane(table);
	ihm i;

	public ChargerPartie(ihm ihm) {
		i = ihm;
		table.setDefaultEditor(Object.class, null);

		i.addComponentListener(new ComponentAdapter() {
			public void componentResized(ComponentEvent evt) {
				titre.setBounds((i.getWidth() / 2) - 100, (i.getHeight() / 6) - 110, 300, 100);
				retour.setBounds((i.getWidth() / 2) - 130, (i.getHeight() / 4) + 320, 120, 40);
				table.setBounds(10, 90, i.getWidth()/2 - 20, i.getHeight()/2);
				sp.setBounds(10, 70, i.getWidth()/2 - 20, i.getHeight()/2);
				lbl.setLocation(0, 0);
				lbl.setVisible(false);
			}
		});
		
		try {
			scan = new Scanner(new FileInputStream(this.getClass().getResource("../data/history.json").getFile()));
			while (scan.hasNextLine()) {
				String line = scan.nextLine();
				try {
					JeuJSON j = mapper.readValue(line, JeuJSON.class);
					Object[] innerData = { j.player1, j.player2, j.CreationDate };
					data.addRow(innerData);
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
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		retour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				i.retourMenuPrincipal();
			}
		});

		table.addMouseListener(new MouseInputAdapter() {
			public void mouseClicked(MouseEvent me) {
				if (me.getClickCount() == 2) {
					JTable target = (JTable) me.getSource();
					int row = target.getSelectedRow();
					startGame(target.getValueAt(row, 2).toString());
				}
			}
		});
		
		Font font = new Font("Arial", Font.BOLD, 30);
		titre.setFont(font);
		this.add(lbl);
		this.add(sp);
		this.add(retour);
		this.add(titre);
	
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