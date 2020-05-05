package Diaballik.Vue;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.*;

public class Menu extends JPanel implements ActionListener{

	JButton button1 = new JButton("Nouvelle partie");
	JButton button2 = new JButton("Charger partie");
	JButton button3 = new JButton("Jouer en réseau");
	JButton button4 = new JButton("Règles");
	JButton button5 = new JButton("Quitter");
    JButton button6 = new JButton();
	JButton button7 = new JButton();
	JButton button8 = new JButton();
	Image logo, drapeauFr, drapeauGB, son, mute;
	playSound ps = new playSound();
	ihm i;
    Graphics2D drawable;
    boolean bson = true, blang = true;
 	
	
	public Menu(ihm ihm) {
		i = ihm;
        this.setLayout(null);
        
        button1.setBounds(280, 150, 150, 50);
        button2.setBounds(280, 210, 150, 50);
        button3.setBounds(280, 270, 150, 50);
        button4.setBounds(280, 330, 150, 50);
        button5.setBounds(280, 390, 150, 50);
        //button6.setBounds(570, 10, 40, 40);
        button7.setBounds(620, 10, 40, 40);
        button8.setBounds(620, 60, 40, 40);
       
        try {
    		drapeauFr = ImageIO.read(this.getClass().getResourceAsStream(("img/drapeaufr.png"))).getScaledInstance(40, 40, Image.SCALE_DEFAULT);; 
    		button7.setIcon(new ImageIcon(drapeauFr));
    		son = ImageIO.read(this.getClass().getResourceAsStream("img/sound.png")).getScaledInstance(40, 40, Image.SCALE_DEFAULT);; 
    		button8.setIcon(new ImageIcon(son));
    	}
    	catch (Exception e) {
    		System.out.println(e);
    	}

        this.add(button1);
        this.add(button2);
        this.add(button3);
        this.add(button4);
        this.add(button5);
        //this.add(button6);
        this.add(button7);
        this.add(button8);
        

        button1.addActionListener(this);
        button2.addActionListener(this);
        button3.addActionListener(this);
        button4.addActionListener(this);
        button5.addActionListener(this);
        button7.addActionListener(this);
        button8.addActionListener(this);
        this.setVisible(true);
	
	}
	public void paintComponent(Graphics g){
        drawable = (Graphics2D) g;
        afficherLogo();
    }
    
    public void afficherLogo() {
    	try {
    		logo = ImageIO.read(this.getClass().getResourceAsStream("img/logo.png"));
    		drawable.drawImage(logo, 230, 25, 250, 100, null);
    	}
    	catch (Exception e) {
    		System.out.println(e);
    	}
    }
    

	public void actionPerformed(ActionEvent arg0) {
		
		if(arg0.getSource() == button1) {
			i.fenetreNouvellePartie();
		}
		
		if(arg0.getSource() == button2) {
			i.fenetreChargerPartie();
		}
		
		if(arg0.getSource() == button3) {
			i.fenetreJouerEnReseau();
		}
		
		if(arg0.getSource() == button4) {
			i.fenetreRegles();
		}
	 	
		if(arg0.getSource() == button5) {
			ps.play("src/main/java/Diaballik/Vue/son/buttonClick.wav");
			msgBox msg = new msgBox();
			msg.MessageBox("Voulez-vous quitter le jeu ? ", "Quitter", i);
		}
		
		if(arg0.getSource() == button7) {
			try {
				if (blang == true) {
					drapeauGB = ImageIO.read(this.getClass().getResourceAsStream("img/drapeauuk.jpg")).getScaledInstance(40, 40, Image.SCALE_DEFAULT);; 
		    		button7.setIcon(new ImageIcon(drapeauGB));
		    		blang = false;
				} else {
					drapeauFr = ImageIO.read(this.getClass().getResourceAsStream(("img/drapeaufr.png"))).getScaledInstance(40, 40, Image.SCALE_DEFAULT);; 
		    		button7.setIcon(new ImageIcon(drapeauFr));
		    		blang = true;
				}
			}
			catch (Exception e) {
				System.out.println(e);
			}
		}
		
		if(arg0.getSource() == button8) {
			
			try {
				if (bson == true) {
		    		mute = ImageIO.read(this.getClass().getResourceAsStream("img/mute.png")).getScaledInstance(40, 40, Image.SCALE_DEFAULT);; 
		    		button8.setIcon(new ImageIcon(mute));
		    		bson = false;
				} else {
					son = ImageIO.read(this.getClass().getResourceAsStream("img/sound.png")).getScaledInstance(40, 40, Image.SCALE_DEFAULT);; 
		    		button8.setIcon(new ImageIcon(son));
		    		bson = true;
				}
					
	    	}
	    	catch (Exception e) {
	    		System.out.println(e);
	    	}
					
		}	
	}
}
