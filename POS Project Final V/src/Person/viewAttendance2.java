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
public class viewAttendance2 {
    
    String cashierID;
    String year;
    String date;
    String month;
    String checkInTIme;
    String mode1;
    String checkOutTime;
    String mode2;

    public viewAttendance2(String cashierID, String year, String date, String month, String checkInTIme, String mode1, String checkOutTime, String mode2) {
        this.cashierID = cashierID;
        this.year = year;
        this.date = date;
        this.month = month;
        this.checkInTIme = checkInTIme;
        this.mode1 = mode1;
        this.checkOutTime = checkOutTime;
        this.mode2 = mode2;
    }

    public viewAttendance2() {
        
             this.cashierID = null;
        this.year = null;
        this.date = null;
        this.month = null;
        this.checkInTIme = null;
        this.mode1 = null;
        this.checkOutTime = null;
        this.mode2 = null;
    }

    public String getCashierID() {
        return cashierID;
    }

    public String getYear() {
        return year;
    }

    public String getDate() {
        return date;
    }

    public String getMonth() {
        return month;
    }

    public String getCheckInTIme() {
        return checkInTIme;
    }

    public String getMode1() {
        return mode1;
    }

    public String getCheckOutTime() {
        return checkOutTime;
    }

    public String getMode2() {
        return mode2;
    }

    public void setCashierID(String cashierID) {
        this.cashierID = cashierID;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public void setCheckInTIme(String checkInTIme) {
        this.checkInTIme = checkInTIme;
    }

    public void setMode1(String mode1) {
        this.mode1 = mode1;
    }

    public void setCheckOutTime(String checkOutTime) {
        this.checkOutTime = checkOutTime;
    }

    public void setMode2(String mode2) {
        this.mode2 = mode2;
    }
    
    
    
    public ArrayList<viewAttendance2> showAttendance()
    {
        ArrayList<viewAttendance2> va2= new ArrayList<viewAttendance2> ();
   
            //tb1.setRowCount(0);
            String url="jdbc:sqlserver://localhost:1433;databaseName=POS2;user=sa;password=sa1234";
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } 
        catch (ClassNotFoundException ex) {
            Logger.getLogger(viewAttendance2.class.getName()).log(Level.SEVERE, null, ex);
        }
        Connection con = null;
        try {
            con = DriverManager.getConnection(url);
        } 
        catch (SQLException ex) {
            Logger.getLogger(viewAttendance2.class.getName()).log(Level.SEVERE, null, ex);
        }
        Statement st = null;
        try {
            st = con.createStatement();
        } 
        catch (SQLException ex) {
            Logger.getLogger(viewAttendance2.class.getName()).log(Level.SEVERE, null, ex);
        }
        String query = "Select *from Attendence";
        ResultSet rs = null;
        try {
            rs = st.executeQuery(query);
        } 
        catch (SQLException ex) {
            Logger.getLogger(viewAttendance2.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            while(rs.next())
            {
                String CashierID = rs.getString("CashierID");
                String year2=rs.getString("Year");
               String date2=rs.getString("Date");
                String month2=rs.getString("Month");
                String CI_time=String.valueOf(rs.getInt("CheckIn"));
                String CI_mode=rs.getString("In_mode");
                String CO_time=String.valueOf(rs.getInt("Checkout"));
                String CO_mode=rs.getString("Out_Mode");
                
              va2.add(new viewAttendance2(CashierID,year2,date2,month2,CI_time,CI_mode,CO_time,CO_mode));
            }

            // while(tb1.getRowCount() > 0)
        } 
        
        catch (SQLException ex) {
            Logger.getLogger(viewAttendance2.class.getName()).log(Level.SEVERE, null, ex);
        }

       
      
      return va2;
    }
    
}
