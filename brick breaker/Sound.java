import java.net.URL;
import javax.sound.sampled.*;


public class Sound {


    
    URL playerHitURL;
    URL wallHitURL;
    URL gameOverURL;
    URL winURL;
    URL startURL;
    Clip clip;

    Sound(){

        playerHitURL = getClass().getResource("playerhit.wav");
        wallHitURL = getClass().getResource("wallhit.wav");
        gameOverURL = getClass().getResource("gameover.wav");
        winURL = getClass().getResource("win.wav");
        startURL = getClass().getResource("start.wav");

    }

    public void playerHitSound(){
        setFile(playerHitURL);
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

    public void winSound(){
        setFile(winURL);
        play();

    }

    public void startSound(){
        setFile(startURL);
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
