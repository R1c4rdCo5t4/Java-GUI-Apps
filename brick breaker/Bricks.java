import java.awt.*;



public class Bricks {

    int map[][];
    int brickWidth;
    int brickHeight;
    GradientPaint gp = new GradientPaint(100f,0f,Color.RED,100f,300f,Color.BLUE);

    Bricks(int rows, int cols){

        map = new int[rows][cols];
        for (int i = 0; i < map.length; i++){
            for (int j = 0; j < map[0].length; j++){
                map[i][j] = 1;

            }
        }
        brickWidth = 540 / cols;
        brickHeight = 170 / rows; 
       }
    
    public void draw(Graphics2D g){
        
        for (int i = 0; i < map.length; i++){
            for (int j = 0; j < map[0].length; j++){
                if (map[i][j] > 0){
                    g.setPaint(gp);
                    g.fillRect(j * brickWidth + 50, i * brickHeight + 50, brickWidth,brickHeight);

                    g.setStroke(new BasicStroke(5));
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
