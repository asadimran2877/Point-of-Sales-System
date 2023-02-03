package Person;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.HeadlessException;
import java.sql.CallableStatement;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.table.DefaultTableModel;
//import PosDesign.viewProducts2;
//import javax.swing.viewProducts2;
/**
 *
 * @author Shahnawaz Ali Raja
 */
public class AddInventory   {
    String Barcode_No ;
    int productID;
    String ProductName;
    String category;
    int Quantity;
    Float Cost_Price;
    Float Sales_Price;
    String Description;
   // viewProducts2 vp=new viewProducts2();
    public void AddInventory(String Barcode_No, int productID, String ProductName, String category, int Quantity, Float Cost_Price, Float Sales_Price, String Description) {
        this.Barcode_No = Barcode_No;
        this.productID = productID;
        this.ProductName = ProductName;
        this.category = category;
        this.Quantity = Quantity;
        this.Cost_Price = Cost_Price;
        this.Sales_Price = Sales_Price;
        this.Description = Description;
    }

    public AddInventory() {
         this.Barcode_No = null;
        this.productID = 0;
        this.ProductName = null;
        this.category = null;
        this.Quantity = 0;
        this.Cost_Price =0.0f;
        this.Sales_Price = 0.0f;
        this.Description = null;
    }

    public String getBarcode_No() {
        return Barcode_No;
    }

    public void setBarcode_No(String Barcode_No) {
        this.Barcode_No = Barcode_No;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public void setProductName(String ProductName) {
        this.ProductName = ProductName;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setQuantity(int Quantity) {
        this.Quantity = Quantity;
    }

    public void setCost_Price(Float Cost_Price) {
        this.Cost_Price = Cost_Price;
    }

    public void setSales_Price(Float Sales_Price) {
        this.Sales_Price = Sales_Price;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public int getProductID() {
        return productID;
    }

    public String getProductName() {
        return ProductName;
    }

    public String getCategory() {
        return category;
    }

    public int getQuantity() {
        return Quantity;
    }

    public Float getCost_Price() {
        return Cost_Price;
    }

    public Float getSales_Price() {
        return Sales_Price;
    }

    public String getDescription() {
        return Description;
    }
     public int AddInventory(AddInventory obj)
     {
         try{
         CallableStatement csmt=null;
         java.sql.Connection con=null;
            String url="jdbc:sqlserver://localhost:1433;databaseName=POS2;user=sa;password=sa1234";
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
             con = DriverManager.getConnection(url);
        
             
              csmt=con.prepareCall("{call InventoryManagement(?,?,?,?,?,?,?,?,?)}");
              csmt.setString(1, obj.getBarcode_No());
              csmt.setInt(2, obj.getProductID());
              csmt.setString(3, obj.getProductName());
              csmt.setString(4, obj.getCategory());
              csmt.setInt(5, obj.getQuantity());
              csmt.setFloat(6,obj.getCost_Price());
              csmt.setFloat(7, obj.getSales_Price());
              csmt.setString(8, obj.getDescription());
              
              
              csmt.registerOutParameter(9, Types.INTEGER);
              csmt.executeUpdate();
              int status= csmt.getInt(9);   
               
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
     
   public int RemoveInventory(AddInventory obj)
     {
         try{
             int status=0;
         CallableStatement csmt=null;
         java.sql.Connection con=null;
            String url="jdbc:sqlserver://localhost:1433;databaseName=POS2;user=sa;password=sa1234";
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
             con = DriverManager.getConnection(url);
        
             
              csmt=con.prepareCall("{call RemoveInventoryManagement(?,?,?)}");
              csmt.setString(1, obj.getBarcode_No());
              csmt.setInt(2, obj.getProductID());
              
              
              
              csmt.registerOutParameter(3, Types.INTEGER);
              csmt.executeUpdate();
               status= csmt.getInt(3);   
               
        return status;
            }
            catch(HeadlessException | ClassNotFoundException | SQLException e)
            {
                JOptionPane.showMessageDialog(null,e);
            }
          return 2;
     }  
     
     
   
   public ResultSet ViewInventory()
     { 
         ResultSet rs=null;
         try
         {
             CallableStatement csmt=null;
             java.sql.Connection con=null;
             Statement st=null;
             String query=null;
             //ResultSet rs=null;
             String url="jdbc:sqlserver://localhost:1433;databaseName=POS2;user=sa;password=sa1234";
             Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
             con = DriverManager.getConnection(url);
            
             st=con.createStatement();
             query="Select *from Item";
             rs=st.executeQuery(query);//result set
          //   var tbl=(DefaultTableModel) vp.viewUserTable.getModel();
             
             
                while(rs.next())//resultset
              {
                  String BarcodeNo=rs.getString("Barcode_NO");
                  String ProductID=rs.getString("productID");
                  String ProductNamee=rs.getString("ProductName");
                  String Category=rs.getString("category");
                  int quant=rs.getInt("Quantity");
                  Float pricee=rs.getFloat("Price");

                  String quantity=Integer.toString(quant);
                  String Price=Float.toString(pricee);

                  String Array[]={BarcodeNo,ProductID,ProductNamee,Category,quantity,Price};
                 //  tbl.addRow(Array);
              }
         }
         catch(Exception Ex)
         {
             
         }
            
            
          return rs;
     }
   
}
   
   
   
   
   
   
