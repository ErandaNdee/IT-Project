

import Code.DBconect;
import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Formatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;


public class HallReservation extends javax.swing.JFrame {

    Connection conn = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    
    public HallReservation() {
        initComponents();
        
        conn = DBconect.connect();
        
        tableload();
    }
    
    public void tableload() {
    
        try {
            String sql = "SELECT reservation_ID, Name, Phone, Address, Email, hall_ID, menu_ID, customer_ID, date, time, description, no_of_participant,total_amount FROM reservation r , customer c where c.NIC=r.customer_ID ";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            reservationTable.setModel(DbUtils.resultSetToTableModel(rs));
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    
    }
    
    public void tabaledata() {
        
        int r = reservationTable.getSelectedRow();
        
        String rid = reservationTable.getValueAt(r, 0).toString();
        String name = reservationTable.getValueAt(r, 1).toString();
        String phone = reservationTable.getValueAt(r, 2).toString();
        String address = reservationTable.getValueAt(r, 3).toString();
        String email = reservationTable.getValueAt(r, 4).toString();
        String hid = reservationTable.getValueAt(r, 5).toString();
        String mid = reservationTable.getValueAt(r, 6).toString();
        String cid = reservationTable.getValueAt(r, 7).toString();
        String date = reservationTable.getValueAt(r, 8).toString();
        String time = reservationTable.getValueAt(r, 9).toString();
        String description = reservationTable.getValueAt(r, 10).toString();
        String participant = reservationTable.getValueAt(r, 11).toString();
         
        idBox.setText(rid);
        hallIdBox.setText(hid);
        menuIdBox.setText(mid);
        nicBox.setText(cid);
        dateBox.setText(date);
        timeBox.setText(time);
        descriptionBox.setText(description);
        participantBox.setText(participant);
        nameBox.setText(name);
        phoneBox.setText(phone);
        addressBox.setText(address);
        emailBox.setText(email);
    
    }
    
    public void search() {
    
        String srch = searchBox.getText();
        
        try {
            
            String sql = "SELECT reservation_ID, Name, Phone, Address, Email, hall_ID, menu_ID, customer_ID, date, time, description, no_of_participant,total_amount FROM reservation r , customer c "
                    + "WHERE (r.customer_ID = c.NIC and r.date LIKE '%"+srch+"%') OR (r.customer_ID = c.NIC and c.NIC LIKE '%"+srch+"%')";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            reservationTable.setModel(DbUtils.resultSetToTableModel(rs));
            
        } catch (Exception e) {
            
            JOptionPane.showMessageDialog(null, e);
        }
    
    }
    
    public void update() {
        
        String cName = nameBox.getText();
        String address = addressBox.getText();
        String phone = phoneBox.getText();
        String email = emailBox.getText();
       
   
        String rid = idBox.getText();
        String nic = nicBox.getText();
        String hallID = hallIdBox.getText();
        String date = dateBox.getText();
        String time = timeBox.getText();
        String menuID = menuIdBox.getText();
        String description = descriptionBox.getText();
        int noParticipant = Integer.parseInt(participantBox.getText());
        
        try {
            
            String sqlCus = "UPDATE customer SET NIC = '"+nic+"', Name = '"+cName+"',Phone = '"+phone+"', Address= '"+address+"', Email= '"+email+"' WHERE NIC = '"+nic+"' " ;
            pst = conn.prepareStatement(sqlCus);
            pst.execute(); 
            
            
            /*String sqlRes = "UPDATE reservation SET hall_ID = '"+hallID+"', menu_ID = '"+menuID+"', customer_ID = '"+nic+"', date = '"+date+"' , time = '"+time+"', description = '"+description+"', no_of_participant = '"+noParticipant+"' WHERE reservation_ID = '"+rid+"'";
            pst = conn.prepareStatement(sqlRes);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Updated");*/
            
            pst = conn.prepareStatement("select * from menus where MenuID = '" +menuID+ "' ");
            rs = pst.executeQuery();
            
            while(rs.next()) {
            
                double mprice = rs.getDouble("Price");
                System.out.println(mprice);
                pst = conn.prepareStatement("select * from hall where Hall_ID= '" +hallID+ "'");
                rs = pst.executeQuery();
                rs.next();
                
                double hprice = rs.getDouble("Charge");
                System.out.println(hprice);
                double total = noParticipant * mprice +hprice; 
                System.out.println(total);
               
                
                String sqltot = "UPDATE reservation SET hall_ID = '"+hallID+"', menu_ID = '"+menuID+"', customer_ID = '"+nic+"', date = '"+date+"' , time = '"+time+"', description = '"+description+"', no_of_participant = '"+noParticipant+"' , total_amount='"+total+"' WHERE reservation_ID = '"+rid+"'";
                pst = conn.prepareStatement(sqltot);
                pst.execute();  
            
           }
            
        } catch (Exception e) {
            
            JOptionPane.showMessageDialog(null, e);
        }
        
    }
    
    public void clear() {
    
        searchBox.setText(" ");
        idBox.setText("ID");
        nameBox.setText(" ");
        nicBox.setText(" ");
        phoneBox.setText(" ");
        addressBox.setText(" ");
        emailBox.setText(" ");
        hallIdBox.setText(" ");
        menuIdBox.setText(" ");
        dateBox.setText(" ");
        timeBox.setText(" ");
        descriptionBox.setText(" ");
        participantBox.setText(" ");
        
    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        nicBox = new javax.swing.JTextField();
        addressBox = new javax.swing.JTextField();
        dateBox = new javax.swing.JTextField();
        nameBox = new javax.swing.JTextField();
        phoneBox = new javax.swing.JTextField();
        hallIdBox = new javax.swing.JTextField();
        menuIdBox = new javax.swing.JTextField();
        descriptionBox = new javax.swing.JTextField();
        participantBox = new javax.swing.JTextField();
        emailBox = new javax.swing.JTextField();
        addBtn = new javax.swing.JButton();
        updateBtn = new javax.swing.JButton();
        deleteBtn = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        reservationTable = new javax.swing.JTable();
        searchBox = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        timeBox = new javax.swing.JTextField();
        hall = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        idBox = new javax.swing.JLabel();
        clearBtn = new javax.swing.JButton();
        exis = new javax.swing.JButton();
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
        b10 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setAlignmentX(getAlignmentX());
        jPanel1.setAlignmentY(getAlignmentX());
        jPanel1.setMaximumSize(new java.awt.Dimension(1024, 768));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        jLabel12.setText("HOTEL ARONWAY");
        jPanel3.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 20, -1, -1));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 2060, 120));

        jLabel1.setText("Hall ID");
        jLabel1.setAlignmentX(getAlignmentX());
        jLabel1.setAlignmentY(getAlignmentX());
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 380, 90, -1));

        jLabel2.setText("Customer NIC");
        jLabel2.setAlignmentX(getAlignmentX());
        jLabel2.setAlignmentY(getAlignmentX());
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 260, 90, -1));

        jLabel3.setText("Customer name ");
        jLabel3.setAlignmentX(getAlignmentX());
        jLabel3.setAlignmentY(getAlignmentX());
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 230, 130, -1));

        jLabel4.setText("Address");
        jLabel4.setAlignmentX(getAlignmentX());
        jLabel4.setAlignmentY(getAlignmentX());
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 290, 90, -1));

        jLabel5.setText("Phone number");
        jLabel5.setAlignmentX(getAlignmentX());
        jLabel5.setAlignmentY(getAlignmentX());
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 320, 90, -1));

        jLabel6.setText("Date");
        jLabel6.setAlignmentX(getAlignmentX());
        jLabel6.setAlignmentY(getAlignmentX());
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 410, 90, -1));

        jLabel7.setText("Time");
        jLabel7.setAlignmentX(getAlignmentX());
        jLabel7.setAlignmentY(getAlignmentX());
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 440, 90, -1));

        jLabel8.setText("Description");
        jLabel8.setAlignmentX(getAlignmentX());
        jLabel8.setAlignmentY(getAlignmentX());
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 500, 90, -1));

        jLabel9.setText("Number of participant ");
        jLabel9.setAlignmentX(getAlignmentX());
        jLabel9.setAlignmentY(getAlignmentX());
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 530, 160, -1));

        jLabel10.setText("Email");
        jLabel10.setAlignmentX(getAlignmentX());
        jLabel10.setAlignmentY(getAlignmentX());
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 350, 90, -1));

        nicBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nicBoxActionPerformed(evt);
            }
        });
        jPanel1.add(nicBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 260, 390, -1));
        jPanel1.add(addressBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 290, 390, -1));

        dateBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dateBoxActionPerformed(evt);
            }
        });
        jPanel1.add(dateBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 410, 390, -1));

        nameBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nameBoxActionPerformed(evt);
            }
        });
        jPanel1.add(nameBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 230, 390, -1));

        phoneBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                phoneBoxActionPerformed(evt);
            }
        });
        jPanel1.add(phoneBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 320, 390, -1));
        jPanel1.add(hallIdBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 380, 390, -1));
        jPanel1.add(menuIdBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 470, 390, -1));
        jPanel1.add(descriptionBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 500, 390, -1));
        jPanel1.add(participantBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 530, 390, -1));

        emailBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                emailBoxActionPerformed(evt);
            }
        });
        jPanel1.add(emailBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 350, 390, -1));

        addBtn.setText("NEW CUSTOMER");
        addBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addBtnActionPerformed(evt);
            }
        });
        jPanel1.add(addBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 590, -1, -1));

        updateBtn.setText("UPDATE");
        updateBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateBtnActionPerformed(evt);
            }
        });
        jPanel1.add(updateBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 590, -1, -1));

        deleteBtn.setText("DELETE");
        deleteBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteBtnActionPerformed(evt);
            }
        });
        jPanel1.add(deleteBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 590, -1, -1));

        reservationTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        reservationTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                reservationTableMouseClicked(evt);
            }
        });
        reservationTable.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                reservationTableKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(reservationTable);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 160, 980, 180));

        searchBox.setForeground(new java.awt.Color(153, 153, 153));
        searchBox.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        searchBox.setText("SEARCH");
        searchBox.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                searchBoxFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                searchBoxFocusLost(evt);
            }
        });
        searchBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchBoxActionPerformed(evt);
            }
        });
        searchBox.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                searchBoxKeyReleased(evt);
            }
        });
        jPanel1.add(searchBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 160, 140, -1));

        jLabel11.setText("Menu ID");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 470, -1, -1));
        jPanel1.add(timeBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 440, 390, -1));

        hall.setText("HALL");
        hall.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hallActionPerformed(evt);
            }
        });
        jPanel1.add(hall, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 160, -1, -1));

        jLabel13.setText("Reservation ID");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 200, -1, -1));

        idBox.setText("ID");
        jPanel1.add(idBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 200, -1, -1));

        clearBtn.setText("CLEAR");
        clearBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearBtnActionPerformed(evt);
            }
        });
        jPanel1.add(clearBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 590, -1, -1));

        exis.setText("EXISITING CUSTOMER");
        exis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exisActionPerformed(evt);
            }
        });
        jPanel1.add(exis, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 650, -1, -1));

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

        b10.setBackground(new java.awt.Color(0, 102, 102));
        b10.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        b10.setForeground(new java.awt.Color(255, 255, 255));
        b10.setText("Home");
        b10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b10ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(b5, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(b6, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(b2, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(b8, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(b1, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(b9, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(b4, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(b3, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(b7, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(b10, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(b10, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(b6, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(b2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(b3, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(b4, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(b7, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(b5, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(b8, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(b1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(b9, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(629, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 120, 180, 1200));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 2060, 1320));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void nameBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nameBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nameBoxActionPerformed

    private void phoneBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_phoneBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_phoneBoxActionPerformed

    private void emailBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_emailBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_emailBoxActionPerformed

    private void dateBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dateBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dateBoxActionPerformed

    private void addBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addBtnActionPerformed
        String cName;
        String nic;
        String address;
        int phone;
        String email;
        String hallID;
        String date;
        String time;
        String menuID;
        String description;
        int noParticipant;
        
        cName = nameBox.getText();
        nic = nicBox.getText();
        address = addressBox.getText();
        phone = Integer.parseInt(phoneBox.getText());
        email = emailBox.getText();
        hallID = hallIdBox.getText();
        date = dateBox.getText();
        time = timeBox.getText();
        menuID = menuIdBox.getText();
        description = descriptionBox.getText();
        noParticipant = Integer.parseInt(participantBox.getText());
        
        try {
            String sqlCus = "INSERT INTO customer(NIC, Name, Phone, Address, Email) VALUES('"+nic+"', '"+cName+"', '"+phone+"', '"+address+"', '"+email+"')";
            pst = conn.prepareStatement(sqlCus);
            pst.execute();       
                 
            
            pst = conn.prepareStatement("select * from menus where MenuID = '" +menuID+ "' ");
            rs = pst.executeQuery();
            
            while(rs.next()) {
            
                double mprice = rs.getDouble("Price");
                pst = conn.prepareStatement("select * from hall where Hall_ID= '" +hallID+ "'");
                rs = pst.executeQuery();
                rs.next();
                
                double hprice = rs.getDouble("Charge");
                double total = noParticipant * mprice +hprice; 

                
                String sqltot = "INSERT INTO reservation (hall_ID, menu_ID, customer_ID, date, time, description, no_of_participant,total_amount) VALUES ('"+hallID+"', '"+menuID+"', '"+nic+"', '"+date+"', '"+time+"', '"+description+"', '"+noParticipant+"', '"+total+"')";
                pst = conn.prepareStatement(sqltot);
                pst.execute();  
            
           }
            
            
           
        }catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        
        tableload();
        clear();
    }//GEN-LAST:event_addBtnActionPerformed

    private void nicBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nicBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nicBoxActionPerformed

    private void updateBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateBtnActionPerformed
        update();
        tableload();
        clear();
    }//GEN-LAST:event_updateBtnActionPerformed

    private void searchBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchBoxActionPerformed

    private void searchBoxFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_searchBoxFocusGained
        if(searchBox.getText().equals("SEARCH")){
            searchBox.setText("");
            searchBox.setForeground(new Color(153,153,153 )); 
        }
    }//GEN-LAST:event_searchBoxFocusGained

    private void searchBoxFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_searchBoxFocusLost
        if(searchBox.getText().equals("")){
            searchBox.setText("SEARCH");
            searchBox.setForeground(new Color(153,153,153 )); 
        }
    }//GEN-LAST:event_searchBoxFocusLost

    private void hallActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hallActionPerformed
         
        hall h = new hall();
        h.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_hallActionPerformed

    private void reservationTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_reservationTableMouseClicked
        tabaledata();
    }//GEN-LAST:event_reservationTableMouseClicked

    private void reservationTableKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_reservationTableKeyReleased
        tabaledata();
    }//GEN-LAST:event_reservationTableKeyReleased

    private void searchBoxKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchBoxKeyReleased
        search();
    }//GEN-LAST:event_searchBoxKeyReleased

    private void deleteBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteBtnActionPerformed
        
        int check = JOptionPane.showConfirmDialog(null, "Do you want to delete?");  
        
        if(check ==0){
            
            String nic = nicBox.getText();
            String rid = idBox.getText();
            
            try {
                
                String sqlRes = "DELETE FROM reservation WHERE reservation_ID = '"+rid+"'";
                pst = conn.prepareStatement(sqlRes);
                pst.execute();
                
               /* String sqlCus = "DELETE FROM customer WHERE NIC = '"+nic+"'";
                pst = conn.prepareStatement(sqlCus);
                pst.execute();*/
                
                JOptionPane.showMessageDialog(null, "Reservation deleted");
                
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
            
        }
        tableload();
    }//GEN-LAST:event_deleteBtnActionPerformed

    private void clearBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearBtnActionPerformed
        clear();
    }//GEN-LAST:event_clearBtnActionPerformed

    private void exisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exisActionPerformed
        
        String cName;
        String nic;
        String address;
        int phone;
        String email;
        String hallID;
        String date;
        String time;
        String menuID;
        String description;
        int noParticipant;
        
        cName = nameBox.getText();
        nic = nicBox.getText();
        address = addressBox.getText();
        phone = Integer.parseInt(phoneBox.getText());
        email = emailBox.getText();
        hallID = hallIdBox.getText();
        date = dateBox.getText();
        time = timeBox.getText();
        menuID = menuIdBox.getText();
        description = descriptionBox.getText();
        noParticipant = Integer.parseInt(participantBox.getText());
        
        try {
            pst = conn.prepareStatement("select * from menus where MenuID = '" +menuID+ "' ");
            rs = pst.executeQuery();
            
            while(rs.next()) {
            
                double mprice = rs.getDouble("Price");
                pst = conn.prepareStatement("select * from hall where Hall_ID= '" +hallID+ "'");
                rs = pst.executeQuery();
                rs.next();
                
                double hprice = rs.getDouble("Charge");
                double total = noParticipant * mprice +hprice; 

                
                String sqltot = "INSERT INTO reservation (hall_ID, menu_ID, customer_ID, date, time, description, no_of_participant,total_amount) VALUES ('"+hallID+"', '"+menuID+"', '"+nic+"', '"+date+"', '"+time+"', '"+description+"', '"+noParticipant+"', '"+total+"')";
                pst = conn.prepareStatement(sqltot);
                pst.execute();  
            
           }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        
        tableload();
        clear();
    }//GEN-LAST:event_exisActionPerformed

    private void b1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_b1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_b1MouseClicked

    private void b1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b1ActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_b1ActionPerformed

    private void b2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b2ActionPerformed
        // TODO add your handling code here:

        HallReservation h = new HallReservation();
        h.setVisible(true);

    }//GEN-LAST:event_b2ActionPerformed

    private void b3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b3ActionPerformed

        availablerooms r = new availablerooms();
        r.setVisible(true);
    }//GEN-LAST:event_b3ActionPerformed

    private void b4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b4ActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_b4ActionPerformed

    private void b5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b5ActionPerformed
        // TODO add your handling code here:

        payment p = new payment();
        p.setVisible(true);

    }//GEN-LAST:event_b5ActionPerformed

    private void b6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b6ActionPerformed
        // TODO add your handling code here:

        ingridient i = new  ingridient();
        i.setVisible(true);

    }//GEN-LAST:event_b6ActionPerformed

    private void b7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_b7ActionPerformed

    private void b8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b8ActionPerformed
        // TODO add your handling code here:

        travelInterface t = new travelInterface();
        t.setVisible(true);
    }//GEN-LAST:event_b8ActionPerformed

    private void b9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_b9ActionPerformed

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
            java.util.logging.Logger.getLogger(HallReservation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HallReservation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HallReservation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HallReservation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new HallReservation().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addBtn;
    private javax.swing.JTextField addressBox;
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
    private javax.swing.JButton clearBtn;
    private javax.swing.JTextField dateBox;
    private javax.swing.JButton deleteBtn;
    private javax.swing.JTextField descriptionBox;
    private javax.swing.JTextField emailBox;
    private javax.swing.JButton exis;
    private javax.swing.JButton hall;
    private javax.swing.JTextField hallIdBox;
    private javax.swing.JLabel idBox;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField menuIdBox;
    private javax.swing.JTextField nameBox;
    private javax.swing.JTextField nicBox;
    private javax.swing.JTextField participantBox;
    private javax.swing.JTextField phoneBox;
    private javax.swing.JTable reservationTable;
    private javax.swing.JTextField searchBox;
    private javax.swing.JTextField timeBox;
    private javax.swing.JButton updateBtn;
    // End of variables declaration//GEN-END:variables
}
