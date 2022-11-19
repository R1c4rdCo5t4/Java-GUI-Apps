import java.awt.*;
import java.awt.event.*;
 

public class Player extends Rectangle {
    int xVelocity;
    int speed = 10;
    GradientPaint gp; 
    

    Player(int x,int y,int width,int height){
        super(x,y,width,height);
        


    }

    public void keyPressed(KeyEvent e) {
        
        if (e.getKeyCode() == KeyEvent.VK_RIGHT){
            setXDirection(speed);
            move();
            
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT){
            setXDirection(-speed);
            move();
            
        }

        if (e.getKeyCode() == KeyEvent.VK_SPACE){
            move();
            
        }
        
       
        

    }

    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT){
            setXDirection(0);
            move();
            
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT){
            setXDirection(0);
            move();
            
        }


    }
    
    public void setXDirection(int xDir){
        xVelocity = xDir;

    }

    public void move(){
        x += xVelocity;

    }

    public void draw(Graphics2D g){
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
        RenderingHints.VALUE_ANTIALIAS_ON);

        gp = new GradientPaint(x, y + height,Color.white,x + width, y + height,Color.GRAY);     
        g.setPaint(gp);
        g.fillRect(x,y,width,height);

    }
}
