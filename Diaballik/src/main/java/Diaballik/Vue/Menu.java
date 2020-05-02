package Diaballik.Vue;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.*;

public class Menu extends JFrame implements ActionListener{
	ImageIcon drapeaufr = new ImageIcon("src/main/java/Diaballik/Vue/drapeaufr.png");
	ImageIcon drapeauuk = new ImageIcon("src/main/java/Diaballik/Vue/drapeauuk.jpg");
	ImageIcon son = new ImageIcon("src/main/java/Diaballik/Vue/sound.png");
	ImageIcon mute = new ImageIcon("src/main/java/Diaballik/Vue/mute.png");

	JButton button1 = new JButton("Nouvelle partie");
	JButton button2 = new JButton("Charger partie");
	JButton button3 = new JButton("Jouer en réseau");
	JButton button4 = new JButton("Règles");
	JButton button5 = new JButton("Quitter");
    JButton button6 = new JButton(drapeaufr);
	JButton button7 = new JButton(drapeauuk);
	JButton button8 = new JButton(son);
	
	JPanel panel = new JPanel();
	
	
	public Menu() {
		this.setTitle("Menu principal");
		this.setSize(700, 500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        panel.setLayout(null);
        /*
        ImageIcon icon = new ImageIcon("src/main/java/Diaballik/Vue/logo.png");
        Image img = icon.getImage();
        BufferedImage bi = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);
        Graphics g = bi.createGraphics();
        g.drawImage(img, 0, 0, 200, 100, null);
        ImageIcon newIcon = new ImageIcon(bi);
        JLabel image = new JLabel(icon);
        //image.setBounds(100, 100, 100, 100);
        this.add(image);
        this.setVisible(true);
        */
        
        button1.setBounds(280, 150, 150, 50);
        button2.setBounds(280, 210, 150, 50);
        button3.setBounds(280, 270, 150, 50);
        button4.setBounds(280, 330, 150, 50);
        button5.setBounds(280, 390, 150, 50);
        button6.setBounds(570, 10, 40, 40);
        button7.setBounds(620, 10, 40, 40);
        button8.setBounds(620, 60, 40, 40);
       


        panel.add(button1);
        panel.add(button2);
        panel.add(button3);
        panel.add(button4);
        panel.add(button5);
        panel.add(button6);
        panel.add(button7);
        panel.add(button8);

        button1.addActionListener(this);
        button2.addActionListener(this);
        button3.addActionListener(this);
        button4.addActionListener(this);
        button5.addActionListener(this);
        
        this.add(panel);
        this.setVisible(true);
	
	}
	
	 
	public void actionPerformed(ActionEvent arg0) {
		
		if(arg0.getSource() == button1) {
			this.remove(panel);
			this.setSize(699, 499);
			this.setTitle("Nouvelle partie");
			JPanel p = new NewGame();
			
			this.add(p);
			this.getContentPane().add(p);
			repaint();
			
		}
		
		if(arg0.getSource() == button2) {
			this.remove(panel);
			this.setSize(699, 499);
			this.setTitle("Charger partie");
			JPanel p = new ChargerPartie();
			
			this.add(p);
			this.getContentPane().add(p);
			repaint();
		}
		
		if(arg0.getSource() == button3) {
			this.remove(panel);
			this.setSize(699, 499);
			this.setTitle("Jouer en réseau");
			JPanel p = new JouerReseau();
			
			this.add(p);
			this.getContentPane().add(p);
			repaint();
		}
		
		if(arg0.getSource() == button4) {
			//ouvrir les règles
		}
		
		if(arg0.getSource() == button5) {
			msgBox msg = new msgBox();
			msg.MessageBox("Voulez-vous quitter le jeu", "Quitter");
			
			
		}
		if(arg0.getSource() == button8) {
			//changer l'image en mute
		}
	}
}
