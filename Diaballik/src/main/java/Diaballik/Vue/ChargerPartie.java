package Diaballik.Vue;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

public class ChargerPartie extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JLabel titre = new JLabel("Charger partie");
	JLabel save = new JLabel("Sauvegardes");
	JButton retour = new JButton("Retour");
	JButton jouer = new JButton("Jouer");
	
	ihm i;

	/*Pour la date, il faut recuperer la date de l'os
	 * et pour il faut rendre chaque ligne cliquable 
	 * Il faut enregistrer une image à chaque fois qu'on
	 * sauvegarde pour pouvoir faire l'apercu
	 * et enfin il faut pouvoir ajouter une fonction qui
	 * ajoute un ligne dans le tableau lorqu'on enregistre une partie
	 * et un bouton pour supprimer qq parties*/

	String[] entetes = {"Joueur 1", "Joueur 2", "Date"};
	public ChargerPartie(ihm ihm) {
		i = ihm;
		DefaultTableModel dm = new DefaultTableModel();
		dm.setDataVector(new Object[][] { { "button 1", "Alaa", "Wassim", "10/05/2020" },
			{ "button 2", "Yohan", "AI", "15/05/2020" } }, new Object[] { "Button", "String","String","String" });
	
		JTable table = new JTable(dm);
		table.getColumn("Button").setCellRenderer(new ButtonRenderer());
		table.getColumn("Button").setCellEditor(
			new ButtonEditor(new JCheckBox()));
		JScrollPane scroll = new JScrollPane(table);
		i.getContentPane().add(scroll);
		setSize(400, 100);
		this.setLayout(null);
		titre.setBounds(240, 0, 300, 100);
		save.setBounds(150, 90, 100, 20);
		jouer.setBounds(350, 390, 120, 40);
		retour.setBounds(210, 390, 120, 40);
		
		Font font = new Font("Arial",Font.BOLD,30);
		titre.setFont(font);
		
		jouer.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) { 
            } 
        } );
		
		retour.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) { 
				i.retourMenuPrincipal();
	
            } 
        } );
		
		this.add(jouer);
		this.add(retour);
		this.add(save);
		this.add(titre);
		this.add(table);
		this.setVisible(true);

	}
	
}
