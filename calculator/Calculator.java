
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.Math;

// import java.io.IOException;
// import javax.imageio.ImageIO;

// by rickrollerⒸ

public class Calculator implements ActionListener {

    JFrame frame;
    JTextField textField;
    JButton [] numberButtons = new JButton[10];
    JButton [] operationButtons = new JButton[12];
    JButton addButton, subButton, mulButton, divButton;
    JButton decButton, equButton, delButton, clrButton, invButton,powButton,sqrtButton,signButton;
    JPanel panel;
    Color grayDefaultColor = new Color(238,238,238);

    // JLabel title;

    Font resultFont = new Font("Microsoft JhengHei",Font.BOLD,38);
    Font numsFont = new Font("Microsoft JhengHei",Font.BOLD,18);
    Font buttonsFont = new Font("Microsoft JhengHei",Font.PLAIN,23);

    double num1 = 0, num2 = 0, result = 0;
    char operator;

    Calculator(){

        frame = new JFrame("Boring Calculator 1.0");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(330,403);
        frame.setLayout(null);
        frame.setBackground(new Color(194,197,197));
        frame.setResizable(false);
        frame.setAlwaysOnTop(true);
        frame.setLocationRelativeTo(null);

        // title = new JLabel();
        // title.setText("Standard Calculator");
        // title.setBounds(10,20,300,30);
        // title.setFont(new Font("Calibri Light",Font.BOLD,21));
        // title.setHorizontalAlignment(JTextField.LEFT);


        textField = new JTextField();
        textField.setBounds(0,5,310,70);
        textField.setFont(resultFont);
        textField.setBackground(grayDefaultColor);
        textField.setForeground(Color.gray.brighter());
        textField.setColumns(30);
        textField.setEditable(false);
        // textField.setText("0");
        textField.setHorizontalAlignment(JTextField.RIGHT);
        textField.setForeground(Color.BLACK);
        textField.setBorder(javax.swing.BorderFactory.createEmptyBorder()); 
           
        addButton = new JButton("+");
        subButton = new JButton("-");
        mulButton = new JButton("×");
        divButton = new JButton("÷");
        decButton = new JButton(".");
        equButton = new JButton("=");
        clrButton = new JButton("CLEAR");
        delButton = new JButton("DEL");
        invButton = new JButton("1/x");
        powButton = new JButton("^");
        sqrtButton = new JButton("√");
        signButton = new JButton("±");


        // Image img;
        // try {
        //     img = ImageIO.read(getClass().getResource("del.png"));
        //     delButton.setIcon(new ImageIcon(img));
        // } 
        // catch (IOException e) {
        //     e.printStackTrace();
        //     delButton = new JButton("DEL");
        // }
        
        operationButtons[0] = addButton;
        operationButtons[1] = subButton;
        operationButtons[2] = mulButton;
        operationButtons[3] = divButton;
        operationButtons[4] = decButton;
        operationButtons[5] = equButton;
        operationButtons[6] = clrButton;
        operationButtons[7] = delButton;
        operationButtons[8] = invButton;
        operationButtons[9] = powButton;
        operationButtons[10] = sqrtButton;
        operationButtons[11] = signButton;


        for (int i = 0; i < operationButtons.length; i++) {
            operationButtons[i].addActionListener(this);
            operationButtons[i].setFont(buttonsFont);
            operationButtons[i].setFocusable(false);
            operationButtons[i].setBackground(new Color(220,220,220));
        }
        
        for (int i = 0; i < 10; i++){
            numberButtons[i] = new JButton(String.valueOf(i));
            numberButtons[i].addActionListener(this);
            numberButtons[i].setFont(numsFont);
            numberButtons[i].setBackground(Color.WHITE);
            numberButtons[i].setFocusable(false);
        }

        clrButton.setBounds(4,75,230,50);
        clrButton.setBackground(new Color(188,188,188));
        clrButton.setFont(numsFont);
        delButton.setBounds(236,75,75,50);
        delButton.setBackground(new Color(188,188,188));
        delButton.setFont(numsFont);
        equButton.setBackground(new Color(188,188,188));
        invButton.setFont(new Font("Microsoft JhengHei",Font.PLAIN,18));
        equButton.setFont(new Font("Microsoft JhengHei",Font.PLAIN,26));
        signButton.setFont(new Font("Microsoft JhengHei",Font.PLAIN,18));


        panel = new JPanel();
        panel.setBounds(4,129,308,235);
        panel.setLayout(new GridLayout(5,4,2,2));

        panel.add(invButton);
        panel.add(powButton);
        panel.add(sqrtButton);
        panel.add(divButton);
        panel.add(numberButtons[7]);
        panel.add(numberButtons[8]);
        panel.add(numberButtons[9]);
        panel.add(mulButton);
        panel.add(numberButtons[4]);
        panel.add(numberButtons[5]);
        panel.add(numberButtons[6]);
        panel.add(subButton);
        panel.add(numberButtons[1]);
        panel.add(numberButtons[2]);
        panel.add(numberButtons[3]);
        panel.add(addButton);
        panel.add(signButton);
        panel.add(numberButtons[0]);
        panel.add(decButton);
        panel.add(equButton);
        panel.setBackground(new Color(220,220,220));
                
        frame.add(textField);
        frame.add(clrButton);
        frame.add(delButton);
        frame.add(panel);
        frame.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        String field = textField.getText();

        for (int i = 0; i < 10; i++) {
            if (e.getSource() == numberButtons[i] && field.length() < 13){
                
                textField.setText(field.concat(String.valueOf(i)));

            }
        }

        if (e.getSource() == decButton){
            if (field.length() - field.replace(".", "").length() < 1){
                textField.setText(field.concat("."));
            }
            
        }

        if (e.getSource() == addButton){
            num1 = Double.parseDouble(field);
            operator = '+';
            textField.setText("");
           }

        if (e.getSource() == subButton){
            num1 = Double.parseDouble(field);
            operator = '-';
            textField.setText("");
        }
        
        if (e.getSource() == mulButton){
            num1 = Double.parseDouble(field);
            operator = '*';
            textField.setText("");
           }
        
        if (e.getSource() == divButton){
            num1 = Double.parseDouble(field);
            operator = '/';
            textField.setText("");
           }

        if (e.getSource() == powButton){
            num1 = Double.parseDouble(field);
            operator = '^';
            textField.setText("");

        }

        try{

        
        if (e.getSource() == equButton) {

            if (field.equals("69")){
                textField.setText(";)");
                
            }

            else{
            num2 = Double.parseDouble(field);

            switch(operator){
                case '+': 
                    result = num1 + num2;
                    break;
                case '-': 
                    result = num1 - num2;
                    break;
                case '*': 
                    result = num1 * num2;
                    break;
                case '/': 
                    result = num1 / num2;
                    break;
                case '^':
                    result = Math.pow(num1,num2);
                    break;
                default:
                    result = num2;
                    break;

            }
            

            String resultStr = String.valueOf(result);
            textField.setText(resultStr);
            num1 = result;
        }
    }   


        }

        catch(Exception i){
            textField.setText("ERROR");
        }

        if (e.getSource() == clrButton){
            textField.setText("");
            num1 = 0;
            
        }

        if (e.getSource() == delButton){
            textField.setText(field.substring(0, field.length() - 1));
            // textField.setText(field.replace(Character.toString(field.charAt(field.length() - 1)),""));
                 
        }

        if (e.getSource() == signButton){
            num1 = -Double.parseDouble(field);
            textField.setText(String.valueOf(num1));
            
        }

        if (e.getSource() == invButton){
            num1 = 1/Double.parseDouble(field);
            textField.setText(String.valueOf(num1));

        }

        if (e.getSource() == sqrtButton){
            num1 = Math.sqrt(Double.parseDouble(field));
            textField.setText(String.valueOf(num1));

        }
  
    }
    
}
