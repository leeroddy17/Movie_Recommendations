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

            String sqlStatement = "SELECT originaltitle,COUNT(originaltitle)  FROM titles INNER JOIN customer_ratings ON "+
            "customer_ratings.titleid=titles.titleid WHERE customer_ratings.rating>3 GROUP BY originaltitle ORDER BY COUNT(originaltitle) DESC LIMIT 20;";
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
            // String sqlStatement = "SELECT titleid FROM customer_ratings WHERE titleid NOT IN" +
            //  "(SELECT titleid FROM customer_ratings WHERE customerid = " + customerid + ") AND customerid IN" +
            //   "(SELECT customerid FROM customer_ratings WHERE customerid!=" + customerid + " AND rating>4 AND" +
            //    "titleid IN (SELECT titleid FROM customer_ratings WHERE customerid=" + customerid + " AND rating>4 LIMIT 10)" +
            //     "LIMIT 10) AND rating >= 4 LIMIT 20;";
            String sqlStatement = "SELECT DISTINCT originaltitle, averagerating FROM titles INNER JOIN customer_ratings ON "+
            "customer_ratings.titleid=titles.titleid WHERE customer_ratings.titleid IN (SELECT DISTINCT titleid FROM customer_ratings "+
            "WHERE titleid NOT IN (SELECT titleid FROM customer_ratings WHERE customerid = "+customerid+") AND customerid IN (SELECT customerid "+
            "FROM customer_ratings WHERE customerid!="+customerid+" AND rating>=4 AND titleid IN (SELECT titleid FROM customer_ratings WHERE customerid="+customerid+" "+
            "AND rating>=4 ORDER BY rating DESC LIMIT 10) LIMIT 10) AND rating >= 4 LIMIT 10) ORDER BY averagerating DESC;";

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
                        if(!temp3.next()) {
                            check = false;
                            break;
                        }
                        if(temp3.getString(2).equals("director")) {
                            directors.add(temp3.getString(1));
                        }
                        else {
                            actors.add(temp3.getString(1));
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

            String sqlStatement = "SELECT nconst FROM principals WHERE titleId IN(" +
                                    "SELECT titleId FROM principals WHERE nconst='" + actor + 
                                    "') AND nconst !='" + actor + "' AND category='actor';";
            
            // System.out.println(sqlStatement);
            result = stmt.executeQuery(sqlStatement);
        }
        catch (Exception e){
            System.out.println("Error accessing Database. CoStars");
            return null;
        }

        return result;
    }
    
    public Double GetTheAverageOfAllCommonMovies(String actor, String costar, Connect conn) {
        ResultSet result;
        Double average;
        try {
            Statement stmt = conn.dbConnection.createStatement();

            String sqlStatement = "SELECT AVG(averageRating) FROM (SELECT averageRating FROM titles WHERE" + 
                                    "titleId IN (SELECT titleId FROM principals WHERE nconst='" + costar + "' "+
                                    "AND titleId IN(SELECT titleId FROM principals WHERE nconst='" + actor + "'))) " +
                                    "AS averageRating;";
            
            // System.out.println(sqlStatement);
            result = stmt.executeQuery(sqlStatement);
            average = Double.parseDouble(result.getString(1));
        }
        catch (Exception e){
            System.out.println("Error accessing Database.");
            return null;
        }

        return average;
    }
    public ResultSet UserBeware(int customerid) {
        conn = new Connect();
        ResultSet result;
        try {
            Statement stmt = conn.dbConnection.createStatement();
            // Returns top 20 recommendations based on finding high-rated, unwatched movies from similiar users
            // String sqlStatement = "SELECT DISTINCT originaltitle, averagerating FROM titles INNER JOIN customer_ratings" + 
            // " ON customer_ratings.titleid=titles.titleid WHERE customer_ratings.titleid IN " + 
            // "(SELECT DISTINCT titleid FROM customer_ratings WHERE titleid NOT IN " +
            // "(SELECT titleid FROM customer_ratings WHERE customerid = " + customerid + ") AND customerid IN "+
            // "(SELECT customerid FROM customer_ratings WHERE customerid!=" + customerid + " AND rating<=2 AND titleid IN "+
            // "(SELECT titleid FROM customer_ratings WHERE customerid=" + customerid + " AND rating<=2 ORDER BY rating ASC LIMIT 10)"+
            // " LIMIT 10) AND rating <= 2 LIMIT 20) ORDER BY averagerating ASC;";
            String sqlStatement = "SELECT DISTINCT originaltitle, averagerating FROM titles INNER JOIN customer_ratings ON "+
            "customer_ratings.titleid=titles.titleid WHERE customer_ratings.titleid IN (SELECT DISTINCT titleid "+
            "FROM customer_ratings WHERE titleid NOT IN (SELECT titleid FROM customer_ratings WHERE customerid = "+customerid+") "+
            "AND customerid IN (SELECT customerid FROM customer_ratings WHERE customerid!="+customerid+" AND rating<=2 AND titleid IN "+
            "(SELECT titleid FROM customer_ratings WHERE customerid="+customerid+" AND rating<=2 ORDER BY rating ASC LIMIT 10) LIMIT 10) "+
            "AND rating <= 2 LIMIT 10) ORDER BY averagerating ASC;";

            result = stmt.executeQuery(sqlStatement);
        }
        catch(Exception e){
            System.out.println("Error Accessing Database");
            return null;
        }
        conn.Disconnect();
        return result;
    }

    public String GetActorName(String actorid, Connect conn) {
        ResultSet result;
        String name;
        try {
            Statement stmt = conn.dbConnection.createStatement();

            String sqlStatement = "SELECT primaryName FROM names WHERE nconst='" + actorid + "';";

            // System.out.println(sqlStatement);
            result = stmt.executeQuery(sqlStatement);
            name = result.getString(1);
        }
        catch (Exception e){
            System.out.println("Error accessing Database.");
            return null;
        }

        return name;
    }

    public Graph<String> graphForHollyWoodPairs() {
        Graph<String> graph = new Graph<>();
        conn = new Connect();
        ResultSet result;
        try {
            Statement stmt = conn.dbConnection.createStatement();
            String sqlStatement = "SELECT names.primaryname, principals.titleId, AVG(customer_ratings.rating) FROM principals " + 
            "INNER JOIN names ON principals.nconst = names.nconst " +
            "INNER JOIN customer_ratings ON principals.titleId = customer_ratings.titleId " +
            " WHERE principals.titleId IN " +
            "(SELECT titleid FROM principals WHERE nconst IN (" +
            "SELECT nconst FROM principals WHERE category = 'actor' OR category = 'actress')) " +
            "AND (category = 'actor' OR category = 'actress') "+
            "GROUP BY principals.titleid, names.primaryname;";

            result = stmt.executeQuery(sqlStatement);

            try {
                boolean check = true;
                String title = "";
                ArrayList<String> actors = new ArrayList<>();
                result.next();

                while(check) {
                    title = result.getString(2);
                    Double weight = Double.valueOf(result.getString(3));

                    while(title.equals(result.getString(2))) {
                        if(!result.next()) {
                            check = false;
                            break;
                        }
                        actors.add(result.getString(1));
                    }

                    for (int i=0; i<actors.size()-1; i++) {
                        for (int j = i+1; j < actors.size(); j++) {
                            graph.addWeightedEdge(actors.get(i), actors.get(j), weight, true);
                        }
                    }
                    actors.clear();
                } 
            }  catch (Exception err) {
                System.out.println(err + " 6 ");
            }      
        }
        catch (Exception e){
            System.out.println("Error accessing Database.");
            System.out.println(e);
            return null;
        }
        conn.Disconnect();
        return graph;
    }

    public Boolean DoesTheUserExist(String customerid) {
        ResultSet result;
        Boolean userExists;
        try {
            Statement stmt = conn.dbConnection.createStatement();

            String sqlStatement = "SELECT customerId FROM customer_ratings WHERE customerId=" + customerid + ";";
            // System.out.println(sqlStatement);
            result = stmt.executeQuery(sqlStatement);

            if(result.next()) {
                userExists = true;
            }
            else {
                userExists = false;
            }

        }
        catch (Exception e){
            System.out.println("Error accessing Database.");
            return null;
        }
        return userExists;

    }
}

