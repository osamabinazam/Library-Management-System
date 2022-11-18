/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DatabaseHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author shappar
 */
public class DatabaseHandler {

    // Method That help us to Check weather Database exists or not
    public boolean isDatabaseExists(Connection conc, String db_Name) {
        ResultSet resultSet = null;

        try {
            resultSet = conc.getMetaData().getCatalogs();
            while (resultSet.next()) {
                String catelog = resultSet.getString(1);
                if (db_Name.equals(catelog))
                    return true;
            }
            return false;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    // Method that help us to check given table exist or not
    public boolean isTableExists(Connection conc, String tablename) {
        try {
            ResultSet resultSet = conc.getMetaData().getTables(null, null, tablename, null);
            while (resultSet.next()) {
                String get_name = resultSet.getString(3);
                if (tablename.equals(get_name)){
                               
                    return true;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            return false;

        }
        return false;

    }
    
 
    public boolean createTable(Connection conc, String db_Name, String table_name, String table_attributes) {
        try {

            String url = "jdbc:mysql://localhost:3306/" + db_Name;
            if (isDatabaseExists(conc, db_Name) && !isTableExists(conc, table_name)) {
                Connection newconc = DriverManager.getConnection(url, "root", "");

                Statement st = newconc.createStatement();
                st.execute(table_attributes);
                return true;

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

//        System.out.println("Successfully create Table");
        return false;

    }
    
    
    
    

    public boolean insertValueToTable(Connection conc, String dbName, String tableName, String attributes,
            ArrayList<String> values) {
        return true;

    }

}
