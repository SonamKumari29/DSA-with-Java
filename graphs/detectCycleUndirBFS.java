import java.util.*;

public class detectCycleUndirBFS {
    static class Pair {
        int node, parent;
        Pair(int node, int parent) {
            this.node = node;
            this.parent = parent;
        }
    }

    public static boolean isCyclic(int V, List<List<Integer>> adj) {
        boolean[] visited = new boolean[V];
        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                if (bfsCheck(i, adj, visited)) return true;
            }
        }
        return false;
    }

    private static boolean bfsCheck(int src, List<List<Integer>> adj, boolean[] visited) {
        Queue<Pair> q = new LinkedList<>();
        visited[src] = true;
        q.add(new Pair(src, -1));
        while (!q.isEmpty()) {
            Pair p = q.poll();
            int node = p.node;
            int parent = p.parent;
            for (int neighbor : adj.get(node)) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    q.add(new Pair(neighbor, node));
                } else if (neighbor != parent) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int V = 5;
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) adj.add(new ArrayList<>());
        adj.get(0).add(1); adj.get(1).add(0);
        adj.get(1).add(2); adj.get(2).add(1);
        adj.get(2).add(3); adj.get(3).add(2);
        adj.get(3).add(4); adj.get(4).add(3);
        adj.get(4).add(1); adj.get(1).add(4);

        System.out.println(isCyclic(V, adj) ? "Cycle detected" : "No cycle");
    }
}

// Algorithm Flow:
// 1. Start from each unvisited node.
// 2. Use BFS and keep track of the parent node for each visited node.
// 3. For each neighbor:
//    - If the neighbor is not visited, mark it visited and add to the queue with current node as parent.
//    - If the neighbor is visited and is not the parent, a cycle exists.
// 4. If no such case is found after checking all nodes, then no cycle exists.

// Time Complexity:
// - Each node and edge is visited at most once: O(V + E)

// Space Complexity:
// - Space for adjacency list and visited array: O(V + E)
