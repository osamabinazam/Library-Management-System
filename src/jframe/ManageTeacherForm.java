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
public class ManageTeacherForm extends javax.swing.JFrame {
    
    String tID,tFname, tLname,tEmail, tPhone,tAddress,tInstitute,tGender,tDepartment;
    int sFine,sIssue;
   
    DefaultTableModel model;
    
    int calltoUpdate =1;

    
    public ManageTeacherForm() {
        initComponents();
        setStudentDetailTable(); 
    }
    
    
    //Method That Fetched Data from Database And set It to table
    
    public void setStudentDetailTable(){
        try {
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/library_ms","root","toor"); 
           
             Statement stmt= con.createStatement();
             ResultSet rset= stmt.executeQuery("select * from teacher");
             
             while (rset.next()){
               
                 String tID= rset.getString(1);
                 String tFullname=rset.getString(2)+ rset.getString(3);
                 String tPhone=rset.getString(4);
                 String tEmail= rset.getString(5);
                 String tAddress=rset.getString(6);
                 String tDepartment=rset.getString(7);
                 String tInstitute=rset.getString(8);
                 String tGender= rset.getString(9);

                 Object [] obj = {tID,tFullname,tEmail,tPhone,tAddress,tInstitute,tGender,tDepartment,"200","500"};
//                Object [] obj = {"1","2","3","4","5","200","500"};
                model = (DefaultTableModel) teacherTable.getModel();
                model.addRow(obj);
                 
             }
             
             
             
        }catch(Exception e )
        {
            e.printStackTrace();
        }
    }
    //To add book to booktable
    public boolean addStudent(){
        boolean isAdded=false;
        boolean checkException=true;
        
        if (t_first.getText().isEmpty() || t_last.getText().isEmpty() || t_email.getText().isEmpty() || t_institute.getText().isEmpty() || t_id.getText().isEmpty() || t_phone.getText().isEmpty() || t_address.getText().isEmpty() || t_department.getText().isEmpty()){
            messageLabel.setText("Please Provide Values To Fields, Thank You");
            return isAdded;
        }
        else{
        
        
        tID= t_id.getText();
        tFname=t_first.getText();
        tLname= t_last.getText();
        tEmail= t_email.getText();
        tPhone=t_phone.getText();
        tAddress=t_address.getText();
        tInstitute=t_institute.getText();
        tDepartment=t_department.getText();
        tGender=t_gender.getText();
        if (checkException) {
            try {

                Connection conc = DriverManager.getConnection("jdbc:mysql://localhost:3306/library_ms", "root", "toor");
                String sql = "insert into teacher values (?,?,?,?,?,?,?,?,?,?,?)";
                PreparedStatement pst = conc.prepareStatement(sql);
                pst.setString(1, tID );
                pst.setString(2, tFname);
                pst.setString(3, tLname);
                pst.setString(4, tPhone);
                pst.setString(5, tEmail);
                pst.setString(6, tAddress );
                pst.setString(7, tDepartment);
                pst.setString(8, tInstitute);
                pst.setString(9, tGender);
                pst.setInt(10, 22);
                pst.setInt(11, 2);

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
        DefaultTableModel model = (DefaultTableModel) teacherTable.getModel();
        model.setRowCount(0);
    }
    
    //Update Book Method
    
    public boolean updateTeacher (){
        
        System.out.println("Update Call:"+calltoUpdate++);
        
        boolean isUpdated=false;
        tID = t_id.getText();
        tFname=t_first.getText();
        tLname= t_last.getText();
        tEmail= t_email.getText();
        tPhone=t_phone.getText();
        tAddress=t_address.getText();
        tInstitute=t_institute.getText();
        tDepartment=t_department.getText();
        tGender=t_gender.getText();
        
        try{
            Connection conc=DriverManager.getConnection("jdbc:mysql://localhost:3306/library_ms","root","toor");
           String sql = "update teacher set fname = ?, lname = ? , phone = ?, emial = ?, address = ? , depart = ? , institute = ? , gender =  ? ";
           PreparedStatement pst = conc.prepareStatement(sql);
           pst.setString(1, tFname);
           pst.setString(2, tLname);
           pst.setString(3, tPhone);
           pst.setString(4, tEmail);
           pst.setString(5, tAddress);
           pst.setString(6, tDepartment);
           pst.setString(7, tInstitute);
           pst.setString(8, tGender);
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
    
    public boolean deleteTeacher(){
        boolean isDeleted = false;
          tID= t_id.getText();
          
          try{
             Connection conc=DriverManager.getConnection("jdbc:mysql://localhost:3306/library_ms","root","toor");
            String sql = "delete from Teacher where tid = ? ";
            PreparedStatement pst = conc.prepareStatement(sql);
            pst.setString(1,tID);
            
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
        t_department = new app.bolivia.swing.JCTextField();
        tdepartmentLabel = new javax.swing.JLabel();
        t_id = new app.bolivia.swing.JCTextField();
        t_first = new app.bolivia.swing.JCTextField();
        t_last = new app.bolivia.swing.JCTextField();
        t_email = new app.bolivia.swing.JCTextField();
        t_phone = new app.bolivia.swing.JCTextField();
        t_address = new app.bolivia.swing.JCTextField();
        tidLabel = new javax.swing.JLabel();
        fnameLabel = new javax.swing.JLabel();
        lnameLabel = new javax.swing.JLabel();
        temailLabel = new javax.swing.JLabel();
        tphoneLabel = new javax.swing.JLabel();
        taddressLabel = new javax.swing.JLabel();
        t_institute = new app.bolivia.swing.JCTextField();
        tinstituteLabel = new javax.swing.JLabel();
        delete_teacher = new necesario.MaterialButton();
        add_teacher = new necesario.MaterialButton();
        update_teacher = new necesario.MaterialButton();
        messageLabel = new javax.swing.JLabel();
        tgenderLabel = new javax.swing.JLabel();
        t_gender = new app.bolivia.swing.JCTextField();
        teach_detail = new javax.swing.JLabel();
        clear = new necesario.MaterialButton();
        jPanel3 = new javax.swing.JPanel();
        manage_Books = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        teacherTable = new rojeru_san.complementos.RSTableMetro();
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

        t_department.setBackground(new java.awt.Color(102, 102, 255));
        t_department.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        t_department.setForeground(new java.awt.Color(255, 255, 255));
        t_department.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        t_department.setPlaceholder("Enter Department...");
        t_department.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                t_departmentActionPerformed(evt);
            }
        });
        jPanel1.add(t_department, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 570, 240, 25));

        tdepartmentLabel.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        tdepartmentLabel.setForeground(new java.awt.Color(255, 255, 255));
        tdepartmentLabel.setText("Department:");
        jPanel1.add(tdepartmentLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 570, 100, -1));

        t_id.setBackground(new java.awt.Color(102, 102, 255));
        t_id.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        t_id.setForeground(new java.awt.Color(255, 255, 255));
        t_id.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        t_id.setPlaceholder("Enter Teacher ID...");
        t_id.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                t_idActionPerformed(evt);
            }
        });
        jPanel1.add(t_id, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 250, 240, 25));

        t_first.setBackground(new java.awt.Color(102, 102, 255));
        t_first.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        t_first.setForeground(new java.awt.Color(255, 255, 255));
        t_first.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        t_first.setPlaceholder("Enter First Name...");
        t_first.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                t_firstActionPerformed(evt);
            }
        });
        jPanel1.add(t_first, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 290, 240, 25));

        t_last.setBackground(new java.awt.Color(102, 102, 255));
        t_last.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        t_last.setForeground(new java.awt.Color(255, 255, 255));
        t_last.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        t_last.setPlaceholder("Enter Last Name...");
        t_last.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                t_lastActionPerformed(evt);
            }
        });
        jPanel1.add(t_last, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 330, 240, 25));

        t_email.setBackground(new java.awt.Color(102, 102, 255));
        t_email.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        t_email.setForeground(new java.awt.Color(255, 255, 255));
        t_email.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        t_email.setPlaceholder("Enter Email...");
        t_email.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                t_emailActionPerformed(evt);
            }
        });
        jPanel1.add(t_email, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 370, 240, 25));

        t_phone.setBackground(new java.awt.Color(102, 102, 255));
        t_phone.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        t_phone.setForeground(new java.awt.Color(255, 255, 255));
        t_phone.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        t_phone.setPlaceholder("Enter Phone...");
        t_phone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                t_phoneActionPerformed(evt);
            }
        });
        jPanel1.add(t_phone, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 410, 240, 25));

        t_address.setBackground(new java.awt.Color(102, 102, 255));
        t_address.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        t_address.setForeground(new java.awt.Color(255, 255, 255));
        t_address.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        t_address.setPlaceholder("Enter Address...");
        t_address.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                t_addressActionPerformed(evt);
            }
        });
        jPanel1.add(t_address, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 450, 240, 25));

        tidLabel.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        tidLabel.setForeground(new java.awt.Color(255, 255, 255));
        tidLabel.setText("Teacher ID:");
        jPanel1.add(tidLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 250, 100, -1));

        fnameLabel.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        fnameLabel.setForeground(new java.awt.Color(255, 255, 255));
        fnameLabel.setText("First Name:");
        jPanel1.add(fnameLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 290, 100, -1));

        lnameLabel.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lnameLabel.setForeground(new java.awt.Color(255, 255, 255));
        lnameLabel.setText("Last Name:");
        jPanel1.add(lnameLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 330, 100, -1));

        temailLabel.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        temailLabel.setForeground(new java.awt.Color(255, 255, 255));
        temailLabel.setText("Email Address:");
        jPanel1.add(temailLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 370, 110, -1));

        tphoneLabel.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        tphoneLabel.setForeground(new java.awt.Color(255, 255, 255));
        tphoneLabel.setText("Phone No:");
        jPanel1.add(tphoneLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 410, 100, -1));

        taddressLabel.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        taddressLabel.setForeground(new java.awt.Color(255, 255, 255));
        taddressLabel.setText("Address:");
        jPanel1.add(taddressLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 450, 120, -1));

        t_institute.setBackground(new java.awt.Color(102, 102, 255));
        t_institute.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        t_institute.setForeground(new java.awt.Color(255, 255, 255));
        t_institute.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        t_institute.setPlaceholder("Enter Institute...");
        t_institute.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                t_instituteActionPerformed(evt);
            }
        });
        jPanel1.add(t_institute, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 490, 240, 25));

        tinstituteLabel.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        tinstituteLabel.setForeground(new java.awt.Color(255, 255, 255));
        tinstituteLabel.setText("Institute:");
        jPanel1.add(tinstituteLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 490, 100, -1));

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

        tgenderLabel.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        tgenderLabel.setForeground(new java.awt.Color(255, 255, 255));
        tgenderLabel.setText("Gender:");
        jPanel1.add(tgenderLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 530, 100, -1));

        t_gender.setBackground(new java.awt.Color(102, 102, 255));
        t_gender.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        t_gender.setForeground(new java.awt.Color(255, 255, 255));
        t_gender.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        t_gender.setPlaceholder("Enter Gender...");
        t_gender.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                t_genderActionPerformed(evt);
            }
        });
        jPanel1.add(t_gender, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 530, 240, 25));

        teach_detail.setBackground(new java.awt.Color(102, 102, 255));
        teach_detail.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        teach_detail.setForeground(new java.awt.Color(51, 51, 51));
        teach_detail.setText(" Teacher Detail:");
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
        manage_Books.setText("Manage Teachers");
        jPanel3.add(manage_Books, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, 400, 90));

        jScrollPane1.setBackground(new java.awt.Color(102, 102, 255));
        jScrollPane1.setBorder(null);
        jScrollPane1.setForeground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        teacherTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Teacher ID", "Full Name ", "Email", "Phone", "Address", "Institute", "Gender", "Department", "Fine", "Issue Books"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
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

        jPanel3.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 240, 940, 490));

        manageTeacher.setBackground(new java.awt.Color(0, 0, 0));
        manageTeacher.setBorder(null);
        manageTeacher.setForeground(new java.awt.Color(255, 255, 255));
        manageTeacher.setText("Teachers");
        manageTeacher.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                manageTeacherActionPerformed(evt);
            }
        });
        jPanel3.add(manageTeacher, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 40, 150, 50));

        manageCitizen.setBackground(new java.awt.Color(255, 255, 255));
        manageCitizen.setBorder(null);
        manageCitizen.setForeground(new java.awt.Color(0, 0, 0));
        manageCitizen.setText("Citizens");
        manageCitizen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                manageCitizenActionPerformed(evt);
            }
        });
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
        if(deleteTeacher()){
            messageLabel.setText("Book deleted Successfully");
            clearTable();
            setStudentDetailTable();
        }
        
        
        
        
        
    }//GEN-LAST:event_delete_teacherActionPerformed

    private void update_teacherActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_update_teacherActionPerformed
        // TODO add your handling code here:
        
           if(updateTeacher()){
            messageLabel.setText("Book Updated Successfully");
            clearTable();
            setStudentDetailTable();
        }
        else{
            messageLabel.setText("Error!, Can't Update Book");
        }
    }//GEN-LAST:event_update_teacherActionPerformed

    private void add_teacherActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_add_teacherActionPerformed
        if(addStudent()){
            messageLabel.setText("Student Record Added Successfully");
            clearTable();
            setStudentDetailTable();
        }
       
        
    }//GEN-LAST:event_add_teacherActionPerformed

    private void t_departmentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_t_departmentActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_t_departmentActionPerformed

    private void t_instituteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_t_instituteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_t_instituteActionPerformed

    private void t_addressActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_t_addressActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_t_addressActionPerformed

    private void t_phoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_t_phoneActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_t_phoneActionPerformed

    private void t_emailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_t_emailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_t_emailActionPerformed

    private void t_lastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_t_lastActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_t_lastActionPerformed

    private void t_idActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_t_idActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_t_idActionPerformed

    private void t_firstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_t_firstActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_t_firstActionPerformed

    private void backMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backMouseClicked
        Home home= new Home();
        home.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_backMouseClicked

    private void teacherTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_teacherTableMouseClicked
        int rowNumber = teacherTable.getSelectedRow();
        TableModel model = teacherTable.getModel();
        t_id.setText(model.getValueAt(rowNumber, 0).toString());
        t_first.setText(model.getValueAt(rowNumber, 1).toString());
//        s_last.setText(model.getValueAt(rowNumber, 2).toString());
        t_email.setText(model.getValueAt(rowNumber, 2).toString());
        t_phone.setText(model.getValueAt(rowNumber, 3).toString());
        t_address.setText(model.getValueAt(rowNumber, 4).toString());
        t_institute.setText(model.getValueAt(rowNumber, 5).toString());
        t_gender.setText(model.getValueAt(rowNumber, 6).toString());
        t_department.setText(model.getValueAt(rowNumber, 7).toString());
      
    }//GEN-LAST:event_teacherTableMouseClicked

    private void t_genderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_t_genderActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_t_genderActionPerformed

    private void manageTeacherActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_manageTeacherActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_manageTeacherActionPerformed

    private void manageStudentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_manageStudentActionPerformed
        // TODO add your handling code here:
        ManageStudentForm managestd = new ManageStudentForm();
        managestd.setVisible(true);
        managestd.setResizable(false);
        this.dispose();
    }//GEN-LAST:event_manageStudentActionPerformed

    private void manageCitizenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_manageCitizenActionPerformed
        // TODO add your handling code here:
        ManageCitizenForm manageCt = new ManageCitizenForm();
        manageCt.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_manageCitizenActionPerformed

    private void clearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearActionPerformed
        // TODO add your handling code here:
        messageLabel.setText("");
        t_id.setText("");
        t_first.setText("");
        t_last.setText("");
        t_email.setText("");
        t_phone.setText("");
        t_institute.setText("");
        t_department.setText("");
        t_gender.setText("");
        t_address.setText("");
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
            java.util.logging.Logger.getLogger(ManageTeacherForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ManageTeacherForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ManageTeacherForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ManageTeacherForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ManageTeacherForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private necesario.MaterialButton add_teacher;
    private javax.swing.JLabel back;
    private necesario.MaterialButton clear;
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
    private app.bolivia.swing.JCTextField t_address;
    private app.bolivia.swing.JCTextField t_department;
    private app.bolivia.swing.JCTextField t_email;
    private app.bolivia.swing.JCTextField t_first;
    private app.bolivia.swing.JCTextField t_gender;
    private app.bolivia.swing.JCTextField t_id;
    private app.bolivia.swing.JCTextField t_institute;
    private app.bolivia.swing.JCTextField t_last;
    private app.bolivia.swing.JCTextField t_phone;
    private javax.swing.JLabel taddressLabel;
    private javax.swing.JLabel tdepartmentLabel;
    private javax.swing.JLabel teach_detail;
    private rojeru_san.complementos.RSTableMetro teacherTable;
    private javax.swing.JLabel temailLabel;
    private javax.swing.JLabel tgenderLabel;
    private javax.swing.JLabel tidLabel;
    private javax.swing.JLabel tinstituteLabel;
    private javax.swing.JLabel tphoneLabel;
    private necesario.MaterialButton update_teacher;
    // End of variables declaration//GEN-END:variables
}
