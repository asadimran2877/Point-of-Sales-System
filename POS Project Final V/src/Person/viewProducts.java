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
public class viewProducts {
     
    String barcode;
    String productID;
    String productName;
    String category;
    String quantity;
    String price;

    public viewProducts(String barcode, String productID, String productName, String category, String quantity, String price) {
        this.barcode = barcode;
        this.productID = productID;
        this.productName = productName;
        this.category = category;
        this.quantity = quantity;
        this.price = price;
    }

    public String getBarcode() {
        return barcode;
    }

    public String getProductID() {
        return productID;
    }

    public String getProductName() {
        return productName;
    }

    public String getCategory() {
        return category;
    }

    public String getQuantity() {
        return quantity;
    }

    public String getPrice() {
        return price;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public viewProducts() {
              this.barcode = null;
        this.productID = null;
        this.productName = null;
        this.category = null;
        this.quantity = null;
        this.price = null;
    }
    
    
    
    
    public ArrayList<viewProducts> showP(){
        ArrayList<viewProducts> vp= new ArrayList<viewProducts> ();
        
        try{
            //tb1.setRowCount(0);
            String url="jdbc:sqlserver://localhost:1433;databaseName=POS2;user=sa;password=sa1234";
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(viewProducts.class.getName()).log(Level.SEVERE, null, ex);
        }
            Connection con = DriverManager.getConnection(url);
            Statement st = con.createStatement();
            String query = "Select *from Item";
            ResultSet rs = st.executeQuery(query);

            while(rs.next())
            {
                String BarcodeNo = rs.getString("Barcode_NO");
                String ProductID = rs.getString("productID");
                String ProductNamee = rs.getString("ProductName");
                String Category = rs.getString("category");
                int quant = rs.getInt("Quantity");
                float pricee = rs.getFloat("Price");

                String Quantity=Integer.toString(quant);
                String Price = Float.toString(pricee);
  
               
               vp.add(new viewProducts(BarcodeNo,ProductID,ProductNamee,Category,Quantity,Price));

            }

            // while(tb1.getRowCount() > 0)

        }
        catch(HeadlessException | SQLException e)
        {
            JOptionPane.showMessageDialog(null,e);
        }
        return vp;
    }
        
    
}
