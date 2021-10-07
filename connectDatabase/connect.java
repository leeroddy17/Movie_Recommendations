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

// String cus_lname = "";
        // try{
        //     //create a statement object
        //     Statement stmt = db.conn.createStatement();
        //     //create an SQL statement
        //     String sqlStatement = "SELECT rating FROM customer_ratings LIMIT 10";
        //     //send statement to DBMS
        //     ResultSet result = stmt.executeQuery(sqlStatement);
    
        //     //OUTPUT
        //     JOptionPane.showMessageDialog(null,"Customer rating from the Database.");
        //     //System.out.println("______________________________________");
        //     while (result.next()) {
        //         //System.out.println(result.getString("cus_lname"));
        //         cus_lname += result.getString("rating")+"\n";
        //     }
        // } catch (Exception e){
        //     JOptionPane.showMessageDialog(null,e.getClass().getName()+": "+e.getMessage());
        // }
        // JOptionPane.showMessageDialog(null,cus_lname);
        //closing the connection

            // contentView.setVisible(true);
        
        // try {
        //     db.conn.close();
        //     JOptionPane.showMessageDialog(null,"Connection Closed.");
        // } catch(Exception e) {
        //     JOptionPane.showMessageDialog(null,"Connection NOT Closed.");
        // }//end try catch
