import java.awt.*;
import java.awt.event.*;
 

public class Player extends Rectangle {
    int xVelocity;
    int speed = 10;
    

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

    public void draw(Graphics g){
 
        g.setColor(Color.CYAN);
        g.fillRect(x,y,width,height);

    }
}
