package Code;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;


public class DBconect {
    
    public static Connection connect() {
        
        Connection conn = null;
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/itp","root","");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        
        return conn;
    }
}
