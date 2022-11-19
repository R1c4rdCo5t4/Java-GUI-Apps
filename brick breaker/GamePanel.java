import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;

public class GamePanel extends JPanel implements Runnable {

    static final int SCREEN_WIDTH = 650; // 650
    static final int SCREEN_HEIGHT = 700; // 700
    static final Dimension SCREEN_SIZE = new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT);
    static final int BALL_SIZE = 20; // diameter
    static final int PLAYER_WIDTH = 100;
    static final int PLAYER_HEIGHT = 20;
    Thread gameThread; 
    Image image; 
    Graphics graphics;
    Random random; 
    Player player;
    Ball ball; 
    Bricks bricks;
    public static int score,previousScore,levelScore; 
    public static boolean running = false;
    Sound soundEffects = new Sound();
    JLabel pressToStart;
    int bricksRows = 1;  // 5,6 8,9
    int bricksCols = 1;
    

    GamePanel(){
        newPlayer();
        newBricks();
        newBall();
        // score = new Score(SCREEN_WIDTH,SCREEN_HEIGHT);

        // Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        // obj.setLocation(dim.width/2-obj.getSize().width/2, dim.height/2-obj.getSize().height/2);


        

        

        this.setPreferredSize(SCREEN_SIZE);
        
        this.setFocusable(true);
        this.addKeyListener(new ActionListener());

        gameThread = new Thread(this);
        gameThread.start();
        
        
    }

    
    public void newBall(){
        // soundEffects.paddleHitSound();
        random = new Random();
        ball = new Ball((SCREEN_WIDTH/2)-(BALL_SIZE/2),SCREEN_HEIGHT/2,BALL_SIZE,BALL_SIZE);

    }

    public void newPlayer(){
        player = new Player((SCREEN_WIDTH/2)-(BALL_SIZE/2)-40,SCREEN_HEIGHT-PLAYER_HEIGHT-40,PLAYER_WIDTH,PLAYER_HEIGHT);
        
    }

    public void newBricks(){
        bricks = new Bricks(bricksRows,bricksCols);
    }

    public void paint(Graphics g){
        image = createImage(getWidth(),getHeight());
        graphics = image.getGraphics();
        draw(graphics);
       
        g.drawImage(image, 0, 0, this);

    }

    public void draw(Graphics g){
        player.draw((Graphics2D)g);

        checkWin(g);
        
        
        
        
        bricks.draw((Graphics2D)g);
        
        if (ball.y >= SCREEN_HEIGHT-ball.width){
            previousScore = score;
            score = 0;
        }
        

        drawScore((Graphics2D)g);
        
        drawPreviousScore((Graphics2D)g,previousScore);

        

        if (ball.yVelocity == 0){
            drawString((Graphics2D)g,"Press Space to Start",true);

        }
        else{
            drawString((Graphics2D)g,"Press Space to Start",false);
        }

        ball.draw((Graphics2D)g);

        Toolkit.getDefaultToolkit().sync(); 

    }

    public void drawString(Graphics2D g,String str,boolean draw){
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
        RenderingHints.VALUE_ANTIALIAS_ON);
        
        
        if (draw){
            g.setColor(Color.WHITE);
        }
        
        else{   
            g.setColor(Color.BLACK);
           
        }
        g.setFont(new Font("Arial",Font.PLAIN,24));
           
        FontMetrics fontMetrics = g.getFontMetrics(g.getFont());
        g.drawString(str, (GamePanel.SCREEN_WIDTH - fontMetrics.stringWidth(str))/2, g.getFont().getSize()+450);
    }

    public void drawScore(Graphics2D g){
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
        RenderingHints.VALUE_ANTIALIAS_ON);
        
        g.setColor(Color.WHITE); //new Color(106, 43, 214)
        g.setFont(new Font("Arial", Font.PLAIN,22));
        g.drawString("Score: "+ score,SCREEN_WIDTH - 110, 35);
        


    }

    public void drawPreviousScore(Graphics2D g, int previousScore){
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
        RenderingHints.VALUE_ANTIALIAS_ON);

        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.PLAIN,22));
        g.drawString("Last Score: "+ previousScore,15, 35);

    }

    public void checkWin(Graphics g){
        if ((levelScore/5) == bricksRows*bricksCols){
            // running = false;
            // FontMetrics fontMetrics = g.getFontMetrics(g.getFont());
            // g.setColor(Color.WHITE);
            // g.setFont(new Font("Arial", Font.PLAIN,50));


            // g.drawString("You Win!", (SCREEN_WIDTH/2 - fontMetrics.stringWidth("You Win!")-45), SCREEN_HEIGHT/2-fontMetrics.stringWidth("You Win!")/2);
            soundEffects.winSound();
            newPlayer();
            newBall();
            bricksRows ++;
            bricksCols += 2;
            levelScore = 0;
            newBricks();
            

        }
        
    }

    

    

    public void move(){
        player.move();
        ball.move();

    }

    public void checkCollision(){

        // bouncing ball of paddels
        
        if (ball.intersects(player)){ // (player.x == ball.x) && ( player.y == ball.y)
            soundEffects.playerHitSound();
            ball.yVelocity = -ball.yVelocity;
            Random random = new Random();
            if (-ball.yVelocity <= 3){                
                ball.yVelocity-=8;
                int xdir = random.nextInt(2);
                if (xdir == 1){
                    ball.xVelocity--;
                }
                else{
                    ball.xVelocity++;

                }
                
                

            }
            
            

            
            

            ball.setXDirection(ball.xVelocity);
            ball.setYDirection(ball.yVelocity);
            // soundEffects.paddleHitSound();
        }
        
        // check brick collision with ball
        A: for (int i = 0; i < bricks.map.length; i++){
            for (int j = 0; j < bricks.map[0].length; j++){
                if (bricks.map[i][j] > 0){
                    int brickX = j * bricks.brickWidth + 50;
                    int brickY = i * bricks.brickHeight + 50;
                    int brickWidth = bricks.brickWidth;
                    int brickHeight = bricks.brickHeight;

                    Rectangle brickRect = new Rectangle(brickX, brickY, brickWidth, brickHeight);
                    // Rectangle ballRect = new Rectangle(ball.x, ball.y, ball.width, ball.height);
                    
                    if (ball.intersects(brickRect)){
                        soundEffects.wallHitSound();
                        bricks.removeBrick(0,i,j);
                        levelScore+=5;
                        score += 5;
                        
                        

                
                        if (ball.x +19 <= brickRect.x || ball.x+1 >= brickRect.x+brickRect.width){
                            ball.xVelocity +=2;
                            ball.setXDirection(-ball.xVelocity);
                            // soundEffects.wallHitSound();
                        }
                        else{
                            ball.setYDirection(-ball.yVelocity);
                        }
                        break A;

                    }
                    


            }


        }
        
    }
        


        // ball top, left right and borders
        if (ball.y <= 0){
            ball.setYDirection(-ball.yVelocity);
            soundEffects.wallHitSound();
    
        }
        if (ball.x >= SCREEN_WIDTH-BALL_SIZE){
            ball.setXDirection(-ball.xVelocity);
            soundEffects.wallHitSound();
            ball.xVelocity -=2;
            
            
        }
        else if(ball.x <= 0) {
            ball.xVelocity +=2;

        }




        // paddle window borders
        if (player.x <= 0){
            player.x = 0;
            

        }
        if (player.x >= (SCREEN_WIDTH-PLAYER_WIDTH)){
            player.x = SCREEN_WIDTH-PLAYER_WIDTH;
            
        }

        // if (paddle2.y <= 0){
        //     paddle2.y = 0;
        // }
        // if (paddle2.y >= (SCREEN_HEIGHT-PADDLE_HEIGHT)){
        //     paddle2.y = SCREEN_HEIGHT-PADDLE_HEIGHT;
        // }

        // scoring system & restart game
        if (ball.y >= SCREEN_HEIGHT){
            levelScore = 0;
            bricksRows = 1;
            bricksCols = 1;
            soundEffects.gameOverSound();

            
            newPlayer();
            newBall();
            newBricks();
            // soundEffects.gameOverSound();
            // System.out.println(score);
        }

        


    }

    public void run(){
        // game loop
        long lastTime = System.nanoTime();
        double amountOfTicks = 80.0;
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
        player.keyPressed(e);
        ball.keyPressedBall(e);
       

    }

    public void keyReleased(KeyEvent e){
        player.keyReleased(e);
        
        
    }

}
}