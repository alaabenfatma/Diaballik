package Diaballik.Vue;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.stream.Stream;

import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import javax.swing.event.MouseInputListener;
import javax.swing.table.DefaultTableModel;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import Diaballik.Models.Jeu;
import Diaballik.Models.JeuJSON;

public class ChargerPartie extends JPanel {
	JFrame parent;
	ObjectMapper mapper = new ObjectMapper();
	String column[] = { "Player1", "Player2", "Date" };
	DefaultTableModel data = new DefaultTableModel(column, 0);

	public ChargerPartie(JFrame parent) {
		this.parent = parent;
		Scanner scan;
		try {
			scan = new Scanner(new FileInputStream("Diaballik/src/main/java/Diaballik/data/history.json"));
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

		JTable table = new JTable(data);

		table.setDefaultEditor(Object.class, null);

		table.setBounds(0, 0, parent.getWidth(), parent.getHeight() - 60);
		JScrollPane sp = new JScrollPane(table);
		JLabel lbl = new JLabel("Double cliques pour lancer la partie");
		lbl.setLocation(0, 0);
		this.add(lbl);
		this.add(sp);
		parent.add(this);
		table.addMouseListener(new MouseInputAdapter() {
			public void mouseClicked(MouseEvent me) {
				if (me.getClickCount() == 2) {
					JTable target = (JTable) me.getSource();
					int row = target.getSelectedRow();
					startGame(target.getValueAt(row, 2).toString());
				}
			}
		});
	}

	private void startGame(String date) {
		Scanner scan;
		try {
			scan = new Scanner(new FileInputStream("Diaballik/src/main/java/Diaballik/data/history.json"));
			while (scan.hasNextLine()) {
				String line = scan.nextLine();
				if (line.contains(date)) {
					try {
						JeuJSON j = mapper.readValue(line, JeuJSON.class);
						//We start the game here.
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