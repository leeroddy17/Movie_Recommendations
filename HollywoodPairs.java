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
       //conn = new Connect();
        
      graph = sqlQuery.graphForHollyWoodPairs();

   }

   public ArrayList<String> GetTop10Pairs() {
        ArrayList<String> topPairs = new ArrayList<>(); //Returned as actor,costar for each instance
        for(int i = 0; i < 10; i++) {
            Double max = 0.0;
            String maxActor = null;
            String maxCostar = null;
            for(String actor : graph.decimal_weighted_map.keySet()) {
                for(String costar : graph.decimal_weighted_map.get(actor).keySet()) {
                    if(graph.decimal_weighted_map.get(actor).get(costar) > max) {
                        max = graph.decimal_weighted_map.get(actor).get(costar);
                        maxActor = actor;
                        maxCostar = costar;
                    } 
                
                }
            }
            String pair = maxActor + "," + maxCostar;
            //graph.decimal_weighted_map.get(maxActor).remove(maxCostar);
            graph.decimal_weighted_map.get(maxCostar).remove(maxActor);
            graph.decimal_weighted_map.get(maxActor).remove(maxCostar);
            topPairs.add(pair);
        }

        return topPairs;
   }


}
