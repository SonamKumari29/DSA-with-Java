import java.util.*;

// Algorithm flow (using set):
// 1. Initialize distances from source to all vertices as infinity, except source itself (0).
// 2. Use a TreeSet to always pick the vertex with the smallest tentative distance.
// 3. For the picked vertex, update distances to its neighbors if a shorter path is found.
// 4. Repeat until all vertices are processed.
// 5. Output the shortest distances from the source.


public class dijkstraSET {
    static class Pair {
        int vertex, weight;
        Pair(int v, int w) { vertex = v; weight = w; }
    }

    static void dijkstra(int V, List<List<Pair>> adj, int src) {
        int[] dist = new int[V]; // shortest distances
        Arrays.fill(dist, Integer.MAX_VALUE); // init distances
        dist[src] = 0; // distance to source is 0

        // set of pairs (distance, vertex)
        TreeSet<Pair> set = new TreeSet<>(Comparator.comparingInt(a -> a.weight));
        set.add(new Pair(src, 0)); // add source

        while (!set.isEmpty()) {
            Pair curr = set.pollFirst(); // get min distance node
            int u = curr.vertex;

            for (Pair neighbor : adj.get(u)) { // for all neighbors
                int v = neighbor.vertex, w = neighbor.weight;
                if (dist[u] + w < dist[v]) { // found shorter path
                    set.remove(new Pair(v, dist[v])); // remove old pair if exists
                    dist[v] = dist[u] + w; // update distance
                    set.add(new Pair(v, dist[v])); // add new pair
                }
            }
        }

        System.out.println("Vertex\tDistance from Source");
        for (int i = 0; i < V; i++)
            System.out.println(i + "\t" + dist[i]);
    }

    public static void main(String[] args) {
        // Example :
        // 5 vertices, 6 edges
        // Edges: 0-1(2), 0-2(4), 1-2(1), 1-3(7), 2-4(3), 3-4(1)
        int V = 5;
        List<List<Pair>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) adj.add(new ArrayList<>());
        adj.get(0).add(new Pair(1, 2));
        adj.get(0).add(new Pair(2, 4));
        adj.get(1).add(new Pair(2, 1));
        adj.get(1).add(new Pair(3, 7));
        adj.get(2).add(new Pair(4, 3));
        adj.get(3).add(new Pair(4, 1));

        int src = 0; // source vertex
        dijkstra(V, adj, src);
    }
}

/*
Dry Run:
Graph:
0 --2--> 1 --1--> 2 --3--> 4
 \       |         ^
  \      7         |
   \---->3---------/

Start from 0:
dist[0]=0, dist[1]=2, dist[2]=3, dist[3]=9, dist[4]=6

Output:
Vertex  Distance from Source
0       0
1       2
2       3
3       9
4       6
*/