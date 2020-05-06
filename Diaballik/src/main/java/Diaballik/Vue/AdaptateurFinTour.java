package Diaballik.Vue;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdaptateurFinTour implements ActionListener {

    CollecteurEvenements control;

    AdaptateurFinTour(CollecteurEvenements c) {
        control = c;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        control.toucheClavier("FinTour");
    }
}