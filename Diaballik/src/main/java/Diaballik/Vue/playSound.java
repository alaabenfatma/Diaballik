package Diaballik.Vue;

import javax.sound.sampled.*;

public class playSound {
	
	public void play(String soundName, boolean mute){
	  try {
		  
		  AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(this.getClass().getResourceAsStream(soundName));
		  Clip clip = AudioSystem.getClip( );
		  if (mute == false) {
			  clip.open(audioInputStream);
			  clip.start( );
		  } else {
			  clip.stop();
		  }
		  
	  }
	  catch(Exception ex) {
	    System.out.println("Error with playing sound.");
	    ex.printStackTrace( );
	  }
	}
}