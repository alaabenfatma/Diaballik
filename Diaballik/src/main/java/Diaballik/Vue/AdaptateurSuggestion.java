package Diaballik.Vue;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdaptateurSuggestion implements ActionListener {

    CollecteurEvenements control;

    AdaptateurSuggestion(CollecteurEvenements c) {
        control = c;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        control.toucheClavier("Suggestion");
    }
}

