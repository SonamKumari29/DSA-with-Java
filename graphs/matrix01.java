/*
Flow:
1. Add all 0 cells to the queue and mark them as visited.

2. While the queue is not empty:
    - Pop a cell from the queue.
    - For each direction (up, down, left, right):
      - Check if the neighbor is valid and not visited.
      - If valid, set distance = parent distance + 1, mark as visited, and add to queue.

Time Complexity: O(m × n)
- Each cell is processed once.

Space Complexity: O(m × n)
- Used for the queue, visited matrix, and result matrix.
*/

import java.util.*; 

//bfs approach
class matrix01 {
    public int[][] updateMatrix(int[][] mat) {
        int m = mat.length; // rows
        int n = mat[0].length; // cols

        int[][] res = new int[m][n]; // result matrix
        boolean[][] visited = new boolean[m][n]; // visited tracker
        Queue<int[]> queue = new LinkedList<>(); // BFS queue

        for (int i = 0; i < m; i++) { // loop rows
            for (int j = 0; j < n; j++) { // loop cols
                if (mat[i][j] == 0) { // if cell is 0
                    queue.offer(new int[]{i, j}); // add to queue
                    visited[i][j] = true; // mark visited
                }
            }
        }

        int[][] directions = {{0,1}, {1,0}, {0,-1}, {-1,0}}; // right, down, left, up

        while (!queue.isEmpty()) { // while queue not empty
            int[] cell = queue.poll(); // get cell
            int row = cell[0]; // row index
            int col = cell[1]; // col index

            for (int[] dir : directions) { // for each direction
                int r = row + dir[0]; // new row
                int c = col + dir[1]; // new col

                if (r >= 0 && r < m && c >= 0 && c < n && !visited[r][c]) { // valid and not visited
                    res[r][c] = res[row][col] + 1; // set distance
                    visited[r][c] = true; // mark visited
                    queue.offer(new int[]{r, c}); // add to queue
                }
            }
        }

        return res; 
    }

    public static void main(String[] args) {
        matrix01 sol = new matrix01(); 

        int[][] mat = {
            {0, 0, 0},
            {0, 1, 0},
            {1, 1, 1}
        }; 

        int[][] result = sol.updateMatrix(mat); 

        for (int[] row : result) { // print result
            for (int val : row) {
                System.out.print(val + " ");
            }
            System.out.println();
        }
    }
}

