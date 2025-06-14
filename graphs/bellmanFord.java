import java.util.*;

/*
Flow of the Bellman-Ford Algorithm:
1. Define an Edge class to represent each edge with source, destination, and weight.
2. Initialize the distance array: set all distances to infinity except the source (set to 0).
3. Relax all edges V-1 times: for each edge, update the destination's distance if a shorter path is found.
4. Check for negative-weight cycles: if any edge can still be relaxed, report a negative cycle.
5. Print the shortest distances from the source to all vertices.
*/


class Edge {
    int src, dest, weight;
    Edge(int s, int d, int w) {
        src = s; dest = d; weight = w;
    }
}

public class bellmanFord {
    static void bellmanFordAlgo(List<Edge> edges, int V, int src) {
        int[] dist = new int[V]; // Distance array
        Arrays.fill(dist, Integer.MAX_VALUE); // Init distances
        dist[src] = 0; // Distance to source is 0

        // Relax all edges V-1 times
        for (int i = 1; i < V; i++) { // Loop V-1 times
            for (Edge e : edges) { // For each edge
                if (dist[e.src] != Integer.MAX_VALUE && dist[e.src] + e.weight < dist[e.dest]) {
                    dist[e.dest] = dist[e.src] + e.weight; // Update distance
                }
            }
        }

        // Check for negative-weight cycles
        for (Edge e : edges) {
            if (dist[e.src] != Integer.MAX_VALUE && dist[e.src] + e.weight < dist[e.dest]) {
                System.out.println("Negative weight cycle detected");
                return;
            }
        }

        System.out.println("Vertex\tDistance from Source");
        for (int i = 0; i < V; i++)
            System.out.println(i + "\t" + dist[i]);
    }

    public static void main(String[] args) {
        int V = 5; // Number of vertices
        List<Edge> edges = new ArrayList<>();

        // Example 
        edges.add(new Edge(0, 1, -1)); // Edge 0->1 weight -1
        edges.add(new Edge(0, 2, 4));  // Edge 0->2 weight 4
        edges.add(new Edge(1, 2, 3));  // Edge 1->2 weight 3
        edges.add(new Edge(1, 3, 2));  // Edge 1->3 weight 2
        edges.add(new Edge(1, 4, 2));  // Edge 1->4 weight 2
        edges.add(new Edge(3, 2, 5));  // Edge 3->2 weight 5
        edges.add(new Edge(3, 1, 1));  // Edge 3->1 weight 1
        edges.add(new Edge(4, 3, -3)); // Edge 4->3 weight -3

        bellmanFordAlgo(edges, V, 0); // Run Bellman-Ford from source 0
    }
}

/*
Dry Run Example:
Vertices: 0, 1, 2, 3, 4
Edges: (0,1,-1), (0,2,4), (1,2,3), (1,3,2), (1,4,2), (3,2,5), (3,1,1), (4,3,-3)
Source: 0

Iteration 1:
dist[1] = 0 + (-1) = -1
dist[2] = 0 + 4 = 4
dist[3] = -1 + 2 = 1
dist[4] = -1 + 2 = 1

Iteration 2:
dist[2] = -1 + 3 = 2
dist[3] = 1 (no change)
dist[1] = 1 + 1 = 2 (no update, as -1 < 2)
dist[3] = 1 (no change)
dist[3] = 1 (no change)

Continue for V-1 iterations...

Final distances:
0 -> 0
1 -> -1
2 -> 2
3 -> -2
4 -> 1
*/