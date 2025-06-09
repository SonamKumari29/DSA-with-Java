import java.util.*; // Import all utility classes

public class detectCycleUndirBFS {
    static class Pair { // Helper class to store node and its parent
        int node, parent;
        Pair(int node, int parent) {
            this.node = node; // Node value
            this.parent = parent; // Parent value
        }
    }

    public static boolean isCyclic(int V, List<List<Integer>> adj) {
        boolean[] visited = new boolean[V]; // Track visited nodes
        for (int i = 0; i < V; i++) { // For each node
            if (!visited[i]) { // If not visited
                if (bfsCheck(i, adj, visited)) return true; // If cycle found, return true
            }
        }
        return false; // No cycle found
    }

    private static boolean bfsCheck(int src, List<List<Integer>> adj, boolean[] visited) {
        Queue<Pair> q = new LinkedList<>(); // Queue for BFS
        visited[src] = true; // Mark source as visited
        q.add(new Pair(src, -1)); // Add source with no parent
        while (!q.isEmpty()) { // While queue not empty
            Pair p = q.poll(); // Get next node
            int node = p.node; // Current node
            int parent = p.parent; // Parent node
            for (int neighbor : adj.get(node)) { // For each neighbor
                if (!visited[neighbor]) { // If neighbor not visited
                    visited[neighbor] = true; // Mark as visited
                    q.add(new Pair(neighbor, node)); // Add to queue with parent
                } else if (neighbor != parent) { // If visited and not parent
                    return true; // Cycle found
                }
            }
        }
        return false; // No cycle found in this component
    }

    public static void main(String[] args) {
        int V = 5; // Number of vertices
        List<List<Integer>> adj = new ArrayList<>(); // Adjacency list
        for (int i = 0; i < V; i++) adj.add(new ArrayList<>()); // Initialize lists
        adj.get(0).add(1); adj.get(1).add(0); // Edge 0-1
        adj.get(1).add(2); adj.get(2).add(1); // Edge 1-2
        adj.get(2).add(3); adj.get(3).add(2); // Edge 2-3
        adj.get(3).add(4); adj.get(4).add(3); // Edge 3-4
        adj.get(4).add(1); adj.get(1).add(4); // Edge 4-1 (creates cycle)

        System.out.println(isCyclic(V, adj) ? "Cycle detected" : "No cycle"); // output 
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
