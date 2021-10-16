import java.util.ArrayList;
import java.util.Map;

public class indirectDirector {
    private Graph<String> graph;
    private  query sqlQuery;
    public indirectDirector() {
        sqlQuery = new query();
        graph = new Graph<String>();
        
    }
    // public static void main(String[] args) {
    //     query sqlqQuery = new query();
    //     graph = new Graph<String>();
    //     Map<String, ArrayList<String> > map = sqlqQuery.dataForIndirectDirector("Fred Astaire");
    //     // System.out.println(map.toString());
    //     for (String director : map.keySet()) {
    //         for (String coActor : map.get(director)) {
    //             graph.addWeightedEdge(director, coActor, true);
    //         }
    //     }
        
    //     graph.printWeightedMap();
    //     System.out.println("Director: " + indirectDirector());
    // }

    public String indirectDirector(String actor) {
        // ArrayList<Integer> sumArray = new ArrayList<>();
        Map<String, ArrayList<String> > map = sqlQuery.dataForIndirectDirector(actor);
        for (String director : map.keySet()) {
            for (String coActor : map.get(director)) {
                graph.addWeightedEdge(director, coActor, true);
            }
        }
        int max=-1;
        int sum = 0;
        String director = "";
        for (String v : graph.weighted_map.keySet()) {
            for (String w : graph.weighted_map.get(v).keySet())
            {
                sum +=  graph.weighted_map.get(v).get(w);
            }
            if(sum > max) {
                max = sum;
                director = v;
            }
            sum = 0;
        }
        return director;
    } 
    
}
