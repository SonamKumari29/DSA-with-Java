import java.util.ArrayList;

class numOfProvinces {
    private static void dfs(int node, ArrayList<ArrayList<Integer>> adjLs, int vis[]) {
        vis[node] = 1; 
        for (Integer it : adjLs.get(node)) {
            if (vis[it] == 0) {
                dfs(it, adjLs, vis); 
            }
        }
    }

    static int numProvinces(ArrayList<ArrayList<Integer>> adj, int V) {
        ArrayList<ArrayList<Integer>> adjLs = new ArrayList<>(); 
        for (int i = 0; i < V; i++) {
            adjLs.add(new ArrayList<>()); 
        }
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                if (adj.get(i).get(j) == 1 && i != j) {
                    adjLs.get(i).add(j); 
                }
            }
        }
        int vis[] = new int[V]; 
        int cnt = 0; 
        for (int i = 0; i < V; i++) {
            if (vis[i] == 0) {
                cnt++;
                dfs(i, adjLs, vis); 
            }
        }
        return cnt; 
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

        System.out.println(numOfProvinces.numProvinces(adj, 3));
    }
}

/*
Algorithm Flow:
1. Convert adjacency matrix to adjacency list.
2. For each unvisited node, call DFS.
3. Each DFS marks a whole province.
4. Count total DFS calls = total provinces.

Time Complexity: O(V + E)
    - V = number of nodes
    - E = number of edges in the graph

Space Complexity: O(V)
    - Visited array + recursion stack in DFS
*/
