package Diaballik.Vue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class msgBox {
	
    public static void MessageBox(String msg, String titre, JFrame i) {
    	 
    	JFrame frame = new JFrame();
        int result = JOptionPane.showConfirmDialog(frame, msg, titre, JOptionPane.YES_NO_OPTION, 
        		JOptionPane.WARNING_MESSAGE);
        if (result == JOptionPane.YES_OPTION) {
        	/*frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        	i.dispose();
            i.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);*/
            System.exit(0);
        } 
        else if (result == JOptionPane.NO_OPTION) {
        	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }
    }
}