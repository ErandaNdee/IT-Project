/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import com.mysql.jdbc.Connection;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author CD
 */
public class payment extends javax.swing.JFrame {
    Connection con;
    /**
     * Creates new form payment
     */
    public payment() {
       
        initComponents();
        fillTable();
        txt_payId.setEditable(false);
        
        Date currentDate = new Date();
        SimpleDateFormat timeformat = new SimpleDateFormat("hh:mm:ss");
        txt_time.setText(timeformat.format(currentDate));
        txt_time.setEditable(false);
    }
    
    public Connection MySqlConnection(){
        Connection conn = null; // creating database connection
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = (Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/itp","root","");
           // JOptionPane.showMessageDialog(null, "MySql DB connection successful...");
            return conn;
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, "My sql connection fail..");
            return null;
        }
    }
    public ArrayList<Payment2> retrieveData(){
        ArrayList<Payment2> pp = null;
        pp = new ArrayList<Payment2>();
        try {
            Connection conn = MySqlConnection();
            String qry = "select * from payment_info";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(qry);
            Payment2 pay2;
            
            while(rs.next()){
                pay2 = new Payment2(rs.getInt(1),rs.getString(2),rs.getString("Date"),rs.getString("Time"),rs.getString("HallRID"),rs.getString("Payment_Type"),
                        Float.parseFloat(rs.getString(7)));
                
                pp.add(pay2);
            }
        } catch (Exception e) {
            System.out.println("Error in retrieveData Method" +e);
        }
        return pp;
    } 
    
    // retriveing data into jTable
    public void fillTable(){
        ArrayList<Payment2> pp = retrieveData();
        DefaultTableModel model = (DefaultTableModel)jTable1.getModel();
        model.setRowCount(0);
        Object[] row = new Object[7];
        for (int i = 0; i < pp.size(); i++) {
            row[0] = pp.get(i).getPaymentid();
            row[1] = pp.get(i).getRoomid();
            row[2] = pp.get(i).getDate();
            row[3] = pp.get(i).getTime();
            row[4] = pp.get(i).getHallid();
            row[5] = pp.get(i).getTxt_type();
            row[6] = pp.get(i).getAmount();
            model.addRow(row);
        }
        
    }
    
    // show item in search
    public void showItemToFields(int index){
        txt_payId.setText(Integer.toString(retrieveData().get(index).getPaymentid()));
        txt_roomId.setText(retrieveData().get(index).getRoomid());
        //txt_date.setText(retrieveData().get(index).getDate());
        txt_time.setText(retrieveData().get(index).getTime());
        txt_hallId.setText(retrieveData().get(index).getHallid());
       // txt_payment1.setText();
        txt_final.setText(Float.toString(retrieveData().get(index).getAmount()));
        
            String gen= retrieveData().get(index).getTxt_type();
    
    if(gen.equals("cash")){
            radioCash.setSelected(true);
    }
    
      if(gen.equals("card")){
            radioCard.setSelected(true);
    }
    
        
        try {
            java.util.Date dob = null;
            dob = new SimpleDateFormat("yyyy-MM-dd").parse((String)retrieveData().get(index).getDate());            
            txt_date.setDate(dob);
        } catch (Exception e) {
            System.out.println("Error at showItemToFields method" + e);
        }
                
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txt_paymentType = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        b1 = new javax.swing.JButton();
        b2 = new javax.swing.JButton();
        b3 = new javax.swing.JButton();
        b4 = new javax.swing.JButton();
        b5 = new javax.swing.JButton();
        b6 = new javax.swing.JButton();
        b7 = new javax.swing.JButton();
        b8 = new javax.swing.JButton();
        b9 = new javax.swing.JButton();
        b10 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txt_roomId = new javax.swing.JTextField();
        txt_payId = new javax.swing.JTextField();
        txt_hallId = new javax.swing.JTextField();
        txt_final = new javax.swing.JTextField();
        txt_time = new javax.swing.JTextField();
        txt_date = new com.toedter.calendar.JDateChooser();
        btn_Add = new javax.swing.JButton();
        btn_update = new javax.swing.JButton();
        btn_delete = new javax.swing.JButton();
        radioCash = new javax.swing.JRadioButton();
        radioCard = new javax.swing.JRadioButton();
        j2 = new javax.swing.JLabel();
        j3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();
        txt_search = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(153, 153, 153));

        jPanel5.setBackground(new java.awt.Color(0, 102, 102));
        jPanel5.setAutoscrolls(true);

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

        b10.setBackground(new java.awt.Color(0, 102, 102));
        b10.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        b10.setForeground(new java.awt.Color(255, 255, 255));
        b10.setText("Home");
        b10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b10ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(b6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(b2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(b3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(b4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(b7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(b5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(b1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(b9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(b8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(b10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(250, 250, 250)
                .addComponent(b10, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(b6, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(b2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(b3, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(b4, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(b7, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(b5, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(b1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(b9, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(b8, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(284, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel3.setBackground(new java.awt.Color(51, 0, 51));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 75)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("HOTEL ARONWAY");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel10)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 159, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel10)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        jPanel4.setBackground(new java.awt.Color(204, 204, 204));
        jPanel4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel1.setText("Payment Info");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel2.setText("Payment ID:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel3.setText("Room RID:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel4.setText("Date:");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel5.setText("Time:");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel6.setText("Hall RID:");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel7.setText("Payment Type:");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel8.setText("Final Amount:");

        txt_roomId.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_roomIdKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_roomIdKeyTyped(evt);
            }
        });

        txt_payId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_payIdActionPerformed(evt);
            }
        });

        txt_hallId.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_hallIdKeyReleased(evt);
            }
        });

        txt_final.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_finalActionPerformed(evt);
            }
        });

        txt_time.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_timeActionPerformed(evt);
            }
        });

        txt_date.setDateFormatString("yyyy-MM-dd");

        btn_Add.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_Add.setIcon(new javax.swing.ImageIcon(getClass().getResource("/payment2/Icons/add.png"))); // NOI18N
        btn_Add.setText("Add");
        btn_Add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_AddActionPerformed(evt);
            }
        });

        btn_update.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_update.setIcon(new javax.swing.ImageIcon(getClass().getResource("/payment2/Icons/update.png"))); // NOI18N
        btn_update.setText("UPDATE");
        btn_update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_updateActionPerformed(evt);
            }
        });

        btn_delete.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_delete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/payment2/Icons/delete.png"))); // NOI18N
        btn_delete.setText("DELETE");
        btn_delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_deleteActionPerformed(evt);
            }
        });

        txt_paymentType.add(radioCash);
        radioCash.setText("Cash");
        radioCash.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioCashActionPerformed(evt);
            }
        });

        txt_paymentType.add(radioCard);
        radioCard.setText("Card");
        radioCard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioCardActionPerformed(evt);
            }
        });

        j2.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        j2.setForeground(new java.awt.Color(204, 0, 0));

        j3.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        j3.setForeground(new java.awt.Color(204, 0, 0));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(53, 53, 53)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel2)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btn_Add, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11)))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(39, 39, 39)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txt_roomId, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_payId, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_date, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_time, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_hallId, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_final, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(radioCard)
                                    .addComponent(radioCash)))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                                .addComponent(btn_delete)
                                .addGap(30, 30, 30)
                                .addComponent(btn_update)))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(j2, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(91, 91, 91))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addComponent(j3, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(163, 163, 163)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2)
                    .addComponent(txt_payId, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3)
                    .addComponent(txt_roomId, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17)
                .addComponent(j2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(txt_date, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(txt_time, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(29, 29, 29)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txt_hallId, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(8, 8, 8)
                        .addComponent(j3, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(radioCash)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(radioCard)
                        .addGap(39, 39, 39)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel8)
                            .addComponent(txt_final, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(66, 66, 66)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn_Add)
                            .addComponent(btn_update)
                            .addComponent(btn_delete)))
                    .addComponent(jLabel4))
                .addContainerGap(157, Short.MAX_VALUE))
        );

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Payment ID", "Room ID", "Date", "Time", "Hall ID", "Payment Type", "Final Amount"
            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/payment2/Icons/search.png"))); // NOI18N
        jLabel9.setText("Search By Payment Type");

        txt_search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_searchActionPerformed(evt);
            }
        });
        txt_search.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_searchKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_searchKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(738, 738, 738)
                                .addComponent(jLabel9)
                                .addGap(18, 18, 18)
                                .addComponent(txt_search, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1109, javax.swing.GroupLayout.PREFERRED_SIZE))))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_search, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 670, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 972, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txt_payIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_payIdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_payIdActionPerformed

    private void txt_finalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_finalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_finalActionPerformed

    //for updating...
    
    private void btn_updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_updateActionPerformed
             txt_payId.setEditable(false);
             String qry = null;
             PreparedStatement ps = null;
             Connection conn = MySqlConnection();
       try{
          
                qry = "update payment_info set RoomRID = ? ,Date = ?,Time = ?,HallRID = ?,Payment_Type = ?,Final_Amount = ? where PaymentID = ?"; // inserting values into db
                 
                ps=conn.prepareStatement(qry);
                
                ps.setString(1,txt_roomId.getText());
                SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd"); // getting date
                 String addDate = dateformat.format(txt_date.getDate());
                ps.setString(2,addDate);
                ps.setString(3,txt_time.getText());
                ps.setString(4,txt_hallId.getText());
                //getting data from radio button 
                if(radioCard.isSelected()){
            
                    txt_payment1 = "card";
                }
                else if(radioCash.isSelected()){

                        txt_payment1 ="cash";
                }
                else{
                txt_payment1="";
                }
                ps.setString(5,txt_payment1);
                ps.setFloat(6,Float.parseFloat(txt_final.getText()));
                ps.setInt(7,Integer.parseInt(txt_payId.getText()));
                int res= ps.executeUpdate();
                
                if(res>=1){
                
                  JOptionPane.showMessageDialog(null,"Data Updated");
                }
                else{
                
                      JOptionPane.showMessageDialog(null,"Not Updated ");
                }
                
    
        }
        catch (SQLException ex) {
            
              JOptionPane.showMessageDialog(this, ex);
        }
                fillTable();
                txt_payId.setText("");
                txt_roomId.setText("");
                txt_date.setCalendar(null);
                txt_time.setText("");
                txt_hallId.setText("");
                txt_paymentType.clearSelection();
                txt_final.setText("");
      
        
    }//GEN-LAST:event_btn_updateActionPerformed

    //deleting an entry from data base...
    private void btn_deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_deleteActionPerformed
        txt_payId.setEditable(false);
        if(txt_payId.getText().equals("")){
            JOptionPane.showMessageDialog(null, "ID feiled is empty");
        }else{           
            try {
                String qry = "delete from payment_info where PaymentID = ? ";
                Connection conn = MySqlConnection();
                PreparedStatement ps = conn.prepareStatement(qry);
                ps.setInt(1, Integer.parseInt(txt_payId.getText().toString()));
                int res = ps.executeUpdate();
                
                if( res >= 1){
                    JOptionPane.showMessageDialog(null, "Entry deleted successfuly");
                }
                else
                    JOptionPane.showMessageDialog(null, "Entry deleted not successful"); 
                
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
                fillTable();
                txt_payId.setText("");
                txt_roomId.setText("");
                txt_date.setCalendar(null);
                txt_time.setText("");
                txt_hallId.setText("");
                txt_paymentType.clearSelection();
                txt_final.setText("");
    }//GEN-LAST:event_btn_deleteActionPerformed

    private void radioCashActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioCashActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_radioCashActionPerformed

    private void radioCardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioCardActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_radioCardActionPerformed
private String txt_payment1;
    private void btn_AddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_AddActionPerformed
        // TODO add your handling code here:
        
        
       try{
           Connection conn = MySqlConnection(); // creating db connection
           String qry = "insert into payment_info"+"(PaymentID,RoomRID,Date,Time,HallRID,Payment_Type,Final_Amount) values(?,?,?,?,?,?,?)"; // inserting values into db
                 
                PreparedStatement ps=conn.prepareStatement(qry);
                ps.setInt(1,0);
                ps.setString(2,txt_roomId.getText());
                SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd"); // getting date
                 String addDate = dateformat.format(txt_date.getDate());
                ps.setString(3,addDate);
                ps.setString(4,txt_time.getText());
                ps.setString(5,txt_hallId.getText());
                //getting data from radio button 
                if(radioCard.isSelected()){
            
                    txt_payment1 = "card";
                }
                else if(radioCash.isSelected()){

                        txt_payment1 ="cash";
                }
                else{
                txt_payment1="";
                }
                ps.setString(6,txt_payment1);
                ps.setFloat(7,Float.parseFloat(txt_final.getText()));
                int res= ps.executeUpdate();
                
                if(res>=1){
                
                  JOptionPane.showMessageDialog(null,"Data inserted");
                }
                else{
                
                      JOptionPane.showMessageDialog(null,"not inserted ");
                }
                fillTable();
                txt_payId.setText("");
                txt_roomId.setText("");
                txt_date.setCalendar(null);
                txt_time.setText("");
                txt_hallId.setText("");
                txt_paymentType.clearSelection();
                txt_final.setText("");
                
                txt_roomId.setEditable(true);
                txt_hallId.setEditable(true);
               
                         
        }
        catch (SQLException ex) {
            
              JOptionPane.showMessageDialog(this, ex);
        }
        
                    
        
       
    }//GEN-LAST:event_btn_AddActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        int ind = jTable1.getSelectedRow();
        showItemToFields(ind);
        btn_Add.setEnabled(false);
        
    }//GEN-LAST:event_jTable1MouseClicked
    // to search
    private void txt_searchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_searchKeyReleased

       txt_search.getText().toString();
       ArrayList<Payment2> pp = null;
       pp = new ArrayList<Payment2>();
       String val = txt_search.getText().toString();
       
        try {
            Connection con = MySqlConnection();
           String qry = "select * from  payment_info where Payment_Type like '%"+val+"%'";
           Statement st = con.createStatement();
           ResultSet rs =st.executeQuery(qry);
           Payment2 paysearch;
           
           while(rs.next()){
               paysearch = new Payment2(rs.getInt(1),rs.getString(2),rs.getString("Date"),rs.getString("Time"),rs.getString("HallRID"),rs.getString("Payment_Type"),
                        Float.parseFloat(rs.getString(7)));
               pp.add(paysearch);
               
               DefaultTableModel model = (DefaultTableModel)jTable1.getModel();
               model.setRowCount(0);
               Object[] row = new Object[7];
               for (int i = 0; i < pp.size(); i++) {
                    row[0] = pp.get(i).getPaymentid();
                    row[1] = pp.get(i).getRoomid();
                    row[2] = pp.get(i).getDate();
                    row[3] = pp.get(i).getTime();
                    row[4] = pp.get(i).getHallid();
                    row[5] = pp.get(i).getTxt_type();
                    row[6] = pp.get(i).getAmount();
                    model.addRow(row);
                    
               }
           }
        } catch (Exception e) {
                  JOptionPane.showMessageDialog(this, e);
        }
       
       
        
    }//GEN-LAST:event_txt_searchKeyReleased

    private void txt_searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_searchActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_txt_searchActionPerformed

    private void txt_searchKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_searchKeyTyped
    }//GEN-LAST:event_txt_searchKeyTyped

    private void txt_timeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_timeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_timeActionPerformed

    private void b1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_b1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_b1MouseClicked

    private void b1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b1ActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_b1ActionPerformed

    private void b2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b2ActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_b2ActionPerformed

    private void b3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b3ActionPerformed

    }//GEN-LAST:event_b3ActionPerformed

    private void b4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b4ActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_b4ActionPerformed

    private void b5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_b5ActionPerformed

    private void b6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b6ActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_b6ActionPerformed

    private void b7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_b7ActionPerformed

    private void b8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_b8ActionPerformed

    private void b9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_b9ActionPerformed

    private void txt_roomIdKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_roomIdKeyReleased
        // TODO add your handling code here:
         try{
        String  PATTERN = "^[0-9]{0,10}$";
        Pattern patt = Pattern.compile(PATTERN);
        Matcher match = patt.matcher(txt_roomId.getText());
        
        if(!match.matches()){
              j2.setText("ID is incorrect");
        }
        else{
            j2.setText(null);
        }
             
        if(txt_roomId.getText()!=null){
        
            
            String id= "0";
            txt_hallId.setText(id);
            txt_hallId.setEditable(false);
            
             int rod = Integer.parseInt(txt_roomId.getText());
             Connection con = MySqlConnection();
           String qry = "select sum(amount) from  trav_pack_handle where RRID like '"+rod+"'";
           Statement st = con.createStatement();
           ResultSet rs =st.executeQuery(qry);
           rs.next() ;
           float amount = 0;
           amount = Float.parseFloat(rs.getString("sum(amount)"));
             System.out.println(amount);
           
           String qry2 = "select Amount from  roomreservation where RRID like '"+rod+"'";
           Statement st2 = con.createStatement();
           ResultSet rs2 =st2.executeQuery(qry2);
           rs2.next();
           float amount2 = 0;
           amount2 = Float.parseFloat(rs2.getString("Amount"));
             System.out.println(amount2);
          
           String qry3 = "select sum(Price) from  roommenu rm,menus m, roomreservation r where rm.RRID  = r.RRID  and rm.MenuId =m.MenuID and r.RRID like '"+rod+"'" ;
           Statement st3 = con.createStatement();
           ResultSet rs3 =st3.executeQuery(qry3);
           rs3.next();
           float amount3 = 0;
           amount3 = Float.parseFloat(rs3.getString("sum(Price)"));
            System.out.println(amount3);      
                  
             float finalA = amount + amount2 + amount3;     
           txt_final.setText(Float.toString(finalA));
                  
                  
                  
           System.out.println(finalA);
                  
                              
        }
        }
         catch(Exception ex){
        
           JOptionPane.showMessageDialog(null, ex);
          // viewleave.setText(" No Leaves ");
           
        }
        
   
    }//GEN-LAST:event_txt_roomIdKeyReleased

    private void txt_hallIdKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_hallIdKeyReleased
        // TODO add your handling code here:
         try{
              String id= "0";
            txt_roomId.setText(id);
            txt_roomId.setEditable(false);
             
             
                String  PATTERN = "^[0-9]{0,10}$";
        Pattern patt = Pattern.compile(PATTERN);
        Matcher match = patt.matcher(txt_hallId.getText());
        
        if(!match.matches()){
              j3.setText("ID is incorrect");
        }
        else{
            j3.setText(null);
        }
             
             int hod = Integer.parseInt(txt_hallId.getText());
               Connection con = MySqlConnection();
           String qry = "select * from  reservation where reservation_ID like '"+hod+"'";
           Statement st = con.createStatement();
           ResultSet rs =st.executeQuery(qry);

                  rs.next() ;
                     

                  int amount = Integer.parseInt(rs.getString("total_amount"));
                    
                   System.out.println(amount);
                  
                   txt_final.setText(Integer.toString(amount));           
        
        }
         catch(Exception ex){
        
           JOptionPane.showMessageDialog(null, ex);
          // viewleave.setText(" No Leaves ");
           
        } 
               
    }//GEN-LAST:event_txt_hallIdKeyReleased

    private void txt_roomIdKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_roomIdKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_roomIdKeyTyped

    private void b10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b10ActionPerformed
        // TODO add your handling code here:
             dispose();
        Home i = new Home();
        i.setVisible(true);
    }//GEN-LAST:event_b10ActionPerformed

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
            java.util.logging.Logger.getLogger(payment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(payment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(payment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(payment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new payment().setVisible(true);
            }
        });
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton b1;
    private javax.swing.JButton b10;
    private javax.swing.JButton b2;
    private javax.swing.JButton b3;
    private javax.swing.JButton b4;
    private javax.swing.JButton b5;
    private javax.swing.JButton b6;
    private javax.swing.JButton b7;
    private javax.swing.JButton b8;
    private javax.swing.JButton b9;
    private javax.swing.JButton btn_Add;
    private javax.swing.JButton btn_delete;
    private javax.swing.JButton btn_update;
    private javax.swing.JLabel j2;
    private javax.swing.JLabel j3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JRadioButton radioCard;
    private javax.swing.JRadioButton radioCash;
    private com.toedter.calendar.JDateChooser txt_date;
    private javax.swing.JTextField txt_final;
    private javax.swing.JTextField txt_hallId;
    private javax.swing.JTextField txt_payId;
    private javax.swing.ButtonGroup txt_paymentType;
    private javax.swing.JTextField txt_roomId;
    private javax.swing.JTextField txt_search;
    private javax.swing.JTextField txt_time;
    // End of variables declaration//GEN-END:variables
}
