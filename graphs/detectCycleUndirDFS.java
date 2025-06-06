import java.util.*;

public class detectCycleUndirDFS {
    private final int V;
    private final List<List<Integer>> adj;

    public detectCycleUndirDFS(int V) {
        this.V = V;
        adj = new ArrayList<>();
        for (int i = 0; i < V; i++)
            adj.add(new ArrayList<>());
    }

    public void addEdge(int u, int v) {
        adj.get(u).add(v);
        adj.get(v).add(u); // undirected graph
    }

    private boolean dfs(int v, boolean[] visited, int parent) {
        visited[v] = true;
        for (int neighbor : adj.get(v)) {
            if (!visited[neighbor]) {
                if (dfs(neighbor, visited, v))
                    return true;
            } else if (neighbor != parent) {
                return true;
            }
        }
        return false;
    }

    public boolean hasCycle() {
        boolean[] visited = new boolean[V];
        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                if (dfs(i, visited, -1))
                    return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        detectCycleUndirDFS g = new detectCycleUndirDFS(5);
        g.addEdge(0, 1);
        g.addEdge(1, 2);
        g.addEdge(2, 3);
        g.addEdge(3, 4);
        g.addEdge(4, 0);
        if (g.hasCycle())
            System.out.println("Graph contains cycle");
        else
            System.out.println("Graph doesn't contain cycle");
    }
}

/*
Algorithm Flow:

1. Initialize graph with adjacency list.

2. Add undirected edges.

3. For each unvisited vertex, run DFS:
    - Mark current vertex as visited.
    - For each neighbor:
      - If neighbor is not visited, recursively DFS on neighbor with current as parent.
      - If neighbor is visited and not parent, a cycle is found → return true.

4. If no cycle is found after all DFS calls, return false.

Time Complexity: O(V + E)
Space Complexity: O(V + E) (adjacency list + visited array + recursion stack)
*/
