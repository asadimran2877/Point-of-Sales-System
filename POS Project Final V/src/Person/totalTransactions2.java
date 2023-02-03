/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Person;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Acer Laptop
 */
public class totalTransactions2 {
    
    String tID;
    String amount;
    String tDate;

    public totalTransactions2(String id, String amount, String dateT) {
        this.tID = id;
        this.amount = amount;
        this.tDate = dateT;
    }

    public totalTransactions2() {
        this.tID=null;
        this.amount=null;
        this.tDate=null;
    }

    public String gettID() {
        return tID;
    }

    public String getAmount() {
        return amount;
    }

    public String gettDate() {
        return tDate;
    }

    public void settID(String tID) {
        this.tID = tID;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public void settDate(String tDate) {
        this.tDate = tDate;
    }
    
    
    
    public ArrayList<totalTransactions2> showT() throws ClassNotFoundException, SQLException
    {
            String url="jdbc:sqlserver://localhost:1433;databaseName=POS2;user=sa;password=sa1234";
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        Connection con = DriverManager.getConnection(url);
        Statement st = con.createStatement();
        String query = "Select *from TransactionDetails";
        ResultSet rs = st.executeQuery(query);
        ArrayList<totalTransactions2> tt= new ArrayList<totalTransactions2>();
        
            while(rs.next())
            {
                String dateT=rs.getString("transactionDate");
                int amount=rs.getInt("transactionAmount");
                int id=rs.getInt("transactionID");
                String am=String.valueOf(amount);
                String id2=String.valueOf(id);
           
                tt.add(new totalTransactions2(id2,am,dateT));
                
            }
        return tt;
    }
    
}
