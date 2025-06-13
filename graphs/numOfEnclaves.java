// Flow:
// 1. Check all border cells of the grid.
//    - If a border cell is land (1), use DFS to change it and all connected land to water (0).
// 2. The DFS will "flood" all land connected to the edges, turning them into water.
// 3. After flooding, count all the land cells (1) that are left inside the grid.
//    - These are enclaves: land cells not connected to any edge.
// 4. Return the count of these enclave cells.
//
// Time Complexity: O(m * n) where m = number of rows, n = number of columns.
// Space Complexity: O(m * n) due to recursion stack in DFS in the worst case.

class numOfEnclaves { 
    public int numEnclaves(int[][] grid) { 
        int m = grid.length; // Number of rows
        int n = grid[0].length; // Number of columns

        for (int i = 0; i < m; i++) { // Loop through first and last column
            if (grid[i][0] == 1) dfs(grid, i, 0); // If land at left edge, remove it
            if (grid[i][n - 1] == 1) dfs(grid, i, n - 1); // If land at right edge, remove it
        }

        for (int j = 0; j < n; j++) { // Loop through first and last row
            if (grid[0][j] == 1) dfs(grid, 0, j); // If land at top edge, remove it
            if (grid[m - 1][j] == 1) dfs(grid, m - 1, j); // If land at bottom edge, remove it
        }

        int count = 0; // Initialize enclave counter
        for (int i = 0; i < m; i++) { // Loop through grid
            for (int j = 0; j < n; j++) { // Loop through columns
                if (grid[i][j] == 1) count++; // Count remaining land cells
            }
        }

        return count; // number of enclaves
    }

    private void dfs(int[][] grid, int i, int j) { // DFS to remove edge-connected land
        int m = grid.length; // Rows
        int n = grid[0].length; // Columns

        if (i < 0 || j < 0 || i >= m || j >= n || grid[i][j] != 1) return; // Out of bounds or not land

        grid[i][j] = 0; // Mark cell as water

        dfs(grid, i + 1, j); // Down
        dfs(grid, i - 1, j); // Up
        dfs(grid, i, j + 1); // Right
        dfs(grid, i, j - 1); // Left
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
