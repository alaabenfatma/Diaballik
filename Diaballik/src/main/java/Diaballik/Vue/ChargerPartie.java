package Diaballik.Vue;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ChargerPartie extends JPanel implements ActionListener{
	
	JLabel label1 = new JLabel("Charger partie");
	JButton retour = new JButton("Retour");
	JButton jouer = new JButton("Jouer");
	
	public ChargerPartie() {
		this.setLayout(null);
		label1.setBounds(300, 0, 100, 100);
		jouer.setBounds(350, 390, 120, 40);
		retour.setBounds(210, 390, 120, 40);
		this.add(jouer);
		this.add(retour);
		this.add(label1);
		
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
