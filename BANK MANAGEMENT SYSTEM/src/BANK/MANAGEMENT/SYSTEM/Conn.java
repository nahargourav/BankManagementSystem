// package src.BANK.MANAGEMENT.SYSTEM;
// Steps in JDBC-Java database connection
// 1.register the driver 
// 2.create connection 
// 3.create statement
// 4.execute query
// 5.close connection
import java.sql.*;

public class Conn {
    Statement s;
    Connection c;
    public Conn()
    {
        try{
            // Class.ForName(com.mysql.cj.jdbc.Driver);  //register a driver
            c=DriverManager.getConnection("jdbc:mysql:///bankmanagementsystem","root","password");  //Create Connection

            s=c.createStatement();   //Create statement
        }catch(Exception e)
        {
            System.out.println(e);
        }
    }
}
