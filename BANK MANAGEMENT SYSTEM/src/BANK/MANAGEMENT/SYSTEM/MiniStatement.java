
// package src.BANK.MANAGEMENT.SYSTEM;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.ResultSet;

public class MiniStatement extends JFrame implements ActionListener {
    JButton deposit, withdrawl, fastcash, miniStatement, pinChange, balanceEnquiry, exit;
    String pin;

    MiniStatement(String pin) {
        this.pin = pin;

        JLabel text = new JLabel("MINI STATEMENT:");
        text.setBounds(20, 20, 200, 35);
        text.setForeground(Color.black);
        text.setFont(new Font("System", Font.BOLD, 16));
        add(text);

        JLabel card=new JLabel("Card Number: " + pin.substring(0, 4) + "-XXXX-XXXX-"+ pin.substring(12, 16));
        card.setBounds(20, 50, 300, 35);
        card.setForeground(Color.black);
        add(card);

        JLabel mini=new JLabel();
        mini.setForeground(Color.black);
        add(mini);
        
        int balance = 0;
        Conn c = new Conn();
        try {
            ResultSet rs = c.s.executeQuery("select * from bank where pin='" + pin + "'");
            while (rs.next()) {
                if (rs.getString("type").equals("Deposit")) {
                    balance += Integer.parseInt(rs.getString("amount"));
                } else {
                    balance -= Integer.parseInt(rs.getString("amount"));
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        try {
            ResultSet rs = c.s.executeQuery("select * from bank where pin='" + pin + "'");
            while (rs.next()) {
                mini.setText(mini.getText() + "<html>" + rs.getString("date") + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + rs.getString("type") + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + rs.getString("amount") + "<br><br><html>");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        mini.setBounds(20, 100, 400, 200);

        JLabel amt = new JLabel("Your Current Account Balance is Rs " + balance);
        amt.setBounds(20, 370, 400, 35);
        amt.setForeground(Color.black);
        add(amt);

        exit = new JButton("BACK");
        exit.setBackground(Color.black);
        exit.setForeground(Color.white);
        exit.setFont(new Font("System", Font.BOLD, 14));
        exit.setBounds(270, 450, 120, 30);
        exit.setFocusPainted(false); 
        add(exit);
        exit.addActionListener(this);

        setSize(450, 550);
        setLocation(600, 100);
        getContentPane().setBackground(Color.white);
        setLayout(null);
        // setUndecorated(true);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == exit) {
            // new Transactions(pin).setVisible(true);
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new MiniStatement("");
    }
}
