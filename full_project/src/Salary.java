/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Shanuka Prabodha
 */
public class Salary {
    
    private String name;
    private double totalsalary;
    
    
    public Salary(String name , double totalsalary){
        this.name=name;
        this.totalsalary=totalsalary;
        
        
    
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the totalsalary
     */
    public double getTotalsalary() {
        return totalsalary;
    }

    /**
     * @param totalsalary the totalsalary to set
     */
    public void setTotalsalary(double totalsalary) {
        this.totalsalary = totalsalary;
    }
    
    
    
}
