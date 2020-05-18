package Diaballik.Vue;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import com.bric.colorpicker.ColorPicker;
import com.bric.colorpicker.listeners.ColorListener;
import com.bric.colorpicker.models.ColorModel;
import java.awt.Color;
import Diaballik.Models.ConfigJeu;

public class TerrainCustomize {
    ConfigJeu cfg;
    JFrame frame = new JFrame();

    public TerrainCustomize(ConfigJeu _cfg) {
        this.cfg = _cfg;
        final ColorPicker colorPicker1 = new ColorPicker(true, true);
        colorPicker1.setColor(Color.BLUE);
        final ColorPicker colorPicker2 = new ColorPicker(true, true);
        colorPicker2.setColor(Color.CYAN);

        final JButton color1 = new JButton();
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
                        cfg.damierA = String.format("#%02x%02x%02x", colorPicker1.getColor().getRed(), colorPicker1.getColor().getGreen(), colorPicker1.getColor().getBlue());  
                    }
                });

            }
        });

        final JButton color2 = new JButton();
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
                        cfg.damierB = String.format("#%02x%02x%02x", colorPicker2.getColor().getRed(), colorPicker1.getColor().getGreen(), colorPicker1.getColor().getBlue());  
                    }
                });

            }
        });

        JButton ok = new JButton();
        frame.setSize(400, 400);
        color1.setBounds(400 - 80, 0, 40, 40);
        color2.setBounds(400 - 80, 50, 40, 40);
        ok.setBounds(400 - 80, 400 - 80, 40, 40);
        JPanel board = new JPanel();
        board.setBounds(0, 0, 400, 300);

        frame.add(color1);
        frame.add(color2);
        frame.add(ok);
        frame.add(board);
        frame.setVisible(true);
    }
}
