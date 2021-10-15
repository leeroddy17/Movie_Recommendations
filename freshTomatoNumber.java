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


		//construct the graph for fresh tomato numbers
		ArrayList<String> users = new ArrayList<>();
		ArrayList<String> titles = new ArrayList<>();

		//query for the users and store in users array list
		ResultSet resultForUsers = sqlQuery.GetUniqueUsers4andAbove(); 
		try {
			while (resultForUsers.next()) {
				users.add(resultForUsers.getString(1));
			}
		} catch (Exception err) {
			System.out.println(err);
		}

		//get the titles for each user that are rated 4 and above and then add to graph
		for (String user : users) {
			ResultSet titlesForUser =  sqlQuery.UserRatings4andAbove(user, conn);
			try {
				while (titlesForUser.next()) {
					titles.add(titlesForUser.getString(1));
					graph.addEdge(user, titlesForUser.getString(1), true);
				}
			} catch (Exception err) {
				System.out.println(err);
			}
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

// class connect {
//     public Connection dbConnection;

//     public connect() {
//         String url = "jdbc:postgresql://csce-315-db.engr.tamu.edu/csce315_914_2_db";
//         String username = "csce315_914_2_user";
//         String password = "Ravi914";
//         dbConnection = null;
//         try {
//             Class.forName("org.postgresql.Driver");
//             dbConnection = DriverManager.getConnection(url, username, password);
//             //JOptionPane.showMessageDialog(null,"Opened database successfully");

//         } catch (Exception e) {
//             e.printStackTrace();
//             System.err.println(e.getClass().getName()+": "+e.getMessage());
//             //JOptionPane.showMessageDialog(null,e.getClass().getName()+": "+e.getMessage());
//             System.exit(0);
//         }//end try catch
//         // JOptionPane.showMessageDialog(null,"Opened database successfully");
//         // System.out.println("Opened database successfully");
//     }

//     public void Disconnect() {
//         try {
//             dbConnection.close();
//             System.out.println("Connection Closed.");
//         } 
//             catch(Exception e) {
//             System.out.println("Connection NOT Closed.");
//         }
//     }//end Class
// }