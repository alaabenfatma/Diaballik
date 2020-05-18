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

import com.fasterxml.jackson.databind.ObjectMapper;

import Diaballik.Models.ConfigJeu;
import Diaballik.Models.Jeu;


public class ihm extends JFrame {

	private static final long serialVersionUID = 1L;
	ObjectMapper objectMapper = new ObjectMapper();
	Menu menu = new Menu(this);
	Menu m = new Menu(this);
	NewGame ng = new NewGame(this);
	ChargerPartie cp = new ChargerPartie(this, false);
	JouerReseau jr = new JouerReseau(this);
	AttenteJoueurReseau ajr = new AttenteJoueurReseau(this);
	CreerPartieReseau crr = new CreerPartieReseau(this);
	Regles r = new Regles(this);
	RejoindrePartieReseau rpr = new RejoindrePartieReseau(this);
	Plateau plateau;
	choisirPerso cP;
	choisirTerrain cT;
	Image icon = Toolkit.getDefaultToolkit().getImage("src/main/java/Diaballik/Vue/img/pionA_ballon.png");  
	JButton sound = new JButton();
	JButton drapeau = new JButton();
	JMenuBar mb = new JMenuBar();
	JMenu m1 = new JMenu("Thèmes");
	JMenu m2 = new JMenu("Options");
	JMenuItem mi1 = new JMenuItem("Daltonien");
	JMenuItem mi2 = new JMenuItem("mute");
	JMenuItem mi3 = new JMenuItem("son");
	Image son, mute, drapeauFr, drapeauGB;
	
	playSound ps = new playSound();


	public ihm() {
		this.setIconImage(icon); 
		
		this.setTitle("Menu principal");
		this.setSize(600, 510);
		
		
		this.addWindowListener(new java.awt.event.WindowAdapter() {
				@Override
			public void windowClosing(java.awt.event.WindowEvent windowEvent) {
				
					if(staticConfig.blang == false) {
						if(msgBox.msgYesNo("Do you want to quit ?", "Quit") == 0){
							System.exit(0);
		            } else {
		               return;
		            }
					
				} else {
					if(msgBox.msgYesNo("Voulez-vous quitter?", "Quitter") == 0){
						System.exit(0);
					} else {
						return;
					}
				}
				
				}
	    });
		
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			
		sound.setBounds(this.getWidth() - 80, 75, 40, 40);
		
		try {
    		son = ImageIO.read(this.getClass().getResourceAsStream("img/sound.png")).getScaledInstance(40, 40, Image.SCALE_DEFAULT);
    		mute = ImageIO.read(this.getClass().getResourceAsStream("img/mute.png")).getScaledInstance(40, 40, Image.SCALE_DEFAULT);
    		sound.setIcon(new ImageIcon(mute));
    		drapeauFr = ImageIO.read(this.getClass().getResourceAsStream(("img/drapeaufr.png"))).getScaledInstance(40, 40, Image.SCALE_DEFAULT);; 
			drapeauGB = ImageIO.read(this.getClass().getResourceAsStream("img/drapeauuk.jpg")).getScaledInstance(40, 40, Image.SCALE_DEFAULT);
    		if (staticConfig.blang == true) {
    			drapeau.setIcon(new ImageIcon(drapeauFr));
    		} else {
    			drapeau.setIcon(new ImageIcon(drapeauGB));
    		}
    	}
    	catch (Exception e) {
    		System.out.println(e);
    	}

		
		drapeau.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) { 
            	try {
    				if (staticConfig.blang == true) {
    					drapeauGB = ImageIO.read(this.getClass().getResourceAsStream("img/drapeauuk.jpg")).getScaledInstance(40, 40, Image.SCALE_DEFAULT); 
    		    		drapeau.setIcon(new ImageIcon(drapeauGB));
    		    		words wEn = objectMapper.readValue(this.getClass().getResourceAsStream("languesEn.json"), words.class);
    		    		menu.nouvelle.setText(wEn.newgame);
    		    		menu.charger.setText(wEn.charger);
    		    		menu.reseau.setText(wEn.reseau);
    		    		menu.regles.setText(wEn.regles);
    		    		menu.quitter.setText(wEn.quit);
    		    		
    		    		ng.titre.setText(wEn.newgame);
    		    		ng.humain.setText(wEn.humain);
    		    		ng.ordinateur.setText(wEn.ordinateur);
    		    		ng.jouerContre.setText(wEn.jouerContre);
    		    		ng.nomJoueur.setText(wEn.nomJoueur1);
    		    		ng.nomJoueur2.setText(wEn.nomJoueur2);
    		    		ng.joueur1.setText(wEn.joueur1);
    		    		ng.joueur2.setText(wEn.joueur2);
    		    		ng.varianteCheckbox.setText(wEn.variante);
    		    		ng.illimite.setText(wEn.illimite);
    		    		ng.priorite.setText(wEn.priorite);
    		    		ng.choixPersoJoueur1.setText(wEn.personnaliser);
    		    		ng.choixPersoJoueur2.setText(wEn.personnaliser);
    		    		ng.name1.setText(wEn.name1);
    		    		ng.name2.setText(wEn.name2);
    		    		ng.name3.setText(wEn.name3);
    		    		ng.niveauIA.setText(wEn.niveauIAlabel);
    		    		ng.duree.setText(wEn.duree);
    		    		ng.br1.setText(wEn.IAFacile);
    		    		ng.br2.setText(wEn.IAMedium);
    		    		ng.br3.setText(wEn.IADifficile);
    		    		ng.choisirTerrain.setText(wEn.choisirTerrain);
    		    		ng.choisirTerrainlabel.setText(wEn.choisirTerrainlabel);
    		    		ng.retour.setText(wEn.retour);
    		    		ng.jouer.setText(wEn.jouer);
    		    		
    		    		//cT.valider.setText(wEn.validerPersonnaliser);
    		    		//cP.valider.setText(wEn.validerPersonnaliser);
    		    		
    		    		cp.titre.setText(wEn.charger);
    		    		cp.retour.setText(wEn.retour);
    		    		cp.jouer.setText(wEn.jouer);
    		    		
    		    		jr.titre.setText(wEn.reseau);
    		    		jr.creer.setText(wEn.creerPartieReseau);
    		    		jr.rejoindre.setText(wEn.rejoindrePartieReseau);
    		    		jr.menuPrincipal.setText(wEn.menuPrincipal);
    		    	
    		    		crr.titre.setText(wEn.creerPartieReseau);
    		    		crr.nomJoueur.setText(wEn.nomJoueurlabel);
    		    		crr.name.setText(wEn.nomJoueurTextArea);
    		    		crr.retour.setText(wEn.retour);
    		    		
    		    		ajr.titre.setText(wEn.attenteTitre);
    		    		ajr.codelabel.setText(wEn.codelabel);
    		    		ajr.retour.setText(wEn.retour);
 
    		    		staticConfig.blang = false;
    		    		
    				} else {
    					drapeauFr = ImageIO.read(this.getClass().getResourceAsStream(("img/drapeaufr.png"))).getScaledInstance(40, 40, Image.SCALE_DEFAULT); 
    		    		drapeau.setIcon(new ImageIcon(drapeauFr));
    		    		words wFr = objectMapper.readValue(this.getClass().getResourceAsStream("languesFr.json"), words.class);
    		    		menu.nouvelle.setText(wFr.newgame);
    		    		menu.charger.setText(wFr.charger);
    		    		menu.reseau.setText(wFr.reseau);
    		    		menu.regles.setText(wFr.regles);
    		    		menu.quitter.setText(wFr.quit);
    		    		
    		    		ng.titre.setText(wFr.newgame);
    		    		ng.humain.setText(wFr.humain);
    		    		ng.ordinateur.setText(wFr.ordinateur);
    		    		ng.jouerContre.setText(wFr.jouerContre);
    		    		ng.nomJoueur.setText(wFr.nomJoueur1);
    		    		ng.nomJoueur2.setText(wFr.nomJoueur2);
    		    		ng.joueur1.setText(wFr.joueur1);
    		    		ng.joueur2.setText(wFr.joueur2);
    		    		ng.varianteCheckbox.setText(wFr.variante);
    		    		ng.illimite.setText(wFr.illimite);
    		    		ng.priorite.setText(wFr.priorite);
    		    		ng.choixPersoJoueur1.setText(wFr.personnaliser);
    		    		ng.choixPersoJoueur2.setText(wFr.personnaliser);
    		    		ng.name1.setText(wFr.name1);
    		    		ng.name2.setText(wFr.name2);
    		    		ng.name3.setText(wFr.name3);
    		    		ng.niveauIA.setText(wFr.niveauIAlabel);
    		    		ng.duree.setText(wFr.duree);
    		    		ng.choisirTerrain.setText(wFr.choisirTerrain);
    		    		ng.choisirTerrainlabel.setText(wFr.choisirTerrainlabel);
    		    		ng.retour.setText(wFr.retour);
    		    		ng.jouer.setText(wFr.jouer);
    		    		
    		    		cp.titre.setText(wFr.charger);
    		    		cp.retour.setText(wFr.retour);
    		    		cp.jouer.setText(wFr.jouer);
    		    		
    		    		jr.titre.setText(wFr.reseau);
    		    		jr.creer.setText(wFr.creerPartieReseau);
    		    		jr.rejoindre.setText(wFr.rejoindrePartieReseau);
    		    		jr.menuPrincipal.setText(wFr.menuPrincipal);
    		    		
    		    		crr.titre.setText(wFr.creerPartieReseau);
    		    		crr.nomJoueur.setText(wFr.nomJoueurlabel);
    		    		crr.name.setText(wFr.nomJoueurTextArea);
    		    		crr.retour.setText(wFr.retour);
    		    		
    		    		ajr.titre.setText(wFr.attenteTitre);
    		    		ajr.codelabel.setText(wFr.codelabel);
    		    		ajr.retour.setText(wFr.retour);
    		    		    		    		
    		    		staticConfig.blang = true;
    		    		
    				}
    			}
    			catch (Exception e1) {
    				System.out.println(e1);
    			}
            } 
        } );

		
		sound.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) { 
            	try {
    				if ( staticConfig.bmute == false) { 
    		    		sound.setIcon(new ImageIcon(mute));
    		    		 staticConfig.bmute = true;
    				} else {
    		    		sound.setIcon(new ImageIcon(son));
    		    		 staticConfig.bmute = false;
    				}
    					
    	    	}
    	    	catch (Exception e1) {
    	    		System.out.println(e1);
    	    	}
            } 
        } );
        
		menu.add(drapeau);
		menu.add(sound);
	    this.setLocationRelativeTo(null);
	    this.add(menu);
	    this.setVisible(true);
	}
	
	
	public void fenetreNouvellePartie() {
		ps.play("son/buttonClick.wav",  staticConfig.bmute);
		this.setSize(700, 600);
		ng.add(sound);
		ng.add(drapeau);		
		this.setContentPane(ng);
		
		if (staticConfig.blang == false) {
			this.setTitle("New Game");
		} else {
			this.setTitle("Nouvelle partie");
		}
		
		this.repaint();
		this.revalidate();
	}

	
	public void fenetreChargerPartie() {
		ps.play("son/buttonClick.wav",  staticConfig.bmute);
		this.setSize(700, 450);
		cp.add(sound);
		cp.add(drapeau);
		this.setContentPane(cp);
		
		if (staticConfig.blang == false) {
			this.setTitle("Load Game");
		} else {
			this.setTitle("Charger partie");
		}
		
		this.repaint();
		this.revalidate();
	}
	
	
	public void fenetreJouerEnReseau() {
		ps.play("son/buttonClick.wav",  staticConfig.bmute);
		this.setSize(600, 511);
		this.setSize(600, 510);
		jr.add(sound);
		jr.add(drapeau);
		this.setContentPane(jr);
		if (staticConfig.blang == false) {
			this.setTitle("Play online");
		} else {
			this.setTitle("Jouer en réseau");
		}
		
		this.repaint();
		this.revalidate();
	}
	
	
	public void retourMenuPrincipal() {
		ps.play("son/buttonClick.wav",  staticConfig.bmute);
		this.setSize(600, 511);
		this.setSize(600, 510);
		this.setLocationRelativeTo(null);
		menu.add(drapeau);
		menu.add(sound);
		this.setContentPane(menu);
		if (staticConfig.blang == false) {
			this.setTitle("Main Menu");
		} else {
			this.setTitle("Menu principal");
		}
		
		this.repaint();
		this.revalidate();
	}
	
	
	public void fenetreCreerPartieReseau() {
		ps.play("son/buttonClick.wav",  staticConfig.bmute);
		this.setSize(600, 401);
		this.setSize(600, 400);
		crr.add(sound);
		crr.add(drapeau);
		this.setContentPane(crr);
		if (staticConfig.blang == false) {
			this.setTitle("Create online game");
		} else {
			this.setTitle("Créer une partie en réseau");
		}
		
		this.repaint();
		this.revalidate();
	}
	
	
	public void fenetreRejoindrePartieReseau() {
		ps.play("son/buttonClick.wav",  staticConfig.bmute);
		this.setSize(600, 401);
		this.setSize(600, 400);
		rpr.add(sound);
		rpr.add(drapeau);
		this.setContentPane(rpr);
		if (staticConfig.blang == false) {
			this.setTitle("Join online game");
		} else {
			this.setTitle("Rejoindre une partie");
		}
		
		this.repaint();
		this.revalidate();
	}
	
	
	public void fenetreAttenteJoueurReseau() {
		ps.play("son/buttonClick.wav", staticConfig.bmute);
		this.setSize(600, 401);
		this.setSize(600, 400);
		ajr.add(sound);
		ajr.add(drapeau);
		this.setContentPane(ajr);
		if (staticConfig.blang == false) {
			this.setTitle("Waiting for the 2nd player");
		} else {
			this.setTitle("Attente du 2ème joueur");
		}
		
		this.repaint();
		this.revalidate();
	}
	
	
	public void fenetreRegles() {
		ps.play("son/buttonClick.wav",  staticConfig.bmute);
		this.setSize(800, 620);
		this.setLocationRelativeTo(null);
		this.setContentPane(r);
		if (staticConfig.blang == false) {
			this.setTitle("Rules");
		} else {
			this.setTitle("Règles du jeu");
		}
		
		this.repaint();
		this.revalidate();
	}
	
	
	public void quit() {
		ps.play("son/buttonClick.wav",  staticConfig.bmute);
		if (staticConfig.blang == false) {
			msgBox.MessageBox("Do you want to quit ?", "Quit", this);
		} else {
			msgBox.MessageBox("Voulez-vous quitter le jeu ? ", "Quitter", this);
		}
		
	}
		
}
