import java.awt.*;

public class Score extends Rectangle{

    static int SCREEN_WIDTH;
    static int SCREEN_HEIGHT;
    int player1;
    int player2;


    Score(int GAME_WIDTH, int GAME_HEIGHT){
        Score.SCREEN_WIDTH = GAME_WIDTH;
        Score.SCREEN_HEIGHT = GAME_HEIGHT;



    }

    public void draw(Graphics g){
        g.setColor(Color.WHITE);
		g.setFont(new Font("Consolas",Font.PLAIN,55));
		
		g.drawLine(SCREEN_WIDTH/2, 0, SCREEN_WIDTH/2, SCREEN_HEIGHT);
        // g.drawLine(GAME_WIDTH/2, 0, GAME_WIDTH/2, GAME_HEIGHT);
		
		g.drawString(String.valueOf(player1/10)+String.valueOf(player1%10), (SCREEN_WIDTH/2)-85, 50);
		g.drawString(String.valueOf(player2/10)+String.valueOf(player2%10), (SCREEN_WIDTH/2)+20, 50);

    }


    
}
