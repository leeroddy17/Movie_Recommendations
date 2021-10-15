import java.util.*;
  
class Graph<T> {
  
    // We use Hashmap to store the edges in the graph
    public Map<T, List<T> > map = new HashMap<>();
    public Map<T, Map<T,Integer>> weighted_map = new HashMap<>();
    public Map<T, Map<T,Double>> decimal_weighted_map = new HashMap<>();
  
    // This function adds a new vertex to the graph
    public void addVertex(T s)
    {
        map.put(s, new LinkedList<T>());
    }
  
    // This function adds the edge
    // between source to destination
    public void addEdge(T source,
                        T destination,
                        boolean bidirectional)
    {
  
        if (!map.containsKey(source)) 
            addVertex(source);
  
        if (!map.containsKey(destination))
            addVertex(destination);
  
        map.get(source).add(destination);
        if (bidirectional == true) {
            map.get(destination).add(source);
        }
    }

    public void addWeightedEdge(T source,
        T destination,
        boolean bidirectional)
    {

        if (!weighted_map.containsKey(source))
        {
            Map<T, Integer> edge = new HashMap<>();
            edge.put(destination,1);

            weighted_map.put(source, edge);
        } else {

            if(weighted_map.get(source).containsKey(destination)){
                weighted_map.get(source).put(destination, weighted_map.get(source).get(destination)+1 );
            } else {
                weighted_map.get(source).put(destination, 1);
            }
        }

    }

    public void addWeightedEdge(T source,
        T destination,
        Integer weight,
        boolean bidirectional)
    {

        if (!weighted_map.containsKey(source))
        {
            Map<T, Integer> edge = new HashMap<>();
            edge.put(destination, weight);

            weighted_map.put(source, edge);
        } else {

            weighted_map.get(source).put(destination, weight);

        }

    }

    public void addWeightedEdge(T source,
        T destination,
        Double weight,
        boolean bidirectional)
    {

        if (!decimal_weighted_map.containsKey(source))
        {
            Map<T, Double> edge = new HashMap<>();
            edge.put(destination, weight);

            decimal_weighted_map.put(source, edge);
        } else {

            decimal_weighted_map.get(source).put(destination, weight);

        }

    }

  
    // This function gives the count of vertices
    public void getVertexCount()
    {
        System.out.println("The graph has "
                           + map.keySet().size()
                           + " vertex");
    }
  
    // This function gives the count of edges
    public void getEdgesCount(boolean bidirection)
    {
        int count = 0;
        for (T v : map.keySet()) {
            count += map.get(v).size();
        }
        if (bidirection == true) {
            count = count / 2;
        }
        System.out.println("The graph has "
                           + count
                           + " edges.");
    }
  
    // This function gives whether
    // a vertex is present or not.
    public void hasVertex(T s)
    {
        if (map.containsKey(s)) {
            System.out.println("The graph contains "
                               + s + " as a vertex.");
        }
        else {
            System.out.println("The graph does not contain "
                               + s + " as a vertex.");
        }
    }
  
    // This function gives whether an edge is present or not.
    public void hasEdge(T s, T d)
    {
        if (map.get(s).contains(d)) {
            System.out.println("The graph has an edge between "
                               + s + " and " + d + ".");
        }
        else {
            System.out.println("The graph has no edge between "
                               + s + " and " + d + ".");
        }
    }

    public  Map<T, List<T> > getMap(){
        return map;
    }
  
    // Prints the adjancency list of each vertex.
    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
  
        for (T v : map.keySet()) {
            builder.append(v.toString() + ": ");
            for (T w : map.get(v)) {
                builder.append(w.toString() + " ");
            }
            builder.append("\n");
        }
  
        return (builder.toString());
    }

    public void printWeightedMap () {
        for (T v : weighted_map.keySet()) {
            System.out.print(v.toString() + ": ");
            for (T w : weighted_map.get(v).keySet())
            {
                System.out.print(w);
                System.out.print( weighted_map.get(v).get(w));
            }
            System.out.println();
        }
    }

}
