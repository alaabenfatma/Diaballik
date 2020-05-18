package Diaballik.Vue;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import com.fasterxml.jackson.databind.ObjectMapper;

public class MenuEnJeu extends JPanel {

    private static final long serialVersionUID = 1L;
    JButton reprendre = new JButton("Reprendre");
    JButton sauvegarde = new JButton("Sauvegarder");
    JButton nouvelle = new JButton("Nouvelle Partie");
    JButton charger = new JButton("Charger");
    JButton menup = new JButton("Menu principal");
    JButton quit = new JButton("Quitter");
    playSound ps = new playSound();
    Image icon = Toolkit.getDefaultToolkit().getImage("src/main/java/Diaballik/Vue/img/pionA_ballon.png");  
	ObjectMapper objectMapper = new ObjectMapper();
	static ihm interHM;
	Menu menu;
	Menu m;
	NewGame ng;
	ChargerPartie cp;
	JouerReseau jr;
	AttenteJoueurReseau ajr;
	CreerPartieReseau crr;
	Regles r;
	RejoindrePartieReseau rpr;
	ihm i;
	

    CollecteurEvenements control;

    public void SetControl( CollecteurEvenements c){
    control = c;

    }
    
    static void setIHM(ihm i) {
        interHM = i;
    }
    
    public MenuEnJeu(final JFrame frame, final JFrame plateau) {
        frame.setSize(600, 530);
        frame.setTitle("Menu");
        this.setLayout(null);
        reprendre.setBounds(220, 20, 150, 50);
        sauvegarde.setBounds(220, 95, 150, 50);
        nouvelle.setBounds(220, 170, 150, 50);
        charger.setBounds(220, 245, 150, 50);
        menup.setBounds(220, 320, 150, 50);
        quit.setBounds(220, 395, 150, 50);


    	try {
			if (staticConfig.blang == false) {
	    		words wEn = objectMapper.readValue(this.getClass().getResourceAsStream("languesEn.json"), words.class);
	    		
	    		nouvelle.setText(wEn.newgame);
	    		sauvegarde.setText(wEn.sauvegarde);
	    		reprendre.setText(wEn.reprendre);
	    		charger.setText(wEn.charger);
	    		menup.setText(wEn.menuPrincipal);
	    		quit.setText(wEn.quit);
	    		
	    		staticConfig.blang = true;
	    		
			} else {
	    		words wFr = objectMapper.readValue(this.getClass().getResourceAsStream("languesFr.json"), words.class);
	    		
	    		nouvelle.setText(wFr.newgame);
	    		sauvegarde.setText(wFr.sauvegarde);
	    		reprendre.setText(wFr.reprendre);
	    		charger.setText(wFr.charger);
	    		menup.setText(wFr.menuPrincipal);
	    		quit.setText(wFr.quit);
	    		
	    		staticConfig.blang = false;
	    		
			}
		}
		catch (Exception e1) {
			System.out.println(e1);
		}
    
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        
        this.add(reprendre);
        this.add(sauvegarde);
        this.add(nouvelle);
        this.add(charger);
        this.add(menup);
        this.add(quit);
       
        
        reprendre.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (Plateau.timer != null)
                    Plateau.timer.start();
                plateau.setVisible(true);
                frame.setVisible(false);
            }
        });

        sauvegarde.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                control.toucheClavier("Save");
            }
        });

        nouvelle.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (Plateau.timer != null)
                    Plateau.timer.restart();
                frame.setVisible(false);
                plateau.setVisible(false);
                ihm i = new ihm();
                i.setSize(700, 600);
                NewGame m = new NewGame(i);
                i.setContentPane(m);
                i.repaint();
                i.revalidate();
            }
        });

        charger.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                ihm i = new ihm();
                ChargerPartie m = new ChargerPartie(i, true);
                i.setSize(700, 450);
                i.setContentPane(m);
                i.repaint();
                i.revalidate();
            }
        });

        menup.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                plateau.setVisible(false);
                i = new ihm();
                i.retourMenuPrincipal();
            }
        });

        quit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ps.play("son/buttonClick.wav", staticConfig.bmute);

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

        this.setVisible(true);
    }
    

}