/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Person;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Acer Laptop
 */
public class Sales {
    int profit;
    int loss;
    int total;

    public Sales(int profit, int loss, int total) {
        this.profit = profit;
        this.loss = loss;
        this.total = total;
    }

    public Sales() {
       profit=0;
       loss=0;
total=0;//To change body of generated methods, choose Tools | Templates.
    }

    public int getProfit() {
        return profit;
    }

    public int getLoss() {
        return loss;
    }

    public int getTotal() {
        return total;
    }

    public void setProfit(int profit) {
        this.profit = profit;
    }

    public void setLoss(int loss) {
        this.loss = loss;
    }

    public void setTotal(int total) {
        this.total = total;
    }
    
    
     public tempSales details() throws SQLException{
   
             String url="jdbc:sqlserver://localhost:1433;databaseName=POS2;user=sa;password=sa1234";
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Sales.class.getName()).log(Level.SEVERE, null, ex);
        }
            Connection con = DriverManager.getConnection(url);
                PreparedStatement pst = con.prepareStatement("Select * from transactionDetails");

            ResultSet rs = pst.executeQuery();
                
              
            int costPrice4=0;
                    int amount=0;
                while(rs.next())
                {
                    
                    costPrice4=costPrice4+rs.getInt("transactionCostPrice");
                    amount=amount+rs.getInt("transactionAmount");
                            
   
                }
                
                tempSales ts=new tempSales();
                
             if(costPrice4>amount)
             {
                 int result=costPrice4-amount;
                 ts.setLoss(result);
                 ts.setProfit(0);
                 ts.setAmount(amount);
                 
             }
              if(costPrice4<=amount)
             {
               int result=amount-costPrice4;
                 ts.setLoss(0);
                 ts.setProfit(result);
                 ts.setAmount(amount);
             }
              return ts;
          
        }
    
     }

