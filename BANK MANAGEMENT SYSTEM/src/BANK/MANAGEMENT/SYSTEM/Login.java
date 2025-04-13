// package src.BANK.MANAGEMENT.SYSTEM;
import java.awt.*;      //imports image class
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;   //install JFrame from extended package of java
import java.sql.ResultSet; //to get result from database
public class Login extends JFrame implements ActionListener
{
    JTextField cardTextField;
    JButton login , signup, clear;
    JPasswordField pinTextField;
    Login()
    {
        ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("logo.jpg"));
        Image i2= i1.getImage().getScaledInstance(100, 100,Image.SCALE_DEFAULT);
        ImageIcon i3=new ImageIcon(i2); //image cannot be directly passed to JLabel but has to br coverted to ImageIcon

        JLabel label=new JLabel(i3);
        add(label);
        setLayout(null);  //sets default layout to null
        label.setBounds(70, 10, 100, 100); //by default border layout is used, so icon will be located at center

        JLabel text=new JLabel("Welcome to ATM");
        text.setFont(new Font("Osward",Font.BOLD,38));
        text.setBounds(200, 40, 400, 40);
        add(text);

        JLabel cardno=new JLabel("Card No:");
        cardno.setFont(new Font("Raleway",Font.BOLD,28));
        cardno.setBounds(120, 150, 150, 30);
        add(cardno);

        cardTextField=new JTextField();
        cardTextField.setBounds(300, 150, 230, 30);
        cardTextField.setFont(new Font("Arial",Font.BOLD,14));
        add(cardTextField);

        JLabel pin=new JLabel("PIN:");
        pin.setFont(new Font("Raleway",Font.BOLD,28));
        pin.setBounds(120, 220, 150, 30);
        add(pin);

        pinTextField=new JPasswordField();
        pinTextField.setBounds(300, 220, 230, 30);
        pinTextField.setFont(new Font("Arial",Font.BOLD,14));
        add(pinTextField);

        login=new JButton("SIGN IN");
        login.setBounds(300, 300, 100, 30);
        login.setBackground(Color.BLACK);
        login.setForeground(Color.WHITE);
        login.addActionListener(this);
        add(login);

        clear=new JButton("Clear");
        clear.setBounds(430, 300, 100, 30);
        clear.setBackground(Color.BLACK);
        clear.setForeground(Color.white);
        clear.addActionListener(this);
        add(clear);

        signup=new JButton("SIGN UP");
        signup.setBounds(300, 350, 230, 30);
        signup.setBackground(Color.BLACK);
        signup.setForeground(Color.white);
        signup.addActionListener(this);
        add(signup);

        getContentPane().setBackground(Color.WHITE);   //gets whole frame

        setTitle("ATM");
        setSize(800,480); //set size of the frame
        setVisible(true); //default visiblity of frame is false
        setLocation(350,200);  //by default fram opens at top left pos

    }
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource()==clear)
        {
            cardTextField.setText("");
            pinTextField.setText("");
        }
        else if(ae.getSource()==login)
        {
            Conn conn=new Conn();
            String cardnumber=cardTextField.getText(); //get text from text field   
            String pin = new String(pinTextField.getPassword()); //convert char[] to String
            String query="select * from login where cardnumber='"+cardnumber+"' and pin='"+pin+"'"; //sql query to check if card number and pin is correct or not
            try{
                ResultSet rs=conn.s.executeQuery(query); //execute query(DDL COMMAND , then executeQuery() instead of updateQuery() in DML COMMAND)

                //it also returns data which is of type ResultSet

                if(rs.next()) //checks if data was returned
                {
                    setVisible(false); //close current frame
                    new Transactions(cardnumber).setVisible(true); //open new frame of transactions
                }
                else
                {
                    JOptionPane.showMessageDialog(null,"Incorrect Card Number or PIN"); //if data is not present in the table
                }
            }
            catch(Exception e)
            {
                System.out.println(e);
            }
            
        }
        else if(ae.getSource()==signup)
        {
            setVisible(false);
            new SignupOne().setVisible(true);
        }
    }
    public static void main(String[]args)
    {
        new Login();
    }
}