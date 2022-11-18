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
public class ManageBookForm extends javax.swing.JFrame {
    
    String book_title,book_author,book_publisher,book_edition;
    int book_id,book_copies,book_available;
    double book_price;
    DefaultTableModel model;
    DatabaseHandler db = new DatabaseHandler();
    
    String databaseName,tableName;

    /**
     * Creates new form ManageBookForm
     */
    public ManageBookForm() {
        databaseName = "library_ms";
        tableName = "books";
        initComponents();
        setBookDetailTable(); 
    }
    
       public void setBookDetailTable(){
        try {
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306","root",""); 
           if (db.isDatabaseExists (con, "library_ms")){
              
                    Connection newCon = DriverManager.getConnection("jdbc:mysql://localhost:3306/library_ms", "root", "");
                    Statement stmt= newCon.createStatement();
                    ResultSet rset= stmt.executeQuery("select * from books");

                    while (rset.next()){
                                System.out.println(rset.getString(1));
                                
                                String bookId = rset.getString(1);
                                String bookCop=rset.getString(5);
                                String bookAva=rset.getString(6);
                                String bookTitle=rset.getString(2);
                                String bookAuthor=rset.getString(3);
                                String bookPublisher=rset.getString(7);
                                String bookEdition=rset.getString(4);
                                String bookPrice=rset.getString(8);
                                

                               Object [] obj = {bookId,bookTitle,bookAuthor,bookEdition,bookCop,bookAva,bookPublisher,bookPrice}; 
                               model = (DefaultTableModel) bookTable.getModel();
                               model.addRow(obj);
                 
                    }
           }
           else {
                    System.out.println("Database Not Found");
           }
        }catch(Exception e )
        {
//                JOptionPane.showMessageDialog(this, "Database Not Found");
        }
    }

    public boolean addBook(){
        boolean isAdded=false;                                  //Check Wheather Book is Added or not
        boolean checkException=true;                    //Check Exceptions and take decision on the basis of them
        
        if (bTitle.getText().isEmpty() || bAuthor.getText().isEmpty() || bEdition.getText().isEmpty() || bPublisher.getText().isEmpty() || bID.getText().isEmpty() || bCopies.getText().isEmpty() || bAvailableCopies.getText().isEmpty() || bPrice.getText().isEmpty()){
                messageLabel.setText("Please Provide Values To Fields, Thank You");
                return isAdded;
        }
        else{
                book_title=bTitle.getText();
                book_author= bAuthor.getText();
                book_edition= bEdition.getText();
                book_publisher=bPublisher.getText();
        
        try{
                book_id = Integer.parseInt(bID.getText());
                book_copies=Integer.parseInt(bCopies.getText());
                book_available=Integer.parseInt(bAvailableCopies.getText());
                book_price=Double.parseDouble(bPrice.getText());
        }catch(Exception e){
            
                messageLabel.setText("Incompatible Values! Please Provide correct values");
                e.printStackTrace();
                checkException=false;
        }
           //Prepaing Database According to checkException, If Exception occure, Connection to database will not be established
        if (checkException) {
                    try {

                       Connection conc = DriverManager.getConnection("jdbc:mysql://localhost:3306", "root","");            //Creating Connection to database
                       String url = "jdbc:mysql://localhost:3306/"+databaseName;                                                //Creating another url in order to select particular database
                       
                       //Checking existence of database in local storage using old connection and url
                        if (db.isDatabaseExists(conc, databaseName) ){
                                        //Creating new Connection to database and select given database so that work on it can begin without any Exception
                                        Connection newConnection = DriverManager.getConnection(url ,"root", "");            

                                        String sql = "insert into "+ tableName+" values (?,?,?,?,?,?,?,?,?)";                     //Creating Querry as a String

                                        PreparedStatement pst = newConnection.prepareStatement(sql);      //Prepare Our Querry and setting values to indicies
                                        pst.setInt(1, book_id);
                                        pst.setString(2, book_title);
                                        pst.setString(3, book_author);
                                        pst.setString(4, book_edition);
                                        pst.setInt(5, book_copies);
                                        pst.setInt(6, book_available);
                                        pst.setString(7, book_publisher);
                                        pst.setDouble(8, book_price);
                                        pst.setString (9, "Available");
                                        int rowcount = pst.executeUpdate();                                                                   //Updating database and storing values accordingly.
                                        if (rowcount > 0) {
                                                    isAdded = true;                                                                                            //Successfull addition of row
                                        } 
                                        else {
                                                     isAdded = false;
                                        }
                            }
                        else {

                                        //Creating Table in Case when table is not created in database
                                        //Attributes of the Table
                                       String attributes ="CREATE TABLE  books (\n"
                                                + "id integer PRIMARY KEY,\n"
                                                + "title  varchar(80),\n"
                                                + "author varchar(50),\n"
                                                + "edition varchar(50),\n"
                                                + "copies integer,\n"
                                                + "avg_copies integer,\n"
                                               + "publisher varchar(50),"
                                                + "cost integer,\n"
                                                + "status varchar(10));\n";

                                     db.createTable(conc, databaseName, tableName,attributes );        //Calling createTable method of DatabaseHandler Class
                                     Connection newConnection = DriverManager.getConnection(url ,"root", "");            

                                        String sql = "insert into "+ tableName+" values (?,?,?,?,?,?,?,?)";                     //Creating Querry as a String

                                        PreparedStatement pst = newConnection.prepareStatement(sql);      //Prepare Our Querry and setting values to indicies
                                        pst.setInt(1, book_id);
                                        pst.setString(2, book_title);
                                        pst.setString(3, book_author);
                                        pst.setString(4, book_edition);
                                        pst.setInt(5, book_copies);
                                        pst.setInt(6, book_available);
                                        pst.setString(7, book_publisher);
                                        pst.setDouble(8, book_price);
                                        int rowcount = pst.executeUpdate();                                                                   //Updating database and storing values accordingly.
                                        if (rowcount > 0) {
                                                    isAdded = true;                                                                                            //Successfull addition of row
                                        } 
                                        else {
                                                     isAdded = false;
                                        }
                        
                        
                        
                        }


                    } catch (Exception e) {
                           e.printStackTrace();
                    }
        }
           
        
     return isAdded;
     }
    }
    
    //Method to clear Table
    public void clearTable(){
        DefaultTableModel model = (DefaultTableModel) bookTable.getModel();
        model.setRowCount(0);
    }
    
    //Update Book Method
    public boolean updateBook (){
        boolean isUpdated=false;
        book_id = Integer.parseInt(bID.getText());
        book_title=bTitle.getText();
        book_author= bAuthor.getText();
        book_edition= bEdition.getText();
        book_copies=Integer.parseInt(bCopies.getText());
        book_available=Integer.parseInt(bAvailableCopies.getText());
        book_publisher=bPublisher.getText();
        book_price=Double.parseDouble(bPrice.getText());
         if(bID.equals("")){
                    
                    return false;
          
          }
         else{
                try{
                    Connection conc=DriverManager.getConnection("jdbc:mysql://localhost:3306/library_ms","root","");
                    String sql = "update books set title = ?, author = ? , edition = ?, copies = ?,avg_copies = ? , publisher = ? , cost = ?  where id =  "+bID.getText().trim();
                    PreparedStatement pst = conc.prepareStatement(sql);
                    pst.setString(1, book_title);
                    pst.setString(2, book_author);
                    pst.setString(3, book_edition);
                    pst.setInt(4, book_copies);
                    pst.setInt(5, book_available);
                    pst.setString(6, book_publisher);
                    pst.setDouble(7, book_price);

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
         }
       
        return isUpdated;
    }
    
    
    //Method to  delete Book
    
    public boolean deleteBook(){
        boolean isDeleted = false;
          book_id = Integer.parseInt(bID.getText());
          
          try{
             Connection conc = DriverManager.getConnection("jdbc:mysql://localhost:3306", "root","");            //Creating Connection to database
            String url = "jdbc:mysql://localhost:3306/"+databaseName;
             if (db.isDatabaseExists(conc, databaseName ) ){
                                conc = DriverManager.getConnection(url,"root","");
                                String sql = "delete from "+tableName+" where id = ? ";
                                PreparedStatement pst = conc.prepareStatement(sql);
                                pst.setInt(1,book_id);
            
                                int countRow = pst.executeUpdate();
                                if(countRow>0){
                                        isDeleted=true;
                                }else{
                                        isDeleted=false;
                                }
             } 
          }catch (Exception e ){
                    e.printStackTrace();
          }
        
        return isDeleted;
    }
    
    
    public void clearFields(){
             messageLabel.setText("");
            bID.setText("");
            bTitle.setText("");
            bAuthor.setText("");
            bEdition.setText("");
            bCopies.setText("");
            bAvailableCopies.setText("");
            bPrice.setText("");
            bPublisher.setText("");
        
    
    
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
        bPrice = new app.bolivia.swing.JCTextField();
        jLabel9 = new javax.swing.JLabel();
        bID = new app.bolivia.swing.JCTextField();
        bTitle = new app.bolivia.swing.JCTextField();
        bAuthor = new app.bolivia.swing.JCTextField();
        bEdition = new app.bolivia.swing.JCTextField();
        bCopies = new app.bolivia.swing.JCTextField();
        bAvailableCopies = new app.bolivia.swing.JCTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        bPublisher = new app.bolivia.swing.JCTextField();
        jLabel16 = new javax.swing.JLabel();
        delete_Book = new necesario.MaterialButton();
        add_Book = new necesario.MaterialButton();
        update_Book = new necesario.MaterialButton();
        messageLabel = new javax.swing.JLabel();
        clear = new necesario.MaterialButton();
        teach_detail = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        manage_Books = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        bookTable = new rojeru_san.complementos.RSTableMetro();
        jPanel4 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Manage Books");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(102, 102, 255));
        jPanel1.setFont(new java.awt.Font("Cantarell", 0, 14)); // NOI18N
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

        bPrice.setBackground(new java.awt.Color(102, 102, 255));
        bPrice.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        bPrice.setForeground(new java.awt.Color(255, 255, 255));
        bPrice.setFont(new java.awt.Font("Cantarell", 0, 14)); // NOI18N
        bPrice.setPlaceholder("Enter Price...");
        bPrice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bPriceActionPerformed(evt);
            }
        });
        jPanel1.add(bPrice, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 530, 240, 25));

        jLabel9.setFont(new java.awt.Font("Cantarell", 0, 16)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Price:");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 530, 100, -1));

        bID.setBackground(new java.awt.Color(102, 102, 255));
        bID.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        bID.setForeground(new java.awt.Color(255, 255, 255));
        bID.setFont(new java.awt.Font("Cantarell", 0, 14)); // NOI18N
        bID.setPlaceholder("Enter Book ID...");
        bID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bIDActionPerformed(evt);
            }
        });
        jPanel1.add(bID, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 250, 240, 25));

        bTitle.setBackground(new java.awt.Color(102, 102, 255));
        bTitle.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        bTitle.setForeground(new java.awt.Color(255, 255, 255));
        bTitle.setFont(new java.awt.Font("Cantarell", 0, 14)); // NOI18N
        bTitle.setPlaceholder("Enter Book Title...");
        bTitle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bTitleActionPerformed(evt);
            }
        });
        jPanel1.add(bTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 290, 240, 25));

        bAuthor.setBackground(new java.awt.Color(102, 102, 255));
        bAuthor.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        bAuthor.setForeground(new java.awt.Color(255, 255, 255));
        bAuthor.setFont(new java.awt.Font("Cantarell", 0, 14)); // NOI18N
        bAuthor.setPlaceholder("Enter Book Author...");
        bAuthor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bAuthorActionPerformed(evt);
            }
        });
        jPanel1.add(bAuthor, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 330, 240, 25));

        bEdition.setBackground(new java.awt.Color(102, 102, 255));
        bEdition.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        bEdition.setForeground(new java.awt.Color(255, 255, 255));
        bEdition.setFont(new java.awt.Font("Cantarell", 0, 14)); // NOI18N
        bEdition.setPlaceholder("Enter Book Edition...");
        bEdition.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bEditionActionPerformed(evt);
            }
        });
        jPanel1.add(bEdition, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 370, 240, 25));

        bCopies.setBackground(new java.awt.Color(102, 102, 255));
        bCopies.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        bCopies.setForeground(new java.awt.Color(255, 255, 255));
        bCopies.setFont(new java.awt.Font("Cantarell", 0, 14)); // NOI18N
        bCopies.setPlaceholder("Enter Copies...");
        bCopies.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bCopiesActionPerformed(evt);
            }
        });
        jPanel1.add(bCopies, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 410, 240, 25));

        bAvailableCopies.setBackground(new java.awt.Color(102, 102, 255));
        bAvailableCopies.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        bAvailableCopies.setForeground(new java.awt.Color(255, 255, 255));
        bAvailableCopies.setFont(new java.awt.Font("Cantarell", 0, 14)); // NOI18N
        bAvailableCopies.setPlaceholder("Enter Available Copies...");
        bAvailableCopies.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bAvailableCopiesActionPerformed(evt);
            }
        });
        jPanel1.add(bAvailableCopies, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 450, 240, 25));

        jLabel10.setFont(new java.awt.Font("Cantarell", 0, 16)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Book ID:");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 250, 100, -1));

        jLabel11.setFont(new java.awt.Font("Cantarell", 0, 16)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Book Title:");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 290, 100, -1));

        jLabel12.setFont(new java.awt.Font("Cantarell", 0, 16)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Book Author:");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 330, 100, -1));

        jLabel13.setFont(new java.awt.Font("Cantarell", 0, 16)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Book Edition:");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 370, 100, -1));

        jLabel14.setFont(new java.awt.Font("Cantarell", 0, 16)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Copies:");
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 410, 100, -1));

        jLabel15.setFont(new java.awt.Font("Cantarell", 0, 16)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Available Copies:");
        jPanel1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 450, 120, -1));

        bPublisher.setBackground(new java.awt.Color(102, 102, 255));
        bPublisher.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        bPublisher.setForeground(new java.awt.Color(255, 255, 255));
        bPublisher.setFont(new java.awt.Font("Cantarell", 0, 14)); // NOI18N
        bPublisher.setPlaceholder("Enter Publisher Name...");
        bPublisher.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bPublisherActionPerformed(evt);
            }
        });
        jPanel1.add(bPublisher, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 490, 240, 25));

        jLabel16.setFont(new java.awt.Font("Cantarell", 0, 16)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Publisher:");
        jPanel1.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 490, 100, -1));

        delete_Book.setBackground(new java.awt.Color(255, 51, 0));
        delete_Book.setForeground(new java.awt.Color(0, 0, 0));
        delete_Book.setText("Delete");
        delete_Book.setFont(new java.awt.Font("Cantarell", 1, 16)); // NOI18N
        delete_Book.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                delete_BookActionPerformed(evt);
            }
        });
        jPanel1.add(delete_Book, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 600, 110, -1));

        add_Book.setBackground(new java.awt.Color(255, 255, 255));
        add_Book.setForeground(new java.awt.Color(0, 0, 0));
        add_Book.setText("Add");
        add_Book.setFont(new java.awt.Font("Cantarell", 1, 16)); // NOI18N
        add_Book.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                add_BookActionPerformed(evt);
            }
        });
        jPanel1.add(add_Book, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 600, 110, -1));

        update_Book.setBackground(new java.awt.Color(255, 255, 255));
        update_Book.setForeground(new java.awt.Color(0, 0, 0));
        update_Book.setText("Update");
        update_Book.setFont(new java.awt.Font("Cantarell", 1, 16)); // NOI18N
        update_Book.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                update_BookActionPerformed(evt);
            }
        });
        jPanel1.add(update_Book, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 600, 120, -1));

        messageLabel.setBackground(new java.awt.Color(102, 102, 255));
        messageLabel.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        messageLabel.setForeground(new java.awt.Color(255, 51, 0));
        messageLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        messageLabel.setAutoscrolls(true);
        jPanel1.add(messageLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 370, 50));

        clear.setBackground(new java.awt.Color(255, 255, 255));
        clear.setForeground(new java.awt.Color(0, 0, 0));
        clear.setText("Clear");
        clear.setFont(new java.awt.Font("Cantarell", 1, 16)); // NOI18N
        clear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearActionPerformed(evt);
            }
        });
        jPanel1.add(clear, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 660, 110, -1));

        teach_detail.setBackground(new java.awt.Color(102, 102, 255));
        teach_detail.setFont(new java.awt.Font("Cantarell", 1, 28)); // NOI18N
        teach_detail.setForeground(new java.awt.Color(51, 51, 51));
        teach_detail.setText(" Book Detail:");
        teach_detail.setAutoscrolls(true);
        jPanel1.add(teach_detail, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, 360, 50));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 410, 760));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        manage_Books.setBackground(new java.awt.Color(255, 255, 255));
        manage_Books.setFont(new java.awt.Font("Cantarell", 1, 36)); // NOI18N
        manage_Books.setForeground(new java.awt.Color(255, 0, 0));
        manage_Books.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AddNewBookIcons/AddNewBookIcons/icons8_Books_52px_1.png"))); // NOI18N
        manage_Books.setText("Manage Books:");
        jPanel3.add(manage_Books, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, 330, -1));

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setBorder(null);
        jScrollPane1.setForeground(new java.awt.Color(0, 0, 0));
        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        jScrollPane1.setFont(new java.awt.Font("Noto Sans", 0, 12)); // NOI18N

        bookTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Book ID", "Book Title", "Author", "Book Edition", "Copies", "Available Copies", "Publisher", "Price"
            }
        ));
        bookTable.setColorBackgoundHead(new java.awt.Color(255, 255, 255));
        bookTable.setColorBordeFilas(new java.awt.Color(102, 102, 255));
        bookTable.setColorForegroundHead(new java.awt.Color(255, 51, 0));
        bookTable.setColorSelBackgound(new java.awt.Color(255, 51, 51));
        bookTable.setFont(new java.awt.Font("Noto Sans", 0, 13)); // NOI18N
        bookTable.setFuenteFilas(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        bookTable.setFuenteFilasSelect(new java.awt.Font("Cantarell", 0, 14)); // NOI18N
        bookTable.setFuenteHead(new java.awt.Font("Cantarell", 1, 16)); // NOI18N
        bookTable.setRowHeight(30);
        bookTable.setRowMargin(1);
        bookTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bookTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(bookTable);

        jPanel3.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, 920, 480));

        jPanel4.setBackground(new java.awt.Color(255, 0, 0));
        jPanel3.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 140, 300, 5));

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 0, 980, 760));

        setSize(new java.awt.Dimension(1415, 768));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void delete_BookActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_delete_BookActionPerformed
        // TODO add your handling code here:
        if(deleteBook()){
            JOptionPane.showMessageDialog(this,"Book deleted Successfully");
            clearTable();
            setBookDetailTable()  ;
            clearFields();
        }
        
        
        
        
        
    }//GEN-LAST:event_delete_BookActionPerformed

    private void update_BookActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_update_BookActionPerformed
        // TODO add your handling code here:
        
           if(updateBook()){
            JOptionPane.showMessageDialog(this, "Book Updated Successfully");
            clearTable();
            setBookDetailTable();
            clearFields();
        }
        else{
           JOptionPane.showMessageDialog(this,"Error!, Can't Update Book");
           clearFields();
        }
    }//GEN-LAST:event_update_BookActionPerformed

    private void add_BookActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_add_BookActionPerformed
        if(addBook()){
                JOptionPane.showMessageDialog(this, "Book Added Successfully");
            clearTable();
            setBookDetailTable();
            clearFields();
        }
       
        
    }//GEN-LAST:event_add_BookActionPerformed

    private void bPriceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bPriceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bPriceActionPerformed

    private void bPublisherActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bPublisherActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bPublisherActionPerformed

    private void bAvailableCopiesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bAvailableCopiesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bAvailableCopiesActionPerformed

    private void bCopiesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bCopiesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bCopiesActionPerformed

    private void bEditionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bEditionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bEditionActionPerformed

    private void bAuthorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bAuthorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bAuthorActionPerformed

    private void bIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bIDActionPerformed

    private void bTitleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bTitleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bTitleActionPerformed

    private void backMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backMouseClicked
        Home home= new Home();
        home.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_backMouseClicked

    private void bookTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bookTableMouseClicked
        int rowNumber = bookTable.getSelectedRow();
        TableModel model = bookTable.getModel();
        bID.setText(model.getValueAt(rowNumber, 0).toString());
        bTitle.setText(model.getValueAt(rowNumber, 1).toString());
        bAuthor.setText(model.getValueAt(rowNumber, 2).toString());
        bEdition.setText(model.getValueAt(rowNumber, 3).toString());
        bCopies.setText(model.getValueAt(rowNumber, 4).toString());
        bAvailableCopies.setText(model.getValueAt(rowNumber, 5).toString());
        bPublisher.setText(model.getValueAt(rowNumber, 6).toString());
        bPrice.setText(model.getValueAt(rowNumber, 7).toString());
      
    }//GEN-LAST:event_bookTableMouseClicked

    private void clearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearActionPerformed
        // TODO add your handling code here:
        clearFields();
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
            java.util.logging.Logger.getLogger(ManageBookForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ManageBookForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ManageBookForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ManageBookForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ManageBookForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private necesario.MaterialButton add_Book;
    private app.bolivia.swing.JCTextField bAuthor;
    private app.bolivia.swing.JCTextField bAvailableCopies;
    private app.bolivia.swing.JCTextField bCopies;
    private app.bolivia.swing.JCTextField bEdition;
    private app.bolivia.swing.JCTextField bID;
    private app.bolivia.swing.JCTextField bPrice;
    private app.bolivia.swing.JCTextField bPublisher;
    private app.bolivia.swing.JCTextField bTitle;
    private javax.swing.JLabel back;
    private rojeru_san.complementos.RSTableMetro bookTable;
    private necesario.MaterialButton clear;
    private necesario.MaterialButton delete_Book;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel manage_Books;
    private javax.swing.JLabel messageLabel;
    private javax.swing.JLabel teach_detail;
    private necesario.MaterialButton update_Book;
    // End of variables declaration//GEN-END:variables
}
