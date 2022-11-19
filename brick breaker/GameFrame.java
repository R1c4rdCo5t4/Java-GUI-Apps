import javax.swing.JFrame;
import java.awt.*;


public class GameFrame extends JFrame{

    GamePanel panel;

    GameFrame(){
        panel = new GamePanel();
        this.add(panel);
        this.setTitle("Brick Breaker Game 1.3- by rickrollerⒸ");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setBackground(Color.BLACK);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }
    
}