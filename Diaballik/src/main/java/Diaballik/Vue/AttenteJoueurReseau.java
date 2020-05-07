package Diaballik.Vue;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class AttenteJoueurReseau extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JLabel titre = new JLabel("Attente du 2ème joueur");
	JLabel codelabel = new JLabel("Code");
	JLabel code = new JLabel("(generation du code)");
	JButton retour = new JButton("Retour");
	ImageIcon gif;
	JLabel gifContainer;
	ihm i;

	Graphics2D drawable;

	public AttenteJoueurReseau(ihm ihm) {
		i = ihm;
		this.setLayout(null);
		try {
			gif = new ImageIcon(getClass().getResource("img/gifchargement.gif"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		titre.setBounds(200, 0, 400, 100);
		retour.setBounds(290, 300, 120, 40);
		code.setBounds(300, 200, 200, 40);
		codelabel.setBounds(200, 200, 120, 40);
		gifContainer = new JLabel(gif);
		gifContainer.setBounds(250, 100, 200, 100);
		
		retour.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) { 
            	i.fenetreCreerPartieReseau();
            } 
        } );

		this.add(codelabel);
		this.add(code);
		this.add(retour);
		this.add(titre);
		this.add(gifContainer);
		Font font = new Font("Arial",Font.BOLD,30);
		Font fontnomJoueur = new Font("Arial",Font.BOLD,15);
		code.setFont(fontnomJoueur);
		codelabel.setFont(fontnomJoueur);
		
		titre.setFont(font);
		this.setVisible(true);
	}

}
