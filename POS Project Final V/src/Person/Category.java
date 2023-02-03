/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Person;
import java.awt.HeadlessException;
import java.sql.CallableStatement;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import javax.swing.JOptionPane;
/**
/**
 *
 * @author Shahnawaz Ali Raja
 */
public class Category {
    String categoryName;
    
    public Category() {
         this.categoryName =null;
    }
    public void Category(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryName() {
        return categoryName;
    }
    public int AddCategory(Category obj)
    {
        try{
         CallableStatement csmt=null;
         java.sql.Connection con=null;
            String url="jdbc:sqlserver://localhost:1433;databaseName=POS2;user=sa;password=sa1234";
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
             con = DriverManager.getConnection(url);
        
             
              csmt=con.prepareCall("{call AddCategory(?,?)}");
              csmt.setString(1, obj.getCategoryName()); 
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
     public int RemoveCategory(Category obj)
     {
          try{
         CallableStatement csmt=null;
         java.sql.Connection con=null;
            String url="jdbc:sqlserver://localhost:1433;databaseName=POS2;user=sa;password=sa1234";
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
             con = DriverManager.getConnection(url);
        
             
              csmt=con.prepareCall("{call  removeCategory(?,?)}");
              csmt.setString(1, obj.getCategoryName()); 
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
}