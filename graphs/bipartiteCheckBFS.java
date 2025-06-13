/*
Flow:
1. Represent the graph as adjacency list.
2. Use BFS to try coloring the graph with 2 colors.
3. If any adjacent nodes have same color, it's not bipartite.
4. Return true if successfully colored, else false.
5. Main function demonstrates with a sample graph.
*/

import java.util.*;

public class bipartiteCheckBFS {

    static boolean isBipartite(int[][] graph) {
        int n = graph.length;
        int[] color = new int[n]; // 0: uncolored, 1: color1, -1: color2

        for (int i = 0; i < n; i++) {
            if (color[i] == 0) { // not colored yet
                Queue<Integer> q = new LinkedList<>();
                q.add(i);
                color[i] = 1; // assign first color

                while (!q.isEmpty()) {
                    int node = q.poll();
                    for (int neighbor : graph[node]) {
                        if (color[neighbor] == 0) { // not colored
                            color[neighbor] = -color[node]; // assign opposite color
                            q.add(neighbor);
                        } else if (color[neighbor] == color[node]) {
                            return false; // same color found, not bipartite
                        }
                    }
                }
            }
        }
        return true; // all nodes colored successfully
    }

    public static void main(String[] args) {
        int[][] graph1 = {
            {1,3},    // 0
            {0,2},    // 1
            {1,3},    // 2
            {0,2}     // 3
        };
        System.out.println("Graph1 is bipartite: " + isBipartite(graph1)); // true

        // Example 2: Not bipartite
        int[][] graph2 = {
            {1,2,3},  // 0
            {0,2},    // 1
            {0,1,3},  // 2
            {0,2}     // 3
        };
        System.out.println("Graph2 is bipartite: " + isBipartite(graph2)); // false
    }
}

/*
Dry Run for graph1:
graph1 = [[1,3],[0,2],[1,3],[0,2]]
Start at node 0, color 1
  Node 0 -> neighbors 1,3: color both -1
  Node 1 -> neighbor 2: color 1
  Node 3 -> neighbor 2: already colored 1, ok
  Node 2 -> neighbors 1,3: already colored -1, ok
No conflicts, so bipartite.

Dry Run for graph2:
graph2 = [[1,2,3],[0,2],[0,1,3],[0,2]]
Start at node 0, color 1
  Node 0 -> neighbors 1,2,3: color all -1
  Node 1 -> neighbor 2: already colored -1 (same as node 1), conflict!
So, not bipartite.
*/