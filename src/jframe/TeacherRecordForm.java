/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package jframe;

import DatabaseHandler.DatabaseHandler;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author kazii
 * 
 * 
 */
public class TeacherRecordForm extends javax.swing.JFrame {
    
    String sID,sFname, sLname,sEmail, sPhone,sAddress,sInstitute,sGender,sDepartment;
    int sFine,sIssue;
    String databaseName = "library_ms";
    String tableName = "teachers";
    String pass = "";
    DatabaseHandler db = new DatabaseHandler ();
    DefaultTableModel model;
    
    int calltoUpdate =1;

    
    public TeacherRecordForm() {
        initComponents();
        setTeacherDetailTable(); 
    }
    
    
    //Method That Fetched Data from Database And set It to table
    
    public void setTeacherDetailTable(){
             try {
                 
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306","root",pass);
            if (db.isDatabaseExists(con, databaseName)){
                                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+databaseName, "root", pass);
                                Statement stmt= con.createStatement();
                                String querry = "select * from "+ tableName;
                                ResultSet rset= stmt.executeQuery(querry);

                                while (rset.next()){
                                 String tID= rset.getString(1);
                                String tFirst=rset.getString(2);
                                String tLast = rset.getString(3);
                                String tPhone=rset.getString(5);
                                String tEmail= rset.getString(4);
                                String tAddress=rset.getString(6);
                                String tDepartment=rset.getString(9);
                                String tInstitute=rset.getString(7);
                                String tGender= rset.getString(8);

                                Object [] obj = {tID,tFirst,tLast,tEmail,tPhone,tAddress,tInstitute,tGender,tDepartment,"200","500"};
                               model = (DefaultTableModel) teacherTable.getModel();
                               model.addRow(obj);
                 
                                }
            }
        }catch(Exception e )
        {
                    
        }
          
    }
             
      
    
    //Method That Fetched Detail According to given TeaccherId
    public void searchRecords() {
            String teachertId="";
            try{
                    teachertId = t_id.getText().trim();
                    Integer.parseInt(teachertId);
            
            }catch(Exception e){
                    messageLabel.setText("Please Provide Correct Student ID ");
                    setTeacherDetailTable();
            
            }
           
                try {
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library_ms", "root",pass);
                    String sql = "select * from teachers where t_id= ? ";
                    PreparedStatement pst = con.prepareStatement(sql);
                    pst.setString(1, teachertId);
                    ResultSet rset=pst.executeQuery();
//                  if (rset.next()){
//                    messageLabel.setText("Record not found.");
//                  }
                    while (rset.next()){
                        String tID= rset.getString(1);
                        String tFirstname=rset.getString(2);
                        String tLastname=rset.getString(3);
                        String tPhone=rset.getString(5);
                        String tEmail= rset.getString(4);
                        String tAddress=rset.getString(6);
                        String tDepartment=rset.getString(7);
                        String tInstitute=rset.getString(8);
                        String tGender= rset.getString(9);
                        Object [] obj = {tID,tFirstname,tLastname,tEmail,tPhone,tAddress,tInstitute,tGender,tDepartment,"200","500"};
                        model = (DefaultTableModel) teacherTable.getModel();
                        model.addRow(obj);
}


                }catch(Exception e){
                                e.printStackTrace();
                }
        
            
        
    }
    
    //Method to clear Table
    public void clearTable(){
        DefaultTableModel model = (DefaultTableModel) teacherTable.getModel();
        model.setRowCount(0);
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
        jPanel2 = new javax.swing.JPanel();
        back = new javax.swing.JLabel();
        manageStudent = new necesario.MaterialButton();
        manageTeacher = new necesario.MaterialButton();
        manageCitizen = new necesario.MaterialButton();
        jPanel5 = new javax.swing.JPanel();
        teach_detail1 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        t_id = new app.bolivia.swing.JCTextField();
        search = new necesario.MaterialButton();
        clear = new necesario.MaterialButton();
        messageLabel = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        manage_Books = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        teacherTable = new rojeru_san.complementos.RSTableMetro();
        jPanel4 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Manage Books");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(102, 102, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(102, 102, 255));

        back.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        back.setForeground(new java.awt.Color(255, 255, 255));
        back.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AddNewBookIcons/AddNewBookIcons/icons8_Rewind_48px.png"))); // NOI18N
        back.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                backMouseClicked(evt);
            }
        });
        jPanel2.add(back);

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 80, 60));

        manageStudent.setBackground(new java.awt.Color(255, 255, 255));
        manageStudent.setBorder(null);
        manageStudent.setForeground(new java.awt.Color(102, 102, 255));
        manageStudent.setText("Students");
        manageStudent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                manageStudentActionPerformed(evt);
            }
        });
        jPanel1.add(manageStudent, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 20, 120, 40));

        manageTeacher.setBackground(new java.awt.Color(102, 102, 200));
        manageTeacher.setForeground(new java.awt.Color(255, 255, 255));
        manageTeacher.setText("Teachers");
        manageTeacher.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                manageTeacherActionPerformed(evt);
            }
        });
        jPanel1.add(manageTeacher, new org.netbeans.lib.awtextra.AbsoluteConstraints(1080, 20, 120, 40));

        manageCitizen.setBackground(new java.awt.Color(255, 255, 255));
        manageCitizen.setForeground(new java.awt.Color(102, 102, 255));
        manageCitizen.setText("Citizens");
        manageCitizen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                manageCitizenActionPerformed(evt);
            }
        });
        jPanel1.add(manageCitizen, new org.netbeans.lib.awtextra.AbsoluteConstraints(1210, 20, 120, 40));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 140, 270, 5));

        teach_detail1.setBackground(new java.awt.Color(102, 102, 255));
        teach_detail1.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        teach_detail1.setForeground(new java.awt.Color(255, 255, 255));
        teach_detail1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        teach_detail1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AddNewBookIcons/AddNewBookIcons/icons8_Literature_100px_1.png"))); // NOI18N
        teach_detail1.setText("  Teachers Detail");
        teach_detail1.setAutoscrolls(true);
        jPanel1.add(teach_detail1, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 60, 430, 100));

        jLabel1.setBackground(new java.awt.Color(102, 102, 255));
        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Enter Teacher ID:");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 120, 140, 40));

        t_id.setBackground(new java.awt.Color(255, 255, 255));
        t_id.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(102, 102, 255)));
        t_id.setForeground(new java.awt.Color(0, 0, 0));
        t_id.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        t_id.setPhColor(new java.awt.Color(102, 102, 255));
        t_id.setPlaceholder("Enter Teacher ID...");
        t_id.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                t_idActionPerformed(evt);
            }
        });
        jPanel1.add(t_id, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 120, 220, 40));

        search.setBackground(new java.awt.Color(255, 255, 255));
        search.setForeground(new java.awt.Color(102, 102, 255));
        search.setText("Search");
        search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchActionPerformed(evt);
            }
        });
        jPanel1.add(search, new org.netbeans.lib.awtextra.AbsoluteConstraints(1080, 120, 120, 40));

        clear.setBackground(new java.awt.Color(255, 255, 255));
        clear.setForeground(new java.awt.Color(255, 51, 51));
        clear.setText("Clear");
        clear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearActionPerformed(evt);
            }
        });
        jPanel1.add(clear, new org.netbeans.lib.awtextra.AbsoluteConstraints(1210, 120, 120, 40));

        messageLabel.setBackground(new java.awt.Color(102, 102, 255));
        messageLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        messageLabel.setForeground(new java.awt.Color(255, 51, 51));
        messageLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(messageLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 70, 590, 40));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1370, 200));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        manage_Books.setBackground(new java.awt.Color(255, 255, 255));
        manage_Books.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        manage_Books.setForeground(new java.awt.Color(102, 102, 255));
        manage_Books.setText("Teachers Record:");
        jPanel3.add(manage_Books, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 320, 60));

        jScrollPane1.setBackground(new java.awt.Color(102, 102, 255));
        jScrollPane1.setBorder(null);
        jScrollPane1.setForeground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        teacherTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Teacher ID", "First Name", "Last Name", "Email", "Phone", "Address", "Institute", "Gender", "Department", "Fine", "Issue Books"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        teacherTable.setColorBackgoundHead(new java.awt.Color(102, 102, 255));
        teacherTable.setColorBordeFilas(new java.awt.Color(102, 102, 255));
        teacherTable.setColorSelBackgound(new java.awt.Color(255, 51, 51));
        teacherTable.setFuenteFilas(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        teacherTable.setFuenteFilasSelect(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        teacherTable.setFuenteHead(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        teacherTable.setRowHeight(30);
        teacherTable.setRowMargin(1);
        teacherTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                teacherTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(teacherTable);

        jPanel3.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 1320, 450));

        jPanel4.setBackground(new java.awt.Color(102, 102, 255));
        jPanel3.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 300, 5));

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 200, 1400, 570));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void backMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backMouseClicked
        Home home= new Home();
        home.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_backMouseClicked

    private void teacherTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_teacherTableMouseClicked

      
    }//GEN-LAST:event_teacherTableMouseClicked

    private void manageTeacherActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_manageTeacherActionPerformed
        // TODO add your handling code here:
       
    }//GEN-LAST:event_manageTeacherActionPerformed

    private void manageStudentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_manageStudentActionPerformed
        // TODO add your handling code here:
         StudentRecordForm stu = new StudentRecordForm();
        stu.setVisible(true);
        this.dispose();
        
    }//GEN-LAST:event_manageStudentActionPerformed

    private void manageCitizenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_manageCitizenActionPerformed
        // TODO add your handling code here:
        CitizenRecordForm manageCt= new CitizenRecordForm ();
        manageCt.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_manageCitizenActionPerformed

    private void t_idActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_t_idActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_t_idActionPerformed

    private void searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchActionPerformed
        // TODO add your handling code here:
        clearTable();
        searchRecords();
        
        
        
    }//GEN-LAST:event_searchActionPerformed

    private void clearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearActionPerformed
        // TODO add your handling code here:
        clearTable();
        t_id.setText("");
        setTeacherDetailTable();
        
    }//GEN-LAST:event_clearActionPerformed

    
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
            java.util.logging.Logger.getLogger(TeacherRecordForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TeacherRecordForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TeacherRecordForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TeacherRecordForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TeacherRecordForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel back;
    private necesario.MaterialButton clear;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private necesario.MaterialButton manageCitizen;
    private necesario.MaterialButton manageStudent;
    private necesario.MaterialButton manageTeacher;
    private javax.swing.JLabel manage_Books;
    private javax.swing.JLabel messageLabel;
    private necesario.MaterialButton search;
    private app.bolivia.swing.JCTextField t_id;
    private javax.swing.JLabel teach_detail1;
    private rojeru_san.complementos.RSTableMetro teacherTable;
    // End of variables declaration//GEN-END:variables
}
