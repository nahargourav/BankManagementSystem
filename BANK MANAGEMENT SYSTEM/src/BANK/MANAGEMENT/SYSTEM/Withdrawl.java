// package src.BANK.MANAGEMENT.SYSTEM;
import javax.swing.*;
import java.awt.*; // imports image class
import java.awt.event.*; // imports ActionListener class
import java.sql.ResultSet;
import java.util.Date;


public class Withdrawl extends JFrame implements ActionListener {
    JButton withdraw,back; // button for deposit
    JTextField amountTextField; // text field for amount 
    String pinnumber; // to store pin number
    Withdrawl(String pinnumber) {
        this.pinnumber = pinnumber; // to initialize pin number
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("atm.jpg"));
        Image i2= i1.getImage().getScaledInstance(900, 900, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2); // image cannot be directly passed to JLabel but has to be converted to ImageIcon 
        JLabel image=new JLabel(i3); // creates a JLabel with the image
        image.setBounds(0, 0, 900, 900); // sets the bounds of the JLabel
        add(image); // adds the JLabel to the JFrame

        JLabel text = new JLabel("Enter the amount you want to withdraw"); // creates a JLabel with the text
        text.setBounds(190, 300, 400, 35); // sets the bounds of the JLabel
        text.setForeground(Color.white); // sets the foreground color of the JLabel
        text.setFont(new Font("System", Font.BOLD, 16)); // sets the font of the JLabel
        image.add(text); // adds the JLabel to the image JLabel

        amountTextField = new JTextField(); // creates a JTextField for the amount
        amountTextField.setBounds(180, 350, 320, 25); // sets the bounds of the JTextField
        amountTextField.setFont(new Font("Raleway", Font.BOLD, 22)); // sets the font of the JTextField
        amountTextField.setBackground(Color.white); // sets the background color of the JTextField
        image.add(amountTextField); // adds the JTextField to the image JLabel


        withdraw = new JButton("Withdraw"); // creates a JButton for withdraw
        withdraw.setBounds(350, 465, 150, 30); // sets the bounds of the JButton
        withdraw.setBackground(Color.white); // sets the background color of the JButton
        withdraw.setForeground(Color.black); // sets the foreground color of the JButton
        withdraw.addActionListener(this); // adds an ActionListener to the JButton
        withdraw.setFont(new Font("Raleway", Font.BOLD, 14)); // sets the font of the JButton
        image.add(withdraw); // adds the JButton to the image JLabel

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
        if(ae.getSource() == withdraw) { // if the source of the event is the withdraw button
            String amount = amountTextField.getText();
            if(amount.equals("")) {
                JOptionPane.showMessageDialog(null, "Please enter the amount you want to withdraw");            
            }
            else{
            Conn c=new Conn();
            try{
                ResultSet rs=c.s.executeQuery("select * from bank where pin='"+pinnumber+"'");
                int balance=0;
                while(rs.next()){
                    if(rs.getString("type").equals("Deposit")){
                        balance+=Integer.parseInt(rs.getString("amount"));
                    }else{
                        balance-=Integer.parseInt(rs.getString("amount"));
                    }
                }
                if(balance<Integer.parseInt(amount)){
                    JOptionPane.showMessageDialog(null, "Insufficient Balance");
                    return;
                }
                Date date=new Date();
                String query="insert into bank values('"+pinnumber+"','"+date+"','Withdrawl','"+amount+"')";
                c.s.executeUpdate(query);
                JOptionPane.showMessageDialog(null, "Rs "+amount+" Withdrawl Successfull");
            }
            catch(Exception e){
                System.out.println(e);
            }
            new Transactions(pinnumber).setVisible(true);
            setVisible(false);
            }
            
        } else if(ae.getSource() == back) { // if the source of the event is the back button
            new Transactions(pinnumber).setVisible(true); // creates a new Transactions JFrame and sets it visible
            setVisible(false); // hides the current JFrame
        }
    }
    public static void main(String[] args) {
        new Withdrawl("");
    }
}
