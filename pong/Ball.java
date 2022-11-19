import java.awt.*;
import java.util.*;

public class Ball extends Rectangle{
    Random random;
    int xVelocity, yVelocity;
    int initialSpeed = 5;

    Ball(int x, int y, int width, int height){
        super(x,y,width,height);
        random = new Random();
        int randomXdir = random.nextInt(2);
        if (randomXdir == 0){
            randomXdir--;
        }
        setXDirection(randomXdir*initialSpeed);

        int randomYdir = random.nextInt(2);
        if (randomYdir == 0){
            randomYdir--;
        }
        setYDirection(randomYdir*initialSpeed);
        


    }

    public void setXDirection(int randomXdir){
        xVelocity = randomXdir;

    }

    public void setYDirection(int randomYdir){
        yVelocity = randomYdir;

    }

    public void move(){
        x += xVelocity;
        y += yVelocity;

    }

    public void draw(Graphics g){
        g.setColor(Color.WHITE);
        g.fillOval(x,y,width,height);

    }
    
}
