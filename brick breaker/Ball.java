import java.awt.*;
import java.util.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import java.io.*;
import javax.sound.sampled.*;
import java.util.Scanner;

public class Ball extends Rectangle{
    Random random;
    int xVelocity, yVelocity;
    int initialSpeed = 0;
    int initialDirection = 1;
    Sound soundEffects = new Sound();
    GradientPaint gp;
    

    Ball(int x, int y, int width, int height){
        super(x,y,width,height);
        random = new Random();

        
        // int randomXdir = random.nextInt(2);
        
        // setXDirection(randomXdir*initialSpeed);

        
        setYDirection(initialSpeed);
        


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

    public void draw(Graphics2D g){
        
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
        RenderingHints.VALUE_ANTIALIAS_ON);
        gp = new GradientPaint(x, y + height/2,Color.white,x + width, y + height/2,Color.GRAY);     // boinha rosinha new Color(245, 90, 250)
        g.setPaint(gp);
    
        g.fillOval(x,y,width,height);
        
        
    }

    



    public void keyPressedBall(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_SPACE){
            if (initialSpeed==0){
                initialSpeed = 3;
                setYDirection(initialSpeed);
                move();
                soundEffects.startSound();
                

            }
            // else{
            //     initialSpeed = -20;
            //     setYDirection(initialSpeed);
            //     setXDirection(initialSpeed-15);
            //     move();
                
            // }
            
            
            
        }
        
       
        

    }

    
    
}
