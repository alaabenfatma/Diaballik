package Diaballik;

import Diaballik.Models.*;
import Diaballik.Models.IA.*;

public class IA_vs_IA {
    public static void main(String[] args) {
        int white = 0, black = 0;
        for (int i = 0; i < 30; i++) {
            Jeu j = new Jeu();
            j.configurer(new ConfigJeu());
            j.config.setVariante(true);
            j.init();
            j.start();
            IA_easy easy = new IA_easy(j);
            MiniMax hard = new MiniMax(j, PieceType.White);
            MiniMax moyen = new MiniMax(j, PieceType.Black);
            while (Math.abs(Evaluator.scoreOfBoard(j.tr)) != 9999) {
               
                //Black
                //easy.joueTourIAEasy();
                hard.VanillaMiniMax(new State(j), 3, true);
                j.JoueTourIAMiniMax(hard.bestMove);
                //White
                moyen.VanillaMiniMax(new State(j), 1, false);
                j.JoueTourIAMiniMax(moyen.bestMove);
            }
            if (Evaluator.scoreOfBoard(j.tr) > 0) {
                white++;
            } else {
                black++;

            }
        }
        System.out.printf("Black wins : %d\nWhite wins : %d", black, white);
    }
}
