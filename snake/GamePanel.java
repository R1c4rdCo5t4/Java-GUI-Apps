
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import java.io.*;
import javax.sound.sampled.*;
import java.util.Scanner;
 
public class GamePanel extends JPanel implements ActionListener{


    static final int SCREEN_WIDTH = 600;
    static final int SCREEN_HEIGHT = 600;
    static final int UNIT_SIZE = 25;
    static final int GAME_UNITS = (SCREEN_WIDTH * SCREEN_HEIGHT) / UNIT_SIZE;
    static int GAME_DELAY = 70;
    final int x[] = new int[GAME_UNITS];
    final int y[] = new int[GAME_UNITS];

    int bodyParts = 6;
    int applesEaten,appleX,appleY;
    Random random = new Random();
    String chars = "UDRL";
    char direction = chars.charAt(random.nextInt(chars.length()));
    Timer timer;
    // char direction = 'D';
    boolean running = false;
    Color appleColor = Color.RED;
    // Color goldenAppleColor = new Color(252, 186, 3);
    Color newColor;
    Clip clip2;
    Clip clip3;


    GamePanel(){
        this.setPreferredSize(new Dimension(SCREEN_WIDTH,SCREEN_HEIGHT));
        this.setBackground(Color.BLACK);
        this.setFocusable(true);
        this.addKeyListener(new myKeyAdapter());
        
    }
   
    
    public void menu(Graphics g) throws UnsupportedAudioFileException, IOException, LineUnavailableException{

            
        
            newColor = new Color(random.nextInt(255),random.nextInt(255),random.nextInt(255));
            //score 
            g.setColor(newColor);
            g.setFont( new Font("Ink Free",Font.BOLD, 40));
            FontMetrics scoreMetrics = getFontMetrics(g.getFont());
            g.drawString("Score: "+applesEaten, (SCREEN_WIDTH - scoreMetrics.stringWidth("Score: "+applesEaten))/2, g.getFont().getSize()+80);
            //highscore 
            g.setColor(newColor);
            g.setFont( new Font("Ink Free",Font.BOLD, 27));
            FontMetrics highScoreMetrics = getFontMetrics(g.getFont());
            g.drawString("Highest Score: "+getHighScore(), (SCREEN_WIDTH - highScoreMetrics.stringWidth("Highest Score: "+getHighScore()))/2, g.getFont().getSize()+130);
            
            //title 
            g.setColor(newColor);
            g.setFont(new Font("Arial", Font.BOLD,75));
            FontMetrics gameMetrics = getFontMetrics(g.getFont());
            g.drawString("Drunk Snake", (SCREEN_WIDTH-gameMetrics.stringWidth("Drunk Snake"))/2, (SCREEN_HEIGHT/2));
            // restart
            g.setColor(newColor);
            g.setFont(new Font("Arial", Font.PLAIN,25));
            FontMetrics restartMetrics = getFontMetrics(g.getFont());
            g.drawString("Press Space to Start", (SCREEN_WIDTH-restartMetrics.stringWidth("Press Space to Start"))/2, (SCREEN_HEIGHT/2+60));
            // credits
            g.setColor(new Color(46, 46, 46));
            g.setFont(new Font("Arial", Font.PLAIN,16));
            FontMetrics creditsMetrics = getFontMetrics(g.getFont());
            g.drawString("by rickroller", (SCREEN_WIDTH-creditsMetrics.stringWidth("by rickroller"))/2+250, (SCREEN_HEIGHT/2+285));
           
            
    }


    public void startMusic() throws UnsupportedAudioFileException, IOException, LineUnavailableException{
        // music
        AudioInputStream audioStream2 = AudioSystem.getAudioInputStream(new File("sounds/music.wav"));
        clip2 = AudioSystem.getClip();
        clip2.open(audioStream2);
        clip2.start();
        

    }

    public void gameOver() throws UnsupportedAudioFileException, IOException, LineUnavailableException{
        AudioInputStream audioStream2 = AudioSystem.getAudioInputStream(new File("sounds/gameover.wav"));
        clip3 = AudioSystem.getClip();
        clip3.open(audioStream2);
        clip3.start();
        
        
    }

    public void startGame() throws UnsupportedAudioFileException, IOException, LineUnavailableException{
        newApple();
        startMusic();

        running = true;
        timer = new Timer(GAME_DELAY,this);
        applesEaten = 0;
        bodyParts = 6;
        timer.start();
    }
    
    

    public void highScoreUpdater() throws FileNotFoundException{
        Scanner sc = new Scanner(new File("highscore.txt"));
        String line = sc.nextLine();
        int highScore = Integer.parseInt(line);

        if (applesEaten > highScore){
            try {
                FileWriter writer = new FileWriter("highscore.txt");
                writer.write(String.valueOf(applesEaten));
                writer.close();
            } 
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public String getHighScore() throws FileNotFoundException{
        Scanner sc = new Scanner(new File("highscore.txt"));
        String score = sc.nextLine();
        return score;


    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        try {
            draw(g);
        } catch (Exception e) {
            e.printStackTrace();
        } 
        


    }

    public void draw(Graphics g) throws UnsupportedAudioFileException, IOException, LineUnavailableException{
        newColor = new Color(random.nextInt(255),random.nextInt(255),random.nextInt(255));
        if (running){
            // for(int i = 0; i < SCREEN_HEIGHT / UNIT_SIZE; i++){
            //     g.drawLine(i*UNIT_SIZE, 0, i*UNIT_SIZE, SCREEN_HEIGHT);
            //     g.drawLine(0,i*UNIT_SIZE, SCREEN_WIDTH, i*UNIT_SIZE);
    
            // }
    
            g.setColor(newColor);
            g.fillRect(appleX,appleY,UNIT_SIZE,UNIT_SIZE);
    
            for (int i = 0; i < bodyParts; i++) {
                if (i==0){
                    g.setColor(newColor.brighter().brighter());
                    g.fillRect(x[i],y[i],UNIT_SIZE,UNIT_SIZE);
                }
                    g.setColor(newColor);
                    g.fillRect(x[i],y[i],UNIT_SIZE,UNIT_SIZE);
                    

                }
                
            
                
                
            
            g.setColor(newColor);
			g.setFont( new Font("Calibri",Font.BOLD, 34));
			FontMetrics metrics = getFontMetrics(g.getFont());
			g.drawString("Score: "+applesEaten, (SCREEN_WIDTH - metrics.stringWidth("Score: "+applesEaten))/2, g.getFont().getSize());
        }
        else{
            menu(g);
        }

        

    }

    public void move(){

        for (int i = bodyParts; i > 0; i--){
            x[i] = x[i-1];
            y[i] = y[i-1];

        }

        switch(direction) {
            case 'U':
                y[0] = y[0] - UNIT_SIZE;
                break;
            case 'D':
                y[0] = y[0] + UNIT_SIZE;
                break;
            case 'R':
                x[0] = x[0] + UNIT_SIZE;
                break;
            case 'L':
                x[0] = x[0] - UNIT_SIZE;
                break;
        }

    }

    public void newApple(){
        appleX = random.nextInt((int)(SCREEN_WIDTH / UNIT_SIZE))*UNIT_SIZE;
        appleY = random.nextInt((int)(SCREEN_HEIGHT / UNIT_SIZE))*UNIT_SIZE;
    }

    public void checkApple() throws UnsupportedAudioFileException, IOException, LineUnavailableException{
        if ((x[0]==appleX) && (y[0]==appleY)){
            bodyParts++;
            applesEaten++;
            newApple();
            // sfx 
            AudioInputStream audioStream1 = AudioSystem.getAudioInputStream(new File("sounds/pickupsound.wav"));
            Clip clip1 = AudioSystem.getClip();
            clip1.open(audioStream1);
            clip1.start();
            
        }

    
    }
    

    public void checkCollision() throws UnsupportedAudioFileException, IOException, LineUnavailableException{
        // checks if snake's head collides with itself
        for (int i = bodyParts; i > 0; i--){
            if((x[0]==x[i]) && (y[0]==y[i])){
                running = false;
                
            }
            // left border
            if(x[0] < 0){
                x[0] = SCREEN_WIDTH;
                
            }
            // right border
            if(x[0] > SCREEN_WIDTH){
                x[0] = 0;
                
            }
            // bottom border
            if(y[0] > SCREEN_HEIGHT){
                y[0] = 0;
                
            }
            // top border
            if(y[0] < 0){
                y[0] = SCREEN_HEIGHT;
                
            }

            if(!running){
                timer.stop();
                gameOver();
                clip2.stop();
                highScoreUpdater();

                
            }

        }

    }



    @Override
    public void actionPerformed(ActionEvent e) {

        if(running){
            move();
            
                try {
                    checkApple();
                } 
                catch (Exception e2) {
                   
                    e2.printStackTrace();
                }
                
            
            try {
                checkCollision();
            } catch (UnsupportedAudioFileException e1) {
                
                e1.printStackTrace();
            } catch (IOException e1) {
                
                e1.printStackTrace();
            } catch (LineUnavailableException e1) {
                
                e1.printStackTrace();
            }
        }
        repaint();
        
        
        
    }


    public class myKeyAdapter extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent e){
            switch(e.getKeyCode()){
                case KeyEvent.VK_LEFT:
                    if(direction != 'R'){
                        direction = 'L';
                    }
                    break;
                case KeyEvent.VK_RIGHT:
                    if(direction != 'L'){
                        direction = 'R';
                    }
                    break;
                case KeyEvent.VK_UP:
                    if(direction != 'D'){
                        direction = 'U';
                    }
                    break;
                case KeyEvent.VK_DOWN:
                    if(direction != 'U'){
                        direction = 'D';
                    }
                    break;
                case KeyEvent.VK_SPACE:
                    if (!running){
                        try {
                            startGame();
                        } catch (UnsupportedAudioFileException e1) {
                            
                            e1.printStackTrace();
                        } catch (IOException e1) {
                            
                            e1.printStackTrace();
                        } catch (LineUnavailableException e1) {
                            
                            e1.printStackTrace();
                        }
                        break;

                    }           
            }
        }

    }
  
}
