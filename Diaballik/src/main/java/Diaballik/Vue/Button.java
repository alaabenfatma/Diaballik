package Diaballik.Vue;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
//import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
/*import java.io.File;
import java.io.IOException; 
import javax.imageio.ImageIO;*/
import javax.swing.JButton;


public class Button extends JButton implements MouseListener {

	  
	  private static final long serialVersionUID = 1L;
	  private String name;

	  public Button(String str){
	    super(str);
	    this.name = str;
	    
	  this.addMouseListener(this);
	  }

	  public void paintComponent(Graphics g){
	    Graphics2D g2d = (Graphics2D)g;
	    GradientPaint gp = new GradientPaint(0, 0, Color.blue, 0, 20, Color.cyan, true);
	    g2d.setPaint(gp);
	    g2d.setColor(Color.black);
	    g2d.drawString(this.name, this.getWidth() / 2 - (this.getWidth() / 2 /4), (this.getHeight() / 2) + 5);
	  }

	  public void mouseClicked(MouseEvent event) {
	  }

	  public void mouseEntered(MouseEvent event) {
	    
	  }

	  public void mouseExited(MouseEvent event) {
	    
	  }

	  public void mousePressed(MouseEvent event) {
	    
	  }
	 
	  public void mouseReleased(MouseEvent event) {
	                  
	  }  
}
