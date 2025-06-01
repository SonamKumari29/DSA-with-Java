import java.util.ArrayList;
import java.util.List;

// 1 -- 3
// |     | \
// 2 -- 4 -- 5

public class AdjacencyList {
    public static void main(String[] args) {
        int n = 5;
        List<Integer>[] adjList = new ArrayList[n+1];

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
        // Print adjacency list
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
