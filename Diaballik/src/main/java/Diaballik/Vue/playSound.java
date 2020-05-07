package Diaballik.Vue;

import javax.sound.sampled.*;

public class playSound {
	
	public void play(String soundName){
	  try {
		  AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(this.getClass().getResourceAsStream(soundName));
		  Clip clip = AudioSystem.getClip( );
		  clip.open(audioInputStream);
		  clip.start( );
	  }
	  catch(Exception ex) {
	    System.out.println("Error with playing sound.");
	    ex.printStackTrace( );
	  }
	}
}