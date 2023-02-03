/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Person;

/**
 *
 * @author Acer Laptop
 */
public class tempSales {
    int profit;
    int loss;
    int amount;

    public tempSales(int profit, int loss, int amount) {
        this.profit = profit;
        this.loss = loss;
        this.amount = amount;
    }

    public tempSales() {
        profit=0;
        loss=0;
        amount=0;
    }
    
    public int getProfit() {
        return profit;
    }

    public int getLoss() {
        return loss;
    }

    public int getAmount() {
        return amount;
    }

    public void setProfit(int profit) {
        this.profit = profit;
    }

    public void setLoss(int loss) {
        this.loss = loss;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
    
    
}
