import java.util.*;
// 0 -- 2
// |  /   \
// 3      4
// |
// 1

class DFS {
    public ArrayList<Integer> dfs(ArrayList<ArrayList<Integer>> adj) {
        int V = adj.size();  // number of vertices
        boolean[] visited = new boolean[V];
        ArrayList<Integer> dfsList = new ArrayList<>();

        dfsUtil(0, adj, visited, dfsList);
        return dfsList;
    }

    // Recursive helper function for DFS
    private void dfsUtil(int node, ArrayList<ArrayList<Integer>> adj, boolean[] visited, ArrayList<Integer> dfsList) {
        visited[node] = true;
        dfsList.add(node);

        for (int neighbor : adj.get(node)) {
            if (!visited[neighbor]) {
                dfsUtil(neighbor, adj, visited, dfsList);
            }
        }
    }

    // Optional: Main method for testing
    public static void main(String[] args) {
        DFS sol = new DFS();

        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        adj.add(new ArrayList<>(Arrays.asList(2, 3, 1)));
        adj.add(new ArrayList<>(Arrays.asList(0)));
        adj.add(new ArrayList<>(Arrays.asList(0, 4)));
        adj.add(new ArrayList<>(Arrays.asList(0)));
        adj.add(new ArrayList<>(Arrays.asList(2)));

        ArrayList<Integer> result = sol.dfs(adj);
        System.out.println(result);  // Output: [0, 2, 4, 3, 1]
    }
}
