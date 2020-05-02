package Diaballik.Vue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class CreerPartieReseau extends JPanel implements ActionListener{
	JLabel titre = new JLabel("Créer une partie");
	JButton ok = new JButton("Ok");
	JButton retour = new JButton("Retour");
	ihm i;
	
	public CreerPartieReseau(ihm ihm) {
		i = ihm;
		this.setLayout(null);
		titre.setBounds(230, 0, 300, 100);
		ok.setBounds(350, 390, 120, 40);
		retour.setBounds(210, 390, 120, 40);
		this.add(ok);
		this.add(retour);
		this.add(titre);
		
		Font font = new Font("Arial",Font.BOLD,30);
		titre.setFont(font);
		ok.addActionListener(this);
		retour.addActionListener(this);
		this.setVisible(true);
	}
	
	
	
	
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getSource() == retour) {
			i.fenetreJouerEnReseau();
		}
		if(arg0.getSource() == ok) {
			i.fenetreAttenteJoueurReseau();
		}
		
	}

}
