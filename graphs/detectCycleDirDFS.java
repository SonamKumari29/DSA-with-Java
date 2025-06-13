/*
Flow
1. Create visited[] and recursionStack[] arrays.
2. For each node, if not visited, call DFS.
3. In DFS, mark node as visited and add to recursionStack.
4. For each neighbor, if not visited, DFS recursively; if in recursionStack, cycle found.
5. After DFS, remove node from recursionStack.
6. If any DFS finds a cycle, return true; else, return false.
*/
import java.util.*;

public class detectCycleDirDFS {
    public boolean isCyclic(int V, ArrayList<ArrayList<Integer>> adj) {
        boolean[] visited = new boolean[V]; // visited array
        boolean[] helper = new boolean[V]; // recursion stack

        for (int i = 0; i < V; i++) { // loop all nodes
            if (!visited[i]) { // if not visited
                boolean ans = dfs(i, adj, visited, helper); // call dfs
                if (ans) return true; // cycle found
            }
        }
        return false; // no cycle
    }

    private static boolean dfs(int node, ArrayList<ArrayList<Integer>> adj, boolean[] visited, boolean[] helper) {
        visited[node] = true; // mark visited
        helper[node] = true; // add to stack

        for (int neighbor : adj.get(node)) { // check neighbors
            if (!visited[neighbor]) { // not visited
                if (dfs(neighbor, adj, visited, helper)) { // dfs call
                    return true; // cycle found
                }
            } else if (helper[neighbor]) { // in stack
                return true; // cycle found
            }
        }

        helper[node] = false; // remove from stack
        return false; // no cycle
    }

    public static void main(String[] args) {
        detectCycleDirDFS sol = new detectCycleDirDFS(); // create object
        int V = 5; // number of nodes
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>(); // adjacency list
        for (int i = 0; i < V; i++) adj.add(new ArrayList<>()); // init lists

        adj.get(0).add(1); // 0->1
        adj.get(1).add(2); // 1->2
        adj.get(2).add(0); // 2->0 (cycle)
        adj.get(3).add(4); // 3->4

        System.out.println(sol.isCyclic(V, adj)); 
    }
}


