import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class Menu implements ActionListener {

    JFrame frame;
    JLabel title;
    JButton playButton;
    JButton onePlayerButton;
    JButton twoPlayerButton;
    JButton exitButton;
    JLabel credits;
    Font menuPlainFont = new Font("Microsoft JhengHei",Font.PLAIN,46);
    JButton[] allButtons = new JButton[4];
    JLabel[] allLabels;
    


    Menu(){
        frame = new JFrame("Tic-Tac-Toe");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500,500);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        // frame.setBackground(new Color(194,197,197));
        frame.setResizable(false);
        // frame.setAlwaysOnTop(true);

        title = new JLabel("TIC-TAC-TOE");
        credits = new JLabel("by rickroller");

        playButton = new JButton("PLAY");
        onePlayerButton = new JButton("1 PLAYER");
        twoPlayerButton = new JButton("2 PLAYERS");
        exitButton = new JButton("EXIT");

        playButton.addActionListener(this);
        exitButton.addActionListener(this);
        onePlayerButton.addActionListener(this);
        twoPlayerButton.addActionListener(this);

        allButtons[0] = playButton;
        allButtons[1] = exitButton;
        allButtons[2] = onePlayerButton;
        allButtons[3] = twoPlayerButton;

        for (int i = 0; i < allButtons.length; i++) {
            allButtons[i].setFont(menuPlainFont);
            allButtons[i].setFocusable(false);
            allButtons[i].setBackground(Color.WHITE);
        }

        


        title.setFont(menuPlainFont);
        credits.setFont(menuPlainFont);


        title.setHorizontalAlignment(JLabel.CENTER);
        title.setBounds(90,30,300,50);

        playButton.setBounds(80,120,320,130);
        playButton.setHorizontalAlignment(JButton.CENTER);
        playButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        exitButton.setBounds(140,270,200,100);
        exitButton.setHorizontalAlignment(JButton.CENTER);
        exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        credits.setFont(new Font("Arial", Font.PLAIN,13));
        credits.setHorizontalAlignment(JLabel.RIGHT);
        credits.setBounds(380,440,100,25);


        onePlayerButton.setBounds(85,80,320,130);
        onePlayerButton.setHorizontalAlignment(JButton.CENTER);
        onePlayerButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        twoPlayerButton.setBounds(85,240,320,130);
        twoPlayerButton.setHorizontalAlignment(JButton.CENTER);
        twoPlayerButton.setAlignmentX(Component.CENTER_ALIGNMENT);




        frame.add(title);
        frame.add(playButton);
        frame.add(exitButton);
        frame.add(credits);

        
        frame.setVisible(true);
    

        






        

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if (e.getSource() == playButton) {
            playButton.setVisible(false);
            exitButton.setVisible(false);
            title.setVisible(false);
            frame.add(onePlayerButton);
            frame.add(twoPlayerButton);
            // onePlayerButton.setVisible(true);
            // twoPlayerButton.setVisible(true);

            // frame.add(onePlayerButton);
            // frame.add(twoPlayerButton);
            // frame.setVisible(true);
        }

        if (e.getSource() == exitButton) {
            System.exit(0);
        }

        if (e.getSource() == onePlayerButton) {
            frame.dispose();
            new TicTacToe(true);
            // frame.setVisible(false);
            // game.setVisible(true);
        }

        if (e.getSource() == twoPlayerButton) {
            frame.dispose();
            new TicTacToe(false);
            // frame.setVisible(false);
            // game.setVisible(true);
        }
       
        
    }
    
}
