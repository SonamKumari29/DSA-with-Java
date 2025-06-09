
import java.util.*;
//      0
//    / | \
//   2  3  1
//    \
//     4
class BFS {
    public ArrayList<Integer> bfs(ArrayList<ArrayList<Integer>> adj) {//bfs fn that takes adj list and return bfs order
        int V = adj.size(); // number of vertices
        ArrayList<Integer> bfsList = new ArrayList<>();//bfs ans store here
        boolean[] visited = new boolean[V]; //visited array
        Queue<Integer> queue = new LinkedList<>();//fifo
        visited[0] = true;// start from v=0
        queue.add(0);//0 added to queue
        while (!queue.isEmpty()) {
            int node = queue.poll();//remove front of queue
            bfsList.add(node);//add to bfs list
            for (int neighbor : adj.get(node)) {//node's neighbors
                if (!visited[neighbor]) { //if not vis
                    visited[neighbor] = true;// mark it vis
                    queue.add(neighbor);//add to queue
                }
            }
        }
        return bfsList;
    }
    public static void main(String[] args) {
        BFS sol = new BFS();//object of class
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>(); //adjacency list
        adj.add(new ArrayList<>(Arrays.asList(2, 3, 1))); // 0 -> 2, 3, 1
        adj.add(new ArrayList<>(Arrays.asList(0))); // 1 -> 0
        adj.add(new ArrayList<>(Arrays.asList(0, 4))); // 2 -> 0, 4
        adj.add(new ArrayList<>(Arrays.asList(0)));// 3 -> 0
        adj.add(new ArrayList<>(Arrays.asList(2)));// 4 -> 2
        ArrayList<Integer> result = sol.bfs(adj);//
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