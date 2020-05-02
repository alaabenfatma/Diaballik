package Diaballik.Vue;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ChargerPartie extends JPanel implements ActionListener{
	
	JLabel titre = new JLabel("Charger partie");
	JButton retour = new JButton("Retour");
	JButton jouer = new JButton("Jouer");
	
	public ChargerPartie() {
		this.setLayout(null);
		titre.setBounds(240, 0, 300, 100);
		jouer.setBounds(350, 390, 120, 40);
		retour.setBounds(210, 390, 120, 40);
		this.add(jouer);
		this.add(retour);
		this.add(titre);
		
		Font font = new Font("Arial",Font.BOLD,30);
		titre.setFont(font);
		retour.addActionListener(this);
		//this.add(this);
		this.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getSource() == retour) {
			new Menu();
		}
		if(arg0.getSource() == jouer) {
			
		}
	}
}
