package Diaballik.Vue;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdaptateurTemps implements ActionListener {
	CollecteurEvenements control;

	AdaptateurTemps(CollecteurEvenements c) {
		control = c;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		control.tictac();
	}
}
