


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import net.proteanit.sql.DbUtils;


public class roomsinterface extends javax.swing.JFrame {

    
    public roomsinterface() {
        initComponents();
        Show_rooms_in_table();
        
        
       }

    
    
    //Database Connectiom
    
    public Connection getConnection(){
    
        Connection con;
        try{
        
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/itp", "root", "");
            return con;
        }catch (Exception e){
        
            e.printStackTrace();
            return null;
        }
    }
    
    //retrive and store data in a arraylist
    
    public ArrayList<Rooms> getroomList(){
    
        ArrayList<Rooms> roomList = new ArrayList<Rooms>();
        Connection connection = getConnection();
        
        String query = "Select * from room";
        Statement st;
        ResultSet rs;
        
        try{
        
            st = connection.createStatement();
            rs= st.executeQuery(query);
            Rooms rooms;
            while(rs.next()){
            
                rooms = new Rooms(rs.getInt("RoomNo"),rs.getString("Type"),rs.getString("Bed"),rs.getDouble("Price"));
                roomList.add(rooms);
            }
        }catch(Exception e){
        
            e.printStackTrace();
        }
        return roomList;
    }
    
    
    //Display data in table
    
    public void Show_rooms_in_table(){
    
        ArrayList<Rooms> rList = getroomList();
        DefaultTableModel model = (DefaultTableModel)rooms_jTable.getModel();
        
        Object[] row = new Object[4];
        for(int i = 0; i < rList.size(); i++){
        
            row[0] = rList.get(i).getRoomNumber();
            row[1] = rList.get(i).getRoomType();
            row[2] = rList.get(i).getBedType();
            row[3] = rList.get(i).getRoomPrice();
            
            model.addRow(row);
        }
    }
    
    public void executeSqlQuery(String query, String message){
    
        Connection con = getConnection();
        Statement st;
        try{
            st = con.createStatement();
            if((st.executeUpdate(query))==1){
                
                JOptionPane.showMessageDialog(null,"Data "+message+" Successfully");
            }else{
            
                JOptionPane.showMessageDialog(null,"Data not "+message);
            }
        }catch(Exception e){
        
            e.printStackTrace();
        }
    } 
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        roomtypegroup = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        roomNumber = new javax.swing.JTextField();
        bedType = new javax.swing.JComboBox<>();
        addRoom = new javax.swing.JButton();
        saveRoomChanges = new javax.swing.JButton();
        roomPrice = new javax.swing.JTextField();
        jRadioButtonAC = new javax.swing.JRadioButton();
        jRadioButtonNonAC = new javax.swing.JRadioButton();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        rooms_jTable = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jButton12 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jTextFieldsearchRoom = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Room", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 18))); // NOI18N
        jPanel1.setPreferredSize(new java.awt.Dimension(344, 315));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Room Number");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Room Type");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Bed Type");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("Price");

        roomNumber.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                roomNumberActionPerformed(evt);
            }
        });

        bedType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select", "Twin", "Double", "Queen", "King" }));

        addRoom.setText("Add ");
        addRoom.setPreferredSize(new java.awt.Dimension(67, 23));
        addRoom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addRoomActionPerformed(evt);
            }
        });

        saveRoomChanges.setText("Update");
        saveRoomChanges.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveRoomChangesActionPerformed(evt);
            }
        });

        roomtypegroup.add(jRadioButtonAC);
        jRadioButtonAC.setText("A/C");
        jRadioButtonAC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonACActionPerformed(evt);
            }
        });

        roomtypegroup.add(jRadioButtonNonAC);
        jRadioButtonNonAC.setText("Non A/C");
        jRadioButtonNonAC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonNonACActionPerformed(evt);
            }
        });

        jButton1.setText("Clear");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(18, 18, 18))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(32, 32, 32))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(61, 61, 61)))
                        .addGap(18, 47, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(bedType, 0, 134, Short.MAX_VALUE)
                                .addComponent(roomNumber, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(roomPrice))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jRadioButtonAC)
                                .addGap(18, 18, 18)
                                .addComponent(jRadioButtonNonAC)))
                        .addGap(23, 23, 23))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(addRoom, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(saveRoomChanges)
                        .addGap(28, 28, 28)
                        .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE)
                        .addGap(43, 43, 43))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(roomNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jRadioButtonAC)
                    .addComponent(jRadioButtonNonAC))
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bedType))
                .addGap(38, 38, 38)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(roomPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addRoom, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(saveRoomChanges, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        rooms_jTable.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        rooms_jTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "RoomNo", "Type", "Bed", "Price"
            }
        ));
        rooms_jTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rooms_jTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(rooms_jTable);

        jPanel2.setBackground(new java.awt.Color(0, 153, 102));

        jButton12.setText("Expenses");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addComponent(jButton12)
                .addContainerGap(137, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton12)
                .addGap(21, 21, 21))
        );

        jPanel3.setBackground(new java.awt.Color(51, 0, 153));
        jPanel3.setAlignmentX(0.0F);
        jPanel3.setAlignmentY(0.0F);

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        jLabel7.setText("HOTEL ARONWAY");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(472, 472, 472)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 445, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(48, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Search", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 18))); // NOI18N

        jTextFieldsearchRoom.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldsearchRoomKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(46, Short.MAX_VALUE)
                .addComponent(jTextFieldsearchRoom, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextFieldsearchRoom, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(36, Short.MAX_VALUE))
        );

        jButton2.setText("Handle Reservationa");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Available Rooms");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(104, 104, 104)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(24, 24, 24)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1127, Short.MAX_VALUE)
                                .addGap(32, 32, 32))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton3)
                                .addGap(32, 32, 32)
                                .addComponent(jButton2)
                                .addGap(67, 67, 67))))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(66, 66, 66)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton3)
                            .addComponent(jButton2))
                        .addGap(74, 74, 74)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 684, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(1178, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(234, 234, 234)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 329, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void addRoomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addRoomActionPerformed
        
       
        String bedtype = bedType.getSelectedItem().toString();
        
        jRadioButtonAC.setActionCommand("A/C");
        jRadioButtonNonAC.setActionCommand("Non A/C");
        
        String roomtype = roomtypegroup.getSelection().getActionCommand();
        
         Connection con = getConnection();
         Statement st ;
         String query1 = "select count(*) from room where RoomNo='"+roomNumber.getText()+"'";
           try{
               st = con.createStatement();
               ResultSet rs = st.executeQuery(query1);
               
               if(rs.next()==false){
                 JOptionPane.showMessageDialog(null,"Room exist!");
               }
               else{
                   
               
                    String query = "INSERT INTO `room`(`RoomNo`, `Type`, `Bed`, `Price`) VALUES ('"+roomNumber.getText()+"','"+roomtype+"','"+bedtype+"','"+roomPrice.getText()+"')";
                    executeSqlQuery(query,"Inserted");
                    DefaultTableModel model = (DefaultTableModel)rooms_jTable.getModel();
                    model.setRowCount(0);
                    Show_rooms_in_table();
                   
               }
         }catch(Exception e){
             e.printStackTrace();
             
         }
         
    }//GEN-LAST:event_addRoomActionPerformed

    private void rooms_jTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rooms_jTableMouseClicked
       
        int i = rooms_jTable.getSelectedRow();
        TableModel model = rooms_jTable.getModel();
        roomNumber.setText(model.getValueAt(i, 0).toString());
        String roomtype = model.getValueAt(i, 1).toString();
            
              if(roomtype.equals("A/C")){
              
                  jRadioButtonAC.setSelected(true);
              }
              else{
              
                  jRadioButtonNonAC.setSelected(true);
              }
        
        String bedtype = model.getValueAt(i, 2).toString();
        
               switch(bedtype){
               
                   case "Twin":
                       bedType.setSelectedIndex(1);
                       break;
                   case "Double":
                       bedType.setSelectedIndex(2);
                       break;   
                   case "Queen":
                       bedType.setSelectedIndex(3);
                       break;    
                   case "King":
                       bedType.setSelectedIndex(4);
                       break;    
               }   
        roomPrice.setText(model.getValueAt(i, 3).toString());
        roomPrice.setText(model.getValueAt(i, 3).toString());
        
        addRoom.setEnabled(false);
    }//GEN-LAST:event_rooms_jTableMouseClicked

    private void saveRoomChangesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveRoomChangesActionPerformed
       
        jRadioButtonAC.setActionCommand("A/C");
        jRadioButtonNonAC.setActionCommand("Non A/C");
        String roomtype = roomtypegroup.getSelection().getActionCommand();
        
        
        String query = "update room set Price='"+roomPrice.getText()+"',Type='"+roomtype+"',Bed='"+bedType.getSelectedItem().toString()+"' where RoomNo='"+roomNumber.getText()+"'";
        
        executeSqlQuery(query,"Updated");
        DefaultTableModel model = (DefaultTableModel)rooms_jTable.getModel();
        model.setRowCount(0);
        Show_rooms_in_table();
        
       
    }//GEN-LAST:event_saveRoomChangesActionPerformed

    public void search(){
    
        String search = jTextFieldsearchRoom.getText();
        Connection connection = getConnection();
        
        try{
        
            String query2 = "SELECT * FROM room WHERE RoomNo like '%"+search+"%' or Type like '%"+search+"%' or Bed like '%"+search+"%'";
             Statement st2 = connection.createStatement();
            ResultSet rs2 = st2.executeQuery(query2);
            rooms_jTable.setModel(DbUtils.resultSetToTableModel(rs2));
        }catch(Exception e){
        
            e.printStackTrace();
        }
    }
    private void roomNumberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_roomNumberActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_roomNumberActionPerformed

    private void jRadioButtonACActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonACActionPerformed
        
       
          
    }//GEN-LAST:event_jRadioButtonACActionPerformed

    private void jRadioButtonNonACActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonNonACActionPerformed
        
        
                
    }//GEN-LAST:event_jRadioButtonNonACActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        
        roomNumber.setText(null);
        roomtypegroup.clearSelection();
        bedType.setSelectedIndex(0);
        roomPrice.setText(null);
        
        addRoom.setEnabled(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTextFieldsearchRoomKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldsearchRoomKeyReleased
        
        search();
    }//GEN-LAST:event_jTextFieldsearchRoomKeyReleased

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
         availablerooms ai = new availablerooms();
        ai.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        reservationsinterface ri = new reservationsinterface();
        ri.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    
    public static void main(String args[]) {
       
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new roomsinterface().setVisible(true);
            }
        });
    }
    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addRoom;
    private javax.swing.JComboBox<String> bedType;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JRadioButton jRadioButtonAC;
    private javax.swing.JRadioButton jRadioButtonNonAC;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextFieldsearchRoom;
    private javax.swing.JTextField roomNumber;
    private javax.swing.JTextField roomPrice;
    private javax.swing.JTable rooms_jTable;
    private javax.swing.ButtonGroup roomtypegroup;
    private javax.swing.JButton saveRoomChanges;
    // End of variables declaration//GEN-END:variables
}
