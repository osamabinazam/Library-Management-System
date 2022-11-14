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
public class ManageStudentForm extends javax.swing.JFrame {
    
    String sID,sFname, sLname,sEmail, sPhone,sAddress,sInstitute,sGender,sDepartment;
    int sFine,sIssue;
   
    DefaultTableModel model;
    
    int calltoUpdate =1;

    
    public ManageStudentForm() {
        initComponents();
        setStudentDetailTable(); 
    }
    
    
    //Method That Fetched Data from Database And set It to table
    
    public void setStudentDetailTable(){
        try {
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/library_ms","root","toor"); 
           
             Statement stmt= con.createStatement();
             ResultSet rset= stmt.executeQuery("select * from student");
             
             while (rset.next()){
               
                 String sID= rset.getString(1);
                 String sFullname=rset.getString(2)+ rset.getString(3);
                 String sPhone=rset.getString(4);
                 String sEmail= rset.getString(5);
                 String sAddress=rset.getString(6);
                 String sDepartment=rset.getString(7);
                 String sInstitute=rset.getString(8);
                 String sGender= rset.getString(9);

                 Object [] obj = {sID,sFullname,sEmail,sPhone,sAddress,sInstitute,sGender,sDepartment,"200","500"};
//                Object [] obj = {"1","2","3","4","5","200","500"};
                model = (DefaultTableModel) studentTable.getModel();
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
        
        if (s_first.getText().isEmpty() || s_last.getText().isEmpty() || s_email.getText().isEmpty() || s_institute.getText().isEmpty() || s_id.getText().isEmpty() || s_phone.getText().isEmpty() || s_address.getText().isEmpty() || s_department.getText().isEmpty()){
            messageLabel.setText("Please Provide Values To Fields, Thank You");
            return isAdded;
        }
        else{
        
        
        sID= s_id.getText();
        sFname=s_first.getText();
        sLname= s_last.getText();
        sEmail= s_email.getText();
        sPhone=s_phone.getText();
        sAddress=s_address.getText();
        sInstitute=s_institute.getText();
        sDepartment=s_department.getText();
        sGender=s_gender.getText();
        if (checkException) {
            try {

                Connection conc = DriverManager.getConnection("jdbc:mysql://localhost:3306/library_ms", "root", "toor");
                String sql = "insert into student values (?,?,?,?,?,?,?,?,?)";
                PreparedStatement pst = conc.prepareStatement(sql);
                pst.setString(1,    sID );
                pst.setString(2, sFname);
                pst.setString(3, sLname);
                pst.setString(4, sPhone);
                pst.setString(5, sEmail);
                pst.setString(6, sAddress );
                pst.setString(7, sDepartment);
                pst.setString(8, sInstitute);
                pst.setString(9, sGender);

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
        DefaultTableModel model = (DefaultTableModel) studentTable.getModel();
        model.setRowCount(0);
    }
    
    //Update Book Method
    
    public boolean updateStudent (){
        
        System.out.println("Update Call:"+calltoUpdate++);
        
        boolean isUpdated=false;
        sID = s_id.getText();
        sFname=s_first.getText();
        sLname= s_last.getText();
        sEmail= s_email.getText();
        sPhone=s_phone.getText();
        sAddress=s_address.getText();
        sInstitute=s_institute.getText();
        sDepartment=s_department.getText();
        sGender=s_gender.getText();
        
        try{
            Connection conc=DriverManager.getConnection("jdbc:mysql://localhost:3306/library_ms","root","toor");
           String sql = "update student set fname = ?, lname = ? , phone = ?, emial = ?, address = ? , depart = ? , institute = ? , gender =  ? ";
           PreparedStatement pst = conc.prepareStatement(sql);
           pst.setString(1, sFname);
           pst.setString(2, sLname);
           pst.setString(3, sPhone);
           pst.setString(4, sEmail);
           pst.setString(5, sAddress);
           pst.setString(6, sDepartment);
           pst.setString(7, sInstitute);
           pst.setString(8, sGender);
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
    
    public boolean deleteStudent(){
        boolean isDeleted = false;
          sID= s_id.getText();
          
          try{
             Connection conc=DriverManager.getConnection("jdbc:mysql://localhost:3306/library_ms","root","toor");
            String sql = "delete from student where sid = ? ";
            PreparedStatement pst = conc.prepareStatement(sql);
            pst.setString(1,sID);
            
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
        s_department = new app.bolivia.swing.JCTextField();
        sdepartmentLabel = new javax.swing.JLabel();
        s_id = new app.bolivia.swing.JCTextField();
        s_first = new app.bolivia.swing.JCTextField();
        s_last = new app.bolivia.swing.JCTextField();
        s_email = new app.bolivia.swing.JCTextField();
        s_phone = new app.bolivia.swing.JCTextField();
        s_address = new app.bolivia.swing.JCTextField();
        sidLabel = new javax.swing.JLabel();
        fnameLabel = new javax.swing.JLabel();
        lnameLabel = new javax.swing.JLabel();
        semailLabel = new javax.swing.JLabel();
        sphoneLabel = new javax.swing.JLabel();
        saddressLabel = new javax.swing.JLabel();
        s_institute = new app.bolivia.swing.JCTextField();
        sinstituteLabel = new javax.swing.JLabel();
        delete_std = new necesario.MaterialButton();
        add_std = new necesario.MaterialButton();
        update_std = new necesario.MaterialButton();
        messageLabel = new javax.swing.JLabel();
        sgenderLabel = new javax.swing.JLabel();
        s_gender = new app.bolivia.swing.JCTextField();
        teach_detail = new javax.swing.JLabel();
        clear = new necesario.MaterialButton();
        jPanel3 = new javax.swing.JPanel();
        manage_Books = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        studentTable = new rojeru_san.complementos.RSTableMetro();
        jPanel4 = new javax.swing.JPanel();
        manageTeacher = new necesario.MaterialButton();
        manageCitizen = new necesario.MaterialButton();
        manageStudent = new necesario.MaterialButton();

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

        s_department.setBackground(new java.awt.Color(102, 102, 255));
        s_department.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        s_department.setForeground(new java.awt.Color(255, 255, 255));
        s_department.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        s_department.setPlaceholder("Enter Department...");
        s_department.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                s_departmentActionPerformed(evt);
            }
        });
        jPanel1.add(s_department, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 570, 240, 25));

        sdepartmentLabel.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        sdepartmentLabel.setForeground(new java.awt.Color(255, 255, 255));
        sdepartmentLabel.setText("Department:");
        jPanel1.add(sdepartmentLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 570, 100, -1));

        s_id.setBackground(new java.awt.Color(102, 102, 255));
        s_id.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        s_id.setForeground(new java.awt.Color(255, 255, 255));
        s_id.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        s_id.setPlaceholder("Enter Student ID...");
        s_id.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                s_idActionPerformed(evt);
            }
        });
        jPanel1.add(s_id, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 250, 240, 25));

        s_first.setBackground(new java.awt.Color(102, 102, 255));
        s_first.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        s_first.setForeground(new java.awt.Color(255, 255, 255));
        s_first.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        s_first.setPlaceholder("Enter First Name...");
        s_first.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                s_firstActionPerformed(evt);
            }
        });
        jPanel1.add(s_first, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 290, 240, 25));

        s_last.setBackground(new java.awt.Color(102, 102, 255));
        s_last.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        s_last.setForeground(new java.awt.Color(255, 255, 255));
        s_last.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        s_last.setPlaceholder("Enter Last Name...");
        s_last.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                s_lastActionPerformed(evt);
            }
        });
        jPanel1.add(s_last, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 330, 240, 25));

        s_email.setBackground(new java.awt.Color(102, 102, 255));
        s_email.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        s_email.setForeground(new java.awt.Color(255, 255, 255));
        s_email.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        s_email.setPlaceholder("Enter Email...");
        s_email.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                s_emailActionPerformed(evt);
            }
        });
        jPanel1.add(s_email, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 370, 240, 25));

        s_phone.setBackground(new java.awt.Color(102, 102, 255));
        s_phone.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        s_phone.setForeground(new java.awt.Color(255, 255, 255));
        s_phone.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        s_phone.setPlaceholder("Enter Phone...");
        s_phone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                s_phoneActionPerformed(evt);
            }
        });
        jPanel1.add(s_phone, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 410, 240, 25));

        s_address.setBackground(new java.awt.Color(102, 102, 255));
        s_address.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        s_address.setForeground(new java.awt.Color(255, 255, 255));
        s_address.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        s_address.setPlaceholder("Enter Address...");
        s_address.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                s_addressActionPerformed(evt);
            }
        });
        jPanel1.add(s_address, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 450, 240, 25));

        sidLabel.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        sidLabel.setForeground(new java.awt.Color(255, 255, 255));
        sidLabel.setText("Student ID:");
        jPanel1.add(sidLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 250, 100, -1));

        fnameLabel.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        fnameLabel.setForeground(new java.awt.Color(255, 255, 255));
        fnameLabel.setText("First Name:");
        jPanel1.add(fnameLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 290, 100, -1));

        lnameLabel.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lnameLabel.setForeground(new java.awt.Color(255, 255, 255));
        lnameLabel.setText("Last Name:");
        jPanel1.add(lnameLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 330, 100, -1));

        semailLabel.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        semailLabel.setForeground(new java.awt.Color(255, 255, 255));
        semailLabel.setText("Email Address:");
        jPanel1.add(semailLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 370, 110, -1));

        sphoneLabel.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        sphoneLabel.setForeground(new java.awt.Color(255, 255, 255));
        sphoneLabel.setText("Phone No:");
        jPanel1.add(sphoneLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 410, 100, -1));

        saddressLabel.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        saddressLabel.setForeground(new java.awt.Color(255, 255, 255));
        saddressLabel.setText("Address:");
        jPanel1.add(saddressLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 450, 120, -1));

        s_institute.setBackground(new java.awt.Color(102, 102, 255));
        s_institute.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        s_institute.setForeground(new java.awt.Color(255, 255, 255));
        s_institute.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        s_institute.setPlaceholder("Enter Institute...");
        s_institute.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                s_instituteActionPerformed(evt);
            }
        });
        jPanel1.add(s_institute, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 490, 240, 25));

        sinstituteLabel.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        sinstituteLabel.setForeground(new java.awt.Color(255, 255, 255));
        sinstituteLabel.setText("Institute:");
        jPanel1.add(sinstituteLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 490, 100, -1));

        delete_std.setBackground(new java.awt.Color(255, 51, 0));
        delete_std.setForeground(new java.awt.Color(0, 0, 0));
        delete_std.setText("Delete");
        delete_std.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                delete_stdActionPerformed(evt);
            }
        });
        jPanel1.add(delete_std, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 630, 110, -1));

        add_std.setBackground(new java.awt.Color(255, 255, 255));
        add_std.setForeground(new java.awt.Color(0, 0, 0));
        add_std.setText("Add");
        add_std.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                add_stdActionPerformed(evt);
            }
        });
        jPanel1.add(add_std, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 630, 110, -1));

        update_std.setBackground(new java.awt.Color(255, 255, 255));
        update_std.setForeground(new java.awt.Color(0, 0, 0));
        update_std.setText("Update");
        update_std.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                update_stdActionPerformed(evt);
            }
        });
        jPanel1.add(update_std, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 630, 120, 40));

        messageLabel.setBackground(new java.awt.Color(102, 102, 255));
        messageLabel.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        messageLabel.setForeground(new java.awt.Color(255, 51, 0));
        messageLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        messageLabel.setAutoscrolls(true);
        jPanel1.add(messageLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 370, 40));

        sgenderLabel.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        sgenderLabel.setForeground(new java.awt.Color(255, 255, 255));
        sgenderLabel.setText("Gender:");
        jPanel1.add(sgenderLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 530, 100, -1));

        s_gender.setBackground(new java.awt.Color(102, 102, 255));
        s_gender.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        s_gender.setForeground(new java.awt.Color(255, 255, 255));
        s_gender.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        s_gender.setPlaceholder("Enter Gender...");
        s_gender.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                s_genderActionPerformed(evt);
            }
        });
        jPanel1.add(s_gender, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 530, 240, 25));

        teach_detail.setBackground(new java.awt.Color(102, 102, 255));
        teach_detail.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        teach_detail.setForeground(new java.awt.Color(51, 51, 51));
        teach_detail.setText(" Student Detail:");
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
        manage_Books.setText("Manage Students");
        jPanel3.add(manage_Books, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, 400, 90));

        jScrollPane1.setBackground(new java.awt.Color(102, 102, 255));
        jScrollPane1.setBorder(null);
        jScrollPane1.setForeground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        studentTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Student ID", "Full Name ", "Email", "Phone", "Address", "Institute", "Gender", "Department", "Fine", "Issue Books"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        studentTable.setColorBackgoundHead(new java.awt.Color(102, 102, 255));
        studentTable.setColorBordeFilas(new java.awt.Color(102, 102, 255));
        studentTable.setColorSelBackgound(new java.awt.Color(255, 51, 51));
        studentTable.setFuenteFilas(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        studentTable.setFuenteFilasSelect(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        studentTable.setFuenteHead(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        studentTable.setRowHeight(30);
        studentTable.setRowMargin(1);
        studentTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                studentTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(studentTable);

        jPanel3.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 240, 920, 490));

        jPanel4.setBackground(new java.awt.Color(255, 0, 0));
        jPanel3.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 220, 390, 5));

        manageTeacher.setBackground(new java.awt.Color(255, 255, 255));
        manageTeacher.setForeground(new java.awt.Color(0, 0, 0));
        manageTeacher.setText("Teachers");
        manageTeacher.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                manageTeacherActionPerformed(evt);
            }
        });
        jPanel3.add(manageTeacher, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 40, 150, 50));

        manageCitizen.setBackground(new java.awt.Color(255, 255, 255));
        manageCitizen.setForeground(new java.awt.Color(0, 0, 0));
        manageCitizen.setText("Citizens");
        manageCitizen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                manageCitizenActionPerformed(evt);
            }
        });
        jPanel3.add(manageCitizen, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 40, 140, 50));

        manageStudent.setBackground(new java.awt.Color(0, 0, 0));
        manageStudent.setBorder(null);
        manageStudent.setForeground(new java.awt.Color(255, 255, 255));
        manageStudent.setText("Students");
        manageStudent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                manageStudentActionPerformed(evt);
            }
        });
        jPanel3.add(manageStudent, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 40, 150, 50));

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 0, 1010, 770));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void delete_stdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_delete_stdActionPerformed
        // TODO add your handling code here:
        if(deleteStudent()){
            messageLabel.setText("Book deleted Successfully");
            clearTable();
            setStudentDetailTable();
        }
        
        
        
        
        
    }//GEN-LAST:event_delete_stdActionPerformed

    private void update_stdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_update_stdActionPerformed
        // TODO add your handling code here:
        
           if(updateStudent()){
            messageLabel.setText("Book Updated Successfully");
            clearTable();
            setStudentDetailTable();
        }
        else{
            messageLabel.setText("Error!, Can't Update Book");
        }
    }//GEN-LAST:event_update_stdActionPerformed

    private void add_stdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_add_stdActionPerformed
        if(addStudent()){
            messageLabel.setText("Student Record Added Successfully");
            clearTable();
            setStudentDetailTable();
        }
       
        
    }//GEN-LAST:event_add_stdActionPerformed

    private void s_departmentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_s_departmentActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_s_departmentActionPerformed

    private void s_instituteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_s_instituteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_s_instituteActionPerformed

    private void s_addressActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_s_addressActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_s_addressActionPerformed

    private void s_phoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_s_phoneActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_s_phoneActionPerformed

    private void s_emailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_s_emailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_s_emailActionPerformed

    private void s_lastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_s_lastActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_s_lastActionPerformed

    private void s_idActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_s_idActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_s_idActionPerformed

    private void s_firstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_s_firstActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_s_firstActionPerformed

    private void backMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backMouseClicked
        Home home= new Home();
        home.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_backMouseClicked

    private void studentTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_studentTableMouseClicked
        int rowNumber = studentTable.getSelectedRow();
        TableModel model = studentTable.getModel();
        s_id.setText(model.getValueAt(rowNumber, 0).toString());
        s_first.setText(model.getValueAt(rowNumber, 1).toString());
//        s_last.setText(model.getValueAt(rowNumber, 2).toString());
        s_email.setText(model.getValueAt(rowNumber, 2).toString());
        s_phone.setText(model.getValueAt(rowNumber, 3).toString());
        s_address.setText(model.getValueAt(rowNumber, 4).toString());
        s_institute.setText(model.getValueAt(rowNumber, 5).toString());
        s_gender.setText(model.getValueAt(rowNumber, 6).toString());
        s_department.setText(model.getValueAt(rowNumber, 7).toString());
      
    }//GEN-LAST:event_studentTableMouseClicked

    private void s_genderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_s_genderActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_s_genderActionPerformed

    private void manageTeacherActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_manageTeacherActionPerformed
        // TODO add your handling code here:
        ManageTeacherForm manageTh = new ManageTeacherForm();
        manageTh.setVisible(true);
        manageTh.setResizable(false);
        this.dispose();
    }//GEN-LAST:event_manageTeacherActionPerformed

    private void manageStudentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_manageStudentActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_manageStudentActionPerformed

    private void clearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearActionPerformed
        // TODO add your handling code here:
        
        messageLabel.setText("");
        s_id.setText("");
        s_first.setText("");
        s_last.setText("");
        s_email.setText("");
        s_phone.setText("");
        s_institute.setText("");
        s_department.setText("");
        s_gender.setText("");
        s_address.setText("");
    }//GEN-LAST:event_clearActionPerformed

    private void manageCitizenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_manageCitizenActionPerformed
        // TODO add your handling code here:
        ManageCitizenForm manageCt= new ManageCitizenForm ();
        manageCt.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_manageCitizenActionPerformed

    
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
            java.util.logging.Logger.getLogger(ManageStudentForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ManageStudentForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ManageStudentForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ManageStudentForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ManageStudentForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private necesario.MaterialButton add_std;
    private javax.swing.JLabel back;
    private necesario.MaterialButton clear;
    private necesario.MaterialButton delete_std;
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
    private app.bolivia.swing.JCTextField s_address;
    private app.bolivia.swing.JCTextField s_department;
    private app.bolivia.swing.JCTextField s_email;
    private app.bolivia.swing.JCTextField s_first;
    private app.bolivia.swing.JCTextField s_gender;
    private app.bolivia.swing.JCTextField s_id;
    private app.bolivia.swing.JCTextField s_institute;
    private app.bolivia.swing.JCTextField s_last;
    private app.bolivia.swing.JCTextField s_phone;
    private javax.swing.JLabel saddressLabel;
    private javax.swing.JLabel sdepartmentLabel;
    private javax.swing.JLabel semailLabel;
    private javax.swing.JLabel sgenderLabel;
    private javax.swing.JLabel sidLabel;
    private javax.swing.JLabel sinstituteLabel;
    private javax.swing.JLabel sphoneLabel;
    private rojeru_san.complementos.RSTableMetro studentTable;
    private javax.swing.JLabel teach_detail;
    private necesario.MaterialButton update_std;
    // End of variables declaration//GEN-END:variables
}
