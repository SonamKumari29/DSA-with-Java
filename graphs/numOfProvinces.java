import java.util.ArrayList; // ArrayList import

class numOfProvinces {
    // DFS function
    static void dfs(int node, ArrayList<ArrayList<Integer>> adjLs, int[] vis) {
        vis[node] = 1; // Mark visited
        for (int neighbor : adjLs.get(node)) { // For each neighbor
            if (vis[neighbor] == 0) { // If not visited
                dfs(neighbor, adjLs, vis); // Visit neighbor
            }
        }
    }

    static int numProvinces(ArrayList<ArrayList<Integer>> adj, int V) {
        ArrayList<ArrayList<Integer>> adjLs = new ArrayList<>(); // Adjacency list
        for (int i = 0; i < V; i++) {
            adjLs.add(new ArrayList<>()); // Init list
        }
        // Matrix to list
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                if (adj.get(i).get(j) == 1 && i != j) { // If edge
                    adjLs.get(i).add(j); // Add neighbor
                }
            }
        }
        int[] vis = new int[V]; // Visited array
        int cnt = 0; // Province count
        for (int i = 0; i < V; i++) {
            if (vis[i] == 0) { // If not visited
                cnt++; // New province
                dfs(i, adjLs, vis); // DFS call
            }
        }
        return cnt; // Return count
    }

    public static void main(String[] args) {
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();

        adj.add(new ArrayList<>());
        adj.get(0).add(1);
        adj.get(0).add(0);
        adj.get(0).add(1);

        adj.add(new ArrayList<>());
        adj.get(1).add(0);
        adj.get(1).add(1);
        adj.get(1).add(0);

        adj.add(new ArrayList<>());
        adj.get(2).add(1);
        adj.get(2).add(0);
        adj.get(2).add(1);

        System.out.println(numOfProvinces.numProvinces(adj, 3)); // Print result
    }
}

/*
Algorithm Flow:
1. Convert adjacency matrix to adjacency list.
2. For each unvisited node, call DFS.
3. Each DFS marks a whole province.
4. Count total DFS calls = total provinces.

Time Complexity: O(V + E)
    

Space Complexity: O(V)
    - Visited array + recursion stack in DFS
*/
