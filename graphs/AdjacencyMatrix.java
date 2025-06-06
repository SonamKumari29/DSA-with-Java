// 1 -- 3
// |     | \
// 2 -- 4 -- 5

public class AdjacencyMatrix {
    public static void main(String[] args) {
        int n = 5;
        int[][] adjMatrix = new int[n+1][n+1];

        // Edges
        adjMatrix[1][2] = 1; adjMatrix[2][1] = 1;
        adjMatrix[1][3] = 1; adjMatrix[3][1] = 1;
        adjMatrix[2][4] = 1; adjMatrix[4][2] = 1;
        adjMatrix[3][4] = 1; adjMatrix[4][3] = 1;
        adjMatrix[3][5] = 1; adjMatrix[5][3] = 1;
        adjMatrix[4][5] = 1; adjMatrix[5][4] = 1;  

        System.out.println("Adjacency Matrix:");
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                System.out.print(adjMatrix[i][j] + " ");
            }
            System.out.println();
        }
    }
}

// Algorithm Flow:
// 1. Initialize an (n+1) x (n+1) matrix with all zeros.
// 2. For each edge (u, v), set adjMatrix[u][v] = 1 and adjMatrix[v][u] = 1 (since the graph is undirected).
// 3. Print the adjacency matrix.
//

// Time Complexity (TC):
// - Matrix initialization: O(n^2)
// - Adding edges: O(E), where E = number of edges
// - Printing the matrix: O(n^2)
// - Overall: O(n^2) (dominated by initialization and printing)
//
// Space Complexity (SC):
// - Adjacency matrix storage: O(n^2)
