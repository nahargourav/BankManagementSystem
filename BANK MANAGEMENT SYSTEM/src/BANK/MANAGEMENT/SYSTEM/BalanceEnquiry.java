
// package src.BANK.MANAGEMENT.SYSTEM;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.ResultSet;

public class BalanceEnquiry extends JFrame implements ActionListener {
    JButton deposit, withdrawl, fastcash, miniStatement, pinChange, balanceEnquiry, exit;
    String pin;

    BalanceEnquiry(String pin) {
        this.pin = pin;
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(900, 900, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 900, 900);
        add(image);

        JLabel text = new JLabel("BALANCE ENQUIRY");
        text.setBounds(210, 310, 400, 35);
        text.setForeground(Color.white);
        text.setFont(new Font("System", Font.BOLD, 16));
        image.add(text);

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

        JLabel amt = new JLabel("Your Current Account Balance is Rs " + balance);
        amt.setBounds(170, 350, 400, 35);
        amt.setForeground(Color.white);
        amt.setFont(new Font("System", Font.BOLD, 16));
        image.add(amt);

        exit = new JButton("BACK");
        exit.setBounds(275, 495, 120, 30);
        image.add(exit);
        exit.addActionListener(this);

        setSize(900, 900);
        setLocation(300, 0);
        getContentPane().setBackground(Color.white);
        setLayout(null);
        // setUndecorated(true);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == exit) {
            new Transactions(pin).setVisible(true);
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new BalanceEnquiry("");
    }
}
