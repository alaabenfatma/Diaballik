/**
 * GIF ARTIST : https://www.pinterest.fr/dribbble/
 */
package Diaballik.Vue.Screens;

import javax.swing.*;
import java.awt.Frame;
public class LoadingScreen {
    private JFrame frame = new JFrame("AI is thinking...");

    public LoadingScreen() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {

                    Icon icon = new ImageIcon(this.getClass().getResource("loading.gif").getPath());
                    JLabel label = new JLabel(icon);
                    JTextField jtxtf = new JTextField();
                    frame.add(jtxtf);
                    frame.setUndecorated(true);
                    frame.getContentPane().add(label);
                    frame.pack();
                    frame.setLocationRelativeTo(null);
                    frame.setAlwaysOnTop(true);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }

    public void Show() {
        frame.setVisible(true);
        Frame[] allFrames = Frame.getFrames();
        for (Frame frame : allFrames) {
            frame.setEnabled(false);
        }
    }

    public void Hide() {
        frame.setVisible(false);
        Frame[] allFrames = Frame.getFrames();
        for (Frame frame : allFrames) {
            frame.setEnabled(true);
        }
    }

}