package Diaballik.Vue;

import java.awt.*;
import java.io.IOException;
import javax.imageio.ImageIO;

import Diaballik.Models.IA.*;
import Diaballik.Models.Jeu;
import Diaballik.Models.PieceType;

public class VuePlateau extends PlateauGraphique {

    private static final long serialVersionUID = 1L;

    Image caseA, caseB, pionA_ballon, pionB_ballon, pionA_bas, pionB_bas;
    int hauteurCase, largeurCase;
    Jeu j;
    public static boolean viewArrow = true;

    VuePlateau(Jeu jeu) {
        try {
            j = jeu;
            j.ajouteObservateur(this);
            if (NewGame.cp1 == null) {
                pionA_ballon = ImageIO.read(this.getClass().getResourceAsStream(("/pionA_ballon.png")));
                pionA_bas = ImageIO.read(this.getClass().getResourceAsStream(("/pionA_bas.png")));
            } else {
                pionA_bas = NewGame.cp1.panel.joueur1;
                pionA_ballon = NewGame.cp1.panel.pionBallon;
            }
            if (NewGame.cp2 == null) {
                pionB_ballon = ImageIO.read(this.getClass().getResourceAsStream(("/pionB_ballon.png")));
                pionB_bas = ImageIO.read(this.getClass().getResourceAsStream(("/pionB_bas.png")));
            } else {
                pionB_bas = NewGame.cp2.panel.joueur2;
                pionB_ballon = NewGame.cp2.panel.pionBallon;
            }

        } catch (IOException e) {

        }
    }

    @Override
    int hauteurCase() {
        return hauteurCase;
    }

    @Override
    int largeurCase() {
        return largeurCase;
    }

    @Override
    void tracerPlateau() {

        try {
            if (NewGame.cp1 == null) {
                pionA_ballon = ImageIO.read(this.getClass().getResourceAsStream(("/pionA_ballon.png")));
                pionA_bas = ImageIO.read(this.getClass().getResourceAsStream(("/pionA_bas.png")));
            } else {
                pionA_bas = NewGame.cp1.panel.joueur1;
                pionA_ballon = NewGame.cp1.panel.pionBallon;
            }
            if (NewGame.cp2 == null) {
                pionB_ballon = ImageIO.read(this.getClass().getResourceAsStream(("/pionB_ballon.png")));
                pionB_bas = ImageIO.read(this.getClass().getResourceAsStream(("/pionB_bas.png")));
            } else {
                pionB_bas = NewGame.cp2.panel.joueur2;
                pionB_ballon = NewGame.cp2.panel.pionBallon;
            }

        } catch (IOException e) {

        }

        largeurCase = largeur() / 7;
        hauteurCase = hauteur() / 7;
        // On prend des cases carrées
        largeurCase = Math.min(largeurCase, hauteurCase);
        hauteurCase = largeurCase;

        // Tracé du plateau
        for (int ligne = 0; ligne < 7; ligne++) {
            for (int colonne = 0; colonne < 7; colonne++) {
                int x = colonne * largeurCase;
                int y = ligne * hauteurCase;
                // int marque = n.marque(ligne, colonne);
                // Tracé du sol
                if (ligne % 2 == 0) {
                    if (colonne % 2 == 0) {
                        tracerDamier(Color.decode(j.config.damierA), x, y, largeurCase, hauteurCase);
                    } else {
                        tracerDamier(Color.decode(j.config.damierB), x, y, largeurCase, hauteurCase);
                    }
                } else {
                    if (colonne % 2 == 0) {
                        tracerDamier(Color.decode(j.config.damierB), x, y, largeurCase, hauteurCase);
                    } else {
                        tracerDamier(Color.decode(j.config.damierA), x, y, largeurCase, hauteurCase);
                    }
                }
                // Affichage des pions
                if (j.tr._terrain[ligne][colonne].Type == PieceType.Black) {
                    if (j.tr._terrain[ligne][colonne].HasBall == true) {
                        tracer(pionB_ballon, x, y, largeurCase, hauteurCase);
                    } else {
                        tracer(pionB_bas, x, y, largeurCase, hauteurCase);
                    }
                } else if (j.tr._terrain[ligne][colonne].Type == PieceType.White) {
                    if (j.tr._terrain[ligne][colonne].HasBall == true) {
                        tracer(pionA_ballon, x, y, largeurCase, hauteurCase);
                    } else {
                        tracer(pionA_bas, x, y, largeurCase, hauteurCase);
                    }
                }
                // Affichage des marques
                if (j.tr._terrain[ligne][colonne].SelectionPasse == true)
                    tracerCarre(Color.yellow, x, y, largeurCase, hauteurCase);
                else if (j.tr._terrain[ligne][colonne].SelectionDeplacement == true)
                    tracerCarre(Color.yellow, x, y, largeurCase, hauteurCase);
                else if (j.tr._terrain[ligne][colonne].PossibleDeplacement == true)
                    tracerCercle(x + largeurCase / 2, y + hauteurCase / 2);
                else if (j.tr._terrain[ligne][colonne].PossiblePasse == true)
                    tracerCarre(Color.green, x, y, largeurCase, hauteurCase);
            }
        }
        // Affichage des feeedback mouvements
        if (viewArrow) {
            for (Couple cpl2 : j.listeDeplacementJ2) {
                drawArrowLine(Color.yellow, cpl2.p2.c * largeurCase + largeurCase / 2,
                        cpl2.p2.l * hauteurCase + hauteurCase / 2, cpl2.p1.c * largeurCase + largeurCase / 2,
                        cpl2.p1.l * hauteurCase + hauteurCase / 2, 4, 4, false, largeurCase, hauteurCase);
            }

            for (Couple cpl : j.listeDeplacementJ1) {
                drawArrowLine(Color.blue, cpl.p2.c * largeurCase + largeurCase / 2,
                        cpl.p2.l * hauteurCase + hauteurCase / 2, cpl.p1.c * largeurCase + largeurCase / 2,
                        cpl.p1.l * hauteurCase + hauteurCase / 2, 4, 4, false, largeurCase, hauteurCase);
            }

            for (Couple cpl : j.listePasseJ1) {
                drawArrowLine(Color.green, cpl.p1.c * largeurCase + largeurCase / 2,
                        cpl.p1.l * hauteurCase + hauteurCase / 2, cpl.p2.c * largeurCase + largeurCase / 2,
                        cpl.p2.l * hauteurCase + hauteurCase / 2, 6, 6, true, largeurCase, hauteurCase);
            }

            for (Couple cpl : j.listePasseJ2) {
                drawArrowLine(Color.green, cpl.p1.c * largeurCase + largeurCase / 2,
                        cpl.p1.l * hauteurCase + hauteurCase / 2, cpl.p2.c * largeurCase + largeurCase / 2,
                        cpl.p2.l * hauteurCase + hauteurCase / 2, 6, 6, true, largeurCase, hauteurCase);
            }
        }
        // Affichage suggestion
        if (j.sugState != null) {
            if (j.sugState.firstMove != null)
                drawArrowLine(Color.ORANGE, j.sugState.firstMove.From.c * largeurCase + largeurCase / 2,
                        j.sugState.firstMove.From.l * hauteurCase + hauteurCase / 2,
                        j.sugState.firstMove.To.c * largeurCase + largeurCase / 2,
                        j.sugState.firstMove.To.l * hauteurCase + hauteurCase / 2, 8, 8, false, largeurCase,
                        hauteurCase);
            if (j.sugState.secondMove != null)
                drawArrowLine(Color.ORANGE, j.sugState.secondMove.From.c * largeurCase + largeurCase / 2,
                        j.sugState.secondMove.From.l * hauteurCase + hauteurCase / 2,
                        j.sugState.secondMove.To.c * largeurCase + largeurCase / 2,
                        j.sugState.secondMove.To.l * hauteurCase + hauteurCase / 2, 8, 8, false, largeurCase,
                        hauteurCase);
            if (j.sugState.pass != null)
                drawArrowLine(Color.ORANGE, j.sugState.pass.From.c * largeurCase + largeurCase / 2,
                        j.sugState.pass.From.l * hauteurCase + hauteurCase / 2,
                        j.sugState.pass.To.c * largeurCase + largeurCase / 2,
                        j.sugState.pass.To.l * hauteurCase + hauteurCase / 2, 8, 8, true, largeurCase, hauteurCase);
        }
    }
}
