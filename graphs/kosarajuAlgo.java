/*
Kosaraju's Algorithm Flow:
1. Do a DFS traversal of the original graph, pushing finished vertices onto a stack.
2. Reverse (transpose) the graph.
3. Pop vertices from the stack and do DFS on the transposed graph.
4. Each DFS on the transposed graph gives one strongly connected component (SCC).
*/


import java.util.*;
public class kosarajuAlgo {
    static void addEdge(List<List<Integer>> adj, int u, int v) {
        adj.get(u).add(v); 
    }

    static void dfs(int v, boolean[] visited, Stack<Integer> stack, List<List<Integer>> adj) {
        visited[v] = true; 
        for (int n : adj.get(v)) {
            if (!visited[n])
                dfs(n, visited, stack, adj); // DFS for unvisited neighbors
        }
        stack.push(v); // push after visiting all neighbors
    }

    static List<List<Integer>> getTranspose(List<List<Integer>> adj, int V) {
        List<List<Integer>> transpose = new ArrayList<>();
        for (int i = 0; i < V; i++)
            transpose.add(new ArrayList<>());
        for (int v = 0; v < V; v++) {
            for (int n : adj.get(v)) {
                transpose.get(n).add(v); // reverse the edge
            }
        }
        return transpose;
    }

    static void DFSUtil(int v, boolean[] visited, List<List<Integer>> adj, List<Integer> component) {
        visited[v] = true; 
        component.add(v); // add to current SCC
        for (int n : adj.get(v)) {
            if (!visited[n])
                DFSUtil(n, visited, adj, component); // DFS for unvisited neighbors
        }
    }

    static void printSCCs(List<List<Integer>> adj, int V) {
        Stack<Integer> stack = new Stack<>();
        boolean[] visited = new boolean[V];

        // Step 1: Fill vertices in stack according to their finishing times
        for (int i = 0; i < V; i++)
            if (!visited[i])
                dfs(i, visited, stack, adj);

        // Step 2: Transpose the graph
        List<List<Integer>> transpose = getTranspose(adj, V);

        // Step 3: Process all vertices in order defined by Stack
        Arrays.fill(visited, false); // reset visited for 2nd DFS

        System.out.println("Strongly Connected Components:");
        while (!stack.isEmpty()) {
            int v = stack.pop();
            if (!visited[v]) {
                List<Integer> component = new ArrayList<>();
                DFSUtil(v, visited, transpose, component); // DFS on transposed graph
                System.out.println(component); 
            }
        }
    }

    public static void main(String[] args) {
        int V = 5; // number of vertices
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++)
            adj.add(new ArrayList<>());

        // Example graph
        addEdge(adj, 0, 1);
        addEdge(adj, 1, 2);
        addEdge(adj, 2, 0);
        addEdge(adj, 1, 3);
        addEdge(adj, 3, 4);

        printSCCs(adj, V); // print all SCCs
    }
}
// Dry Run Example:
// Graph: 0 → 1, 1 → 2, 2 → 0, 1 → 3, 3 → 4
// Step 1: DFS order (finish times): 4,3,1,2,0 (stack: 4,3,1,2,0)
// Step 2: Transpose edges.
// Step 3: Pop from stack, DFS on transpose:
//     - Start at 0: visit 0,2,1 (SCC: 0,2,1)
//     - Next: 3 (SCC: 3)
//     - Next: 4 (SCC: 4)