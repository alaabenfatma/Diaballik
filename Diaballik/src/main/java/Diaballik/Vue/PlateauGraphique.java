package Diaballik.Vue;

import java.awt.*;

import javax.swing.JComponent;

import Diaballik.Patterns.Observateur;

import java.awt.geom.AffineTransform;

public abstract class PlateauGraphique extends JComponent implements Observateur {

    private static final long serialVersionUID = 1L;

    Graphics2D drawable;

    protected void tracer(Image i, int x, int y, int l, int h) {
        drawable.drawImage(i, x, y, l, h, null);
    }

    protected void tracerCarre(Color couleur, int x, int y, int l, int h) {
        int s = l / 8;
        int nbPixel = 3;
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

    private final int ARR_SIZE = 10;

    protected void drawArrow(Color couleur, int x1, int y1, int x2, int y2) {

        drawable.setColor(couleur);
        drawable.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        double dx = x2 - x1, dy = y2 - y1;
        double angle = Math.atan2(dy, dx);
        int len = (int) Math.sqrt(dx * dx + dy * dy);
        AffineTransform at = AffineTransform.getTranslateInstance(x1, y1);
        at.concatenate(AffineTransform.getRotateInstance(angle));
        drawable.transform(at);

        // Draw horizontal arrow starting in (0, 0)
        // drawable.setStroke(new BasicStroke(3));
        drawable.drawLine(0, 0, len, 0);
        drawable.fillPolygon(new int[] { len, len - ARR_SIZE, len - ARR_SIZE, len },
                new int[] { 0, -ARR_SIZE, ARR_SIZE, 0 }, 4);
    }

    protected void tracerCercle(int x, int y) {
        int r = 10;
        x = x - (r / 2);
        y = y - (r / 2);
        drawable.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        drawable.setColor(Color.green);
        drawable.fillOval(x, y, r, r);
    }

    protected void drawArrowLine(Color couleur, int x1, int y1, int x2, int y2, int d, int h, boolean dashedBool,
            int hauteur, int largeur) {

        if (x1 == x2) {
            if (y1 < y2)
                y2 -= hauteur / 2;
            else
                y2 += hauteur / 2;
        } else if (y1 == y2) {
            if (x1 < x2)
                x2 -= hauteur / 2;
            else
                x2 += hauteur / 2;
        } else {
            if (y1 < y2)
                y2 -= hauteur / 3;
            else
                y2 += hauteur / 3;
            if (x1 < x2)
                x2 -= hauteur / 3;
            else
                x2 += hauteur / 3;
        }

        drawable.setColor(couleur);
        drawable.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        if (dashedBool) {
            Stroke dashed = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[] { 9 }, 0);
            drawable.setStroke(dashed);
        } else
            drawable.setStroke(new BasicStroke(4));
        int dx = x2 - x1, dy = y2 - y1;
        double D = Math.sqrt(dx * dx + dy * dy);
        double xm = D - d, xn = xm, ym = h, yn = -h, x;
        double sin = dy / D, cos = dx / D;

        x = xm * cos - ym * sin + x1;
        ym = xm * sin + ym * cos + y1;
        xm = x;

        x = xn * cos - yn * sin + x1;
        yn = xn * sin + yn * cos + y1;
        xn = x;

        drawable.drawLine(x1, y1, x2, y2);
        drawable.drawLine(x2, y2, (int) xm, (int) ym);
        drawable.drawLine(x2, y2, (int) xn, (int) yn);

        // drawable.fillPolygon(xpoints, ypoints, 3);
    }

    protected void tracerDamier(Color couleur, int x, int y, int l, int h) {
        drawable.setColor(couleur);
        drawable.fillRect(x, y, l, h);
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
