package Diaballik.Vue;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class choisirPerso extends JFrame{

	private static final long serialVersionUID = 1L;
	JButton suivant = new JButton(">");
	JButton precedent = new JButton("<");
	JButton valider = new JButton("Valider");
	
	public choisirPerso(int persoJoueur) {
		choisirPersoPanel panel = new choisirPersoPanel(this, persoJoueur);
		int p = persoJoueur;
		this.setTitle("Personnalisation");
		this.setSize(300, 300);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		

		addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent evt) {
            	precedent.setBounds(0, 100, 50, 50);
        		suivant.setBounds(240, 100, 50, 50);
        		valider.setBounds(100, 230, 100, 30);
            }
		});
		
		
		valider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});

		precedent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		suivant.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		this.add(valider);
		this.add(suivant);
		this.add(precedent);
		this.add(panel);
		
		this.setVisible(true);
	}
	
	
}




