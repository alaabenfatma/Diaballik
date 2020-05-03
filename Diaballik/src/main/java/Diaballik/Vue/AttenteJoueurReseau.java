package Diaballik.Vue;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.print.DocFlavor.URL;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class AttenteJoueurReseau extends JPanel implements ActionListener {
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
			gif = new ImageIcon(getClass().getResource("gifchargement.gif"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		titre.setBounds(200, 0, 400, 100);
		retour.setBounds(290, 300, 120, 40);
		code.setBounds(300, 200, 200, 40);
		codelabel.setBounds(200, 200, 120, 40);
		gifContainer = new JLabel(gif);
		gifContainer.setBounds( 280, 100, 150, 100);

		 /*java.net.URL url = getClass().getClassLoader().getResource("src/main/java/Diaballik/Vue/gifchargement.gif");
		 Icon icon = new ImageIcon(url);
		 JLabel center = new JLabel(icon);
		 this.add(center, BorderLayout.CENTER);
		*/
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
		retour.addActionListener(this);
		this.setVisible(true);
	}

	public void paintComponent(Graphics g){
        drawable = (Graphics2D) g;
        afficherLogo();
    }
    
    public void afficherLogo() {
    	try {
    	}
    	catch (Exception e) {
    		System.out.println(e);
    	}
    }
    
	
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getSource() == retour) {
			i.fenetreCreerPartieReseau();
		}
	}

}
