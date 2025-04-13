// package src.BANK.MANAGEMENT.SYSTEM;
import java.util.*;
import javax.swing.*;
import java.awt.*; // imports image class
import java.awt.event.*; // imports ActionListener class


public class Deposit extends JFrame implements ActionListener {
    JButton deposit,back; // button for deposit
    JTextField amountTextField; // text field for amount 
    String pinnumber; // to store pin number
    Deposit(String pinnumber) {
        this.pinnumber = pinnumber; // to initialize pin number
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("atm.jpg"));
        Image i2= i1.getImage().getScaledInstance(900, 900, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2); // image cannot be directly passed to JLabel but has to be converted to ImageIcon 
        JLabel image=new JLabel(i3); // creates a JLabel with the image
        image.setBounds(0, 0, 900, 900); // sets the bounds of the JLabel
        add(image); // adds the JLabel to the JFrame

        JLabel text = new JLabel("Enter the amount you want to deposit"); // creates a JLabel with the text
        text.setBounds(190, 300, 400, 35); // sets the bounds of the JLabel
        text.setForeground(Color.white); // sets the foreground color of the JLabel
        text.setFont(new Font("System", Font.BOLD, 16)); // sets the font of the JLabel
        image.add(text); // adds the JLabel to the image JLabel

        amountTextField = new JTextField(); // creates a JTextField for the amount
        amountTextField.setBounds(180, 350, 320, 25); // sets the bounds of the JTextField
        amountTextField.setFont(new Font("Raleway", Font.BOLD, 22)); // sets the font of the JTextField
        amountTextField.setBackground(Color.white); // sets the background color of the JTextField
        image.add(amountTextField); // adds the JTextField to the image JLabel


        deposit = new JButton("Deposit"); // creates a JButton for deposit
        deposit.setBounds(350, 465, 150, 30); // sets the bounds of the JButton
        deposit.setBackground(Color.white); // sets the background color of the JButton
        deposit.setForeground(Color.black); // sets the foreground color of the JButton
        deposit.addActionListener(this); // adds an ActionListener to the JButton
        deposit.setFont(new Font("Raleway", Font.BOLD, 14)); // sets the font of the JButton
        image.add(deposit); // adds the JButton to the image JLabel

        back= new JButton("Back"); // creates a JButton for back
        back.setBounds(350, 500, 150, 30); // sets the bounds of the JButton
        back.setBackground(Color.white); // sets the background color of the JButton
        back.setForeground(Color.black); // sets the foreground color of the JButton
        back.addActionListener(this); // adds an ActionListener to the JButton
        back.setFont(new Font("Raleway", Font.BOLD, 14)); // sets the font of the JButton
        image.add(back); // adds the JButton to the image JLabel


        setSize(900, 900);
        setLocation(300, 0);
        getContentPane().setBackground(Color.white);
        setLayout(null); // sets default layout to null
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource() == deposit) { // if the source of the event is the deposit button
            String number = amountTextField.getText();
            Date date = new Date();
            if(number.equals("")) {
                JOptionPane.showMessageDialog(null, "Please enter the amount you want to deposit");            
            }
            else{
                try{
                    Conn c=new Conn();
                    String query = "insert into bank values('"+pinnumber+"', '"+date+"', 'Deposit', '"+number+"')"; 
                    c.s.executeUpdate(query); 
                    JOptionPane.showMessageDialog(null, "Rs. "+number+" Deposited Successfully"); 
                    new Transactions(pinnumber).setVisible(true);
                    setVisible(false);
                }
                catch(Exception e) {
                    System.out.println(e);
                }
            }
        } else if(ae.getSource() == back) { // if the source of the event is the back button
            new Transactions(pinnumber).setVisible(true); // creates a new Transactions JFrame and sets it visible
            setVisible(false); // hides the current JFrame
        }
    }
    public static void main(String[] args) {
        new Deposit("");
    }
}
