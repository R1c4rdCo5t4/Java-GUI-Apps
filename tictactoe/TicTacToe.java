import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TicTacToe extends JFrame implements ActionListener {
    JFrame frame;
    JPanel panel;
    JButton[] buttons = new JButton[9];
    JLabel textLabel;
    JButton restartButton;
    JButton menuButton;


    boolean playerOneTurn;
    private boolean isSinglePlayer;
    boolean gameOver;
    
    
    TicTacToe(boolean isSinglePlayer){
        
        this.isSinglePlayer = isSinglePlayer;
        playerOneTurn = true;
        gameOver = false;


        restartButton = new JButton("Restart");
        restartButton.addActionListener(this);
        restartButton.setFont(new Font("Arial", Font.BOLD,26));
        restartButton.setBackground(new Color(200,200,200));
        restartButton.setBounds(450,200,150,80);
        restartButton.setFocusable(false);

        menuButton = new JButton("Menu");
        menuButton.addActionListener(this);
        menuButton.setFont(new Font("Arial", Font.BOLD,26));
        menuButton.setBackground(new Color(200,200,200));
        menuButton.setBounds(450,300,150,80);
        menuButton.setFocusable(false);

        textLabel = new JLabel("X's Turn");
        textLabel.setBounds(460,15,300,80);
        textLabel.setFont(new Font("Arial", Font.BOLD,34));
        //435,460
        frame = new JFrame("Tic-Tac-Toe");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(640,460);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        // frame.setBackground(new Color(194,197,197));
        frame.setResizable(false);

        panel = new JPanel();
        panel.setBounds(1,2,420,420);
        panel.setLayout(new GridLayout(3,3,5,5));
        panel.setAlignmentX(JPanel.CENTER_ALIGNMENT);
        panel.setAlignmentY(JPanel.CENTER_ALIGNMENT);
        

        for (int i = 0; i < 9; i++){
            buttons[i] = new JButton();
            buttons[i].addActionListener(this);
            buttons[i].setFont(new Font("Arial", Font.PLAIN,60));
            buttons[i].setBackground(new Color(230,230,230));
            buttons[i].setFocusable(false);
            panel.add(buttons[i]);
        }
        
        // panel.setBackground(Color.BLACK);

        frame.add(panel);
        frame.add(textLabel);
        frame.add(restartButton);
        frame.add(menuButton);
 
        frame.setVisible(true);
    }

    public void checkWinner(){

        // check rows
        if(checkEquals(0,1,2)){
            gameOver(0,1,2);
            
        }

        if(checkEquals(3,4,5)){
            gameOver(3,4,5);
        }
        if(checkEquals(6,7,8)){
            gameOver(6,7,8);
        }

        // check collumns
        if(checkEquals(0,3,6)){
            gameOver(0,3,6);
        }
        if(checkEquals(1,4,7)){
            gameOver(1,4,7);
        }
        if(checkEquals(2,5,8)){
            gameOver(2,5,8);
        }

        //check diagonals
        if(checkEquals(0,4,8)){
            gameOver(0,4,8);
        }
        if(checkEquals(2,4,6)){
            gameOver(2,4,6);
        }

    }
    public void colorGreen(int i1, int i2, int i3){
        buttons[i1].setBackground(Color.GREEN);
        buttons[i2].setBackground(Color.GREEN);
        buttons[i3].setBackground(Color.GREEN);       
    }

    public boolean checkEquals(int a, int b, int c){
        if(
            (buttons[a].getText() == buttons[b].getText()) &&  
            (buttons[b].getText() == buttons[c].getText()) && 
            (buttons[a].getText() != "")){
            return true;       
        }
        else{
            return false;
        }
    }

    public void checkDraw(){
        if (!gameOver){
            int count = 0;
            for (int i = 0; i < 9; i++){
                if (buttons[i].getText() == ""){
                    count++;
                }
            }
            if (count == 0){
                textLabel.setText("Draw!");
            } 
        }
        
    }

    public void gameOver(int i1, int i2, int i3){
        gameOver = true;
        colorGreen(i1,i2,i3);
        String player = buttons[i1].getText();
        textLabel.setText(player +" wins!");     
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource()==restartButton){
            frame.dispose();
            new TicTacToe(isSinglePlayer);       
        }

        if(e.getSource()==menuButton){
            frame.dispose();
            new Menu();
            
        }
  
        for (int i = 0; i < 9; i++) {
            if (e.getSource()==buttons[i]){
                if (buttons[i].getText()=="" && !gameOver){
                    if (playerOneTurn){
                        buttons[i].setForeground(Color.RED);
                        buttons[i].setText("X");
                        playerOneTurn = false;
                        textLabel.setText("O's turn");
                    }

                    else if (!isSinglePlayer){
                        buttons[i].setForeground(Color.BLUE);
                        buttons[i].setText("O");
                        playerOneTurn = true;
                        textLabel.setText("X's turn");
                    }
                }
            }
        }
        checkWinner();
        checkDraw();       
    }

}