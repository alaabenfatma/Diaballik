package Diaballik.Vue;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JComponent;


public class imgMenu extends JComponent {
	Image logo;
	Image drapeauFr;
	Image drapeauGB;
	

    Graphics2D drawable;
    

    public void paintComponent(Graphics g){
        drawable = (Graphics2D) g;
        afficherLogo();
		System.out.println("aaaaccc");

        
    }
    
    public void afficherLogo() {
    	try {
    		logo = ImageIO.read(new File("src/main/java/Diaballik/Vue/logo.png"));
    		System.out.println("aaaa");
    		drawable.drawImage(logo, 50, 50, 100, 100, null);
    		System.out.println("bbbbb");
    	}
    	catch (Exception e) {
    		System.out.println(e);
    	}
    }
    
    /*public void afficherDrapeauFr() {
    	try {
    		drapeauFr = ImageIO.read(new File("Diaballik.Vue/drapeaufr.png")); 
    		drawable.drawImage(drapeauFr, 100, 100, 100, 100, null);
    	}
    	catch (Exception e) {
    		
    	}
    }
    
    public void afficherDrapeauGB() {
    	try {
    		drapeauGB = ImageIO.read(new File("Diaballik.Vue/drapeauuk.png"));
    		drawable.drawImage(drapeauGB, 100, 100, 100, 100, null);
    	}
    	catch (Exception e) {
    		
    	}
    }*/
}
