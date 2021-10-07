package connectDatabase;

import java.sql.*;
import javax.swing.JOptionPane;
import util.dbSetup;
import java.sql.DriverManager;
/*
CSCE 315
9-25-2019
 */
public class connect {
    public Connection conn;

    public connect() {
        // dbSetup my = new dbSetup();
        conn = null;
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://csce-315-db.engr.tamu.edu/csce315_914_2_db",
            "csce315_914_2_user", "Ravi914");
            JOptionPane.showMessageDialog(null,"Opened database successfully");

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            JOptionPane.showMessageDialog(null,e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }//end try catch
        // JOptionPane.showMessageDialog(null,"Opened database successfully");
        // System.out.println("Opened database successfully");
    }
}//end Class
