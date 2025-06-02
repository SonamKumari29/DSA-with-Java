import java.util.*;

public class detectCycleUndirDFS {
    private int V;
    private List<List<Integer>> adj;

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
