package Diaballik.Vue;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class ihm extends JFrame {

	private static final long serialVersionUID = 1L;
	Menu menu = new Menu(this);
	NewGame ng = new NewGame(this);
	JouerReseau jr = new JouerReseau(this);
	Image icon = Toolkit.getDefaultToolkit().getImage("src/main/java/Diaballik/Vue/img/pionA_ballon.png");  
	JButton sound = new JButton();
	JMenuBar mb = new JMenuBar();
	JMenu m1 = new JMenu("Thèmes");
	JMenu m2 = new JMenu("Options");
	JMenuItem mi1 = new JMenuItem("Daltonien");
	JMenuItem mi2 = new JMenuItem("mute");
	JMenuItem mi3 = new JMenuItem("son");
	Image son, mute;
	
	playSound ps = new playSound();
	boolean bmute = true;
	
	

	public ihm() {
		
		this.setIconImage(icon); 
		this.setTitle("Menu principal");
		this.setSize(600, 510);
		
		
		this.addWindowListener(new java.awt.event.WindowAdapter() {
				@Override
			public void windowClosing(java.awt.event.WindowEvent windowEvent) {
				if(msgBox.msgYesNo("Voulez-vous quitter?", "Quitter") == 0){
						System.exit(0);
	            }
	            else{
	               return;
	               }
				}
	    });
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			
		sound.setBounds(this.getWidth() - 80, 75, 40, 40);
		
		try {
    		son = ImageIO.read(this.getClass().getResourceAsStream("img/sound.png")).getScaledInstance(40, 40, Image.SCALE_DEFAULT);
    		mute = ImageIO.read(this.getClass().getResourceAsStream("img/mute.png")).getScaledInstance(40, 40, Image.SCALE_DEFAULT);
    		sound.setIcon(new ImageIcon(mute));
    	}
    	catch (Exception e) {
    		System.out.println(e);
    	}
		
		sound.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) { 
            	try {
    				if (bmute == false) { 
    		    		sound.setIcon(new ImageIcon(mute));
    		    		bmute = true;
    				} else {
    		    		sound.setIcon(new ImageIcon(son));
    		    		bmute = false;
    				}
    					
    	    	}
    	    	catch (Exception e1) {
    	    		System.out.println(e1);
    	    	}
            } 
        } );
        
		menu.add(sound);
	    this.setLocationRelativeTo(null);
	    this.add(menu);
	    this.setVisible(true);
	}
	
	
	public void fenetreNouvellePartie() {
		ps.play("son/buttonClick.wav", bmute);
		this.setSize(601, 550);
		NewGame ng = new NewGame(this);	
		ng.add(sound);
		this.setContentPane(ng);
		this.setTitle("Nouvelle partie");
		this.repaint();
		this.revalidate();
	}

	
	public void fenetreChargerPartie() {
		ps.play("son/buttonClick.wav", bmute);
		this.setSize(800, 540);
		this.setSize(800, 550);
		ChargerPartie cp = new ChargerPartie(this);
		cp.add(sound);
		this.setContentPane(cp);
		this.setTitle("Charger partie");
		this.repaint();
		this.revalidate();
	}
	
	
	public void fenetreJouerEnReseau() {
		ps.play("son/buttonClick.wav", bmute);
		this.setSize(600, 511);
		this.setSize(600, 510);
		JouerReseau jr = new JouerReseau(this);
		jr.add(sound);
		this.setContentPane(jr);
		this.setTitle("Jouer en réseau");
		this.repaint();
		this.revalidate();
	}
	
	
	public void retourMenuPrincipal() {
		ps.play("son/buttonClick.wav", bmute);
		this.setSize(600, 511);
		this.setSize(600, 510);
		this.setLocationRelativeTo(null);
		Menu m = new Menu(this);
		m.add(sound);
		this.setContentPane(m);
		this.setTitle("Menu principal");
		this.repaint();
		this.revalidate();
	}
	
	
	public void fenetreCreerPartieReseau() {
		ps.play("son/buttonClick.wav", bmute);
		this.setSize(600, 401);
		this.setSize(600, 400);
		CreerPartieReseau crr = new CreerPartieReseau(this);
		crr.add(sound);
		this.setContentPane(crr);
		this.setTitle("Créer une partie en réseau");
		this.repaint();
		this.revalidate();
	}
	
	
	public void fenetreRejoindrePartieReseau() {
		ps.play("son/buttonClick.wav", bmute);
		this.setSize(600, 401);
		this.setSize(600, 400);
		RejoindrePartieReseau rpr = new RejoindrePartieReseau(this);
		rpr.add(sound);
		this.setContentPane(rpr);
		this.setTitle("Rejoindre une partie");
		this.repaint();
		this.revalidate();
	}
	
	
	public void fenetreAttenteJoueurReseau() {
		ps.play("son/buttonClick.wav", bmute);
		this.setSize(600, 401);
		this.setSize(600, 400);
		AttenteJoueurReseau ajr = new AttenteJoueurReseau(this);
		ajr.add(sound);
		this.setContentPane(ajr);
		this.setTitle("Attente du 2ème joueur");
		this.repaint();
		this.revalidate();
	}
	
	
	public void fenetreRegles() {
		ps.play("son/buttonClick.wav", bmute);
		this.setSize(800, 620);
		this.setLocationRelativeTo(null);
		Regles r = new Regles(this);
		this.setContentPane(r);
		this.setTitle("Règles du jeu");
		this.repaint();
		this.revalidate();
	}
	
	
	public void quit() {
		ps.play("son/buttonClick.wav", bmute);
		msgBox.MessageBox("Voulez-vous quitter le jeu ? ", "Quitter", this);
	}
	
}
