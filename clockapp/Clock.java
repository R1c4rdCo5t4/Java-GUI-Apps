
import javax.swing.*;
import java.awt.*;
import java.util.Calendar;
import java.text.SimpleDateFormat;



public class Clock extends JFrame{

    Calendar calendar;
    SimpleDateFormat timeFormat;
    JLabel timeLabel;
    String time;
    SimpleDateFormat dateFormat;
    JLabel dateLabel;
    String date;

    Clock(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Shitty Clock 1.01 - by rickrollerⒸ");
        this.setLayout(new FlowLayout());
        this.setSize(new Dimension(500,220));
        this.getContentPane().setBackground(Color.black);
        this.setResizable(false);
        this.setLocationRelativeTo(null);


        timeFormat = new SimpleDateFormat("kk:mm:ss");
        dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        timeLabel = new JLabel();
        timeLabel.setFont(new Font("Monospaced", Font.PLAIN,80));
        timeLabel.setForeground(Color.green);
        timeLabel.setBackground(Color.black);
        timeLabel.setOpaque(true);

        
        dateLabel = new JLabel();
        dateLabel.setFont(new Font("Monospaced", Font.ITALIC,35));
        dateLabel.setForeground(Color.green);



        this.add(timeLabel);
        this.add(dateLabel);
        this.setVisible(true);

        setClock();
        

        
    }
    public void setClock(){
        while (true){
            time = timeFormat.format(Calendar.getInstance().getTime());
            timeLabel.setText(time);
            
            date = dateFormat.format(Calendar.getInstance().getTime());
            dateLabel.setText(date);
            
            try {
                Thread.sleep(1000);
    
            } 
            catch (InterruptedException e) {
                e.printStackTrace();
            }
            finally{
                

            }
        }
    }
    
}
