import java.sql.*;
import javax.swing.JOptionPane;
import java.sql.DriverManager;
/*
CSCE 315
9-25-2019
 */
public class query {
    private Connect conn;
    public query() { //Once connected to GUI, the GUI will have the main function, this is just temporary
        // conn = new connect();           //Ideally this will be a constructor that establishes the connection

    }

    public ResultSet WatchHistory(Integer customerId, String startDate, String endDate) {
        conn = new Connect(); 
        ResultSet result;
        try {
            Statement stmt = conn.dbConnection.createStatement();

            String sqlStatement = "SELECT originaltitle FROM titles INNER JOIN customer_ratings ON " +
                                    "customer_ratings.titleid=titles.titleid WHERE customer_ratings.customerid=" +
                                    customerId.toString() + 
                                    " AND '"+startDate+"'<customer_ratings.date AND '"+endDate+"'>customer_ratings.date LIMIT 50";
            // System.out.println(sqlStatement);
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
        conn = new Connect(); 
        ResultSet result;
        try {
            Statement stmt = conn.dbConnection.createStatement();

            String sqlStatement = "SELECT originaltitle FROM titles WHERE genres LIKE '%%" +genre+"%%';";
            // System.out.println(sqlStatement);
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
        conn = new Connect(); 
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

    public ResultSet CultClassics() {
        conn = new Connect(); 
        ResultSet result;
        try {
            Statement stmt = conn.dbConnection.createStatement();

            String sqlStatement = "SELECT originaltitle,COUNT(originaltitle)" +
                                    "FROM titles INNER JOIN customer_ratings ON" +
                                    "customer_ratings.titleid=titles.titleid" +
                                    "WHERE customer_ratings.rating>3 GROUP BY originaltitle" +
                                    "ORDER BY COUNT(originaltitle) DESC LIMIT 30;";
            result = stmt.executeQuery(sqlStatement);     
        }
        catch (Exception e){
            System.out.println("Error accessing Database.");
            return null;
        }

        conn.Disconnect();
        return result;  
    }         
    //Gets all users with ratings greater or equal to 4
    public ResultSet GetUniqueUsers4andAbove() {
        conn = new Connect(); 
        ResultSet result;
        try {
            Statement stmt = conn.dbConnection.createStatement();
            String sqlStatement = "SELECT DISTINCT customerid FROM customer_ratings WHERE rating>3;";
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

    //Get a specific user ratings that are 4 or above
    public ResultSet UserRatings4andAbove(String customerId, Connect conn) {
        ResultSet result;
        try {
            Statement stmt = conn.dbConnection.createStatement();

            String sqlStatement = "SELECT titles.originaltitle FROM customer_ratings INNER JOIN titles ON titles.titleid = customer_ratings.titleid WHERE rating>3 AND customerid = " + customerId + ";";
            // System.out.println(sqlStatement);
            result = stmt.executeQuery(sqlStatement);
        }
        catch (Exception e){
            System.out.println("Error accessing Database.");
            return null;
        }

        return result;
    }


    public ResultSet TopRecommendations(int customerid) {
        conn = new Connect();
        ResultSet result;
        try {
            Statement stmt = conn.dbConnection.createStatement();
            // Returns top 20 recommendations based on finding high-rated, unwatched movies from similiar users
            String sqlStatement = "SELECT titleid FROM customer_ratings WHERE titleid NOT IN" +
             "(SELECT titleid FROM customer_ratings WHERE customerid = " + customerid + ") AND customerid IN" +
              "(SELECT customerid FROM customer_ratings WHERE customerid!=" + customerid + " AND rating>4 AND" +
               "titleid IN (SELECT titleid FROM customer_ratings WHERE customerid=" + customerid + " AND rating>4 LIMIT 10)" +
                "LIMIT 10) AND rating >= 4 LIMIT 20;";

            result = stmt.executeQuery(sqlStatement);
        }
        catch (Exception e){
            System.out.println("Error accessing Database.");
            return null;
        }

        conn.Disconnect();
        return result;
    }

    public ResultSet GetAllActors(Connect conn) {
        ResultSet result;
        try {
            Statement stmt = conn.dbConnection.createStatement();

            String sqlStatement = "SELECT nconst FROM principals WHERE category='actor';";
            // System.out.println(sqlStatement);
            result = stmt.executeQuery(sqlStatement);
        }
        catch (Exception e){
            System.out.println("Error accessing Database.");
            return null;
        }

        return result;
    }

    public ResultSet GetCostars(String actor, Connect conn) {
        ResultSet result;
        try {
            Statement stmt = conn.dbConnection.createStatement();

            String sqlStatement = "SELECT nconst FROM principals WHERE titleId IN(SELECT titleid FROM principals" + 
            "WHERE nconst='" + actor + "');";
            
            // System.out.println(sqlStatement);
            result = stmt.executeQuery(sqlStatement);
        }
        catch (Exception e){
            System.out.println("Error accessing Database.");
            return null;
        }

        return result;
    }
    public void CloseConnection() {
        conn.Disconnect();
    }
}

