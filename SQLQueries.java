package SQLQueries;


public final class SQLQueries {

    public String getMoviesByDatesAndID(int customerid, String startDate, String endDate) {
        // SELECT * FROM customer_ratings WHERE customerid=1488844 AND date BETWEEN '2005-04-01' AND '2005-04-29';
        return String.format("SELECT titleid FROM customer_ratings WHERE customerid=%d AND date BETWEEN %s AND %s;"
        , customerid, startDate, endDate);
    }

    public String getTrendingByDates(String startDate, String endDate) {
        // Given a start and end date, counts the number of watches 
        // SELECT titleid, count(*) as customerid FROM customer_ratings WHERE date BETWEEN '2005-04-01' AND '2005-04-29' GROUP BY titleid;

        return String.format("SELECT titleid, count(*) as customerid FROM customer_ratings WHERE date BETWEEN %s AND %s GROUP BY titleid;"
        , startDate, endDate);
    }


}