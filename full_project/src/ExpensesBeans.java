


/**
 *
 * @author User
 */
public class ExpensesBeans {
    
    private int AccID;
    private String Description;
    private String ExpensesTypeNo;
    private String Date;
    private float Amount;

    public ExpensesBeans(int AccID, String Description, String ExpensesTypeNo, String Date, float Amount) {
        this.AccID = AccID;
        this.Description = Description;
        this.ExpensesTypeNo = ExpensesTypeNo;
        this.Date = Date;
        this.Amount = Amount;
    }

    public int getAccID() {
        return AccID;
    }

    public void setAccID(int AccID) {
        this.AccID = AccID;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public String getExpensesTypeNo() {
        return ExpensesTypeNo;
    }

    public void setExpensesTypeNo(String ExpensesTypeNo) {
        this.ExpensesTypeNo = ExpensesTypeNo;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String Date) {
        this.Date = Date;
    }

    public float getAmount() {
        return Amount;
    }

    public void setAmount(float Amount) {
        this.Amount = Amount;
    }
    
   
    
}
