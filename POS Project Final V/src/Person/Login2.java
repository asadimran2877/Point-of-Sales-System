/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Person;



import java.awt.HeadlessException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
//import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 *
 * @author Acer Laptop
 */
public class Login2 {
    String userName;
    String Password;

    public Login2()
    {
        this.userName=null;
        this.Password=null;
    }
    public Login2(String userName, String password)
    {
        this.userName=userName;
        this.Password=password;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }
    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return Password;
    }

    public int ValidateManager(Login2 obj)
    {
        try{
           
            
            java.sql.Connection con=null;
            String url="jdbc:sqlserver://localhost:1433;databaseName=POS2;user=sa;password=sa1234";
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
             con = DriverManager.getConnection(url);
            
            String user=obj.getUserName();
            String pass=obj.getPassword();
            String sql="Select * from Manager where ManagerID='"+user+"' and Password='"+pass+"'";
            PreparedStatement pst=con.prepareStatement(sql);
          
            ResultSet rs=pst.executeQuery();
            if(rs.next())
            {
                
                return 1;
            }
            else
            {
               
                return 0;
            }
           // con.close();
            }
            catch(HeadlessException | ClassNotFoundException | SQLException e)
            {
                JOptionPane.showMessageDialog(null,e);
            }
        return 0;
    }
    
public int ValidateCashier(Login2 obj)
{
    
        try
           {
            java.sql.Connection con=null;
            String url="jdbc:sqlserver://localhost:1433;databaseName=POS2;user=sa;password=sa1234";
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(url);
          
            String user=obj.getUserName();
            String pass=obj.getPassword();
            String sql="Select * from Cashier where CashierID='"+user+"' and Password='"+pass+"'";
            PreparedStatement pst=con.prepareStatement(sql);
          
            ResultSet rs=pst.executeQuery();
            if(rs.next())
            {
               return 1;
                
            }
            else
            {
                return 0;
            }
          //  con.close();
            }
           catch(HeadlessException | ClassNotFoundException | SQLException e)
            {
                JOptionPane.showMessageDialog(null,e);
            }   
             
    
    return 0;
}
    
        
}
