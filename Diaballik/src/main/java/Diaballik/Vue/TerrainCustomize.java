package Diaballik.Vue;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.*;

import com.bric.colorpicker.ColorPicker;
import com.bric.colorpicker.listeners.ColorListener;
import com.bric.colorpicker.models.ColorModel;
import java.awt.Color;
import java.awt.*;
import Diaballik.Models.ConfigJeu;

public class TerrainCustomize {
    ConfigJeu cfg;
    JFrame frame = new JFrame();
    JPanel board;

    public TerrainCustomize(ConfigJeu _cfg) {

        this.cfg = _cfg;
        board = new boardZone(cfg);
        final ColorPicker colorPicker1 = new ColorPicker(true, true);
        colorPicker1.setColor(Color.BLUE);
        final ColorPicker colorPicker2 = new ColorPicker(true, true);
        colorPicker2.setColor(Color.CYAN);

        final JButton color1 = new JButton("1");
        color1.addActionListener(new ActionListener() {
            JFrame pickerFrame = new JFrame();

            @Override
            public void actionPerformed(ActionEvent e) {
                pickerFrame.setSize(400, 400);
                pickerFrame.add(colorPicker1);
                pickerFrame.setVisible(true);
                colorPicker1.addColorListener(new ColorListener() {

                    @Override
                    public void colorChanged(ColorModel colorModel) {
                        color1.setBackground(colorPicker1.getColor());
                        cfg.damierA = String.format("#%02x%02x%02x", colorPicker1.getColor().getRed(),
                                colorPicker1.getColor().getGreen(), colorPicker1.getColor().getBlue());
                        board.repaint();
                    }
                });

            }
        });

        final JButton color2 = new JButton("2");
        color2.addActionListener(new ActionListener() {
            JFrame pickerFrame = new JFrame();

            @Override
            public void actionPerformed(ActionEvent e) {
                pickerFrame.setSize(400, 400);
                pickerFrame.add(colorPicker2);
                pickerFrame.setVisible(true);
                colorPicker2.addColorListener(new ColorListener() {
                    @Override
                    public void colorChanged(ColorModel colorModel) {
                        color2.setBackground(colorPicker2.getColor());
                        cfg.damierB = String.format("#%02x%02x%02x", colorPicker2.getColor().getRed(),
                                colorPicker1.getColor().getGreen(), colorPicker1.getColor().getBlue());
                        board.repaint();
                    }
                });

            }
        });

        final  JButton ok = new JButton("Ok");
        frame.setSize(400, 400);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        
        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               frame.dispose(); 
            }
         });

            

        frame.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent evt) {
                board.setLocation(0, 50);
                color1.setBounds((frame.getWidth() / 2) - 150, 0, 50, 40);
                color2.setBounds((frame.getWidth() / 2) - 90, 0, 50, 40);
                ok.setBounds(frame.getWidth() - 70, 0, 50, 40);
            }
        });
        frame.add(color1);
        frame.add(color2);
        frame.add(ok);
        frame.add(board);
        frame.setVisible(true);
       
    }

}

class boardZone extends JPanel {
    ConfigJeu cfg;

    Graphics2D drawable;

    protected boardZone(ConfigJeu _j) {
        setBounds(0, 50, 400, 350);
        cfg = _j;
    }

    protected void tracerDamier(Graphics2D drawable, Color couleur, int x, int y, int l, int h) {
        drawable.setColor(couleur);
        drawable.fillRect(x, y, l, h);
    }

    void drawSpecimen(Graphics2D g) {
        // Tracé du plateau

    }

    @Override
    public void paintComponent(Graphics g) {

        drawable = (Graphics2D) g;

        // On efface tout
        drawable.clearRect(0, 0, this.getWidth(), this.getHeight());
        int largeurCase = this.getWidth() / 7;
        int hauteurCase = this.getHeight() / 7;
        // On prend des cases carrées

        // Tracé du plateau
        for (int ligne = 0; ligne < 7; ligne++) {
            for (int colonne = 0; colonne < 7; colonne++) {
                int x = colonne * largeurCase;
                int y = ligne * hauteurCase;
                // int marque = n.marque(ligne, colonne);
                // Tracé du sol
                if (ligne % 2 == 0) {
                    if (colonne % 2 == 0) {
                        tracerDamier(drawable, Color.decode(cfg.damierA), x, y, largeurCase, hauteurCase);
                    } else {
                        tracerDamier(drawable, Color.decode(cfg.damierB), x, y, largeurCase, hauteurCase);
                    }
                } else {
                    if (colonne % 2 == 0) {
                        tracerDamier(drawable, Color.decode(cfg.damierB), x, y, largeurCase, hauteurCase);
                    } else {
                        tracerDamier(drawable, Color.decode(cfg.damierA), x, y, largeurCase, hauteurCase);
                    }
                }
            }
        }
    }
}
