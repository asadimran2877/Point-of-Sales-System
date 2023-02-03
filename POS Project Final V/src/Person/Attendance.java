package Person;



import java.sql.CallableStatement;
import java.awt.HeadlessException;
import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
import java.sql.SQLException;
//import java.util.logging.Level;
//import java.util.logging.Logger;
import javax.swing.JOptionPane;
import java.sql.CallableStatement;
import java.sql.Connection;
//mport javax.lang.model.util.Types;
import java.sql.Types;
/*

package Person;

/**
 *
 * @author Acer Laptop
 */
public class Attendance {
    
    java.sql.Connection con;
String Name;
Object year;
Object month;
Object date;
int checkin;
int checkout;
Object Timemode1;
Object Timemode2;
CallableStatement csmt2;

    public Attendance(Connection con, String Name, Object year, Object month, Object date, int checkin, int checkout, Object Timemode1, Object Timemode2, CallableStatement csmt2) {
        this.con = con;
        this.Name = Name;
        this.year = year;
        this.month = month;
        this.date = date;
        this.checkin = checkin;
        this.checkout = checkout;
        this.Timemode1 = Timemode1;
        this.Timemode2 = Timemode2;
        this.csmt2 = csmt2;
    }

    public Attendance() {
                this.con = null;
        this.Name = null;
        this.year = null;
        this.month = null;
        this.date = null;
        this.checkin = 0;
        this.checkout = 0;
        this.Timemode1 = null;
        this.Timemode2 = null;
        this.csmt2 = null;
    }

    public Connection getCon() {
        return con;
    }

    public String getName() {
        return Name;
    }

    public Object getYear() {
        return year;
    }

    public Object getMonth() {
        return month;
    }

    public Object getDate() {
        return date;
    }

    public int getCheckin() {
        return checkin;
    }

    public int getCheckout() {
        return checkout;
    }

    public Object getTimemode1() {
        return Timemode1;
    }

    public Object getTimemode2() {
        return Timemode2;
    }

    public CallableStatement getCsmt2() {
        return csmt2;
    }

    public void setCon(Connection con) {
        this.con = con;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public void setYear(Object year) {
        this.year = year;
    }

    public void setMonth(Object month) {
        this.month = month;
    }

    public void setDate(Object date) {
        this.date = date;
    }

    public void setCheckin(int checkin) {
        this.checkin = checkin;
    }

    public void setCheckout(int checkout) {
        this.checkout = checkout;
    }

    public void setTimemode1(Object Timemode1) {
        this.Timemode1 = Timemode1;
    }

    public void setTimemode2(Object Timemode2) {
        this.Timemode2 = Timemode2;
    }

    public void setCsmt2(CallableStatement csmt2) {
        this.csmt2 = csmt2;
    }

   public int addAttendance(String Name, Object year, Object month, Object date, int checkin, int checkout, Object Timemode1, Object Timemode2) throws ClassNotFoundException, SQLException
    {
    
        int status=0;
            String url="jdbc:sqlserver://localhost:1433;databaseName=POS2;user=sa;password=sa1234";
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(url);
             csmt2=con.prepareCall("{call AttendenceRecord(?,?,?,?,?,?,?,?,?)}");
            csmt2.setString(1, Name);
            csmt2.setObject(2, year);
            csmt2.setObject(3, date);
            csmt2.setObject(4, month);
            csmt2.setInt(5, checkin);
            csmt2.setObject(6,Timemode1);
            csmt2.setInt(7,checkout);
            csmt2.setObject(8, Timemode2);

            csmt2.registerOutParameter(9, Types.INTEGER);
            csmt2.executeUpdate();
            status= csmt2.getInt(9);
      
        return status;
    
    
    }





    
}
