/*
Flow (BFS/Kahn's Algorithm for cycle detection in directed graph)
1. Compute indegree[] for all nodes.
2. Add all nodes with indegree 0 to a queue.
3. While queue is not empty:
    a. Remove node from queue, increment count of processed nodes.
    b. For each neighbor, decrement indegree; if indegree becomes 0, add to queue.
4. If count of processed nodes != total nodes, cycle exists (return true); else, no cycle (return false).
*/

import java.util.*;

public class detectCycleDirBFS {

    public boolean isCyclicBFS(int V, ArrayList<ArrayList<Integer>> adj) {
        int[] indegree = new int[V]; // incoming edges for each node

        for (int i = 0; i < V; i++) {
            for (int neighbor : adj.get(i)) {
                indegree[neighbor]++;
            }
        }

        Queue<Integer> q = new LinkedList<>();

        // push nodes with indegree 0
        for (int i = 0; i < V; i++) {
            if (indegree[i] == 0) q.add(i);
        }

        int count = 0; // count of processed nodes
        while (!q.isEmpty()) {
            int node = q.poll();
            count++;

            for (int neighbor : adj.get(node)) {
                indegree[neighbor]--;         // remove edge
                if (indegree[neighbor] == 0)  // if now indegree is 0, push
                    q.add(neighbor);
            }
        }

        // if all nodes not processed → cycle exists
        return count != V;
    }

    public static void main(String[] args) {
        /*
           Graph:
           0 → 1 → 2 → 3
               ↑       ↓
                 ← ← ←
           This contains a cycle: 1 → 2 → 3 → 1
        */

        int V = 4; // total nodes
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();

        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }

        // edges
        adj.get(0).add(1);
        adj.get(1).add(2);
        adj.get(2).add(3);
        adj.get(3).add(1); // forms a cycle

        detectCycleDirBFS obj = new detectCycleDirBFS();
        boolean hasCycle = obj.isCyclicBFS(V, adj);

        if (hasCycle)
            System.out.println("Cycle detected in the directed graph.");
        else
            System.out.println("No cycle in the directed graph.");
    }
}