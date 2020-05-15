package Diaballik.Vue;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class choisirPerso extends JFrame{

	private static final long serialVersionUID = 1L;
	Image joueur1;
	Graphics2D drawable;
	JPanel panel = new JPanel();

	public choisirPerso() {
		this.setTitle("Personnalisation");
		this.setSize(300, 300);
		this.setLocationRelativeTo(null);
	
		this.add(panel);
		
		this.setVisible(true);
	}
	
	public void paintComponent(Graphics g){
        drawable = (Graphics2D) g;
        afficherLogo();
    }
    
    public void afficherLogo() {
    	try {
    		joueur1 = ImageIO.read(this.getClass().getResourceAsStream("img/pionA_bas.png"));
    		drawable.drawImage(joueur1, 100, 100, 100, 100, null);
    	}
    	catch (Exception e) {
    		System.out.println(e);
    	}
    }
	
	
}
