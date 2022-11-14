/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package jframe;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author kazii
 *
 *
 */
public class IssueBookForm extends javax.swing.JFrame {

    public IssueBookForm() {
        initComponents();
    }

    //Fetch Book Details From Database and set it to book details Panel
    public void getBookDetails() {
        int bId = Integer.parseInt(b_id.getText().trim());
        try {
            Connection conc = DriverManager.getConnection("jdbc:mysql://localhost:3306/library_ms", "root", "toor");
            String sql = "select * from books where book_id = ?";
            PreparedStatement pst = conc.prepareStatement(sql);
            pst.setInt(1, bId);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
//                String bookId = String.parseString(rs.getInt("book_id"));
                b_idLabel.setText(rs.getString("book_id"));
                b_titleLabel.setText(rs.getString("book_title"));
                b_authorLabel.setText(rs.getString("book_author"));
                b_editionLabel.setText(rs.getString("book_edition"));
                b_copiesLabel.setText(rs.getString("book_copies"));
                b_availableLabel.setText(rs.getString("book_available"));
                b_publisherLabel.setText(rs.getString("book_publisher"));
                b_costLabel.setText(rs.getString("book_price"));
            }

            System.out.println("Successfully Fetched Book From Database......\n\n");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void changeLabel() {
        binstituteLabel1.setText("CNIC");
        br_addressLabel.setText("");
        bdepart.setText("Gender");
        br_departmentLabel.setText("");
        bgender.setText("");
    }

    public void revertLabel() {
        binstituteLabel1.setText("Istitute");
        br_addressLabel.setText("");
        bdepart.setText("Department");
        br_departmentLabel.setText("");
        bgender.setText("Gender");

    }

    //Method That Fetched Detail of Student from database
    public void getBorrowerDetails() {
        String Id = borrower_id.getText().trim().toLowerCase();
        String Substr = Id.substring(0, 3);
        System.out.println("Sub Sting : " + Substr);

        try {
            Connection conc = DriverManager.getConnection("jdbc:mysql://localhost:3306/library_ms", "root", "toor");

            String sql = "";

            if (Substr.equals("stu")) {
                revertLabel();
                sql = "select * from student where sid = ?";
            } else if (Substr.equals("tch")) {
                revertLabel();
                sql = "select * from teacher where tid = ? ";
            } else if (Substr.equals("ctz")) {
                changeLabel();
                getCitizensDetails();
                return;

            }

            PreparedStatement pst = conc.prepareStatement(sql);
            pst.setString(1, Id);
            ResultSet rst = pst.executeQuery();
            System.out.println("Before While");
            while (rst.next()) {
                System.out.print("In While");
//                String bookId = String.parseString(rs.getInt("book_id"));
                br_idLabel.setText(rst.getString(1));
                br_fnameLabel.setText(rst.getString("fname"));
                br_lnameLabel.setText(rst.getString("lname"));
                br_phoneLabel.setText(rst.getString("phone"));
                br_emailLabel.setText(rst.getString("email"));
                br_addressLabel.setText(rst.getString("address"));
                br_departmentLabel.setText(rst.getString("depart"));
                br_instituteLabel.setText(rst.getString("institute"));
                br_genderLabel.setText(rst.getString("gender"));
            }

            System.out.println("Successfully Fetched Brrower Record From Database......\n\n");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //Method That Fetched Detail of teachers From Database And set it to Borrowers detail Panel
    //Method That Fetched Detail of Citizens From Database And set it to Borrowers detail Panel
    public void getCitizensDetails() {
        String Id = borrower_id.getText().trim();
        try {
            Connection conc = DriverManager.getConnection("jdbc:mysql://localhost:3306/library_ms", "root", "toor");
            String sql = "select * from citizen where cid = ?";
            PreparedStatement pst = conc.prepareStatement(sql);
            pst.setString(1, Id);
            ResultSet rst = pst.executeQuery();
            System.out.println("Before While");
            while (rst.next()) {

                br_idLabel.setText(rst.getString(1));
                br_fnameLabel.setText(rst.getString(2));
                br_lnameLabel.setText(rst.getString(3));
                br_phoneLabel.setText(rst.getString(4));

                br_addressLabel.setText(rst.getString(5));
                br_departmentLabel.setText(rst.getString(7));
                br_instituteLabel.setText(rst.getString(6));
//              
            }

            System.out.println("Successfully Fetched Citizen Record From Database......\n\n");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //Method That Add Issue Books Detail to Database
    public boolean issueBook() {
        boolean isAdded = false;

        int bookId = 0;
        int staffId = 0;
        //Picking Values from Book Issue panel
        try {
            bookId = Integer.parseInt(b_id.getText().trim());
            staffId = Integer.parseInt(staff_id.getText().trim());
        } catch (Exception e) {
            System.out.println("Error !,  Value is not an integer type.");
        }

        String borrowerId = "";
        String substr = borrower_id.getText().trim().toLowerCase().substring(0, 3);
        System.out.println(substr);

        if (substr.equals("stu") || substr.equals("tch") || substr.equals("ctz")) {
            System.out.println("Correct values to Borrower Text Field");
            borrowerId = borrower_id.getText().trim();
        }
        

        java.util.Date issueDate = issue_date.getDatoFecha();
        java.util.Date dueDate = due_date.getDatoFecha();

        long l_issue = issueDate.getTime();
        long l_due = dueDate.getTime();

        java.sql.Date dIssueDate = new java.sql.Date(l_issue);
        java.sql.Date dDueDate = new java.sql.Date(l_due);
        
        System.out.println(dIssueDate);
        System.out.println(dDueDate);

        //End Picking Data And Setting Constraints On It.
        try {

            Connection conc = DriverManager.getConnection("jdbc:mysql://localhost:3306/library_ms", "root", "toor");
            String sql = "INSERT INTO borrow (borrower_id,staff_id,book_id,issue_date,due_date,status)values (?,?,?,?,?,?)";
            PreparedStatement pst = conc.prepareStatement(sql);
            pst.setString(1, borrowerId);
            pst.setInt(2, staffId);
            pst.setInt(3, bookId);
            
            pst.setDate(4, dIssueDate);
            pst.setDate(5, dDueDate);
            pst.setString(6,"pending");

            int rowCount = pst.executeUpdate();
            if (rowCount > 0) {
                isAdded = true;
            } else {
                isAdded = false;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return isAdded;
    }

    //Method That Set Book Update Count After issue a Book
    public void bookUpdateCount() {

        //Picking Values from Book Issue panel
        try {
            int bookId = Integer.parseInt(b_id.getText().trim());
            try {
                Connection conc = DriverManager.getConnection("jdbc:mysql://localhost:3306/library_ms", "root", "toor");
                String sql = " update books set book_available = book_available-1 where book_id = ? ";
                PreparedStatement pst = conc.prepareStatement(sql);
                pst.setInt(1, bookId);

                int rowCount = pst.executeUpdate();
                if (rowCount > 0) {
                    System.out.println("Book Updated Successfully");
                    int initialCount = Integer.parseInt(b_availableLabel.getText().trim());
                    b_availableLabel.setText(Integer.toString(initialCount - 1));
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

    //Method that check wheather the the book is issued or not 
    public boolean isAlreadyIssue() {
        boolean isIssue = false;
        int bookId = 0;
        //Picking Values from Book Issue panel
        try {
            bookId = Integer.parseInt(b_id.getText().trim());
        } catch (Exception e) {
            System.out.println("Error !,  Value is not an integer type.");
        }

        String borrowerId = "";
        String substr = borrower_id.getText().trim().toLowerCase().substring(0, 3);
        if (substr.equals("stu") || substr.equals("thr") || substr.equals("ctz")) {
            System.out.println("Correct values to Borrower Text Field");
            borrowerId = borrower_id.getText().trim().toLowerCase();

            try {

                Connection conc = DriverManager.getConnection("jdbc:mysql://localhost:3306/library_ms", "root", "toor");
                String sql = " select * from borrow where book_id = ? and borrower_id = ? ";
                PreparedStatement pst = conc.prepareStatement(sql);
                pst.setInt(1, bookId);
                pst.setString(2, borrowerId);

                ResultSet rs = pst.executeQuery();
                
                System.out.print("before While Already Issue Bookk Method");
                while(rs.next()) {
                    isIssue = true;
                } 

            } catch (Exception e) {

            }

        }

        return isIssue;
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
        tidLabel = new javax.swing.JLabel();
        fnameLabel = new javax.swing.JLabel();
        lnameLabel = new javax.swing.JLabel();
        temailLabel = new javax.swing.JLabel();
        tphoneLabel = new javax.swing.JLabel();
        taddressLabel = new javax.swing.JLabel();
        tinstituteLabel = new javax.swing.JLabel();
        tgenderLabel = new javax.swing.JLabel();
        teach_detail = new javax.swing.JLabel();
        b_costLabel = new javax.swing.JLabel();
        b_idLabel = new javax.swing.JLabel();
        b_titleLabel = new javax.swing.JLabel();
        b_authorLabel = new javax.swing.JLabel();
        b_editionLabel = new javax.swing.JLabel();
        b_copiesLabel = new javax.swing.JLabel();
        b_availableLabel = new javax.swing.JLabel();
        b_publisherLabel = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        StudentDetailPanel = new javax.swing.JPanel();
        tidLabel1 = new javax.swing.JLabel();
        fnameLabel1 = new javax.swing.JLabel();
        lnameLabel1 = new javax.swing.JLabel();
        temailLabel1 = new javax.swing.JLabel();
        tphoneLabel1 = new javax.swing.JLabel();
        taddressLabel1 = new javax.swing.JLabel();
        binstituteLabel1 = new javax.swing.JLabel();
        bgender = new javax.swing.JLabel();
        teach_detail1 = new javax.swing.JLabel();
        br_genderLabel = new javax.swing.JLabel();
        br_idLabel = new javax.swing.JLabel();
        br_fnameLabel = new javax.swing.JLabel();
        br_lnameLabel = new javax.swing.JLabel();
        br_emailLabel = new javax.swing.JLabel();
        br_phoneLabel = new javax.swing.JLabel();
        br_addressLabel = new javax.swing.JLabel();
        br_instituteLabel = new javax.swing.JLabel();
        bdepart = new javax.swing.JLabel();
        br_departmentLabel = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        IssueBookPanel = new javax.swing.JPanel();
        fnameLabel2 = new javax.swing.JLabel();
        teach_detail2 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        borrower_id = new app.bolivia.swing.JCTextField();
        fnameLabel3 = new javax.swing.JLabel();
        b_id = new app.bolivia.swing.JCTextField();
        fnameLabel4 = new javax.swing.JLabel();
        fnameLabel5 = new javax.swing.JLabel();
        staff_id = new app.bolivia.swing.JCTextField();
        fnameLabel6 = new javax.swing.JLabel();
        due_date = new rojeru_san.componentes.RSDateChooser();
        issue_date = new rojeru_san.componentes.RSDateChooser();
        issue_book = new necesario.MaterialButton();
        clear = new necesario.MaterialButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Manage Books");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        BookDetailsPanel.setBackground(new java.awt.Color(102, 102, 255));
        BookDetailsPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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

        BookDetailsPanel.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 80, 60));

        tidLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        tidLabel.setForeground(new java.awt.Color(255, 255, 255));
        tidLabel.setText("ID:");
        BookDetailsPanel.add(tidLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 290, 70, -1));

        fnameLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        fnameLabel.setForeground(new java.awt.Color(255, 255, 255));
        fnameLabel.setText("Title:");
        BookDetailsPanel.add(fnameLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 330, 70, -1));

        lnameLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lnameLabel.setForeground(new java.awt.Color(255, 255, 255));
        lnameLabel.setText("Author:");
        BookDetailsPanel.add(lnameLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 370, 70, -1));

        temailLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        temailLabel.setForeground(new java.awt.Color(255, 255, 255));
        temailLabel.setText("Edition:");
        BookDetailsPanel.add(temailLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 410, 70, -1));

        tphoneLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        tphoneLabel.setForeground(new java.awt.Color(255, 255, 255));
        tphoneLabel.setText("Copies:");
        BookDetailsPanel.add(tphoneLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 450, 70, -1));

        taddressLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        taddressLabel.setForeground(new java.awt.Color(255, 255, 255));
        taddressLabel.setText("Available:");
        BookDetailsPanel.add(taddressLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 490, 80, -1));

        tinstituteLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        tinstituteLabel.setForeground(new java.awt.Color(255, 255, 255));
        tinstituteLabel.setText("Publisher:");
        BookDetailsPanel.add(tinstituteLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 530, 80, -1));

        tgenderLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        tgenderLabel.setForeground(new java.awt.Color(255, 255, 255));
        tgenderLabel.setText("Cost:");
        BookDetailsPanel.add(tgenderLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 570, 70, -1));

        teach_detail.setBackground(new java.awt.Color(102, 102, 255));
        teach_detail.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        teach_detail.setForeground(new java.awt.Color(255, 51, 51));
        teach_detail.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        teach_detail.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AddNewBookIcons/AddNewBookIcons/icons8_Book_50px_1.png"))); // NOI18N
        teach_detail.setText(" Book Details");
        teach_detail.setAutoscrolls(true);
        BookDetailsPanel.add(teach_detail, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 140, 370, 90));

        b_costLabel.setBackground(new java.awt.Color(102, 102, 255));
        b_costLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        b_costLabel.setForeground(new java.awt.Color(255, 255, 255));
        BookDetailsPanel.add(b_costLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 570, 250, 25));

        b_idLabel.setBackground(new java.awt.Color(102, 102, 255));
        b_idLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        b_idLabel.setForeground(new java.awt.Color(255, 255, 255));
        BookDetailsPanel.add(b_idLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 290, 250, 25));

        b_titleLabel.setBackground(new java.awt.Color(102, 102, 255));
        b_titleLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        b_titleLabel.setForeground(new java.awt.Color(255, 255, 255));
        BookDetailsPanel.add(b_titleLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 330, 250, 25));

        b_authorLabel.setBackground(new java.awt.Color(102, 102, 255));
        b_authorLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        b_authorLabel.setForeground(new java.awt.Color(255, 255, 255));
        BookDetailsPanel.add(b_authorLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 370, 250, 25));

        b_editionLabel.setBackground(new java.awt.Color(102, 102, 255));
        b_editionLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        b_editionLabel.setForeground(new java.awt.Color(255, 255, 255));
        BookDetailsPanel.add(b_editionLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 410, 250, 25));

        b_copiesLabel.setBackground(new java.awt.Color(102, 102, 255));
        b_copiesLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        b_copiesLabel.setForeground(new java.awt.Color(255, 255, 255));
        BookDetailsPanel.add(b_copiesLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 450, 250, 25));

        b_availableLabel.setBackground(new java.awt.Color(102, 102, 255));
        b_availableLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        b_availableLabel.setForeground(new java.awt.Color(255, 255, 255));
        BookDetailsPanel.add(b_availableLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 490, 250, 25));

        b_publisherLabel.setBackground(new java.awt.Color(102, 102, 255));
        b_publisherLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        b_publisherLabel.setForeground(new java.awt.Color(255, 255, 255));
        BookDetailsPanel.add(b_publisherLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 530, 250, 25));

        jPanel6.setBackground(new java.awt.Color(255, 0, 0));
        BookDetailsPanel.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 230, 330, 5));

        getContentPane().add(BookDetailsPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 370, 760));

        StudentDetailPanel.setBackground(new java.awt.Color(215, 215, 215));
        StudentDetailPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tidLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        tidLabel1.setForeground(new java.awt.Color(255, 51, 51));
        tidLabel1.setText("ID:");
        StudentDetailPanel.add(tidLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 290, 80, -1));

        fnameLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        fnameLabel1.setForeground(new java.awt.Color(255, 51, 51));
        fnameLabel1.setText("First Name:");
        StudentDetailPanel.add(fnameLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 330, 90, -1));

        lnameLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lnameLabel1.setForeground(new java.awt.Color(255, 51, 51));
        lnameLabel1.setText("Last Name:");
        StudentDetailPanel.add(lnameLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 370, -1, -1));

        temailLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        temailLabel1.setForeground(new java.awt.Color(255, 51, 51));
        temailLabel1.setText("Email:");
        StudentDetailPanel.add(temailLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 410, 80, -1));

        tphoneLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        tphoneLabel1.setForeground(new java.awt.Color(255, 51, 51));
        tphoneLabel1.setText("Phone:");
        StudentDetailPanel.add(tphoneLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 450, 80, -1));

        taddressLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        taddressLabel1.setForeground(new java.awt.Color(255, 51, 51));
        taddressLabel1.setText("Address:");
        StudentDetailPanel.add(taddressLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 490, 80, -1));

        binstituteLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        binstituteLabel1.setForeground(new java.awt.Color(255, 51, 51));
        binstituteLabel1.setText("Institute:");
        StudentDetailPanel.add(binstituteLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 530, 80, -1));

        bgender.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        bgender.setForeground(new java.awt.Color(255, 51, 51));
        bgender.setText("Gender:");
        StudentDetailPanel.add(bgender, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 610, 70, -1));

        teach_detail1.setBackground(new java.awt.Color(102, 102, 255));
        teach_detail1.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        teach_detail1.setForeground(new java.awt.Color(255, 51, 51));
        teach_detail1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        teach_detail1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AddNewBookIcons/AddNewBookIcons/icons8_Student_Male_100px.png"))); // NOI18N
        teach_detail1.setText(" Borrower Details");
        teach_detail1.setAutoscrolls(true);
        StudentDetailPanel.add(teach_detail1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, 360, 100));

        br_genderLabel.setBackground(new java.awt.Color(102, 102, 255));
        br_genderLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        br_genderLabel.setForeground(new java.awt.Color(0, 0, 0));
        StudentDetailPanel.add(br_genderLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 610, 220, 25));

        br_idLabel.setBackground(new java.awt.Color(102, 102, 255));
        br_idLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        br_idLabel.setForeground(new java.awt.Color(0, 0, 0));
        StudentDetailPanel.add(br_idLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 290, 230, 25));

        br_fnameLabel.setBackground(new java.awt.Color(102, 102, 255));
        br_fnameLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        br_fnameLabel.setForeground(new java.awt.Color(0, 0, 0));
        StudentDetailPanel.add(br_fnameLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 330, 230, 25));

        br_lnameLabel.setBackground(new java.awt.Color(102, 102, 255));
        br_lnameLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        br_lnameLabel.setForeground(new java.awt.Color(0, 0, 0));
        StudentDetailPanel.add(br_lnameLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 370, 230, 25));

        br_emailLabel.setBackground(new java.awt.Color(102, 102, 255));
        br_emailLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        br_emailLabel.setForeground(new java.awt.Color(0, 0, 0));
        StudentDetailPanel.add(br_emailLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 410, 230, 25));

        br_phoneLabel.setBackground(new java.awt.Color(102, 102, 255));
        br_phoneLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        br_phoneLabel.setForeground(new java.awt.Color(0, 0, 0));
        StudentDetailPanel.add(br_phoneLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 450, 230, 25));

        br_addressLabel.setBackground(new java.awt.Color(102, 102, 255));
        br_addressLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        br_addressLabel.setForeground(new java.awt.Color(0, 0, 0));
        StudentDetailPanel.add(br_addressLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 490, 230, 25));

        br_instituteLabel.setBackground(new java.awt.Color(102, 102, 255));
        br_instituteLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        br_instituteLabel.setForeground(new java.awt.Color(0, 0, 0));
        StudentDetailPanel.add(br_instituteLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 530, 230, 25));

        bdepart.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        bdepart.setForeground(new java.awt.Color(255, 51, 51));
        bdepart.setText("Department:");
        StudentDetailPanel.add(bdepart, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 570, 110, -1));

        br_departmentLabel.setBackground(new java.awt.Color(102, 102, 255));
        br_departmentLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        br_departmentLabel.setForeground(new java.awt.Color(0, 0, 0));
        StudentDetailPanel.add(br_departmentLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 570, 220, 25));

        jPanel5.setBackground(new java.awt.Color(255, 0, 0));
        StudentDetailPanel.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 230, 330, 5));

        getContentPane().add(StudentDetailPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 0, 400, 760));

        IssueBookPanel.setBackground(new java.awt.Color(255, 255, 255));
        IssueBookPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        fnameLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        fnameLabel2.setForeground(new java.awt.Color(255, 51, 51));
        fnameLabel2.setText("Due Date:");
        IssueBookPanel.add(fnameLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 540, 100, 30));

        teach_detail2.setBackground(new java.awt.Color(102, 102, 255));
        teach_detail2.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        teach_detail2.setForeground(new java.awt.Color(255, 51, 51));
        teach_detail2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        teach_detail2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AddNewBookIcons/AddNewBookIcons/icons8_Books_52px_1.png"))); // NOI18N
        teach_detail2.setText("  Issue Book");
        teach_detail2.setAutoscrolls(true);
        IssueBookPanel.add(teach_detail2, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 150, 330, 70));

        jPanel4.setBackground(new java.awt.Color(255, 0, 0));
        IssueBookPanel.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 230, 270, 5));

        borrower_id.setBackground(new java.awt.Color(255, 255, 255));
        borrower_id.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 51, 51)));
        borrower_id.setForeground(new java.awt.Color(0, 0, 0));
        borrower_id.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        borrower_id.setPlaceholder("Borrower (Student, Teacher, Citizen) ID...");
        borrower_id.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                borrower_idFocusLost(evt);
            }
        });
        borrower_id.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                borrower_idActionPerformed(evt);
            }
        });
        IssueBookPanel.add(borrower_id, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 420, 320, 30));

        fnameLabel3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        fnameLabel3.setForeground(new java.awt.Color(255, 51, 51));
        fnameLabel3.setText("Book ID:");
        IssueBookPanel.add(fnameLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 300, 100, 30));

        b_id.setBackground(new java.awt.Color(255, 255, 255));
        b_id.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 51, 51)));
        b_id.setForeground(new java.awt.Color(0, 0, 0));
        b_id.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        b_id.setPlaceholder("Enter Book ID...");
        b_id.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                b_idFocusLost(evt);
            }
        });
        b_id.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_idActionPerformed(evt);
            }
        });
        IssueBookPanel.add(b_id, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 300, 320, 30));

        fnameLabel4.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        fnameLabel4.setForeground(new java.awt.Color(255, 51, 51));
        fnameLabel4.setText("Staff ID:");
        IssueBookPanel.add(fnameLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 360, 100, 30));

        fnameLabel5.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        fnameLabel5.setForeground(new java.awt.Color(255, 51, 51));
        fnameLabel5.setText("Borrower ID:");
        IssueBookPanel.add(fnameLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 420, 100, 30));

        staff_id.setBackground(new java.awt.Color(255, 255, 255));
        staff_id.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 51, 51)));
        staff_id.setForeground(new java.awt.Color(0, 0, 0));
        staff_id.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        staff_id.setPlaceholder("Enter Staff ID...");
        staff_id.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                staff_idActionPerformed(evt);
            }
        });
        IssueBookPanel.add(staff_id, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 360, 320, 30));

        fnameLabel6.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        fnameLabel6.setForeground(new java.awt.Color(255, 51, 51));
        fnameLabel6.setText("Issue Date:");
        IssueBookPanel.add(fnameLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 480, 100, 30));

        due_date.setColorBackground(new java.awt.Color(255, 51, 51));
        due_date.setColorForeground(new java.awt.Color(255, 51, 51));
        due_date.setPlaceholder("Due Date");
        IssueBookPanel.add(due_date, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 540, 320, -1));

        issue_date.setColorBackground(new java.awt.Color(255, 51, 51));
        issue_date.setColorForeground(new java.awt.Color(255, 51, 51));
        issue_date.setPlaceholder("Issue Date");
        IssueBookPanel.add(issue_date, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 480, 320, -1));

        issue_book.setBackground(new java.awt.Color(255, 51, 51));
        issue_book.setText("Issue Book");
        issue_book.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                issue_bookActionPerformed(evt);
            }
        });
        IssueBookPanel.add(issue_book, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 620, 120, 40));

        clear.setBackground(new java.awt.Color(204, 204, 204));
        clear.setForeground(new java.awt.Color(0, 0, 0));
        clear.setText("Clear");
        clear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearActionPerformed(evt);
            }
        });
        IssueBookPanel.add(clear, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 620, 110, -1));

        getContentPane().add(IssueBookPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 0, 600, 760));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void backMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backMouseClicked
        Home home = new Home();
        home.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_backMouseClicked

    private void borrower_idActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_borrower_idActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_borrower_idActionPerformed

    private void b_idActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_idActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_b_idActionPerformed

    private void staff_idActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_staff_idActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_staff_idActionPerformed

    private void clearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearActionPerformed
        // TODO add your handling code here:

        b_id.setText("");
        staff_id.setText("");
        borrower_id.setText("");

        b_idLabel.setText("");
        b_titleLabel.setText("");
        b_authorLabel.setText("");
        b_editionLabel.setText("");
        b_copiesLabel.setText("");
        b_availableLabel.setText("");
        b_publisherLabel.setText("");
        b_costLabel.setText("");

        br_idLabel.setText("");
        br_fnameLabel.setText("");
        br_lnameLabel.setText("");
        br_phoneLabel.setText("");
        br_emailLabel.setText("");
        br_addressLabel.setText("");
        br_departmentLabel.setText("");
        br_instituteLabel.setText("");
        br_genderLabel.setText("");

    }//GEN-LAST:event_clearActionPerformed

    private void b_idFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_b_idFocusLost
        // TODO add your handling code here:
        System.out.println("In Book ID Focus Lost Method\n");
        if (!(b_id.getText().isEmpty()))
            getBookDetails();
    }//GEN-LAST:event_b_idFocusLost

    private void borrower_idFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_borrower_idFocusLost
        // TODO add your handling code here:
        System.out.println("In borrower ID Focus Lost Method\n");
        if (!(borrower_id.getText().isEmpty())) {
            getBorrowerDetails();
        }

    }//GEN-LAST:event_borrower_idFocusLost

    private void issue_bookActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_issue_bookActionPerformed
        // TODO add your handling code here:
        if (b_availableLabel.getText().trim().toLowerCase().equals("0")) {
            JOptionPane.showMessageDialog(this, "Book is not Available");

        } else {
            if (!(isAlreadyIssue())) {
                if (issueBook()) {
                    System.out.println("SuccessFully Book issue Detail in Database");
                    bookUpdateCount();
                    JOptionPane.showMessageDialog(this, "record Added Successfully");
                } else {
                    JOptionPane.showMessageDialog(this, "Error, Can't Add Record To Database");
                }
            } else {
                JOptionPane.showMessageDialog(this, "This Student ALready issued This Book");
            }
        }

    }//GEN-LAST:event_issue_bookActionPerformed

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
            java.util.logging.Logger.getLogger(IssueBookForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(IssueBookForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(IssueBookForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(IssueBookForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new IssueBookForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel BookDetailsPanel;
    private javax.swing.JPanel IssueBookPanel;
    private javax.swing.JPanel StudentDetailPanel;
    private javax.swing.JLabel b_authorLabel;
    private javax.swing.JLabel b_availableLabel;
    private javax.swing.JLabel b_copiesLabel;
    private javax.swing.JLabel b_costLabel;
    private javax.swing.JLabel b_editionLabel;
    private app.bolivia.swing.JCTextField b_id;
    private javax.swing.JLabel b_idLabel;
    private javax.swing.JLabel b_publisherLabel;
    private javax.swing.JLabel b_titleLabel;
    private javax.swing.JLabel back;
    private javax.swing.JLabel bdepart;
    private javax.swing.JLabel bgender;
    private javax.swing.JLabel binstituteLabel1;
    private app.bolivia.swing.JCTextField borrower_id;
    private javax.swing.JLabel br_addressLabel;
    private javax.swing.JLabel br_departmentLabel;
    private javax.swing.JLabel br_emailLabel;
    private javax.swing.JLabel br_fnameLabel;
    private javax.swing.JLabel br_genderLabel;
    private javax.swing.JLabel br_idLabel;
    private javax.swing.JLabel br_instituteLabel;
    private javax.swing.JLabel br_lnameLabel;
    private javax.swing.JLabel br_phoneLabel;
    private necesario.MaterialButton clear;
    private rojeru_san.componentes.RSDateChooser due_date;
    private javax.swing.JLabel fnameLabel;
    private javax.swing.JLabel fnameLabel1;
    private javax.swing.JLabel fnameLabel2;
    private javax.swing.JLabel fnameLabel3;
    private javax.swing.JLabel fnameLabel4;
    private javax.swing.JLabel fnameLabel5;
    private javax.swing.JLabel fnameLabel6;
    private necesario.MaterialButton issue_book;
    private rojeru_san.componentes.RSDateChooser issue_date;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JLabel lnameLabel;
    private javax.swing.JLabel lnameLabel1;
    private app.bolivia.swing.JCTextField staff_id;
    private javax.swing.JLabel taddressLabel;
    private javax.swing.JLabel taddressLabel1;
    private javax.swing.JLabel teach_detail;
    private javax.swing.JLabel teach_detail1;
    private javax.swing.JLabel teach_detail2;
    private javax.swing.JLabel temailLabel;
    private javax.swing.JLabel temailLabel1;
    private javax.swing.JLabel tgenderLabel;
    private javax.swing.JLabel tidLabel;
    private javax.swing.JLabel tidLabel1;
    private javax.swing.JLabel tinstituteLabel;
    private javax.swing.JLabel tphoneLabel;
    private javax.swing.JLabel tphoneLabel1;
    // End of variables declaration//GEN-END:variables
}
