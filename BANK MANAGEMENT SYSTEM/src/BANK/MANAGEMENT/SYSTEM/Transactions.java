// package src.BANK.MANAGEMENT.SYSTEM;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
public class Transactions extends JFrame implements ActionListener{
    JButton deposit,withdrawl,fastcash,miniStatement,pinChange,balanceEnquiry,exit;
    String pin;
    Transactions(String pin)
    {
        this.pin=pin;

        ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("atm.jpg"));
        Image i2=i1.getImage().getScaledInstance(900,900,Image.SCALE_DEFAULT);
        ImageIcon i3=new ImageIcon(i2);
        JLabel image=new JLabel(i3);
        image.setBounds(0,0,900,900);
        add(image);

        JLabel text=new JLabel("Please Select Your Transaction");
        text.setBounds(210,310,400,35);
        text.setForeground(Color.white);
        text.setFont(new Font("System",Font.BOLD,16));
        image.add(text);

        deposit=new JButton("DEPOSIT");
        deposit.setBounds(150,380,120,30);
        image.add(deposit);
        deposit.addActionListener(this);

        withdrawl=new JButton("WITHDRAWL");
        withdrawl.setBounds(380,380,120,30);
        image.add(withdrawl);
        withdrawl.addActionListener(this);

        fastcash=new JButton("FAST CASH");
        fastcash.setBounds(150,415,120,30);
        image.add(fastcash);
        fastcash.addActionListener(this);

        miniStatement=new JButton("MINI STATEMENT");
        miniStatement.setBounds(380,415,120,30);
        image.add(miniStatement);
        miniStatement.addActionListener(this);

        pinChange=new JButton("PIN CHANGE");
        pinChange.setBounds(150,450,120,30);
        image.add(pinChange);
        pinChange.addActionListener(this);

        balanceEnquiry=new JButton("BALANCE ENQUIRY");
        balanceEnquiry.setBounds(380,450,120,30);
        image.add(balanceEnquiry);
        balanceEnquiry.addActionListener(this);

        exit=new JButton("EXIT");
        exit.setBounds(265,485,120,30);
        image.add(exit);
        exit.addActionListener(this);

        setSize(900,900);
        setLocation(300,0);
        getContentPane().setBackground(Color.white);
        setLayout(null);
        // setUndecorated(true);
        setVisible(true);
    }
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource()==exit){
            System.exit(0);
        }
        else if(ae.getSource()==deposit){
            new Deposit(pin).setVisible(true);
            setVisible(false);
        }
        else if(ae.getSource()==withdrawl){
            new Withdrawl(pin).setVisible(true);
            setVisible(false);
        }
        else if(ae.getSource()==fastcash){
            new FastCash(pin).setVisible(true);
            setVisible(false);
        }
        else if(ae.getSource()==miniStatement){
            new MiniStatement(pin).setVisible(true);
            // setVisible(false);
        }
        else if(ae.getSource()==pinChange){
            new PinChange(pin).setVisible(true);
            setVisible(false);
        }
        else if(ae.getSource()==balanceEnquiry){
            new BalanceEnquiry(pin).setVisible(true);
            setVisible(false);
        }
    }
public static void main(String[] args) {
    new Transactions("");
}
}
