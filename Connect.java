import java.sql.*;
import javax.swing.JOptionPane;
import java.sql.DriverManager;

public class Connect {
    public Connection dbConnection;

    public Connect() {
        String url = "jdbc:postgresql://csce-315-db.engr.tamu.edu/csce315_914_2_db";
        String username = "csce315_914_2_user";
        String password = "Ravi914";
        dbConnection = null;
        try {
            Class.forName("org.postgresql.Driver");
            dbConnection = DriverManager.getConnection(url, username, password);
            // JOptionPane.showMessageDialog(null,"Opened database successfully");

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getClass().getName()+": "+e.getMessage());
            //JOptionPane.showMessageDialog(null,e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }//end try catch
        // JOptionPane.showMessageDialog(null,"Opened database successfully");
        // System.out.println("Opened database successfully");
    }

    public void Disconnect() {
        try {
            dbConnection.close();
            System.out.println("Connection Closed.");
        } 
            catch(Exception e) {
            System.out.println("Connection NOT Closed.");
        }
    }//end Class
}
