package Diaballik.Vue;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class Menu extends JFrame implements ActionListener{
	
	Button button1 = new Button("Nouvelle partie");
	Button button2 = new Button("Charger partie");
	Button button3 = new Button("Jouer en réseau");
	Button button4 = new Button("Règles");
	Button button5 = new Button("Quitter");
	Button button6 = new Button("Fr");
	Button button7 = new Button("En");
	
	JPanel panel = new JPanel();
	
	public Menu() {
		this.setTitle("Menu principal");
		this.setSize(700, 500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        panel.setLayout(null);
        
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
