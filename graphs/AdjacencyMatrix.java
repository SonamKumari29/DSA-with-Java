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

        // Print adjacency matrix
        System.out.println("Adjacency Matrix:");
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                System.out.print(adjMatrix[i][j] + " ");
            }
            System.out.println();
        }
    }
}

