
/*
Flow:
1. Build adjacency list for the graph.
2. Use DFS to visit all nodes.
3. On returning from DFS, push node to stack.
4. Pop from stack for topological order.
*/

import java.util.*;

public class topoSortDFS {
    static void dfs(int node, boolean[] visited, Stack<Integer> stack, List<List<Integer>> adj) {
        visited[node] = true; // mark visited
        for (int nbr : adj.get(node)) { // visit neighbors
            if (!visited[nbr])
                dfs(nbr, visited, stack, adj);
        }
        stack.push(node); // push after visiting all neighbors
    }

    static List<Integer> topoSort(int V, List<List<Integer>> adj) {
        boolean[] visited = new boolean[V]; // visited array
        Stack<Integer> stack = new Stack<>(); // stack for order

        for (int i = 0; i < V; i++) { // for all nodes
            if (!visited[i])
                dfs(i, visited, stack, adj);
        }

        List<Integer> result = new ArrayList<>();
        while (!stack.isEmpty())
            result.add(stack.pop()); // pop for topological order
        return result;
    }

    public static void main(String[] args) {
        int V = 6; 
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++)
            adj.add(new ArrayList<>());

        adj.get(5).add(2); // 5 -> 2
        adj.get(5).add(0); // 5 -> 0
        adj.get(4).add(0); // 4 -> 0
        adj.get(4).add(1); // 4 -> 1
        adj.get(2).add(3); // 2 -> 3
        adj.get(3).add(1); // 3 -> 1

        List<Integer> topoOrder = topoSort(V, adj);
        System.out.println("Topological Sort: " + topoOrder);
    }
}

/*
Dry Run:
Graph:
5 -> 2, 0
4 -> 0, 1
2 -> 3
3 -> 1

DFS from 0: push 0
DFS from 1: push 1
DFS from 2: 2->3->1, push 1,3,2
DFS from 3: already visited
DFS from 4: 4->0,1, push 4
DFS from 5: 5->2,0, push 5

Stack (top to bottom): 5 4 2 3 1 0
Topological Order: [5, 4, 2, 3, 1, 0]
*/