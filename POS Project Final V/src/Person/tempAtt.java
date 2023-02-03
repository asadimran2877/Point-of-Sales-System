/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Person;

/**
 *
 * @author Acer Laptop
 */
public class tempAtt {
    String VoucherID;
    String Discount;

    public tempAtt(String VoucherID, String Discount) {
        this.VoucherID = VoucherID;
        this.Discount = Discount;
    }
    
    
    public tempAtt() {
        this.VoucherID =null;
        this.Discount = null;
    }

    public String getVoucherID() {
        return VoucherID;
    }

    public String getDiscount() {
        return Discount;
    }

    public void setVoucherID(String VoucherID) {
        this.VoucherID = VoucherID;
    }

    public void setDiscount(String Discount) {
        this.Discount = Discount;
    }
    
    
    
}
