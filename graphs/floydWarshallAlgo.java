/*
Floyd-Warshall Algorithm Flow:
1. Initialize the distance matrix same as the input graph.
2. For each vertex k, update all pairs (i, j) such that
    dist[i][j] = min(dist[i][j], dist[i][k] + dist[k][j]).
3. Repeat for all vertices as intermediate nodes.
4. Output the shortest distances between every pair of vertices.
*/

public class floydWarshallAlgo {
     final static int INF = 99999; 

     // Function to implement Floyd-Warshall algorithm
     static void floydWarshall(int graph[][], int V) {
          int dist[][] = new int[V][V]; // Distance matrix

          // Initialize distance matrix
          for (int i = 0; i < V; i++)
                for(int j = 0; j < V; j++){
                         if (i == j)
                              dist[i][j] = 0; // Distance to self is 0
                         else if (graph[i][j] == 0)
                              dist[i][j] = INF; // No edge, set to INF
                         else
                              dist[i][j] = graph[i][j]; // Copy graph value
                }

          // Update distances using each vertex as intermediate
          for (int k = 0; k < V; k++) { // Pick all vertices as intermediate
                for (int i = 0; i < V; i++) { // Pick all source vertices
                     for (int j = 0; j < V; j++) { // Pick all destination vertices
                          if (dist[i][k] + dist[k][j] < dist[i][j]) // If shorter path found
                                dist[i][j] = dist[i][k] + dist[k][j]; // Update distance
                     }
                }
          }

          printSolution(dist, V);
     }

     static void printSolution(int dist[][], int V) {
          System.out.println("Shortest distances between every pair of vertices:");
          for (int i = 0; i < V; i++) {
                for (int j = 0; j < V; j++) {
                     if (dist[i][j] == INF)
                          System.out.print("INF "); // No path
                     else
                          System.out.print(dist[i][j] + " "); // Print distance
                }
                System.out.println();
          }
     }

     // Main function
     public static void main(String[] args) {
          /* Example (4 vertices)
              0   5   INF 10
              INF 0   3   INF
              INF INF 0   1
              INF INF INF 0
          */
          int graph[][] = { 
                {0,   5,  INF, 10},
                {INF, 0,   3,  INF},
                {INF, INF, 0,   1},
                {INF, INF, INF, 0}
          };
          int V = 4; 
          floydWarshall(graph, V); 

          // Dry run for path 0->2:
          // Initially: dist[0][2] = INF
          // After k=1: dist[0][2] = min(INF, dist[0][1]+dist[1][2]) = min(INF, 5+3) = 8
          // After k=2: dist[0][3] = min(10, dist[0][2]+dist[2][3]) = min(10, 8+1) = 9
          // Final: dist[0][2]=8, dist[0][3]=9
     }
}