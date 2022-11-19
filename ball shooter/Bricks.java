import java.awt.*;



public class Bricks {

    int map[][];
    int brickWidth;
    int brickHeight;

    Bricks(int rows, int cols){

        map = new int[rows][cols];
        for (int i = 0; i < map.length; i++){
            for (int j = 0; j < map[0].length; j++){
                map[i][j] = 1;

            }
        }
        brickWidth = 540 / cols;
        brickHeight = 150 / rows; 
       }
    
    public void draw(Graphics2D g){
        for (int i = 0; i < map.length; i++){
            for (int j = 0; j < map[0].length; j++){
                if (map[i][j] > 0){
                    g.setColor(Color.WHITE);
                    g.fillRect(j * brickWidth + 50, i * brickHeight + 50, brickWidth,brickHeight);

                    g.setStroke(new BasicStroke(6));
                    g.setColor(Color.BLACK);
                    g.drawRect(j * brickWidth + 50, i * brickHeight + 50, brickWidth,brickHeight);

                    
                }

            }
        }
    }

    public void removeBrick(int value, int row, int col){
        map[row][col] = value;
    }
    
}
