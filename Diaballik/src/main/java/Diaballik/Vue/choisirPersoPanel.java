package Diaballik.Vue;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;

public class choisirPersoPanel extends JPanel{

	private static final long serialVersionUID = 1L;
	Image joueur1, joueur2;
	Graphics2D drawable;
	choisirPerso cp;
	int persoJoueur;
	
	
	public choisirPersoPanel(choisirPerso c, int p) {
		cp = c;
		persoJoueur = p;
		
	}
	
	
	public void paintComponent(Graphics g){
        drawable = (Graphics2D) g;
        afficherJoueur();
    }
    
    public void afficherJoueur() {
    	try {
    		if (persoJoueur == 1) {
    			joueur1 = ImageIO.read(this.getClass().getResourceAsStream("img/pionB_bas.png"));
        		drawable.drawImage(joueur1, 70, 30, 170, 200, null);
    		} else {
    			joueur1 = ImageIO.read(this.getClass().getResourceAsStream("img/pionA_bas.png"));
        		drawable.drawImage(joueur1, 70, 30, 170, 200, null);
    		}
    		
    	}
    	catch (Exception e) {
    		System.out.println(e);
    	}
    }

}
