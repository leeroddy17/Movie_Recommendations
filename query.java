import java.sql.*;
import javax.swing.JOptionPane;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
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
    //Get a specific user ratings that are 4 or above
    public ResultSet UserRatings4andAbove() {
        conn = new Connect(); 
        ResultSet result;
        try {
            Statement stmt = conn.dbConnection.createStatement();

            String sqlStatement = "SELECT customerid, titles.originaltitle FROM customer_ratings INNER JOIN titles ON titles.titleid = customer_ratings.titleid WHERE rating>3 AND customerid IN (SELECT DISTINCT customerid FROM customer_ratings WHERE rating>3);";
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

    public Map<String, ArrayList<String> > dataForIndirectDirector(String actor) {
        conn = new Connect();
        Map<String,ArrayList<String>> actorsDirectors = new HashMap<>();
        try {
           
            Statement stmt = conn.dbConnection.createStatement();
            String sqlStatement = "SELECT nconst FROM names WHERE primaryname = '" + actor +"';";
           
            ResultSet rSet = stmt.executeQuery(sqlStatement);
            String actorId = "";
            try {
				while (rSet.next()) {
                    actorId = rSet.getString(1);
				}
			} catch (Exception err) {
				System.out.println(err + " 0 ");
			}

            sqlStatement = "SELECT names.primaryname, principals.category, titleid FROM principals INNER JOIN names ON names.nconst = principals.nconst WHERE titleid IN " +
            "(SELECT titleid FROM principals WHERE nconst IN (SELECT nconst FROM principals WHERE titleid IN  " +
            "(SELECT titleid FROM principals WHERE nconst IN (SELECT nconst FROM principals WHERE titleid IN (SELECT titleid FROM principals WHERE nconst = '" + actorId + "') AND nconst != '" + actorId + "' AND (category = 'actor' OR category = 'actress')))"
            + " AND (principals.category = 'director') EXCEPT SELECT names.primaryname FROM principals INNER JOIN names ON names.nconst = principals.nconst WHERE titleid IN (SELECT titleid FROM principals WHERE nconst = '" + actorId + "') AND principals.category = 'director' )" 
            + " AND titleid IN (SELECT titleid FROM principals WHERE nconst IN (SELECT nconst FROM principals WHERE titleid IN (SELECT titleid FROM principals WHERE nconst = '" + actorId + "') AND nconst !='" + actorId + "' AND (category = 'actor' OR category = 'actress'))))" 
            + " AND (principals.category = 'actor' OR principals.category = 'actress' OR principals.category = 'director') ORDER BY titleid;";
            ResultSet temp3 = stmt.executeQuery(sqlStatement);
            boolean check = true;
            try {
                temp3.next();
                while (check) {
                    String a = temp3.getString(3);
                    ArrayList<String> directors = new ArrayList<>();
                    ArrayList<String> actors = new ArrayList<>();
                    while(a.equals(temp3.getString(3))){
                        if(temp3.getString(2).equals("director")) {
                            directors.add(temp3.getString(1));
                        }
                        else {
                            actors.add(temp3.getString(1));
                        }
                        if(!temp3.next()) {
                            check = false;
                        }
                    }
                    for (String director : directors) {
                        if(actorsDirectors.containsKey(director)) {
                            actorsDirectors.get(director).addAll(actors);
                        } else {
                            actorsDirectors.put(director,actors);
                        }
                    }

                    
                   
                }
            } catch (Exception err) {
                System.out.println(err + " 6 ");
            }
        }
        catch (Exception e){
            System.out.println("Error accessing Database." + e);
            return null;
        }

        conn.Disconnect();
        return actorsDirectors;
    }
}

