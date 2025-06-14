
/*
Flow of Prim's Algorithm:
1. Start with any node as the starting point.
2. Mark the starting node as visited.
3. Find the smallest edge connecting a visited node to an unvisited node.
4. Add that edge to the Minimum Spanning Tree (MST).
5. Mark the new node as visited.
6. Repeat steps 3-5 until all nodes are visited.
*/
import java.util.*;

public class primsAlgo {
    static class Edge {
        int dest, weight;
        Edge(int dest, int weight) {
            this.dest = dest; // destination node
            this.weight = weight; // edge weight
        }
    }

    static int prims(List<Edge>[] graph, int n) {
        boolean[] visited = new boolean[n]; // track visited nodes
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1])); // min-heap: [node, weight]
        pq.offer(new int[]{0, 0}); // start from node 0
        int mstWeight = 0; // total weight of MST

        while (!pq.isEmpty()) {
            int[] curr = pq.poll(); // get edge with min weight
            int node = curr[0], weight = curr[1];
            if (visited[node]) continue; // skip if already visited
            visited[node] = true; // mark as visited
            mstWeight += weight; // add edge weight to MST

            for (Edge edge : graph[node]) { // check all adjacent edges
                if (!visited[edge.dest]) {
                    pq.offer(new int[]{edge.dest, edge.weight}); // add unvisited neighbors
                }
            }
        }
        return mstWeight; // return total MST weight
    }

    public static void main(String[] args) {
        int n = 5; // number of nodes
        List<Edge>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) graph[i] = new ArrayList<>(); // init graph

        // add undirected edges: (u, v, w)
        graph[0].add(new Edge(1, 2)); graph[1].add(new Edge(0, 2));
        graph[0].add(new Edge(3, 6)); graph[3].add(new Edge(0, 6));
        graph[1].add(new Edge(2, 3)); graph[2].add(new Edge(1, 3));
        graph[1].add(new Edge(3, 8)); graph[3].add(new Edge(1, 8));
        graph[1].add(new Edge(4, 5)); graph[4].add(new Edge(1, 5));
        graph[2].add(new Edge(4, 7)); graph[4].add(new Edge(2, 7));

        int mstWeight = prims(graph, n); 
        System.out.println("Total weight of MST: " + mstWeight); 

        // Dry Run:
        // Start at node 0, pick edge (0-1,2), then (1-2,3), (1-4,5), (0-3,6)
        // MST edges: (0-1), (1-2), (1-4), (0-3) => total weight = 2+3+5+6 = 16
    }
}