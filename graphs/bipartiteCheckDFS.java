/*
Flow:
1. Represent graph as adjacency list.
2. Use DFS to try coloring nodes with 2 colors.
3. If any adjacent nodes have same color, not bipartite.
4. Main function builds graph, calls bipartite check, prints result.
*/

import java.util.*;

public class bipartiteCheckDFS {
    static boolean dfs(int node, int color, int[] colors, List<List<Integer>> graph) {
        colors[node] = color; // color current node
        for (int neighbor : graph.get(node)) { // check neighbors
            if (colors[neighbor] == -1) { // if not colored
                if (!dfs(neighbor, 1 - color, colors, graph)) // color with opposite color
                    return false; // not bipartite
            } else if (colors[neighbor] == color) { // same color neighbor
                return false; // not bipartite
            }
        }
        return true; // all good
    }

    static boolean isBipartite(int n, List<List<Integer>> graph) {
        int[] colors = new int[n];
        Arrays.fill(colors, -1); // -1 means uncolored
        for (int i = 0; i < n; i++) {
            if (colors[i] == -1) { // not colored yet
                if (!dfs(i, 0, colors, graph))
                    return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int n = 4; // number of nodes
        // Example: 0-1, 1-2, 2-3, 3-0 (even cycle, bipartite)
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) graph.add(new ArrayList<>());
        graph.get(0).add(1); graph.get(1).add(0);
        graph.get(1).add(2); graph.get(2).add(1);
        graph.get(2).add(3); graph.get(3).add(2);
        graph.get(3).add(0); graph.get(0).add(3);

        if (isBipartite(n, graph))
            System.out.println("Graph is Bipartite");
        else
            System.out.println("Graph is NOT Bipartite");
    }
}

/*
Dry Run:
Nodes: 0-1-2-3-0 (cycle of 4)
Start at node 0, color 0.
Node 1 gets color 1.
Node 2 gets color 0.
Node 3 gets color 1.
No adjacent nodes have same color.
Output: Graph is Bipartite
*/