/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Person;
import java.awt.HeadlessException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
/**
/**
 *
 * @author Shahnawaz Ali Raja
 */
public class Voucher {
    String VoucherCode;
    Float Discount;

    public void Voucher() 
    {
        this.VoucherCode =null;
        this.Discount =0.0f;
    }
    public void Voucher(String VoucherCode, Float Discount)
    {
        this.VoucherCode = VoucherCode;
        this.Discount = Discount;
    }
    public String getVoucherCode() {
        return VoucherCode;
    }

    public void setVoucherCode(String VoucherCode) {
        this.VoucherCode = VoucherCode;
    }

    public Float getDiscount() {
        return Discount;
    }

    public void setDiscount(Float Discount) {
        this.Discount = Discount;
    }
    public int Addvoucher(Voucher obj)
    {
         try{
         CallableStatement csmt=null;
         java.sql.Connection con=null;
            String url="jdbc:sqlserver://localhost:1433;databaseName=POS2;user=sa;password=sa1234";
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
             con = DriverManager.getConnection(url);
        
             
              csmt=con.prepareCall("{call Addvoucher(?,?,?)}");
              csmt.setString(1, obj.getVoucherCode()); 
              csmt.setFloat(2, obj.getDiscount()); 
              csmt.registerOutParameter(3, Types.INTEGER);
              csmt.executeUpdate();
              int status= csmt.getInt(3);   
               
        switch (status) {
            case 0:
                
                return 0;
            case 1:
                return 1;
              
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
    public int Removevoucher(Voucher obj)
    {
        try{
         CallableStatement csmt=null;
         java.sql.Connection con=null;
            String url="jdbc:sqlserver://localhost:1433;databaseName=POS2;user=sa;password=sa1234";
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
             con = DriverManager.getConnection(url);
        
             
              csmt=con.prepareCall("{call removeVoucher(?,?)}");
              csmt.setString(1, obj.getVoucherCode()); 
              csmt.registerOutParameter(2, Types.INTEGER);
              csmt.executeUpdate();
              int status= csmt.getInt(2);   
               
        switch (status) {
            case 0:
                
                return 0;
            case 1:
                return 1;
              
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
   
    public ArrayList<tempAtt> viewVouchers() throws ClassNotFoundException, SQLException
    {
        DefaultTableModel tb1;
          //tb1.setRowCount(0);
            String url="jdbc:sqlserver://localhost:1433;databaseName=POS2;user=sa;password=sa1234";
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        java.sql.Connection con = null;
        try {
            con = DriverManager.getConnection(url);
        } catch (SQLException ex) {
            Logger.getLogger(Voucher.class.getName()).log(Level.SEVERE, null, ex);
        }
        Statement st = null;
        try {
            st = con.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(Voucher.class.getName()).log(Level.SEVERE, null, ex);
        }
        String query = "Select *from voucher";
        ResultSet rs = st.executeQuery(query);
           
        
               ArrayList<tempAtt> ta=new ArrayList<tempAtt>();
        
           while(rs.next())
           {
                String VoucherID = rs.getString("V_code");
                float discount = rs.getFloat("Discount");
                String Discount=String.valueOf(discount);
                ta.add(new tempAtt(VoucherID,Discount));
           }
            
            // while(tb1.getRowCount() > 0)
 
              
                return ta;
               
    }
    
    
    
}