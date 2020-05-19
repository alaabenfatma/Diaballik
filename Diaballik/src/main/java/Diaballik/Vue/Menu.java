package Diaballik.Vue;

import java.awt.*;
import java.awt.event.*;

import javax.imageio.ImageIO;
import javax.swing.*;

import com.fasterxml.jackson.databind.ObjectMapper;


public class Menu extends JPanel {

	private static final long serialVersionUID = 1L;
	ObjectMapper objectMapper = new ObjectMapper();
	JButton nouvelle = new JButton("Nouvelle partie");
	JButton charger = new JButton("Charger partie");
	JButton reseau = new JButton("Jouer en réseau");
	JButton regles = new JButton("Règles");
	JButton quitter = new JButton("Quitter");
	JButton drapeau = new JButton();
	JButton credit = new JButton("Credits");
	Image logo, drapeauFr, drapeauGB;
	playSound ps = new playSound();
	ihm i;
    Graphics2D drawable; 	
	
	public Menu(ihm ihm) {
		i = ihm;
	
		this.setSize(600, 510);
		
        this.setLayout(null);
        
        i.addComponentListener(new ComponentAdapter() {
                public void componentResized(ComponentEvent evt) {
                    nouvelle.setBounds((i.getWidth()/2) - 80 , (i.getHeight()/4) + 20,150 , 50 + (i.getHeight()/150));
                    charger.setBounds((i.getWidth()/2) - 80, (i.getHeight()/4) + 80, 150, 50 + (i.getHeight()/150));
                    reseau.setBounds((i.getWidth()/2) - 80, (i.getHeight()/4) + 140, 150, 50 + (i.getHeight()/150));
                    regles.setBounds((i.getWidth()/2) - 80, (i.getHeight()/4) + 200, 150, 50 + (i.getHeight()/150));
                    quitter.setBounds((i.getWidth()/2) - 80, (i.getHeight()/4) + 260, 150, 50 + (i.getHeight()/150));
                    credit.setBounds((i.getWidth()/2) + 120, (i.getHeight()/4) + 280, 100, 30);
                    i.drapeau.setBounds(i.getWidth() - 80, 25, 40, 40);
                    i.sound.setBounds(i.getWidth() - 80, 75, 40, 40);
                }
        });
        
        
        this.add(nouvelle);
        this.add(charger);
        this.add(reseau);
        this.add(regles);
        this.add(credit);
        this.add(quitter);
    		
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
        
        credit.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) { 
            	new credits();
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
    		logo = ImageIO.read(this.getClass().getResourceAsStream("/logo.png"));
    		drawable.drawImage(logo, (i.getWidth()/2) - 130, (i.getHeight()/25) + 20, 250, (i.getHeight()/5), null);
    	}
    	catch (Exception e) {
    		System.out.println(e);
    	}
    }
    	
}
