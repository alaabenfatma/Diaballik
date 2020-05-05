package Diaballik.Vue;


import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AdaptateurSouris extends MouseAdapter {
	PlateauGraphique p;
	CollecteurEvenements control;

	AdaptateurSouris(PlateauGraphique plat, CollecteurEvenements c) {
		p = plat;
		control = c;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		int l = e.getY() / p.hauteurCase();
		int c = e.getX() / p.largeurCase();
		control.clicSouris(l, c);
	}
}
