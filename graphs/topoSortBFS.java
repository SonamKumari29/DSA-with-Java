/*
Flow:
1. Build adjacency list and indegree array.
2. Add nodes with indegree 0 to queue.
3. Pop from queue, add to result, decrease indegree of neighbors.
4. If neighbor indegree becomes 0, add to queue.
5. Repeat until queue is empty.
*/
//kahn's algorithm for topological sorting using BFS
import java.util.*;

public class topoSortBFS {
    public static List<Integer> topoSort(int V, List<List<Integer>> adj) {
        int[] indegree = new int[V]; // indegree array
        for (int u = 0; u < V; u++) {
            for (int v : adj.get(u)) indegree[v]++; // count indegrees
        }

        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < V; i++) {
            if (indegree[i] == 0) q.add(i); // add nodes with indegree 0
        }

        List<Integer> topo = new ArrayList<>();
        while (!q.isEmpty()) {
            int u = q.poll(); // get node
            topo.add(u); // add to result
            for (int v : adj.get(u)) {
                indegree[v]--; // decrease indegree
                if (indegree[v] == 0) q.add(v); // if indegree 0, add to queue
            }
        }
        return topo;
    }

    public static void main(String[] args) {
        int V = 6; 
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) adj.add(new ArrayList<>());
        //  edges: 5->0, 5->2, 4->0, 4->1, 2->3, 3->1
        adj.get(5).add(0);
        adj.get(5).add(2);
        adj.get(4).add(0);
        adj.get(4).add(1);
        adj.get(2).add(3);
        adj.get(3).add(1);

        List<Integer> result = topoSort(V, adj);
        System.out.println("Topological Sort: " + result);

        // Dry run:
        // indegree: [2,2,1,1,0,0]
        // queue: [4,5]
        // pop 4 -> [5], result [4]
        // pop 5 -> [], result [4,5]
        // add 0,2 to queue (indegree 0)
        // pop 0 -> [2], result [4,5,0]
        // pop 2 -> [], result [4,5,0,2]
        // add 3 to queue
        // pop 3 -> [], result [4,5,0,2,3]
        // add 1 to queue
        // pop 1 -> [], result [4,5,0,2,3,1]
    }
}