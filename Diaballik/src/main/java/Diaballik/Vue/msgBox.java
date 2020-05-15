package Diaballik.Vue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class msgBox {

    public static void MessageBox(String msg, String titre, JFrame i) {

        JFrame frame = new JFrame();
        int result = JOptionPane.showConfirmDialog(frame, msg, titre, JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE);
        if (result == JOptionPane.YES_OPTION) {
            System.exit(0);
        } else if (result == JOptionPane.NO_OPTION) {
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }
    }

    public static int msgYesNo(String msg, String titre) {
        int dialogButton = JOptionPane.YES_NO_OPTION;
        int dialogResult = JOptionPane.showConfirmDialog(null, msg, titre, dialogButton);
        return dialogResult; //0 = yes, 1 = no
    }
    
}