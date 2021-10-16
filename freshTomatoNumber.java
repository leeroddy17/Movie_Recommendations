import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.Stack;
import java.util.LinkedList;
import java.util.HashMap;
import java.util.List;
import java.sql.*;

public class freshTomatoNumber {
    private  query sqlQuery;
	private  Graph<String> graph;

    public freshTomatoNumber() {
        sqlQuery = new query();
		graph = new Graph<String>();
        Connect conn = new Connect(); 

		//get the titles for each user that are rated 4 and above and then add to graph
		ResultSet titlesForUser =  sqlQuery.UserRatings4andAbove();
		try {
			while (titlesForUser.next()) {
				graph.addEdge(titlesForUser.getString(1), titlesForUser.getString(2), true);
			}
		} catch (Exception err) {
			System.out.println(err);
		}
		

        conn.Disconnect();

    }

    public Stack<String> freshTomatoNumber(String src, String dest) {

		Stack<String> path = new Stack<String>();
		LinkedList<String> queue = new LinkedList<String>(); 
		Map<String, List<String> > map = graph.map;
		HashSet<String> visited = new HashSet<>();
		Map<String,String> pred = new HashMap<>();
		boolean connected = false;

		pred.put(null, src);
		queue.add(src);
		visited.add(src);
		while (!queue.isEmpty()) {
            String u = queue.remove();
            // System.out.println(map.get(u));
			for (String w : map.get(u)) {
				if (visited.contains(w) == false) {
					visited.add(w);
                    pred.put(w,u);
                    queue.add(w);
 
                    // stopping condition (when we find
                    // our destination)
                    if (w.equals(dest)) {
						connected = true;
                    	break;
					}		
                }
            }
        }

        // System.out.println(pred);
		if(connected){
			String v = dest;
            System.out.println(v);

			while(v != null) {
				path.add(v);
				v = pred.get(v);
                System.out.println(v);

			}
		}
		else {
			System.out.println(visited);
			System.out.println(pred);
			System.out.println("Given source and destination are not connected");
		}
		

		// System.out.println("Graph:\n"
        //                    + thisObj.graph.toString());
		return path;

	}
    
}
