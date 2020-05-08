package Diaballik.Vue;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class JouerReseau extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JLabel titre = new JLabel("Jouer en réseau");
	JButton creer = new JButton("Créer une partie");
	JButton rejoindre = new JButton("Rejoindre une partie");
	JButton menuPrincipal = new JButton("Menu principal");
	JMenuBar mb = new JMenuBar();
	JMenu m1 = new JMenu("Thèmes");
	JMenu m2 = new JMenu("Options");
	JMenuItem mi1 = new JMenuItem("Daltonien");
	ihm i;
	
	public JouerReseau(ihm ihm) {
		i = ihm;
		m1.add(mi1);
		mb.add(m1);
		mb.add(m2);
		mb.setBounds(0, 0, 600, 20);
		this.add(mb);
		this.setLayout(null);
		
		i.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent evt) {
                titre.setBounds((i.getWidth()/2) - 110, (i.getHeight()/4) - 120, 300, 100);
                creer.setBounds((i.getWidth()/2) - 80 , (i.getHeight()/4) + 10, 150, 50);
        		rejoindre.setBounds((i.getWidth()/2) - 80, (i.getHeight()/4) + 80, 150, 50);
        		menuPrincipal.setBounds((i.getWidth()/2) - 80, (i.getHeight()/4) + 150, 150, 50);
            }
		});
		
		Font font = new Font("Arial",Font.BOLD,30);
		titre.setFont(font);
		
		creer.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) { 
            	i.fenetreCreerPartieReseau();
            } 
        } );
		
		rejoindre.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) { 
            	i.fenetreRejoindrePartieReseau();
            } 
        } );
		
		menuPrincipal.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) { 
            	i.retourMenuPrincipal();
            } 
        } );
		
		this.add(creer);
		this.add(rejoindre);
		this.add(menuPrincipal);
		this.add(titre);
		this.setVisible(true);
	}

}
