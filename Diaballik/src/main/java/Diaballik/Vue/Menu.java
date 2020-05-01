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
	JButton button1 = new JButton("Nouvelle partie");
	JButton button2 = new JButton("Charger partie");
	JButton button3 = new JButton("Jouer en réseau");
	JButton button4 = new JButton("Règles");
	JButton button5 = new JButton("Quitter");
    JButton button6 = new JButton(drapeaufr);
	JButton button7 = new JButton(drapeauuk);
	Image logo;
	Image drapeauFr;
	Image drapeauGB;
	

	JPanel panel = new JPanel();
	
	
	
	public Menu() {
		this.setTitle("Menu principal");
		this.setSize(700, 500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        panel.setLayout(null);
        
        ImageIcon icon = new ImageIcon("src/main/java/Diaballik/Vue/logo.png");
        Image img = icon.getImage();
        BufferedImage bi = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);
        Graphics g = bi.createGraphics();
        g.drawImage(img, 170, -50, 200, 100, null);
        ImageIcon newIcon = new ImageIcon(bi);
        JLabel image = new JLabel(newIcon);
        this.add(image);
        this.setVisible(true);
        	
        
        button1.setBounds(280, 150, 120, 50);
        button2.setBounds(280, 210, 120, 50);
        button3.setBounds(280, 270, 120, 50);
        button4.setBounds(280, 330, 120, 50);
        button5.setBounds(280, 390, 120, 50);
        button6.setBounds(570, 10, 40, 40);
        button7.setBounds(620, 10, 40, 40);

        panel.add(button1);
        panel.add(button2);
        panel.add(button3);
        panel.add(button4);
        panel.add(button5);
        panel.add(button6);
        panel.add(button7);

        this.add(panel);
        this.setVisible(true);
	
	}
	
	public void actionPerformed(ActionEvent arg0) {
		
		
		if(arg0.getSource() == button1) {
			
		}
		if(arg0.getSource() == button2) {
					
		}
		if(arg0.getSource() == button3) {
			
		}
		if(arg0.getSource() == button4) {
			
		}
		if(arg0.getSource() == button5) {
			
		}
	}
}
