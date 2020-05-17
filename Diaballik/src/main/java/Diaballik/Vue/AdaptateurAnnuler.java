package Diaballik.Vue;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdaptateurAnnuler implements ActionListener {
	CollecteurEvenements control;

	AdaptateurAnnuler(CollecteurEvenements c) {
		control = c;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		control.toucheClavier("Undo");
	}
}
