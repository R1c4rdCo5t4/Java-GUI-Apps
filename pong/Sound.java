import java.net.URL;
import javax.sound.sampled.*;


public class Sound {


    
    URL paddleHitURL;
    URL wallHitURL;
    URL gameOverURL;
    Clip clip;

    Sound(){

        paddleHitURL = getClass().getResource("paddlehit.wav");
        wallHitURL = getClass().getResource("wallhit.wav");
        gameOverURL = getClass().getResource("gameover.wav");

    }

    public void paddleHitSound(){
        setFile(paddleHitURL);
        play();

    }

    public void wallHitSound(){
        setFile(wallHitURL);
        play();

    }

    public void gameOverSound(){
        setFile(gameOverURL);
        play();

    }

    public void setFile(URL soundFileName){
        try{
            AudioInputStream sound = AudioSystem.getAudioInputStream(soundFileName);
            clip = AudioSystem.getClip();
            clip.open(sound);

        }
        catch(Exception e){

        }
    }

    public void play(){
        clip.setFramePosition(0);
        clip.start();
    }

    
}
