import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.Stack;
import java.util.LinkedList;
import java.util.HashMap;
import java.util.List;
import java.sql.*;



public class HollywoodPairs {
   private query sqlQuery;
   private Graph<String> graph;
   private Connect conn;

   public HollywoodPairs() {
       sqlQuery = new query();
       graph = new Graph<String>();
       conn = new Connect();

       ArrayList<String> allActors = new ArrayList<>();
       Map<String, ArrayList<String>> allCostars = new HashMap<>();

       //Use RetainAll
       ResultSet actors = sqlQuery.GetAllActors(conn); 
		try {
			while (actors.next()) {
				allActors.add(actors.getString(1));
			}
		} catch (Exception err) {
			System.out.println(err);
		}

        
        for(String actor : allActors) {
            ResultSet costars = sqlQuery.GetCostars(actor, conn);
            allCostars.put(actor, new ArrayList<>());
            try {
				while (costars.next()) {
                    allCostars.get(actor).add(costars.getString(1));
					graph.addWeightedEdge(actor, costars.getString(1), 0.0, true);
				}
			} catch (Exception err) {
				System.out.println(err);
			}

        }

        for(String actor : graph.decimal_weighted_map.keySet()) { //For each actor get list of movies they're in
            for(String costar : graph.decimal_weighted_map.get(actor).keySet()) { //For each costar get list of movie they're in
                if(graph.decimal_weighted_map.get(actor).get(costar) < 1) {
                    Double averageMovieRating = sqlQuery.GetTheAverageOfAllCommonMovies(actor, costar, conn);
                    graph.addWeightedEdge(actor, costar, averageMovieRating, true);
                }

            }
 
        }

   }

   public ArrayList<String> GetTop10Pairs() {
        ArrayList<String> topPairs = new ArrayList<>(); //Returned as actor,costar for each instance
        for(int i = 0; i < 10; i++) {
            Double max = 0.0;
            String maxActor = null;
            String maxCostar = null;
            for(String actor : graph.weighted_map.keySet()) {
                for(String costar : graph.decimal_weighted_map.get(actor).keySet()) {
                    if(graph.decimal_weighted_map.get(actor).get(costar) > max) {
                        max = graph.decimal_weighted_map.get(actor).get(costar);
                        maxActor = actor;
                        maxCostar = costar;
                    } 
                
                }
            }
            maxActor = sqlQuery.GetActorName(maxActor, conn);
            maxCostar = sqlQuery.GetActorName(maxCostar, conn);
            String pair = maxActor + "," + maxCostar;
            topPairs.add(pair);
            graph.decimal_weighted_map.get(maxActor).remove(maxCostar);
        }

        return topPairs;
   }

}
