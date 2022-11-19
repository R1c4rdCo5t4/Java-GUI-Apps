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

    public void draw(Graphics g){
        g.setColor(Color.YELLOW);
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
            
            
            
        }
        
       
        

    }

    
    
}
