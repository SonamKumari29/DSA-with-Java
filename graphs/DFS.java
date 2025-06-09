import java.util.*;
// 0 -- 2
// |  /   \
// 3      4
// |
// 1
class DFS {
    public ArrayList<Integer> dfs(ArrayList<ArrayList<Integer>> adj) {
        int V = adj.size();  // number of vertices
        boolean[] visited = new boolean[V];//visited array
        ArrayList<Integer> dfsList = new ArrayList<>();//dfs ans 

        dfsUtil(0, adj, visited, dfsList);//helper fn 
        return dfsList;
    }
    // Recursive helper function for DFS
    private void dfsUtil(int node, ArrayList<ArrayList<Integer>> adj, boolean[] visited, ArrayList<Integer> dfsList) {
        visited[node] = true;//visit every node
        dfsList.add(node);//add it to dfslist
        for (int neighbor : adj.get(node)) {//neighbor of node
            if (!visited[neighbor]) {//if neighbour not vis
                dfsUtil(neighbor, adj, visited, dfsList);
            }
        }
    }
    public static void main(String[] args) {
        DFS sol = new DFS(); //object of class
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        adj.add(new ArrayList<>(Arrays.asList(2, 3, 1))); // 0 -> 2, 3, 1
        adj.add(new ArrayList<>(Arrays.asList(0)));// 1 -> 0
        adj.add(new ArrayList<>(Arrays.asList(0, 4))); // 2 -> 0, 4
        adj.add(new ArrayList<>(Arrays.asList(0))); // 3 -> 0
        adj.add(new ArrayList<>(Arrays.asList(2)));  // 4 -> 2
        ArrayList<Integer> result = sol.dfs(adj);
        System.out.println(result);  // Output: [0, 2, 4, 3, 1]
    }
}

// Algorithm Flow:
// 1. Start DFS traversal from node 0.
// 2. Mark the current node as visited and add it to the result list.
// 3. Recursively visit all unvisited neighbors of the current node.
//
// Time Complexity: O(V + E)
//   - V = number of vertices
//   - E = number of edges
//
// Space Complexity: O(V)
//   - Due to the visited array and recursion stack
