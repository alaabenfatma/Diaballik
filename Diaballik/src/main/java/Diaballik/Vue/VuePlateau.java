package Diaballik.Vue;

import java.awt.*;
import java.io.IOException;
import javax.imageio.ImageIO;

import Diaballik.IA.Couple;
import Diaballik.Models.Jeu;
import Diaballik.Models.PieceType;

public class VuePlateau extends PlateauGraphique {

    private static final long serialVersionUID = 1L;

    Image caseA, caseB, pionA_ballon, pionB_ballon, pionA_bas, pionB_bas;
    int hauteurCase, largeurCase;
    Jeu j;
    public static boolean viewArrow;

    VuePlateau(Jeu jeu) {
        try {
            j = jeu;
            j.ajouteObservateur(this);
            caseA = ImageIO.read(this.getClass().getResourceAsStream(("img/caseA.png")));
            caseB = ImageIO.read(this.getClass().getResourceAsStream(("img/caseB.png")));
            pionA_ballon = ImageIO.read(this.getClass().getResourceAsStream(("img/pionA_ballon.png")));
            pionB_ballon = ImageIO.read(this.getClass().getResourceAsStream(("img/pionB_ballon.png")));
            pionA_bas = ImageIO.read(this.getClass().getResourceAsStream(("img/pionA_bas.png")));
            pionB_bas = ImageIO.read(this.getClass().getResourceAsStream(("img/pionB_bas.png")));

        } catch (IOException e) {
            System.out.println(e.toString());
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

    private void marque() {
    }

    @Override
    void tracerPlateau() {

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
                    if (colonne % 2 == 0)
                        tracer(caseA, x, y, largeurCase, hauteurCase);
                    else
                        tracer(caseB, x, y, largeurCase, hauteurCase);
                } else {
                    if (colonne % 2 == 0)
                        tracer(caseB, x, y, largeurCase, hauteurCase);
                    else
                        tracer(caseA, x, y, largeurCase, hauteurCase);
                }
                // Pose des pions
                if (j.tr._terrain[ligne][colonne].Type == PieceType.Black) {
                    if (j.tr._terrain[ligne][colonne].HasBall == true) {
                        tracer(pionA_ballon, x, y, largeurCase, hauteurCase);
                    } else {
                        tracer(pionA_bas, x, y, largeurCase, hauteurCase);
                    }
                } else if (j.tr._terrain[ligne][colonne].Type == PieceType.White) {
                    if (j.tr._terrain[ligne][colonne].HasBall == true) {
                        tracer(pionB_ballon, x, y, largeurCase, hauteurCase);
                    } else {
                        tracer(pionB_bas, x, y, largeurCase, hauteurCase);
                    }
                }
                // Traçage des marques
                if (j.tr._terrain[ligne][colonne].SelectionPasse == true)
                    tracerCarre(Color.yellow, x, y, largeurCase, hauteurCase);
                else if (j.tr._terrain[ligne][colonne].SelectionDeplacement == true)
                    tracerCarre(Color.yellow, x, y, largeurCase, hauteurCase);
                else if (j.tr._terrain[ligne][colonne].PossibleDeplacement == true)
                    // tracerCarre(Color.blue, x, y, largeurCase, hauteurCase);
                    tracerCercle(x + largeurCase / 2, y + hauteurCase / 2);
                else if (j.tr._terrain[ligne][colonne].PossiblePasse == true)
                    tracerCarre(Color.green, x, y, largeurCase, hauteurCase);
            }
        }
        // Tracage des feeedback mouvements
        if (viewArrow) {
            for (Couple cpl2 : j.listeDeplacementJ2) {
                drawArrowLine(Color.blue, cpl2.p2.c * largeurCase + largeurCase / 2,
                        cpl2.p2.l * hauteurCase + hauteurCase / 2, cpl2.p1.c * largeurCase + largeurCase / 2,
                        cpl2.p1.l * hauteurCase + hauteurCase / 2, 4, 4, false);
            }

            for (Couple cpl : j.listeDeplacementJ1) {
                drawArrowLine(Color.red, cpl.p2.c * largeurCase + largeurCase / 2,
                        cpl.p2.l * hauteurCase + hauteurCase / 2, cpl.p1.c * largeurCase + largeurCase / 2,
                        cpl.p1.l * hauteurCase + hauteurCase / 2, 4, 4, false);
            }

            for (Couple cpl : j.listePasseJ1) {
                drawArrowLine(Color.orange, cpl.p1.c * largeurCase + largeurCase / 2,
                        cpl.p1.l * hauteurCase + hauteurCase / 2, cpl.p2.c * largeurCase + largeurCase / 2,
                        cpl.p2.l * hauteurCase + hauteurCase / 2, 6, 6, true);
            }

            for (Couple cpl : j.listePasseJ2) {
                drawArrowLine(Color.ORANGE, cpl.p1.c * largeurCase + largeurCase / 2,
                        cpl.p1.l * hauteurCase + hauteurCase / 2, cpl.p2.c * largeurCase + largeurCase / 2,
                        cpl.p2.l * hauteurCase + hauteurCase / 2, 6, 6, true);
            }
        }
    }
}