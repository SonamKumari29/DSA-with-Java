import java.util.*;
//bfs approach
class matrix01 {
    public int[][] updateMatrix(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;

        int[][] res = new int[m][n];
        boolean[][] visited = new boolean[m][n];
        Queue<int[]> queue = new LinkedList<>();

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (mat[i][j] == 0) {
                    queue.offer(new int[]{i, j});
                    visited[i][j] = true;
                }
            }
        }

        int[][] directions = {{0,1}, {1,0}, {0,-1}, {-1,0}};

        while (!queue.isEmpty()) {
            int[] cell = queue.poll();
            int row = cell[0];
            int col = cell[1];

            for (int[] dir : directions) {
                int r = row + dir[0];
                int c = col + dir[1];

                if (r >= 0 && r < m && c >= 0 && c < n && !visited[r][c]) {
                    res[r][c] = res[row][col] + 1;
                    visited[r][c] = true;
                    queue.offer(new int[]{r, c});
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

        for (int[] row : result) {
            for (int val : row) {
                System.out.print(val + " ");
            }
            System.out.println();
        }
    }
}

/*
Algorithm Flow:
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
