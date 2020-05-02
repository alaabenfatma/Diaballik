package Diaballik.Vue;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.imageio.ImageIO;
import javax.print.DocFlavor.URL;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class AttenteJoueurReseau  extends JPanel implements ActionListener{
	JLabel titre = new JLabel("Attente du 2ème joueur");
	JButton retour = new JButton("Retour");
	Image gif;
	ihm i;
	
    Graphics2D drawable;

	
	public AttenteJoueurReseau (ihm ihm) {
		i = ihm;
		this.setLayout(null);

		titre.setBounds(200, 0, 400, 100);
		retour.setBounds(290, 300, 120, 40);
		
		 /*java.net.URL url = getClass().getClassLoader().getResource("src/main/java/Diaballik/Vue/gifchargement.gif");
		 Icon icon = new ImageIcon(url);
		 JLabel center = new JLabel(icon);
		 this.add(center, BorderLayout.CENTER);
		*/
		this.add(retour);
		this.add(titre);
		
		Font font = new Font("Arial",Font.BOLD,30);
		titre.setFont(font);
		retour.addActionListener(this);
		this.setVisible(true);
	}

	public void paintComponent(Graphics g){
        drawable = (Graphics2D) g;
        afficherLogo();
    }
    
    public void afficherLogo() {
    	try {
    		gif = ImageIO.read(new File("src/main/java/Diaballik/Vue/gifchargement.gif"));
    		drawable.drawImage(gif, 250, 120, 200, 150, null);
    	}
    	catch (Exception e) {
    		System.out.println(e);
    	}
    }
    
	
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getSource() == retour) {
			i.fenetreCreerPartieReseau();
		}
	}

}
