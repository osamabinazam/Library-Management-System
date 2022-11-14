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
public class LoginForm extends javax.swing.JFrame {

    /**
     * Creates new form SignUpForm
     */
    public LoginForm() {
        initComponents();
    }
    
    
    //Validattion Method
    
   public boolean validateLogin(){
       String username = this.username.getText();
       String password= this.password.getText();
       boolean validate=true;
        if (username.isEmpty() && password.isEmpty()){
            System.out.println("112");
           messageLabel.setText("Please Enter your information");
           validate=false;
       }
       else if(username.isEmpty()){
           messageLabel.setText("Please Enter Username");
           validate=false;
       }
       else if (password.isEmpty()){
           messageLabel.setText("Please Enter Password");
           validate=false;
       }
      
       
       return validate;
   }
   
   
   //Method that varify the credentials
   public void Login(){
       String username=this.username.getText();
       String password=this.password.getText();
       try{
       
           Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/library_ms","root","toor"); 
//           String sql = "insert into users (firstname,lastname,email,phone,address,username,password,age,gender) values (?,?,?,?,?,?,?,?,?)";
           PreparedStatement pst= con.prepareStatement("select * from users where username = ? and password = ? ");
           pst.setString(1, username);
           pst.setString(2, password);
           
           ResultSet rs= pst.executeQuery();
           if(rs.next()){
               messageLabel.setText("Sucessfully Loged in");
               Home home = new Home();
               home.setVisible(true);
               this.dispose();
           }
           else{
               messageLabel.setText("Incorrent Username or Password");
           }
       
       
       
       }catch(Exception e){
           e.printStackTrace(); 
       }
   }//End Of the Login Method
   
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
        jLabel14 = new javax.swing.JLabel();
        username = new app.bolivia.swing.JCTextField();
        password = new rojerusan.RSPasswordTextPlaceHolder();
        jLabel15 = new javax.swing.JLabel();
        signup = new necesario.MaterialButton();
        signin = new necesario.MaterialButton();
        messageLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Login");
        setName("Login"); // NOI18N
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

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons/library-3.png.png"))); // NOI18N
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, 890, 570));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 900, 768));

        jPanel2.setBackground(new java.awt.Color(102, 102, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel6.setFont(new java.awt.Font("Segoe UI", 3, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 51, 0));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Login");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 35, 350, 60));

        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 22)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Username");
        jPanel2.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 250, 110, -1));

        username.setBackground(new java.awt.Color(102, 102, 255));
        username.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        username.setForeground(new java.awt.Color(255, 255, 255));
        username.setFont(new java.awt.Font("Segoe UI", 0, 22)); // NOI18N
        username.setPlaceholder("Enter Username...");
        username.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                usernameFocusLost(evt);
            }
        });
        username.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usernameActionPerformed(evt);
            }
        });
        jPanel2.add(username, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 290, 360, 40));

        password.setBackground(new java.awt.Color(102, 102, 255));
        password.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(51, 51, 51)));
        password.setForeground(new java.awt.Color(255, 255, 255));
        password.setToolTipText("");
        password.setFont(new java.awt.Font("Segoe UI", 0, 22)); // NOI18N
        password.setPhColor(new java.awt.Color(0, 0, 0));
        password.setPlaceholder("Enter Password...");
        password.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                passwordActionPerformed(evt);
            }
        });
        jPanel2.add(password, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 380, 360, 50));
        password.getAccessibleContext().setAccessibleName("");

        jLabel15.setFont(new java.awt.Font("Segoe UI", 0, 22)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Password");
        jPanel2.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 350, 110, -1));

        signup.setBackground(new java.awt.Color(255, 0, 0));
        signup.setForeground(new java.awt.Color(255, 255, 255));
        signup.setText("Sign up");
        signup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                signupActionPerformed(evt);
            }
        });
        jPanel2.add(signup, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 580, -1, -1));

        signin.setBackground(new java.awt.Color(255, 255, 255));
        signin.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, new java.awt.Color(255, 0, 0)));
        signin.setForeground(new java.awt.Color(0, 0, 0));
        signin.setText("Sign in");
        signin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                signinActionPerformed(evt);
            }
        });
        jPanel2.add(signin, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 510, -1, -1));

        messageLabel.setFont(new java.awt.Font("Segoe UI", 2, 18)); // NOI18N
        messageLabel.setForeground(new java.awt.Color(255, 51, 51));
        jPanel2.add(messageLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 110, 380, 60));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 0, 470, 770));

        setSize(new java.awt.Dimension(1379, 776));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void passwordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_passwordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_passwordActionPerformed

    private void signupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_signupActionPerformed
        SignUpForm sign = new SignUpForm();
        sign.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_signupActionPerformed

    
    private void usernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usernameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_usernameActionPerformed

    private void usernameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_usernameFocusLost
       
    }//GEN-LAST:event_usernameFocusLost

    //This Method Perfrom Signup Operation and call Mysql Method 
    //And store data in Mysql Server
    private void signinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_signinActionPerformed
            if(validateLogin()){
                Login();
            }
    }//GEN-LAST:event_signinActionPerformed

  
    
    
    //Main Method
    public static void main(String args[]) {
       
       //New Thread
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel messageLabel;
    private rojerusan.RSPasswordTextPlaceHolder password;
    private necesario.MaterialButton signin;
    private necesario.MaterialButton signup;
    private app.bolivia.swing.JCTextField username;
    // End of variables declaration//GEN-END:variables
}
