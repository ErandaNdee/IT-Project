
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.mysql.jdbc.Connection;
import com.sun.glass.events.KeyEvent;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.UIManager;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.plaf.FontUIResource;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Shanuka Prabodha
 */
public class NewJFrame extends javax.swing.JFrame {

    /**
     * Creates new form NewJFrame
     *
     *
     */
    public NewJFrame() {
        initComponents();
        fillTable();
        autoID();
        txtid.setEditable(false);
        fillcombo1();
       // fillcombo2();
       // fillcombo3();
       // fillcombo4();
        
        
        //<-- Jtable Customerzed--> 
        
        jTable1.getTableHeader().setFont(new Font("Times New Roman",Font.BOLD,18));
        
        jTable1.setRowHeight(30);
        TableColumn col0= jTable1.getColumnModel().getColumn(0);
        TableColumn col1= jTable1.getColumnModel().getColumn(1);
        TableColumn col2= jTable1.getColumnModel().getColumn(2);
        TableColumn col3= jTable1.getColumnModel().getColumn(3);
        TableColumn col4= jTable1.getColumnModel().getColumn(4);
        TableColumn col5= jTable1.getColumnModel().getColumn(5);
        TableColumn col6= jTable1.getColumnModel().getColumn(6);
        TableColumn col7= jTable1.getColumnModel().getColumn(7);
        TableColumn col8= jTable1.getColumnModel().getColumn(8);
        TableColumn col9= jTable1.getColumnModel().getColumn(9);
      
        
        
        
        col0.setPreferredWidth(60);
        col1.setPreferredWidth(150);
        col3.setPreferredWidth(2);
        col4.setPreferredWidth(30);
        col6.setPreferredWidth(220);
        

        
             
                     UIManager.put("OptionPane.messageFont", new FontUIResource(new Font(  
                    "Arial", Font.BOLD, 18)));  
    }

    
     //<-- check the salary table if the one employee salary for a given month is already inserted or not--> 
    public boolean checksalary(){
    
         try {
                Class.forName("com.mysql.jdbc.Driver");
                con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/itp", "root", "");

                String value = ename4.getSelectedItem().toString();
                int yr = year2.getYear();
                int mnth = Integer.parseInt((String) month2.getSelectedItem());
                String mon = month2.getSelectedItem().toString();
               
                insert = con.prepareStatement("select * from employees where name like '" + value + "' ");
                ResultSet rs = insert.executeQuery();

                rs.next();
                    String empid = rs.getString("id");

                insert = con.prepareStatement("select * from salary where eid = '" + empid + "' and year = '" + yr + "'and month = '" + mnth + "'");
                ResultSet rs2 = insert.executeQuery(); 
                    
                while( rs2.next()){
                            String eid = rs2.getString("eid");
                            int year = Integer.parseInt(rs2.getString("year"));
                            int month = Integer.parseInt(rs2.getString("month"));
                            double tot = Double.parseDouble(rs2.getString("total"));
                            
                       if(year==yr&month==mnth){
                           
                            return true;
                            }
   
                         }
            } catch (Exception ex) {

                JOptionPane.showMessageDialog(null, ex);
                // viewleave.setText(" No Leaves ");
            } 

    return false;
    }
    
    
     //<-- Employee ID auto generated--> 
    public void autoID() {

        try {

            Class.forName("com.mysql.jdbc.Driver");
            con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/itp", "root", "");

            String sql = "select Max(id) from employees";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);

            rs.next();
            rs.getString("Max(id)");   // get maxid in the database

            System.out.println(rs.getString("Max(id)"));
            if (rs.getString("Max(id)") == null) {

                txtid.setText("Emp001");
            } else {
                //  Long ids= Long.parseLong(rs.getString("Max(id)").substring);
                Long id = Long.parseLong(rs.getString("Max(id)").substring(3, rs.getString("Max(id)").length()));   //divide the employee id to interger part and String part
                id++;

                txtid.setText("Emp" + String.format("%03d", id)); //Set next Employee id to the jfield

            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, e);
        }

    }


    String photopath ="";

    public ImageIcon resetImageSize(String photopath, byte[] photo) {

        ImageIcon myphoto = null;
        if (photopath != null) {
            myphoto = new ImageIcon(photopath);

        } else {
            myphoto = new ImageIcon(photo);
        }

        Image img = myphoto.getImage();
        Image img1 = img.getScaledInstance(lbl_photo.getWidth(), lbl_photo.getHeight(), Image.SCALE_SMOOTH);

        ImageIcon ph = new ImageIcon(img1);
        return ph;

    }
    
    //assign salary data to the arraylist
    
    public ArrayList<Salary> retrivesalary(){
        
      ArrayList<Salary> sal = null;
     sal = new ArrayList<Salary>();

        try {

            Class.forName("com.mysql.jdbc.Driver");
            con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/itp", "root", "");

            
               String value = ename4.getSelectedItem().toString();
         //   System.out.println(value);
            int yr = year2.getYear();
            //   System.out.println(yr);

            int mnth = Integer.parseInt((String) month2.getSelectedItem());
            String mon = month.getSelectedItem().toString();
            
            String sql = "select * from salary s , employees e  where s.year like '" + yr +"' and s.month like '"+mnth+"' and s.eid=e.id ";
            
           //  insert = con.prepareStatement("select * from salary s , employees e  where s.year like '" + yr +"' and s.month like '"+mnth+"' and s.eid=e.id ");
         
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            Salary salary;

            while (rs.next()) {

                salary = new Salary(rs.getString("e.name"), Double.parseDouble(rs.getString("s.total")));  //pass values to the salary constructor

                sal.add(salary);        // add salary object to the array list

            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, e);
        }

        return sal;

    
    }

    //assign employee data to the array list
    public ArrayList<Employee> retriveData() {

        ArrayList<Employee> al = null;

        al = new ArrayList<Employee>();

        try {

            Class.forName("com.mysql.jdbc.Driver");
            con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/itp", "root", "");

            String sql = "select * from employees";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            Employee employee;

            while (rs.next()) {

                employee = new Employee(rs.getString(1), rs.getString("name"),
                        rs.getString(3), Integer.parseInt(rs.getString(4)), rs.getString(5),
                        rs.getString(6), rs.getString(7), rs.getString(8),
                        rs.getString(9), Double.parseDouble(rs.getString(10)), rs.getBytes(11),rs.getString(12));

                al.add(employee);

            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, e);
        }

        return al;

    }

    
    //<--get data into the Jtable-->
    public void fillTable() {

        try {

            int c;
            Class.forName("com.mysql.jdbc.Driver");
            con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/itp", "root", "");
            insert = con.prepareStatement("select * from employees");

            ResultSet rs = insert.executeQuery();
            ResultSetMetaData res = rs.getMetaData();
            c = res.getColumnCount();

            DefaultTableModel df = (DefaultTableModel) jTable1.getModel();
            df.setRowCount(0);

            while (rs.next()) {
                Vector v2 = new Vector();

                for (int i = 1; i <= c; i++) {

                    v2.add(rs.getString("id"));
                    v2.add(rs.getString("name"));
                    v2.add(rs.getString("nic"));
                    v2.add(rs.getInt("age"));
                    v2.add(rs.getString("gender"));
                    v2.add(rs.getString("phone"));
                    v2.add(rs.getString("email"));

                    v2.add(rs.getString("edate"));
                    v2.add(rs.getString("designation"));

                    v2.add(rs.getDouble("sal"));

                }

                df.addRow(v2);

            }

        } catch (ClassNotFoundException ex) {

        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(this, ex);
        }

    }

    //update combo box according to the database
    public DefaultComboBoxModel fillcombo1() {

        DefaultComboBoxModel dm = new DefaultComboBoxModel();

        try {

            Class.forName("com.mysql.jdbc.Driver");
            con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/itp", "root", "");
            insert = con.prepareStatement("select * from employees");

            ResultSet rs = insert.executeQuery();

            while (rs.next()) {

                String name = rs.getString("name");
                // ename.addItem(name);
                dm.addElement(name);

            }

            ename.setModel(dm);  //assign value to the combo box
            ename1.setModel(dm);
            ename3.setModel(dm);
             ename4.setModel(dm);
            

        } catch (ClassNotFoundException ex) {

        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(this, ex);
        }

        return dm;
    }
/*
    private DefaultComboBoxModel fillcombo2() {

        DefaultComboBoxModel dm = new DefaultComboBoxModel();
        try {

            Class.forName("com.mysql.jdbc.Driver");
            con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/itp", "root", "");
            insert = con.prepareStatement("select * from employees");

            ResultSet rs = insert.executeQuery();

            while (rs.next()) {

                String name = rs.getString("name");
                // ename1.addItem(name);
                dm.addElement(name);

            }

            ename1.setModel(dm);

        } catch (ClassNotFoundException ex) {

        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(this, ex);
        }
        return dm;
    }

    private DefaultComboBoxModel fillcombo3() {

        DefaultComboBoxModel dm = new DefaultComboBoxModel();
        try {

            Class.forName("com.mysql.jdbc.Driver");
            con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/itp", "root", "");
            insert = con.prepareStatement("select * from employees");

            ResultSet rs = insert.executeQuery();

            while (rs.next()) {

                String name = rs.getString("name");
                // ename1.addItem(name);
                dm.addElement(name);

            }

            ename3.setModel(dm);

        } catch (ClassNotFoundException ex) {

        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(this, ex);
        }
        return dm;
    }

    private DefaultComboBoxModel fillcombo4() {

        DefaultComboBoxModel dm = new DefaultComboBoxModel();
        try {

            Class.forName("com.mysql.jdbc.Driver");
            con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/itp", "root", "");
            insert = con.prepareStatement("select * from employees");

            ResultSet rs = insert.executeQuery();

            while (rs.next()) {

                String name = rs.getString("name");
                // ename1.addItem(name);
                dm.addElement(name);

            }

            ename4.setModel(dm);

        } catch (ClassNotFoundException ex) {

        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(this, ex);
        }
        return dm;
    }*/

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtgender = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        b1 = new javax.swing.JButton();
        b2 = new javax.swing.JButton();
        b3 = new javax.swing.JButton();
        b4 = new javax.swing.JButton();
        b5 = new javax.swing.JButton();
        b6 = new javax.swing.JButton();
        b7 = new javax.swing.JButton();
        b8 = new javax.swing.JButton();
        b9 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        addleave = new javax.swing.JTabbedPane();
        jPanel17 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        lbl_photo = new javax.swing.JLabel();
        jToggleButton1 = new javax.swing.JToggleButton();
        txtid = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        Name = new javax.swing.JLabel();
        txtname = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtnic = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtage = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtphone = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtemail = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txtdes = new javax.swing.JTextField();
        txtsal = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txtdate = new com.toedter.calendar.JDateChooser();
        male = new javax.swing.JRadioButton();
        female = new javax.swing.JRadioButton();
        j1 = new javax.swing.JLabel();
        j2 = new javax.swing.JLabel();
        j3 = new javax.swing.JLabel();
        pt = new javax.swing.JLabel();
        jb = new javax.swing.JLabel();
        jd = new javax.swing.JLabel();
        jn = new javax.swing.JLabel();
        jnic = new javax.swing.JLabel();
        ipath = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jToggleButton2 = new javax.swing.JToggleButton();
        edit = new javax.swing.JToggleButton();
        delete = new javax.swing.JToggleButton();
        clear = new javax.swing.JToggleButton();
        demo = new javax.swing.JToggleButton();
        jPanel18 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        leavedate = new com.toedter.calendar.JDateChooser();
        jLabel16 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        ename = new javax.swing.JComboBox<>();
        jPanel4 = new javax.swing.JPanel();
        ename1 = new javax.swing.JComboBox<>();
        jLabel15 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        year = new com.toedter.calendar.JYearChooser();
        jLabel18 = new javax.swing.JLabel();
        check = new javax.swing.JButton();
        jLabel19 = new javax.swing.JLabel();
        viewleave = new javax.swing.JLabel();
        month = new javax.swing.JComboBox<>();
        jLabel20 = new javax.swing.JLabel();
        jPanel19 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        get = new javax.swing.JButton();
        ottime = new javax.swing.JTextField();
        ename3 = new javax.swing.JComboBox<>();
        otdate = new com.toedter.calendar.JDateChooser();
        wr1 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jPanel16 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        year2 = new com.toedter.calendar.JYearChooser();
        month2 = new javax.swing.JComboBox<>();
        ename4 = new javax.swing.JComboBox<>();
        jLabel35 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        basicsalary = new javax.swing.JTextField();
        epf = new javax.swing.JTextField();
        bonus = new javax.swing.JTextField();
        etf = new javax.swing.JTextField();
        totalleaves = new javax.swing.JTextField();
        leaverate = new javax.swing.JTextField();
        othour = new javax.swing.JTextField();
        otrate = new javax.swing.JTextField();
        calculate = new javax.swing.JButton();
        jLabel33 = new javax.swing.JLabel();
        total = new javax.swing.JTextField();
        warning = new javax.swing.JLabel();
        save = new javax.swing.JButton();
        j5 = new javax.swing.JLabel();
        j6 = new javax.swing.JLabel();
        j7 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        hide = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(0, 102, 102));
        jPanel2.setAutoscrolls(true);

        b1.setBackground(new java.awt.Color(0, 102, 102));
        b1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        b1.setForeground(new java.awt.Color(255, 255, 255));
        b1.setText("Employees");
        b1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                b1MouseClicked(evt);
            }
        });
        b1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b1ActionPerformed(evt);
            }
        });

        b2.setBackground(new java.awt.Color(0, 102, 102));
        b2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        b2.setForeground(new java.awt.Color(255, 255, 255));
        b2.setText("Hall Reservation");
        b2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b2ActionPerformed(evt);
            }
        });

        b3.setBackground(new java.awt.Color(0, 102, 102));
        b3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        b3.setForeground(new java.awt.Color(255, 255, 255));
        b3.setText("Room Reservation");
        b3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b3ActionPerformed(evt);
            }
        });

        b4.setBackground(new java.awt.Color(0, 102, 102));
        b4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        b4.setForeground(new java.awt.Color(255, 255, 255));
        b4.setText("Menus");
        b4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b4ActionPerformed(evt);
            }
        });

        b5.setBackground(new java.awt.Color(0, 102, 102));
        b5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        b5.setForeground(new java.awt.Color(255, 255, 255));
        b5.setText("Payment");
        b5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b5ActionPerformed(evt);
            }
        });

        b6.setBackground(new java.awt.Color(0, 102, 102));
        b6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        b6.setForeground(new java.awt.Color(255, 255, 255));
        b6.setText("Inventory");
        b6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b6ActionPerformed(evt);
            }
        });

        b7.setBackground(new java.awt.Color(0, 102, 102));
        b7.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        b7.setForeground(new java.awt.Color(255, 255, 255));
        b7.setText("Orders");
        b7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b7ActionPerformed(evt);
            }
        });

        b8.setBackground(new java.awt.Color(0, 102, 102));
        b8.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        b8.setForeground(new java.awt.Color(255, 255, 255));
        b8.setText("Travel Package");
        b8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b8ActionPerformed(evt);
            }
        });

        b9.setBackground(new java.awt.Color(0, 102, 102));
        b9.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        b9.setForeground(new java.awt.Color(255, 255, 255));
        b9.setText("Expenses");
        b9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b9ActionPerformed(evt);
            }
        });

        jButton4.setBackground(new java.awt.Color(0, 102, 102));
        jButton4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setText("Go Home");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE)
                    .addComponent(b6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(b2, javax.swing.GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE)
                    .addComponent(b3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(b4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(b7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(b5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(b8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(b1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(b9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(26, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(238, 238, 238)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(b6, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(b2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(b3, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(b4, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(b7, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(b5, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(b8, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(b1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(b9, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(205, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 220, 1090));

        addleave.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);
        addleave.setFont(new java.awt.Font("Tempus Sans ITC", 1, 15)); // NOI18N
        addleave.setRequestFocusEnabled(false);
        addleave.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                addleaveStateChanged(evt);
            }
        });
        addleave.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addleaveMouseClicked(evt);
            }
        });

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Registration", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 24))); // NOI18N

        jPanel8.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbl_photo, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(lbl_photo, javax.swing.GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        jToggleButton1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jToggleButton1.setText("Upload Photo");
        jToggleButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton1ActionPerformed(evt);
            }
        });

        txtid.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        txtid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtidActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel5.setText("ID");

        Name.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        Name.setText("Name");

        txtname.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        txtname.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtnameKeyReleased(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel7.setText("NIC");

        txtnic.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        txtnic.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtnicActionPerformed(evt);
            }
        });
        txtnic.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtnicKeyReleased(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel8.setText("Age");

        txtage.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        txtage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtageActionPerformed(evt);
            }
        });
        txtage.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtageKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtageKeyReleased(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel9.setText("Gender");

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel10.setText("Phone");

        txtphone.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        txtphone.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtphoneKeyReleased(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel11.setText("Email");

        txtemail.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        txtemail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtemailActionPerformed(evt);
            }
        });
        txtemail.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtemailKeyReleased(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel12.setText("Entrance Date");

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel13.setText("Designation");

        txtdes.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        txtdes.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtdesKeyReleased(evt);
            }
        });

        txtsal.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        txtsal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtsalKeyReleased(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel14.setText("Basic Salary");

        txtdate.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        txtgender.add(male);
        male.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        male.setText("Male");
        male.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                maleActionPerformed(evt);
            }
        });

        txtgender.add(female);
        female.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        female.setText("Female");
        female.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                femaleActionPerformed(evt);
            }
        });

        j1.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        j1.setForeground(new java.awt.Color(204, 0, 0));

        j2.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        j2.setForeground(new java.awt.Color(204, 0, 0));

        j3.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        j3.setForeground(new java.awt.Color(204, 0, 0));

        jb.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jb.setForeground(new java.awt.Color(204, 0, 0));

        jd.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jd.setForeground(new java.awt.Color(204, 0, 0));

        jn.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jn.setForeground(new java.awt.Color(204, 0, 0));

        jnic.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jnic.setForeground(new java.awt.Color(204, 0, 0));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(36, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(27, 27, 27)
                                        .addComponent(txtnic, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(Name, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(27, 27, 27)
                                        .addComponent(txtname, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(27, 27, 27)
                                        .addComponent(txtid, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(27, 27, 27)
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txtphone, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addComponent(male)
                                                .addGap(27, 27, 27)
                                                .addComponent(female))
                                            .addComponent(txtemail, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(ipath, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jnic, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(30, 30, 30)
                                        .addComponent(txtage, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(j3, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGap(27, 27, 27))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(18, 18, 18)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtdes, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtdate, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtsal, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(24, 24, 24))
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addComponent(j1, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(44, 44, 44))
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                    .addGap(27, 27, 27)
                                    .addComponent(pt))
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jToggleButton1)))
                            .addGap(7, 7, 7)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jd, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jb, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(37, 37, 37))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(j2, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35))))
            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                .addGap(187, 187, 187)
                .addComponent(jn, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addComponent(pt)
                        .addGap(36, 36, 36)
                        .addComponent(jToggleButton1))
                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addComponent(ipath)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(txtid, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Name, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtname, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addComponent(jn, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtnic, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addComponent(jnic, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtage, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(j3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(male)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(female))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtphone, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(j2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtemail, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(j1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtdate, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtdes, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addComponent(jd, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtsal, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jb, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        jTable1.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Name", "NIC", "Age", "Gender", "Phone", "Email", "Entrance Date", "Designation", "Basic Salary"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jToggleButton2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jToggleButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/add.png"))); // NOI18N
        jToggleButton2.setText("Add");
        jToggleButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton2ActionPerformed(evt);
            }
        });

        edit.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        edit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/edit.png"))); // NOI18N
        edit.setText("Edit");
        edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editActionPerformed(evt);
            }
        });

        delete.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        delete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/del.png"))); // NOI18N
        delete.setText("Delete");
        delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteActionPerformed(evt);
            }
        });

        clear.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        clear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/clear.png"))); // NOI18N
        clear.setText("Clear");
        clear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearActionPerformed(evt);
            }
        });

        demo.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        demo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/demo_1.png"))); // NOI18N
        demo.setText("Demo");
        demo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                demoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel17Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addComponent(jToggleButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(edit, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(delete, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(clear, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(demo, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1163, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(79, Short.MAX_VALUE))
        );

        jPanel17Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {delete, edit, jToggleButton2});

        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addGap(132, 132, 132)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 546, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jToggleButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(delete)
                            .addComponent(edit, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(clear, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(demo, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 99, Short.MAX_VALUE))
        );

        jPanel17Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {delete, edit, jToggleButton2});

        addleave.addTab("ADD NEW EMPLOEE", jPanel17);

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setText("Employee ");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel6.setText("Year");

        leavedate.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N

        jLabel16.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        jLabel16.setText("Take A Leave");

        jButton5.setBackground(new java.awt.Color(51, 102, 255));
        jButton5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton5.setForeground(new java.awt.Color(255, 255, 255));
        jButton5.setText("ADD");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        ename.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        ename.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                enameItemStateChanged(evt);
            }
        });
        ename.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                enameMouseEntered(evt);
            }
        });
        ename.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                enameActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(62, 62, 62)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(61, 61, 61)
                        .addComponent(ename, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(143, 143, 143)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(leavedate, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(126, 126, 126)
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(504, 504, 504)
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(153, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel9Layout.createSequentialGroup()
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(63, 63, 63)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(leavedate, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ename, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(166, Short.MAX_VALUE))
        );

        jPanel4.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        ename1.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        ename1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ename1ActionPerformed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel15.setText("Employee ");

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel17.setText("Month :");

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel18.setText("Year :");

        check.setBackground(new java.awt.Color(255, 0, 0));
        check.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        check.setForeground(new java.awt.Color(255, 255, 255));
        check.setText("Check leaves");
        check.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkActionPerformed(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel19.setText("**  you have only 21 leaves per year");

        viewleave.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        viewleave.setForeground(new java.awt.Color(204, 0, 0));
        viewleave.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        month.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        month.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" }));

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel20.setText("Check Leaves");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(80, 80, 80)
                        .addComponent(ename1, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(45, 45, 45)
                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(year, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(91, 91, 91)
                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(month, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(60, 60, 60)
                        .addComponent(check))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(71, 71, 71)
                        .addComponent(viewleave, javax.swing.GroupLayout.PREFERRED_SIZE, 1262, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(543, 543, 543)
                        .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(469, 469, 469)
                        .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 366, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel4Layout.createSequentialGroup()
                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(year, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel4Layout.createSequentialGroup()
                            .addGap(29, 29, 29)
                            .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(76, 76, 76)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(ename1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(month, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(check, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(58, 58, 58)
                .addComponent(viewleave, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addGap(99, 99, 99)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(265, Short.MAX_VALUE))
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 352, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(143, Short.MAX_VALUE))
        );

        addleave.addTab("ADD LEAVE", jPanel18);

        jPanel5.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel21.setText("Date :");

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel22.setText("Employee : ");

        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel24.setText("OT Time :");

        get.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        get.setText("APPLY");
        get.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                getActionPerformed(evt);
            }
        });

        ottime.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        ottime.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ottimeMouseClicked(evt);
            }
        });
        ottime.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ottimeActionPerformed(evt);
            }
        });
        ottime.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                ottimeKeyReleased(evt);
            }
        });

        ename3.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N

        otdate.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N

        wr1.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        wr1.setForeground(new java.awt.Color(204, 0, 0));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(72, 72, 72)
                        .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ename3, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel21)
                        .addGap(18, 18, 18)
                        .addComponent(otdate, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(49, 49, 49)
                        .addComponent(jLabel24)
                        .addGap(34, 34, 34)
                        .addComponent(ottime, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(wr1, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(524, 524, 524)
                        .addComponent(get, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(122, 122, 122)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel21)
                            .addComponent(jLabel24)
                            .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ottime, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ename3, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(wr1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(otdate, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(66, 66, 66)
                .addComponent(get, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(142, Short.MAX_VALUE))
        );

        jLabel36.setBackground(new java.awt.Color(0, 102, 102));
        jLabel36.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel36.setText("ADD OT TIME");
        jLabel36.setAutoscrolls(true);

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addGap(655, 655, 655)
                        .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addGap(153, 153, 153)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(242, Short.MAX_VALUE))
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(383, Short.MAX_VALUE))
        );

        addleave.addTab("ADD OT TIME", jPanel19);

        jPanel6.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel1.setText("Employee Name :");

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel4.setText("Year :");

        jLabel23.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel23.setText("Month :");

        jButton1.setBackground(new java.awt.Color(51, 0, 102));
        jButton1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Find");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        month2.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        month2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" }));

        ename4.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        ename4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " " }));

        jLabel35.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        jLabel35.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel35.setText("Choose a Month for the Calculate the salary");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jLabel1)
                .addGap(30, 30, 30)
                .addComponent(ename4, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(67, 67, 67)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(year2, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(96, 96, 96)
                .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42)
                .addComponent(month2, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 138, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44))
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(306, 306, 306)
                .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 572, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(year2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(month2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(ename4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(36, 36, 36))
        );

        jPanel7.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel25.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel25.setText("Basic Salary :");

        jLabel26.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel26.setText("Bonus :");

        jLabel27.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel27.setText("EPF :");

        jLabel28.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel28.setText("ETF :");

        jLabel29.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel29.setText("Leaves taken (year): ");

        jLabel30.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel30.setText("Leave Rate :");

        jLabel31.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel31.setText("Total OT hour :");

        jLabel32.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel32.setText("OT Rate :");

        basicsalary.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        basicsalary.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        epf.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        epf.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        epf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                epfActionPerformed(evt);
            }
        });

        bonus.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        bonus.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        bonus.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                bonusKeyReleased(evt);
            }
        });

        etf.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        etf.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        totalleaves.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        totalleaves.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        leaverate.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        leaverate.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        leaverate.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                leaverateKeyReleased(evt);
            }
        });

        othour.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        othour.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        otrate.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        otrate.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        otrate.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                otrateKeyReleased(evt);
            }
        });

        calculate.setBackground(new java.awt.Color(204, 0, 0));
        calculate.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        calculate.setForeground(new java.awt.Color(255, 255, 255));
        calculate.setText("Calculate");
        calculate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                calculateActionPerformed(evt);
            }
        });

        jLabel33.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel33.setText("Total Amount :");

        total.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        total.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        warning.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        warning.setForeground(new java.awt.Color(255, 0, 0));

        save.setBackground(new java.awt.Color(153, 0, 153));
        save.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        save.setForeground(new java.awt.Color(255, 255, 255));
        save.setText("save");
        save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveActionPerformed(evt);
            }
        });

        j5.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        j5.setForeground(new java.awt.Color(204, 0, 0));

        j6.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        j6.setForeground(new java.awt.Color(204, 0, 0));

        j7.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        j7.setForeground(new java.awt.Color(204, 0, 0));

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(248, 248, 248)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(total, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel7Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(save, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel7Layout.createSequentialGroup()
                                        .addGap(21, 21, 21)
                                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(j7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(j6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                                .addGap(44, 44, 44))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(j5, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 92, Short.MAX_VALUE))))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(74, 74, 74)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(calculate, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(otrate)
                                    .addComponent(othour)
                                    .addComponent(leaverate, javax.swing.GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)
                                    .addComponent(epf)
                                    .addComponent(bonus)
                                    .addComponent(etf)
                                    .addComponent(totalleaves)
                                    .addComponent(basicsalary))
                                .addGap(18, 18, 18)
                                .addComponent(warning, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(21, 21, 21))))))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(basicsalary, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE))
                .addGap(27, 27, 27)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bonus, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                    .addComponent(j5))
                .addGap(20, 20, 20)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(epf, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(etf, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(totalleaves, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(leaverate, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(j6))
                        .addGap(20, 20, 20)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(othour, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(otrate, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(j7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                        .addComponent(calculate, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(total, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(save, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(warning, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jButton2.setBackground(new java.awt.Color(0, 102, 102));
        jButton2.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("PaySheet");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(0, 102, 102));
        jButton3.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("Monthly Report");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGap(163, 163, 163)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(55, 55, 55)
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(259, Short.MAX_VALUE))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGap(180, 180, 180)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(113, Short.MAX_VALUE))
        );

        addleave.addTab("SALARY GENERATOR", jPanel16);

        jPanel1.add(addleave, new org.netbeans.lib.awtextra.AbsoluteConstraints(225, 170, 1730, 920));

        hide.setBackground(new java.awt.Color(51, 0, 102));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 75)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("HOTEL ARONWAY");

        javax.swing.GroupLayout hideLayout = new javax.swing.GroupLayout(hide);
        hide.setLayout(hideLayout);
        hideLayout.setHorizontalGroup(
            hideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(hideLayout.createSequentialGroup()
                .addGap(462, 462, 462)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 788, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(480, Short.MAX_VALUE))
        );
        hideLayout.setVerticalGroup(
            hideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(hideLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel1.add(hide, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 0, 1730, 170));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void b1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b1ActionPerformed
        // TODO add your handling code here:
         dispose();
        NewJFrame i = new NewJFrame();
        i.setVisible(true);

    }//GEN-LAST:event_b1ActionPerformed

    private void b2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b2ActionPerformed
        // TODO add your handling code here:
          dispose();
        HallReservation i = new HallReservation();
        i.setVisible(true);

    }//GEN-LAST:event_b2ActionPerformed

    private void b3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b3ActionPerformed
        dispose();
        Room i = new Room();
        i.setVisible(true);
    }//GEN-LAST:event_b3ActionPerformed

    private void b4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b4ActionPerformed
        // TODO add your handling code here:
        dispose();
        AllMenu i = new AllMenu();
        i.setVisible(true);
    }//GEN-LAST:event_b4ActionPerformed

    Connection con;

    private void jToggleButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton2ActionPerformed
        // TODO add your handling code here:

        if (male.isSelected()) {

            gender = "male";
        } else if (female.isSelected()) {

            gender = "female";
        } else {
            gender = "";
        }

        try {
            if (ipath.getText().toString().equals("")) {
                JOptionPane.showMessageDialog(null, "Select the photo ");
            }
            if (txtname.getText().toString().equals("")) {
                JOptionPane.showMessageDialog(null, "Enter the name ");
            }
            if (txtnic.getText().toString().equals("")) {
                JOptionPane.showMessageDialog(null, "Enter the NIC ");
            }
            if (txtage.getText().toString().equals("")) {
                JOptionPane.showMessageDialog(null, "Enter the Age ");
            }
            if (gender.equals("")) {
                JOptionPane.showMessageDialog(null, "Select the Gender ");
            }
            if (txtphone.getText().toString().equals("")) {
                JOptionPane.showMessageDialog(null, "Enter the Phone Number ");
            }
            if (txtemail.getText().toString().equals("")) {
                JOptionPane.showMessageDialog(null, "Enter the Email ");
            }
            if (txtdate.getDate() == null) {
                JOptionPane.showMessageDialog(null, "Choose the Date ");
            }
            if (txtdes.getText().toString().equals("")) {
                JOptionPane.showMessageDialog(null, "Enter the Designation ");
            }
            if (txtsal.getText().toString().equals("")) {
                JOptionPane.showMessageDialog(null, "Enter the Basic Salary ");
            }
            if (jn.getText() == "Name is incorrect") {
                JOptionPane.showMessageDialog(null, "Please enter Name in correct input ");
            }
            if (jnic.getText() == "NIC is incorrect") {
                JOptionPane.showMessageDialog(null, "Please enter NIC in correct input ");
            }
            if (j3.getText() == "Age is incorrect") {
                JOptionPane.showMessageDialog(null, "Please enter age in correct input ");
            }

            if (j1.getText() == "Email is incorrect") {
                JOptionPane.showMessageDialog(null, "Please enter Email in correct input ");
            }

            if (j2.getText() == "phone number is incorrect") {
                JOptionPane.showMessageDialog(null, "Please enter Phone Number in correct input ");
            }
            if (jd.getText() == "Designation is incorrect") {
                JOptionPane.showMessageDialog(null, "Please enter Designation in correct input ");
            }
            if (jb.getText() == "Basic Salary is incorrect") {
                JOptionPane.showMessageDialog(null, "Please enter Basic Salary in correct input ");
            } else {
                Class.forName("com.mysql.jdbc.Driver");
                con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/itp", "root", "");

                String sql = "insert into employees" + "(id,name,nic,age,gender,phone,email,edate,designation,sal,photo,path)" + "values(?,?,?,?,?,?,?,?,?,?,?,?)";

                SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
                String addDate = dateformat.format(txtdate.getDate());

               // InputStream is = new FileInputStream(new File(photopath));
                InputStream is = new FileInputStream(new File(ipath.getText()));

                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, txtid.getText());
                ps.setString(2, txtname.getText());
                ps.setString(3, txtnic.getText());
                ps.setInt(4, Integer.parseInt(txtage.getText()));
                ps.setString(5, gender);
                ps.setString(6, txtphone.getText());
                ps.setString(7, txtemail.getText());
                ps.setString(8, addDate);
                ps.setString(9, txtdes.getText());
                ps.setDouble(10, Double.parseDouble(txtsal.getText()));
                ps.setBlob(11, is);
                ps.setString(12, ipath.getText());

                int res = ps.executeUpdate();

                if (res >= 1) {

                    JOptionPane.showMessageDialog(null, "Inserted ");
                } else {

                    JOptionPane.showMessageDialog(null, "not inserted ");
                }

              //  fillcombo1();
                ename.setSelectedItem(txtname.getText());

               // fillcombo2();
                ename1.setSelectedItem(txtname.getText());

                txtid.setText("");
                txtname.setText("");
                txtnic.setText("");
                txtage.setText("");

                txtphone.setText("");
                txtemail.setText("");
                txtdes.setText("");
                txtsal.setText("");

                txtgender.clearSelection();
                lbl_photo.setIcon(null);
                txtdate.setCalendar(null);
                ipath.setText("");

                fillTable();
                autoID();
                fillcombo1();
              //  fillcombo2();
               // fillcombo3();
              //  fillcombo4();

            }
        } catch (ClassNotFoundException ex) {

        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(this, ex);
        } catch (FileNotFoundException ex) {

            JOptionPane.showMessageDialog(this, ex);
        }


    }//GEN-LAST:event_jToggleButton2ActionPerformed

    private void jToggleButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton1ActionPerformed
        // TODO add your handling code here:

        String currentDirectorypath = "C:\\Users\\Public\\Pictures\\";
        JFileChooser file = new JFileChooser(currentDirectorypath);
        //    file.setCurrentDirectory(new File(System.getProperty("user.home")));

        FileNameExtensionFilter filter = new FileNameExtensionFilter("*.image", "jpg", "png");
        file.addChoosableFileFilter(filter);

        int result = file.showSaveDialog(null);

        if (result == JFileChooser.APPROVE_OPTION) {

            File selectedFile = file.getSelectedFile();
            String path = selectedFile.getAbsolutePath();
            lbl_photo.setIcon(resetImageSize(path, null));
            this.photopath = path;
            //System.out.println(path);
            String imagefile = selectedFile.getName();
            ipath.setText(path);
           
           
           

        } else {

            System.out.println("no file selected");

        }
    }//GEN-LAST:event_jToggleButton1ActionPerformed


    private void deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteActionPerformed
        // TODO add your handling code here:

        if (txtid.getText().equals("")) {

            JOptionPane.showMessageDialog(null, "selecttion is compolsary");

        } else {

            int opt = JOptionPane.showConfirmDialog(null, "Are You sure to Delete", "Delete", JOptionPane.YES_NO_OPTION);

            if (opt == 0) {
                
                if (male.isSelected()) {

                    gender = "male";
                } else if (female.isSelected()) {

                    gender = "female";
                } else {
                    gender = "";
                }

                
                
                try {

                    Class.forName("com.mysql.jdbc.Driver");
                    con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/itp", "root", "");

                                String sql2 = "insert into deletedemployee" + "(id,name,nic,age,gender,phone,email,edate,designation,sal,photo,path)" + "values(?,?,?,?,?,?,?,?,?,?,?,?)";

                SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
                String addDate = dateformat.format(txtdate.getDate());

               // InputStream is = new FileInputStream(new File(photopath));
                InputStream is = new FileInputStream(new File(ipath.getText()));

                PreparedStatement ps1 = con.prepareStatement(sql2);
                ps1.setString(1, txtid.getText());
                ps1.setString(2, txtname.getText());
                ps1.setString(3, txtnic.getText());
                ps1.setInt(4, Integer.parseInt(txtage.getText()));
                ps1.setString(5, gender);
                ps1.setString(6, txtphone.getText());
                ps1.setString(7, txtemail.getText());
                ps1.setString(8, addDate);
                ps1.setString(9, txtdes.getText());
                ps1.setDouble(10, Double.parseDouble(txtsal.getText()));
                ps1.setBlob(11, is);
                ps1.setString(12, ipath.getText());

                int res1 = ps1.executeUpdate();
                    
                    String sql = "delete from employees where id=?";
                    PreparedStatement ps = con.prepareStatement(sql);

                    ps.setString(1, txtid.getText());
                    int res = ps.executeUpdate();

                    if (res >= 1) {

                        JOptionPane.showMessageDialog(null, "Deleted Successfully ");
                        fillTable();
                    } else {

                        JOptionPane.showMessageDialog(null, "Not deleted ");
                    }

                } catch (Exception e) {

                    JOptionPane.showMessageDialog(null, e);
                }

                txtid.setText("");
                txtname.setText("");
                txtnic.setText("");
                txtage.setText("");

                txtphone.setText("");
                txtemail.setText("");
                txtdes.setText("");
                txtsal.setText("");

                txtgender.clearSelection();
                lbl_photo.setIcon(null);
                txtdate.setCalendar(null);
                ipath.setText("");

                fillTable();
                autoID();
                jToggleButton2.setEnabled(true);
                fillcombo1();
              //  fillcombo2();
               // fillcombo3();
               // fillcombo4();

            }
        }


    }//GEN-LAST:event_deleteActionPerformed

    private void editActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editActionPerformed
        // TODO add your handling code here:

        if (male.isSelected()) {

            gender = "male";
        } else if (female.isSelected()) {

            gender = "female";
        } else {
            gender = "";
        }

        try {

            if (ipath.getText()==null) {
                JOptionPane.showMessageDialog(null, "Select the photo ");
            }
            else if (txtname.getText().toString().equals("")) {
                JOptionPane.showMessageDialog(null, "Enter the name ");
            }
             else if  (txtnic.getText().toString().equals("")) {
                JOptionPane.showMessageDialog(null, "Enter the NIC ");
            }
             else if  (txtage.getText().toString().equals("")) {
                JOptionPane.showMessageDialog(null, "Enter the Age ");
            }
             else if  (gender.equals("")) {
                JOptionPane.showMessageDialog(null, "Select the Gender ");
            }
             else if  (txtphone.getText().toString().equals("")) {
                JOptionPane.showMessageDialog(null, "Enter the Phone Number ");
            }
             else if  (txtemail.getText().toString().equals("")) {
                JOptionPane.showMessageDialog(null, "Enter the Email ");
            }
             else if  (txtdate.equals("")) {
                JOptionPane.showMessageDialog(null, "Choose the Date ");
            }
             else if  (txtdes.getText().toString().equals("")) {
                JOptionPane.showMessageDialog(null, "Enter the Designation ");
            }
             else if  (txtsal.getText().toString().equals("")) {
                JOptionPane.showMessageDialog(null, "Enter the Basic Salary ");
            }
             else if  (jn.getText() == "Name is incorrect") {
                JOptionPane.showMessageDialog(null, "Please enter Name in correct input ");
            }
             else if  (jnic.getText() == "NIC is incorrect") {
                JOptionPane.showMessageDialog(null, "Please enter NIC in correct input ");
            }
             else if  (j3.getText() == "Age is incorrect") {
                JOptionPane.showMessageDialog(null, "Please enter age in correct input ");
            }

             else if  (j1.getText() == "Email is incorrect") {
                JOptionPane.showMessageDialog(null, "Please enter Email in correct input ");
            }

             else if  (j2.getText() == "phone number is incorrect") {
                JOptionPane.showMessageDialog(null, "Please enter Phone Number in correct input ");
            }
             else if  (jd.getText() == "Designation is incorrect") {
                JOptionPane.showMessageDialog(null, "Please enter Designation in correct input ");
            }
             else if  (jb.getText() == "Basic Salary is incorrect") {
                JOptionPane.showMessageDialog(null, "Please enter Basic Salary in correct input ");
            } else {
                Class.forName("com.mysql.jdbc.Driver");
                con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/itp", "root", "");

                String sql = "update employees set name=?, nic=? , age=? , gender=? , phone=? , email=? , edate=? , designation=? , sal=? , photo=? ,path=? where id=?";

                // ""+"(id,name,nic,age,gender,phone,email,edate,designation,sal,photo)"+"values(?,?,?,?,?,?,?,?,?,?,?)";
                SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
                String addDate = dateformat.format(txtdate.getDate());

                InputStream is = new FileInputStream(new File(ipath.getText()));

                PreparedStatement ps = con.prepareStatement(sql);

                ps.setString(1, txtname.getText());
                ps.setString(2, txtnic.getText());
                ps.setInt(3, Integer.parseInt(txtage.getText()));
                ps.setString(4, gender);
                ps.setString(5, txtphone.getText());
                ps.setString(6, txtemail.getText());
                ps.setString(7, addDate);
                ps.setString(8, txtdes.getText());
                ps.setDouble(9, Double.parseDouble(txtsal.getText()));
                ps.setBlob(10, is);
                ps.setString(11, ipath.getText());
                ps.setString(12, txtid.getText());

                int res = ps.executeUpdate();

                if (res >= 1) {

                    JOptionPane.showMessageDialog(null, "updated ");
                } else {

                    JOptionPane.showMessageDialog(null, "not inserted ");
                }

                txtid.setText("");
                txtname.setText("");
                txtnic.setText("");
                txtage.setText("");

                txtphone.setText("");
                txtemail.setText("");
                txtdes.setText("");
                txtsal.setText("");

                txtgender.clearSelection();
                lbl_photo.setIcon(null);
                txtdate.setCalendar(null);
                ipath.setText("");

                fillTable();
                jToggleButton2.setEnabled(true);
                autoID();     
                fillcombo1();
              //  fillcombo2();
              //  fillcombo3();
              //  fillcombo4();

            }

        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(this, ex);

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex);

        } catch (FileNotFoundException ex) {

            JOptionPane.showMessageDialog(this, ex);
        }


    }//GEN-LAST:event_editActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
        jToggleButton2.setEnabled(false);
        int id = jTable1.getSelectedRow();

        showitemstofileds(id);
    }//GEN-LAST:event_jTable1MouseClicked

    private String gender;
    private void maleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_maleActionPerformed
        // TODO add your handling code here:


    }//GEN-LAST:event_maleActionPerformed

    private void femaleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_femaleActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_femaleActionPerformed

    private void b5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b5ActionPerformed
        // TODO add your handling code here:
            dispose();
        payment i = new payment();
        i.setVisible(true);
    }//GEN-LAST:event_b5ActionPerformed

    private void b6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b6ActionPerformed
        // TODO add your handling code here:
        dispose();
        used i = new used();
        i.setVisible(true);

    }//GEN-LAST:event_b6ActionPerformed

    private void b7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b7ActionPerformed
        // TODO add your handling code here:
         dispose();
        order i = new order();
        i.setVisible(true);
    }//GEN-LAST:event_b7ActionPerformed

    private void b8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b8ActionPerformed
        // TODO add your handling code here:
          dispose();
        travelInterface i = new travelInterface();
        i.setVisible(true);
    }//GEN-LAST:event_b8ActionPerformed

    private void b9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b9ActionPerformed
        // TODO add your handling code here:
        dispose();
        expenses i = new expenses();
        i.setVisible(true);
    }//GEN-LAST:event_b9ActionPerformed

    private void b1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_b1MouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_b1MouseClicked

    ///ADD LEAVES
    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:

        try {

            if (leavedate.getDate() == null) {
                JOptionPane.showMessageDialog(null, "Choose the Date ");
            } else {

                Class.forName("com.mysql.jdbc.Driver");
                con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/itp", "root", "");

                String value = ename.getSelectedItem().toString();

                System.out.println(value);

                insert = con.prepareStatement("select * from employees where name = '" + value + "' ");

                ResultSet rs = insert.executeQuery();

                while (rs.next()) {

                    String empid = rs.getString("id");

                    System.out.println(empid);

                    String sql = "insert into leaves" + "(id,leavedate)" + "values(?,?)";

                    SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
                    String addDate = dateformat.format(leavedate.getDate());

                    PreparedStatement ps = con.prepareStatement(sql);

                    ps.setString(1, empid);
                    ps.setString(2, addDate);

                    int res = ps.executeUpdate();

                    if (res >= 1) {

                        JOptionPane.showMessageDialog(null, "inserted ");
                    } else {

                        JOptionPane.showMessageDialog(null, "not inserted ");
                    }

                    ename.setName(null);
                    leavedate.setCalendar(null);

                }

            }
        } catch (Exception ex) {

            JOptionPane.showMessageDialog(this, ex);
        }


    }//GEN-LAST:event_jButton5ActionPerformed

    private void ename1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ename1ActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_ename1ActionPerformed

    private void checkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkActionPerformed
        // TODO add your handling code here:

        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/itp", "root", "");

            String value = ename1.getSelectedItem().toString();
            System.out.println(value);
            int yr = year.getYear();
            //   System.out.println(yr);

            int mnth = Integer.parseInt((String) month.getSelectedItem());
            String mon = month.getSelectedItem().toString();

            // int mont= Integer.parseInt(mon);
            //  String mn = month.getSelectedItem().toString();
            //        System.out.println(mnth);
            if (mnth == 0) {

                insert = con.prepareStatement("select * from employees where name like '" + value + "' ");

                ResultSet rs1 = insert.executeQuery();

                while (rs1.next()) {

                    String empid = rs1.getString("id");

                    System.out.println(empid);
                    insert = con.prepareStatement("select count(id) from leaves where leavedate like '" + yr + "%' and  id like '" + empid + "'");

                    ResultSet rs = insert.executeQuery();

                    rs.next();

                    int count = Integer.parseInt(rs.getString("count(id)"));

                    System.out.println(count);

                    if (count >= 1) {
                        viewleave.setText(Integer.toString(count) + "  Leaves already taken for this Year  " + Integer.toString(yr));
                    }

                    if (count <= 0) //  JOptionPane.showMessageDialog(this, "No leaves ");
                    {
                        viewleave.setText(" No Leaves  ");
                    }

                }

            } else if (mnth > 0 & mnth < 10) {

                insert = con.prepareStatement("select id from employees where name like '" + value + "' ");

                ResultSet rs1 = insert.executeQuery();

                while (rs1.next()) {

                    String empid = rs1.getString("id");
                    insert = con.prepareStatement("select count(id) from leaves where leavedate like '" + yr + "_0" + mnth + "%'and id like '" + empid + "%'");

                    ResultSet rs = insert.executeQuery();

                    rs.next();

                    int count = Integer.parseInt(rs.getString("count(id)"));

                    System.out.println(count);

                    if (count >= 1) {
                        viewleave.setText(Integer.toString(count) + "  Leaves already taken for Year  " + Integer.toString(yr) + "  Month  " + Integer.toString(mnth));
                    }

                    if (count <= 0) //  JOptionPane.showMessageDialog(this, "No leaves ");
                    {
                        viewleave.setText(" No Leaves  ");
                    }

                    // viewleave.setText(" ");
                }

            } else {

                insert = con.prepareStatement("select id from employees where name like '" + value + "' ");

                ResultSet rs1 = insert.executeQuery();

                while (rs1.next()) {

                    String empid = rs1.getString("id");
                    insert = con.prepareStatement("select count(id) from leaves where leavedate like '" + yr + "-" + mnth + "%'and id like '" + empid + "%'");

                    ResultSet rs = insert.executeQuery();

                    rs.next();

                    int count = Integer.parseInt(rs.getString("count(id)"));

                    System.out.println(count);

                    if (count >= 1) {
                        viewleave.setText(Integer.toString(count) + "  Leaves already taken for Year  " + Integer.toString(yr) + "  Month  " + Integer.toString(mnth));
                    }

                    if (count <= 0) //   JOptionPane.showMessageDialog(this, "No leaves ");
                    {
                        viewleave.setText("No Leaves   ");
                    }
                }

            }

        } catch (SQLException ex) {

            //JOptionPane.showMessageDialog(this, "No leaves ");
            viewleave.setText(" No Leaves ");

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(NewJFrame.class.getName()).log(Level.SEVERE, null, ex);
        }


    }//GEN-LAST:event_checkActionPerformed

    private void enameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_enameActionPerformed
        // TODO add your handling code here:


    }//GEN-LAST:event_enameActionPerformed

    private void addleaveStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_addleaveStateChanged
        // TODO add your handling code here:


    }//GEN-LAST:event_addleaveStateChanged

    private void addleaveMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addleaveMouseClicked
        /* fillcombo1();  
  fillcombo2();// TODO add your handling code here:
  fillcombo3();
   fillcombo4();*/


    }//GEN-LAST:event_addleaveMouseClicked

    private void enameItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_enameItemStateChanged
        // TODO add your handling code here:

    }//GEN-LAST:event_enameItemStateChanged

    private void clearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearActionPerformed
        // TODO add your handling code here:
        jToggleButton2.setEnabled(true);
        txtid.setText("");
        txtname.setText("");
        txtnic.setText("");
        txtage.setText("");

        txtphone.setText("");
        txtemail.setText("");
        txtdes.setText("");
        txtsal.setText("");
        j1.setText("");
        j2.setText("");
        jn.setText("");
        jnic.setText("");
        jb.setText("");
        jd.setText("");
        j3.setText("");
        ipath.setText("");

        txtgender.clearSelection();
        lbl_photo.setIcon(null);
        txtdate.setCalendar(null);
        autoID();
        fillcombo1();
       // fillcombo2();
     //   fillcombo3();
       // fillcombo4();
    }//GEN-LAST:event_clearActionPerformed

    private void getActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_getActionPerformed
        // TODO add your handling code here:

        try {

            if (otdate.getDate() == null) {
                JOptionPane.showMessageDialog(null, "Choose the Date ");
            }
            if (ottime.getText().toString().equals("")) {
                JOptionPane.showMessageDialog(null, "Enter the Time ");
            } else {
                Class.forName("com.mysql.jdbc.Driver");
                con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/itp", "root", "");

                String value = ename3.getSelectedItem().toString();
                double ot = Double.parseDouble(ottime.getText());

                System.out.println(value);

                insert = con.prepareStatement("select * from employees where name = '" + value + "' ");

                ResultSet rs = insert.executeQuery();

                while (rs.next()) {

                    String empid = rs.getString("id");

                    System.out.println(empid);

                    String sql = "insert into ot" + "(id,otdate,ottime)" + "values(?,?,?)";

                    SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
                    String addDate = dateformat.format(otdate.getDate());

                    PreparedStatement ps = con.prepareStatement(sql);

                    ps.setString(1, empid);

                    ps.setString(2, addDate);
                    ps.setDouble(3, ot);

                    int res = ps.executeUpdate();

                    if (res >= 1) {

                        JOptionPane.showMessageDialog(null, "inserted ");
                    } else {

                        JOptionPane.showMessageDialog(null, "not inserted ");
                    }

                    ename3.setName(null);
                    otdate.setCalendar(null);
                    ottime.setText(null);

                }

            }
        } catch (Exception ex) {

            JOptionPane.showMessageDialog(null, "Please Enter the Correct Input");
        }


    }//GEN-LAST:event_getActionPerformed

    private void txtidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtidActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtidActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:

        epf.setEditable(false);
        etf.setEditable(false);
        basicsalary.setEditable(false);
        othour.setEditable(false);
        totalleaves.setEditable(false);
        total.setEditable(false);

        bonus.setText("");
        otrate.setText("");
        leaverate.setText("");
        total.setText("");
        j5.setText("");
        j6.setText("");
        j7.setText("");

        double sc;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/itp", "root", "");

            String value = ename4.getSelectedItem().toString();
           // System.out.println(value);
            int yr = year2.getYear();
            //   System.out.println(yr);

            int mnth = Integer.parseInt((String) month2.getSelectedItem());
            String mon = month.getSelectedItem().toString();

            insert = con.prepareStatement("select * from employees where name like '" + value + "' ");

            ResultSet rs1 = insert.executeQuery();

            while (rs1.next()) {

                String empid = rs1.getString("id");
                Double sal = rs1.getDouble("sal");

               // System.out.println(empid);
                insert = con.prepareStatement("select count(id) from leaves where leavedate like '" + yr + "%' and  id like '" + empid + "'");

                ResultSet rs = insert.executeQuery();

                rs.next();

                int count = Integer.parseInt(rs.getString("count(id)"));

               // System.out.println(count);

                if (count <= 21) {
                    warning.setText(" ");
                    leaverate.setEditable(false);
                    leaverate.setEnabled(false);
                    leaverate.setText("");
                }
                if (count > 21) {

                    warning.setText("You exceeded 21 annual leaves please enter the leave rate ");
                    leaverate.setEditable(true);
                    leaverate.setEnabled(true);
                }

                totalleaves.setText(Integer.toString(count));
                basicsalary.setText(Double.toString(sal));

                double etfcal = sal * 3 / 100;
                double epfcal = sal * 8 / 100;

                double r1 = etfcal;
                r1=Math.round(r1*100.0)/100.0;
                etf.setText(Double.toString(r1));
                
                double r2 = epfcal;
                r2=Math.round(r2*100.0)/100.0;
                epf.setText(Double.toString(r2));
                
                //etf.setText(Double.toString(etfcal));
                //epf.setText(Double.toString(epfcal));

                if (mnth > 0 & mnth < 10) {
                    insert = con.prepareStatement("select sum(ottime) from ot where otdate like '" + yr + "-0" + mnth + "%' and  id like '" + empid + "'");

                    ResultSet rs2 = insert.executeQuery();

                    rs2.next();

                    double count2 = rs2.getDouble("sum(ottime)");
                   // System.out.println(count2);

                    String doubleAsString = String.valueOf(count2);
                    int indexOfDecimal1 = doubleAsString.indexOf(".");
                    double diff = Double.parseDouble(doubleAsString.substring(indexOfDecimal1));
                   
                   
                    int time = (int) count2;

                  //  double diff = count2 - time;

                    if (diff > 0.60) {

                        double remain = diff - 0.60;
                        time = time + 1;
                        count2 = time + remain;
                    } else {
                        count2 = count2;
                    }

                    othour.setText(Double.toString(count2));

                } else {

                    insert = con.prepareStatement("select sum(ottime) from ot where otdate like '" + yr + "-" + mnth + "%' and  id like '" + empid + "'");

                    ResultSet rs2 = insert.executeQuery();

                    rs2.next();

                    double count2 = rs2.getDouble("sum(ottime)");
                    System.out.println(count2);

                     String doubleAsString = String.valueOf(count2);
                    int indexOfDecimal1 = doubleAsString.indexOf(".");
                    double diff = Double.parseDouble(doubleAsString.substring(indexOfDecimal1));
                    
                    int time = (int) count2;

                  //  double diff = count2 - time;

                    if (diff >= 0.60) {

                        double remain = diff - 0.60;
                        time = time + 1;
                        count2 = time + remain;
                    } else {
                        count2 = count2;
                    }

                    othour.setText(Double.toString(count2));

                }

            }

        } catch (SQLException ex) {

            //JOptionPane.showMessageDialog(this, "No leaves ");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(NewJFrame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NumberFormatException ex) {

            JOptionPane.showMessageDialog(null, "please fill the all fields");

        }


    }//GEN-LAST:event_jButton1ActionPerformed

    double calot,leavededuct;
    private void calculateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_calculateActionPerformed
        // TODO add your handling code here:
        if (bonus.getText().toString().equals("")) {
            JOptionPane.showMessageDialog(null, "Enter the Bonus Salary ");
        } else if (otrate.getText().toString().equals("")) {
            JOptionPane.showMessageDialog(null, "Enter the OT Rate ");
        } else if (j5.getText() == "please enter correct input") {
            JOptionPane.showMessageDialog(null, "Enter the Bonus in correct input ");
        } else if (j6.getText() == "please enter correct input") {
            JOptionPane.showMessageDialog(null, "Enter the Leave Rate in correct input ");
        } else if (j7.getText() == "please enter correct input") {
            JOptionPane.showMessageDialog(null, "Enter the OT Rate in correct input ");
        } else {
            int totleave = Integer.parseInt(totalleaves.getText());
            double basicsal = Double.parseDouble(basicsalary.getText());
            double epfal = Double.parseDouble(epf.getText());
            double etfal = Double.parseDouble(etf.getText());

            double orate = Double.parseDouble(otrate.getText());
            double bonus1 = Double.parseDouble(bonus.getText());
            double totOT = Double.parseDouble(othour.getText());

            /*      if(bonus.getText()==null)
              bonus1=0;
          if(otrate.getText()==null)
              orate=0;
             */
            double remain;
            int time = (int) totOT;
            System.out.println(time);
            double diff = (totOT - time) * 100;
            remain = diff / 60;

            System.out.println(diff);
            //      System.out.println(remain);

            calot=(orate * time) + (orate * remain);
           calot= Math.round(calot*100.0)/100.0;
           
            
            if (totleave > 21) {
                if (leaverate.getText().toString().equals("")) {
                    JOptionPane.showMessageDialog(null, "Enter the Leave Rate ");
                } else {
                    double lrate = Double.parseDouble(leaverate.getText());
                    double netsal = (basicsal + bonus1 + (orate * time) + (orate * remain)) - (epfal + etfal + ((totleave - 21) * lrate));
                    
                     double r = netsal;
                r=Math.round(r*100.0)/100.0;
                total.setText(Double.toString(r));
                 leavededuct=((totleave - 21) * lrate);
                    
                }
            } else {

                double netsal = (basicsal + bonus1 + (orate * time) + (orate * remain)) - (epfal + etfal);

                double r = netsal;
                r=Math.round(r*100.0)/100.0;
                total.setText(Double.toString(r));
                leavededuct=0;
                

            }

        }


    }//GEN-LAST:event_calculateActionPerformed

    private void epfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_epfActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_epfActionPerformed

    private void ottimeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ottimeMouseClicked
        // TODO add your handling code here:
        wr1.setText("(example 2.30)");
    }//GEN-LAST:event_ottimeMouseClicked

    private void ottimeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ottimeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ottimeActionPerformed

    private void enameMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_enameMouseEntered
        /*        // TODO add your handling code here:
          fillcombo1();  
  fillcombo2();// TODO add your handling code here:
  fillcombo3();
   fillcombo4();*/

    }//GEN-LAST:event_enameMouseEntered

    private void saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveActionPerformed
        // TODO add your handling code here:

        if (total.getText().toString().equals("")) {
            
            JOptionPane.showMessageDialog(null, "First Calculate the Salary ");
            
        } else {
            try {

                if (true == checksalary()) {

                    String value1 = ename4.getSelectedItem().toString();
                     int yr1 = year2.getYear();
                     int mnth1 = Integer.parseInt((String) month2.getSelectedItem());
                     
                    int opt = JOptionPane.showConfirmDialog(null, "This " +value1+" Year "+yr1 +" Month " +mnth1+ " salary is already inserted .\nDo you want to update it ?", "Delete", JOptionPane.YES_NO_OPTION);

                    if (opt == 0) {

                        Class.forName("com.mysql.jdbc.Driver");
                        con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/itp", "root", "");

                        String value = ename4.getSelectedItem().toString();
                        int yr = year2.getYear();
                        int mnth = Integer.parseInt((String) month2.getSelectedItem());
                        String mon = month2.getSelectedItem().toString();

                        insert = con.prepareStatement("select s.salaryid,e.id from employees e , salary s where s.eid=e.id and s.year='" + yr + "' and s.month='" + mnth + "' and e.name like '" + value + "' ");
                        ResultSet rs = insert.executeQuery();

                        rs.next();
                        int sid = rs.getInt("s.salaryid");
                        String empid = rs.getString("e.id");

                        String sql = "update salary set eid=? , year=? , month=? , total=? , etype=?  where salaryid=?";

                        PreparedStatement ps = con.prepareStatement(sql);

                        double totsal = Double.parseDouble(total.getText());
                        ps.setString(1, empid);
                        ps.setInt(2, yr);
                        ps.setInt(3, mnth);
                        ps.setDouble(4, totsal);
                        ps.setInt(5, 2);
                        ps.setInt(6, sid);

                        int res = ps.executeUpdate();

                        if (res >= 1) {

                            JOptionPane.showMessageDialog(null, "Updated ");
                        } else {

                            JOptionPane.showMessageDialog(null, "not updated ");
                        }

                    }
                }
                
                else{
                    
                Class.forName("com.mysql.jdbc.Driver");
                con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/itp", "root", "");

                String value = ename4.getSelectedItem().toString();
                int yr = year2.getYear();
                int mnth = Integer.parseInt((String) month2.getSelectedItem());
                String mon = month2.getSelectedItem().toString();
               
                insert = con.prepareStatement("select * from employees where name like '" + value + "' ");
                ResultSet rs = insert.executeQuery();

                rs.next();
                    String empid = rs.getString("id");
                      String sql = "insert into salary" + "(salaryid,eid,year,month,total,etype)" + "values(?,?,?,?,?,?)";

                        PreparedStatement ps = con.prepareStatement(sql);

                        double totsal = Double.parseDouble(total.getText());

                        ps.setInt(1, 0);
                        ps.setString(2, empid);
                        ps.setInt(3, yr);
                        ps.setInt(4, mnth);
                        ps.setDouble(5, totsal);
                        ps.setInt(6, 2);

                        int res = ps.executeUpdate();

                        if (res >= 1) {

                            JOptionPane.showMessageDialog(null, "inserted ");
                        } else {

                            JOptionPane.showMessageDialog(null, "not inserted ");
                        }

                }                
            } catch (Exception ex) {

                JOptionPane.showMessageDialog(null, ex);
                // viewleave.setText(" No Leaves ");
            } 

        }
    }//GEN-LAST:event_saveActionPerformed

    private void txtageKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtageKeyPressed
        // TODO add your handling code here:

        /*  int key=evt.getKeyCode();
        if((key>evt.VK_0 && key <=evt.VK_9)|| (key>evt.VK_NUMPAD0 && key <=evt.VK_NUMPAD9)||key==KeyEvent.VK_BACKSPACE ){
         txtage.setEditable(true);
        }
        
        else{
        
           
         JOptionPane.showMessageDialog(null, "please input correct input");
        }*/

    }//GEN-LAST:event_txtageKeyPressed

    private void txtemailKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtemailKeyReleased
        // TODO add your handling code here:
        String PATTERN = "^[a-zA-Z0-9]{0,30}[@][a-zA-Z0-9]{0,10}[.][a-zA-Z]{2,5}$";
        Pattern patt = Pattern.compile(PATTERN);
        Matcher match = patt.matcher(txtemail.getText());

        if (!match.matches()) {
            j1.setText("Email is incorrect");
        } else {
            j1.setText(null);
        }
    }//GEN-LAST:event_txtemailKeyReleased

    private void txtemailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtemailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtemailActionPerformed

    private void txtphoneKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtphoneKeyReleased
        // TODO add your handling code here:

        String PATTERN = "^[0-9]{10,10}$";
        Pattern patt = Pattern.compile(PATTERN);
        Matcher match = patt.matcher(txtphone.getText());

        if (!match.matches()) {
            j2.setText("phone number is incorrect");
        } else {
            j2.setText(null);
        }
    }//GEN-LAST:event_txtphoneKeyReleased

    private void txtageKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtageKeyReleased
        // TODO add your handling code here:
        String PATTERN = "^[0-9]{0,2}$";
        Pattern patt = Pattern.compile(PATTERN);
        Matcher match = patt.matcher(txtage.getText());

        if (!match.matches()) {
            j3.setText("Age is incorrect");
        } else {
            j3.setText(null);
        }

    }//GEN-LAST:event_txtageKeyReleased

    private void txtageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtageActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtageActionPerformed

    private void bonusKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_bonusKeyReleased
        // TODO add your handling code here:

        String PATTERN = "^[0-9. ]{0,12}$";
        Pattern patt = Pattern.compile(PATTERN);
        Matcher match = patt.matcher(bonus.getText());

        if (!match.matches()) {
            j5.setText("please enter correct input");
        } else {
            j5.setText(null);
        }
    }//GEN-LAST:event_bonusKeyReleased

    private void leaverateKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_leaverateKeyReleased
        // TODO add your handling code here:
        String PATTERN = "^[0-9. ]{0,12}$";
        Pattern patt = Pattern.compile(PATTERN);
        Matcher match = patt.matcher(leaverate.getText());

        if (!match.matches()) {
            j6.setText("please enter correct input");
        } else {
            j6.setText(null);
        }
    }//GEN-LAST:event_leaverateKeyReleased

    private void otrateKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_otrateKeyReleased
        // TODO add your handling code here:
        String PATTERN = "^[0-9. ]{0,12}$";
        Pattern patt = Pattern.compile(PATTERN);
        Matcher match = patt.matcher(otrate.getText());

        if (!match.matches()) {
            j7.setText("please enter correct input");
        } else {
            j7.setText(null);
        }
    }//GEN-LAST:event_otrateKeyReleased

    private void ottimeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ottimeKeyReleased
        // TODO add your handling code here:
        String PATTERN = "^[0-9]{0,2}[.][0-9]{0,2}$";
        Pattern patt = Pattern.compile(PATTERN);
        Matcher match = patt.matcher(ottime.getText());

        if (!match.matches()) {
            wr1.setText("please enter correct input \n Example : 2.30 ");
        } else {
            wr1.setText(null);
        }
    }//GEN-LAST:event_ottimeKeyReleased

    private void txtnicActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtnicActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtnicActionPerformed

    private void txtsalKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtsalKeyReleased
        // TODO add your handling code here:
        String PATTERN = "^[0-9. ]{0,30}$";
        Pattern patt = Pattern.compile(PATTERN);
        Matcher match = patt.matcher(txtsal.getText());

        if (!match.matches()) {
            jb.setText("Basic Salary is incorrect");
        } else {
            jb.setText(null);
        }
    }//GEN-LAST:event_txtsalKeyReleased

    private void txtdesKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtdesKeyReleased
        // TODO add your handling code here:
        String PATTERN = "^[a-zA-Z ]{0,30}$";
        Pattern patt = Pattern.compile(PATTERN);
        Matcher match = patt.matcher(txtdes.getText());

        if (!match.matches()) {
            jd.setText("Designation is incorrect");
        } else {
            jd.setText(null);
        }
    }//GEN-LAST:event_txtdesKeyReleased

    private void txtnameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtnameKeyReleased
        // TODO add your handling code here:
        String PATTERN = "^[a-zA-Z. ]{0,50}$";
        Pattern patt = Pattern.compile(PATTERN);
        Matcher match = patt.matcher(txtname.getText());

        if (!match.matches()) {
            jn.setText("Name is incorrect");
        } else {
            jn.setText(null);
        }

    }//GEN-LAST:event_txtnameKeyReleased

    private void txtnicKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtnicKeyReleased
        // TODO add your handling code here:        
        String PATTERN = "^[0-9vV]{10,10}$";
        Pattern patt = Pattern.compile(PATTERN);
        Matcher match = patt.matcher(txtnic.getText());

        if (!match.matches()) {
            jnic.setText("NIC is incorrect");
        } else {
            jnic.setText(null);
        }
    }//GEN-LAST:event_txtnicKeyReleased

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        if (total.getText().toString().equals("")) {
            JOptionPane.showMessageDialog(null, "First Calculate the Salary ");
        } else {
            
            String value1 = ename4.getSelectedItem().toString();
            int yr1 = year2.getYear();
            int mnth1 = Integer.parseInt((String) month2.getSelectedItem());
            
               int opt = JOptionPane.showConfirmDialog(null, "You selected Employee "+value1 +" Year "+yr1 +" Month " +mnth1+ " for create the Pay Sheet ","Message", JOptionPane.OK_CANCEL_OPTION);

                if (opt == 0) {
            
            String value = ename4.getSelectedItem().toString();
            System.out.println(value);
            int yr = year2.getYear();
            //   System.out.println(yr);

            int mnth = Integer.parseInt((String) month2.getSelectedItem());
            String mon = month.getSelectedItem().toString();
            int totleave = Integer.parseInt(totalleaves.getText());
            double basicsal = Double.parseDouble(basicsalary.getText());
            double epfal = Double.parseDouble(epf.getText());
            double etfal = Double.parseDouble(etf.getText());

            double orate = Double.parseDouble(otrate.getText());
            double bonus1 = Double.parseDouble(bonus.getText());
            double totOT = Double.parseDouble(othour.getText());
            double totsal = Double.parseDouble(total.getText());

            JFileChooser dialog = new JFileChooser();
            dialog.setSelectedFile(new File(value+"_"+"Year "+yr+" Month "+mnth+"_"+"slary_slip" + ".pdf"));
            int dialogResult = dialog.showSaveDialog(null);
            if (dialogResult == JFileChooser.APPROVE_OPTION) {

                String filepath = dialog.getSelectedFile().getPath();

                try {

                    Document myDocument = new Document();
                    PdfWriter mywriter = PdfWriter.getInstance(myDocument, new FileOutputStream(filepath));

                    myDocument.open();
                    com.itextpdf.text.Image image = com.itextpdf.text.Image.getInstance("header.png");
                    image.scalePercent(21);
                    myDocument.add(image);

                   // myDocument.add(new Paragraph(new Date().toString()));
                    myDocument.add(new Paragraph("----------------------------------------------------------------------------------------------------------------------------------\n"));
                    myDocument.add(new Paragraph("PAY SLIP", FontFactory.getFont(FontFactory.TIMES_BOLD, 20, Font.BOLD)));
                    myDocument.add(new Paragraph(new Date().toString()));
                  //  myDocument.add(new Paragraph("=========================================================================="));
                    myDocument.add(new Paragraph("YEAR: " + yr + "      " + "MONTH : " + mnth + "\n\n", FontFactory.getFont(FontFactory.TIMES_BOLD, 12)));
                    
                    myDocument.add(new Paragraph("Name Of Employee  : " + value + "", FontFactory.getFont(FontFactory.TIMES_ROMAN, 11, Font.PLAIN)));
                    
                    myDocument.add(new Paragraph("Leaves Taken  : " + totleave + "", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10, Font.PLAIN)));
                    
                    if(totleave>21){
                        int releave= totleave-21;
                    myDocument.add(new Paragraph("No pay Leaves taken  : " + releave + "", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10, Font.PLAIN)));
                    }else{
                    myDocument.add(new Paragraph("No pay Leaves taken : " + 0 + "", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10, Font.PLAIN)));
                    }
                    
                        String doubleAsString = String.valueOf(totOT);
                    int indexOfDecimal = doubleAsString.indexOf(".");
                    double diff = Double.parseDouble(doubleAsString.substring(indexOfDecimal));

                    if (diff == 0.0 || diff == 0.1 || diff == 0.2 || diff == 0.3 || diff == 0.4 || diff == 0.5 || diff == 0.6 || diff == 0.7 || diff == 0.8 || diff == 0.9) {
                        myDocument.add(new Paragraph("Total OT Hours  : " + totOT + "0", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10, Font.PLAIN)));
                    } else {
                        myDocument.add(new Paragraph("Total OT Hours  : " + totOT + "", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10, Font.PLAIN)));
                    }

                    

                    myDocument.add(new Paragraph(" \n", FontFactory.getFont(FontFactory.TIMES_BOLD, 15, Font.BOLD)));
                    
                    PdfPTable table = new PdfPTable(4);

                    table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.getDefaultCell().setFixedHeight(25);

                    PdfPCell cell1 = new PdfPCell(new Paragraph("Earnings  ", FontFactory.getFont(FontFactory.TIMES_BOLD, 12, Font.BOLD)));
                    PdfPCell cell2 = new PdfPCell(new Paragraph("Amount Rs.  ", FontFactory.getFont(FontFactory.TIMES_BOLD, 12, Font.BOLD)));
                    PdfPCell cell3 = new PdfPCell(new Paragraph("Deductions  ", FontFactory.getFont(FontFactory.TIMES_BOLD, 12, Font.BOLD)));
                    PdfPCell cell4 = new PdfPCell(new Paragraph("Amount Rs.  ", FontFactory.getFont(FontFactory.TIMES_BOLD, 12, Font.BOLD)));

                    cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell4.setHorizontalAlignment(Element.ALIGN_CENTER);

                    cell1.setFixedHeight(25);
                    table.addCell(cell1);
                    table.addCell(cell2);
                    table.addCell(cell3);
                    table.addCell(cell4);
                    
                    table.addCell("Basic Salary");
                    table.addCell(String.valueOf(basicsal)+"0");
                    
                    String doubleAsString2 = String.valueOf(epfal);
                    int indexOfDecimal2 = doubleAsString2.indexOf(".");
                    double diff2 = Double.parseDouble(doubleAsString2.substring(indexOfDecimal2));

                    if (diff2 == 0.0 || diff2 == 0.1 || diff2 == 0.2 || diff2 == 0.3 || diff2 == 0.4 || diff2 == 0.5 || diff2 == 0.6 || diff2 == 0.7 || diff2 == 0.8 || diff2 == 0.9) {
                        table.addCell("EPF");
                        table.addCell(String.valueOf(epfal)+"0");
                    } else {     
                        table.addCell("EPF");
                        table.addCell(String.valueOf(epfal));
                    }
                    
                    
                    table.addCell("Bonus Salary");
                    table.addCell(String.valueOf(bonus1)+"0");
                    
                    
                    String doubleAsString3 = String.valueOf(etfal);
                    int indexOfDecimal3 = doubleAsString3.indexOf(".");
                    double diff3 = Double.parseDouble(doubleAsString3.substring(indexOfDecimal3));

                    if (diff3 == 0.0 || diff3 == 0.1 || diff3 == 0.2 || diff3 == 0.3 || diff3 == 0.4 || diff3 == 0.5 || diff3 == 0.6 || diff3 == 0.7 || diff3 == 0.8 || diff3 == 0.9) {
                        table.addCell("ETF");
                        table.addCell(String.valueOf(etfal)+"0");
                    } else {
                        table.addCell("ETF");
                        table.addCell(String.valueOf(etfal));
                    }

                    table.addCell("OT Amount");
                    table.addCell(String.valueOf(calot)+"0");
                    table.addCell("Leaves Deduction");
                    table.addCell(String.valueOf(leavededuct)+"0");
                    
                    table.addCell(" ");
                    table.addCell(" ");
                    
                    table.addCell(new Paragraph("Total Deduction", FontFactory.getFont(FontFactory.COURIER_BOLD, 12, Font.BOLD)));
                    table.addCell(new Paragraph((epfal+etfal+leavededuct)+"0", FontFactory.getFont(FontFactory.COURIER_BOLD, 12, Font.BOLD)));
                    
                    table.addCell(new Paragraph("Gross Pay", FontFactory.getFont(FontFactory.COURIER_BOLD, 12, Font.BOLD)));
                    table.addCell(new Paragraph((basicsal+bonus1+calot)+"0", FontFactory.getFont(FontFactory.COURIER_BOLD, 12, Font.BOLD)));

                    
                         String doubleAsString1 = String.valueOf(totsal);
                    int indexOfDecimal1 = doubleAsString1.indexOf(".");
                    double diff1 = Double.parseDouble(doubleAsString1.substring(indexOfDecimal1));

                    if (diff1 == 0.0 || diff1 == 0.1 || diff1 == 0.2 || diff1 == 0.3 || diff1 == 0.4 || diff1 == 0.5 || diff1 == 0.6 || diff1 == 0.7 || diff1 == 0.8 || diff1 == 0.9) {
                        table.addCell(new Paragraph("Net Pay", FontFactory.getFont(FontFactory.COURIER_BOLD, 12, Font.BOLD)));
                        table.addCell(new Paragraph(totsal+"0", FontFactory.getFont(FontFactory.COURIER_BOLD, 12, Font.BOLD)));
                    } else {
                        table.addCell(new Paragraph("Net Pay", FontFactory.getFont(FontFactory.COURIER_BOLD, 12, Font.BOLD)));
                        table.addCell(new Paragraph(totsal +"", FontFactory.getFont(FontFactory.COURIER_BOLD, 12, Font.BOLD)));
                    }
                 
                    
                    
               /*     myDocument.add(new Paragraph("Name Of Employee  : " + value + "", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10, Font.PLAIN)));
                    myDocument.add(new Paragraph("Basic Salary              : " + basicsal + "0", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10, Font.PLAIN)));
                    myDocument.add(new Paragraph("Bonus Salary             : " + bonus1 + "0", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10, Font.PLAIN)));

              

                  
                    myDocument.add(new Paragraph("Total Leaves              : " + totleave + "", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10, Font.PLAIN)));

               

                    myDocument.add(new Paragraph("--------------------------------"));

                    String doubleAsString1 = String.valueOf(totsal);
                    int indexOfDecimal1 = doubleAsString1.indexOf(".");
                    double diff1 = Double.parseDouble(doubleAsString1.substring(indexOfDecimal1));

                    if (diff1 == 0.0 || diff1 == 0.1 || diff1 == 0.2 || diff1 == 0.3 || diff1 == 0.4 || diff1 == 0.5 || diff1 == 0.6 || diff1 == 0.7 || diff1 == 0.8 || diff1 == 0.9) {
                        myDocument.add(new Paragraph("Total Salary         : " + totsal + "0", FontFactory.getFont(FontFactory.TIMES_BOLD, 12, Font.PLAIN)));
                    } else {
                        myDocument.add(new Paragraph("Total Salary         : " + totsal, FontFactory.getFont(FontFactory.TIMES_BOLD, 12, Font.PLAIN)));
                    }*/

                    myDocument.add(table);
                    myDocument.close();
                    JOptionPane.showMessageDialog(null, "Report created ");

                } catch (Exception e) {

                    JOptionPane.showMessageDialog(null, " ");

                }
    }//GEN-LAST:event_jButton2ActionPerformed
        }
        
    }
    }

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        dispose();
        Home i = new Home();
        i.setVisible(true);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        int yr1 = year2.getYear();
        int mnth1 = Integer.parseInt((String) month2.getSelectedItem());
        
   int opt = JOptionPane.showConfirmDialog(null, "You selected Year "+yr1 +" Month " +mnth1+ " for create the report ","Message", JOptionPane.OK_CANCEL_OPTION);

  if (opt == 0) {
        double nettot = 0;
        String net = null;
        double remain = 0;
        String value = ename4.getSelectedItem().toString();
        System.out.println(value);
        int yr = year2.getYear();
        //   System.out.println(yr);

        int mnth = Integer.parseInt((String) month2.getSelectedItem());
        String mon = month.getSelectedItem().toString();

        JFileChooser dialog = new JFileChooser();
        dialog.setSelectedFile(new File("Year "+yr+" Month "+mnth+"_"+"slary_report" + ".pdf"));
        int dialogResult = dialog.showSaveDialog(null);
        if (dialogResult == JFileChooser.APPROVE_OPTION) {

            String filepath = dialog.getSelectedFile().getPath();

            try {

                Document myDocument = new Document();
                PdfWriter mywriter = PdfWriter.getInstance(myDocument, new FileOutputStream(filepath));

                myDocument.open();

                com.itextpdf.text.Image image = com.itextpdf.text.Image.getInstance("header.png");
                image.scalePercent(21);
                myDocument.add(image);

                myDocument.add(new Paragraph(new Date().toString()));
                myDocument.add(new Paragraph("----------------------------------------------------------------------------------------------------------------------------------\n"));
                myDocument.add(new Paragraph("                     MONTHLY SALARY REPORT\n\n", FontFactory.getFont(FontFactory.TIMES_BOLD, 20, Font.BOLD)));

                // myDocument.add(new Paragraph("=========================================================================="));
                myDocument.add(new Paragraph("YEAR: " + yr + "      " + "MONTH : " + mnth + "\n\n", FontFactory.getFont(FontFactory.TIMES_BOLD, 12)));

                PdfPTable table = new PdfPTable(2);

                table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
                table.getDefaultCell().setFixedHeight(25);

                PdfPCell cell1 = new PdfPCell(new Paragraph("EMPLOYEE NAME  ", FontFactory.getFont(FontFactory.TIMES_BOLD, 12, Font.BOLD)));
                PdfPCell cell2 = new PdfPCell(new Paragraph("SALARY  ", FontFactory.getFont(FontFactory.TIMES_BOLD, 12, Font.BOLD)));

                cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell2.setHorizontalAlignment(Element.ALIGN_CENTER);

                cell1.setFixedHeight(25);
                table.addCell(cell1);
                table.addCell(cell2);

                for (int i = 0; i < retrivesalary().size(); i++) {
                    cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell2.setHorizontalAlignment(Element.ALIGN_CENTER);

                    //  int totint = (int) retrivesalary().get(i).getTotalsalary();
                    //   double diff = retrivesalary().get(i).getTotalsalary() - totint;
                    String doubleAsString = String.valueOf(retrivesalary().get(i).getTotalsalary());
                    int indexOfDecimal = doubleAsString.indexOf(".");
                    double diff = Double.parseDouble(doubleAsString.substring(indexOfDecimal));

                    if (diff == 0.0 || diff == 0.1 || diff == 0.2 || diff == 0.3 || diff == 0.4 || diff == 0.5 || diff == 0.6 || diff == 0.7 || diff == 0.8 || diff == 0.9) {

                        String tot = String.valueOf(retrivesalary().get(i).getTotalsalary());
                        table.addCell(retrivesalary().get(i).getName());
                        table.addCell("Rs. "+tot + "0");

                    } else {

                        String tot = String.valueOf(retrivesalary().get(i).getTotalsalary());
                        table.addCell(retrivesalary().get(i).getName());
                        table.addCell("Rs. "+tot);

                    }

                    nettot = nettot + retrivesalary().get(i).getTotalsalary();
                    net = String.valueOf(nettot);

                    //  int tt = (int)nettot;
                    // remain = nettot-tt;
                    String doubleAsString1 = String.valueOf(nettot);
                    int indexOfDecimal1 = doubleAsString1.indexOf(".");
                    remain = Double.parseDouble(doubleAsString1.substring(indexOfDecimal1));
                }

                if (remain == 0.0 || remain == 0.1 || remain == 0.2 || remain == 0.3 || remain == 0.4 || remain == 0.5 || remain == 0.6 || remain == 0.7 || remain == 0.8 || remain == 0.9) {

                    table.addCell(new Paragraph("NET TOTAL", FontFactory.getFont(FontFactory.TIMES_BOLD, 12, Font.BOLD)));
                    //   table.addCell(net+"0");
                    table.addCell(new Paragraph("Rs. "+net + "0", FontFactory.getFont(FontFactory.TIMES_BOLD, 12, Font.BOLD)));;

                } else {

                    table.addCell(new Paragraph("NET TOTAL", FontFactory.getFont(FontFactory.TIMES_BOLD, 12, Font.BOLD)));
                    table.addCell(new Paragraph("Rs. "+net, FontFactory.getFont(FontFactory.TIMES_BOLD, 12, Font.BOLD)));;

                }

                myDocument.add(table);

                myDocument.close();
                JOptionPane.showMessageDialog(null, "Report created ");

            } catch (Exception e) {

                JOptionPane.showMessageDialog(null, " ");

            }
        }

  }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void demoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_demoActionPerformed
        // TODO add your handling code here:
        jToggleButton2.setEnabled(true);
        txtname.setText("Nimesha Rathnayake");
        txtnic.setText("886544845V");
        txtage.setText("35");

        txtphone.setText("0768451488");
        txtemail.setText("nimesha@gmail.com");
        txtdes.setText("Waitor");
        txtsal.setText("60000.00");
        j1.setText("");
        j2.setText("");
        jn.setText("");
        jnic.setText("");
        jb.setText("");
        jd.setText("");
        j3.setText("");
        female.setSelected(true);
       

        lbl_photo.setIcon(resetImageSize(null, retriveData().get(4).getPhoto()));
        ipath.setText(retriveData().get(4).getPath());
        java.util.Date addDate = null;
        try {
            addDate = new SimpleDateFormat("yyyy-MM-dd").parse((String) retriveData().get(4).getEdate());
        } catch (ParseException ex) {
            Logger.getLogger(NewJFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        txtdate.setDate(addDate);
        autoID();
        fillcombo1();
        
    }//GEN-LAST:event_demoActionPerformed

    PreparedStatement insert, insert2;

    public void showitemstofileds(int index) {
        try {
            txtid.setText(retriveData().get(index).getId());
            txtname.setText(retriveData().get(index).getName());
            txtnic.setText(retriveData().get(index).getNic());
            txtage.setText(Integer.toString(retriveData().get(index).getAge()));

            String gen = retriveData().get(index).getGender();

            if (gen.equals("male")) {
                male.setSelected(true);
            }

            if (gen.equals("female")) {
                female.setSelected(true);
            }

            txtphone.setText(retriveData().get(index).getPhone());
            txtemail.setText(retriveData().get(index).getEmail());
            //txtdate.setText(retriveData().get(index).getId());
            txtdes.setText(retriveData().get(index).getDesignation());
            txtsal.setText(Double.toString(retriveData().get(index).getBsalary()));
            // txtid.setText(retriveData().get(index).getId());

            java.util.Date addDate = null;

            addDate = new SimpleDateFormat("yyyy-MM-dd").parse((String) retriveData().get(index).getEdate());
            txtdate.setDate(addDate);

            lbl_photo.setIcon(resetImageSize(null, retriveData().get(index).getPhoto()));

            ipath.setText(retriveData().get(index).getPath());

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);

        }

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NewJFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Name;
    private javax.swing.JTabbedPane addleave;
    private javax.swing.JButton b1;
    private javax.swing.JButton b2;
    private javax.swing.JButton b3;
    private javax.swing.JButton b4;
    private javax.swing.JButton b5;
    private javax.swing.JButton b6;
    private javax.swing.JButton b7;
    private javax.swing.JButton b8;
    private javax.swing.JButton b9;
    private javax.swing.JTextField basicsalary;
    private javax.swing.JTextField bonus;
    private javax.swing.JButton calculate;
    private javax.swing.JButton check;
    private javax.swing.JToggleButton clear;
    private javax.swing.JToggleButton delete;
    private javax.swing.JToggleButton demo;
    private javax.swing.JToggleButton edit;
    private javax.swing.JComboBox<String> ename;
    private javax.swing.JComboBox<String> ename1;
    private javax.swing.JComboBox<String> ename3;
    private javax.swing.JComboBox<String> ename4;
    private javax.swing.JTextField epf;
    private javax.swing.JTextField etf;
    private javax.swing.JRadioButton female;
    private javax.swing.JButton get;
    private javax.swing.JPanel hide;
    private javax.swing.JLabel ipath;
    private javax.swing.JLabel j1;
    private javax.swing.JLabel j2;
    private javax.swing.JLabel j3;
    private javax.swing.JLabel j5;
    private javax.swing.JLabel j6;
    private javax.swing.JLabel j7;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JToggleButton jToggleButton1;
    private javax.swing.JToggleButton jToggleButton2;
    private javax.swing.JLabel jb;
    private javax.swing.JLabel jd;
    private javax.swing.JLabel jn;
    private javax.swing.JLabel jnic;
    private javax.swing.JLabel lbl_photo;
    private com.toedter.calendar.JDateChooser leavedate;
    private javax.swing.JTextField leaverate;
    private javax.swing.JRadioButton male;
    private javax.swing.JComboBox<String> month;
    private javax.swing.JComboBox<String> month2;
    private com.toedter.calendar.JDateChooser otdate;
    private javax.swing.JTextField othour;
    private javax.swing.JTextField otrate;
    private javax.swing.JTextField ottime;
    private javax.swing.JLabel pt;
    private javax.swing.JButton save;
    private javax.swing.JTextField total;
    private javax.swing.JTextField totalleaves;
    private javax.swing.JTextField txtage;
    private com.toedter.calendar.JDateChooser txtdate;
    private javax.swing.JTextField txtdes;
    private javax.swing.JTextField txtemail;
    private javax.swing.ButtonGroup txtgender;
    private javax.swing.JTextField txtid;
    private javax.swing.JTextField txtname;
    private javax.swing.JTextField txtnic;
    private javax.swing.JTextField txtphone;
    private javax.swing.JTextField txtsal;
    private javax.swing.JLabel viewleave;
    private javax.swing.JLabel warning;
    private javax.swing.JLabel wr1;
    private com.toedter.calendar.JYearChooser year;
    private com.toedter.calendar.JYearChooser year2;
    // End of variables declaration//GEN-END:variables
}
