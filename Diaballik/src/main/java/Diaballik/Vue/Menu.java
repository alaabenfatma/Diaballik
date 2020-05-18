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
	JMenuBar mb = new JMenuBar();
	JMenu m1 = new JMenu("Thèmes");
	JMenu m2 = new JMenu("Options");
	JMenuItem mi1 = new JMenuItem("Daltonien");
	JMenuItem mi2 = new JMenuItem("mute");
	JMenuItem mi3 = new JMenuItem("son");
	Image logo, drapeauFr, drapeauGB;
	playSound ps = new playSound();
	ihm i;
    Graphics2D drawable; 	
	
	public Menu(ihm ihm) {
		i = ihm;
		m1.add(mi1);
		m2.add(mi2);
		mb.add(m1);
		mb.add(m2);
		mb.setBounds(0, 0, 600, 20);
		this.add(mb);
		this.setSize(600, 510);

        this.setLayout(null);
        
        i.addComponentListener(new ComponentAdapter() {
                public void componentResized(ComponentEvent evt) {
                    nouvelle.setBounds((i.getWidth()/2) - 80 , (i.getHeight()/4) + 20,150 , 50 + (i.getHeight()/150));
                    charger.setBounds((i.getWidth()/2) - 80, (i.getHeight()/4) + 80, 150, 50 + (i.getHeight()/150));
                    reseau.setBounds((i.getWidth()/2) - 80, (i.getHeight()/4) + 140, 150, 50 + (i.getHeight()/150));
                    regles.setBounds((i.getWidth()/2) - 80, (i.getHeight()/4) + 200, 150, 50 + (i.getHeight()/150));
                    quitter.setBounds((i.getWidth()/2) - 80, (i.getHeight()/4) + 260, 150, 50 + (i.getHeight()/150));
                    i.drapeau.setBounds(i.getWidth() - 80, 25, 40, 40);
                    i.sound.setBounds(i.getWidth() - 80, 75, 40, 40);
                }
        });
        
        
        this.add(nouvelle);
        this.add(charger);
        this.add(reseau);
        this.add(regles);
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
        
  
        mi2.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) { 
            	try {
    				if ( staticConfig.bmute == false) { 
    		    		i.sound.setIcon(new ImageIcon(i.mute));
    		    		m2.remove(mi2);
    		    		m2.add(mi3);
    		    		 staticConfig.bmute = true;
    				} else {
    		    		i.sound.setIcon(new ImageIcon(i.son));
    		    		m2.remove(mi3);
    		    		m2.add(mi2);
    		    		 staticConfig.bmute = false;
    				}
    					
    	    	}
    	    	catch (Exception e1) {
    	    		System.out.println(e1);
    	    	}
            } 
        } );
        
        mi3.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) { 
            	try {
    				if ( staticConfig.bmute == false) { 
    		    		i.sound.setIcon(new ImageIcon(i.mute));
    		    		m2.remove(mi2);
    		    		m2.add(mi3);
    		    		 staticConfig.bmute = true;
    				} else {
    		    		i.sound.setIcon(new ImageIcon(i.son));
    		    		m2.remove(mi3);
    		    		m2.add(mi2);
    		    		 staticConfig.bmute = false;
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
    		drawable.drawImage(logo, (i.getWidth()/2) - 130, (i.getHeight()/25) + 20, 250, (i.getHeight()/5), null);
    	}
    	catch (Exception e) {
    		System.out.println(e);
    	}
    }
    	
}
