package Diaballik.Vue;

import java.awt.*;

import javax.swing.JComponent;

import Diaballik.Patterns.Observateur;

public abstract class PlateauGraphique extends JComponent implements Observateur {

    private static final long serialVersionUID = 1L;
    
    Graphics2D drawable;

    protected void tracer(Image i, int x, int y, int l, int h) {
        drawable.drawImage(i, x, y, l, h, null);
    }

    protected void tracerCarre(Color couleur, int x, int y, int l, int h) {
        int s = l / 8;
        int nbPixel = 4;
        drawable.setColor(couleur);
        drawable.setStroke(new BasicStroke(nbPixel * 2));
        drawable.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        drawable.drawLine(x + nbPixel, y + nbPixel, x + s + nbPixel, y + nbPixel);
        drawable.drawLine(x + nbPixel, y + nbPixel, x + nbPixel, y + s + nbPixel);
        drawable.drawLine(x + l - nbPixel, y + h - nbPixel, x + l - nbPixel, y + h - s - nbPixel);
        drawable.drawLine(x + l - nbPixel, y + h - nbPixel, x + l - s - nbPixel, y + h - nbPixel);
        drawable.drawLine(x + nbPixel, y + h - nbPixel, x + nbPixel, y + h - s - nbPixel);
        drawable.drawLine(x + nbPixel, y + h - nbPixel, x + s, y + h - nbPixel);
        drawable.drawLine(x + l - nbPixel, y + nbPixel, x + l - nbPixel, y + s + nbPixel);
        drawable.drawLine(x + l - nbPixel, y + nbPixel, x + l - s - nbPixel, y + nbPixel);
    }

    abstract void tracerPlateau();

    public void paintComponent(Graphics g) {
        drawable = (Graphics2D) g;

        // On efface tout
        drawable.clearRect(0, 0, largeur(), hauteur());
        tracerPlateau();
    }

    int hauteur() {
        return getHeight();
    }

    int largeur() {
        return getWidth();
    }

    abstract int hauteurCase();

    abstract int largeurCase();

    @Override
    public void miseAJour() {
        repaint();
    }
}
