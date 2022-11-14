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
public class ReturnBookForm extends javax.swing.JFrame {

    public ReturnBookForm() {
        initComponents();
    }

    //Method That Set Book Update Count After issue a Book
    public void bookUpdateCount() {

        //Picking Values from Book Issue panel
        try {
            int bookId = Integer.parseInt(b_id.getText().trim());
            try {
                Connection conc = DriverManager.getConnection("jdbc:mysql://localhost:3306/library_ms", "root", "toor");
                String sql = " update books set book_available = book_available+1 where book_id = ? ";
                PreparedStatement pst = conc.prepareStatement(sql);
                pst.setInt(1, bookId);

                int rowCount = pst.executeUpdate();
                if (rowCount > 0) {
                    System.out.println("Book Updated Successfully");
//                    int initialCount = Integer.parseInt(b_availableLabel.getText().trim());
//                    b_availableLabel.setText(Integer.toString(initialCount-1));
                } else {
                    System.out.println("Error");
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            System.out.println("Error !,  Value is not an integer type.");
        }
    }

    //Method that fetched issue book detail from database 
    public void getIssueBook() {

//        boolean isGet=false;
        int bookId = 0;
        //Picking Values from Book Issue panel
        try {
            bookId = Integer.parseInt(b_id.getText().trim());
        } catch (Exception e) {
            System.out.println("Error !,  Value is not an integer type.");
        }

        String borrowerId = "";
        String substr = br_id.getText().trim().toLowerCase().substring(0, 3);
        if (substr.equals("stu") || substr.equals("thr") || substr.equals("ctz")) {
            System.out.println("Correct values to Borrower Text Field");
            borrowerId = br_id.getText().trim().toLowerCase();

            try {

                Connection conc = DriverManager.getConnection("jdbc:mysql://localhost:3306/library_ms", "root", "toor");
                String sql = " select * from borrow where book_id = ? and borrower_id = ? ";
                String sql1 ="select * books where book_id = ? ";
                PreparedStatement pst1=conc.prepareStatement(sql1);
                pst1.setInt(1, bookId);
                PreparedStatement pst = conc.prepareStatement(sql);
                pst.setInt(1, bookId);
                pst.setString(2, borrowerId);
                
                ResultSet rs1=pst.executeQuery();
                String bookname="";
                if(rs1.next()){
                    bookname=rs1.getString("book_title");
                }
                String dis="";
                if(substr.equals("stu")){
                    dis="Student";
                }
                else if (substr.equals("tch")){
                    dis="Teacher";
                }
                else if (substr.equals("ctz")){
                    dis="Citizen";
                }
                System.out.println("Before Setting Value ");
                ResultSet rs = pst.executeQuery();
                if (rs.next()) {
//                isGet=true;
                    issue_id.setText(Integer.toString(rs.getInt("issue_id")));
                    staff_id.setText(Integer.toString(rs.getInt("staff_id")));
                    b_title.setText(rs.getString("book_title"));
                    br_name.setText(bookname);
                    designation.setText(dis);
                    issue_date.setText(rs.getString("issue_date"));
                    due_date.setText(rs.getString("due_date"));
                    fine.setText("200");

                } else {
                    JOptionPane.showMessageDialog(this, "Record not Found");
                    issue_id.setText("");
                    staff_id.setText("");
                    b_title.setText("");
                    br_name.setText("");
                    designation.setText("");
                    issue_date.setText("");
                    due_date.setText("");
                    fine.setText("");
//                isGet=false;
                }

            } catch (Exception e) {
                    e.printStackTrace();
            }

        }

//        return isGet; 
    }
    
    
    public boolean returnBook (){
        boolean isReturned=false;
        int bookId = 0;
        //Picking Values from Book Issue panel
        try {
            bookId = Integer.parseInt(b_id.getText().trim());
        } catch (Exception e) {
            System.out.println("Error !,  Value is not an integer type.");
        }

        String borrowerId = "";
        String substr = br_id.getText().trim().toLowerCase().substring(0, 3);
        if (substr.equals("stu") || substr.equals("thr") || substr.equals("ctz")) {
            System.out.println("Correct values to Borrower Text Field");
            borrowerId = br_id.getText().trim().toLowerCase();

            try {

                Connection conc = DriverManager.getConnection("jdbc:mysql://localhost:3306/library_ms", "root", "toor");
                String sql = " update borrow set status = ? where book_id = ? and borrower_id = ? and status = ? ";
                PreparedStatement pst = conc.prepareStatement(sql);
                pst.setString(1, "Returned");
                pst.setInt(2, bookId);
                pst.setString(3, borrowerId);
                pst.setString(4, "Pending");

                int rowCount = pst.executeUpdate();
                if(rowCount > 0){
                    isReturned = true;
                }
                else {
                    isReturned=false;
                }
                
            }catch(Exception e  ){
                JOptionPane.showMessageDialog(this, "Error");
                 
            }
        }
        
        
        
        return isReturned;
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")

    //Intialization To Form
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        BookDetailsPanel = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        back = new javax.swing.JLabel();
        fine = new javax.swing.JLabel();
        tgenderLabel1 = new javax.swing.JLabel();
        tinstituteLabel1 = new javax.swing.JLabel();
        taddressLabel1 = new javax.swing.JLabel();
        tphoneLabel1 = new javax.swing.JLabel();
        temailLabel1 = new javax.swing.JLabel();
        lnameLabel1 = new javax.swing.JLabel();
        fnameLabel1 = new javax.swing.JLabel();
        tidLabel1 = new javax.swing.JLabel();
        issue_id = new javax.swing.JLabel();
        staff_id = new javax.swing.JLabel();
        b_title = new javax.swing.JLabel();
        br_name = new javax.swing.JLabel();
        designation = new javax.swing.JLabel();
        issue_date = new javax.swing.JLabel();
        due_date = new javax.swing.JLabel();
        teach_detail1 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        IssueBookPanel = new javax.swing.JPanel();
        fnameLabel2 = new javax.swing.JLabel();
        teach_detail2 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        br_id = new app.bolivia.swing.JCTextField();
        fnameLabel3 = new javax.swing.JLabel();
        b_id = new app.bolivia.swing.JCTextField();
        return_book = new necesario.MaterialButton();
        clear = new necesario.MaterialButton();
        find_book = new necesario.MaterialButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Manage Books");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        BookDetailsPanel.setBackground(new java.awt.Color(215, 215, 215));
        BookDetailsPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(215, 215, 215));

        back.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        back.setForeground(new java.awt.Color(255, 255, 255));
        back.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AddNewBookIcons/AddNewBookIcons/icons8_Rewind_48px.png"))); // NOI18N
        back.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                backMouseClicked(evt);
            }
        });
        jPanel2.add(back);

        BookDetailsPanel.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 80, 60));

        fine.setBackground(new java.awt.Color(102, 102, 255));
        fine.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        fine.setForeground(new java.awt.Color(0, 0, 0));
        BookDetailsPanel.add(fine, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 570, 360, 25));

        tgenderLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        tgenderLabel1.setForeground(new java.awt.Color(255, 51, 51));
        tgenderLabel1.setText("Fine:");
        BookDetailsPanel.add(tgenderLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 570, 80, -1));

        tinstituteLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        tinstituteLabel1.setForeground(new java.awt.Color(255, 51, 51));
        tinstituteLabel1.setText("Due Date: ");
        BookDetailsPanel.add(tinstituteLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 530, 110, -1));

        taddressLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        taddressLabel1.setForeground(new java.awt.Color(255, 51, 51));
        taddressLabel1.setText("Issue Date: ");
        BookDetailsPanel.add(taddressLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 490, 110, -1));

        tphoneLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        tphoneLabel1.setForeground(new java.awt.Color(255, 51, 51));
        tphoneLabel1.setText("Designation: ");
        BookDetailsPanel.add(tphoneLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 450, 110, -1));

        temailLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        temailLabel1.setForeground(new java.awt.Color(255, 51, 51));
        temailLabel1.setText("Borrower Name: ");
        BookDetailsPanel.add(temailLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 410, 150, -1));

        lnameLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lnameLabel1.setForeground(new java.awt.Color(255, 51, 51));
        lnameLabel1.setText("Book Title: ");
        BookDetailsPanel.add(lnameLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(27, 370, 100, -1));

        fnameLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        fnameLabel1.setForeground(new java.awt.Color(255, 51, 51));
        fnameLabel1.setText("Staff ID:");
        BookDetailsPanel.add(fnameLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 330, 100, -1));

        tidLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        tidLabel1.setForeground(new java.awt.Color(255, 51, 51));
        tidLabel1.setText("Issue ID:");
        BookDetailsPanel.add(tidLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 290, 90, -1));

        issue_id.setBackground(new java.awt.Color(102, 102, 255));
        issue_id.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        issue_id.setForeground(new java.awt.Color(0, 0, 0));
        BookDetailsPanel.add(issue_id, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 290, 360, 25));

        staff_id.setBackground(new java.awt.Color(102, 102, 255));
        staff_id.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        staff_id.setForeground(new java.awt.Color(0, 0, 0));
        BookDetailsPanel.add(staff_id, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 330, 360, 25));

        b_title.setBackground(new java.awt.Color(102, 102, 255));
        b_title.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        b_title.setForeground(new java.awt.Color(0, 0, 0));
        BookDetailsPanel.add(b_title, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 370, 360, 25));

        br_name.setBackground(new java.awt.Color(102, 102, 255));
        br_name.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        br_name.setForeground(new java.awt.Color(0, 0, 0));
        BookDetailsPanel.add(br_name, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 410, 380, 25));

        designation.setBackground(new java.awt.Color(102, 102, 255));
        designation.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        designation.setForeground(new java.awt.Color(0, 0, 0));
        BookDetailsPanel.add(designation, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 450, 360, 25));

        issue_date.setBackground(new java.awt.Color(102, 102, 255));
        issue_date.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        issue_date.setForeground(new java.awt.Color(0, 0, 0));
        BookDetailsPanel.add(issue_date, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 490, 360, 25));

        due_date.setBackground(new java.awt.Color(102, 102, 255));
        due_date.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        due_date.setForeground(new java.awt.Color(0, 0, 0));
        BookDetailsPanel.add(due_date, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 530, 360, 25));

        teach_detail1.setBackground(new java.awt.Color(102, 102, 255));
        teach_detail1.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        teach_detail1.setForeground(new java.awt.Color(255, 51, 51));
        teach_detail1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        teach_detail1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AddNewBookIcons/AddNewBookIcons/icons8_Book_50px_1.png"))); // NOI18N
        teach_detail1.setText(" Issue Details");
        teach_detail1.setAutoscrolls(true);
        BookDetailsPanel.add(teach_detail1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 130, 360, 100));

        jPanel5.setBackground(new java.awt.Color(255, 0, 0));
        BookDetailsPanel.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 230, 330, 5));

        getContentPane().add(BookDetailsPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 590, 760));

        IssueBookPanel.setBackground(new java.awt.Color(255, 255, 255));
        IssueBookPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        fnameLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        fnameLabel2.setForeground(new java.awt.Color(255, 51, 51));
        fnameLabel2.setText("Borrower ID:");
        IssueBookPanel.add(fnameLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 470, 100, 30));

        teach_detail2.setBackground(new java.awt.Color(102, 102, 255));
        teach_detail2.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        teach_detail2.setForeground(new java.awt.Color(255, 51, 51));
        teach_detail2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        teach_detail2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AddNewBookIcons/AddNewBookIcons/icons8_Books_52px_1.png"))); // NOI18N
        teach_detail2.setText("  Return Book");
        teach_detail2.setAutoscrolls(true);
        IssueBookPanel.add(teach_detail2, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 150, 330, 70));

        jPanel4.setBackground(new java.awt.Color(255, 0, 0));
        IssueBookPanel.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 230, 290, 5));

        br_id.setBackground(new java.awt.Color(255, 255, 255));
        br_id.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 51, 51)));
        br_id.setForeground(new java.awt.Color(0, 0, 0));
        br_id.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        br_id.setPlaceholder("Borrower (Student, Teacher, Citizen) ID...");
        br_id.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                br_idActionPerformed(evt);
            }
        });
        IssueBookPanel.add(br_id, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 470, 360, 30));

        fnameLabel3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        fnameLabel3.setForeground(new java.awt.Color(255, 51, 51));
        fnameLabel3.setText("Book ID:");
        IssueBookPanel.add(fnameLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 380, 100, 30));

        b_id.setBackground(new java.awt.Color(255, 255, 255));
        b_id.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 51, 51)));
        b_id.setForeground(new java.awt.Color(0, 0, 0));
        b_id.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        b_id.setPlaceholder("Enter Book ID...");
        b_id.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_idActionPerformed(evt);
            }
        });
        IssueBookPanel.add(b_id, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 380, 360, 30));

        return_book.setBackground(new java.awt.Color(255, 51, 51));
        return_book.setText("Return Book");
        return_book.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                return_bookActionPerformed(evt);
            }
        });
        IssueBookPanel.add(return_book, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 560, 130, 40));

        clear.setBackground(new java.awt.Color(204, 204, 204));
        clear.setForeground(new java.awt.Color(0, 0, 0));
        clear.setText("Clear");
        clear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearActionPerformed(evt);
            }
        });
        IssueBookPanel.add(clear, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 560, 110, -1));

        find_book.setBackground(new java.awt.Color(204, 204, 204));
        find_book.setForeground(new java.awt.Color(0, 0, 0));
        find_book.setText("Find");
        find_book.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                find_bookActionPerformed(evt);
            }
        });
        IssueBookPanel.add(find_book, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 560, 110, -1));

        getContentPane().add(IssueBookPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 0, 780, 760));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void backMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backMouseClicked
        Home home = new Home();
        home.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_backMouseClicked

    private void br_idActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_br_idActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_br_idActionPerformed

    private void b_idActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_idActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_b_idActionPerformed

    private void clearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_clearActionPerformed

    private void find_bookActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_find_bookActionPerformed
        
        
        getIssueBook();
        
    }//GEN-LAST:event_find_bookActionPerformed

    private void return_bookActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_return_bookActionPerformed
        // TODO add your handling code here:
        
        if(returnBook()){
            JOptionPane.showMessageDialog(this,"Successfully Return Book ");
            bookUpdateCount();
        }
        else 
            JOptionPane.showMessageDialog(this,"Failed");
    }//GEN-LAST:event_return_bookActionPerformed

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
            java.util.logging.Logger.getLogger(ReturnBookForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ReturnBookForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ReturnBookForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ReturnBookForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
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
                new ReturnBookForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel BookDetailsPanel;
    private javax.swing.JPanel IssueBookPanel;
    private app.bolivia.swing.JCTextField b_id;
    private javax.swing.JLabel b_title;
    private javax.swing.JLabel back;
    private app.bolivia.swing.JCTextField br_id;
    private javax.swing.JLabel br_name;
    private necesario.MaterialButton clear;
    private javax.swing.JLabel designation;
    private javax.swing.JLabel due_date;
    private necesario.MaterialButton find_book;
    private javax.swing.JLabel fine;
    private javax.swing.JLabel fnameLabel1;
    private javax.swing.JLabel fnameLabel2;
    private javax.swing.JLabel fnameLabel3;
    private javax.swing.JLabel issue_date;
    private javax.swing.JLabel issue_id;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JLabel lnameLabel1;
    private necesario.MaterialButton return_book;
    private javax.swing.JLabel staff_id;
    private javax.swing.JLabel taddressLabel1;
    private javax.swing.JLabel teach_detail1;
    private javax.swing.JLabel teach_detail2;
    private javax.swing.JLabel temailLabel1;
    private javax.swing.JLabel tgenderLabel1;
    private javax.swing.JLabel tidLabel1;
    private javax.swing.JLabel tinstituteLabel1;
    private javax.swing.JLabel tphoneLabel1;
    // End of variables declaration//GEN-END:variables
}
