package Diaballik.Vue;

import java.awt.*;
import java.awt.event.*;
import javax.imageio.ImageIO;
import javax.swing.*;


public class Menu extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JButton nouvelle = new JButton("Nouvelle partie");
	JButton charger = new JButton("Charger partie");
	JButton reseau = new JButton("Jouer en réseau");
	JButton regles = new JButton("Règles");
	JButton quitter = new JButton("Quitter");
	JButton drapeau = new JButton();
	JButton sound = new JButton();
	Image logo, drapeauFr, drapeauGB, son, mute;
	playSound ps = new playSound();
	ihm i;
    Graphics2D drawable;
    boolean bson = true, blang = true;
     	
	
	public Menu(ihm ihm) {
		i = ihm;
		
        this.setLayout(null);
        
        i.addComponentListener(new ComponentAdapter() {
                public void componentResized(ComponentEvent evt) {
                    nouvelle.setBounds((i.getWidth()/2)-70, (i.getHeight()/4), 150, 50);
                    charger.setBounds((i.getWidth()/2)-70, (i.getHeight()/4) + 60, 150,50);
                    reseau.setBounds((i.getWidth()/2)-70, (i.getHeight()/4) + 120, 150,50);
                    regles.setBounds((i.getWidth()/2)-70, (i.getHeight()/4) + 180, 150,50);
                    quitter.setBounds((i.getWidth()/2)-70, (i.getHeight()/4) + 240,150,50);
                    drapeau.setBounds(i.getWidth()-80, 10, 40, 40);
                    sound.setBounds(i.getWidth()-80, 60, 40, 40);
                }
        });
        
        try {
    		drapeauFr = ImageIO.read(this.getClass().getResourceAsStream(("img/drapeaufr.png"))).getScaledInstance(40, 40, Image.SCALE_DEFAULT);; 
    		drapeau.setIcon(new ImageIcon(drapeauFr));
    		son = ImageIO.read(this.getClass().getResourceAsStream("img/sound.png")).getScaledInstance(40, 40, Image.SCALE_DEFAULT);; 
    		sound.setIcon(new ImageIcon(son));
    	}
    	catch (Exception e) {
    		System.out.println(e);
    	}
        
        this.add(nouvelle);
        this.add(charger);
        this.add(reseau);
        this.add(regles);
        this.add(quitter);
        this.add(drapeau);
        this.add(sound);
        

        nouvelle.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) { 
            	i.fenetreNouvellePartie();
            } 
        } );
        
        charger.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) { 
            	i.fenetreChargerPartie();
            } 
        } );
        
        reseau.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) { 
            	i.fenetreJouerEnReseau();
            } 
        } );
        regles.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) { 
            	i.fenetreRegles();
            } 
        } );
        quitter.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) { 
            	i.quit();
            } 
        } );
        drapeau.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) { 
            	try {
    				if (blang == true) {
    					drapeauGB = ImageIO.read(this.getClass().getResourceAsStream("img/drapeauuk.jpg")).getScaledInstance(40, 40, Image.SCALE_DEFAULT); 
    		    		drapeau.setIcon(new ImageIcon(drapeauGB));
    		    		blang = false;
    				} else {
    					drapeauFr = ImageIO.read(this.getClass().getResourceAsStream(("img/drapeaufr.png"))).getScaledInstance(40, 40, Image.SCALE_DEFAULT); 
    		    		drapeau.setIcon(new ImageIcon(drapeauFr));
    		    		blang = true;
    				}
    			}
    			catch (Exception e1) {
    				System.out.println(e1);
    			}
            } 
        } );
        
        sound.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) { 
            	try {
    				if (bson == true) {
    		    		mute = ImageIO.read(this.getClass().getResourceAsStream("img/mute.png")).getScaledInstance(40, 40, Image.SCALE_DEFAULT);; 
    		    		sound.setIcon(new ImageIcon(mute));
    		    		bson = false;
    				} else {
    					son = ImageIO.read(this.getClass().getResourceAsStream("img/sound.png")).getScaledInstance(40, 40, Image.SCALE_DEFAULT);; 
    		    		sound.setIcon(new ImageIcon(son));
    		    		bson = true;
    				}
    					
    	    	}
    	    	catch (Exception e1) {
    	    		System.out.println(e1);
    	    	}
            } 
        } );
        
        this.setVisible(true);
	
	}
	
	public void paintComponent(Graphics g){
        drawable = (Graphics2D) g;
        afficherLogo();
    }
    
    public void afficherLogo() {
    	try {
    		logo = ImageIO.read(this.getClass().getResourceAsStream("img/logo.png"));
    		drawable.drawImage(logo, (i.getWidth()/2) - 120, (i.getHeight()/4) - 115, 250, 100, null);
    	}
    	catch (Exception e) {
    		System.out.println(e);
    	}
    }
    	
}
