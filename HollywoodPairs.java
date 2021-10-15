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

       ArrayList<String> allActors = new ArrayList();
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
            allCostars.put(actor, new ArrayList());
            try {
				while (costars.next()) {
                    allCostars.get(actor).add(costars.getString(1));
					graph.addWeightedEdge(actor, costars.getString(1), 0, true);
				}
			} catch (Exception err) {
				System.out.println(err);
			}

        }

        for(String vertex : graph.weighted_map.keySet()) { //For each actor get list of movies they're in
            for(String value : graph.weighted_map.get(vertex).keySet()) { //For each costar get list of movie they're in
                //RetainAll list1.RetainAll(list2)
                //Where list1 is the list of movies the actor is in
                //and list2 is the list of movies the costar is in
                //Result being the common movies between them
                //From those common movies query the average rating of them and save into an ArrayList
                //Compute the average of that array list.
                //Then set the weight of the edge between the actor and costar equal to that average
            }
 
        }

   }


}
