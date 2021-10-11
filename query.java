import java.sql.*;
import javax.swing.JOptionPane;
//import util.dbSetup;
import java.sql.DriverManager;
/*
CSCE 315
9-25-2019
 */
public class query {
    private connect conn;
    public query() { //Once connected to GUI, the GUI will have the main function, this is just temporary
        // conn = new connect();           //Ideally this will be a constructor that establishes the connection

    }

    public ResultSet WatchHistory(Integer customerId, String startDate, String endDate) {
        conn = new connect(); 
        ResultSet result;
        try {
            Statement stmt = conn.dbConnection.createStatement();

            String sqlStatement = "SELECT originaltitle FROM titles INNER JOIN customer_ratings ON " +
                                    "customer_ratings.titleid=titles.titleid WHERE customer_ratings.customerid=" +
                                    customerId.toString() + 
                                    " AND '"+startDate+"'<customer_ratings.date AND '"+endDate+"'>customer_ratings.date LIMIT 50";
            System.out.println(sqlStatement);
            result = stmt.executeQuery(sqlStatement);
        }
        catch (Exception e){
            System.out.println("Error accessing Database.");
            return null;
        }

        conn.Disconnect();
        return result;
    }

    public ResultSet MoviesByGenre(String genre) {
        conn = new connect(); 
        ResultSet result;
        try {
            Statement stmt = conn.dbConnection.createStatement();

            String sqlStatement = "SELECT originaltitle FROM titles WHERE genres LIKE '%%" +genre+"%%';";
            System.out.println(sqlStatement);
            result = stmt.executeQuery(sqlStatement);
        }
        catch (Exception e){
            System.out.println("Error accessing Database.");
            return null;
        }

        conn.Disconnect();
        return result;

    }

    public ResultSet TopRatedMovies(String startDate, String endDate) {
        conn = new connect(); 
        ResultSet result;
        try {
            Statement stmt = conn.dbConnection.createStatement();

            String sqlStatement = "SELECT originaltitle FROM titles INNER JOIN customer_ratings" +
                                    " ON customer_ratings.titleid = titles.titleid WHERE '"+
                                    endDate + "'>customer_ratings.date AND '" + startDate + 
                                    "'<customer_ratings.date GROUP BY titles.titleid ORDER BY numvotes DESC LIMIT 10;";
            System.out.println(sqlStatement);
            result = stmt.executeQuery(sqlStatement);
        }
        catch (Exception e){
            System.out.println("Error accessing Database.");
            return null;
        }

        conn.Disconnect();
        return result;
    }

    public void CloseConnection() {
        conn.Disconnect();
    }
}

class connect {
    public Connection dbConnection;

    public connect() {
        String url = "jdbc:postgresql://csce-315-db.engr.tamu.edu/csce315_914_2_db";
        String username = "csce315_914_2_user";
        String password = "Ravi914";
        dbConnection = null;
        try {
            Class.forName("org.postgresql.Driver");
            dbConnection = DriverManager.getConnection(url, username, password);
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

