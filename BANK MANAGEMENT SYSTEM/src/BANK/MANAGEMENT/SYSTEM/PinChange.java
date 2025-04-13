// package src.BANK.MANAGEMENT.SYSTEM;
import javax.swing.*;
import java.awt.*; // imports image class
import java.awt.event.*; // imports ActionListener class


public class PinChange extends JFrame implements ActionListener {
    JButton deposit,back; // button for deposit
    JPasswordField amountTextField,repin; // text field for amount 
    String pinnumber; // to store pin number
    PinChange(String pinnumber) {
        this.pinnumber = pinnumber; // to initialize pin number
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("atm.jpg"));
        Image i2= i1.getImage().getScaledInstance(900, 900, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2); // image cannot be directly passed to JLabel but has to be converted to ImageIcon 
        JLabel image=new JLabel(i3); // creates a JLabel with the image
        image.setBounds(0, 0, 900, 900); // sets the bounds of the JLabel
        add(image); // adds the JLabel to the JFrame

        JLabel text = new JLabel("CHANGE YOUR PIN"); // creates a JLabel with the text
        text.setBounds(190, 300, 400, 35); // sets the bounds of the JLabel
        text.setForeground(Color.white); // sets the foreground color of the JLabel
        text.setFont(new Font("System", Font.BOLD, 16)); // sets the font of the JLabel
        image.add(text); // adds the JLabel to the image JLabel

        JLabel pintext=new JLabel("New PIN:");
        pintext.setBounds(190, 350, 100, 35); // sets the bounds of the JLabel
        pintext.setForeground(Color.white); // sets the foreground color of the JLabel
        pintext.setFont(new Font("System", Font.BOLD, 16)); // sets the font of the JLabel
        image.add(pintext); // adds the JLabel to the image JLabel

        amountTextField = new JPasswordField(); // creates a JTextField for the amount
        amountTextField.setBounds(310, 355, 150, 25); // sets the bounds of the JTextField
        amountTextField.setFont(new Font("Raleway", Font.BOLD, 22)); // sets the font of the JTextField
        amountTextField.setBackground(Color.white); // sets the background color of the JTextField
        image.add(amountTextField); // adds the JTextField to the image JLabel

        JLabel pintext1=new JLabel("Re-enter PIN:");
        pintext1.setBounds(190, 400, 400, 35); // sets the bounds of the JLabel 
        pintext1.setForeground(Color.white); // sets the foreground color of the JLabel
        pintext1.setFont(new Font("System", Font.BOLD, 16)); // sets the font of the JLabel
        image.add(pintext1); // adds the JLabel to the image JLabel

        repin= new JPasswordField(); 
        repin.setBounds(310, 405, 150, 25); // sets the bounds of the JTextField
        repin.setFont(new Font("Raleway", Font.BOLD, 22)); // sets the font of the JTextField
        repin.setBackground(Color.white); // sets the background color of the JTextField
        image.add(repin); // adds the JTextField to the image JLabel

        deposit = new JButton("CHANGE"); // creates a JButton for deposit
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
            String npin = new String(amountTextField.getPassword());
            String rpin = new String(repin.getPassword());
            if(!npin.equals(rpin)) {
                JOptionPane.showMessageDialog(null, "Entered PIN does not match");            
                return;
            }
            if(npin.equals("")) {
                JOptionPane.showMessageDialog(null, "Please enter the new PIN");            
                return;
            }
            if(rpin.equals("")) {
                JOptionPane.showMessageDialog(null, "Please re-enter the new PIN");            
                return;
            }
            try{
                Conn c=new Conn(); 
                String query2= "update login set pin = '"+rpin+"' where cardnumber = '"+pinnumber+"'"; // updates the pin in the login table
                String query3= "update signupthree set pin = '"+rpin+"' where cardnumber = '"+pinnumber+"'"; // updates the pin in the signupthree table
                c.s.executeUpdate(query2); // executes the query to update the pin in the login table
                c.s.executeUpdate(query3); // executes the query to update the pin in the signupthree table
                JOptionPane.showMessageDialog(null, "PIN changed successfully"); // shows a message dialog to the user
                new Transactions(pinnumber).setVisible(true);
                setVisible(false);
                }
            catch(Exception e) {
                System.out.println(e);
            }
            }
         else if(ae.getSource() == back) { // if the source of the event is the back button
            new Transactions(pinnumber).setVisible(true); // creates a new Transactions JFrame and sets it visible
            setVisible(false); // hides the current JFrame
        }
    }
    public static void main(String[] args) {
        new PinChange("");
    }
}
