
class numOfEnclaves {
    public int numEnclaves(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        for (int i = 0; i < m; i++) {
            if (grid[i][0] == 1) dfs(grid, i, 0);
            if (grid[i][n - 1] == 1) dfs(grid, i, n - 1);
        }

        for (int j = 0; j < n; j++) {
            if (grid[0][j] == 1) dfs(grid, 0, j);
            if (grid[m - 1][j] == 1) dfs(grid, m - 1, j);
        }

        int count = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) count++;
            }
        }

        return count;
    }

    private void dfs(int[][] grid, int i, int j) {
        int m = grid.length;
        int n = grid[0].length;

        if (i < 0 || j < 0 || i >= m || j >= n || grid[i][j] != 1) return;

        grid[i][j] = 0;

        dfs(grid, i + 1, j);
        dfs(grid, i - 1, j);
        dfs(grid, i, j + 1);
        dfs(grid, i, j - 1);
    }

    public static void main(String[] args) {
        numOfEnclaves sol = new numOfEnclaves();
        int[][] grid = {
            {0,0,0,0},
            {1,0,1,0},
            {0,1,1,0},
            {0,0,0,0}
        };
        int result = sol.numEnclaves(grid);
        System.out.println(result); // Output: 3
    }
}

/*
Algorithm Flow:
- Perform DFS from all border cells with value 1 and mark them as 0.
- These cells cannot be enclaves since they are connected to the boundary.
- After DFS, count all remaining 1s in the grid; these are the enclaves.

Time Complexity: O(m × n)

Space Complexity: O(m × n) in the worst case (due to recursion stack in DFS).
- No extra space is used for a visited array.
*/
