package Diaballik.Vue;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class CreerPartieReseau extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JLabel titre = new JLabel("Créer une partie");
	JLabel nomJoueur = new JLabel("Nom du joueur");
	JLabel erreur = new JLabel("Entrez un nom de joueur");
	JTextArea name = new JTextArea("Joueur 1");
	JButton ok = new JButton("Ok");
	JButton retour = new JButton("Retour");
	JMenuBar mb = new JMenuBar();
	JMenu m1 = new JMenu("Thèmes");
	JMenu m2 = new JMenu("Options");
	JMenuItem mi1 = new JMenuItem("Daltonien");
	ihm i;
	
	public CreerPartieReseau(ihm ihm) {
		i = ihm;
		m1.add(mi1);
		mb.add(m1);
		mb.add(m2);
		mb.setBounds(0, 0, 600, 20);
		this.add(mb);
		this.setLayout(null);
		
		
		i.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent evt) {
                titre.setBounds((i.getWidth()/2) - 110, (i.getHeight()/4) - 100, 300, 100);
        		name.setBounds((i.getWidth()/2) - 90, (i.getHeight()/4) + 40, 200, 20);
            	nomJoueur.setBounds((i.getWidth()/2) - 240, (i.getHeight()/4) + 40, 200, 20);
            	erreur.setBounds((i.getWidth()/2) - 120, (i.getHeight()/4) + 150, 300, 50);
        		ok.setBounds((i.getWidth()/2) + 20, (i.getHeight()/4) + 200, 120, 40);
         		retour.setBounds((i.getWidth()/2) - 130, (i.getHeight()/4) + 200, 120, 40);
         		i.sound.setBounds(i.getWidth() - 80, 75, 40, 40);
         		
            }
		});
		
		
		name.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void changedUpdate(DocumentEvent arg0) {
				// TODO Auto-generated method stub
			}

			@Override
			public void insertUpdate(DocumentEvent arg0) {
				if (name.getText() != "" || name.getText() != " ") {
					erreur.setVisible(false);
					ok.setEnabled(true);
				}
			}

			@Override
			public void removeUpdate(DocumentEvent arg0) {
				if (name.getText().equals("") || name.getText().equals(" ")) {
					erreur.setVisible(true);
					ok.setEnabled(false);
				}
			}
			
		});
		
		this.add(erreur);
		erreur.setVisible(false);
		this.add(nomJoueur);
		this.add(name);
		this.add(ok);
		this.add(retour);
		this.add(titre);
		
		Font fonttitre = new Font("Arial",Font.BOLD,30);
		Font fontnomJoueur = new Font("Arial",Font.BOLD,15);
		Font fonterreur = new Font("Arial",Font.BOLD,20);
		erreur.setForeground(Color.red);
		erreur.setFont(fonterreur);
		nomJoueur.setFont(fontnomJoueur);
		titre.setFont(fonttitre);
		
		ok.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) { 
    			i.fenetreAttenteJoueurReseau();
            } 
        });
		
		retour.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) { 
            	i.fenetreJouerEnReseau();            
            } 
        });
		

		this.setVisible(true);
	}

}
