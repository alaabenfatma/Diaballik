package Diaballik.Vue;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class choisirPersoPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	Image joueur1, joueur2, pionBallon;
	Graphics2D drawable;
	choisirPerso cp;
	int persoJoueur, numJoueur;

	public choisirPersoPanel(choisirPerso c, int p, int n) {
		cp = c;
		persoJoueur = p;
		numJoueur = n;

	}

	public void paintComponent(Graphics g) {
		drawable = (Graphics2D) g;
		afficherJoueur();
	}

	public void afficherJoueur() {
		try {
			if (numJoueur == 1) {
				if (persoJoueur == 1) {
					joueur1 = ImageIO.read(this.getClass().getResourceAsStream("/pionB_bas.png"));
					drawable.drawImage(joueur1, 70, 30, 170, 200, null);
					pionBallon = ImageIO.read(this.getClass().getResourceAsStream("/pionB_ballon.png"));
				} else if (persoJoueur == 2) {
					joueur1 = ImageIO.read(this.getClass().getResourceAsStream("/pionA_bas.png"));
					drawable.drawImage(joueur1, 70, 30, 170, 200, null);
					pionBallon = ImageIO.read(this.getClass().getResourceAsStream("/pionA_ballon.png"));
				} else if (persoJoueur == 3) {
					joueur1 = ImageIO.read(this.getClass().getResourceAsStream("/pion3_bas.png"));
					drawable.drawImage(joueur1, 70, 30, 170, 200, null);
					pionBallon = ImageIO.read(this.getClass().getResourceAsStream("/pion3_ballon.png"));
				} else if (persoJoueur == 4) {
					joueur1 = ImageIO.read(this.getClass().getResourceAsStream("/pion4_bas.png"));
					drawable.drawImage(joueur1, 70, 30, 170, 200, null);
					pionBallon = ImageIO.read(this.getClass().getResourceAsStream("/pion4_ballon.png"));
				}
			} else {
				if (persoJoueur == 1) {
					joueur2 = ImageIO.read(this.getClass().getResourceAsStream("/pionB_bas.png"));
					drawable.drawImage(joueur2, 70, 30, 170, 200, null);
					pionBallon = ImageIO.read(this.getClass().getResourceAsStream("/pionB_ballon.png"));
				} else if (persoJoueur == 2) {
					joueur2 = ImageIO.read(this.getClass().getResourceAsStream("/pionA_bas.png"));
					drawable.drawImage(joueur2, 70, 30, 170, 200, null);
					pionBallon = ImageIO.read(this.getClass().getResourceAsStream("/pionA_ballon.png"));
				} else if (persoJoueur == 3) {
					joueur2 = ImageIO.read(this.getClass().getResourceAsStream("/pion3_bas.png"));
					drawable.drawImage(joueur2, 70, 30, 170, 200, null);
					pionBallon = ImageIO.read(this.getClass().getResourceAsStream("/pion3_ballon.png"));
				} else if (persoJoueur == 4) {
					joueur2 = ImageIO.read(this.getClass().getResourceAsStream("/pion4_bas.png"));
					drawable.drawImage(joueur2, 70, 30, 170, 200, null);
					pionBallon = ImageIO.read(this.getClass().getResourceAsStream("/pion4_ballon.png"));
				}
			}

		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
