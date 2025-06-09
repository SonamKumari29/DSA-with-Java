import java.util.*; // import utilities

public class detectCycleUndirDFS { // class for cycle detection
    private final int V; // number of vertices
    private final List<List<Integer>> adj; // adjacency list

    public detectCycleUndirDFS(int V) { // constructor
        this.V = V; // set vertices
        adj = new ArrayList<>(); // init adjacency list
        for (int i = 0; i < V; i++) // for each vertex
            adj.add(new ArrayList<>()); // add empty list
    }

    public void addEdge(int u, int v) { // add undirected edge
        adj.get(u).add(v); // add v to u's list
        adj.get(v).add(u); // add u to v's list
    }

    private boolean dfs(int v, boolean[] visited, int parent) { // DFS helper
        visited[v] = true; // mark current as visited
        for (int neighbor : adj.get(v)) { // for each neighbor
            if (!visited[neighbor]) { // if not visited
                if (dfs(neighbor, visited, v)) // DFS on neighbor
                    return true; // cycle found
            } else if (neighbor != parent) { // visited and not parent
                return true; // cycle found
            }
        }
        return false; // no cycle
    }

    public boolean hasCycle() { // check for cycle
        boolean[] visited = new boolean[V]; // visited array
        for (int i = 0; i < V; i++) { // for each vertex
            if (!visited[i]) { // if not visited
                if (dfs(i, visited, -1)) // DFS from i
                    return true; // cycle found
            }
        }
        return false; // no cycle
    }

    public static void main(String[] args) { // main method
        detectCycleUndirDFS g = new detectCycleUndirDFS(5); // create graph with 5 vertices
        g.addEdge(0, 1); // add edge 0-1
        g.addEdge(1, 2); // add edge 1-2
        g.addEdge(2, 3); // add edge 2-3
        g.addEdge(3, 4); // add edge 3-4
        g.addEdge(4, 0); // add edge 4-0 (forms a cycle)
        if (g.hasCycle()) // check for cycle
            System.out.println("Graph contains cycle"); // print if cycle
        else
            System.out.println("Graph doesn't contain cycle"); // print if no cycle
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
