/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Person;

import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Acer Laptop
 */
public class viewUsers {
    
    String firstName;
    String lastName;
    String cashierID;
    String password;
    String age;
    String cnic;
    String phneNo;
    String address;

    public viewUsers(String firstName, String lastName, String cashierID, String password, String age, String cnic, String phneNo, String address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.cashierID = cashierID;
        this.password = password;
        this.age = age;
        this.cnic = cnic;
        this.phneNo = phneNo;
        this.address = address;
    }

    public viewUsers() {
           this.firstName = null;
        this.lastName = null;
        this.cashierID = null;
        this.password = null;
        this.age = null;
        this.cnic = null;
        this.phneNo = null;
        this.address = null;
        
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getCashierID() {
        return cashierID;
    }

    public String getPassword() {
        return password;
    }

    public String getAge() {
        return age;
    }

    public String getCnic() {
        return cnic;
    }

    public String getPhneNo() {
        return phneNo;
    }

    public String getAddress() {
        return address;
    }
    
    
    public ArrayList<viewUsers> showUsers(){
        
           ArrayList<viewUsers> vu= new ArrayList<viewUsers>();
            try{
          //tb1.setRowCount(0);
            String url="jdbc:sqlserver://localhost:1433;databaseName=POS2;user=sa;password=sa1234";
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(viewUsers.class.getName()).log(Level.SEVERE, null, ex);
        }
                Connection con = DriverManager.getConnection(url);
                Statement st = con.createStatement();
                String query = "Select *from Cashier";
                ResultSet rs = st.executeQuery(query);
           while(rs.next())
           {
                String FirstName = rs.getString("FirstName");
                String LastName = rs.getString("LastName");
                String CashierID = rs.getString("CashierID");
                String Password = rs.getString("Password");
                String Age = rs.getString("Age");
                String CNIC = rs.getString("CNIC");
                String PhoneNumber = rs.getString("PhoneNumber");
                String Address = rs.getString("Address");
               
                
                vu.add(new viewUsers(FirstName,LastName,CashierID,Password, Age, CNIC, PhoneNumber,Address));
               
           }
              return vu;
               
            }
            catch(HeadlessException | SQLException e)
            {
                JOptionPane.showMessageDialog(null,e);
            }
        return vu;
         
    }
   
}
