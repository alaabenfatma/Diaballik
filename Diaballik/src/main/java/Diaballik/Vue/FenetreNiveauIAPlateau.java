package Diaballik.Vue;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import Diaballik.Models.*;

public class FenetreNiveauIAPlateau {
	JFrame niveau = new JFrame();
	JPanel panel = new JPanel();
	ButtonGroup b = new ButtonGroup();
	JRadioButton br1 = new JRadioButton("Facile");
	JRadioButton br2 = new JRadioButton("Moyen");
	JRadioButton br3 = new JRadioButton("Difficile");
	JButton valider = new JButton("Valider");
	ConfigJeu conf;
	int niveau1 = 0;
	
	public FenetreNiveauIAPlateau(ConfigJeu config) {
		conf = config;
		b.add(br1);
		b.add(br2);
		b.add(br3);
		panel.add(br1);
		panel.add(br2);
		panel.add(br3);
		niveau.setSize(400, 150);
		niveau.setLocationRelativeTo(null);
		panel.setLayout(null);
		niveau.setTitle("Niveau de l'IA");
		niveau.setResizable(false);
		br1.setBounds(50, 20, 70, 30);
		br2.setBounds(150, 20, 70, 30);
		br3.setBounds(250, 20, 70, 30);
		valider.setBounds(150, 70, 100, 30);
		
		
		if (conf.getIALevel() == ConfigJeu.IALevel.facile) {
			br1.setSelected(true);
        } else if (conf.getIALevel() == ConfigJeu.IALevel.moyen) {
        	br2.setSelected(true);
        } else if (conf.getIALevel() == ConfigJeu.IALevel.difficile){
        	br3.setSelected(true);
        }
		
		
		br1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				conf.setIALevel(ConfigJeu.IALevel.facile);
				conf.setName3("IA facile");
				niveau1 = 1;
			}
		});

		br2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				conf.setIALevel(ConfigJeu.IALevel.moyen);
				conf.setName3("IA moyen");
				niveau1 = 2;
			}
		});

		br3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				conf.setIALevel(ConfigJeu.IALevel.difficile);
				conf.setName3("IA difficile");
				niveau1 = 3;
			}
		});
		
		valider.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				niveau.dispose();
			
			}
		});
		panel.add(valider);
		niveau.add(panel);
		niveau.setVisible(true);
	}
	
}
