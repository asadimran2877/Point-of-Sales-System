/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Person;
import java.awt.HeadlessException;
import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
import java.sql.SQLException;
//import java.util.logging.Level;
//import java.util.logging.Logger;
import javax.swing.JOptionPane;
import java.sql.CallableStatement;
//mport javax.lang.model.util.Types;
import java.sql.Types;

/**
 *
 * @author HP
 */
public class Customer {
    String FirstName;
	String LastName;
	String CNIC ;
	String Phone ;
	String Age ;
	String CustomerID; 
	String Address 	;

   public void Customer() {
        this.FirstName = null;
        this.LastName =null;
        this.CNIC = null;
        this.Phone = null;
        this.Age = null;
        this.CustomerID = null;
        this.Address = null;
    }     
    public void Customer(String FirstName, String LastName, String CNIC, String Phone, String Age, String CustomerID, String Address) {
        this.FirstName = FirstName;
        this.LastName = LastName;
        this.CNIC = CNIC;
        this.Phone = Phone;
        this.Age = Age;
        this.CustomerID = CustomerID;
        this.Address = Address;
    }
    public String getFirstName() {
        return FirstName;
    }

    public String getLastName() {
        return LastName;
    }

    public String getCNIC() {
        return CNIC;
    }

    public String getPhone() {
        return Phone;
    }

    public String getAge() {
        return Age;
    }

    public String getCustomerID() {
        return CustomerID;
    }

    public String getAddress() {
        return Address;
    }
    public int AddCustomer(Customer obj)
{
    
    try{
        CallableStatement csmt=null;
         java.sql.Connection con=null;
            String url="jdbc:sqlserver://localhost:1433;databaseName=POS2;user=sa;password=sa1234";
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
             con = DriverManager.getConnection(url);
        
             
              csmt=con.prepareCall("{call AddCustomer(?,?,?,?,?,?,?,?)}");
              csmt.setString(1, obj.getFirstName());
              csmt.setString(2, obj.getLastName());
              csmt.setString(3, obj.getCNIC());
              csmt.setString(4, obj.getPhone());
              csmt.setString(5, obj.getAge());
              csmt.setString(6,obj.getCustomerID());
              csmt.setString(7, obj.getAddress());
         
              csmt.registerOutParameter(8, Types.INTEGER);
              csmt.executeUpdate();
              int status= csmt.getInt(8);   
               
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
    return 0;
}
}