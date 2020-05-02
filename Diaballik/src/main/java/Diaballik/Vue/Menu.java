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
	ihm i;
    Graphics2D drawable;
 	
	
	public Menu(ihm ihm) {
		i = ihm;
        this.setLayout(null);
        
        button1.setBounds(280, 150, 150, 50);
        button2.setBounds(280, 210, 150, 50);
        button3.setBounds(280, 270, 150, 50);
        button4.setBounds(280, 330, 150, 50);
        button5.setBounds(280, 390, 150, 50);
        button6.setBounds(570, 10, 40, 40);
        button7.setBounds(620, 10, 40, 40);
        button8.setBounds(620, 60, 40, 40);
       
        try {
    		drapeauFr = ImageIO.read(new File("src/main/java/Diaballik/Vue/drapeaufr.png")).getScaledInstance(40, 40, Image.SCALE_DEFAULT);; 
    		button6.setIcon(new ImageIcon(drapeauFr));
    		drapeauGB = ImageIO.read(new File("src/main/java/Diaballik/Vue/drapeauuk.jpg")).getScaledInstance(40, 40, Image.SCALE_DEFAULT);; 
    		button7.setIcon(new ImageIcon(drapeauGB));
    		son = ImageIO.read(new File("src/main/java/Diaballik/Vue/sound.png")).getScaledInstance(40, 40, Image.SCALE_DEFAULT);; 
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
        this.add(button6);
        this.add(button7);
        this.add(button8);
        

        button1.addActionListener(this);
        button2.addActionListener(this);
        button3.addActionListener(this);
        button4.addActionListener(this);
        button5.addActionListener(this);
        button8.addActionListener(this);
        this.setVisible(true);
	
	}
	public void paintComponent(Graphics g){
        drawable = (Graphics2D) g;
        afficherLogo();
    }
    
    public void afficherLogo() {
    	try {
    		logo = ImageIO.read(new File("src/main/java/Diaballik/Vue/logo.png"));
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
			//ouvrir les règles
		}
	 	
		if(arg0.getSource() == button5) {
			msgBox msg = new msgBox();
			msg.MessageBox("Voulez-vous quitter le jeu ? ", "Quitter", i);
		}
		
		if(arg0.getSource() == button8) {
			try {
	    		mute = ImageIO.read(new File("src/main/java/Diaballik/Vue/mute.png")).getScaledInstance(40, 40, Image.SCALE_DEFAULT);; 
	    		button8.setIcon(new ImageIcon(mute));
	    	}
	    	catch (Exception e) {
	    		System.out.println(e);
	    	}
		}
		
	}
}
