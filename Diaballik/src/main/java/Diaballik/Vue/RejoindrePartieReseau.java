package Diaballik.Vue;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class RejoindrePartieReseau extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JLabel titre = new JLabel("Rejoindre une partie");
	JLabel nomJoueur = new JLabel("Nom du joueur");
	JLabel codelabel = new JLabel("Code");
	JTextArea name = new JTextArea("Joueur 2");
	JTextArea code = new JTextArea("copier coller");
	JButton ok = new JButton("Ok");
	JButton retour = new JButton("Retour");
	JLabel erreur = new JLabel("Entrez un nom de joueur");
	JLabel codeerreur = new JLabel("Entrez un code");
	ihm i;
	
	public RejoindrePartieReseau(ihm ihm) {
		i = ihm;
		this.setLayout(null);
		
		i.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent evt) {
            
            	titre.setBounds((i.getWidth()/2) - 140, (i.getHeight()/4) - 100, 300, 100);
        		name.setBounds((i.getWidth()/2) - 90, (i.getHeight()/4) + 40, 200, 20);
            	nomJoueur.setBounds((i.getWidth()/2) - 240, (i.getHeight()/4) + 40, 200, 20);
            	codelabel.setBounds((i.getWidth()/2) - 240, (i.getHeight()/4) + 70, 200, 20);
            	code.setBounds((i.getWidth()/2) - 90, (i.getHeight()/4) + 70, 200, 20);
            	erreur.setBounds((i.getWidth()/2) - 120, (i.getHeight()/4) + 120, 300, 50);
            	codeerreur.setBounds((i.getWidth()/2) - 80, (i.getHeight()/4) + 150, 300, 50);
        		ok.setBounds((i.getWidth()/2) + 20, (i.getHeight()/4) + 200, 120, 40);
         		retour.setBounds((i.getWidth()/2) - 130, (i.getHeight()/4) + 200, 120, 40);
         		i.sound.setBounds(i.getWidth() - 80, 75, 40, 40);
         		
            }
		});
		
		this.add(nomJoueur);
		this.add(name);
		this.add(codelabel);
		this.add(code);
		this.add(ok);
		this.add(retour);
		this.add(titre);
		this.add(erreur);
		this.add(codeerreur);
		erreur.setVisible(false);
		codeerreur.setVisible(false);
		
		Font fonttitre = new Font("Arial",Font.BOLD,30);
		Font fontnomJoueur = new Font("Arial",Font.BOLD,15);
		Font fonterreur = new Font("Arial",Font.BOLD,20);
		codeerreur.setForeground(Color.red);
		codeerreur.setFont(fonterreur);
		erreur.setForeground(Color.red);
		erreur.setFont(fonterreur);
		nomJoueur.setFont(fontnomJoueur);
		codelabel.setFont(fontnomJoueur);
		titre.setFont(fonttitre);
		
		name.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void changedUpdate(DocumentEvent arg0) {
				// TODO Auto-generated method stub
			}

			@Override
			public void insertUpdate(DocumentEvent arg0) {
				// TODO Auto-generated method stub
				if (name.getText() != "" || code.getText() != "") {
					erreur.setVisible(false);
					if (code.getText() != "" || code.getText() != " ") {
						ok.setEnabled(true);
					} else {
						ok.setEnabled(false);
					}
				}
			}

			@Override
			public void removeUpdate(DocumentEvent arg0) {
				// TODO Auto-generated method stub
				if (name.getText().equals("") || name.getText().equals(" ")) {
					erreur.setVisible(true);
					ok.setEnabled(false);
					if (code.getText().equals("") || code.getText().equals(" ")) {
						ok.setEnabled(false);
					} 
				} 
			}
		});
		
		
		code.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void changedUpdate(DocumentEvent arg0) {
				// TODO Auto-generated method stub
			}

			@Override
			public void insertUpdate(DocumentEvent arg0) {
				// TODO Auto-generated method stub
				if (code.getText() != "" || code.getText() != " ") {
					codeerreur.setVisible(false);
					if (name.getText() != "" && name.getText() != " ") {
						ok.setEnabled(true);
					} else {
						ok.setEnabled(false);
					}
					
					
				}
			}

			@Override
			public void removeUpdate(DocumentEvent arg0) {
				// TODO Auto-generated method stub
				if (code.getText().equals("") || code.getText().equals(" ")) {
					codeerreur.setVisible(true);
					ok.setEnabled(false);
					if (name.getText().equals("") || name.getText().equals(" ")) {
						ok.setEnabled(false);
					} 
				} 
			}
			});
		
		
		ok.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) { 
            } 
        } );
		
		
		retour.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) { 
            	i.fenetreJouerEnReseau();
            } 
        } );
		
		this.setVisible(true);
	}

}
