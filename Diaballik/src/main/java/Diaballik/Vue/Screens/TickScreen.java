package Diaballik.Vue.Screens;

import javax.swing.*;
import java.awt.*;

public class TickScreen {

    private static JFrame frame = new JFrame("Validated");
    static TickScreen screen = new TickScreen();

    public TickScreen() {

        try {
            frame.setUndecorated(true);

            frame.setSize(50, 50);

            // frame.setBackground(new Color(0, 0, 0, 0));

            frame.setLocationRelativeTo(null);
            frame.setAlwaysOnTop(true);
            Icon icon = new ImageIcon(this.getClass().getResource("tick.gif").getPath());
            JLabel label = new JLabel(icon);
            label.setSize(100   , 150);
            frame.add(label);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void Show() {
        frame.setVisible(true);
    }

    public void Hide() {
        frame.setVisible(false);
        Frame[] allFrames = Frame.getFrames();
        for (Frame frame : allFrames) {
            frame.setEnabled(true);
        }
    }
}