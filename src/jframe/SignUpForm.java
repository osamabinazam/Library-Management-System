/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package jframe;

//import com.sun.jdi.connect.spi.Connection;
import java.sql.*;
import javax.swing.JOptionPane;



/**
 *
 * @author kazii
 */
public class SignUpForm extends javax.swing.JFrame {

    /**
     * Creates new form SignUpForm
     */
    public SignUpForm() {
        initComponents();
    }
    
    
    //Method that communicate with mysql Server
    
    public void insertSignupvalues(){
        String f_name= this.f_name.getText();
        String l_name= this.l_name.getText();
        String email= this.email_add.getText();
        String phone=this.phone_no.getText();
        String address=this.age.getText();
        String username=this.username.getText();
       
        
        
        String password=this.password.getText();
        int age;
        try{
            age=Integer.parseInt(this.age.getText());
        }catch(Exception e){
            age=0;
        }
        int  index =this.gender.getSelectedIndex();
        System.out.println("after vars");
        
        String gender="Male";
         if(index==0){
            gender="Male";
        }
        else if (index==1){
            gender="Female";
        }
        else{
            gender="Others";
        }
        
         
         //Data Vallidity
        if(f_name.isEmpty() || l_name.isEmpty() || email.isEmpty() || phone.isEmpty() || address.isEmpty() || username.isEmpty() || password.isEmpty()){
            messageLabel.setText("Error! ,Please provide complete information");
        
        }
        else if(age<=0){
            messageLabel.setText("Error! ,Age can not be zero or Negative");
        }
        else if(age>120){
             messageLabel.setText("Error!, Exceeding age limit");
        }
        else{
        try{
             System.out.println("Connecting");
             
           if(!(f_name.isEmpty() || l_name.isEmpty() || email.isEmpty() || phone.isEmpty() || address.isEmpty() || username.isEmpty() || password.isEmpty())){
            messageLabel.setText("Error! ,Please provide complete information");
        
        
           Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/library_ms","root","toor"); 
           String sql = "insert into users (firstname,lastname,email,phone,address,username,password,age,gender) values (?,?,?,?,?,?,?,?,?)";
           PreparedStatement pst= con.prepareStatement(sql);
           
           System.out.println("Connected");
           pst.setString(1, f_name);
           pst.setString(2, l_name);
           pst.setString(3, email);
           pst.setString(4, phone);
           pst.setString(5, address);
           pst.setString(6, username);
           pst.setString(7, password);
           pst.setInt(8, age);
           pst.setString(9, gender);
          
           
          
           int updated= pst.executeUpdate();
           
           if(updated > 0){
               JOptionPane.showMessageDialog(this, "Record Successfully inserted");
                LoginForm login= new LoginForm();
                login.setVisible(true);
                this.dispose();
           }
           else{
               JOptionPane.showMessageDialog(this, "Insertion Failed");
           }
           
           }
            
            
        }catch(Exception e){
            e.printStackTrace();
        }
        }
        
    
    }
    
    
    public boolean checkDuplicateUsers(){
        String username = this.username.getText();
        
        boolean isExist=false;
        try{
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/library_ms","root","toor");
            PreparedStatement pst = con.prepareStatement("select * from users where name = ?");
            pst.setString(1,username);
            ResultSet rs=pst.executeQuery();
            if(rs.next()){
                isExist=true;
            }
            else
            {
                isExist=false;
            }     
            
        }catch(Exception e){}
        
        
        return false;
        
    }
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        gender = new javax.swing.JComboBox<>();
        messageLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Signup");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 51, 51));
        jLabel1.setText("Unique ");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, -1, -1));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(102, 102, 255));
        jLabel2.setText("Developer");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 10, -1, -1));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 30)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 51, 51));
        jLabel3.setText("Welcome To");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 80, -1, -1));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 30)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(102, 102, 255));
        jLabel4.setText("SL Library");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 80, 150, -1));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons/signup-library-icon.png"))); // NOI18N
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, 890, 570));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 900, 768));

        jPanel2.setBackground(new java.awt.Color(102, 102, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 51, 0));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Create New Account");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 10, 320, -1));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("First Name:");
        jPanel2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 150, 110, -1));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Last Name:");
        jPanel2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 190, 110, -1));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Email Address: ");
        jPanel2.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 230, 110, -1));

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Phone:");
        jPanel2.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 270, 110, -1));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Address:");
        jPanel2.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 310, 110, -1));

        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Username:");
        jPanel2.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 350, 110, -1));

        jLabel15.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Password:");
        jPanel2.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 390, 110, -1));

        jLabel16.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Age:");
        jPanel2.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 430, 110, -1));

        jLabel17.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("Gender:");
        jPanel2.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 470, 110, -1));

        gender.setBackground(new java.awt.Color(102, 102, 255));
        gender.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        gender.setForeground(new java.awt.Color(255, 255, 255));
        gender.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Male", "Female", "Other" }));
        gender.setToolTipText("Select Gender");
        gender.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        gender.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                genderActionPerformed(evt);
            }
        });
        jPanel2.add(gender, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 470, 280, 25));

        messageLabel.setFont(new java.awt.Font("Segoe UI", 2, 18)); // NOI18N
        messageLabel.setForeground(new java.awt.Color(255, 51, 51));
        jPanel2.add(messageLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 60, 320, 40));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 0, 440, 770));

        setBounds(0, 0, 1352, 776);
    }// </editor-fold>//GEN-END:initComponents

    private void usernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usernameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_usernameActionPerformed

    private void ageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ageActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ageActionPerformed

    private void phone_noActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_phone_noActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_phone_noActionPerformed

    private void email_addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_email_addActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_email_addActionPerformed

    private void l_nameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_l_nameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_l_nameActionPerformed

    private void f_nameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_f_nameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_f_nameActionPerformed

    private void genderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_genderActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_genderActionPerformed

    private void passwordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_passwordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_passwordActionPerformed

    private void signinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_signinActionPerformed
       LoginForm login = new LoginForm();
       login.setVisible(true);
       this.dispose();
    }//GEN-LAST:event_signinActionPerformed

    
    //This Method Perfrom Signup Operation and call Mysql Method 
    //And store data in Mysql Server
    private void signupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_signupActionPerformed
         if (checkDuplicateUsers()== true){
            messageLabel.setText("Error!,  User is already Exist");
        }
         else{
            insertSignupvalues();
         }
         
        
    }//GEN-LAST:event_signupActionPerformed

    private void address1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_address1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_address1ActionPerformed

    private void usernameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_usernameFocusLost
        if (checkDuplicateUsers()== true){
            messageLabel.setText("Error!,  User is already Exist");
        }
    }//GEN-LAST:event_usernameFocusLost

  
    
    
    //Main Method
    public static void main(String args[]) {
       
       //New Thread
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SignUpForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> gender;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel messageLabel;
    // End of variables declaration//GEN-END:variables
}
