package Diaballik.Vue;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class AdaptateurClavier extends KeyAdapter {
	CollecteurEvenements control;

	AdaptateurClavier(CollecteurEvenements c) {
		control = c;
	}

	@Override
	public void keyPressed(KeyEvent event) {
		switch (event.getKeyCode()) {
			case KeyEvent.VK_U:
				control.toucheClavier("Undo");
				break;
			case KeyEvent.VK_R:
				control.toucheClavier("Redo");
				break;
			case KeyEvent.VK_Q:
				control.toucheClavier("Quit");
				break;
			case KeyEvent.VK_S:
				control.toucheClavier("Save");
				break;
			case KeyEvent.VK_ESCAPE:
				control.toucheClavier("Full");
				break;
		}
	}
}
