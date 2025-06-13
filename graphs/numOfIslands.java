/*
Flow:
1. Traverse each cell in the grid.
2. When a '1' (land) is found, increment island count and start DFS to mark all connected land as visited.
3. DFS marks connected '1's as '0' (visited).
4. Continue until all cells are checked.
*/

public class numOfIslands {
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) return 0; // edge case
        int count = 0; // island counter
        int rows = grid.length, cols = grid[0].length;
        for (int i = 0; i < rows; i++) { // traverse rows
            for (int j = 0; j < cols; j++) { // traverse cols
                if (grid[i][j] == '1') { // found land
                    count++; // new island
                    dfs(grid, i, j); // mark all connected land
                }
            }
        }
        return count; // total islands
    }

    private void dfs(char[][] grid, int i, int j) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] == '0') return; // out of bounds or water
        grid[i][j] = '0'; // mark as visited
        dfs(grid, i + 1, j); // down
        dfs(grid, i - 1, j); // up
        dfs(grid, i, j + 1); // right
        dfs(grid, i, j - 1); // left
    }

    public static void main(String[] args) {
        numOfIslands sol = new numOfIslands();
        char[][] grid = {
            {'1','1','0','0','0'},
            {'1','1','0','0','0'},
            {'0','0','1','0','0'},
            {'0','0','0','1','1'}
        };
        System.out.println(sol.numIslands(grid)); // Output: 3
    }
}

/*
Dry Run:
Input grid:
1 1 0 0 0
1 1 0 0 0
0 0 1 0 0
0 0 0 1 1

Step 1: Find first '1' at (0,0), count=1, mark all connected '1's as '0'.
Step 2: Next '1' at (2,2), count=2, mark as '0'.
Step 3: Next '1' at (3,3), count=3, mark (3,3) and (3,4) as '0'.
No more '1's left. Final count = 3.
*/