/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Dhananjaya
 */
public class DBConnection {
    
    private static final String dbURL = "jdbc:mysql://localhost:3306/itp";
    private static final String username = "root";
    private static final String password = "";
    
    private static Connection conn;
    
    public static Connection getDBConnection(){
    
        try {
            
            conn = DriverManager.getConnection(dbURL,username,password);
            
            if(conn != null){
            
                System.out.println("Success");
            }else{
                System.out.println("Failed");
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    
        return conn;
    }
    
    
    
    
}
