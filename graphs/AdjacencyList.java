import java.util.ArrayList;
import java.util.List;

// 1 -- 3
// |     | \
// 2 -- 4 -- 5

public class AdjacencyList {
    public static void main(String[] args) {
        int n = 5;
        List<Integer>[] adjList = new List[n+1];

        for (int i = 1; i <= n; i++) {
            adjList[i] = new ArrayList<>();
        }

        // Edges
        adjList[1].add(2); adjList[2].add(1);
        adjList[1].add(3); adjList[3].add(1);
        adjList[2].add(4); adjList[4].add(2);
        adjList[3].add(4); adjList[4].add(3);
        adjList[3].add(5); adjList[5].add(3);
        adjList[4].add(5); adjList[5].add(4);  
        System.out.println("Adjacency List:");
        for (int i = 1; i <= n; i++) {
            System.out.print(i + " -> ");
            for (int node : adjList[i]) {
                System.out.print(node + " ");
            }
            System.out.println();
        }
    }
}

/*
Algorithm Flow:
1. Create an array of lists (adjList), size n+1.
2. Initialize each list to store neighbors.
3. For each edge (u, v), add v to u’s list and u to v’s list (undirected).
4. Print the adjacency list.

Time Complexity (TC):
- Initializing lists: O(n)
- Adding edges: O(E) (E = number of edges)
- Printing adjacency list: O(n + E)
- Overall: O(n + E)

Space Complexity (SC):
- Storing adjacency list: O(n + E)
*/
