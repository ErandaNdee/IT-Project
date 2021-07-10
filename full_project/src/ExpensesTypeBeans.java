



public class ExpensesTypeBeans {
    
    private  int ExpenseTypeNo;
    private String ExpensesName;

    public ExpensesTypeBeans(int ExpenseTypeNo, String ExpensesName) {
        this.ExpenseTypeNo = ExpenseTypeNo;
        this.ExpensesName = ExpensesName;
    }

    public int getExpenseTypeNo() {
        return ExpenseTypeNo;
    }

    public void setExpenseTypeNo(int ExpenseTypeNo) {
        this.ExpenseTypeNo = ExpenseTypeNo;
    }

    public String getExpensesName() {
        return ExpensesName;
    }

    public void setExpensesName(String ExpensesName) {
        this.ExpensesName = ExpensesName;
    }
  
    
}
