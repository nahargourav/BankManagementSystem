// package src.BANK.MANAGEMENT.SYSTEM;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.util.Date;
public class FastCash extends JFrame implements ActionListener{
    JButton deposit,withdrawl,fastcash,miniStatement,pinChange,balanceEnquiry,exit;
    String pin;
    FastCash(String pin)
    {
        this.pin=pin;
        ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("atm.jpg"));
        Image i2=i1.getImage().getScaledInstance(900,900,Image.SCALE_DEFAULT);
        ImageIcon i3=new ImageIcon(i2);
        JLabel image=new JLabel(i3);
        image.setBounds(0,0,900,900);
        add(image);

        JLabel text=new JLabel("Select Your Withdrawl Amount");
        text.setBounds(210,310,400,35);
        text.setForeground(Color.white);
        text.setFont(new Font("System",Font.BOLD,16));
        image.add(text);

        deposit=new JButton("Rs 100");
        deposit.setBounds(170,380,120,30);
        image.add(deposit);
        deposit.addActionListener(this);

        withdrawl=new JButton("Rs 500");
        withdrawl.setBounds(380,380,120,30);
        image.add(withdrawl);
        withdrawl.addActionListener(this);

        fastcash=new JButton("Rs 1000");
        fastcash.setBounds(170,415,120,30);
        image.add(fastcash);
        fastcash.addActionListener(this);

        miniStatement=new JButton("Rs 2000");
        miniStatement.setBounds(380,415,120,30);
        image.add(miniStatement);
        miniStatement.addActionListener(this);

        pinChange=new JButton("Rs 5000");
        pinChange.setBounds(170,450,120,30);
        image.add(pinChange);
        pinChange.addActionListener(this);

        balanceEnquiry=new JButton("Rs 10000");
        balanceEnquiry.setBounds(380,450,120,30);
        image.add(balanceEnquiry);
        balanceEnquiry.addActionListener(this);

        exit=new JButton("BACK");
        exit.setBounds(275,495,120,30);
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
            new Transactions(pin).setVisible(true);
            setVisible(false);
        }
        else{
            String amount=((JButton)ae.getSource()).getText().substring(3);
            Conn c=new Conn();
            try{
                ResultSet rs=c.s.executeQuery("select * from bank where pin='"+pin+"'");
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
                String query="insert into bank values('"+pin+"','"+date+"','Withdrawl','"+amount+"')";
                c.s.executeUpdate(query);
                JOptionPane.showMessageDialog(null, "Rs "+amount+" Debited Successfully");
                new Transactions(pin).setVisible(true);
                setVisible(false);
            }
            catch(Exception e){
                System.out.println(e);
            }
        }
    }
public static void main(String[] args) {
    new FastCash("");
}
}
