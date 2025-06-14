/*
Flow of the code:
1. Define a class to represent edges in the graph.
2. Build the adjacency list for the graph.
3. Implement Dijkstra's algorithm using a priority queue (min-heap).
4. Print the shortest distances from the source to all vertices.
5. Dry run example for better understanding.
*/
import java.util.*;

public class dijkstraPQ {

    static class Edge {
        int dest, weight; // destination vertex and edge weight
        Edge(int dest, int weight) {
            this.dest = dest;
            this.weight = weight;
        }
    }

    static void dijkstra(List<List<Edge>> graph, int src) {
        int V = graph.size(); // number of vertices
        int[] dist = new int[V]; // shortest distances from src
        Arrays.fill(dist, Integer.MAX_VALUE); // initialize all distances as infinity
        dist[src] = 0; // distance to source is 0

        // Min-heap: stores {vertex, distance}
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        pq.offer(new int[]{src, 0}); // start with source

        while (!pq.isEmpty()) {
            int[] curr = pq.poll(); // get vertex with smallest distance
            int u = curr[0], d = curr[1];
            if (d > dist[u]) continue; // skip if not optimal

            for (Edge edge : graph.get(u)) { // check all neighbors
                int v = edge.dest, w = edge.weight;
                if (dist[u] + w < dist[v]) { // found shorter path
                    dist[v] = dist[u] + w;
                    pq.offer(new int[]{v, dist[v]}); // push updated distance
                }
            }
        }

        System.out.println("Vertex\tDistance from Source");
        for (int i = 0; i < V; i++)
            System.out.println(i + "\t" + dist[i]);
    }

    public static void main(String[] args) {
        int V = 5; // number of vertices
        List<List<Edge>> graph = new ArrayList<>();
        for (int i = 0; i < V; i++) graph.add(new ArrayList<>()); // initialize adjacency list

        // add edges: from, to, weight
        graph.get(0).add(new Edge(1, 2));
        graph.get(0).add(new Edge(2, 4));
        graph.get(1).add(new Edge(2, 1));
        graph.get(1).add(new Edge(3, 7));
        graph.get(2).add(new Edge(4, 3));
        graph.get(3).add(new Edge(4, 1));

        dijkstra(graph, 0); // run Dijkstra from vertex 0

        /*
        Dry run:
        - Start at 0: dist[0]=0, dist[1]=2, dist[2]=4
        - Visit 1: dist[2]=min(4,2+1)=3, dist[3]=2+7=9
        - Visit 2: dist[4]=3+3=6
        - Visit 4: nothing to update
        - Visit 3: dist[4]=min(6,9+1)=6 (no change)
        Final distances: 0->0, 1->2, 2->3, 3->9, 4->6
        */
    }
}
