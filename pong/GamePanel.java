import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;

public class GamePanel extends JPanel implements Runnable {

    static final int SCREEN_WIDTH = 1000;
    static final int SCREEN_HEIGHT = (int)(SCREEN_WIDTH * (0.555555));
    static final Dimension SCREEN_SIZE = new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT);
    static final int BALL_SIZE = 18; // diameter
    static final int PADDLE_WIDTH = 25;
    static final int PADDLE_HEIGHT = 100;
    Thread gameThread; //= new Thread(new Runnable() {})
    Image image; //= new Image
    Graphics graphics; //= new Graphics
    Random random; //= new Random();
    Paddle paddle1,paddle2;
    Ball ball; 
    Score score; 
    boolean running = false;
    Sound soundEffects = new Sound();

    GamePanel(){
        newPaddles();
        newBall();
        score = new Score(SCREEN_WIDTH,SCREEN_HEIGHT);




        this.setPreferredSize(SCREEN_SIZE);
        // this.setBackground(Color.BLACK);
        this.setFocusable(true);
        this.addKeyListener(new ActionListener());

        gameThread = new Thread(this);
        gameThread.start();

        
    }

    public void newBall(){
        soundEffects.paddleHitSound();
        random = new Random();
        ball = new Ball((SCREEN_WIDTH/2)-(BALL_SIZE/2),random.nextInt(SCREEN_HEIGHT-BALL_SIZE),BALL_SIZE,BALL_SIZE);

    }

    public void newPaddles(){
        paddle1 = new Paddle(0,(SCREEN_HEIGHT/2)-(PADDLE_HEIGHT/2),PADDLE_WIDTH,PADDLE_HEIGHT,1);
        paddle2 = new Paddle(SCREEN_WIDTH-PADDLE_WIDTH,(SCREEN_HEIGHT/2)-(PADDLE_HEIGHT/2),PADDLE_WIDTH,PADDLE_HEIGHT,2);


    }

    public void paint(Graphics g){
        image = createImage(getWidth(),getHeight());
        graphics = image.getGraphics();
        draw(graphics);
        g.drawImage(image, 0, 0, this);

    }

    public void draw(Graphics g){
        paddle1.draw(g);
        paddle2.draw(g);
        ball.draw(g);
        score.draw(g);
        Toolkit.getDefaultToolkit().sync(); 

    }

    public void move(){
        paddle1.move();
        paddle2.move();
        ball.move();

    }

    public void checkCollision(){

        // bouncing ball of paddels
        
        if (ball.intersects(paddle1)){
            ball.xVelocity = -ball.xVelocity;
            ball.xVelocity+=1.5;

            if (ball.yVelocity>0){
                ball.yVelocity++;
            }
            else{
                ball.yVelocity--;
            }

            ball.setXDirection(ball.xVelocity);
            ball.setYDirection(ball.yVelocity);
            soundEffects.paddleHitSound();
        }
        if (ball.intersects(paddle2)){
            ball.xVelocity = -ball.xVelocity;
            ball.xVelocity-=1.5;

            if (ball.yVelocity>0){
                ball.yVelocity++;
            }
            else{
                ball.yVelocity--;
            }

            ball.setXDirection(ball.xVelocity);
            ball.setYDirection(ball.yVelocity);
            soundEffects.paddleHitSound();
        }
        
        


        // ball top and bottom borders
        if (ball.y <= 0){
            ball.setYDirection(-ball.yVelocity);
            soundEffects.wallHitSound();
        }
        if (ball.y >= (SCREEN_HEIGHT-BALL_SIZE)){
            ball.setYDirection(-ball.yVelocity);
            soundEffects.wallHitSound();
        }




        // paddle window borders
        if (paddle1.y <= 0){
            paddle1.y = 0;
        }
        if (paddle1.y >= (SCREEN_HEIGHT-PADDLE_HEIGHT)){
            paddle1.y = SCREEN_HEIGHT-PADDLE_HEIGHT;
        }

        if (paddle2.y <= 0){
            paddle2.y = 0;
        }
        if (paddle2.y >= (SCREEN_HEIGHT-PADDLE_HEIGHT)){
            paddle2.y = SCREEN_HEIGHT-PADDLE_HEIGHT;
        }

        // scoring system & restart game
        if (ball.x <= 0){
            score.player2++;
            newPaddles();
            newBall();
            soundEffects.gameOverSound();
            System.out.println(score.player1+":"+score.player2);
        }

        if (ball.x >= (SCREEN_WIDTH-BALL_SIZE)){
            score.player1++;
            
            newPaddles();
            newBall();
            soundEffects.gameOverSound();
            System.out.println(score.player1+":"+score.player2);
        }



    }

    public void run(){
        // game loop
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double nanos = 1000000000 / amountOfTicks;
        double delta = 0;

        running = true;
        while (running){
            long now = System.nanoTime();
            delta += (now - lastTime)/nanos;
            lastTime = now;

            if (delta >= 1){
                move();
                checkCollision();
                repaint();
                delta--;

            }

        }


    }

    public class ActionListener extends KeyAdapter{

        public void keyPressed(KeyEvent e){
            paddle1.keyPressed(e);
            paddle2.keyPressed(e);

        }

        public void keyReleased(KeyEvent e){
            paddle1.keyReleased(e);
            paddle2.keyReleased(e);
            
        }

    }
    
}
