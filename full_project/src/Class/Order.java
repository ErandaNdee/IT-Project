/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Class;

/**
 *
 * @author Dhananjaya
 */
public class Order {
    
    private int orderID;
    private String orderDate;
    private String recDate;
    private int itemCode;
    private String supName;
    private int amount;
    private String state;
    private int e_Type;
    private int qunty;
    
    
    public Order(int oID,String oDate,String rDate,int itemCode,String supName,int amount,String state,int eType,int qunty){
        
        this.orderID = oID;
        this.orderDate = oDate;
        this.recDate = rDate;
        this.itemCode = itemCode;
        this.supName = supName;
        this.amount = amount;
        this.state = state;
        this.e_Type = eType;
        this.qunty = qunty;
    }

    public int getOrderID() {
        return orderID;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public String getRecDate() {
        return recDate;
    }

    public int getItemCode() {
        return itemCode;
    }

    public String getSupName() {
        return supName;
    }

    public int getAmount() {
        return amount;
    }

    public String getState() {
        return state;
    }

    public int getE_Type() {
        return e_Type;
    }

    public int getQunty() {
        return qunty;
    }
    
   
    
    
    
}
