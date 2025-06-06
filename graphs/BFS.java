import java.util.*;
//      0
//    / | \
//   2  3  1
//    \
//     4

class BFS {
    public ArrayList<Integer> bfs(ArrayList<ArrayList<Integer>> adj) {
        int V = adj.size(); // number of vertices
        ArrayList<Integer> bfsList = new ArrayList<>();
        boolean[] visited = new boolean[V];
        Queue<Integer> queue = new LinkedList<>();

        // Start from vertex 0
        visited[0] = true;
        queue.add(0);

        while (!queue.isEmpty()) {
            int node = queue.poll();
            bfsList.add(node);

            for (int neighbor : adj.get(node)) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    queue.add(neighbor);
                }
            }
        }

        return bfsList;
    }

    public static void main(String[] args) {
        BFS sol = new BFS();

        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        adj.add(new ArrayList<>(Arrays.asList(2, 3, 1)));
        adj.add(new ArrayList<>(Arrays.asList(0)));
        adj.add(new ArrayList<>(Arrays.asList(0, 4)));
        adj.add(new ArrayList<>(Arrays.asList(0)));
        adj.add(new ArrayList<>(Arrays.asList(2)));

        ArrayList<Integer> result = sol.bfs(adj);
        System.out.println(result);  // Output: [0, 2, 3, 1, 4]
    }
}


/*
 * Algorithm Flow:
 * 1. Start from node 0.
 * 2. Mark as visited and add to queue.
 * 3. While queue is not empty:
 *    - Remove node and add to result.
 *    - Add unvisited neighbors to queue.
 *
 * Time Complexity: O(V + E)
 *   (V = number of vertices, E = number of edges)
 *
 * Space Complexity: O(V)
 *   (visited array + queue)
 */