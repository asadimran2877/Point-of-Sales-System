/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Person;

import java.awt.HeadlessException;
import java.sql.CallableStatement;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import javax.swing.JOptionPane;

/**
 *
 * @author HP
 */
public class Cashier {
    String  FirstName;
    String  LastName; 
    String  CashierID;
    String  Password ;
    String  Age;
    String  CNIC ;
    String  PhoneNumber; 
    String  Address ;

public Cashier()
{
    this.FirstName=null;
    this.LastName=null; 
    this.CashierID=null;
    this.Password =null;
    this.Age=null;
    this.CNIC =null;
    this.PhoneNumber=null; 
    this.Address=null ;
}
public void Cashier(String FirstName,String LastName,String CashierID,String Password,
                String Age,String CNIC,String PhoneNumber,String Address)
{
    this.FirstName=FirstName;
    this.LastName=LastName; 
    this.CashierID=CashierID;
    this.Password =Password;
    this.Age=Age;
    this.CNIC =CNIC;
    this.PhoneNumber=PhoneNumber; 
    this.Address=Address ;
}
    public String getFirstName() {
        return FirstName;
    }

    public String getLastName() {
        return LastName;
    }

    public String getCashierID() {
        return CashierID;
    }

    public String getPassword() {
        return Password;
    }

    public String getAge() {
        return Age;
    }

    public String getCNIC() {
        return CNIC;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public String getAddress() {
        return Address;
    }

    public void setFirstName(String FirstName) {
        this.FirstName = FirstName;
    }

    public void setLastName(String LastName) {
        this.LastName = LastName;
    }

    public void setCashierID(String CashierID) {
        this.CashierID = CashierID;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public void setAge(String Age) {
        this.Age = Age;
    }

    public void setCNIC(String CNIC) {
        this.CNIC = CNIC;
    }

    public void setPhoneNumber(String PhoneNumber) {
        this.PhoneNumber = PhoneNumber;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

public int AddCashier(Cashier obj)
{
    
    try{
        CallableStatement csmt=null;
         java.sql.Connection con=null;
            String url="jdbc:sqlserver://localhost:1433;databaseName=POS2;user=sa;password=sa1234";
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
             con = DriverManager.getConnection(url);
        
             
              csmt=con.prepareCall("{call AddCashier(?,?,?,?,?,?,?,?,?)}");
              csmt.setString(1, obj.getFirstName());
              csmt.setString(2, obj.getLastName());
              csmt.setString(3, obj.getCNIC());
              csmt.setString(4, obj.getPhoneNumber());
              csmt.setString(5, obj.getAge());
              csmt.setString(6,obj.getPassword());
              csmt.setString(7, obj.getCashierID());
              
              csmt.setString(8, obj.getAddress());
              
              
              csmt.registerOutParameter(9, Types.INTEGER);
              csmt.executeUpdate();
              int status= csmt.getInt(9);   
               
        switch (status) {
            case 0:
                //JOptionPane.showMessageDialog(null,"Cashier with this CNIC or ID Already exist");
                return 0;
            case 1:
                return 1;
                // JOptionPane.showMessageDialog(null,"Inserted Successfully");
            default:
                return 2;
        }
            }
            catch(HeadlessException | ClassNotFoundException | SQLException e)
            {
                JOptionPane.showMessageDialog(null,e);
            }
    
    
    
    return 3;
}

public int RemoveCashier(Cashier obj)
{
    java.sql.Connection con=null;
    
    CallableStatement csmt=null;
    
    try{
            String url="jdbc:sqlserver://localhost:1433;databaseName=POS2;user=sa;password=sa1234";
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
             con = DriverManager.getConnection(url);
            // Userid=userId.getText();
             
             
              csmt=con.prepareCall("{call RemoveCashier(?,?)}");
              csmt.setString(1, obj.getCashierID());
              
              
            
              csmt.registerOutParameter(2, Types.INTEGER);
              csmt.executeUpdate();
              int status= csmt.getInt(2);   
               
               if(status==0)
               {
                  // JOptionPane.showMessageDialog(null,"No Cashier with this ID found");
                   return 0;
               }
               else
               {
                   return 1;
                  // JOptionPane.showMessageDialog(null,"Deleted Successfully");
               }
            }
            catch(HeadlessException | ClassNotFoundException | SQLException e)
            {
                JOptionPane.showMessageDialog(null,e);
            }
    
    
    
    
    return 2;
}
}
