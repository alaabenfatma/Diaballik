package Diaballik.Vue;

import javax.swing.JOptionPane;


public class msgBox {
    public static void MessageBox(String msg, String titre)
    {
        JOptionPane.showMessageDialog(null, msg, titre, JOptionPane.INFORMATION_MESSAGE);
        
    }
}