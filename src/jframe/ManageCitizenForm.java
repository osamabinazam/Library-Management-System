/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package jframe;

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
public class ManageCitizenForm extends javax.swing.JFrame {
    
    String cID,cFname, cLname,cCnic, cPhone,cAddress,cGender;
    int sFine,sIssue;
   
    DefaultTableModel model;
    
    int calltoUpdate =1;

    
    public ManageCitizenForm() {
        initComponents();
        setCitizenDetailTable(); 
    }
    
    
    //Method That Fetched Data from Database And set It to table
    
    public void setCitizenDetailTable(){
        try {
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/library_ms","root","toor"); 
           
             Statement stmt= con.createStatement();
             ResultSet rset= stmt.executeQuery("select * from citizen");
             
             while (rset.next()){
               
                 String cID= rset.getString(1);
                 String cFullname=rset.getString(2)+ rset.getString(3);
                 String cPhone=rset.getString(4);
                 String cCnic= rset.getString(5);
                 String cAddress=rset.getString(6);
                 
                 String cGender= rset.getString(7);
                 

                 Object [] obj = {cID,cFullname,cCnic,cPhone,cAddress,cGender,"200","500"};
//                Object [] obj = {"1","2","3","4","5","200","500"};
                model = (DefaultTableModel) citizenTable.getModel();
                model.addRow(obj);
                 
             }
             
             
             
        }catch(Exception e )
        {
            e.printStackTrace();
        }
    }
    //To add book to booktable
    public boolean addCitizen(){
        boolean isAdded=false;
        boolean checkException=true;
        
        if (c_first.getText().isEmpty() || c_last.getText().isEmpty() || c_cnic.getText().isEmpty() || c_gender.getText().isEmpty() || c_id.getText().isEmpty() || c_phone.getText().isEmpty() || c_address.getText().isEmpty()){
            messageLabel.setText("Please Provide Values To Fields, Thank You");
            return isAdded;
        }
        else{
        
        
        cID= c_id.getText();
        cFname=c_first.getText();
        cLname= c_last.getText();
        cCnic= c_cnic.getText();
        cPhone=c_phone.getText();
        cAddress=c_address.getText();
        cGender=c_gender.getText();
        
//        sDepartment=t_department.getText();
//        sGender=t_gender.getText();
        if (checkException) {
            try {

                Connection conc = DriverManager.getConnection("jdbc:mysql://localhost:3306/library_ms", "root", "toor");
                String sql = "insert into citizen values (?,?,?,?,?,?,?,?,?)";
                PreparedStatement pst = conc.prepareStatement(sql);
                pst.setString(1,    cID );
                pst.setString(2, cFname);
                pst.setString(3, cLname);
                pst.setString(4, cPhone);
                pst.setString(5, cCnic);
                pst.setString(6, cAddress );
                pst.setString(7, cGender);
                pst.setInt(8, 20);
                pst.setInt(9, 2);
//                pst.setString(8, sInstitute);
//                pst.setString(9, sGender);

                int rowcount = pst.executeUpdate();
                if (rowcount > 0) {
                    isAdded = true;
                } else {
                    isAdded = false;
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        System.out.println(isAdded);
           
        
     return isAdded;
     }
    }
    
    //Method to clear Table
    public void clearTable(){
        DefaultTableModel model = (DefaultTableModel) citizenTable.getModel();
        model.setRowCount(0);
    }
    
    //Update Book Method
    
    public boolean updateCitizen (){
        
        System.out.println("Update Call:"+calltoUpdate++);
        
        boolean isUpdated=false;
        cID = c_id.getText();
        cFname=c_first.getText();
        cLname= c_last.getText();
        cCnic= c_cnic.getText();
        cPhone=c_phone.getText();
        cAddress=c_address.getText();
        cGender=c_gender.getText();
//        sDepartment=t_department.getText();
//        sGender=t_gender.getText();
        
        try{
            Connection conc=DriverManager.getConnection("jdbc:mysql://localhost:3306/library_ms","root","toor");
           String sql = "update citizen set fname = ?, lname = ? , phone = ? , cnic = ?, address = ? ,  gender =  ? ";
           PreparedStatement pst = conc.prepareStatement(sql);
           pst.setString(1, cFname);
           pst.setString(2, cLname);
           pst.setString(3, cPhone);
           pst.setString(4, cCnic);
           pst.setString(5, cAddress);
           pst.setString(6, cGender);
//           pst.setString(7, sInstitute);
//           pst.setString(8, sGender);
//            
            int rowcount = pst.executeUpdate();
            if(rowcount> 0){
                isUpdated = true;
           }
           else {
               isUpdated=false;
            }           
        }catch(Exception e ){
            e.printStackTrace();
        }
        return isUpdated;
    }
    
//    
//    //Method to  delete Book
    
    public boolean deleteCitizen(){
        boolean isDeleted = false;
          cID= c_id.getText();
          
          try{
             Connection conc=DriverManager.getConnection("jdbc:mysql://localhost:3306/library_ms","root","toor");
            String sql = "delete from citizen where cid = ? ";
            PreparedStatement pst = conc.prepareStatement(sql);
            pst.setString(1,cID);
            
            int countRow = pst.executeUpdate();
            if(countRow>0){
                isDeleted=true;
            }else{
                isDeleted=false;
            }
            
          }catch (Exception e ){
              
          }
        
        return isDeleted;
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
        c_id = new app.bolivia.swing.JCTextField();
        c_first = new app.bolivia.swing.JCTextField();
        c_last = new app.bolivia.swing.JCTextField();
        c_cnic = new app.bolivia.swing.JCTextField();
        c_phone = new app.bolivia.swing.JCTextField();
        c_address = new app.bolivia.swing.JCTextField();
        tidLabel = new javax.swing.JLabel();
        fnameLabel = new javax.swing.JLabel();
        lnameLabel = new javax.swing.JLabel();
        ccnicLabel = new javax.swing.JLabel();
        cphoneLabel = new javax.swing.JLabel();
        caddressLabel = new javax.swing.JLabel();
        c_gender = new app.bolivia.swing.JCTextField();
        cinstituteLabel = new javax.swing.JLabel();
        delete_teacher = new necesario.MaterialButton();
        add_teacher = new necesario.MaterialButton();
        update_teacher = new necesario.MaterialButton();
        messageLabel = new javax.swing.JLabel();
        teach_detail = new javax.swing.JLabel();
        clear = new necesario.MaterialButton();
        jPanel3 = new javax.swing.JPanel();
        manage_Books = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        citizenTable = new rojeru_san.complementos.RSTableMetro();
        manageTeacher = new necesario.MaterialButton();
        manageCitizen = new necesario.MaterialButton();
        manageStudent = new necesario.MaterialButton();
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

        c_id.setBackground(new java.awt.Color(102, 102, 255));
        c_id.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        c_id.setForeground(new java.awt.Color(255, 255, 255));
        c_id.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        c_id.setPlaceholder("Enter Teacher ID...");
        c_id.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                c_idActionPerformed(evt);
            }
        });
        jPanel1.add(c_id, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 290, 240, 25));

        c_first.setBackground(new java.awt.Color(102, 102, 255));
        c_first.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        c_first.setForeground(new java.awt.Color(255, 255, 255));
        c_first.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        c_first.setPlaceholder("Enter First Name...");
        c_first.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                c_firstActionPerformed(evt);
            }
        });
        jPanel1.add(c_first, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 330, 240, 25));

        c_last.setBackground(new java.awt.Color(102, 102, 255));
        c_last.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        c_last.setForeground(new java.awt.Color(255, 255, 255));
        c_last.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        c_last.setPlaceholder("Enter Last Name...");
        c_last.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                c_lastActionPerformed(evt);
            }
        });
        jPanel1.add(c_last, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 370, 240, 25));

        c_cnic.setBackground(new java.awt.Color(102, 102, 255));
        c_cnic.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        c_cnic.setForeground(new java.awt.Color(255, 255, 255));
        c_cnic.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        c_cnic.setPlaceholder("Enter Email...");
        c_cnic.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                c_cnicActionPerformed(evt);
            }
        });
        jPanel1.add(c_cnic, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 410, 240, 25));

        c_phone.setBackground(new java.awt.Color(102, 102, 255));
        c_phone.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        c_phone.setForeground(new java.awt.Color(255, 255, 255));
        c_phone.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        c_phone.setPlaceholder("Enter Phone...");
        c_phone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                c_phoneActionPerformed(evt);
            }
        });
        jPanel1.add(c_phone, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 450, 240, 25));

        c_address.setBackground(new java.awt.Color(102, 102, 255));
        c_address.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        c_address.setForeground(new java.awt.Color(255, 255, 255));
        c_address.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        c_address.setPlaceholder("Enter Address...");
        c_address.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                c_addressActionPerformed(evt);
            }
        });
        jPanel1.add(c_address, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 490, 240, 25));

        tidLabel.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        tidLabel.setForeground(new java.awt.Color(255, 255, 255));
        tidLabel.setText("Citizen ID:");
        jPanel1.add(tidLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 290, 100, -1));

        fnameLabel.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        fnameLabel.setForeground(new java.awt.Color(255, 255, 255));
        fnameLabel.setText("First Name:");
        jPanel1.add(fnameLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 330, 100, -1));

        lnameLabel.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lnameLabel.setForeground(new java.awt.Color(255, 255, 255));
        lnameLabel.setText("Last Name:");
        jPanel1.add(lnameLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 370, 100, -1));

        ccnicLabel.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        ccnicLabel.setForeground(new java.awt.Color(255, 255, 255));
        ccnicLabel.setText("National CNIC:");
        jPanel1.add(ccnicLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 410, 110, -1));

        cphoneLabel.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        cphoneLabel.setForeground(new java.awt.Color(255, 255, 255));
        cphoneLabel.setText("Phone No:");
        jPanel1.add(cphoneLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 450, 100, -1));

        caddressLabel.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        caddressLabel.setForeground(new java.awt.Color(255, 255, 255));
        caddressLabel.setText("Address:");
        jPanel1.add(caddressLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 490, 120, -1));

        c_gender.setBackground(new java.awt.Color(102, 102, 255));
        c_gender.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        c_gender.setForeground(new java.awt.Color(255, 255, 255));
        c_gender.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        c_gender.setPlaceholder("Enter Institute...");
        c_gender.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                c_genderActionPerformed(evt);
            }
        });
        jPanel1.add(c_gender, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 530, 240, 25));

        cinstituteLabel.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        cinstituteLabel.setForeground(new java.awt.Color(255, 255, 255));
        cinstituteLabel.setText("Gender");
        jPanel1.add(cinstituteLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 530, 100, -1));

        delete_teacher.setBackground(new java.awt.Color(255, 51, 0));
        delete_teacher.setForeground(new java.awt.Color(0, 0, 0));
        delete_teacher.setLabel("Delete");
        delete_teacher.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                delete_teacherActionPerformed(evt);
            }
        });
        jPanel1.add(delete_teacher, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 630, 110, -1));

        add_teacher.setBackground(new java.awt.Color(255, 255, 255));
        add_teacher.setForeground(new java.awt.Color(0, 0, 0));
        add_teacher.setLabel("Add");
        add_teacher.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                add_teacherActionPerformed(evt);
            }
        });
        jPanel1.add(add_teacher, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 630, 110, -1));

        update_teacher.setBackground(new java.awt.Color(255, 255, 255));
        update_teacher.setForeground(new java.awt.Color(0, 0, 0));
        update_teacher.setLabel("Update");
        update_teacher.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                update_teacherActionPerformed(evt);
            }
        });
        jPanel1.add(update_teacher, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 630, 120, 40));

        messageLabel.setBackground(new java.awt.Color(102, 102, 255));
        messageLabel.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        messageLabel.setForeground(new java.awt.Color(255, 51, 0));
        messageLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        messageLabel.setAutoscrolls(true);
        jPanel1.add(messageLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 370, 40));

        teach_detail.setBackground(new java.awt.Color(102, 102, 255));
        teach_detail.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        teach_detail.setForeground(new java.awt.Color(51, 51, 51));
        teach_detail.setText(" Citizen Detail:");
        teach_detail.setAutoscrolls(true);
        jPanel1.add(teach_detail, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, 360, 50));

        clear.setBackground(new java.awt.Color(255, 255, 255));
        clear.setForeground(new java.awt.Color(0, 0, 0));
        clear.setText("Clear");
        clear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearActionPerformed(evt);
            }
        });
        jPanel1.add(clear, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 690, 110, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 390, 770));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        manage_Books.setBackground(new java.awt.Color(255, 255, 255));
        manage_Books.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        manage_Books.setForeground(new java.awt.Color(255, 0, 0));
        manage_Books.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AddNewBookIcons/AddNewBookIcons/icons8_Student_Male_100px.png"))); // NOI18N
        manage_Books.setText("Manage Citizens");
        jPanel3.add(manage_Books, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, 400, 90));

        jScrollPane1.setBackground(new java.awt.Color(102, 102, 255));
        jScrollPane1.setBorder(null);
        jScrollPane1.setForeground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        citizenTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Citizen ID", "Full Name ", "CNIC", "Phone", "Address", "Gender", "Fine", "Issue Books"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        citizenTable.setColorBackgoundHead(new java.awt.Color(102, 102, 255));
        citizenTable.setColorBordeFilas(new java.awt.Color(102, 102, 255));
        citizenTable.setColorSelBackgound(new java.awt.Color(255, 51, 51));
        citizenTable.setFuenteFilas(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        citizenTable.setFuenteFilasSelect(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        citizenTable.setFuenteHead(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        citizenTable.setRowHeight(30);
        citizenTable.setRowMargin(1);
        citizenTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                citizenTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(citizenTable);

        jPanel3.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 240, 940, 490));

        manageTeacher.setBackground(new java.awt.Color(255, 255, 255));
        manageTeacher.setBorder(null);
        manageTeacher.setForeground(new java.awt.Color(0, 0, 0));
        manageTeacher.setText("Teachers");
        manageTeacher.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                manageTeacherActionPerformed(evt);
            }
        });
        jPanel3.add(manageTeacher, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 40, 150, 50));

        manageCitizen.setBackground(new java.awt.Color(0, 0, 0));
        manageCitizen.setBorder(null);
        manageCitizen.setForeground(new java.awt.Color(255, 255, 255));
        manageCitizen.setText("Citizens");
        jPanel3.add(manageCitizen, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 40, 140, 50));

        manageStudent.setBackground(new java.awt.Color(255, 255, 255));
        manageStudent.setBorder(null);
        manageStudent.setForeground(new java.awt.Color(0, 0, 0));
        manageStudent.setText("Students");
        manageStudent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                manageStudentActionPerformed(evt);
            }
        });
        jPanel3.add(manageStudent, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 40, 150, 50));

        jPanel4.setBackground(new java.awt.Color(255, 0, 0));
        jPanel3.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 220, 390, 5));

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 0, 1010, 770));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void delete_teacherActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_delete_teacherActionPerformed
        // TODO add your handling code here:
        if(deleteCitizen()){
            messageLabel.setText("Book deleted Successfully");
            clearTable();
            setCitizenDetailTable();
        }
        
        
        
        
        
    }//GEN-LAST:event_delete_teacherActionPerformed

    private void update_teacherActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_update_teacherActionPerformed
        // TODO add your handling code here:
        
           if(updateCitizen()){
            messageLabel.setText("Book Updated Successfully");
            clearTable();
            setCitizenDetailTable();
        }
        else{
            messageLabel.setText("Error!, Can't Update Book");
        }
    }//GEN-LAST:event_update_teacherActionPerformed

    private void add_teacherActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_add_teacherActionPerformed
        if(addCitizen()){
            messageLabel.setText("Student Record Added Successfully");
            clearTable();
            setCitizenDetailTable();
        }
       
        
    }//GEN-LAST:event_add_teacherActionPerformed

    private void c_genderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_c_genderActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_c_genderActionPerformed

    private void c_addressActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_c_addressActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_c_addressActionPerformed

    private void c_phoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_c_phoneActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_c_phoneActionPerformed

    private void c_cnicActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_c_cnicActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_c_cnicActionPerformed

    private void c_lastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_c_lastActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_c_lastActionPerformed

    private void c_idActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_c_idActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_c_idActionPerformed

    private void c_firstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_c_firstActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_c_firstActionPerformed

    private void backMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backMouseClicked
        Home home= new Home();
        home.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_backMouseClicked

    private void citizenTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_citizenTableMouseClicked
        int rowNumber = citizenTable.getSelectedRow();
        TableModel model = citizenTable.getModel();
        c_id.setText(model.getValueAt(rowNumber, 0).toString());
        c_first.setText(model.getValueAt(rowNumber, 1).toString());
//        s_last.setText(model.getValueAt(rowNumber, 2).toString());
        c_cnic.setText(model.getValueAt(rowNumber, 2).toString());
        c_phone.setText(model.getValueAt(rowNumber, 3).toString());
        c_address.setText(model.getValueAt(rowNumber, 4).toString());
        c_gender.setText(model.getValueAt(rowNumber, 5).toString());
//        t_gender.setText(model.getValueAt(rowNumber, 6).toString());
//        t_department.setText(model.getValueAt(rowNumber, 7).toString());
      
    }//GEN-LAST:event_citizenTableMouseClicked

    private void manageTeacherActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_manageTeacherActionPerformed
        // TODO add your handling code here:
        ManageTeacherForm manageTh = new ManageTeacherForm();
        manageTh.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_manageTeacherActionPerformed

    private void manageStudentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_manageStudentActionPerformed
        // TODO add your handling code here:
        ManageStudentForm managestd = new ManageStudentForm();
        managestd.setVisible(true);
        managestd.setResizable(false);
        this.dispose();
    }//GEN-LAST:event_manageStudentActionPerformed

    private void clearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearActionPerformed
        // TODO add your handling code here:
         messageLabel.setText("");
        c_id.setText("");
        c_first.setText("");
        c_last.setText("");
        c_cnic.setText("");
        c_phone.setText("");
//        t_institute.setText("");
//        t_department.setText("");
        c_gender.setText("");
        c_address.setText("");
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
            java.util.logging.Logger.getLogger(ManageCitizenForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ManageCitizenForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ManageCitizenForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ManageCitizenForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new ManageCitizenForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private necesario.MaterialButton add_teacher;
    private javax.swing.JLabel back;
    private app.bolivia.swing.JCTextField c_address;
    private app.bolivia.swing.JCTextField c_cnic;
    private app.bolivia.swing.JCTextField c_first;
    private app.bolivia.swing.JCTextField c_gender;
    private app.bolivia.swing.JCTextField c_id;
    private app.bolivia.swing.JCTextField c_last;
    private app.bolivia.swing.JCTextField c_phone;
    private javax.swing.JLabel caddressLabel;
    private javax.swing.JLabel ccnicLabel;
    private javax.swing.JLabel cinstituteLabel;
    private rojeru_san.complementos.RSTableMetro citizenTable;
    private necesario.MaterialButton clear;
    private javax.swing.JLabel cphoneLabel;
    private necesario.MaterialButton delete_teacher;
    private javax.swing.JLabel fnameLabel;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lnameLabel;
    private necesario.MaterialButton manageCitizen;
    private necesario.MaterialButton manageStudent;
    private necesario.MaterialButton manageTeacher;
    private javax.swing.JLabel manage_Books;
    private javax.swing.JLabel messageLabel;
    private javax.swing.JLabel teach_detail;
    private javax.swing.JLabel tidLabel;
    private necesario.MaterialButton update_teacher;
    // End of variables declaration//GEN-END:variables
}
