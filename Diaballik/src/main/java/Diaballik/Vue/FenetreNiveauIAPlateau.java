package Diaballik.Vue;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import Diaballik.Models.ConfigJeu;
import Diaballik.Models.ConfigJeu.IALevel;

public class FenetreNiveauIAPlateau {
	JFrame niveau = new JFrame();
	JPanel panel = new JPanel();
	ButtonGroup b = new ButtonGroup();
	JRadioButton br1 = new JRadioButton("Facile");
	JRadioButton br2 = new JRadioButton("Moyen");
	JRadioButton br3 = new JRadioButton("Difficile");
	ConfigJeu configJeu;
	
	public FenetreNiveauIAPlateau() {
		b.add(br1);
		b.add(br2);
		b.add(br3);
		panel.add(br1);
		panel.add(br2);
		panel.add(br3);
		niveau.setSize(400, 100);
		niveau.setLocationRelativeTo(null);
		niveau.setTitle("Niveau de l'IA");
		
		br1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				configJeu.setIALevel(IALevel.facile);
				configJeu.setName3("IA facile");
			}
		});

		br2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				configJeu.setIALevel(IALevel.moyen);
				configJeu.setName3("IA moyen");
			}
		});

		br3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				configJeu.setIALevel(IALevel.difficile);
				configJeu.setName3("IA difficile");
			}
		});

		niveau.add(panel);
		niveau.setVisible(true);
	}
	
}
